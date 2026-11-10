package com.oplus.systemui.media.controls.pipeline;

import com.oplus.media.OplusMediaPlayerInfo;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaPlayerDataInfo {
    private final OplusMediaPlayerInfo oplusMediaPlayerInfo;
    public final int userId;

    public MediaPlayerDataInfo(OplusMediaPlayerInfo oplusMediaPlayerInfo, int i) {
        this.oplusMediaPlayerInfo = oplusMediaPlayerInfo;
        this.userId = i;
    }

    public final OplusMediaPlayerInfo component1() {
        return this.oplusMediaPlayerInfo;
    }

    public final MediaPlayerDataInfo copy(OplusMediaPlayerInfo oplusMediaPlayerInfo, int i) {
        return new MediaPlayerDataInfo(oplusMediaPlayerInfo, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPlayerDataInfo)) {
            return false;
        }
        MediaPlayerDataInfo mediaPlayerDataInfo = (MediaPlayerDataInfo) obj;
        return Intrinsics.areEqual(this.oplusMediaPlayerInfo, mediaPlayerDataInfo.oplusMediaPlayerInfo) && this.userId == mediaPlayerDataInfo.userId;
    }

    public final OplusMediaPlayerInfo getOplusMediaPlayerInfo() {
        return this.oplusMediaPlayerInfo;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + (this.oplusMediaPlayerInfo.hashCode() * 31);
    }

    public String toString() {
        return "MediaPlayerDataInfo(oplusMediaPlayerInfo=" + this.oplusMediaPlayerInfo + ", userId=" + this.userId + ")";
    }
}
