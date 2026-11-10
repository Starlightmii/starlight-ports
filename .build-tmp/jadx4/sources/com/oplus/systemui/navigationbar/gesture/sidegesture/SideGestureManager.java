package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.content.Context;
import com.android.systemui.Dependency;
import com.android.systemui.LauncherProxyService;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler$$ExternalSyntheticLambda0;
import com.oplus.systemui.navigationbar.gesture.OplusInputInjector;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SideGestureManager {
    public static volatile SideGestureManager sInstance;

    public SideGestureManager(Context context) {
        ((LauncherProxyService) Dependency.sDependency.getDependencyInner(LauncherProxyService.class)).addCallback(OplusInputInjector.getInstance());
        AppSettingsHandler appSettingsHandler = AppSettingsHandler.getInstance(context);
        Executor executor = appSettingsHandler.mBgExecutor;
        AppSettingsHandler$$ExternalSyntheticLambda0 appSettingsHandler$$ExternalSyntheticLambda0 = new AppSettingsHandler$$ExternalSyntheticLambda0(0);
        appSettingsHandler$$ExternalSyntheticLambda0.f$0 = appSettingsHandler;
        VarHandle.storeStoreFence();
        executor.execute(appSettingsHandler$$ExternalSyntheticLambda0);
    }
}
