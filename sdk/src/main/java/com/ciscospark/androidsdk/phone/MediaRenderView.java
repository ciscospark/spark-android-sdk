package com.ciscospark.androidsdk.phone;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;

import com.webex.wseclient.WseSurfaceView;


public class MediaRenderView extends WseSurfaceView {
    public MediaRenderView(Context context) {
        super(context);
    }

    public MediaRenderView(Context var1, AttributeSet var2) {
        super(var1, var2);
    }

    public MediaRenderView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
    }

    @TargetApi(21)
    public MediaRenderView(Context var1, AttributeSet var2, int var3, int var4) {
        super(var1, var2, var3, var4);
    }
}
