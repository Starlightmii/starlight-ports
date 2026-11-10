package com.oplus.systemui.statusbar;

import android.app.Notification;
import android.app.admin.DevicePolicyManager;
import android.content.pm.UserInfo;
import android.util.SparseArray;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.OplusNotificationLockscreenUserManagerEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationLockscreenUserManagerExImpl extends OplusNotificationLockscreenUserManagerEx {
    private final Lazy devicePolicyManager;
    private NotificationLockscreenUserManager notificationLockscreenUserManager;
    private final Lazy privacyHelper;

    public OplusNotificationLockscreenUserManagerExImpl(Lazy lazy, Lazy lazy2) {
        this.devicePolicyManager = lazy;
        this.privacyHelper = lazy2;
    }

    public void init(NotificationLockscreenUserManager notificationLockscreenUserManager) {
        this.notificationLockscreenUserManager = notificationLockscreenUserManager;
    }

    public boolean needsRedaction(NotificationEntry notificationEntry, int i, SparseArray<UserInfo> sparseArray) {
        if (notificationEntry == null) {
            return false;
        }
        if (((PrivacyHelper) this.privacyHelper.get()).shouldHideNotiContentWhenLockScreen(notificationEntry, notificationEntry.getKey())) {
            return true;
        }
        int userId = notificationEntry.getSbn().getUserId();
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = this.notificationLockscreenUserManager;
        boolean z = (notificationLockscreenUserManagerImpl == null || notificationLockscreenUserManagerImpl.userAllowsPrivateNotificationsInPublic(i)) ? false : true;
        boolean zContains = sparseArray.contains(userId);
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl2 = this.notificationLockscreenUserManager;
        boolean z2 = (!zContains && z) || (notificationLockscreenUserManagerImpl2 != null && !notificationLockscreenUserManagerImpl2.userAllowsPrivateNotificationsInPublic(userId));
        Notification notification = notificationEntry.getSbn().getNotification();
        return notification != null && notification.visibility == 0 && z2;
    }

    public final boolean updateLockscreenNotificationSetting(int i) {
        boolean z = (((DevicePolicyManager) this.devicePolicyManager.get()).getKeyguardDisabledFeatures(null, i) & 4) == 0;
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = this.notificationLockscreenUserManager;
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl2 = notificationLockscreenUserManagerImpl instanceof NotificationLockscreenUserManagerImpl ? notificationLockscreenUserManagerImpl : null;
        Boolean boolValueOf = notificationLockscreenUserManagerImpl2 != null ? Boolean.valueOf(notificationLockscreenUserManagerImpl2.mShowLockscreenNotifications) : null;
        if (notificationLockscreenUserManagerImpl2 != null) {
            notificationLockscreenUserManagerImpl2.setShowLockscreenNotifications(z);
        }
        return !Intrinsics.areEqual(boolValueOf, Boolean.valueOf(z));
    }
}
