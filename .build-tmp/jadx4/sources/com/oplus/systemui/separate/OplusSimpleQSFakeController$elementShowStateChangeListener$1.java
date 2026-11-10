package com.oplus.systemui.separate;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.statusbar.policy.Clock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplusos.systemui.common.rendercache.RenderBooster;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ OplusSimpleQSFakeController this$0;

    public final void onShadeShowingChanged(int i, boolean z) {
        ImageView settingsButton;
        RenderBooster renderBoosterEnsureRenderBooster$default;
        ViewGroup nITZContainer;
        RenderBooster renderBoosterEnsureRenderBooster$default2;
        OplusQSDateView dateView;
        RenderBooster renderBoosterEnsureRenderBooster$default3;
        Clock clockView;
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
        OplusSimpleQSFakeController oplusSimpleQSFakeController = this.this$0;
        if (z && i == 0) {
            FrameLayout frameLayout = oplusSimpleQSFakeController.fakeClockViewContainer;
            if (frameLayout != null && (renderBoosterEnsureRenderBooster$default13 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default13, 15, false, 6);
            }
            FrameLayout frameLayout2 = oplusSimpleQSFakeController.fakeStatusBarIconsContainer;
            if (frameLayout2 != null && (renderBoosterEnsureRenderBooster$default12 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout2, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default12, 13, false, 6);
            }
            FrameLayout frameLayout3 = oplusSimpleQSFakeController.fakeDynamicIconsContainer;
            if (frameLayout3 != null && (renderBoosterEnsureRenderBooster$default11 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout3, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default11, 13, false, 6);
            }
            FrameLayout frameLayout4 = oplusSimpleQSFakeController.fakeNotificationContainer;
            if (frameLayout4 != null && (renderBoosterEnsureRenderBooster$default10 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout4, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default10, 13, false, 6);
            }
            FrameLayout frameLayout5 = oplusSimpleQSFakeController.fakeCarrierContainer;
            if (frameLayout5 != null && (renderBoosterEnsureRenderBooster$default9 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, frameLayout5, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default9, 9, false, 6);
            }
            ViewGroup viewGroup = oplusSimpleQSFakeController.oplusStatusBarIconContainer;
            if (viewGroup != null && (renderBoosterEnsureRenderBooster$default8 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, viewGroup, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default8, 6, false, 6);
            }
            ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusQSFakeFixedContainer;
            if (viewGroup2 != null && (renderBoosterEnsureRenderBooster$default7 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, viewGroup2, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default7, 4, false, 6);
            }
            ViewGroup viewGroup3 = oplusSimpleQSFakeController.oplusQSFakeContainer;
            if (viewGroup3 != null && (renderBoosterEnsureRenderBooster$default6 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, viewGroup3, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default6, 4, false, 6);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader != null && (renderBoosterEnsureRenderBooster$default5 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, oplusQSSimpleHeader, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default5, 4, false, 6);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader2 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader2 != null && (clockView = oplusQSSimpleHeader2.getClockView()) != null && (renderBoosterEnsureRenderBooster$default4 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, clockView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default4, 15, false, 6);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader3 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader3 != null && (dateView = oplusQSSimpleHeader3.getDateView()) != null && (renderBoosterEnsureRenderBooster$default3 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, dateView, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default3, 4, false, 6);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader4 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader4 != null && (nITZContainer = oplusQSSimpleHeader4.getNITZContainer()) != null && (renderBoosterEnsureRenderBooster$default2 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, nITZContainer, null, 3)) != null) {
                RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default2, 4, false, 6);
            }
            OplusQSSimpleHeader oplusQSSimpleHeader5 = oplusSimpleQSFakeController.oplusQSSimpleHeader;
            if (oplusQSSimpleHeader5 == null || (settingsButton = oplusQSSimpleHeader5.getSettingsButton()) == null || (renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, settingsButton, null, 3)) == null) {
                return;
            }
            RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default, 12, false, 6);
        }
    }
}
