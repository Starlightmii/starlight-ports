package com.oplus.systemui.monitor;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppLaunchMonitor$listener$1 implements TaskStackChangeListener {
    public /* synthetic */ AppLaunchMonitor this$0;

    public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        String packageName;
        AppLaunchMonitor appLaunchMonitor = this.this$0;
        ComponentName componentName = runningTaskInfo.topActivity;
        if (componentName == null || (packageName = componentName.getPackageName()) == null || !packageName.equals("com.opluskgd.onetaplocking") || !AppLaunchMonitor.access$doesPackageSignatureMatch(appLaunchMonitor)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "AppLaunchMonitor-->onTaskMovedToFront");
        }
        Handler handler = appLaunchMonitor.handler;
        onTaskMovedToFront.2 r0 = new onTaskMovedToFront.2();
        r0.this$0 = appLaunchMonitor;
        VarHandle.storeStoreFence();
        handler.post(r0);
    }
}
