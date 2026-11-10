package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleItemPhysicalAnimator$$ExternalSyntheticLambda3 implements Function1 {
    public /* synthetic */ CapsuleItemPhysicalAnimator f$0;
    public /* synthetic */ CapsuleItemView f$1;
    public /* synthetic */ CapsuleItemView f$2;
    public /* synthetic */ boolean f$3;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.View, com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public final Object invoke(Object obj) {
        CapsuleItemPhysicalAnimator capsuleItemPhysicalAnimator = this.f$0;
        CapsuleItemView capsuleItemView = this.f$1;
        CapsuleItemView capsuleItemView2 = this.f$2;
        boolean z = this.f$3;
        UpdateData updateData = (UpdateData) obj;
        capsuleItemPhysicalAnimator.resetCenterPivotXY(capsuleItemView);
        capsuleItemPhysicalAnimator.resetCenterPivotXY(capsuleItemView2);
        boolean z2 = updateData.isPhase1;
        ?? r3 = z2 ? capsuleItemView2 : capsuleItemView;
        if (!z2) {
            capsuleItemView = capsuleItemView2;
        }
        float p1BlurRadius = z2 ? updateData.getP1BlurRadius() : updateData.getP2BlurRadius();
        if (!updateData.isPhase1) {
            ViewExpandKtKt.setScaleXY(capsuleItemView.getContentContainer(), 1.0f, 1.0f);
        }
        if (updateData.getEarTransX() == Float.MIN_VALUE) {
            CapsuleEarController earController = r3.getEarController();
            if (earController != null) {
                earController.setEarVisible(false);
            }
        } else {
            CapsuleEarController earController2 = r3.getEarController();
            if (earController2 != null) {
                earController2.setEarVisible(true);
            }
            CapsuleEarController earController3 = r3.getEarController();
            if (earController3 != null) {
                earController3.setTransitionX(updateData.getEarTransX());
            }
        }
        capsuleItemView.setAlpha(0.0f);
        r3.getContentContainer().setAlpha(1.0f);
        r3.getViewState().setAlpha(1.0f, "CapsuleItemAnimator.updateSwitchView");
        r3.updateWidth(updateData.getWidth());
        r3.updateHeight(updateData.getHeight());
        if (z) {
            r3.updateStrokeViewSize((int) updateData.getWidth(), (int) updateData.getHeight());
        }
        ((ExpandableViewState) r3.getViewState()).mExpandableViewStateEx.setBlurRadio(p1BlurRadius);
        r3.getViewState().applyToView(r3);
        CapsuleItemPhysicalAnimator.logUpdater(r3, "MultiWidthUpdater:" + updateData);
        return Unit.INSTANCE;
    }
}
