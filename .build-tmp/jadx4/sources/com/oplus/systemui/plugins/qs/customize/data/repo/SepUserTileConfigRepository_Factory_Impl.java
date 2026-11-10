package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepUserTileConfigRepository_Factory_Impl implements SepUserTileConfigRepository.Factory {
    public C0228SepUserTileConfigRepository_Factory delegateFactory;

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository.Factory
    public final SepUserTileConfigRepository create(int i, MutableSharedFlow mutableSharedFlow) {
        C0228SepUserTileConfigRepository_Factory c0228SepUserTileConfigRepository_Factory = this.delegateFactory;
        return new SepUserTileConfigRepository(i, (SepDefaultGridInfoRepository) c0228SepUserTileConfigRepository_Factory.sepDefaultGridInfoRepositoryProvider.get(), (SepDefaultTileSpecRepository) c0228SepUserTileConfigRepository_Factory.sepDefaultTileSpecRepoProvider.get(), (SepStaticTileConfigRepository) c0228SepUserTileConfigRepository_Factory.staticTileConfigRepoProvider.get(), (SepEditDBTileRepository) c0228SepUserTileConfigRepository_Factory.sepEditDBTileRepositoryProvider.get(), (OplusSeparateQSPlugin.IStub) c0228SepUserTileConfigRepository_Factory.pluginStubProvider.get(), (SepTileMatchedStateSharedPreferenceRepository) c0228SepUserTileConfigRepository_Factory.matchedStateRepositoryProvider.get(), (SepRestoreTileConfig) c0228SepUserTileConfigRepository_Factory.sepRestoreTileConfigProvider.get(), (CoroutineScope) c0228SepUserTileConfigRepository_Factory.mainScopeProvider.get(), (CoroutineDispatcher) c0228SepUserTileConfigRepository_Factory.bgDispatcherProvider.get(), mutableSharedFlow, (SeparateQsTileListLogBuffer) c0228SepUserTileConfigRepository_Factory.separateQsTileLoggerProvider.get());
    }
}
