package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.android.systemui.broadcast.BroadcastDispatcher;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileConnectionsRepositoryImpl$simState$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ BroadcastDispatcher f$0;
    public /* synthetic */ OplusMobileConnectionsRepositoryImpl$simState$1$receiver$1 f$1;
    public /* synthetic */ OplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1 f$2;

    public final Object invoke() {
        BroadcastDispatcher broadcastDispatcher = this.f$0;
        OplusMobileConnectionsRepositoryImpl$simState$1$receiver$1 oplusMobileConnectionsRepositoryImpl$simState$1$receiver$1 = this.f$1;
        OplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1 oplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1 = this.f$2;
        broadcastDispatcher.unregisterReceiver(oplusMobileConnectionsRepositoryImpl$simState$1$receiver$1);
        broadcastDispatcher.unregisterReceiver(oplusMobileConnectionsRepositoryImpl$simState$1$serviceStateReceiver$1);
        return Unit.INSTANCE;
    }
}
