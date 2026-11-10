package com.oplus.systemui.statusbar.seeding;

import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusLiveAlertViewModel$$ExternalSyntheticLambda0 implements Function2 {
    public /* synthetic */ OplusLiveAlertViewModel f$0;

    public final Object invoke(Object obj, Object obj2) {
        boolean z;
        List<Pair> listZip = CollectionsKt___CollectionsKt.zip((List) obj, (List) obj2);
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            z = true;
        } else {
            for (Pair pair : listZip) {
                LiveAlertChipModel liveAlertChipModel = (LiveAlertChipModel) pair.component1();
                LiveAlertChipModel liveAlertChipModel2 = (LiveAlertChipModel) pair.component2();
                if (liveAlertChipModel.getChipType() != liveAlertChipModel2.getChipType() || !Intrinsics.areEqual(liveAlertChipModel.getModel(), liveAlertChipModel2.getModel()) || !Intrinsics.areEqual(OplusLiveAlertViewModel.chipCardStopId(liveAlertChipModel), OplusLiveAlertViewModel.chipCardStopId(liveAlertChipModel2))) {
                    z = false;
                }
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
