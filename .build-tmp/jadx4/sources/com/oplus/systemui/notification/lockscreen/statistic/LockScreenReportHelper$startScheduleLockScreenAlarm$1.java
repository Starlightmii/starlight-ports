package com.oplus.systemui.notification.lockscreen.statistic;

import android.app.AlarmManager;
import android.content.Context;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenReportHelper$startScheduleLockScreenAlarm$1 implements AlarmManager.OnAlarmListener {
    public /* synthetic */ Context $context;

    @Override // android.app.AlarmManager.OnAlarmListener
    public final void onAlarm() throws JSONException {
        LockScreenReportHelper lockScreenReportHelper = LockScreenReportHelper.INSTANCE;
        Context context = this.$context;
        lockScreenReportHelper.getClass();
        LockScreenReportHelper.scheduleLockScreenAlarm();
        LockScreenStatisticUtil.reportSwitchCount(context);
        LockScreenStatisticUtil.reportImmerseStateStatistics(context);
        LockScreenStatisticUtil.reportSinkEvent(context);
    }
}
