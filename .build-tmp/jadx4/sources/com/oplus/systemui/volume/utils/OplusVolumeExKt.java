package com.oplus.systemui.volume.utils;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusVolumeExKt {
    public static final boolean setVisOrGone(View view, boolean z) {
        if ((view.getVisibility() == 0) == z) {
            return false;
        }
        view.setVisibility(z ? 0 : 8);
        return true;
    }
}
