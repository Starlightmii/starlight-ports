package com.oplus.systemui.statusbar.notification.collection;

import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.util.Log;
import androidx.car.app.model.TimerSpan$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationResolverExImpl$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ NotificationResolverExImpl this$0;

    public final void onConfigChanged(Configuration configuration) {
        NotificationResolverExImpl notificationResolverExImpl = this.this$0;
        long uxIconConfig = OplusBaseConfigurationAddon.getUxIconConfig(configuration);
        long themeChanged = OplusBaseConfigurationAddon.getThemeChanged(configuration);
        long themeChangedFlags = OplusBaseConfigurationAddon.getThemeChangedFlags(configuration);
        if (notificationResolverExImpl.mLastIconConfiguration == uxIconConfig && notificationResolverExImpl.mLastThemeChangeFlag == themeChangedFlags && notificationResolverExImpl.mLastThemeChange == themeChanged) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(uxIconConfig, "onConfigChanged update package icon, currentIconConfiguration:", ",currentThemeChangeFlag:");
            sbM.append(themeChangedFlags);
            sbM.append(", currentThemeChange:");
            sbM.append(themeChanged);
            Log.d("SystemUi--Notification", "NotificationResolverExImpl-->".concat(sbM.toString()));
        }
        notificationResolverExImpl.mLastIconConfiguration = uxIconConfig;
        notificationResolverExImpl.mLastThemeChangeFlag = themeChangedFlags;
        notificationResolverExImpl.mLastThemeChange = themeChanged;
        notificationResolverExImpl.clearAllPackageIcons();
        notificationResolverExImpl.appIconGroupColorManager.clearAll();
    }

    public final void onLocaleListChanged() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationResolverExImpl", null, NotificationResolverExImpl$configurationListener$1$onLocaleListChanged$1.INSTANCE, 4);
        ThreadManager companion = ThreadManager.Companion.getInstance();
        NotificationResolverExImpl notificationResolverExImpl = this.this$0;
        NotificationResolverExImpl$configurationListener$1$$ExternalSyntheticLambda0 notificationResolverExImpl$configurationListener$1$$ExternalSyntheticLambda0 = new NotificationResolverExImpl$configurationListener$1$$ExternalSyntheticLambda0();
        notificationResolverExImpl$configurationListener$1$$ExternalSyntheticLambda0.f$0 = notificationResolverExImpl;
        VarHandle.storeStoreFence();
        companion.postInThread(notificationResolverExImpl$configurationListener$1$$ExternalSyntheticLambda0);
        notificationResolverExImpl.appIconGroupColorManager.clearAll();
    }
}
