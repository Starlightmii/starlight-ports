package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.common.coroutine.ChannelExt;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.oplus.systemui.qs.pipeline.data.event.QSBackupRestoreProxy;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StandardQSSettingsRestoredRepository$restoreData$1$onRestoreData$1 implements QSBackupRestoreProxy.CallBack {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    @Override // com.oplus.systemui.qs.pipeline.data.event.QSBackupRestoreProxy.CallBack
    public final void onRestoreTileData(RestoreData restoreData) {
        ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, this.$$this$conflatedCallbackFlow, restoreData, "StandardQSSettingsRestoredRepository");
    }
}
