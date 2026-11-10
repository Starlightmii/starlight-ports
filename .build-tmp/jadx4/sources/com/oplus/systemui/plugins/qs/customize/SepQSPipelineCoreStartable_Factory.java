package com.oplus.systemui.plugins.qs.customize;

import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileResetInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileRestoreInteractor;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSPipelineCoreStartable_Factory implements Factory {
    public Provider backupInteractorProvider;
    public Provider bgDispatcherProvider;
    public Provider contextInstanceProvider;
    public Provider mainScopeProvider;
    public Provider resetInteractorProvider;
    public DoubleCheck restoreInteractorProvider;
    public Provider sepQsHostProvider;
    public Provider sepTileRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepQSPipelineCoreStartable m123get() {
        return new SepQSPipelineCoreStartable((ContextInstance) this.contextInstanceProvider.get(), (SepQSHostAdapter) this.sepQsHostProvider.get(), (SepTileConfigRepository) this.sepTileRepositoryProvider.get(), (TileResetInteractor) this.resetInteractorProvider.get(), (TileBackupInteractor) this.backupInteractorProvider.get(), (TileRestoreInteractor) this.restoreInteractorProvider.get(), (CoroutineScope) this.mainScopeProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get());
    }
}
