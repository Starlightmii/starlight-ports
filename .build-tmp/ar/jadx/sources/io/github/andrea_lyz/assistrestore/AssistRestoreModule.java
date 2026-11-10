package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistRestoreModule extends XposedModule {
    private static final String GOOGLE_APP_PACKAGE = "com.google.android.googlequicksearchbox";
    private static final String LAUNCHER_PACKAGE = "com.android.launcher";
    private static final String SYSTEM_UI_PACKAGE = "com.android.systemui";
    static final String TAG = "AssistRestore";
    private volatile boolean googleAppInstalled;
    private volatile boolean launcherInstalled;
    private volatile String processName = "unknown";
    private volatile boolean systemServerInstalled;
    private volatile boolean systemUiInstalled;

    private static boolean isPackageProcess(String str, String str2) {
        return str.equals(str2) || str2.startsWith(str.concat(":"));
    }

    public void logError(String str) {
        log(6, TAG, str);
    }

    public void logError(String str, Throwable th) {
        log(6, TAG, str, th);
    }

    public void logInfo(String str) {
        log(4, TAG, str);
    }

    public void logWarn(String str) {
        log(5, TAG, str);
    }

    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam moduleLoadedParam) {
        this.processName = moduleLoadedParam.getProcessName();
        logInfo("module_loaded process=" + this.processName + " systemServer=" + moduleLoadedParam.isSystemServer() + " api=" + getApiVersion() + " framework=" + getFrameworkName() + "/" + getFrameworkVersion());
        HookPrefs.attach(this);
    }

    public void onPackageReady(XposedModuleInterface.PackageReadyParam packageReadyParam) {
        String packageName = packageReadyParam.getPackageName();
        if (GOOGLE_APP_PACKAGE.equals(packageName) && isPackageProcess(GOOGLE_APP_PACKAGE, this.processName)) {
            if (this.googleAppInstalled) {
                return;
            }
            try {
                GoogleAppHooks.install(this, packageReadyParam.getClassLoader());
                this.googleAppInstalled = true;
                return;
            } catch (Throwable th) {
                logError("google_app_install_failed", th);
                return;
            }
        }
        if (LAUNCHER_PACKAGE.equals(packageName) && LAUNCHER_PACKAGE.equals(this.processName)) {
            if (this.launcherInstalled) {
                return;
            }
            try {
                LauncherHooks.install(this, packageReadyParam.getClassLoader());
                this.launcherInstalled = true;
                return;
            } catch (Throwable th2) {
                logError("launcher_install_failed", th2);
                return;
            }
        }
        if (SYSTEM_UI_PACKAGE.equals(packageName) && SYSTEM_UI_PACKAGE.equals(this.processName) && !this.systemUiInstalled) {
            try {
                SystemUiHooks.install(this, packageReadyParam.getClassLoader());
                this.systemUiInstalled = true;
            } catch (Throwable th3) {
                logError("systemui_install_failed", th3);
            }
        }
    }

    public void onSystemServerStarting(XposedModuleInterface.SystemServerStartingParam systemServerStartingParam) {
        if (this.systemServerInstalled) {
            return;
        }
        try {
            SystemServerHooks.install(this, systemServerStartingParam.getClassLoader());
            WalletHooks.install(this, systemServerStartingParam.getClassLoader());
            CtsHooks.install(this, systemServerStartingParam.getClassLoader());
            HansProcessFreezeHooks.install(this, systemServerStartingParam.getClassLoader());
            this.systemServerInstalled = true;
        } catch (Throwable th) {
            logError("system_server_install_failed", th);
        }
    }
}
