package com.oplus.systemui.qs.tiles;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveModeTile$toggleStateRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SuperPowerSaveModeTile this$0;

    public /* synthetic */ SuperPowerSaveModeTile$toggleStateRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                SuperPowerSaveModeTile superPowerSaveModeTile = this.this$0;
                if (!((QSTile.AdapterState) ((QSTileImpl) superPowerSaveModeTile).mState).value) {
                    QsSettingsValueProxy.Companion.setSuperPowerSaveEnterAction(((QSTileImpl) superPowerSaveModeTile).mHost.getContext(), 1);
                }
                break;
            default:
                SuperPowerSaveModeTile superPowerSaveModeTile2 = this.this$0;
                ((QSTileImpl) superPowerSaveModeTile2).mClickHandler.postDelayed(superPowerSaveModeTile2.toggleStateRunnable, 400L);
                break;
        }
    }
}
