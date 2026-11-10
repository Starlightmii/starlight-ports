package com.oplus.systemui.plugins.qs.dagger;

import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSViewModule_Companion_ProvideCellAreaProviderFactory implements Factory {
    public InstanceFactory viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public CellAreaProvider m196get() {
        CellAreaProvider cellAreaProviderProvideCellAreaProvider = SeparateQSViewModule.Companion.provideCellAreaProvider((OplusQSRootView) this.viewProvider.get());
        cellAreaProviderProvideCellAreaProvider.getClass();
        return cellAreaProviderProvideCellAreaProvider;
    }
}
