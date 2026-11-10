package com.oplus.systemui.plugins.qs.bottom;

import com.oplus.systemui.qs.base.springtransition.FadeTransition;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSBottomComponent this$0;

    public /* synthetic */ OplusQSBottomComponent$onExitEditMode$$inlined$doOnPreDraw$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                FadeTransition fadeTransition = (FadeTransition) this.this$0.getTransitionProperties().fetchTransitionProperty(this.this$0.indicatorContainer, FadeTransition.class);
                this.this$0.indicatorAnimator.playAnimation(((Number) fadeTransition.getStartValue()).floatValue(), ((Number) fadeTransition.getEndValue()).floatValue());
                break;
            default:
                FadeTransition fadeTransition2 = (FadeTransition) this.this$0.getTransitionProperties().fetchTransitionProperty(this.this$0.indicatorContainer, FadeTransition.class);
                this.this$0.indicatorAnimator.playAnimation(((Number) fadeTransition2.getStartValue()).floatValue(), ((Number) fadeTransition2.getEndValue()).floatValue());
                break;
        }
    }
}
