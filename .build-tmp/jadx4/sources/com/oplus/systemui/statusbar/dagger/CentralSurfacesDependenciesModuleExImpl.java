package com.oplus.systemui.statusbar.dagger;

import android.content.Context;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.navigationbar.NavigationBarGestureEx;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import com.oplus.systemui.security.SecurityLockModeManager;
import com.oplus.systemui.statusbar.CommandQueueLogger;
import com.oplus.systemui.statusbar.OplusCommandQueue;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CentralSurfacesDependenciesModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final CommandQueue provideCommandQueue(Context context, DisplayTracker displayTracker, CommandRegistry commandRegistry, DumpHandler dumpHandler, Lazy lazy, SecurityLockModeManager securityLockModeManager, NavigationBarGestureEx navigationBarGestureEx, CommandQueueLogger commandQueueLogger) {
            OplusCommandQueue oplusCommandQueue = new OplusCommandQueue(context, displayTracker, commandRegistry, dumpHandler, lazy, securityLockModeManager, navigationBarGestureEx);
            oplusCommandQueue.addCallback(commandQueueLogger);
            return oplusCommandQueue;
        }

        public final StatusBarIconList provideStatusBarIconList(Context context) {
            return new StatusBarIconList(context.getResources().getStringArray(2130903169));
        }
    }
}
