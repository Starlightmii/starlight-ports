package com.oplus.systemui.plugins.qs.customize.component.proxy;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileOperationHintAction_Factory implements Factory {
    public DoubleCheck editViewModelProvider;
    public InstanceFactory qsRootViewProvider;
    public InstanceFactory separateQsPluginStubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public TileOperationHintAction m145get() {
        return new TileOperationHintAction((OplusQSRootView) this.qsRootViewProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (OplusSeparateQSPlugin.IStub) this.separateQsPluginStubProvider.get());
    }
}
