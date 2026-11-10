package com.oplus.systemui.qs.media;

import com.android.systemui.media.controls.shared.model.MediaData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDetailPanelDialog$special$$inlined$compareByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        MediaData mediaData = (MediaData) obj2;
        Boolean boolIsPlaying = mediaData.isPlaying();
        Boolean bool = Boolean.TRUE;
        boolean z = false;
        Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(boolIsPlaying, bool) && mediaData.getPlaybackLocation() == 0);
        MediaData mediaData2 = (MediaData) obj;
        if (Intrinsics.areEqual(mediaData2.isPlaying(), bool) && mediaData2.getPlaybackLocation() == 0) {
            z = true;
        }
        return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
    }
}
