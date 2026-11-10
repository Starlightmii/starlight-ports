package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureMistouchPreventionObserver;
import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SwipeUpGesturePostProcess$$ExternalSyntheticLambda0 implements IObserverListener {
    public /* synthetic */ SwipeUpGesturePostProcess f$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        SwipeUpGesturePostProcess swipeUpGesturePostProcess = this.f$0;
        swipeUpGesturePostProcess.mMistouchEnable = false;
        if (NavStateObserver.INSTANCE.getNavState() == 2 && SwipeUpGestureMistouchPreventionObserver.INSTANCE.getSwipeUpGestureMistouchPrevention() == 1) {
            swipeUpGesturePostProcess.mMistouchEnable = true;
        }
        swipeUpGesturePostProcess.mHasSwipeOnceInMisTouch = false;
    }
}
