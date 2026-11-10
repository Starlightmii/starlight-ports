package com.oplus.systemui.qs.mydevice;

import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MyDevicePanelControllerImpl$mCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ MyDevicePanelControllerImpl this$0;

    public final void onKeyguardVisibilityChanged(boolean z) {
        MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.this$0;
        float f = (z && myDevicePanelControllerImpl.mRemoved) ? 0.0f : 1.0f;
        myDevicePanelControllerImpl.updateViewState(f, f);
    }

    public final void onUserSwitchComplete(int i) {
        PluginManager pluginManager;
        OplusNotificationLogger.logDevicePanel("MyDevicePanelControllerImpl", "onUserSwitchComplete userId: " + i + " onUserSwitchComplete");
        MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.this$0;
        PluginListener pluginListener = myDevicePanelControllerImpl.mPluginListener;
        if (pluginListener == null || (pluginManager = myDevicePanelControllerImpl.mPluginManager) == null) {
            return;
        }
        pluginManager.addPluginListener(pluginListener, QsPanelPlugin.class, true);
    }

    public final void onUserSwitching(int i) {
        PluginManager pluginManager;
        OplusNotificationLogger.logDevicePanel("MyDevicePanelControllerImpl", "onUserSwitching userId: " + i + " removePluginListener");
        MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.this$0;
        PluginListener pluginListener = myDevicePanelControllerImpl.mPluginListener;
        if (pluginListener == null || (pluginManager = myDevicePanelControllerImpl.mPluginManager) == null) {
            return;
        }
        pluginManager.removePluginListener(pluginListener);
    }
}
