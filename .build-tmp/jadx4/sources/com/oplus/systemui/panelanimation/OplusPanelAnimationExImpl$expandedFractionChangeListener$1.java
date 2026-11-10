package com.oplus.systemui.panelanimation;

import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewControllerEx;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplusos.systemui.common.util.NotificationShadeRegionHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$expandedFractionChangeListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public boolean hasExclusionDone;
    public float lastFraction;
    public /* synthetic */ OplusPanelAnimationExImpl this$0;

    public final boolean getHasExclusionDone() {
        return this.hasExclusionDone;
    }

    public final float getLastFraction() {
        return this.lastFraction;
    }

    public final void onFractionChanged(float f) {
        boolean separateMode = SeparateModeHelper.INSTANCE.getSeparateMode();
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
        boolean z = true;
        if (separateMode) {
            SysUiStateControllerImpl sysUiStateControllerImpl = oplusPanelAnimationExImpl.sysUiStateController;
            sysUiStateControllerImpl.separateNtExpandFraction = f;
            float fMax = Math.max(sysUiStateControllerImpl.separateQSExpandFraction, f);
            if (sysUiStateControllerImpl.panelExpandFraction != fMax) {
                sysUiStateControllerImpl.panelExpandFraction = fMax;
                sysUiStateControllerImpl.notifyExpandFractionChange(fMax, 2);
            }
        } else {
            SysUiStateControllerImpl sysUiStateControllerImpl2 = oplusPanelAnimationExImpl.sysUiStateController;
            if (sysUiStateControllerImpl2.panelExpandFraction != f) {
                sysUiStateControllerImpl2.panelExpandFraction = f;
                sysUiStateControllerImpl2.notifyExpandFractionChange(f, 1);
            }
        }
        oplusPanelAnimationExImpl.updateElementShowing(f, this.lastFraction);
        boolean z2 = oplusPanelAnimationExImpl.getPanelExpandFraction().getRawFraction() < 0.2f && oplusPanelAnimationExImpl.getPanelExpandFraction().getRawFraction() > 0.0f;
        if (((OplusSeparateNotificationAndQSEx) oplusPanelAnimationExImpl.separateNotificationAndQSEx.get()).isFullyCollapsed() && ((Boolean) ((PlatformBlurManager) oplusPanelAnimationExImpl.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue() && z2 && !this.hasExclusionDone) {
            NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) oplusPanelAnimationExImpl.notificationPanelViewController.get();
            if (notificationPanelViewController == null || !notificationPanelViewController.isClosing() || (oplusPanelAnimationExImpl.keyguardStateController.mShowing && !oplusPanelAnimationExImpl.keyguardStateController.mOccluded)) {
                z = false;
            }
            this.hasExclusionDone = NotificationShadeRegionHelper.setTouchExclusionRegion(z, ((NotificationPanelViewController) oplusPanelAnimationExImpl.notificationPanelViewController.get()).getView().getRootView(), "notification onFractionChanged");
        }
        NotificationPanelViewController notificationPanelViewController2 = (NotificationPanelViewController) oplusPanelAnimationExImpl.notificationPanelViewController.get();
        NotificationPanelViewControllerEx ext = notificationPanelViewController2.getExt();
        if (ext != null) {
            ext.onHeightUpdatedExt(notificationPanelViewController2.getExpandedHeight(), notificationPanelViewController2.getQsController(), notificationPanelViewController2.mSplitShadeEnabled);
        }
        this.lastFraction = f;
    }

    public final void onFractionEnd(float f) {
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
        oplusPanelAnimationExImpl.setFlingToHeight(false);
        if (f == 0.0f || f == 1.0f) {
            OplusPanelAnimationExImpl.access$expandedFractionEnd(oplusPanelAnimationExImpl);
            this.hasExclusionDone = false;
            oplusPanelAnimationExImpl.setBreakCollapse(false);
        }
    }

    public final void setHasExclusionDone(boolean z) {
        this.hasExclusionDone = z;
    }

    public final void setLastFraction(float f) {
        this.lastFraction = f;
    }
}
