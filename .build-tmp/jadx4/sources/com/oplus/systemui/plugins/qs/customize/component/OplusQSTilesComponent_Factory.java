package com.oplus.systemui.plugins.qs.customize.component;

import androidx.recyclerview.widget.COUIRecyclerView;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.animator.QSPanelAnimatorManager;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper.RecyclerViewDragHelper;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidesIndicatorFactory;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvidesOtherTilesContainerFactory;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent_Factory implements Factory {
    public Provider dragHelperProvider;
    public Provider editModeSwitchAnimationManagerProvider;
    public Provider editViewModelProvider;
    public SeparateQSViewModule_Companion_ProvidesIndicatorFactory indicatorViewProvider;
    public Provider qsPanelAnimatorManagerProvider;
    public InstanceFactory qsRootViewProvider;
    public SeparateQSViewModule_Companion_ProvidesOtherTilesContainerFactory recyclerViewProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSTilesComponent m141get() {
        return new OplusQSTilesComponent((COUIRecyclerView) this.recyclerViewProvider.get(), (OplusQSPageIndicator) this.indicatorViewProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (OplusQSRootView) this.qsRootViewProvider.get(), (QSPanelAnimatorManager) this.qsPanelAnimatorManagerProvider.get(), (EditModeSwitchAnimationManager) this.editModeSwitchAnimationManagerProvider.get(), (RecyclerViewDragHelper) this.dragHelperProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get());
    }
}
