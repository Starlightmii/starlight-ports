package com.oplus.systemui.notification.row.material;

import android.util.Log;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorModeController$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ NotificationMaterialColorModeController this$0;

    public final void onThemeChanged() {
        NotificationMaterialColorConfig.INSTANCE.onConfigurationChanged("onThemeChanged");
        NotificationMaterialColorModeController notificationMaterialColorModeController = this.this$0;
        BuildersKt.launch$default(notificationMaterialColorModeController.coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new NotificationMaterialColorModeController$configurationListener$1$onThemeChanged$1(notificationMaterialColorModeController, null), 2);
    }

    public final void onUiModeChanged() {
        NotificationMaterialColorModeController notificationMaterialColorModeController = this.this$0;
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(notificationMaterialColorModeController.context);
        if (((Boolean) notificationMaterialColorModeController.getDarkMode().getValue()).booleanValue() != zIsNightMode) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MaterialColorController-->".concat("onUiModeChanged: darkMode new=" + zIsNightMode + ", last=" + notificationMaterialColorModeController.getDarkMode().getValue()));
            }
            NotificationMaterialColorConfig.INSTANCE.onConfigurationChanged("onUiModeChanged");
            notificationMaterialColorModeController._darkMode.setValue(Boolean.valueOf(zIsNightMode));
        }
    }
}
