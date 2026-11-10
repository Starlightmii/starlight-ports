package com.oplus.systemui.plugins.qs.edittop;

import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditTopComponent_Factory implements Factory {
    public Provider cellAreaProvider;
    public Provider qsQSQuickEntranceProvider;
    public InstanceFactory qsRootViewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public EditTopComponent m225get() {
        return new EditTopComponent((OplusQSRootView) this.qsRootViewProvider.get(), (CellAreaProvider) this.cellAreaProvider.get(), (QSQuickEntrance) this.qsQSQuickEntranceProvider.get());
    }
}
