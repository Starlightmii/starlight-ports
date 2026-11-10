package com.oplus.systemui.navigationbar.gesture;

import android.app.ActivityManager;
import android.content.ComponentName;
import com.android.systemui.shared.system.ActivityManagerWrapper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AppSettingsHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ AppSettingsHandler f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        AppSettingsHandler appSettingsHandler = this.f$0;
        switch (i) {
            case 0:
                AppSettingsHandler.$r8$lambda$yN_SWR9ER6LInxdglP6do805j_o(appSettingsHandler);
                break;
            default:
                if (appSettingsHandler.mDisablePkg != null || appSettingsHandler.mGestureRegionReduceEnablePkg != null) {
                    ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
                    ComponentName componentName = runningTask == null ? null : runningTask.topActivity;
                    String packageName = componentName != null ? componentName.getPackageName() : "_UNKNOWN";
                    if (runningTask != null && packageName != null) {
                        int windowingMode = runningTask.getWindowingMode();
                        if (windowingMode == 1 || windowingMode == 120) {
                            appSettingsHandler.setCurrentTopPackageName(packageName);
                            appSettingsHandler.setGestureRegionReduceEnablePkgName(packageName);
                        }
                        break;
                    }
                }
                break;
        }
    }
}
