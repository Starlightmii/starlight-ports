package com.oplus.systemui.media;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $this_thenByDescending;

    public /* synthetic */ OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        boolean z = false;
        switch (this.$r8$classId) {
            case 0:
                int iCompare = ((OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey mediaSortKey = (OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2;
                Boolean boolIsPlaying = mediaSortKey.getData().isPlaying();
                Boolean bool = Boolean.TRUE;
                Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(boolIsPlaying, bool) && mediaSortKey.getData().getPlaybackLocation() == 1);
                OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey mediaSortKey2 = (OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj;
                if (Intrinsics.areEqual(mediaSortKey2.getData().isPlaying(), bool) && mediaSortKey2.getData().getPlaybackLocation() == 1) {
                    z = true;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
            case 1:
                int iCompare2 = ((OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare2 != 0 ? iCompare2 : ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(!((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2).getData().getResumption()), Boolean.valueOf(!((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj).getData().getResumption()));
            case 2:
                int iCompare3 = ((OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare3 != 0) {
                    return iCompare3;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2).getData().getPlaybackLocation() != 2), Boolean.valueOf(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj).getData().getPlaybackLocation() != 2));
            case 3:
                int iCompare4 = ((OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare4 != 0 ? iCompare4 : ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2).getUpdateTime()), Long.valueOf(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj).getUpdateTime()));
            default:
                int iCompare5 = ((OplusMediaDataFilterExImpl$MediaPlayerData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare5 != 0 ? iCompare5 : ComparisonsKt__ComparisonsKt.compareValues(((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj2).getData().getNotificationKey(), ((OplusMediaDataFilterExImpl.MediaPlayerData.MediaSortKey) obj).getData().getNotificationKey());
        }
    }
}
