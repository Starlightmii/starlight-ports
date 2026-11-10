package com.oplus.systemui.qs.pipeline.dagger;

import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorEx;
import com.oplus.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSPipelineModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final CurrentTilesInteractorEx providerCurrentTilesInteractorEx(CurrentTilesInteractorExImpl currentTilesInteractorExImpl) {
            return currentTilesInteractorExImpl;
        }
    }
}
