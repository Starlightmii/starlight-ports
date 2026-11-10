package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.content.Context;
import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.collection.FloatList$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SwipeUpGesturePostProcess {
    public SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 mBackEventRunnable;
    public Handler mBackgroundHandler;
    public Context mContext;
    public long mDownTime;
    public boolean mHasSwipeOnceInMisTouch;
    public SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 mHomeEventRunnable;
    public ArrayList mMisTouchAppWhiteList;
    public SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 mMisTouchRunnable;
    public boolean mMistouchEnable;
    public Toast mNotifyMisTouchToast;
    public SwipeUpGestureManager mSwipeUpGestureManager;
    public SwipeUpGesturePostProcess$$ExternalSyntheticLambda0 mUpGestureMistouchStateListener;

    public final void sendEvent(int i, int i2, long j) {
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("sendEvent action:", i2, i, ",code:", ",flags:0,when:");
        sbM.append(j);
        LogUtil.d(LogUtil.TAG_NAVBAR, "SwipeUpGesturePostProcess", sbM.toString());
        InputManagerGlobal.getInstance().injectInputEvent(new KeyEvent(this.mDownTime, j, i2, i, 0, 0, -1, 0, 72, 257), 0);
    }
}
