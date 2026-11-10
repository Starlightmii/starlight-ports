package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplus.systemui.plugins.qs.customize.data.algorithm.DataConvertorKt;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.viewholder.GridData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ViewDataExtKt {
    public static final GridData mergeTwoCols(GridData gridData) {
        return GridData.copy$default(gridData, DataConvertorKt.mergeTwoCols(gridData.getSize()), null, 29);
    }

    public static final List<GridData> mergeTwoColsForListGridData(List<GridData> list) {
        List<GridData> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(mergeTwoCols((GridData) it.next()));
        }
        return arrayList;
    }

    public static final List<Loc> mergeTwoColsForListLoc(List<Loc> list) {
        List<Loc> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(DataConvertorKt.mergeTwoCols((Loc) it.next()));
        }
        return arrayList;
    }

    public static final GridData revertToTwoCols(GridData gridData) {
        return GridData.copy$default(gridData, DataConvertorKt.revertToTwoCols(gridData.getSize()), null, 29);
    }
}
