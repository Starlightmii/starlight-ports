package com.oplus.systemui.qs.res.domain.startable;

import com.android.systemui.CoreStartable;
import com.oplus.systemui.qs.res.domain.interactor.BaseQSResInteractor;
import com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor;
import com.oplus.systemui.qs.res.domain.interactor.SepQSTileResInteractor;
import com.oplus.systemui.qs.res.domain.interactor.StdQSResInteractor;
import com.oplus.systemui.qs.res.domain.interactor.StdQSTileResInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResourcesStartable implements CoreStartable {
    private final BaseQSResInteractor baseQSResInteractor;
    private final SepQSResInteractor sepQSResInteractor;
    private final SepQSTileResInteractor sepQSTileResInteractor;
    private final StdQSResInteractor stdQSResInteractor;
    private final StdQSTileResInteractor stdQSTileResInteractor;

    public OplusQSResourcesStartable(BaseQSResInteractor baseQSResInteractor, StdQSResInteractor stdQSResInteractor, StdQSTileResInteractor stdQSTileResInteractor, SepQSResInteractor sepQSResInteractor, SepQSTileResInteractor sepQSTileResInteractor) {
        this.baseQSResInteractor = baseQSResInteractor;
        this.stdQSResInteractor = stdQSResInteractor;
        this.stdQSTileResInteractor = stdQSTileResInteractor;
        this.sepQSResInteractor = sepQSResInteractor;
        this.sepQSTileResInteractor = sepQSTileResInteractor;
    }

    public final void start() {
        this.baseQSResInteractor.init();
        this.stdQSResInteractor.init();
        this.stdQSTileResInteractor.init();
        this.sepQSResInteractor.init();
        this.sepQSTileResInteractor.init();
    }
}
