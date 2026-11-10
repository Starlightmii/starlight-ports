package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHotspotTile$$ExternalSyntheticLambda1 implements IReceiverListener {
    public /* synthetic */ OplusHotspotTile f$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        OplusHotspotTile oplusHotspotTile = this.f$0;
        int intExtra = intent.getIntExtra("wifi_state", 14);
        int intExtra2 = intent.getIntExtra("android.net.wifi.extra.WIFI_AP_MODE", -1);
        oplusHotspotTile.mIsLocalHotspotRunning = intExtra2 == 2 && (intExtra == 12 || intExtra == 13 || intExtra == 10);
        LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusHotspotTile).TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(intExtra, intExtra2, "state=", ",mode="));
        if (intExtra == 11 || intExtra == 14) {
            Settings.Global.putInt(((QSTileImpl) oplusHotspotTile).mContext.getContentResolver(), "wifi_ap_state", 0);
        }
    }
}
