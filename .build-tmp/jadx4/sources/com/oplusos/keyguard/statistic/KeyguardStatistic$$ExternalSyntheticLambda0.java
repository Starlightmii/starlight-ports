package com.oplusos.keyguard.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardStatistic$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ Context f$0;

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f$0;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = context != null ? context.getSharedPreferences("user_data_collection_info", 0).getLong("last_send_durable_count_time", 0L) : -1L;
        if (jCurrentTimeMillis - j <= 10800000) {
            if (j - jCurrentTimeMillis > 10800000) {
                KeyguardStatistic.writeLastSendingDurableCountTime(context);
                return;
            }
            return;
        }
        if (KeyguardStatistic.sDurableCounter != null) {
            DurableCounter durableCounter = KeyguardStatistic.sDurableCounter;
            synchronized (durableCounter.mRecords) {
                durableCounter.saveLocked();
            }
        }
        if (KeyguardStatistic.sDurableCounter == null || context == null) {
            return;
        }
        List recordsList = KeyguardStatistic.sDurableCounter.getRecordsList();
        for (int i = 0; i < recordsList.size(); i += 2) {
            int i2 = i + 1;
            if (i2 < recordsList.size()) {
                String str = (String) recordsList.get(i);
                String str2 = (String) recordsList.get(i2);
                if (str2 != null && KeyguardStatistic.NUMERIC_PATTERN.matcher(str2).matches()) {
                    HashMap map = new HashMap();
                    map.put("event_count", str2);
                    OplusTrackProxyUtil.onCommon(context, "20082003", str, map);
                }
            }
        }
        DurableCounter durableCounter2 = KeyguardStatistic.sDurableCounter;
        synchronized (durableCounter2.mRecords) {
            durableCounter2.mRecords.clear();
            DurableCounter.Editor editor = durableCounter2.mEditor;
            SharedPreferences.Editor editorEdit = editor.mContext.getSharedPreferences(editor.mFileName, 0).edit();
            editorEdit.clear();
            editorEdit.commit();
        }
        KeyguardStatistic.writeLastSendingDurableCountTime(context);
    }
}
