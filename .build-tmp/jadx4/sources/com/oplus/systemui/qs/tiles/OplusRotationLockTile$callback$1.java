package com.oplus.systemui.qs.tiles;

import com.android.systemui.statusbar.policy.RotationLockController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRotationLockTile$callback$1 implements RotationLockController.RotationLockControllerCallback {
    public /* synthetic */ OplusRotationLockTile this$0;

    public final void onRotationLockStateChanged(boolean z) {
        this.this$0.refreshState(Boolean.valueOf(z));
    }
}
