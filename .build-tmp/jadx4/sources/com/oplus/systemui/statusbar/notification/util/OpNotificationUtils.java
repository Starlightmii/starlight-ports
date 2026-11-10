package com.oplus.systemui.statusbar.notification.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.systemui.Dependency;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.HeadsUpToZoomUtils;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplus.zoomwindow.OplusZoomWindowManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OpNotificationUtils {
    public static PendingIntent getDefaultIntent(Notification notification) {
        if (notification == null) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "OpNotificationUtils", "getPIntentByNotification entry is null");
            return null;
        }
        PendingIntent pendingIntent = notification.contentIntent;
        return pendingIntent != null ? pendingIntent : notification.fullScreenIntent;
    }

    public static boolean isBackgroundIntentInternal(Context context, int i, PendingIntent pendingIntent) {
        return (context == null || pendingIntent == null || pendingIntent.getIntent() == null || HeadsUpToZoomUtils.calculateFlexibleWindowBounds(pendingIntent.getIntent(), context, i).getInt("androidx.flexible.ResizeMode", 0) != 4) ? false : true;
    }

    public static boolean isGrayscaleOplus(ImageView imageView, OplusContrastColorUtil oplusContrastColorUtil, Notification notification, Boolean bool) {
        if (bool.booleanValue() || OplusNotificationSmallIconUtil.useAppIconForSmallIcon(notification)) {
            return false;
        }
        Object tag = imageView.getTag(2131363396);
        if (tag != null) {
            return Boolean.TRUE.equals(tag);
        }
        boolean zIsGrayscaleIcon = oplusContrastColorUtil.isGrayscaleIcon(imageView.getDrawable());
        imageView.setTag(2131363396, Boolean.valueOf(zIsGrayscaleIcon));
        return zIsGrayscaleIcon;
    }

    public static boolean isNormalScreen() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            return false;
        }
        return !FeatureOption.isLargeScreenFolding() || DeviceStateController.getInstance().isFolding();
    }

    /* JADX WARN: Code duplicated, block: B:42:0x007d  */
    public static boolean isSupportZoonByIntent(Context context, NotificationEntry notificationEntry, PendingIntent pendingIntent, boolean z, boolean z2) {
        String packageName;
        boolean zIsSupportZoomMode;
        if (context == null || notificationEntry == null || notificationEntry.isBubble() || notificationEntry.getSbn() == null) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "OpNotificationUtils", "isSupportZoonByIntent entry is error");
            return false;
        }
        Notification notification = notificationEntry.getSbn().getNotification();
        if (pendingIntent == null) {
            pendingIntent = getDefaultIntent(notification);
        }
        if (pendingIntent != null && pendingIntent.isActivity() && (!z || (notification != null && notification.contentView == null && notification.bigContentView == null && notification.headsUpContentView == null))) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
            if (keyguardStateControllerImpl == null) {
                LogUtil.e(LogUtil.TAG_NOTIFICATION, "OpNotificationUtils", "isSupportZoonByIntent keyguardStateController is null");
                return false;
            }
            if ((z2 || !keyguardStateControllerImpl.mShowing) && !HeadsUpToZoomUtils.isZoomOneHandMode(context)) {
                int userId = notificationEntry.getSbn().getUserId();
                if (userId == -1) {
                    userId = ActivityManagerWrapper.getInstance().getCurrentUserId();
                }
                Intent intent = pendingIntent.getIntent();
                if (intent != null) {
                    ComponentName component = intent.getComponent();
                    if (component != null) {
                        packageName = component.getPackageName();
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("[isSupportZoonByIntent] componentName pkg=", packageName, LogUtil.TAG_NOTIFICATION, "OpNotificationUtils");
                    } else {
                        packageName = intent.getPackage();
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("[isSupportZoonByIntent] originIntent pkg=", packageName, LogUtil.TAG_NOTIFICATION, "OpNotificationUtils");
                    }
                    if (TextUtils.isEmpty(packageName)) {
                        packageName = notificationEntry.getSbn().getPackageName();
                    }
                    String strFlattenToShortString = component != null ? component.flattenToShortString() : "";
                    if (TextUtils.isEmpty(strFlattenToShortString)) {
                        zIsSupportZoomMode = OplusZoomWindowManager.getInstance().isSupportZoomMode(packageName, userId, "com.android.systemui", (Bundle) null);
                    } else if (OplusZoomWindowManager.getInstance().isSupportZoomMode(strFlattenToShortString, userId, "com.android.systemui", (Bundle) null) && OplusZoomWindowManager.getInstance().isSupportZoomMode(packageName, userId, "com.android.systemui", (Bundle) null)) {
                        zIsSupportZoomMode = true;
                    } else {
                        zIsSupportZoomMode = false;
                    }
                } else {
                    zIsSupportZoomMode = false;
                }
                if (zIsSupportZoomMode) {
                    return isBackgroundIntentInternal(context, notificationEntry.getSbn().getUserId(), pendingIntent);
                }
            }
        }
        return false;
    }
}
