package com.oplus.systemui.plugins.qs.dagger;

import android.os.Looper;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalConcurrencyModule_ProvideMainLooperFactory implements Factory {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class InstanceHolder {
        public static final GlobalConcurrencyModule_ProvideMainLooperFactory INSTANCE = new GlobalConcurrencyModule_ProvideMainLooperFactory();
    }

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public Looper m192get() {
        Looper looperProvideMainLooper = GlobalConcurrencyModule.INSTANCE.provideMainLooper();
        looperProvideMainLooper.getClass();
        return looperProvideMainLooper;
    }
}
