package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.util.Log;
import com.android.systemui.statusbar.policy.PolicyObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1 implements PolicyObserver.IconBlockCallback {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

    public final void onChanged(List list) {
        OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = this.this$0;
        oplusMobileConnectionsRepositoryImpl.blockListIcons.clear();
        oplusMobileConnectionsRepositoryImpl.blockListIcons.addAll(list);
        boolean zContains = oplusMobileConnectionsRepositoryImpl.blockListIcons.contains(oplusMobileConnectionsRepositoryImpl.slotMobile);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusMobileConnectionsRepository-->".concat("isSatelliteForbid:" + zContains + " blockList:" + oplusMobileConnectionsRepositoryImpl.blockListIcons));
        }
        oplusMobileConnectionsRepositoryImpl.logger.logOnIconBlockCallbackChanged(zContains, oplusMobileConnectionsRepositoryImpl.blockListIcons);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Boolean.valueOf(zContains));
    }
}
