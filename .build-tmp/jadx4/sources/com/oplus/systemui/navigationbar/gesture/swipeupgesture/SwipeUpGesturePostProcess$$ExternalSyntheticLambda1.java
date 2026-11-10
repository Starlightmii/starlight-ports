package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.os.SystemClock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SwipeUpGesturePostProcess$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SwipeUpGesturePostProcess f$0;

    public /* synthetic */ SwipeUpGesturePostProcess$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        SwipeUpGesturePostProcess swipeUpGesturePostProcess = this.f$0;
        switch (i) {
            case 0:
                swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
                break;
            case 1:
                long jUptimeMillis = SystemClock.uptimeMillis();
                swipeUpGesturePostProcess.mDownTime = jUptimeMillis;
                swipeUpGesturePostProcess.sendEvent(4, 0, jUptimeMillis);
                swipeUpGesturePostProcess.sendEvent(4, 1, swipeUpGesturePostProcess.mDownTime);
                break;
            default:
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                swipeUpGesturePostProcess.mDownTime = jUptimeMillis2;
                swipeUpGesturePostProcess.sendEvent(3, 0, jUptimeMillis2);
                swipeUpGesturePostProcess.sendEvent(3, 1, swipeUpGesturePostProcess.mDownTime);
                break;
        }
    }
}
