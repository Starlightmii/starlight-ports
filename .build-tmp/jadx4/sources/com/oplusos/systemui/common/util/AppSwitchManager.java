package com.oplusos.systemui.common.util;

import android.content.Context;
import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.app.OplusAppSwitchManager;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppSwitchManager {
    private static final String LAUNCHER_ACTIVITY_NAME = "com.android.launcher.Launcher";
    private static final String LAUNCHER_ACTIVITY_NAME_SUPER_POWER = "com.android.launcher.powersave.SuperPowerSaveModeLauncher";
    private static final String PANEL_GOOGLE_HOME_ACTIVITY_NAME = "com.google.android.apps.chromecast.app.systemcontrol.panel.GoogleHomePanelActivity";
    private static final String TAG = "KeyguardOplusAppSwitchManager";
    private static final String UGC_AWEME_ACTIVITY_NAME = "com.ss.android.ugc.aweme.main.MainActivity";
    private static Runnable registerRunnable;
    public static int retryCount;
    public static final AppSwitchManager INSTANCE = new AppSwitchManager();
    private static Handler handler = new Handler();
    public static boolean isInLauncher = true;
    private static ArrayList<LauncherExitObserver> launcherExitObservers = new ArrayList<>();
    private static String curTopActivityName = "";
    private static final OplusAppSwitchManager.OnAppSwitchObserver launcherSwitchObserver = new AppSwitchManager$launcherSwitchObserver$1();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface LauncherExitObserver {
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.util.AppSwitchManager$registerLauncherSwitchObserver$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ Context $context;

        @Override // java.lang.Runnable
        public final void run() {
            AppSwitchManager.retryCount++;
            boolean zUnregisterAppSwitchObserver = OplusAppSwitchManager.getInstance().unregisterAppSwitchObserver(this.$context, AppSwitchManager.launcherSwitchObserver);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("unregister app switch observer result:", "KeyguardOplusAppSwitchManager-->", "SystemUi--Common", zUnregisterAppSwitchObserver);
            }
            AppSwitchManager.INSTANCE.registerLauncherSwitchObserver(this.$context);
        }
    }

    public final void addLauncherExitCallback(LauncherExitObserver launcherExitObserver) {
        if (launcherExitObservers.contains(launcherExitObserver)) {
            return;
        }
        launcherExitObservers.add(launcherExitObserver);
    }

    public final String getCurTopActivityName() {
        return curTopActivityName;
    }

    public final void registerLauncherSwitchObserver(Context context) {
        OplusAppSwitchConfig oplusAppSwitchConfig = new OplusAppSwitchConfig();
        ArrayList arrayList = new ArrayList();
        arrayList.add(LAUNCHER_ACTIVITY_NAME);
        arrayList.add(LAUNCHER_ACTIVITY_NAME_SUPER_POWER);
        arrayList.add(UGC_AWEME_ACTIVITY_NAME);
        arrayList.add(PANEL_GOOGLE_HOME_ACTIVITY_NAME);
        oplusAppSwitchConfig.addAppConfig(1, arrayList);
        boolean zRegisterAppSwitchObserver = OplusAppSwitchManager.getInstance().registerAppSwitchObserver(context, launcherSwitchObserver, oplusAppSwitchConfig);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("register app switch observer result:", "KeyguardOplusAppSwitchManager-->", "SystemUi--Common", zRegisterAppSwitchObserver);
        }
        if (zRegisterAppSwitchObserver || retryCount >= 10) {
            return;
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$context = context;
        VarHandle.storeStoreFence();
        registerRunnable = anonymousClass2;
        handler.postDelayed(anonymousClass2, 500L);
    }

    public final void removeLauncherExitCallback(LauncherExitObserver launcherExitObserver) {
        if (launcherExitObservers.contains(launcherExitObserver)) {
            launcherExitObservers.remove(launcherExitObserver);
        }
    }

    public final void unregisterLauncherSwitchObserver(Context context) {
        Runnable runnable = registerRunnable;
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
        OplusAppSwitchManager.getInstance().unregisterAppSwitchObserver(context, launcherSwitchObserver);
    }
}
