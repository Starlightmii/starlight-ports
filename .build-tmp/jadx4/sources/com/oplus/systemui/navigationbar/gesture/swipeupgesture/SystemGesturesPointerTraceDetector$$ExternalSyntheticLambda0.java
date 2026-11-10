package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SystemGesturesPointerTraceDetector$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SystemGesturesPointerTraceDetector f$0;

    @Override // java.lang.Runnable
    public final void run() {
        SystemGesturesPointerTraceDetector systemGesturesPointerTraceDetector = this.f$0;
        if (systemGesturesPointerTraceDetector.mSwipeUpFireable) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "SystemGesturesPointerTraceDetector", "SIWPE_TOUCH_EVENT_GAP_TIME  TIME OUT");
            systemGesturesPointerTraceDetector.mSwipeUpFireable = false;
            systemGesturesPointerTraceDetector.mCallbacks.getClass();
            LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "mSystemGesturesCallbacks   onSwipeFail ");
            SwipeUpGestureManager.injectAllEvent();
        }
    }
}
