/*
 * Copyright 2016-2017 Cisco Systems Inc
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

package com.ciscospark.androidsdk.room;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * A data type represents a Room at Cisco Spark cloud.
 * <p>
 * Room has been renamed to Space in Cisco Spark.
 *
 * @since 0.1
 */
public class Room {

    /**
     * The enumeration of the types of a room.
     *
     * @since 0.1
     */
    public enum RoomType {
        /**
         * Group room among multiple people
         *
         * @since 0.1
         */
        @SerializedName("group")
        group,

        /**
         * 1-to-1 room between two people
         *
         * @since 0.1
         */
        @SerializedName("direct")
        direct
    }

    @SerializedName("id")
    private String _id;

    @SerializedName("title")
    private String _title;

    @SerializedName("type")
    private RoomType _type;

    @SerializedName("teamId")
    private String _teamId;

    @SerializedName("isLocked")
    private boolean _isLocked;

    @SerializedName("lastActivity")
    private Date _lastActivity;

    @SerializedName("created")
    private Date _created;

    @SerializedName("sipAddress")
    private String _sipAddress;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return The identifier of this room.
     * @since 0.1
     */
    public String getId() {
        return _id;
    }

    /**
     * @return The title of this room.
     * @since 0.1
     */
    public String getTitle() {
        return _title;
    }

    /**
     * @return The type of this room.
     * @since 0.1
     */
    public RoomType getType() {
        return _type;
    }

    /**
     * @return The team Id that this room associated with.
     * @since 0.1
     */
    public String getTeamId() {
        return _teamId;
    }

    /**
     * @return Indicate if this room is locked.
     * @since 0.1
     */
    public boolean isLocked() {
        return _isLocked;
    }

    /**
     * @return Last activity of this room.
     * @since 0.1
     */
    public Date getLastActivity() {
        return _lastActivity;
    }

    /**
     * @return The timestamp that this room being created.
     * @since 0.1
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * @return The sipAddress that this room associated with.
     * @since 1.4
     */
    public String getSipAddress() {
        return _sipAddress;
    }
}


