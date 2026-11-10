package com.oplus.systemui.plugins.qs.dagger;

import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule_ProvideBgCoroutineScopeFactory implements Factory {
    public Provider bgDispatcherProvider;

    public CoroutineScope get() {
        CoroutineScope coroutineScopeProvideBgCoroutineScope = GlobalConcurrencyModule.INSTANCE.provideBgCoroutineScope((CoroutineDispatcher) this.bgDispatcherProvider.get());
        coroutineScopeProvideBgCoroutineScope.getClass();
        return coroutineScopeProvideBgCoroutineScope;
    }
}
