package com.oplus.systemui.plugins.qs.dagger;

import dagger.internal.Factory;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule_ProvideBgDispatcherFactory implements Factory {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class InstanceHolder {
        public static final GlobalConcurrencyModule_ProvideBgDispatcherFactory INSTANCE = new GlobalConcurrencyModule_ProvideBgDispatcherFactory();
    }

    public CoroutineDispatcher get() {
        CoroutineDispatcher coroutineDispatcherProvideBgDispatcher = GlobalConcurrencyModule.INSTANCE.provideBgDispatcher();
        coroutineDispatcherProvideBgDispatcher.getClass();
        return coroutineDispatcherProvideBgDispatcher;
    }
}
