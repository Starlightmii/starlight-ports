package com.oplus.systemui.plugins.qs.privacy;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.privacy.ui.view.OplusQSPrivacyContainerView;
import com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPrivacyComponent_Factory implements Factory {
    public DoubleCheck separateQSFakeStatusControllerProvider;
    public InstanceFactory stubProvider;
    public Provider viewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSPrivacyComponent m229get() {
        return new OplusQSPrivacyComponent((OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (OplusQSPrivacyContainerView) this.viewProvider.get(), (SeparateQSFakeStatusController) this.separateQSFakeStatusControllerProvider.get());
    }
}
