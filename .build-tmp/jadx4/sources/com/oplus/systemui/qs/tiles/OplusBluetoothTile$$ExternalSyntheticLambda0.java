package com.oplus.systemui.qs.tiles;

import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.qs.tiles.BluetoothTile;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusBluetoothTile$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ OplusBluetoothTile f$0;

    @Override // java.lang.Runnable
    public final void run() {
        OplusBluetoothTile oplusBluetoothTile = this.f$0;
        OplusQSDetailInteractor oplusQSDetailInteractor = oplusBluetoothTile.mDetailInteractor;
        int bluetoothState = ((BluetoothTile) oplusBluetoothTile).mController.getBluetoothState();
        if (bluetoothState != 10) {
            return;
        }
        LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", KeyguardLogger$.ExternalSyntheticOutline0.m("mRecheckRunnable:", oplusQSDetailInteractor.isShowing(), OplusPreventModeData.SEPARATOR, bluetoothState));
        oplusQSDetailInteractor.updateSwitchState(false);
    }
}
