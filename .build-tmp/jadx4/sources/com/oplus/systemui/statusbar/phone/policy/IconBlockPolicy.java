package com.oplus.systemui.statusbar.phone.policy;

import com.android.systemui.Dumpable;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IconBlockPolicy extends Dumpable {
    default List<String> getBlockList() {
        return CollectionsKt__CollectionsKt.emptyList();
    }
}
