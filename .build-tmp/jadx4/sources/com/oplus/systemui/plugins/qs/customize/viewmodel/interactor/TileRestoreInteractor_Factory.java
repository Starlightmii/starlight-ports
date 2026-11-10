package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileRestoreInteractor_Factory implements Factory {
    public Provider bgScopeProvider;
    public Provider contextInstanceProvider;
    public Provider repoSwitcherProvider;
    public Provider staticTileConfigRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public TileRestoreInteractor m187get() {
        return new TileRestoreInteractor((ContextInstance) this.contextInstanceProvider.get(), (SepStaticTileConfigRepository) this.staticTileConfigRepositoryProvider.get(), (TileConfigRepoSwitcher) this.repoSwitcherProvider.get(), (CoroutineScope) this.bgScopeProvider.get());
    }
}
