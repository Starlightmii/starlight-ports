package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public final void onFeatureChange() {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, "slot oplusFeatureChanged change", null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Boolean.TRUE);
    }
}
