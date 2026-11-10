package com.oplus.systemui.plugins.qs.quickentrance.viewmodel;

import com.oplus.systemui.plugins.qs.quickentrance.domain.interactor.OplusSeparateSettingsEntranceInteractor;
import com.oplus.systemui.plugins.qs.quickentrance.repository.OplusQSQuickEntranceRepository;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceViewModel_Factory implements Factory {
    public DoubleCheck interactorProvider;
    public Provider quickEntranceRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQSQuickEntranceViewModel m236get() {
        return new OplusQSQuickEntranceViewModel((OplusQSQuickEntranceRepository) this.quickEntranceRepositoryProvider.get(), (OplusSeparateSettingsEntranceInteractor) this.interactorProvider.get());
    }
}
