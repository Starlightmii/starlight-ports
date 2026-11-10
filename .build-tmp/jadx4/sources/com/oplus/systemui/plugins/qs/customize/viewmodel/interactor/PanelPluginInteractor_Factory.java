package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelPluginInteractor_Factory implements Factory {
    public Provider contextInstanceProvider;
    public Provider proxyProvider;
    public Provider staticTileConfigRepoProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public PanelPluginInteractor m182get() {
        return new PanelPluginInteractor((ContextInstance) this.contextInstanceProvider.get(), (OplusSeparateQSPlugin.IStub) this.proxyProvider.get(), (SepStaticTileConfigRepository) this.staticTileConfigRepoProvider.get());
    }
}
