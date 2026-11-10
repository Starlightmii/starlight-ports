package com.oplusos.systemui.aod.mediapanel;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaData$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $this_thenByDescending;

    public /* synthetic */ AodMediaData$special$$inlined$thenByDescending$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        boolean z = false;
        switch (this.$r8$classId) {
            case 0:
                int iCompare = ((AodMediaData$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                AodMediaData.MediaSortKey mediaSortKey = (AodMediaData.MediaSortKey) obj2;
                Boolean boolIsPlaying = mediaSortKey.getData().isPlaying();
                Boolean bool = Boolean.TRUE;
                Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(boolIsPlaying, bool) && mediaSortKey.getData().getPlaybackLocation() == 1);
                AodMediaData.MediaSortKey mediaSortKey2 = (AodMediaData.MediaSortKey) obj;
                if (Intrinsics.areEqual(mediaSortKey2.getData().isPlaying(), bool) && mediaSortKey2.getData().getPlaybackLocation() == 1) {
                    z = true;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
            case 1:
                int iCompare2 = ((AodMediaData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
                AodMediaData.MediaSortKey mediaSortKey3 = (AodMediaData.MediaSortKey) obj2;
                AodMediaData aodMediaData = AodMediaData.INSTANCE;
                AodMediaData.MediaSortKey mediaSortKey4 = (AodMediaData.MediaSortKey) obj;
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(aodMediaData.getShouldPrioritizeSs$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() ? mediaSortKey3.isSsMediaRec : !mediaSortKey3.isSsMediaRec), Boolean.valueOf(aodMediaData.getShouldPrioritizeSs$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() ? mediaSortKey4.isSsMediaRec : !mediaSortKey4.isSsMediaRec));
            case 2:
                int iCompare3 = ((AodMediaData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare3 != 0 ? iCompare3 : ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(!((AodMediaData.MediaSortKey) obj2).getData().getResumption()), Boolean.valueOf(!((AodMediaData.MediaSortKey) obj).getData().getResumption()));
            case 3:
                int iCompare4 = ((AodMediaData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare4 != 0) {
                    return iCompare4;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(((AodMediaData.MediaSortKey) obj2).getData().getPlaybackLocation() != 2), Boolean.valueOf(((AodMediaData.MediaSortKey) obj).getData().getPlaybackLocation() != 2));
            case 4:
                int iCompare5 = ((AodMediaData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare5 != 0 ? iCompare5 : ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((AodMediaData.MediaSortKey) obj2).getUpdateTime()), Long.valueOf(((AodMediaData.MediaSortKey) obj).getUpdateTime()));
            default:
                int iCompare6 = ((AodMediaData$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare6 != 0 ? iCompare6 : ComparisonsKt__ComparisonsKt.compareValues(((AodMediaData.MediaSortKey) obj2).getData().getNotificationKey(), ((AodMediaData.MediaSortKey) obj).getData().getNotificationKey());
        }
    }
}
