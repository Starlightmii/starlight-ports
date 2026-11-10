package com.oplus.systemui.qs.pipeline.data.repository;

import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class AirplaneModeConfirmRepository$isAirplaneModeNeedConfirm$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ AirplaneModeConfirmRepository$isAirplaneModeNeedConfirm$1$callback$1 f$0;

    public final Object invoke() {
        CustomizeFeatureOption.sFeatureChangeCallBacks.remove(this.f$0);
        return Unit.INSTANCE;
    }
}
