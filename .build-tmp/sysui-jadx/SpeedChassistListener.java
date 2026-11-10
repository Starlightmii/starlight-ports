package com.oplus.systemui.navigationbar.gesture.otherbusiness;

import android.util.Log;
import com.oplus.systemui.navigationbar.observer.SpeedChassistMainObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class SpeedChassistMainBusiness$speedChassistListener$1 implements IObserverListener {
    public /* synthetic */ SpeedChassistMainBusiness this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        boolean speedChassistEnableStatus = SpeedChassistMainObserver.INSTANCE.getSpeedChassistEnableStatus();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "SpeedChassistMainBusiness-->".concat("speedChassistListener enableChassist = " + speedChassistEnableStatus + " "));
        }
        SpeedChassistMainBusiness speedChassistMainBusiness = this.this$0;
        if (speedChassistEnableStatus) {
            GestureHomeHandleEventController.INSTANCE.addHomeHandlerLongClickListener(speedChassistMainBusiness);
        } else {
            GestureHomeHandleEventController.INSTANCE.removeHomeHandlerLongClickListener(speedChassistMainBusiness);
        }
    }
}
