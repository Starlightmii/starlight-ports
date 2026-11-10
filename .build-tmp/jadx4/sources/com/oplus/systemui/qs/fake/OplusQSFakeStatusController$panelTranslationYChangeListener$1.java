package com.oplus.systemui.qs.fake;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.OneShotPreDrawListener;
import com.android.systemui.panelanimation.PanelAnimationEx;
import java.lang.invoke.VarHandle;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFakeStatusController$panelTranslationYChangeListener$1 implements PanelAnimationEx.PanelTranslationYChangeListener {
    public /* synthetic */ OplusQSFakeStatusController this$0;

    public final void onTranslationYChanged() {
        OplusQSFakeStatusController oplusQSFakeStatusController = this.this$0;
        if (oplusQSFakeStatusController.updateMonitor.isKeyguardVisible$1()) {
            if (oplusQSFakeStatusController.keyguardNeedUpdate) {
                oplusQSFakeStatusController.keyguardNeedUpdate = false;
                View view = oplusQSFakeStatusController.mView;
                OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 = new OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1(2);
                oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1.this$0 = oplusQSFakeStatusController;
                VarHandle.storeStoreFence();
                OneShotPreDrawListener.add(view, oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1);
            }
            oplusQSFakeStatusController.keyguardFraction = 1.0f - RangesKt___RangesKt.coerceIn((-oplusQSFakeStatusController.panelAnimationEx.getTranslationYValue().getTranslationYValue()) / OplusQSFakeStatusController.Companion.getDefault_setting_translationy(), 0.0f, 1.0f);
            OplusQSFakeStatusController.access$showOrHideCarrierForKeyguard(oplusQSFakeStatusController);
            if (!oplusQSFakeStatusController.isTabletLandscape()) {
                FrameLayout frameLayout = oplusQSFakeStatusController.keyguardFakeStatusIconContainer;
                if (frameLayout != null) {
                    frameLayout.setTranslationX(oplusQSFakeStatusController.keyguardFraction * oplusQSFakeStatusController.statusIconTranslationX);
                }
                FrameLayout frameLayout2 = oplusQSFakeStatusController.keyguardFakeStatusIconContainer;
                if (frameLayout2 != null) {
                    frameLayout2.setTranslationY(oplusQSFakeStatusController.keyguardFraction * oplusQSFakeStatusController.statusIconTranslationY);
                }
                FrameLayout frameLayout3 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
                if (frameLayout3 != null) {
                    frameLayout3.setTranslationX(oplusQSFakeStatusController.keyguardFraction * oplusQSFakeStatusController.carrierContainerTranslationX);
                }
                FrameLayout frameLayout4 = oplusQSFakeStatusController.keyguardFakeCarrierContainer;
                if (frameLayout4 != null) {
                    frameLayout4.setTranslationY(oplusQSFakeStatusController.keyguardFraction * oplusQSFakeStatusController.carrierContainerTranslationY);
                }
                ViewGroup mStatusIconsView = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
                if (mStatusIconsView != null) {
                    mStatusIconsView.setTranslationX((oplusQSFakeStatusController.keyguardFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationX);
                }
                ViewGroup mStatusIconsView2 = oplusQSFakeStatusController.statusBarHeader.getMStatusIconsView();
                if (mStatusIconsView2 != null) {
                    mStatusIconsView2.setTranslationY((oplusQSFakeStatusController.keyguardFraction - 1.0f) * oplusQSFakeStatusController.statusIconTranslationY);
                }
                ViewGroup mClockContainer = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
                if (mClockContainer != null) {
                    mClockContainer.setTranslationX((oplusQSFakeStatusController.keyguardFraction - 1.0f) * oplusQSFakeStatusController.carrierContainerTranslationX);
                }
                ViewGroup mClockContainer2 = oplusQSFakeStatusController.statusBarHeader.getMClockContainer();
                if (mClockContainer2 != null) {
                    mClockContainer2.setTranslationY((oplusQSFakeStatusController.keyguardFraction - 1.0f) * oplusQSFakeStatusController.carrierContainerTranslationY);
                }
            }
            float f = oplusQSFakeStatusController.keyguardFraction;
            if (f == 0.0f || f == 1.0f) {
                oplusQSFakeStatusController.keyguardNeedUpdate = true;
            }
        }
    }
}
