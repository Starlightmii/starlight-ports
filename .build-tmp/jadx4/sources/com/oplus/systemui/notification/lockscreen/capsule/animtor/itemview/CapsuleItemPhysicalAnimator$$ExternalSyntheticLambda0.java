package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ CapsuleItemPhysicalAnimator f$0;
    public /* synthetic */ CapsuleItemView f$1;

    public /* synthetic */ CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.beforeDisappearAnim(this.f$1);
                break;
            case 1:
                this.f$0.afterDisappearAnim(this.f$1);
                break;
            case 2:
                this.f$0.afterAppearAnim(this.f$1);
                break;
            case 3:
                this.f$0.afterDisappearAnim(this.f$1);
                break;
            case 4:
                CapsuleItemPhysicalAnimator capsuleItemPhysicalAnimator = this.f$0;
                CapsuleItemView capsuleItemView = this.f$1;
                capsuleItemPhysicalAnimator.beforeAppearAnim(capsuleItemView);
                CapsuleItemView.Companion companion = CapsuleItemView.Companion;
                capsuleItemView.startStrokeAnimation(0.25f);
                break;
            default:
                this.f$0.afterAppearAnim(this.f$1);
                break;
        }
        return Unit.INSTANCE;
    }
}
