package com.oplus.systemui.shade;

import android.util.MathUtils;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.QuickSettingsControllerEx;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.oplus.systemui.qs.helper.QSFragmentHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQuickSettingsControllerExImp extends QuickSettingsControllerEx {
    public void coverQsEffect(QuickSettingsControllerImpl quickSettingsControllerImpl, float f, int i) {
        int iMin;
        int height;
        float fConstrain;
        int iCalculateBottomPosition = quickSettingsControllerImpl.calculateBottomPosition(quickSettingsControllerImpl.computeExpansionFraction());
        boolean z = quickSettingsControllerImpl.getBarState() == 1;
        NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) quickSettingsControllerImpl.mPanelViewControllerLazy.get();
        if (quickSettingsControllerImpl.mSplitShadeEnabled) {
            if (notificationPanelViewController.mExpandingFromHeadsUp) {
                iMin = Math.min(iCalculateBottomPosition, quickSettingsControllerImpl.getLargeScreenShadeHeaderHeight());
            } else {
                iMin = Math.min(quickSettingsControllerImpl.getLargeScreenShadeHeaderHeight() + ((int) quickSettingsControllerImpl.getHeaderTranslation()), quickSettingsControllerImpl.getLargeScreenShadeHeaderHeight());
            }
            height = quickSettingsControllerImpl.getNotificationStackScrollLayoutController().getHeight() + iMin;
        } else {
            if (quickSettingsControllerImpl.getTransitioningToFullShadeProgress() > 0.0f) {
                iCalculateBottomPosition = quickSettingsControllerImpl.getTransitionToFullShadePosition();
            } else if (!z || !quickSettingsControllerImpl.getKeyguardBypassController().getBypassEnabled()) {
                iCalculateBottomPosition = (int) (z ? Math.min(iCalculateBottomPosition, quickSettingsControllerImpl.getEdgePosition()) : quickSettingsControllerImpl.getEdgePosition());
            }
            iMin = iCalculateBottomPosition + ((int) notificationPanelViewController.mOverStretchAmount);
            height = notificationPanelViewController.getView().getBottom();
        }
        int iMin2 = Math.min(iMin, height);
        if (iMin2 != i) {
            iMin2 = Math.min((int) (iMin2 - f), i);
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        float stackY = quickSettingsControllerImpl.getAmbientState().getStackY();
        float quickQsHeaderHeight = quickSettingsControllerImpl.getQuickQsHeaderHeight();
        qSFragmentHelper.getClass();
        int i2 = iMin2 <= 0 ? (int) quickQsHeaderHeight : iMin2;
        float f2 = stackY - quickQsHeaderHeight;
        if (f2 < 0.0f) {
            fConstrain = 1.0f;
        } else {
            float f3 = stackY - i2;
            if (f3 != 0.0f && f2 == 0.0f) {
                f2 = f3;
            }
            fConstrain = MathUtils.constrain(f3 / f2, 0.0f, 1.0f);
            if (stackY == 0.0f && fConstrain == 1.0f) {
                fConstrain = 0.0f;
            }
            if (fConstrain < 0.001f) {
                fConstrain = 0.0f;
            }
        }
        boolean z2 = quickSettingsControllerImpl.getBarState() == 1;
        if (quickSettingsControllerImpl.mSplitShadeEnabled || z2) {
            QSFragmentHelper qSFragmentHelper2 = QSFragmentHelper.getInstance();
            qSFragmentHelper2.mConverFraction = 0.0f;
            if (qSFragmentHelper2.mHasReset) {
                return;
            }
            qSFragmentHelper2.onFractionUpdated(0.0f, true);
            qSFragmentHelper2.mHasReset = true;
            return;
        }
        boolean z3 = ((QuickSettingsControllerEx) this).isClosing && ((fConstrain > 1.0f ? 1 : (fConstrain == 1.0f ? 0 : -1)) == 0) && ((float) iMin2) >= quickSettingsControllerImpl.getQuickQsHeaderHeight();
        boolean z4 = (quickSettingsControllerImpl.getAmbientState().getStackY() == 0.0f || iMin2 == 0 || Math.abs(quickSettingsControllerImpl.getAmbientState().getStackY() - ((float) iMin2)) >= 1.0f) ? false : true;
        float qsExpansionFraction = QSFragmentHelper.getInstance().getQsExpansionFraction();
        if ((getExpandedFraction() != 1.0f && !z3 && z4) || qsExpansionFraction != 0.0f || quickSettingsControllerImpl.mQsVisible) {
            QSFragmentHelper qSFragmentHelper3 = QSFragmentHelper.getInstance();
            qSFragmentHelper3.mConverFraction = 0.0f;
            if (qSFragmentHelper3.mHasReset) {
                return;
            }
            qSFragmentHelper3.onFractionUpdated(0.0f, true);
            qSFragmentHelper3.mHasReset = true;
            return;
        }
        QSFragmentHelper qSFragmentHelper4 = QSFragmentHelper.getInstance();
        quickSettingsControllerImpl.getAmbientState().getStackY();
        quickSettingsControllerImpl.getQuickQsHeaderHeight();
        if (fConstrain != qSFragmentHelper4.mConverFraction) {
            qSFragmentHelper4.mConverFraction = fConstrain;
            qSFragmentHelper4.onFractionUpdated(fConstrain, false);
        }
    }

    public final boolean isCoveringOrCoveredQs() {
        return QSFragmentHelper.getInstance().isCoveringOrCoveredQs();
    }

    public final boolean isOnLockscreenNtfCenter() {
        return ((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isOnLockscreenNtfCenter();
    }

    public boolean isQsFalseTouch(FalsingManager falsingManager, boolean z) {
        return false;
    }

    public void updateNotificationScrimViewAlpha(QuickSettingsControllerImpl quickSettingsControllerImpl) {
        quickSettingsControllerImpl.mScrimController.getExt().setNotificationsAlpha(ShadeInterpolation.getContentAlpha(1.0f - (!quickSettingsControllerImpl.mSplitShadeEnabled ? quickSettingsControllerImpl.mNotificationStackScrollLayoutController.getView().getQsExpansionFraction() : 0.0f)));
    }
}
