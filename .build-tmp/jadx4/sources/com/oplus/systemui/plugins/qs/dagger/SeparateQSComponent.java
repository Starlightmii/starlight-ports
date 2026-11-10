package com.oplus.systemui.plugins.qs.dagger;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SeparateQSComponent {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Builder {
        SeparateQSComponent build();

        Builder contextInstance(ContextInstance contextInstance);

        Builder pluginStub(OplusSeparateQSPlugin.IStub iStub);
    }

    SeparateQsGuideComponent.Builder getGuideComponent();

    SeparateQSViewComponent.Factory getSeparateQSViewComponent();

    Map<Class<?>, Provider> getStartables();

    void inject(OplusSeparateQSPluginImpl oplusSeparateQSPluginImpl);
}
