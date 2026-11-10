package com.oplus.systemui.notification.headsup.nssl;

import com.android.systemui.notification.stackednotification.StackedNotificationEx;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.panelanimation.notification.OplusNotificationRowShowAnimationExImpl;
import com.oplus.systemui.statusbar.notification.animation.RowAppearAnimationExImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import dagger.Lazy;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpNsslStateManager {
    private final Lazy notificationMaterialColorEx;
    private final Lazy notificationRowShowAnimationEx;
    private final Lazy rowAppearAnimationEx;
    private final Lazy stackedNotificationEx;
    private final VisualStabilityCoordinator visualStabilityCoordinator;
    private final VisualStabilityProvider visualStabilityProvider;

    public HeadsUpNsslStateManager(Lazy lazy, Lazy lazy2, Lazy lazy3, VisualStabilityCoordinator visualStabilityCoordinator, VisualStabilityProvider visualStabilityProvider, Lazy lazy4) {
        this.rowAppearAnimationEx = lazy;
        this.stackedNotificationEx = lazy2;
        this.notificationRowShowAnimationEx = lazy3;
        this.visualStabilityCoordinator = visualStabilityCoordinator;
        this.visualStabilityProvider = visualStabilityProvider;
        this.notificationMaterialColorEx = lazy4;
    }

    public final void clearTempState(ExpandableNotificationRow expandableNotificationRow) {
        List<ExpandableNotificationRow> attachedChildren;
        expandableNotificationRow.setAlpha(1.0f);
        expandableNotificationRow.setTransitionAlpha(1.0f);
        expandableNotificationRow.setActualHeight(expandableNotificationRow.getIntrinsicHeight(), false);
        expandableNotificationRow.setDismissUsingRowTranslationX(false, false);
        expandableNotificationRow.setTranslation(0.0f);
        expandableNotificationRow.setHeadsUpAnimatingAway(false);
        expandableNotificationRow.setClipTopAmount(0);
        expandableNotificationRow.setClipBottomAmount(0);
        expandableNotificationRow.getViewState().clipTopAmount = 0;
        expandableNotificationRow.getViewState().clipBottomAmount = 0;
        ((RowAppearAnimationExImpl) this.rowAppearAnimationEx.get()).resetAppearAnimation(expandableNotificationRow);
        ((StackedNotificationEx) this.stackedNotificationEx.get()).clearNotificationStackedState(expandableNotificationRow);
        Object obj = this.notificationRowShowAnimationEx.get();
        OplusNotificationRowShowAnimationExImpl oplusNotificationRowShowAnimationExImpl = obj instanceof OplusNotificationRowShowAnimationExImpl ? (OplusNotificationRowShowAnimationExImpl) obj : null;
        if (oplusNotificationRowShowAnimationExImpl != null) {
            oplusNotificationRowShowAnimationExImpl.resetViewState(expandableNotificationRow, false);
        }
        ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).applyColorModeForHeadsUp(expandableNotificationRow, true);
        if (!expandableNotificationRow.mIsSummaryWithChildren || (attachedChildren = expandableNotificationRow.getAttachedChildren()) == null) {
            return;
        }
        for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
            expandableNotificationRow2.getClass();
            clearTempState(expandableNotificationRow2);
        }
    }

    public final void resumeNsslState(ExpandableNotificationRow expandableNotificationRow) {
        ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).applyColorModeForHeadsUp(expandableNotificationRow, false);
        Object obj = this.notificationRowShowAnimationEx.get();
        OplusNotificationRowShowAnimationExImpl oplusNotificationRowShowAnimationExImpl = obj instanceof OplusNotificationRowShowAnimationExImpl ? (OplusNotificationRowShowAnimationExImpl) obj : null;
        if (oplusNotificationRowShowAnimationExImpl != null) {
            oplusNotificationRowShowAnimationExImpl.resetViewState(expandableNotificationRow, FeatureOption.getUseLayeredPanelAnimation());
        }
    }

    public final void setReorderingAllowed(boolean z) {
        if (z) {
            this.visualStabilityCoordinator.updateAllowedStates("oplusHeadsUpDismiss", true, false);
        } else {
            this.visualStabilityProvider.setReorderingAllowed(false);
        }
    }
}
