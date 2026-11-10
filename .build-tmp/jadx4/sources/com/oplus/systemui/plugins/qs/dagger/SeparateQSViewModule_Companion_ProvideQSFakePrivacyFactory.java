package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSViewModule_Companion_ProvideQSFakePrivacyFactory implements Factory {
    public Provider contextProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public QSFakeStatusElement m203get() {
        QSFakeStatusElement qSFakeStatusElementProvideQSFakePrivacy = SeparateQSViewModule.Companion.provideQSFakePrivacy((OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (Context) this.contextProvider.get());
        qSFakeStatusElementProvideQSFakePrivacy.getClass();
        return qSFakeStatusElementProvideQSFakePrivacy;
    }
}
