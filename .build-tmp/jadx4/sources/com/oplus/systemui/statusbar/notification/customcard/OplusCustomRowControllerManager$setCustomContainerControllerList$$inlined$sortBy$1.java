package com.oplus.systemui.statusbar.notification.customcard;

import com.android.systemui.plugins.custom.CustomRowEntry;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowControllerManager$setCustomContainerControllerList$$inlined$sortBy$1 implements Comparator {
    public /* synthetic */ OplusCustomRowControllerManager this$0;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i;
        OplusCustomRowController oplusCustomRowController = (OplusCustomRowController) obj;
        ArrayList arrayList = this.this$0.customRowEntryList;
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = -1;
            if (i4 >= size) {
                i3 = -1;
                break;
            }
            Object obj3 = arrayList.get(i4);
            i4++;
            String key = ((CustomRowEntry) obj3).getKey();
            CustomRowEntry entry = oplusCustomRowController.getEntry();
            if (Intrinsics.areEqual(key, entry != null ? entry.getKey() : null)) {
                break;
            }
            i3++;
        }
        Integer numValueOf = Integer.valueOf(i3);
        OplusCustomRowController oplusCustomRowController2 = (OplusCustomRowController) obj2;
        ArrayList arrayList2 = this.this$0.customRowEntryList;
        int size2 = arrayList2.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj4 = arrayList2.get(i5);
            i5++;
            String key2 = ((CustomRowEntry) obj4).getKey();
            CustomRowEntry entry2 = oplusCustomRowController2.getEntry();
            if (Intrinsics.areEqual(key2, entry2 != null ? entry2.getKey() : null)) {
                i = i2;
                break;
            }
            i2++;
        }
        return ComparisonsKt__ComparisonsKt.compareValues(numValueOf, Integer.valueOf(i));
    }
}
