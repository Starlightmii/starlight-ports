package com.oplus.systemui.qs.pipeline.data.event;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import android.util.Log;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1 implements OplusDevicepolicyManager.OplusDevicePolicyObserver {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public final void onOplusDevicePolicyUpdate(String str, List list) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "CustomizeModifyQsTilesEvent-->".concat("list: " + list));
        }
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(list);
    }

    public final void onOplusDevicePolicyUpdate(String str, String str2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("value: ", str2, "CustomizeModifyQsTilesEvent-->", "SystemUi--Statusbar");
        }
    }
}
