package com.oplus.systemui.qs.slider.detail.data;

import com.android.settingslib.media.LocalMediaManager;
import com.android.settingslib.media.MediaDevice;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsLocalMediaDeviceRepository$callback$1 implements LocalMediaManager.DeviceCallback {
    public /* synthetic */ OplusQsLocalMediaDeviceRepository this$0;

    public final void onDeviceAttributesChanged() {
        this.this$0.updateMediaDevice();
    }

    public final void onDeviceListUpdate(List list) {
        this.this$0.updateMediaDevice();
    }

    public final void onSelectedDeviceStateChanged(MediaDevice mediaDevice, int i) {
        this.this$0.updateMediaDevice();
    }
}
