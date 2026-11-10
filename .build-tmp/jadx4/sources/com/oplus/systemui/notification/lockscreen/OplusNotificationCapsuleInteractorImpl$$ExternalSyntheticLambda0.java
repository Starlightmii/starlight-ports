package com.oplus.systemui.notification.lockscreen;

import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationCapsuleInteractorImpl$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusNotificationCapsuleInteractorImpl f$0;

    public final Object invoke() {
        return StateFlowKt.MutableStateFlow(this.f$0.createInitCapsulePosition());
    }
}
