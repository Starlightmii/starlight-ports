package com.oplus.systemui.notification.utlils;

import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.NotificationClickNotifier;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Locale;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationUtil {
    public static final OplusNotificationUtil INSTANCE = new OplusNotificationUtil();
    private static final Set<String> SPECIAL_HEIGHT_LANGUAGES = SetsKt__SetsJVMKt.setOf("my");
    private static final String TAG = "OplusNotificationUtils";

    public static final boolean isSpecialHeightLanguage() {
        return SPECIAL_HEIGHT_LANGUAGES.contains(Locale.getDefault().getLanguage());
    }

    public static final void notifyClickToNms(String str) {
        NotificationClickNotifier notificationClickNotifier = (NotificationClickNotifier) DependencyEx.sDependency.getDependency(NotificationClickNotifier.class);
        NotificationVisibilityProviderImpl notificationVisibilityProviderImpl = (NotificationVisibilityProviderImpl) DependencyEx.sDependency.getDependency(NotificationVisibilityProviderImpl.class);
        if (str != null) {
            NotificationVisibility notificationVisibilityObtain = notificationVisibilityProviderImpl != null ? notificationVisibilityProviderImpl.obtain(str, true) : null;
            if (notificationVisibilityObtain != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("[notifyClickToNms] open float window notificationKey: ", str, "OplusNotificationUtils-->", "SystemUi--Notification");
                }
                if (notificationClickNotifier != null) {
                    notificationClickNotifier.onNotificationClick(str, notificationVisibilityObtain);
                }
            }
        }
    }
}
