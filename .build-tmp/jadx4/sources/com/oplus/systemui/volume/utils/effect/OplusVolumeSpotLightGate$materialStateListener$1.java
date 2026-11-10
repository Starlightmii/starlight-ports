package com.oplus.systemui.volume.utils.effect;

import com.coui.appcompat.OnMaterialStateChangeListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSpotLightGate$materialStateListener$1 implements OnMaterialStateChangeListener {
    public final CharSequence onDump() {
        return "OplusVolumeSpotLightGate";
    }

    public final void onMaterialStateChange(int i, boolean z) {
        if (i == 4) {
            OplusVolumeSpotLightGate.access$notifyChanged(OplusVolumeSpotLightGate.INSTANCE);
        }
    }
}
