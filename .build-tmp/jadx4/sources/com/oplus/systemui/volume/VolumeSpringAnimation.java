package com.oplus.systemui.volume;

import com.oplus.systemui.volume.view.OplusVolumeRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSpringAnimation {
    public boolean mIsAnimEnabled;
    public boolean mIsDirectionUp;
    public OplusVolumeRow mProxyRow;

    public void setAnimEnableState(boolean z) {
        this.mIsAnimEnabled = z;
    }
}
