package com.oplus.systemui.navigationbar.gesture.sidegesture.view.animcalculator;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RubberBandBezierCalculator$initAnim$1$2 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ RubberBandBezierCalculator this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                RubberBandBezierCalculator rubberBandBezierCalculator = this.this$0;
                rubberBandBezierCalculator.getCurrentPoint().y = f;
                rubberBandBezierCalculator.updateView();
                break;
            default:
                RubberBandBezierCalculator rubberBandBezierCalculator2 = this.this$0;
                rubberBandBezierCalculator2.getCurrentPoint().x = f;
                rubberBandBezierCalculator2.updateView();
                break;
        }
    }
}
