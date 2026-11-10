package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.view.MotionEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SwipeUpGestureManager$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SwipeUpGestureManager f$0;

    @Override // java.lang.Runnable
    public final void run() {
        SwipeUpGestureManager swipeUpGestureManager = this.f$0;
        MotionEvent motionEvent = swipeUpGestureManager.mCancelEvent;
        if (motionEvent != null) {
            swipeUpGestureManager.addPointerEvent(motionEvent);
            if (swipeUpGestureManager.mCancelEvent != null) {
                swipeUpGestureManager.mCancelEvent = null;
            }
            swipeUpGestureManager.mHasMoveEvent = false;
            swipeUpGestureManager.mIsValidTouchEvent = false;
            swipeUpGestureManager.mIsSendDownEvent = false;
            swipeUpGestureManager.mGestureDownState = 1001;
        }
    }
}
