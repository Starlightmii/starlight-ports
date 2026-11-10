package com.oplus.systemui.qs.media;

import com.android.systemui.media.controls.shared.model.MediaData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $this_thenByDescending;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        boolean z = false;
        switch (this.$r8$classId) {
            case 0:
                int iCompare = ((OplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                MediaData mediaData = (MediaData) obj2;
                Boolean boolIsPlaying = mediaData.isPlaying();
                Boolean bool = Boolean.TRUE;
                Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(boolIsPlaying, bool) && mediaData.getPlaybackLocation() == 1);
                MediaData mediaData2 = (MediaData) obj;
                if (Intrinsics.areEqual(mediaData2.isPlaying(), bool) && mediaData2.getPlaybackLocation() == 1) {
                    z = true;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
            case 1:
                int iCompare2 = ((OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare2 != 0 ? iCompare2 : ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(!((MediaData) obj2).getResumption()), Boolean.valueOf(!((MediaData) obj).getResumption()));
            case 2:
                int iCompare3 = ((OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                if (iCompare3 != 0) {
                    return iCompare3;
                }
                return ComparisonsKt__ComparisonsKt.compareValues(Boolean.valueOf(((MediaData) obj2).getPlaybackLocation() != 2), Boolean.valueOf(((MediaData) obj).getPlaybackLocation() != 2));
            default:
                int iCompare4 = ((OplusQsMediaDetailPanelDialog$special$$inlined$thenByDescending$1) this.$this_thenByDescending).compare(obj, obj2);
                return iCompare4 != 0 ? iCompare4 : ComparisonsKt__ComparisonsKt.compareValues(((MediaData) obj2).getNotificationKey(), ((MediaData) obj).getNotificationKey());
        }
    }
}
