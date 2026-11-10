package com.oplus.zoom;

import android.content.Context;
import android.os.Handler;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.SystemWindows;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ZoomController_Factory implements Factory {
    private final Provider animExecutorProvider;
    private final Provider contextProvider;
    private final Provider displayControllerProvider;
    private final Provider displayImeControllerProvider;
    private final Provider displayInsetsControllerProvider;
    private final Provider mainExecutorProvider;
    private final Provider mainHandlerProvider;
    private final Provider rootTDAOrganizerProvider;
    private final Provider shellInitProvider;
    private final Provider shellTaskOrganizerProvider;
    private final Provider syncQueueProvider;
    private final Provider systemWindowsProvider;
    private final Provider taskStackListenerProvider;
    private final Provider transitionsProvider;

    private ZoomController_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14) {
        this.contextProvider = provider;
        this.displayControllerProvider = provider2;
        this.displayImeControllerProvider = provider3;
        this.displayInsetsControllerProvider = provider4;
        this.taskStackListenerProvider = provider5;
        this.shellTaskOrganizerProvider = provider6;
        this.systemWindowsProvider = provider7;
        this.syncQueueProvider = provider8;
        this.transitionsProvider = provider9;
        this.mainHandlerProvider = provider10;
        this.mainExecutorProvider = provider11;
        this.animExecutorProvider = provider12;
        this.rootTDAOrganizerProvider = provider13;
        this.shellInitProvider = provider14;
    }

    public static ZoomController_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14) {
        return new ZoomController_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public static ZoomController newInstance(Context context, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, TaskStackListenerImpl taskStackListenerImpl, ShellTaskOrganizer shellTaskOrganizer, SystemWindows systemWindows, SyncTransactionQueue syncTransactionQueue, Transitions transitions, Handler handler, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, ShellInit shellInit) {
        return new ZoomController(context, displayController, displayImeController, displayInsetsController, taskStackListenerImpl, shellTaskOrganizer, systemWindows, syncTransactionQueue, transitions, handler, shellExecutor, shellExecutor2, rootTaskDisplayAreaOrganizer, shellInit);
    }

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public ZoomController m527get() {
        return newInstance((Context) this.contextProvider.get(), (DisplayController) this.displayControllerProvider.get(), (DisplayImeController) this.displayImeControllerProvider.get(), (DisplayInsetsController) this.displayInsetsControllerProvider.get(), (TaskStackListenerImpl) this.taskStackListenerProvider.get(), (ShellTaskOrganizer) this.shellTaskOrganizerProvider.get(), (SystemWindows) this.systemWindowsProvider.get(), (SyncTransactionQueue) this.syncQueueProvider.get(), (Transitions) this.transitionsProvider.get(), (Handler) this.mainHandlerProvider.get(), (ShellExecutor) this.mainExecutorProvider.get(), (ShellExecutor) this.animExecutorProvider.get(), (RootTaskDisplayAreaOrganizer) this.rootTDAOrganizerProvider.get(), (ShellInit) this.shellInitProvider.get());
    }
}
