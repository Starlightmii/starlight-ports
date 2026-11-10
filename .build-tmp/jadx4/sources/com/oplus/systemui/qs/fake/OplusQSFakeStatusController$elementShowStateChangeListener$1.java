package com.oplus.systemui.qs.fake;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.oplusos.systemui.common.rendercache.RenderBooster;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFakeStatusController$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ OplusQSFakeStatusController this$0;

    public final void onElementShowingChanged() {
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        OplusQSFakeStatusController.access$showOrHideView(oplusQSFakeStatusController, oplusQSFakeStatusController.panelAnimationEx.isElementShowing());
        OplusQSFakeStatusController.access$showOrHideCarrierForKeyguard(oplusQSFakeStatusController);
    }

    public final void onFirstElementShowingChanged() {
    }

    public final void onFixedElementShowingChanged() {
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        oplusQSFakeStatusController.showOrHideCarrier(oplusQSFakeStatusController.panelAnimationEx.isFixedElementShowing());
    }

    public final void onShadeShowingChanged(int i, boolean z) {
        RenderBooster renderBoosterEnsureRenderBooster$default;
        RenderBooster renderBoosterEnsureRenderBooster$default2;
        RenderBooster renderBoosterEnsureRenderBooster$default3;
        RenderBooster renderBoosterEnsureRenderBooster$default4;
        RenderBooster renderBoosterEnsureRenderBooster$default5;
        RenderBooster renderBoosterEnsureRenderBooster$default6;
        RenderBooster renderBoosterEnsureRenderBooster$default7;
        RenderBooster renderBoosterEnsureRenderBooster$default8;
        RenderBooster renderBoosterEnsureRenderBooster$default9;
        RenderBooster renderBoosterEnsureRenderBooster$default10;
        RenderBooster renderBoosterEnsureRenderBooster$default11;
        RenderBooster renderBoosterEnsureRenderBooster$default12;
        RenderBooster renderBoosterEnsureRenderBooster$default13;
        RenderBooster renderBoosterEnsureRenderBooster$default14;
        RenderBooster renderBoosterEnsureRenderBooster$default15;
        RenderBooster renderBoosterEnsureRenderBooster$default16;
        RenderBooster renderBoosterEnsureRenderBooster$default17;
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        oplusQSFakeStatusController.updateElementTranslation();
        if (z && i == 0) {
            FrameLayout frameLayout = oplusQSFakeStatusController.fakeCarrierContainer;
            if (frameLayout != null && (renderBoosterEnsureRenderBooster$default17 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default17, 15, false, 6);
            }
            FrameLayout frameLayout2 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
            if (frameLayout2 != null && (renderBoosterEnsureRenderBooster$default16 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout2, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default16, 15, false, 6);
            }
            FrameLayout frameLayout3 = oplusQSFakeStatusController.fakeClockContainer;
            if (frameLayout3 != null && (renderBoosterEnsureRenderBooster$default15 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout3, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default15, 15, false, 6);
            }
            FrameLayout frameLayout4 = oplusQSFakeStatusController.fakeNotificationIconContainer;
            if (frameLayout4 != null && (renderBoosterEnsureRenderBooster$default14 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout4, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default14, 15, false, 6);
            }
            FrameLayout frameLayout5 = oplusQSFakeStatusController.fakeStatusIconContainer;
            if (frameLayout5 != null && (renderBoosterEnsureRenderBooster$default13 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout5, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default13, 15, false, 6);
            }
            FrameLayout frameLayout6 = oplusQSFakeStatusController.fakePrivacyIconContainer;
            if (frameLayout6 != null && (renderBoosterEnsureRenderBooster$default12 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout6, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default12, 15, false, 6);
            }
            RenderBooster.Companion companion = RenderBooster.Companion;
            RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(companion, oplusQSFakeStatusController.fakeKeyguardSystemIcon, null, 3), 15, false, 6);
            FrameLayout frameLayout7 = oplusQSFakeStatusController.keyguardFakeStatusIconContainer;
            if (frameLayout7 != null && (renderBoosterEnsureRenderBooster$default11 = RenderBooster.Companion.ensureRenderBooster$default(companion, frameLayout7, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default11, 4, false, 6);
            }
            RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(companion, oplusQSFakeStatusController.header, null, 3), 1, false, 6);
            ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
            if (mClockContainer != null && (renderBoosterEnsureRenderBooster$default10 = RenderBooster.Companion.ensureRenderBooster$default(companion, mClockContainer, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default10, 15, false, 6);
            }
            ViewGroup mStatusIconsView = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
            if (mStatusIconsView != null && (renderBoosterEnsureRenderBooster$default9 = RenderBooster.Companion.ensureRenderBooster$default(companion, mStatusIconsView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default9, 12, false, 6);
            }
            RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(companion, oplusQSFakeStatusController.statusBarHeader.getView(), null, 3), 4, false, 6);
            RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(companion, oplusQSFakeStatusController.oplusQSFooterViewController.getView(), null, 3), 10, false, 6);
            View settingView = oplusQSFakeStatusController.oplusQSFooterViewController.getSettingView();
            if (settingView != null && (renderBoosterEnsureRenderBooster$default8 = RenderBooster.Companion.ensureRenderBooster$default(companion, settingView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default8, 13, false, 6);
            }
            View clockView = oplusQSFakeStatusController.oplusQSFooterViewController.getClockView();
            if (clockView != null && (renderBoosterEnsureRenderBooster$default7 = RenderBooster.Companion.ensureRenderBooster$default(companion, clockView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default7, 8, false, 6);
            }
            View dateView = oplusQSFakeStatusController.oplusQSFooterViewController.getDateView();
            if (dateView != null && (renderBoosterEnsureRenderBooster$default6 = RenderBooster.Companion.ensureRenderBooster$default(companion, dateView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default6, 4, false, 6);
            }
            AppCompatTextView appCompatTextView = oplusQSFakeStatusController.splitCarrier;
            if (appCompatTextView != null && (renderBoosterEnsureRenderBooster$default5 = RenderBooster.Companion.ensureRenderBooster$default(companion, appCompatTextView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default5, 6, false, 6);
            }
            ConstraintLayout constraintLayout = oplusQSFakeStatusController.fakeLeft;
            if (constraintLayout != null && (renderBoosterEnsureRenderBooster$default4 = RenderBooster.Companion.ensureRenderBooster$default(companion, constraintLayout, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default4, 4, false, 6);
            }
            ConstraintLayout constraintLayout2 = oplusQSFakeStatusController.fakeRight;
            if (constraintLayout2 != null && (renderBoosterEnsureRenderBooster$default3 = RenderBooster.Companion.ensureRenderBooster$default(companion, constraintLayout2, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default3, 4, false, 6);
            }
            ConstraintLayout constraintLayout3 = oplusQSFakeStatusController.qsCarrierLeft;
            if (constraintLayout3 != null && (renderBoosterEnsureRenderBooster$default2 = RenderBooster.Companion.ensureRenderBooster$default(companion, constraintLayout3, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default2, 4, false, 6);
            }
            ViewGroup viewGroup = oplusQSFakeStatusController.quickStatus;
            if (viewGroup == null || (renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(companion, viewGroup, null, 3)) == null) {
                return;
            }
            RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default, 4, false, 6);
        }
    }
}
