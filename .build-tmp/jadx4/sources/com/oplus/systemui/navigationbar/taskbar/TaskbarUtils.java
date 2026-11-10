package com.oplus.systemui.navigationbar.taskbar;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.navigationbar.observer.TaskbarSettingsOberver;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TaskbarUtils {
    public static final TaskbarUtils INSTANCE = new TaskbarUtils();
    private static final String KEY_LAUNCHER_TASKBAR_METADATA = "com.android.launcher.support.taskbar";
    private static final String PACKAGENAME_LAUNCHER = "com.android.launcher";
    private static final String TAG = "TaskbarUtils";
    public static int isLauncherSupportTaskbar = -1;

    public static final boolean isEnableTaskbar(Context context) {
        boolean z = false;
        boolean z2 = 1 == TaskbarSettingsOberver.INSTANCE.getTaskbarEnable();
        NavBarFeatureOption.INSTANCE.getClass();
        boolean z3 = NavBarFeatureOption.sIsSupportTaskbar;
        if (z2 && z3 && isSupportTaskbarLauncher(context)) {
            z = true;
        }
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m(" isSupportTaskbarFeature ", " taskbarEnable ", " isInitTaskbar = ", z3, z2);
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, sbM.toString(), null, 8);
        return z;
    }

    public static final boolean isSupportTaskbarLauncher(Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(isLauncherSupportTaskbar, " isLauncherSupportTaskbar ", "TaskbarUtils-->", "SystemUi--NavBar");
        }
        int i = isLauncherSupportTaskbar;
        if (i != -1) {
            if (i == 1) {
                return true;
            }
        } else if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.android.launcher", 128);
                Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
                if (bundle != null && bundle.getBoolean(KEY_LAUNCHER_TASKBAR_METADATA, false)) {
                    isLauncherSupportTaskbar = 1;
                    return true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.e$default(8, LogUtil.TAG_NAVBAR, TAG, "failed to get taskbar metadata");
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "TaskbarUtils-->launcher not support taskbar");
            }
            isLauncherSupportTaskbar = 0;
            return false;
        }
        return false;
    }
}
