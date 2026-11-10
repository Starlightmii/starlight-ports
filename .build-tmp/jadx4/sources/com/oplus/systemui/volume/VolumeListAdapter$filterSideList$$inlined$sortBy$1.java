package com.oplus.systemui.volume;

import com.oplus.systemui.volume.view.OplusVolumeRow;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeListAdapter$filterSideList$$inlined$sortBy$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ VolumeListAdapter$filterSideList$$inlined$sortBy$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(VolumeListAdapter.STREAM_PRI_LIST.indexOf(Integer.valueOf(((OplusVolumeRow) obj).getStream()))), Integer.valueOf(VolumeListAdapter.STREAM_PRI_LIST.indexOf(Integer.valueOf(((OplusVolumeRow) obj2).getStream()))));
            case 1:
                return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(VolumeListAdapter.STREAM_PRI_LIST.indexOf(Integer.valueOf(((OplusVolumeRow) obj).getStream()))), Integer.valueOf(VolumeListAdapter.STREAM_PRI_LIST.indexOf(Integer.valueOf(((OplusVolumeRow) obj2).getStream()))));
            default:
                return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((OplusVolumeRow) obj2).getStartTimestamp()), Long.valueOf(((OplusVolumeRow) obj).getStartTimestamp()));
        }
    }
}
