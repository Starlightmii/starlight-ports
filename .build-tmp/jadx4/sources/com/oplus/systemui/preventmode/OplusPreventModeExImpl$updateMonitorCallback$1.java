package com.oplus.systemui.preventmode;

import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPreventModeExImpl$updateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusPreventModeExImpl this$0;

    public final void onCoverViewAttachedStateChange(int i) {
        if (i == 1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusPreventModeExImpl-->onCoverViewAttachedStateChange add cover view, hide prevent view");
            }
            this.this$0.hidePreventModeView(false);
        }
    }

    public final void onFinishedGoingToSleep(int i) {
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onFinishedGoingToSleep: ", "OplusPreventModeExImpl-->", "SystemUi--Keyguard");
        }
        oplusPreventModeExImpl.setMScreenOn(false);
        oplusPreventModeExImpl.hidePreventModeView(false);
        oplusPreventModeExImpl.unregisterSensorListener();
    }

    public final void onStartedGoingToSleep(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onStartedGoingToSleep: ", "OplusPreventModeExImpl-->", "SystemUi--Keyguard");
        }
        this.this$0.setMScreenOn(false);
    }

    public final void onStartedWakingUp() {
        OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusPreventModeExImpl-->onStartedWakingUp");
        }
        oplusPreventModeExImpl.setMScreenOn(true);
        oplusPreventModeExImpl.registerSensorListener();
    }
}
