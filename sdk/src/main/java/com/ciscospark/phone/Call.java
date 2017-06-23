/*
 * Copyright (c) 2016-2017 Cisco Systems Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ciscospark.phone;

import android.util.Log;

import com.cisco.spark.android.callcontrol.CallContext;
import com.cisco.spark.android.callcontrol.CallControlService;
import com.cisco.spark.android.locus.model.LocusData;
import com.cisco.spark.android.locus.model.LocusKey;
import com.cisco.spark.android.locus.model.LocusParticipant;
import com.cisco.spark.android.locus.model.LocusParticipantInfo;
import com.ciscospark.core.SparkApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Allen Xiao<xionxiao@cisco.com>
 * @version 0.1
 */
public class Call {
    private static final String TAG = "Call";

    public enum FacingMode {
        USER,
        ENVIROMENT
    }

    public enum CallStatus {
        INITIATED,
        RINGING,
        CONNECTED,
        DISCONNECTED
    }

    public enum CallType {
        VIDEO,
        AUDIO
    }

    public enum Direction {
        INCOMING,
        OUTGOING
    }

    protected Phone mPhone;
    protected CallStatus status;
    protected CallType calltype;
    protected Direction direction;
    private CallObserver mObserver;
    public LocusKey locusKey;

    @Inject
    CallControlService callControlServcie;

    private List<CallMembership> callMemberships;

    public void setObserver(CallObserver observer) {
        this.mObserver = observer;
    }

    public CallObserver getObserver() {
        return this.mObserver;
    }

    public Call(Phone phone) {
        this.mPhone = phone;
        this.status = CallStatus.INITIATED;
        SparkApplication.getInstance().inject(this);
        callMemberships = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public void answer(CallOption option) {
        Log.i(TAG, "answer call");
        if (direction == Direction.INCOMING) {
            if (status == CallStatus.INITIATED || status == CallStatus.RINGING) {
                Log.i(TAG, "locuskey: " + locusKey.toString());
                CallContext callContext = new CallContext.Builder(locusKey).build();
                Log.i(TAG, "active call is null " + (mPhone.getActiveCall() != null));
                if (mPhone.getActiveCall() != null) {
                    mPhone.getActiveCall().hangup();
                }
                mPhone.setCallOption(option);
                mPhone.setActiveCall(this);
                mPhone.callControlService.joinCall(callContext);
            }
        }
    }

    @SuppressWarnings("unused")
    public void reject() {
        Log.i(TAG, "reject call");
        if (direction == Direction.INCOMING && status == CallStatus.RINGING) {
            Log.i(TAG, "decline call");
            mPhone.callControlService.declineCall(locusKey);
            status = CallStatus.DISCONNECTED;
            /*
            mPhone.callControlService.leaveCall(locusKey);
            mPhone.callControlService.endCall();
            */
        }
    }

    //handup ongoing active call(both in dialing and incall status)
    public void hangup() {
        Log.i(TAG, "hangup call");
        if (this.mPhone.getActiveCall() != this) {
            Log.i(TAG, "this Call is not active call");
            reject();
            return;
        }
        this.mPhone.hangup();
    }

    public void send(String dtmf) {

    }

    public void acknowledge() {

    }

    public List<CallMembership> getMembership() {
        LocusData locusData = callControlServcie.getLocusData(locusKey);
        if (locusData == null) {
            return callMemberships;
        }
        List<LocusParticipant> participants = locusData.getJoinedParticipants();
        callMemberships.clear();
        for (LocusParticipant p : participants) {
            CallMembership member = new CallMembership(p);
            Log.d(TAG, "add member: " + member.toString());
            callMemberships.add(member);
        }
        return callMemberships;
    }

    public int getLocalVideoSize() {
        return 0;
    }

    public int getRemoteVideoSize() {
        return 0;
    }

    public boolean isSendingDTMFEnabled() {
        return false;
    }

    public boolean isRemoteSendingVideo() {
        return false;
    }

    public boolean isRemoteSendingAudio() {
        return false;
    }

    public boolean isSendingVideo() {
        return false;
    }

    public boolean isSendingAudio() {
        return false;
    }

    public FacingMode getFacingMode() {
        return null;
    }

}
