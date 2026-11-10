package com.oplus.systemui.plugins.qs.quickentrance;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSPrivary;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.quickentrance.popupwindow.OplusQSMoreButtonPopupWindowController;
import com.oplus.systemui.plugins.qs.quickentrance.ui.view.OplusQSQuickEntranceContainerView;
import com.oplus.systemui.plugins.qs.quickentrance.viewmodel.OplusQSQuickEntranceViewModel;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceComponent_Factory implements Factory {
    public DoubleCheck bgScopeProvider;
    public Provider cellAreaProvider;
    public Provider qsFgsProvider;
    public Provider qsMoreButtonPopupWindowControllerProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public Provider qsPrivaryProvider;
    public Provider qsQSQuickEntranceProvider;
    public Provider quickEntranceProvider;
    public Provider statusBarHeaderProvider;
    public InstanceFactory stubProvider;
    public DoubleCheck viewModelProvider;
    public Provider viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSQuickEntranceComponent m231get() {
        return new OplusQSQuickEntranceComponent((OplusQSQuickEntranceContainerView) this.viewProvider.get(), (OplusQSMoreButtonPopupWindowController) this.qsMoreButtonPopupWindowControllerProvider.get(), (QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get(), (QSPrivary) this.qsPrivaryProvider.get(), (OplusQSQuickEntranceViewModel) this.viewModelProvider.get(), (QSQuickEntrance) this.quickEntranceProvider.get(), (QSFgs) this.qsFgsProvider.get(), (QSElement) this.statusBarHeaderProvider.get(), (CellAreaProvider) this.cellAreaProvider.get(), (CoroutineScope) this.bgScopeProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (QSQuickEntrance) this.qsQSQuickEntranceProvider.get());
    }
}
