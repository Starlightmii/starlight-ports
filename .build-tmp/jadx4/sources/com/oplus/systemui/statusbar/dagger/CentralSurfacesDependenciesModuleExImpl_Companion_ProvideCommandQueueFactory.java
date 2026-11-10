package com.oplus.systemui.statusbar.dagger;

import android.content.Context;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.navigationbar.NavigationBarGestureEx;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.oplus.systemui.security.SecurityLockModeManager;
import com.oplus.systemui.statusbar.CommandQueueLogger;
import dagger.Lazy;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CentralSurfacesDependenciesModuleExImpl_Companion_ProvideCommandQueueFactory implements Factory {
    public static CommandQueue provideCommandQueue(Context context, DisplayTracker displayTracker, CommandRegistry commandRegistry, DumpHandler dumpHandler, Lazy lazy, SecurityLockModeManager securityLockModeManager, NavigationBarGestureEx navigationBarGestureEx, CommandQueueLogger commandQueueLogger) {
        CommandQueue commandQueueProvideCommandQueue = CentralSurfacesDependenciesModuleExImpl.Companion.provideCommandQueue(context, displayTracker, commandRegistry, dumpHandler, lazy, securityLockModeManager, navigationBarGestureEx, commandQueueLogger);
        commandQueueProvideCommandQueue.getClass();
        return commandQueueProvideCommandQueue;
    }
}
