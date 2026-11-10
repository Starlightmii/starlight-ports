package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.statusbar.notification.livealert.data.model.SbnModel;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsRepository$updateLiveAlert$1 implements UnaryOperator {
    public /* synthetic */ NotificationEntry $entry;
    public /* synthetic */ int $entryFlag;
    public /* synthetic */ int $remindType;
    public /* synthetic */ boolean $shouldReminderAgain;
    public /* synthetic */ OplusLiveAlertNotificationsRepository this$0;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        SbnModel sbnModel = (SbnModel) obj;
        if (!Intrinsics.areEqual(sbnModel.getSbn().getKey(), this.$entry.getKey())) {
            return sbnModel;
        }
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        NotificationEntry notificationEntry = this.$entry;
        int i = this.$entryFlag;
        int i2 = this.$remindType;
        return oplusLiveAlertNotificationsRepository.entryToLiveAlert(notificationEntry, i, i2, !(i2 == 11 || i2 == 12 || i2 == 13) || this.$shouldReminderAgain);
    }
}
