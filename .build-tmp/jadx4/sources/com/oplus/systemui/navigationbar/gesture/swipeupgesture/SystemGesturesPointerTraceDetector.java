package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.os.Handler;
import android.view.MotionEvent;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemGesturesPointerTraceDetector {
    public int mBottomSwipeStartThreshold;
    public SwipeUpGestureManager.AnonymousClass1 mCallbacks;
    public long mDownTime;
    public float mDownX;
    public float mDownY;
    public int mReason;
    public float mScreenHeight;
    public SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 mSwipeUpFailRunnable;
    public boolean mSwipeUpFireable;
    public Handler mSwipeUpGestureUIHandler;
    public int mTopSwipeStartThreshold;

    public final void captureDown(MotionEvent motionEvent) {
        this.mDownX = motionEvent.getX();
        this.mDownY = motionEvent.getY();
        this.mDownTime = motionEvent.getDownTime();
        LogUtil.d(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "down x=" + this.mDownX + " y=" + this.mDownY);
    }

    public final void removeFailCallback() {
        Handler handler = this.mSwipeUpGestureUIHandler;
        SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 = this.mSwipeUpFailRunnable;
        if (handler.hasCallbacks(systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0)) {
            handler.removeCallbacks(systemGesturesPointerTraceDetector$$ExternalSyntheticLambda0);
        }
    }

    public void setSwipeUpThreshold(int i) {
        this.mBottomSwipeStartThreshold = i;
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mBottomSwipeStartThreshold, LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", new StringBuilder(" mBottomSwipeStartThreshold="));
    }
}
