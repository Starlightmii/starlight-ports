package com.oplus.systemui.notification.aon;

import androidx.collection.FloatList$;
import com.aiunit.aon.AON.AONEventCallback;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthManager$aonEventCallback$1 implements AONEventCallback {
    public int eventOld;
    public volatile boolean lastAuthStateFromAonEvent;
    public /* synthetic */ AonAuthManager this$0;

    public final int getEventOld() {
        return this.eventOld;
    }

    public final boolean getLastAuthStateFromAonEvent() {
        return this.lastAuthStateFromAonEvent;
    }

    public final void onAONEvent(int i, int i2) {
        AonAuthManager aonAuthManager = this.this$0;
        if (this.eventOld != i2 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(this.eventOld, "AonAuthManager-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("[onAONEvent] eventType:", i, i2, ",event:", ",mEventOld:"));
        }
        if (i != 393219) {
            return;
        }
        AonAuthManager$aonHandler$1 aonAuthManager$aonHandler$1 = aonAuthManager.aonHandler;
        onAONEvent.2 r1 = new onAONEvent.2();
        r1.this$0 = aonAuthManager;
        r1.this$1 = this;
        r1.$event = i2;
        VarHandle.storeStoreFence();
        aonAuthManager$aonHandler$1.post(r1);
    }

    public final void setEventOld(int i) {
        this.eventOld = i;
    }

    public final void setLastAuthStateFromAonEvent(boolean z) {
        this.lastAuthStateFromAonEvent = z;
    }
}
