package com.oplus.systemui.qs.tileex;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileExParams {
    private final ActivityStarter activityStarter;
    private final DumpManager dumpManager;
    private final OplusQSActivityStarter oplusQSActivityStarter;
    private final PanelInteractor panelInteractor;
    private final Lazy qsHostLazy;
    private final SuperPowerSaveRepository superPowerRepo;
    private final WellBeingAssistantRepository wellBeingRepo;

    public QSTileExParams(Lazy lazy, PanelInteractor panelInteractor, SuperPowerSaveRepository superPowerSaveRepository, WellBeingAssistantRepository wellBeingAssistantRepository, ActivityStarter activityStarter, DumpManager dumpManager, OplusQSActivityStarter oplusQSActivityStarter) {
        this.qsHostLazy = lazy;
        this.panelInteractor = panelInteractor;
        this.superPowerRepo = superPowerSaveRepository;
        this.wellBeingRepo = wellBeingAssistantRepository;
        this.activityStarter = activityStarter;
        this.dumpManager = dumpManager;
        this.oplusQSActivityStarter = oplusQSActivityStarter;
    }

    public final Lazy component1() {
        return this.qsHostLazy;
    }

    public final PanelInteractor component2() {
        return this.panelInteractor;
    }

    public final SuperPowerSaveRepository component3() {
        return this.superPowerRepo;
    }

    public final WellBeingAssistantRepository component4() {
        return this.wellBeingRepo;
    }

    public final ActivityStarter component5() {
        return this.activityStarter;
    }

    public final DumpManager component6() {
        return this.dumpManager;
    }

    public final OplusQSActivityStarter component7() {
        return this.oplusQSActivityStarter;
    }

    public final QSTileExParams copy(Lazy lazy, PanelInteractor panelInteractor, SuperPowerSaveRepository superPowerSaveRepository, WellBeingAssistantRepository wellBeingAssistantRepository, ActivityStarter activityStarter, DumpManager dumpManager, OplusQSActivityStarter oplusQSActivityStarter) {
        return new QSTileExParams(lazy, panelInteractor, superPowerSaveRepository, wellBeingAssistantRepository, activityStarter, dumpManager, oplusQSActivityStarter);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QSTileExParams)) {
            return false;
        }
        QSTileExParams qSTileExParams = (QSTileExParams) obj;
        return Intrinsics.areEqual(this.qsHostLazy, qSTileExParams.qsHostLazy) && Intrinsics.areEqual(this.panelInteractor, qSTileExParams.panelInteractor) && Intrinsics.areEqual(this.superPowerRepo, qSTileExParams.superPowerRepo) && Intrinsics.areEqual(this.wellBeingRepo, qSTileExParams.wellBeingRepo) && Intrinsics.areEqual(this.activityStarter, qSTileExParams.activityStarter) && Intrinsics.areEqual(this.dumpManager, qSTileExParams.dumpManager) && Intrinsics.areEqual(this.oplusQSActivityStarter, qSTileExParams.oplusQSActivityStarter);
    }

    public final ActivityStarter getActivityStarter() {
        return this.activityStarter;
    }

    public final DumpManager getDumpManager() {
        return this.dumpManager;
    }

    public final OplusQSActivityStarter getOplusQSActivityStarter() {
        return this.oplusQSActivityStarter;
    }

    public final PanelInteractor getPanelInteractor() {
        return this.panelInteractor;
    }

    public final Lazy getQsHostLazy() {
        return this.qsHostLazy;
    }

    public final SuperPowerSaveRepository getSuperPowerRepo() {
        return this.superPowerRepo;
    }

    public final WellBeingAssistantRepository getWellBeingRepo() {
        return this.wellBeingRepo;
    }

    public final int hashCode() {
        return this.oplusQSActivityStarter.hashCode() + ((this.dumpManager.hashCode() + ((this.activityStarter.hashCode() + ((this.wellBeingRepo.hashCode() + ((this.superPowerRepo.hashCode() + ((this.panelInteractor.hashCode() + (this.qsHostLazy.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "QSTileExParams(qsHostLazy=" + this.qsHostLazy + ", panelInteractor=" + this.panelInteractor + ", superPowerRepo=" + this.superPowerRepo + ", wellBeingRepo=" + this.wellBeingRepo + ", activityStarter=" + this.activityStarter + ", dumpManager=" + this.dumpManager + ", oplusQSActivityStarter=" + this.oplusQSActivityStarter + ")";
    }
}
