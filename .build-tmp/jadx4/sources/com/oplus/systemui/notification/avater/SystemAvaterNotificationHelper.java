package com.oplus.systemui.notification.avater;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.ui.spatial.RectList$;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.Dependency;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SystemAvaterNotificationHelper {
    public static SystemAvaterNotificationHelper sInstance;
    public final String[] mBlackList;
    public int mCurrentUserId;
    public final LockPatternUtils mLockPatternUtils;
    public final NotificationManager mNotificationManager;
    public int mMultiSystemUserId = -10000;
    public int mMultiSystemNotifications = 0;
    public int mMainSystemNotifications = 0;
    public int mSendNotificationUserId = -10000;

    public SystemAvaterNotificationHelper(Context context) {
        this.mCurrentUserId = -10000;
        this.mBlackList = context.getResources().getStringArray(2130903301);
        this.mNotificationManager = (NotificationManager) context.getApplicationContext().getSystemService(NotificationManager.class);
        this.mCurrentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        this.mLockPatternUtils = new LockPatternUtils(context);
    }

    public static synchronized SystemAvaterNotificationHelper getInstance(Context context) {
        try {
            if (sInstance == null) {
                synchronized (SystemAvaterNotificationHelper.class) {
                    try {
                        if (sInstance == null) {
                            sInstance = new SystemAvaterNotificationHelper(context);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return sInstance;
    }

    private int getMultiSystemUserId() {
        for (UserInfo userInfo : ((UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class)).getUserProfiles()) {
            if (userInfo != null && (userInfo.flags & 536870912) != 0) {
                return userInfo.id;
            }
        }
        return -10000;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0069  */
    public final void checkAndShowSystemAvatarNotification(Context context, StatusBarNotification statusBarNotification) {
        boolean z;
        String quantityString;
        if (this.mMultiSystemUserId == -10000) {
            int multiSystemUserId = getMultiSystemUserId();
            this.mMultiSystemUserId = multiSystemUserId;
            if (multiSystemUserId == -10000) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "SystemAvaterNotificationHelper", "getMultiSystemUserId is DEFALUT_USERID");
                return;
            }
        }
        int userId = statusBarNotification.getUserId();
        int shareState = SystemAvaterShareStateObserver.getInstance().getShareState();
        int id = statusBarNotification.getId();
        String packageName = statusBarNotification.getPackageName();
        int i = statusBarNotification.getNotification().flags;
        boolean zIsGroupSummary = statusBarNotification.getNotification().isGroupSummary();
        int i2 = 0;
        if ((i & 2) != 0) {
            z = false;
            break;
        }
        String[] strArr = this.mBlackList;
        int length = strArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                if (shareState != 1 || userId != this.mMultiSystemUserId) {
                    if (shareState == 2) {
                        if ((OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userId)) ? 0 : userId) != 0) {
                            if (shareState == 3) {
                                break;
                            }
                        }
                    } else if (shareState == 3) {
                        break;
                    }
                }
                z = true;
                break;
            }
            if (!strArr[i3].equals(packageName)) {
                i3++;
            }
            z = false;
            break;
        }
        int i4 = OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userId)) ? 0 : userId;
        boolean z2 = ((i4 == this.mCurrentUserId || (i4 != 0 && userId != this.mMultiSystemUserId)) ? false : OplusMultiUserManager.getInstance().hasMultiSystemUser()) && 10010 != id && !zIsGroupSummary && z;
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(shareState, "state:", " userId: ");
        RectList$.ExternalSyntheticOutline0.m(this.mCurrentUserId, userId, " notificationUserId: ", " mMultiSystemUserId: ", sbM);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(this.mMultiSystemUserId, " isGroupSummary:", " needbuildNotifications: ", sbM, zIsGroupSummary);
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NOTIFICATION, "SystemAvaterNotificationHelper", sbM, z2);
        if (z2) {
            StringBuilder sbM2 = ListPopupWindow$.ExternalSyntheticOutline0.m(userId, "buildNotifications notificationUserId : ", "  mCurrentUserId:");
            sbM2.append(this.mCurrentUserId);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "SystemAvaterNotificationHelper", sbM2.toString());
            Intent intentPutExtra = new Intent().setAction("android.intent.switch_user").putExtra("android.intent.extra.user_id", OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userId)) ? 0 : userId);
            intentPutExtra.setPackage(context.getPackageName());
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intentPutExtra, 201326592);
            Bundle bundle = new Bundle();
            if (userId == this.mMultiSystemUserId) {
                this.mMultiSystemNotifications++;
                Resources resources = context.getResources();
                int i5 = this.mMultiSystemNotifications;
                quantityString = resources.getQuantityString(2131820565, i5, Integer.valueOf(i5));
                bundle.putString("android.substName", context.getString(2131956752));
            } else {
                this.mMainSystemNotifications++;
                Resources resources2 = context.getResources();
                int i6 = this.mMainSystemNotifications;
                quantityString = resources2.getQuantityString(2131820566, i6, Integer.valueOf(i6));
                bundle.putString("android.substName", context.getString(2131956758));
            }
            Notification notificationBuild = new Notification.Builder(context, "SYSTEM_AVERTOR_NOTICE_CHANNEL").setExtras(bundle).setSmallIcon(2131234591).setColor(context.getColor(R.color.system_notification_accent_color)).setWhen(System.currentTimeMillis()).setShowWhen(true).setContentText(quantityString).setGroup("TAG_SYSTEM_AVATER_NOTIFICATION").setStyle(new Notification.BigTextStyle().bigText(quantityString)).setFullScreenIntent(broadcast, false).setContentIntent(broadcast).build();
            int i7 = this.mCurrentUserId;
            if (i7 == 0 || i7 == this.mMultiSystemUserId) {
                i2 = i7;
            } else {
                if (OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userId))) {
                    userId = 0;
                }
                if (userId == 0) {
                    i2 = this.mMultiSystemUserId;
                }
            }
            this.mSendNotificationUserId = i2;
            this.mNotificationManager.notifyAsUser("TAG_SYSTEM_AVATER_NOTIFICATION", 10010, notificationBuild, UserHandle.of(i2));
        }
    }

    public final void clearNotificationCount(boolean z) {
        int i;
        StringBuilder sb = new StringBuilder("clearNotificationCount  mSendNotificationUserId=");
        sb.append(this.mSendNotificationUserId);
        sb.append("  currentUser:");
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mCurrentUserId, LogUtil.TAG_NOTIFICATION, "SystemAvaterNotificationHelper", sb);
        int i2 = this.mSendNotificationUserId;
        if (i2 == -10000) {
            return;
        }
        if (z || (((i = this.mCurrentUserId) == 0 || i == this.mMultiSystemUserId) && i != i2)) {
            this.mNotificationManager.cancelAsUser("TAG_SYSTEM_AVATER_NOTIFICATION", 10010, UserHandle.of(i2));
            this.mMultiSystemNotifications = 0;
            this.mMainSystemNotifications = 0;
            this.mSendNotificationUserId = -10000;
        }
    }

    public void setCurrentUserId(int i) {
        this.mCurrentUserId = i;
    }
}
