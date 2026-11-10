package com.oplus.systemui.plugins.qs.dagger;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSModule_Companion_ProvideSepQSTileHostFactory implements Factory {
    public Provider mainScopeProvider;
    public DoubleCheck repoSwitcherInteractorProvider;
    public Provider separateQsTileLoggerProvider;
    public Provider stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepQSHostAdapter m194get() {
        SepQSHostAdapter sepQSHostAdapterProvideSepQSTileHost = SeparateQSModule.Companion.provideSepQSTileHost((OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (TileConfigRepoSwitcher) this.repoSwitcherInteractorProvider.get(), (CoroutineScope) this.mainScopeProvider.get(), (SeparateQsTileListLogBuffer) this.separateQsTileLoggerProvider.get());
        sepQSHostAdapterProvideSepQSTileHost.getClass();
        return sepQSHostAdapterProvideSepQSTileHost;
    }
}
