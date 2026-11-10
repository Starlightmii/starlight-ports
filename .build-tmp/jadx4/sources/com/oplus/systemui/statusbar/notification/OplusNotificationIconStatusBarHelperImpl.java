package com.oplus.systemui.statusbar.notification;

import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.notification.OplusNotificationIconStatusBarHelper;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconStatusBarHelperImpl extends OplusNotificationIconStatusBarHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationIconStatusBarHelperImpl";
    private final Lazy notificationCenterHelper;
    private final NotificationMediaManager notificationMediaManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationIconStatusBarHelperImpl(Lazy lazy, NotificationMediaManager notificationMediaManager) {
        this.notificationCenterHelper = lazy;
        this.notificationMediaManager = notificationMediaManager;
    }

    public boolean isOplusNormalSilent(NotificationEntry notificationEntry) {
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isExpRegion() || this.notificationMediaManager.getOplusNotificationMediaManagerExt().isMediaNotification(notificationEntry) || notificationEntry.getImportance() >= 3 || NotificationUtilsKt.isWechatCallingNotification(notificationEntry)) ? false : true;
    }

    public boolean oplusShouldShowIcon(NotificationEntry notificationEntry) {
        if (notificationEntry.getRow().getVisibility() == 8) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("entry:", notificationEntry.getKey(), " hide for row", "OplusNotificationIconStatusBarHelperImpl-->", "SystemUi--Statusbar");
            }
            return false;
        }
        if (((NotificationCenterHelper) this.notificationCenterHelper.get()).canShowIcon(notificationEntry)) {
            return true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("entry:", notificationEntry.getKey(), " hide for notificationCenterHelper", "OplusNotificationIconStatusBarHelperImpl-->", "SystemUi--Statusbar");
        }
        return false;
    }
}
