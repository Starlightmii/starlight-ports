package com.oplus.systemui.statusbar.domain.interactor;

import com.android.systemui.statusbar.data.repository.StatusBarConfigurationController;
import com.android.systemui.statusbar.domain.interactor.OplusStatusBarIconRefreshInteractorEx;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIconRefreshInteractorExImpl extends OplusStatusBarIconRefreshInteractorEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarIconRefreshInteractorExImpl";
    private final OplusStatusBarIconRefreshInteractorExImpl$configurationListener$1 configurationListener;
    public final int displayId;
    public int oplusThemeChanged;
    private final StatusBarConfigurationController statusBarConfigurationController;
    private final StatusBarIconController statusBarIconController;
    public int uiMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusStatusBarIconRefreshInteractorExImpl(int i, StatusBarConfigurationController statusBarConfigurationController, StatusBarIconController statusBarIconController) {
        this.displayId = i;
        this.statusBarConfigurationController = statusBarConfigurationController;
        this.statusBarIconController = statusBarIconController;
        OplusStatusBarIconRefreshInteractorExImpl$configurationListener$1 oplusStatusBarIconRefreshInteractorExImpl$configurationListener$1 = new OplusStatusBarIconRefreshInteractorExImpl$configurationListener$1();
        oplusStatusBarIconRefreshInteractorExImpl$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = oplusStatusBarIconRefreshInteractorExImpl$configurationListener$1;
    }

    public final void start() {
        this.statusBarConfigurationController.addCallback(this.configurationListener);
    }

    public final void stop() {
        this.statusBarConfigurationController.removeCallback(this.configurationListener);
    }
}
