package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigRepository_Factory implements Factory {
    public Provider bgDispatcherProvider;
    public Provider dbTileRepositoryProvider;
    public Provider sepUserTileRepositoryProvider;
    public Provider separateQsTileLoggerProvider;
    public Provider superPowerTileConfigRepositoryProvider;
    public DoubleCheck wellBeingTileConfigRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepTileConfigRepository m155get() {
        return new SepTileConfigRepository((SepUserTileConfigRepository.Factory) this.sepUserTileRepositoryProvider.get(), (SepSuperPowerTileConfigRepository) this.superPowerTileConfigRepositoryProvider.get(), (SepWellBeingTileConfigRepository) this.wellBeingTileConfigRepositoryProvider.get(), (SepEditDBTileRepository) this.dbTileRepositoryProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get(), (SeparateQsTileListLogBuffer) this.separateQsTileLoggerProvider.get());
    }
}
