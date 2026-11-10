package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda6 implements Function0 {
    public /* synthetic */ CapsuleItemPhysicalAnimator f$0;
    public /* synthetic */ CapsuleItemView f$1;
    public /* synthetic */ CapsuleItemView f$2;

    public final Object invoke() {
        CapsuleItemPhysicalAnimator capsuleItemPhysicalAnimator = this.f$0;
        CapsuleItemView capsuleItemView = this.f$1;
        CapsuleItemView capsuleItemView2 = this.f$2;
        capsuleItemPhysicalAnimator.beforeAppearAnim(capsuleItemView);
        capsuleItemPhysicalAnimator.beforeDisappearAnim(capsuleItemView2);
        return Unit.INSTANCE;
    }
}
