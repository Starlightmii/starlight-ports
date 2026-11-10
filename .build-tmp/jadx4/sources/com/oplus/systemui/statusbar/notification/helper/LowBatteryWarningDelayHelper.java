package com.oplus.systemui.statusbar.notification.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LowBatteryWarningDelayHelper {
    public static final String ACTION_LOW_BATTERY_WARNING_HOLD = "android.systemui.action.LOW_BATTERY_WARNING_HOLD";
    public static final String ACTION_SUPER_POWER_READY_NOTIFY = "oplus.intent.action.superpowersave.ready";
    public static final Companion Companion = new Companion();
    private static volatile LowBatteryWarningDelayHelper sInstance;
    public boolean beforeUnlock;
    private final Context context;
    public boolean hadBootCompleted;
    public boolean hasHoldWarningShow;
    public boolean holdLowBatteryWarning = false;
    public boolean needShowDialog;
    public boolean timeOut;
    public boolean warningBeforeUnlock;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final LowBatteryWarningDelayHelper getInstance(Context context) {
            if (LowBatteryWarningDelayHelper.sInstance == null) {
                synchronized (LowBatteryWarningDelayHelper.class) {
                    if (LowBatteryWarningDelayHelper.sInstance == null) {
                        LowBatteryWarningDelayHelper.sInstance = new LowBatteryWarningDelayHelper(context);
                    }
                }
            }
            return LowBatteryWarningDelayHelper.sInstance;
        }
    }

    public LowBatteryWarningDelayHelper(Context context) {
        this.context = context;
    }

    public final boolean getBeforeUnlock() {
        return this.beforeUnlock;
    }

    public final boolean getHadBootCompleted() {
        return this.hadBootCompleted;
    }

    public final boolean getHasHoldWarningShow() {
        return this.hasHoldWarningShow;
    }

    public final boolean getHoldLowBatteryWarning() {
        return this.holdLowBatteryWarning;
    }

    public final boolean getNeedShowDialog() {
        return this.needShowDialog;
    }

    public final boolean getTimeOut() {
        return this.timeOut;
    }

    public final boolean getWarningBeforeUnlock() {
        return this.warningBeforeUnlock;
    }

    public final void setBeforeUnlock(boolean z) {
        this.beforeUnlock = z;
    }

    public final void setHadBootCompleted(boolean z) {
        this.hadBootCompleted = z;
    }

    public final void setHasHoldWarningShow(boolean z) {
        this.hasHoldWarningShow = z;
    }

    public final void setHoldLowBatteryWarning(boolean z) {
        this.holdLowBatteryWarning = z;
    }

    public final void setNeedShowDialog(boolean z) {
        this.needShowDialog = z;
    }

    public final void setTimeOut(boolean z) {
        this.timeOut = z;
    }

    public final void setWarningBeforeUnlock(boolean z) {
        this.warningBeforeUnlock = z;
    }

    public final void startHoldDelay() {
        this.beforeUnlock = false;
        Intent intent = new Intent(ACTION_LOW_BATTERY_WARNING_HOLD);
        intent.setPackage(this.context.getPackageName());
        ((AlarmManager) this.context.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + 30000, PendingIntent.getBroadcast(this.context, 0, intent, 67108864));
    }
}
