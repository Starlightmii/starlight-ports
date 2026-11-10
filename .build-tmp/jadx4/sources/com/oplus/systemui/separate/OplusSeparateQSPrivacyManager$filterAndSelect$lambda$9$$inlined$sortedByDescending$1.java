package com.oplus.systemui.separate;

import com.android.systemui.privacy.PrivacyDialogDelegate;
import com.android.systemui.privacy.PrivacyItem;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPrivacyManager$filterAndSelect$lambda$9$$inlined$sortedByDescending$1 implements Comparator {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((PrivacyDialogDelegate.PrivacyElement) obj2).getLastActiveTimestamp()), Long.valueOf(((PrivacyDialogDelegate.PrivacyElement) obj).getLastActiveTimestamp()));
            default:
                return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((PrivacyItem) obj).getPrivacyType().ordinal()), Integer.valueOf(((PrivacyItem) obj2).getPrivacyType().ordinal()));
        }
    }
}
