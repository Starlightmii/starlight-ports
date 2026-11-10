package com.oplus.vfxsdk.common;

import com.oplus.vfxsdk.common.parameter.IPop;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorNative$pop$1 implements IPop {
    @Override // com.oplus.vfxsdk.common.parameter.IPop
    public Object popValue(ChannelData channelData, String str, int i) {
        Object channel;
        return (channelData == null || (channel = channelData.getChannel(i)) == null) ? new Object() : channel;
    }
}
