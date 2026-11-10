package com.oplus.systemui.plugins.qs.customize.data.repo;

import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepWellBeingTileConfigRepository_Factory implements Factory {
    public Provider mainScopeProvider;
    public Provider sepDefaultTileSpecRepoProvider;
    public Provider staticTileConfigRepoProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepWellBeingTileConfigRepository m157get() {
        return new SepWellBeingTileConfigRepository((SepDefaultTileSpecRepository) this.sepDefaultTileSpecRepoProvider.get(), (SepStaticTileConfigRepository) this.staticTileConfigRepoProvider.get(), (CoroutineScope) this.mainScopeProvider.get());
    }
}
