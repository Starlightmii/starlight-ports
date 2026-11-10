package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import android.os.Handler;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager_Factory;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualViewController_Factory;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideStateProvider_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DaggerSeparateQSComponent$SeparateQsGuideComponentBuilder implements SeparateQsGuideComponent.Builder {
    public Context inject;
    public Handler inject2;
    public DaggerSeparateQSComponent$SeparateQSComponentImpl separateQSComponentImpl;

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQsGuideComponent.Builder
    public final SeparateQsGuideComponent build() {
        Preconditions.checkBuilderRequirement(Context.class, this.inject);
        Preconditions.checkBuilderRequirement(Handler.class, this.inject2);
        DaggerSeparateQSComponent$SeparateQSComponentImpl daggerSeparateQSComponent$SeparateQSComponentImpl = this.separateQSComponentImpl;
        Context context = this.inject;
        Handler handler = this.inject2;
        DaggerSeparateQSComponent$SeparateQsGuideComponentImpl daggerSeparateQSComponent$SeparateQsGuideComponentImpl = new DaggerSeparateQSComponent$SeparateQsGuideComponentImpl();
        InstanceFactory instanceFactoryCreate = InstanceFactory.create(context);
        daggerSeparateQSComponent$SeparateQsGuideComponentImpl.injectProvider = instanceFactoryCreate;
        OplusQsGuideStateProvider_Factory oplusQsGuideStateProvider_Factory = new OplusQsGuideStateProvider_Factory();
        oplusQsGuideStateProvider_Factory.contextProvider = instanceFactoryCreate;
        VarHandle.storeStoreFence();
        daggerSeparateQSComponent$SeparateQsGuideComponentImpl.oplusQsGuideStateProvider = DoubleCheck.provider(oplusQsGuideStateProvider_Factory);
        InstanceFactory instanceFactoryCreate2 = InstanceFactory.create(handler);
        daggerSeparateQSComponent$SeparateQsGuideComponentImpl.injectProvider2 = instanceFactoryCreate2;
        InstanceFactory instanceFactory = daggerSeparateQSComponent$SeparateQsGuideComponentImpl.injectProvider;
        DoubleCheck doubleCheck = daggerSeparateQSComponent$SeparateQsGuideComponentImpl.oplusQsGuideStateProvider;
        OplusQsGuideSpiritualViewController_Factory oplusQsGuideSpiritualViewController_Factory = new OplusQsGuideSpiritualViewController_Factory();
        oplusQsGuideSpiritualViewController_Factory.contextProvider = instanceFactory;
        oplusQsGuideSpiritualViewController_Factory.guideStateProvider = doubleCheck;
        oplusQsGuideSpiritualViewController_Factory.handlerProvider = instanceFactoryCreate2;
        VarHandle.storeStoreFence();
        DoubleCheck doubleCheckProvider = DoubleCheck.provider(oplusQsGuideSpiritualViewController_Factory);
        daggerSeparateQSComponent$SeparateQsGuideComponentImpl.oplusQsGuideSpiritualViewControllerProvider = doubleCheckProvider;
        InstanceFactory instanceFactory2 = daggerSeparateQSComponent$SeparateQsGuideComponentImpl.injectProvider;
        DoubleCheck doubleCheck2 = daggerSeparateQSComponent$SeparateQsGuideComponentImpl.oplusQsGuideStateProvider;
        InstanceFactory instanceFactory3 = daggerSeparateQSComponent$SeparateQSComponentImpl.pluginStubProvider;
        InstanceFactory instanceFactory4 = daggerSeparateQSComponent$SeparateQsGuideComponentImpl.injectProvider2;
        OplusQsGuideSpiritualManager_Factory oplusQsGuideSpiritualManager_Factory = new OplusQsGuideSpiritualManager_Factory();
        oplusQsGuideSpiritualManager_Factory.contextProvider = instanceFactory2;
        oplusQsGuideSpiritualManager_Factory.guideSpiritualViewControllerProvider = doubleCheckProvider;
        oplusQsGuideSpiritualManager_Factory.guideStateProvider = doubleCheck2;
        oplusQsGuideSpiritualManager_Factory.separateQsPluginStubProvider = instanceFactory3;
        oplusQsGuideSpiritualManager_Factory.handlerProvider = instanceFactory4;
        VarHandle.storeStoreFence();
        daggerSeparateQSComponent$SeparateQsGuideComponentImpl.oplusQsGuideSpiritualManagerProvider = DoubleCheck.provider(oplusQsGuideSpiritualManager_Factory);
        VarHandle.storeStoreFence();
        return daggerSeparateQSComponent$SeparateQsGuideComponentImpl;
    }

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQsGuideComponent.Builder
    public final SeparateQsGuideComponent.Builder inject(Context context) {
        context.getClass();
        this.inject = context;
        return this;
    }

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQsGuideComponent.Builder
    public final SeparateQsGuideComponent.Builder inject(Handler handler) {
        handler.getClass();
        this.inject2 = handler;
        return this;
    }
}
