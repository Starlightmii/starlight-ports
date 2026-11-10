package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileBackupInteractor_Factory implements Factory {
    public DoubleCheck bgScopeProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public TileBackupInteractor m183get() {
        return new TileBackupInteractor((CoroutineScope) this.bgScopeProvider.get());
    }
}
