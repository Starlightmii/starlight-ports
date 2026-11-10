package com.oplus.vfxsdk.common;

import com.oplus.vfxsdk.common.parameter.IUpdate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorNative$update$1 implements IUpdate {
    @Override // com.oplus.vfxsdk.common.parameter.IUpdate
    public void updateValue(String str, Object obj) {
    }

    @Override // com.oplus.vfxsdk.common.parameter.IUpdate
    public void updateValueIndex(ChannelData channelData, String str, Object obj, int i) {
        if (channelData == null) {
            return;
        }
        channelData.addChannel(obj, i);
    }
}
