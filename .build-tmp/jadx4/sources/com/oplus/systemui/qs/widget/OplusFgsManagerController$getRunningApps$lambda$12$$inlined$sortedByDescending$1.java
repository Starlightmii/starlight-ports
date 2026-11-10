package com.oplus.systemui.qs.widget;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusFgsManagerController$getRunningApps$lambda$12$$inlined$sortedByDescending$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((OplusFgsManagerController.RunningApp) obj2).getTimeStarted()), Long.valueOf(((OplusFgsManagerController.RunningApp) obj).getTimeStarted()));
    }
}
