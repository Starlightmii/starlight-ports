package com.oplusos.systemui.common.statistic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.collection.ArraySet;
import java.lang.invoke.VarHandle;
import java.util.Calendar;
import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DailyStatisticProxyManager {
    public static final Companion Companion = new Companion();
    private static final String SP_KEY_LAST_TIME = "last_time";
    private static final String SP_NAME = "DAILY_STATISTIC_RECORD";
    private static volatile DailyStatisticProxyManager sInstance;
    private final ArraySet mProxies = new ArraySet(0);
    private final Supplier<Long> mCurrentTime = DailyStatisticProxyManager$mCurrentTime$1.INSTANCE;
    private final DailyStatisticReceiver mReceiver = new DailyStatisticReceiver() { // from class: com.oplusos.systemui.common.statistic.DailyStatisticProxyManager$mReceiver$1
        @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
        public final void onReceive(Context context, Intent intent) {
            this.this$0.checkDateForStatistic(context);
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final DailyStatisticProxyManager getInstance() {
            if (DailyStatisticProxyManager.sInstance == null) {
                synchronized (DailyStatisticProxyManager.class) {
                    if (DailyStatisticProxyManager.sInstance == null) {
                        DailyStatisticProxyManager.sInstance = new DailyStatisticProxyManager();
                    }
                }
            }
            DailyStatisticProxyManager dailyStatisticProxyManager = DailyStatisticProxyManager.sInstance;
            dailyStatisticProxyManager.getClass();
            return dailyStatisticProxyManager;
        }
    }

    public final void checkDateForStatistic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        long j = sharedPreferences.getLong(SP_KEY_LAST_TIME, 0L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        int i2 = calendar.get(6);
        long jLongValue = this.mCurrentTime.get().longValue();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(jLongValue);
        int i3 = calendar2.get(1);
        int i4 = calendar2.get(6);
        sharedPreferences.edit().putLong(SP_KEY_LAST_TIME, jLongValue).apply();
        if (i3 > i || (i3 == i && i4 > i2)) {
            ArraySet arraySet = this.mProxies;
            DailyStatisticProxyManager$onStatistic$1 dailyStatisticProxyManager$onStatistic$1 = new DailyStatisticProxyManager$onStatistic$1();
            dailyStatisticProxyManager$onStatistic$1.$context = context;
            VarHandle.storeStoreFence();
            arraySet.forEach(dailyStatisticProxyManager$onStatistic$1);
        }
    }

    public final void init(ArraySet arraySet) {
        this.mProxies.addAll(arraySet);
    }

    public final void registerDailyStatisticListener(Context context) {
        this.mReceiver.register(context);
        checkDateForStatistic(context);
    }
}
