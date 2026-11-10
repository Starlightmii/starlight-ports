package com.oplus.systemui.notification.statistic;

import android.app.AlarmManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1 implements AlarmManager.OnAlarmListener {
    public /* synthetic */ NotificationTrackLinkHelper this$0;

    @Override // android.app.AlarmManager.OnAlarmListener
    public final void onAlarm() {
        NotificationTrackLinkHelper.access$sendDailyExposeNotificationsData(this.this$0);
    }
}
