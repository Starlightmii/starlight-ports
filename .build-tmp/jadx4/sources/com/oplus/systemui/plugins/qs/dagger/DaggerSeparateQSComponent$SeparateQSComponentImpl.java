package com.oplus.systemui.plugins.qs.dagger;

import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.plugins.qs.customize.SepQSPipelineCoreStartable;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer_Factory;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import java.lang.invoke.VarHandle;
import java.util.Collections;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DaggerSeparateQSComponent$SeparateQSComponentImpl implements SeparateQSComponent {
    public DoubleCheck allTilesInteractorProvider;
    public InstanceFactory contextInstanceProvider;
    public InstanceFactory factoryProvider;
    public DoubleCheck panelPluginInteractorProvider;
    public InstanceFactory pluginStubProvider;
    public DoubleCheck provideBgCoroutineScopeProvider;
    public DoubleCheck provideBgDispatcherProvider;
    public DoubleCheck provideMainCoroutineScopeProvider;
    public GlobalConcurrencyModule_ProvideMainHandlerFactory provideMainHandlerProvider;
    public DoubleCheck providePanelAnimationLogBufferProvider;
    public DoubleCheck provideSepQSTileHostProvider;
    public DoubleCheck provideSeparateQSTileListLogBufferProvider;
    public DoubleCheck sepDefaultGridInfoRepositoryProvider;
    public DoubleCheck sepDefaultTileSpecRepositoryProvider;
    public DoubleCheck sepEditDBTileRepositoryProvider;
    public DoubleCheck sepMoreTileConfigRepositoryProvider;
    public DoubleCheck sepQSPipelineCoreStartableProvider;
    public SepRestoreTileConfig_Factory sepRestoreTileConfigProvider;
    public DoubleCheck sepStaticTileConfigRepositoryProvider;
    public DoubleCheck sepSuperPowerTileConfigRepositoryProvider;
    public DoubleCheck sepTileConfigRepositoryProvider;
    public DoubleCheck sepTileMatchedStateSharedPreferenceRepositoryProvider;
    public DoubleCheck sepWellBeingTileConfigRepositoryProvider;
    public DaggerSeparateQSComponent$SeparateQSComponentImpl separateQSComponentImpl;
    public SeparateQsTileListLogBuffer_Factory separateQsTileListLogBufferProvider;
    public DoubleCheck statisticHandlerProvider;
    public DoubleCheck tileBackupInteractorProvider;
    public DoubleCheck tileConfigRepoSwitcherProvider;
    public DoubleCheck tileEditInteractorProvider;
    public DoubleCheck tileResetInteractorProvider;
    public DoubleCheck tileRestoreInteractorProvider;

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQSComponent
    public SeparateQsGuideComponent.Builder getGuideComponent() {
        DaggerSeparateQSComponent$SeparateQSComponentImpl daggerSeparateQSComponent$SeparateQSComponentImpl = this.separateQSComponentImpl;
        DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder daggerSeparateQSComponent$SeparateQsGuideComponentBuilder = new DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder();
        daggerSeparateQSComponent$SeparateQsGuideComponentBuilder.separateQSComponentImpl = daggerSeparateQSComponent$SeparateQSComponentImpl;
        VarHandle.storeStoreFence();
        return daggerSeparateQSComponent$SeparateQsGuideComponentBuilder;
    }

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQSComponent
    public SeparateQSViewComponent.Factory getSeparateQSViewComponent() {
        DaggerSeparateQSComponent$SeparateQSComponentImpl daggerSeparateQSComponent$SeparateQSComponentImpl = this.separateQSComponentImpl;
        DaggerSeparateQSComponent$SeparateQSViewComponentFactory daggerSeparateQSComponent$SeparateQSViewComponentFactory = new DaggerSeparateQSComponent$SeparateQSViewComponentFactory();
        daggerSeparateQSComponent$SeparateQSViewComponentFactory.separateQSComponentImpl = daggerSeparateQSComponent$SeparateQSComponentImpl;
        VarHandle.storeStoreFence();
        return daggerSeparateQSComponent$SeparateQSViewComponentFactory;
    }

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQSComponent
    public Map<Class<?>, Provider> getStartables() {
        return Collections.singletonMap(SepQSPipelineCoreStartable.class, this.sepQSPipelineCoreStartableProvider);
    }

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQSComponent
    public final void inject(OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl) {
    }
}
