package com.oplus.systemui.media.seedling;

import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $this_thenByDescending;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                int iCompare = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj2;
                SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
                SeedlingMediaData seedlingMediaData2 = (SeedlingMediaData) obj;
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(companion.isPlayState(seedlingMediaData.getState()) && seedlingMediaData.getPlaybackLocation() == 1), Boolean.valueOf(companion.isPlayState(seedlingMediaData2.getState()) && seedlingMediaData2.getPlaybackLocation() == 1));
            case 1:
                int iCompare2 = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare2 != 0 ? iCompare2 : ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(((SeedlingMediaData) obj2).getActive()), Boolean.valueOf(((SeedlingMediaData) obj).getActive()));
            case 2:
                int iCompare3 = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare3 != 0) {
                    return iCompare3;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(((SeedlingMediaData) obj2).getPlaybackLocation() != 2), Boolean.valueOf(((SeedlingMediaData) obj).getPlaybackLocation() != 2));
            case 3:
                int iCompare4 = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare4 != 0 ? iCompare4 : ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((SeedlingMediaData) obj2).getLastActiveTime()), Long.valueOf(((SeedlingMediaData) obj).getLastActiveTime()));
            case 4:
                int iCompare5 = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare5 != 0 ? iCompare5 : ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((SeedlingMediaData) obj2).getCreateTime()), Long.valueOf(((SeedlingMediaData) obj).getCreateTime()));
            default:
                int iCompare6 = ((OplusSeedlingMediaDataCombineLatest$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare6 != 0 ? iCompare6 : ComparisonsKt__ComparisonsKt.compareValues(((SeedlingMediaData) obj2).getPlayerId(), ((SeedlingMediaData) obj).getPlayerId());
        }
    }
}
