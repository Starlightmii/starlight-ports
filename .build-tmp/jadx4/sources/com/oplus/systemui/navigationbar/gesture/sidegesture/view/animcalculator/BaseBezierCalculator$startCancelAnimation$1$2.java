package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseBezierCalculator$startCancelAnimation$1$2 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ BaseBezierCalculator this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        BaseBezierCalculator baseBezierCalculator = this.this$0;
        baseBezierCalculator.getCurrentPoint().x = f;
        baseBezierCalculator.updateView();
    }
}
