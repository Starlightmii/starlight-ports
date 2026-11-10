package com.oplus.systemui.plugins.qs.customize.component;

import androidx.recyclerview.widget.COUIRecyclerView;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidersLeftBTilesContainerFactory;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSLeftBTilesComponent_Factory implements Factory {
    public Provider editViewModelProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public SeparateQSViewModule_Companion_ProvidersLeftBTilesContainerFactory recyclerViewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSLeftBTilesComponent m128get() {
        return new OplusQSLeftBTilesComponent((COUIRecyclerView) this.recyclerViewProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get());
    }
}
