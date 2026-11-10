package com.oplus.systemui.plugins.qs.dagger;

import android.os.Handler;
import android.os.Looper;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule_ProvideMainHandlerFactory implements Factory {
    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public Handler m191get() {
        Handler handlerProvideMainHandler = GlobalConcurrencyModule.INSTANCE.provideMainHandler((Looper) GlobalConcurrencyModule_ProvideMainLooperFactory.InstanceHolder.INSTANCE.get());
        handlerProvideMainHandler.getClass();
        return handlerProvideMainHandler;
    }
}
