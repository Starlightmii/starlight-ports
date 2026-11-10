package com.oplus.systemui.statusbar.pipeline.mobile.data.ims;

import com.android.ims.FeatureConnector;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImsListenerProxy$init$1$1 implements Runnable {
    public /* synthetic */ int $i;
    public /* synthetic */ ImsListenerProxy this$0;

    @Override // java.lang.Runnable
    public final void run() {
        FeatureConnector featureConnector = this.this$0.featureConnectorArray[this.$i];
        if (featureConnector != null) {
            featureConnector.connect();
        }
        int i = this.$i;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "ImsManager connect slotId: ", "ImsListenerProxy-->", "SystemUi--Statusbar");
        }
    }
}
