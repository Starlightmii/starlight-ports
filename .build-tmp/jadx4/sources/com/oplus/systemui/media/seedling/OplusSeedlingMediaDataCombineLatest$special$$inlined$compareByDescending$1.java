package com.oplus.systemui.media.seedling;

import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeedlingMediaDataCombineLatest$special$$inlined$compareByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj2;
        SeedlingMediaData.Companion companion = SeedlingMediaData.Companion;
        boolean z = false;
        Boolean boolValueOf = Boolean.valueOf(companion.isPlayState(seedlingMediaData.getState()) && seedlingMediaData.getPlaybackLocation() == 0);
        SeedlingMediaData seedlingMediaData2 = (SeedlingMediaData) obj;
        if (companion.isPlayState(seedlingMediaData2.getState()) && seedlingMediaData2.getPlaybackLocation() == 0) {
            z = true;
        }
        return ComparisonsKt__ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(z));
    }
}
