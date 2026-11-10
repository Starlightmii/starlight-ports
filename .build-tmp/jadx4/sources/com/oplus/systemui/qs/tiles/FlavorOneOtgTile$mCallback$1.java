package com.oplus.systemui.qs.tiles;

import com.android.systemui.statusbar.policy.BatteryController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneOtgTile$mCallback$1 implements BatteryController.BatteryStateChangeCallback {
    public /* synthetic */ FlavorOneOtgTile this$0;

    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.this$0.mIsCharging = z2;
    }
}
