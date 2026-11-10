package com.oplus.systemui.qs.tiles;

import android.net.NetworkRequest;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.statusbar.util.WifiUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiTile$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusWifiTile f$0;

    public /* synthetic */ OplusWifiTile$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusWifiTile oplusWifiTile = this.f$0;
        switch (i) {
            case 0:
                NetworkRequest networkRequest = OplusWifiTile.REQUEST;
                WifiUtils.setWifiLowLatencyEnabled(((QSTileImpl) oplusWifiTile).mContext, false);
                break;
            case 1:
                NetworkRequest networkRequest2 = OplusWifiTile.REQUEST;
                WifiUtils.setWifiLowLatencyEnabled(((QSTileImpl) oplusWifiTile).mContext, true);
                break;
            case 2:
                NetworkRequest networkRequest3 = OplusWifiTile.REQUEST;
                oplusWifiTile.mController.setWifiEnabled(false);
                break;
            default:
                oplusWifiTile.mOplusWifiControllerExt.isDeviceSupportWiFi7(WifiUtils.isWifiStandardSupported(((QSTileImpl) oplusWifiTile).mContext, 8));
                oplusWifiTile.mWifi7HasLoad = true;
                break;
        }
    }
}
