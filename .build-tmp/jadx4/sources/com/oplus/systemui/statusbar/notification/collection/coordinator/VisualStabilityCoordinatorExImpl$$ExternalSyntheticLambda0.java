package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class VisualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ NotifStabilityManager f$1;

    public final Object invoke() {
        try {
            this.f$1.invalidateList("after heads up animating away invalidate");
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }
}
