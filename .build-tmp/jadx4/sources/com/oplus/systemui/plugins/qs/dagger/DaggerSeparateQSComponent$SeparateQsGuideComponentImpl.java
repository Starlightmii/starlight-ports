package com.oplus.systemui.plugins.qs.dagger;

import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DaggerSeparateQSComponent$SeparateQsGuideComponentImpl implements SeparateQsGuideComponent {
    public InstanceFactory injectProvider;
    public InstanceFactory injectProvider2;
    public DoubleCheck oplusQsGuideSpiritualManagerProvider;
    public DoubleCheck oplusQsGuideSpiritualViewControllerProvider;
    public DoubleCheck oplusQsGuideStateProvider;

    @Override // com.oplus.systemui.plugins.qs.dagger.SeparateQsGuideComponent
    public OplusQsGuideSpiritualManager getGuideManager() {
        return (OplusQsGuideSpiritualManager) this.oplusQsGuideSpiritualManagerProvider.get();
    }
}
