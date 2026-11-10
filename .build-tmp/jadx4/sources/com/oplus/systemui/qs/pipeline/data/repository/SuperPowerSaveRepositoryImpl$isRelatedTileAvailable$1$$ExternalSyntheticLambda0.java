package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1 f$0;
    public /* synthetic */ BroadcastDispatcher f$1;

    public final Object invoke() {
        SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1 superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1 = this.f$0;
        BroadcastDispatcher broadcastDispatcher = this.f$1;
        LogUtil.i$default(LogUtil.TAG_QS, "SuperPowerSaveRepositoryImpl", "unregisterReceiver:  " + superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1, null, 8);
        broadcastDispatcher.unregisterReceiver(superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1);
        return Unit.INSTANCE;
    }
}
