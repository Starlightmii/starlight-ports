package com.oplusos.keyguard.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.statistic.OplusStatisticSamplingController;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class KeyguardStatistic {
    public static final HashMap NEED_DURABLE_COUNT_EVENTS;
    public static final Pattern NUMERIC_PATTERN;
    public static volatile DurableCounter sDurableCounter;

    static {
        HashMap map = new HashMap();
        NEED_DURABLE_COUNT_EVENTS = map;
        NUMERIC_PATTERN = Pattern.compile("[0-9]*");
        sDurableCounter = null;
        map.put("counts_unlock_ripple", 1);
        OplusStatisticSamplingController.INSTANCE.addSamplingEvent("total_time_for_input_password", 100);
    }

    public static void sendEventData(Context context, String str, Map map) {
        if (!OplusStatisticSamplingController.INSTANCE.needSampling(str)) {
            KgdLogUtil.i("KeyguardStatistic", "eventData intercepted by sampling, eventID = ".concat(str));
            return;
        }
        if (!NEED_DURABLE_COUNT_EVENTS.containsKey(str)) {
            if (context != null) {
                OplusTrackProxyUtil.onCommon(context, "20082003", str, map);
                return;
            } else {
                Log.e("KeyguardStatistic", "sendDynamicEventData error, context is null");
                return;
            }
        }
        if (sDurableCounter == null) {
            synchronized (KeyguardStatistic.class) {
                try {
                    if (sDurableCounter == null) {
                        sDurableCounter = new DurableCounter(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        DurableCounter durableCounter = sDurableCounter;
        synchronized (durableCounter.mRecords) {
            durableCounter.countLocked(str);
        }
    }

    public static void writeLastSendingDurableCountTime(Context context) {
        if (context != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("user_data_collection_info", 0).edit();
            editorEdit.putLong("last_send_durable_count_time", jCurrentTimeMillis);
            editorEdit.apply();
            KgdLogUtil.d("KeyguardStatistic", "writeLastSendingDurableCountTime, write time " + jCurrentTimeMillis);
        }
    }
}
