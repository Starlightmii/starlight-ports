package com.oplus.systemui.media.seedling.rus;

import android.content.Context;
import android.content.Intent;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaRusUpdateManager$mRomUpdateReceiver$1 implements IReceiverListener {
    public /* synthetic */ OplusMediaRusUpdateManager this$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "intent.action: " + intent.getAction());
        if ("oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS".equals(intent.getAction())) {
            OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "OplusMediaController receive RUS update");
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("ROM_UPDATE_CONFIG_LIST");
            if (stringArrayListExtra != null) {
                OplusNotificationLogger.logMediaSeedling(OplusMediaRusUpdateManager.TAG, "updateRusData");
                OplusMediaRusUpdateManager.access$updateRusData(this.this$0, context, stringArrayListExtra);
            }
        }
    }
}
