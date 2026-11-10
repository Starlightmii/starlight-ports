package com.oplusos.systemui.common.util;

import android.util.Log;
import com.oplus.app.OplusAppEnterInfo;
import com.oplus.app.OplusAppExitInfo;
import com.oplus.app.OplusAppSwitchManager;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl$;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppSwitchManager$launcherSwitchObserver$1 implements OplusAppSwitchManager.OnAppSwitchObserver {
    public final void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) {
        String str;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardOplusAppSwitchManager-->".concat("onActivityEnter , oplusAppEnterInfo : " + oplusAppEnterInfo));
        }
        AppSwitchManager appSwitchManager = AppSwitchManager.INSTANCE;
        if (oplusAppEnterInfo == null || (str = oplusAppEnterInfo.targetName) == null) {
            str = "";
        }
        AppSwitchManager.curTopActivityName = str;
        AppSwitchManager appSwitchManager2 = AppSwitchManager.INSTANCE;
        AppSwitchManager.isInLauncher = "com.android.launcher.Launcher".equals(appSwitchManager2.getCurTopActivityName()) || "com.android.launcher.powersave.SuperPowerSaveModeLauncher".equals(appSwitchManager2.getCurTopActivityName());
    }

    public final void onActivityExit(OplusAppExitInfo oplusAppExitInfo) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardOplusAppSwitchManager-->".concat("onActivityExit , oplusAppExitInfo : " + oplusAppExitInfo));
        }
        AppSwitchManager.curTopActivityName = "";
        if (oplusAppExitInfo == null) {
            return;
        }
        String str = oplusAppExitInfo.targetName;
        if ("com.android.launcher.Launcher".equals(str) || "com.android.launcher.powersave.SuperPowerSaveModeLauncher".equals(str)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "KeyguardOplusAppSwitchManager-->launcher exit");
            }
            AppSwitchManager.isInLauncher = false;
            ArrayList arrayList = AppSwitchManager.launcherExitObservers;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ChargingAnimationImpl chargingAnimationImpl = ((AppSwitchManager.LauncherExitObserver) obj).f$0;
                ChargingAnimationImpl.4 r3 = chargingAnimationImpl.mHandler;
                ChargingAnimationImpl$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new ChargingAnimationImpl$.ExternalSyntheticLambda4(0);
                externalSyntheticLambda4.f$0 = chargingAnimationImpl;
                VarHandle.storeStoreFence();
                r3.post(externalSyntheticLambda4);
            }
        }
    }

    public final void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) {
    }

    public final void onAppExit(OplusAppExitInfo oplusAppExitInfo) {
    }
}
