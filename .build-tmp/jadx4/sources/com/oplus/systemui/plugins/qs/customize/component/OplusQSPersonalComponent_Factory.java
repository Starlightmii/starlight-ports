package com.oplus.systemui.plugins.qs.customize.component;

import androidx.recyclerview.widget.COUIRecyclerView;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.component.proxy.TileOperationHintAction;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPersonalComponent_Factory implements Factory {
    public DoubleCheck cellAreaProvider;
    public Provider dragHelperProvider;
    public Provider editModeSwitchAnimationManagerProvider;
    public Provider editViewModelProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public Provider recyclerViewProvider;
    public Provider tileOperationHintActionProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSPersonalComponent m135get() {
        return new OplusQSPersonalComponent((COUIRecyclerView) this.recyclerViewProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (TileOperationHintAction) this.tileOperationHintActionProvider.get(), (EditModeSwitchAnimationManager) this.editModeSwitchAnimationManagerProvider.get(), (QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get(), (RecyclerViewDragHelper) this.dragHelperProvider.get(), (CellAreaProvider) this.cellAreaProvider.get());
    }
}
