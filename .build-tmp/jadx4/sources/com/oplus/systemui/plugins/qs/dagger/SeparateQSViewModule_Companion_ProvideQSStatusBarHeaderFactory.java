package com.oplus.systemui.plugins.qs.dagger;

import android.content.Context;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSElement;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSViewModule_Companion_ProvideQSStatusBarHeaderFactory implements Factory {
    public SeparateQSViewModule_Companion_ProvideContextFactory contextProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public QSElement m210get() {
        QSElement qSElementProvideQSStatusBarHeader = SeparateQSViewModule.Companion.provideQSStatusBarHeader((OplusSeparateQSPlugin.IStub) this.stubProvider.get(), (Context) this.contextProvider.get());
        qSElementProvideQSStatusBarHeader.getClass();
        return qSElementProvideQSStatusBarHeader;
    }
}
