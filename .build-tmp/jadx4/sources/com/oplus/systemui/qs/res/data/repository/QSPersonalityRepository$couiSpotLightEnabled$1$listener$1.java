package com.oplus.systemui.qs.res.data.repository;

import com.android.keyguard.OplusLockIconView$;
import com.coui.appcompat.OnMaterialStateChangeListener;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPersonalityRepository$couiSpotLightEnabled$1$listener$1 implements OnMaterialStateChangeListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public final CharSequence onDump() {
        return "QSPersonalityRepository";
    }

    public final void onMaterialStateChange(int i, boolean z) {
        if (i == 1 || i == 4) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onMaterialStateChange: newState=", "QSPersonalityRepository-->", "SystemUi--QuickSettings", z);
            }
            this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Unit.INSTANCE);
        }
    }
}
