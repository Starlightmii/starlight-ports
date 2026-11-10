package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileIconViewModel$$ExternalSyntheticLambda1 implements Function0 {
    public /* synthetic */ OplusMobileIconViewModel f$0;
    public /* synthetic */ OplusMobileIconInteractorImpl f$1;
    public /* synthetic */ OplusMobileIconsInteractor f$2;
    public /* synthetic */ CoroutineScope f$3;

    public final Object invoke() {
        OplusMobileIconViewModel oplusMobileIconViewModel = this.f$0;
        return new OplusCarrierBasedSatelliteViewModelImpl(oplusMobileIconViewModel.getSubscriptionId(), this.f$1, this.f$2, this.f$3);
    }
}
