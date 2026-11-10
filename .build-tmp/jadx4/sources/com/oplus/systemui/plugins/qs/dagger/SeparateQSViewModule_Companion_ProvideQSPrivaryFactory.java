package com.oplus.systemui.plugins.qs.dagger;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSPrivary;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSViewModule_Companion_ProvideQSPrivaryFactory implements Factory {
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public QSPrivary m207get() {
        QSPrivary qSPrivaryProvideQSPrivary = SeparateQSViewModule.Companion.provideQSPrivary((OplusSeparateQSPlugin.IStub) this.stubProvider.get());
        qSPrivaryProvideQSPrivary.getClass();
        return qSPrivaryProvideQSPrivary;
    }
}
