package com.oplus.systemui.statusbar.notification.power;

import com.oplusos.systemui.common.observer.HighTempSafetyObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPowerNotificationWarnings$$ExternalSyntheticLambda1 implements IObserverListener {
    public /* synthetic */ OplusPowerNotificationWarnings f$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        OplusPowerNotificationWarnings oplusPowerNotificationWarnings = this.f$0;
        if (HighTempSafetyObserver.getInstance().mHighTempSafetyState) {
            LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI.Notification", "HighTempSafetyState, dismiss low battery warning");
            oplusPowerNotificationWarnings.dismissLowBatteryWarning();
        }
    }
}
