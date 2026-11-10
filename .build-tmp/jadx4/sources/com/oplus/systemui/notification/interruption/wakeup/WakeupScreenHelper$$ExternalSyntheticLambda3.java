package com.oplus.systemui.notification.interruption.wakeup;

import android.app.NotificationChannel;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Pair;
import com.android.systemui.Dependency;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.notification.interruption.OplusWakeupInterruptionUtils;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WakeupScreenHelper$$ExternalSyntheticLambda3 implements WakeupScreenHelper.Filter {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ WakeupScreenHelper f$0;

    public /* synthetic */ WakeupScreenHelper$$ExternalSyntheticLambda3(int i) {
        this.$r8$classId = i;
    }

    @Override // com.oplus.systemui.notification.interruption.wakeup.WakeupScreenHelper.Filter
    public final Pair shouldWakeup(NotificationEntry notificationEntry) {
        Pair pairCreate;
        int i = this.$r8$classId;
        WakeupScreenHelper wakeupScreenHelper = this.f$0;
        switch (i) {
            case 0:
                boolean zNeedInterruptWakeUp = false;
                int intForUser = Settings.System.getIntForUser(wakeupScreenHelper.mContext.getContentResolver(), "keyguard_notice_wakelock_state", 0, -2);
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(intForUser, "Notification--isNoticeWakeUpSwitchTurnedOn value = ", LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper");
                if (intForUser != 1) {
                    return Pair.create(Boolean.FALSE, "switch not on");
                }
                NotifFilter excludingFilter = notificationEntry.getExcludingFilter();
                if (excludingFilter != null && "KeyguardCoordinator".equals(excludingFilter.getName())) {
                    return Pair.create(Boolean.FALSE, "notification was filtered by: " + excludingFilter);
                }
                if (notificationEntry.getSbn().isOngoing() && !WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST.contains(notificationEntry.getSbn().getPackageName())) {
                    return Pair.create(Boolean.FALSE, "onGoing");
                }
                if (notificationEntry.getSbn().getNotification().extras.getInt("key_option_stow") == -1) {
                    return Pair.create(Boolean.FALSE, "entry option is show");
                }
                if (wakeupScreenHelper.mKeyguardHelper.shouldShowOnKeyguard(notificationEntry.getSbn())) {
                    pairCreate = wakeupScreenHelper.mKeyguardNotificationVisibilityProvider.shouldHideNotification(notificationEntry) ? Pair.create(Boolean.FALSE, "notification should hide on keyguard") : Pair.create(Boolean.TRUE, "can show on keyguard");
                } else {
                    pairCreate = Pair.create(Boolean.FALSE, "can not show on keyguard");
                }
                if (!((Boolean) pairCreate.first).booleanValue()) {
                    return pairCreate;
                }
                if (notificationEntry.getSbn() != null && notificationEntry.getSbn().getNotification() != null) {
                    if (wakeupScreenHelper.mNotificationMediaManager == null) {
                        wakeupScreenHelper.mNotificationMediaManager = (NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class);
                    }
                    if (!notificationEntry.getSbn().getNotification().isMediaNotification() && !wakeupScreenHelper.mNotificationMediaManager.getOplusNotificationMediaManagerExt().isOplusCustomMediaNotification(notificationEntry)) {
                        ((NotificationDependOnKeyguardImpl) wakeupScreenHelper.mDependOnKeyguard).getClass();
                        LockDeadUtil.INSTANCE.getClass();
                        if (LockDeadUtil.isLockDeadState) {
                            return Pair.create(Boolean.FALSE, "isLockDeadState");
                        }
                        if (Util.isChildrenOrStudyCenterMode()) {
                            return Pair.create(Boolean.FALSE, "isChildrenOrStudyCenterMode");
                        }
                        if (wakeupScreenHelper.mListEntries.isEmpty()) {
                            return Pair.create(Boolean.FALSE, "not in rend list");
                        }
                        NotificationKeyguardHelper notificationKeyguardHelper = wakeupScreenHelper.mKeyguardHelper;
                        StatusBarNotification sbn = notificationEntry.getSbn();
                        if (sbn == null) {
                            notificationKeyguardHelper.getClass();
                        } else {
                            zNeedInterruptWakeUp = OplusWakeupInterruptionUtils.needInterruptWakeUp(notificationKeyguardHelper.mContext, sbn.getPackageName());
                        }
                        return zNeedInterruptWakeUp ? Pair.create(Boolean.FALSE, "need interrupt wakeup for blacklist") : Pair.create(Boolean.TRUE, "need wakeup");
                    }
                }
                return Pair.create(Boolean.FALSE, "entry is media");
            default:
                if (wakeupScreenHelper.mZenModeSettingsObserver.mZenModeStatusOn) {
                    NotificationChannel channel = notificationEntry.getChannel();
                    if (notificationEntry.shouldSuppressVisualEffect(256)) {
                        return Pair.create(Boolean.FALSE, "zenMode on shouldSuppress");
                    }
                    if (!wakeupScreenHelper.mZenModeKeyguardSettingsObserver.mZenModeKeyguardScreenStatus && channel != null && !channel.canBypassDnd()) {
                        return Pair.create(Boolean.FALSE, "zenMode on and channel no bypass dnd");
                    }
                }
                return Pair.create(Boolean.TRUE, null);
        }
    }
}
