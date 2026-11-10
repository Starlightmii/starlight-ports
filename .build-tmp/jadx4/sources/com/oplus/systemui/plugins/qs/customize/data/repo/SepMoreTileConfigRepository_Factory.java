package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.ContextInstance;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepMoreTileConfigRepository_Factory implements Factory {
    public Provider bgDispatcherProvider;
    public Provider contextInstanceProvider;
    public Provider staticTileRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepMoreTileConfigRepository m151get() {
        return new SepMoreTileConfigRepository((ContextInstance) this.contextInstanceProvider.get(), (SepStaticTileConfigRepository) this.staticTileRepositoryProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get());
    }
}
