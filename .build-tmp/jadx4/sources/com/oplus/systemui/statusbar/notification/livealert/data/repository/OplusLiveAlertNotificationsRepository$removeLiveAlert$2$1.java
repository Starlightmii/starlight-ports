package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.statusbar.notification.livealert.data.model.SbnModel;
import java.util.function.Predicate;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsRepository$removeLiveAlert$2$1 implements Predicate {
    public /* synthetic */ NotificationEntry $entry;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return Intrinsics.areEqual(((SbnModel) obj).getSbn().getKey(), this.$entry.getKey());
    }
}
