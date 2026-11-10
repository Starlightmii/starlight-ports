package com.oplus.systemui.statusbar.notification;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.statusbar.PulseExpansionHandlerExt;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationClickerLogger;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.stack.StackCardControl;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationClicker extends NotificationClicker {
    public NotificationClickerLogger mLogger;
    public NotificationActivityStarter mNotificationActivityStarter;
    public PowerInteractor mPowerInteractor;
    public PulseExpansionHandlerExt mPulseExpansionHandlerExt;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class Builder extends NotificationClicker.Builder {
        public final NotificationClickerLogger mLogger;
        public final PowerInteractor mPowerInteractor;
        public final PulseExpansionHandlerExt mPulseExpansionHandlerExt;

        public Builder(NotificationClickerLogger notificationClickerLogger, PowerInteractor powerInteractor, PulseExpansionHandlerExt pulseExpansionHandlerExt) {
            super(notificationClickerLogger, powerInteractor);
            this.mLogger = notificationClickerLogger;
            this.mPowerInteractor = powerInteractor;
            this.mPulseExpansionHandlerExt = pulseExpansionHandlerExt;
        }

        public final NotificationClicker build(NotificationActivityStarter notificationActivityStarter) {
            NotificationClickerLogger notificationClickerLogger = this.mLogger;
            PowerInteractor powerInteractor = this.mPowerInteractor;
            OplusNotificationClicker oplusNotificationClicker = new OplusNotificationClicker(notificationClickerLogger, powerInteractor);
            oplusNotificationClicker.mLogger = notificationClickerLogger;
            oplusNotificationClicker.mPowerInteractor = powerInteractor;
            oplusNotificationClicker.mNotificationActivityStarter = notificationActivityStarter;
            oplusNotificationClicker.mPulseExpansionHandlerExt = this.mPulseExpansionHandlerExt;
            VarHandle.storeStoreFence();
            return oplusNotificationClicker;
        }
    }

    public final void onClick(View view) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        if (!(view instanceof ExpandableNotificationRow)) {
            Log.e("OplusNotificationClicker", "NotificationClicker called on a view that is not a notification row.");
            return;
        }
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
        if (expandableNotificationRow.mIsHeadsUp) {
            NotificationStatisticUtil.collectNotificationMenuEvent(expandableNotificationRow.getContext(), expandableNotificationRow.getEntry(), "", "click", 1000);
        } else {
            NotificationStatisticUtil.collectNotificationMenuEvent(expandableNotificationRow.getContext(), expandableNotificationRow.getEntry(), "", "click", ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState());
        }
        if (!expandableNotificationRow.isVisibleToUser() && !((OplusNotificationEntryExImpl) expandableNotificationRow.getEntry().getEx()).isClickFromIcdf) {
            LogUtil.i(LogUtil.TAG_NOTIFICATION, "OplusNotificationClicker", "notification is not visible and not from icdf, return");
            return;
        }
        ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow.getEntry().row;
        if (expandableNotificationRow2 != null && expandableNotificationRow2.mLaunchAnimationRunning) {
            LogUtil.i(LogUtil.TAG_NOTIFICATION, "OplusNotificationClicker", "notification is running the expanding anim, return");
            return;
        }
        ExpandableViewState viewState = expandableNotificationRow.getViewState();
        if (viewState != null && (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) != null && oplusExpandableViewStateEx.getNotificationStackedProgress() != 0.0f) {
            ILockScreenExtStateController iLockScreenExtStateController = (ILockScreenExtStateController) DependencyEx.sDependency.getDependency(ILockScreenExtStateController.class);
            if (((StackCardControl) DependencyEx.sDependency.getDependency(StackCardControl.class)).isSinkingStaticStackState()) {
                iLockScreenExtStateController.goToExtState(true, 0, "ExpandClicked:sinking_to_stack", 125, null);
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusNotificationClicker", "Notification--sinking static stacked notification click, switch to top stack");
                return;
            }
        }
        NotificationEntry entry = expandableNotificationRow.getEntry();
        if (entry.getSbn().getNotification() != null) {
            Notification notification = entry.getSbn().getNotification();
            if (notification.contentIntent == null && notification.fullScreenIntent == null && !expandableNotificationRow.getEntry().isBubble() && !notification.isGroupSummary()) {
                LogUtil.i(LogUtil.TAG_NOTIFICATION, "OplusNotificationClicker", "NOTIFICATION_CLICK-->click not work,  key = " + entry.getEx().getEncryptionKey() + " , not has Intent and not bubble and not group summary");
                return;
            }
        }
        this.mPowerInteractor.wakeUpIfDozing("NOTIFICATION_CLICK", 4);
        this.mLogger.logOnClick(expandableNotificationRow.getLoggingKey());
        if (NotificationClicker.isMenuVisible(expandableNotificationRow)) {
            this.mLogger.logMenuVisible(expandableNotificationRow.getLoggingKey());
            expandableNotificationRow.animateResetTranslation();
            return;
        }
        if (expandableNotificationRow.isChildInGroup() && NotificationClicker.isMenuVisible(expandableNotificationRow.getNotificationParent())) {
            this.mLogger.logParentMenuVisible(expandableNotificationRow.getLoggingKey());
            expandableNotificationRow.getNotificationParent().animateResetTranslation();
            return;
        }
        if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.mChildrenExpanded) {
            this.mLogger.logChildrenExpanded(expandableNotificationRow.getLoggingKey());
            return;
        }
        if (expandableNotificationRow.areGutsExposed()) {
            this.mLogger.logGutsExposed(expandableNotificationRow.getLoggingKey());
            return;
        }
        expandableNotificationRow.setJustClicked(true);
        OplusNotificationClicker$$ExternalSyntheticLambda0 oplusNotificationClicker$$ExternalSyntheticLambda0 = new OplusNotificationClicker$$ExternalSyntheticLambda0();
        oplusNotificationClicker$$ExternalSyntheticLambda0.f$0 = expandableNotificationRow;
        VarHandle.storeStoreFence();
        DejankUtils.postAfterTraversal(oplusNotificationClicker$$ExternalSyntheticLambda0);
        if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isExpandable() && !this.mPulseExpansionHandlerExt.isDisableDragSbWhenInKg() && expandableNotificationRow.getShowingLayout() != expandableNotificationRow.getPublicLayout()) {
            expandableNotificationRow.performAccessibilityActionInternal(OplusAnimationController.FLAG_CANCEL_FIXED_ROTATION, (Bundle) null);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusNotificationClicker", "Notification--NotificationClicker performExpandClick");
        } else {
            OplusNotificationEntryExImpl oplusNotificationEntryExImpl = (OplusNotificationEntryExImpl) expandableNotificationRow.getEntry().getEx();
            if (oplusNotificationEntryExImpl.isClickFromIcdf) {
                oplusNotificationEntryExImpl.setClickFromIcdf(false);
            }
            this.mNotificationActivityStarter.onNotificationClicked(entry, expandableNotificationRow);
        }
    }
}
