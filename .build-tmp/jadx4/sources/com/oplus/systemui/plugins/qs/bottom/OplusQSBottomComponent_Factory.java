package com.oplus.systemui.plugins.qs.bottom;

import android.widget.FrameLayout;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.dagger.SeparateQSViewModule_Companion_ProvideQSBottomContainerFactory;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSBottomComponent_Factory implements Factory {
    public Provider cellAreaProvider;
    public SeparateQSViewModule_Companion_ProvideQSBottomContainerFactory viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSBottomComponent m122get() {
        return new OplusQSBottomComponent((FrameLayout) this.viewProvider.get(), (CellAreaProvider) this.cellAreaProvider.get());
    }
}
