package com.oplus.systemui.plugins.qs.seamless;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.OneShotPreDrawListener;
import com.oplus.systemui.plugins.qs.animator.AnimatorUtils;
import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSFakeStatusController$qsPanelExpandFractionListener$1 implements QSPanelExpandFraction.ExpandedFractionChangeListener {
    public /* synthetic */ SeparateQSFakeStatusController this$0;

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionChange() {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionEnd(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionChanged(float f) {
        SeparateQSFakeStatusController separateQSFakeStatusController = this.this$0;
        if (separateQSFakeStatusController.needUpdatePosition) {
            if (!separateQSFakeStatusController.isKeyguardVisible() && !separateQSFakeStatusController.stub.getNtIsFullCollapsed()) {
                separateQSFakeStatusController.separateQsFakeLayout.setVisibility(4);
                ViewGroup viewGroup = separateQSFakeStatusController.statusIconsView;
                if (viewGroup != null) {
                    viewGroup.setTranslationX(0.0f);
                }
                separateQSFakeStatusController.separateQSPrivacyView.setTranslationX(0.0f);
                ViewGroup viewGroup2 = separateQSFakeStatusController.carrierContainer;
                if (viewGroup2 != null) {
                    viewGroup2.setTranslationY(0.0f);
                }
                ViewGroup viewGroup3 = separateQSFakeStatusController.carrierContainer;
                if (viewGroup3 != null) {
                    viewGroup3.setTranslationX(0.0f);
                }
                separateQSFakeStatusController.updateQSClockAndDateState(1.0f);
                return;
            }
            separateQSFakeStatusController.separateQsFakeLayout.setVisibility(0);
            SeparateQSFakeStatusController.access$showOrHideView(separateQSFakeStatusController, separateQSFakeStatusController.qsPanelAnimatorManager.isFirstElementVisible(), false);
            separateQSFakeStatusController.needUpdatePosition = false;
            if (separateQSFakeStatusController.fakeStatusIconHeight == 0.0f) {
                separateQSFakeStatusController.fakeStatusIconHeight = ((Number) separateQSFakeStatusController.getFakeStatusIconView().getRealStatusElementParams().getSecond()).intValue();
                separateQSFakeStatusController.qsPanelAnimatorManager.setPanelDestTranslationY(separateQSFakeStatusController.getPanelDestTranslationY());
            }
            View view = separateQSFakeStatusController.mView;
            SeparateQSFakeStatusController$updateElementTranslationRunnable$1 separateQSFakeStatusController$updateElementTranslationRunnable$1 = new SeparateQSFakeStatusController$updateElementTranslationRunnable$1(1);
            separateQSFakeStatusController$updateElementTranslationRunnable$1.this$0 = separateQSFakeStatusController;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(view, separateQSFakeStatusController$updateElementTranslationRunnable$1);
        }
        separateQSFakeStatusController.updateExtraTranslationY(f);
        separateQSFakeStatusController.updateLeftAreaState(f);
        separateQSFakeStatusController.updateRightArea(f);
        if (f == 0.0f || f == 1.0f) {
            separateQSFakeStatusController.needUpdatePosition = true;
            AnimatorUtils.INSTANCE.setExtraFakeTranslationY(0.0f);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionEnd(boolean z) {
        SeparateQSFakeStatusController separateQSFakeStatusController = this.this$0;
        float f = z ? 1.0f : 0.0f;
        SeparateQSFakeStatusController.Companion companion = SeparateQSFakeStatusController.Companion;
        separateQSFakeStatusController.resetState(f);
        separateQSFakeStatusController.needUpdatePosition = true;
    }
}
