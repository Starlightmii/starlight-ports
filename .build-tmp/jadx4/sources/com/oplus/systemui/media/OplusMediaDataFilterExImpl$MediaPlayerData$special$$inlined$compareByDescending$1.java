package com.oplus.systemui.media;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey mediaSortKey = (OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2;
        Boolean boolIsPlaying = mediaSortKey.getData().isPlaying();
        Boolean bool = Boolean.TRUE;
        boolean z = false;
        Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(boolIsPlaying, bool) && mediaSortKey.getData().getPlaybackLocation() == 0);
        OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey mediaSortKey2 = (OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj;
        if (Intrinsics.areEqual(mediaSortKey2.getData().isPlaying(), bool) && mediaSortKey2.getData().getPlaybackLocation() == 0) {
            z = true;
        }
        return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
    }
}
