package com.oplus.systemui.statusbar.phone.netspeed;

import android.util.Log;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNetworkSpeedControllerExImpl$mScreenObserver$1 implements ScreenLifecycle.Observer {
    public /* synthetic */ OplusNetworkSpeedControllerExImpl this$0;

    public final void onScreenTurningOff() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusNetworkSpeedControllExImpl-->SCREEN_OFF");
        }
        OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
        oplusNetworkSpeedControllerExImpl.isPause = true;
        oplusNetworkSpeedControllerExImpl.bgHandler.removeMessages(100001);
    }

    public final void onScreenTurningOn() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusNetworkSpeedControllExImpl-->SCREEN_ON");
        }
        OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl = this.this$0;
        oplusNetworkSpeedControllerExImpl.isPause = false;
        oplusNetworkSpeedControllerExImpl.postUpdateNetworkSpeedDelay(0L);
    }
}
