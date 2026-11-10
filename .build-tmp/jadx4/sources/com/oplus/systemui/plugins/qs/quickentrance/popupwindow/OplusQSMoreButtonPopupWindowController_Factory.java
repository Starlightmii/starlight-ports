package com.oplus.systemui.plugins.qs.quickentrance.popupwindow;

import android.content.Context;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.quickentrance.dialog.OplusQSFgsDialogController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSMoreButtonPopupWindowController_Factory implements Factory {
    public DoubleCheck bgDispatcherProvider;
    public Provider contextProvider;
    public Provider editViewModelProvider;
    public DoubleCheck fgsDialogControllerProvider;
    public DoubleCheck mainScopeProvider;
    public Provider qsQSQuickEntranceProvider;
    public InstanceFactory rootViewProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSMoreButtonPopupWindowController m234get() {
        return new OplusQSMoreButtonPopupWindowController((Context) this.contextProvider.get(), (OplusQSFgsDialogController) this.fgsDialogControllerProvider.get(), (QSQuickEntrance) this.qsQSQuickEntranceProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (OplusQSRootView) this.rootViewProvider.get(), (CoroutineScope) this.mainScopeProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get());
    }
}
