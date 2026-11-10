package com.oplus.systemui.plugins.qs.quickentrance.dialog;

import android.content.Context;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFgsDialogController_Factory implements Factory {
    public Provider contextProvider;
    public Provider qsFgsProvider;
    public DoubleCheck quickEntranceProvider;
    public InstanceFactory stubProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSFgsDialogController m232get() {
        return new OplusQSFgsDialogController((Context) this.contextProvider.get(), (QSFgs) this.qsFgsProvider.get(), (QSQuickEntrance) this.quickEntranceProvider.get(), (OplusSeparateQSPlugin.IStub) this.stubProvider.get());
    }
}
