package com.oplus.systemui.notification.lockscreen.statistic;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Handler;
import com.android.systemui.util.AlarmTimeout;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenReportHelper {
    public static final LockScreenReportHelper INSTANCE = new LockScreenReportHelper();
    private static final String TAG = "LockScreenReportHelper";
    private static AlarmTimeout lockScreenAlarmTimeout;

    public static void scheduleLockScreenAlarm() {
        AlarmTimeout alarmTimeout = lockScreenAlarmTimeout;
        if (alarmTimeout != null) {
            alarmTimeout.schedule(2, 3, 86400000L);
        }
    }

    public final void init(Context context, Handler handler) {
        Object systemService = context.getSystemService("alarm");
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            return;
        }
        LockScreenReportHelper$startScheduleLockScreenAlarm$1 lockScreenReportHelper$startScheduleLockScreenAlarm$1 = new LockScreenReportHelper$startScheduleLockScreenAlarm$1();
        lockScreenReportHelper$startScheduleLockScreenAlarm$1.$context = context;
        VarHandle.storeStoreFence();
        lockScreenAlarmTimeout = new AlarmTimeout(alarmManager, lockScreenReportHelper$startScheduleLockScreenAlarm$1, TAG, handler);
        scheduleLockScreenAlarm();
    }
}
