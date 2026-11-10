package com.oplus.systemui.plugins.qs.dagger;

import dagger.internal.Factory;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule_ProvideMainCoroutineScopeFactory implements Factory {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class InstanceHolder {
        public static final GlobalConcurrencyModule_ProvideMainCoroutineScopeFactory INSTANCE = new GlobalConcurrencyModule_ProvideMainCoroutineScopeFactory();
    }

    public CoroutineScope get() {
        CoroutineScope coroutineScopeProvideMainCoroutineScope = GlobalConcurrencyModule.INSTANCE.provideMainCoroutineScope();
        coroutineScopeProvideMainCoroutineScope.getClass();
        return coroutineScopeProvideMainCoroutineScope;
    }
}
