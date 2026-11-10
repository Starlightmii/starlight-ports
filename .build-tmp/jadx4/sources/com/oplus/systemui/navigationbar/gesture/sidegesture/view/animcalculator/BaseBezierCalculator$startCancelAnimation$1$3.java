package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.SideGestureNavView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseBezierCalculator$startCancelAnimation$1$3 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ BaseBezierCalculator this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        SideGestureNavView sideGestureNavView = (SideGestureNavView) this.this$0.getCallback();
        sideGestureNavView.exitAnimProcessing = false;
        if (sideGestureNavView.hasDownEvent || sideGestureNavView.getVisibility() == 8) {
            return;
        }
        sideGestureNavView.resetState();
    }
}
