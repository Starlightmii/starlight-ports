package com.oplus.systemui.qs.tiles;

import android.net.NetworkRequest;
import android.os.Handler;
import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusWifiTile$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusWifiTile f$0;
    public /* synthetic */ Expandable f$1;

    public /* synthetic */ OplusWifiTile$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusWifiTile oplusWifiTile = this.f$0;
                Expandable expandable = this.f$1;
                NetworkRequest networkRequest = OplusWifiTile.REQUEST;
                Handler handler = ((QSTileImpl) oplusWifiTile).mUiHandler;
                OplusWifiTile$$ExternalSyntheticLambda2 oplusWifiTile$$ExternalSyntheticLambda2 = new OplusWifiTile$$ExternalSyntheticLambda2(1);
                oplusWifiTile$$ExternalSyntheticLambda2.f$0 = oplusWifiTile;
                oplusWifiTile$$ExternalSyntheticLambda2.f$1 = expandable;
                VarHandle.storeStoreFence();
                handler.postDelayed(oplusWifiTile$$ExternalSyntheticLambda2, 650L);
                break;
            default:
                OplusWifiTile oplusWifiTile2 = this.f$0;
                Expandable expandable2 = this.f$1;
                oplusWifiTile2.mPanelInteractor.openPanels();
                oplusWifiTile2.showOplusQSDetailDialogProvider(expandable2);
                break;
        }
    }
}
