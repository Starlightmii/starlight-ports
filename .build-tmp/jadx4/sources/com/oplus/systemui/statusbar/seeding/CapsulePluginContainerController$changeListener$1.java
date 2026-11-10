package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsulePluginContainerController$changeListener$1 implements SeedlingPlugin.ChangeListener {
    public /* synthetic */ CapsulePluginContainerController this$0;

    public final void onChangeCancel(Bundle bundle) {
    }

    public final void onChangeEnd(Bundle bundle) {
    }

    public final void onChangeStart(Bundle bundle) {
    }

    public final void onPreChange(Bundle bundle) {
        CapsulePluginContainerController capsulePluginContainerController = this.this$0;
        if (bundle.containsKey("hiddenIconList")) {
            capsulePluginContainerController.hiddenStatusBarIconView(bundle);
            return;
        }
        int i = bundle.getInt("placeholder_location", -1);
        int i2 = bundle.getInt("placeholder_width");
        if (i == 0) {
            ConcurrentHashMap concurrentHashMap = capsulePluginContainerController.capsuleWithMap;
            StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
            concurrentHashMap.put(statusBarLocation, Integer.valueOf(i2));
            capsulePluginContainerController.notifyRect(statusBarLocation);
            return;
        }
        if (i == 1) {
            ConcurrentHashMap concurrentHashMap2 = capsulePluginContainerController.capsuleWithMap;
            StatusBarLocation statusBarLocation2 = StatusBarLocation.KEYGUARD;
            concurrentHashMap2.put(statusBarLocation2, Integer.valueOf(i2));
            capsulePluginContainerController.notifyRect(statusBarLocation2);
            return;
        }
        if (i != 3) {
            return;
        }
        ConcurrentHashMap concurrentHashMap3 = capsulePluginContainerController.capsuleWithMap;
        StatusBarLocation statusBarLocation3 = StatusBarLocation.HOME;
        concurrentHashMap3.put(statusBarLocation3, Integer.valueOf(i2));
        ConcurrentHashMap concurrentHashMap4 = capsulePluginContainerController.capsuleWithMap;
        StatusBarLocation statusBarLocation4 = StatusBarLocation.KEYGUARD;
        concurrentHashMap4.put(statusBarLocation4, Integer.valueOf(i2));
        capsulePluginContainerController.notifyRect(statusBarLocation3);
        capsulePluginContainerController.notifyRect(statusBarLocation4);
    }
}
