package com.oplus.systemui.qs.pipeline.data.event;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CustomizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusDevicepolicyManager f$0;
    public /* synthetic */ CustomizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1 f$1;

    public final Object invoke() {
        this.f$0.unregisterOplusDevicePolicyObserver(this.f$1);
        return Unit.INSTANCE;
    }
}
