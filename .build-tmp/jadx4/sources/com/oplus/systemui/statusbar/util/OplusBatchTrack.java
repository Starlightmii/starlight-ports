package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.coe.expr.Expressions$28$;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import oplus.util.OplusEncryptionUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBatchTrack {
    private static final String EVENT_TIME = "post_event_time";
    private static final String TAG = "OplusBatchTrack";
    public static final OplusBatchTrack INSTANCE = new OplusBatchTrack();
    public static long lastUploadTime = System.currentTimeMillis();
    private static final Map<Pair<String, String>, LogBuffer> cache = new LinkedHashMap();
    private static final Map<String, TrackItem> itemsMap = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LogBuffer {
        public int count;
        private final List<String[]> logs;

        public LogBuffer(int i, List<String[]> list) {
            this.count = i;
            this.logs = list;
        }

        public final List<String[]> component2() {
            return this.logs;
        }

        public final LogBuffer copy(int i, List<String[]> list) {
            return new LogBuffer(i, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LogBuffer)) {
                return false;
            }
            LogBuffer logBuffer = (LogBuffer) obj;
            return this.count == logBuffer.count && Intrinsics.areEqual(this.logs, logBuffer.logs);
        }

        public final int getCount() {
            return this.count;
        }

        public final List<String[]> getLogs() {
            return this.logs;
        }

        public final int hashCode() {
            return this.logs.hashCode() + (Integer.hashCode(this.count) * 31);
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public String toString() {
            return CollectionsKt___CollectionsKt.joinToString$default(this.logs, ";", "[", "]", new OplusBatchTrack$LogBuffer$$ExternalSyntheticLambda0(), 24);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TrackItem {
        private final List<String> keys;

        public TrackItem(String[] strArr) {
            List<String> mutableList = ArraysKt___ArraysKt.toMutableList(strArr);
            mutableList.add(OplusBatchTrack.EVENT_TIME);
            this.keys = mutableList;
        }

        /* JADX WARN: Code duplicated, block: B:9:0x002b A[PHI: r3
          0x002b: PHI (r3v5 java.lang.String) = (r3v4 java.lang.String), (r3v8 java.lang.String) binds: [B:11:0x0033, B:7:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
        public final String[] getData(Map<String, String> map) {
            String packageNameHash;
            int size = this.keys.size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                String str = this.keys.get(i);
                String str2 = "";
                if (Intrinsics.areEqual(str, "pkg")) {
                    packageNameHash = OplusEncryptionUtils.getPackageNameHash(map.get(str));
                    if (packageNameHash != null) {
                        str2 = packageNameHash;
                    }
                } else {
                    packageNameHash = map.get(str);
                    if (packageNameHash != null) {
                        str2 = packageNameHash;
                    }
                }
                strArr[i] = str2;
            }
            return strArr;
        }

        public final List<String> getKeys() {
            return this.keys;
        }
    }

    public static final void addMap(String str, String... strArr) {
        Map<String, TrackItem> map = itemsMap;
        if (map.containsKey(str)) {
            return;
        }
        map.put(str, new TrackItem(strArr));
    }

    public static final boolean onCommon(Context context, String str, String str2, Map<String, String> map) {
        TrackItem trackItem = itemsMap.get(str2);
        if (trackItem == null) {
            Expressions$28$.ExternalSyntheticBUOutline0.m(Bundler$.ExternalSyntheticOutline0.m("you must put ", str2, " map to ITME_MAP"));
            return false;
        }
        Map<Pair<String, String>, LogBuffer> map2 = cache;
        LogBuffer logBuffer = map2.get(TuplesKt.to(str, str2));
        if (logBuffer == null) {
            logBuffer = new LogBuffer(0, new LinkedList());
            map2.put(TuplesKt.to(str, str2), logBuffer);
        }
        map.put(EVENT_TIME, String.valueOf(System.currentTimeMillis()));
        logBuffer.getLogs().add(trackItem.getData(map));
        logBuffer.setCount(logBuffer.getCount() + 1);
        INSTANCE.getClass();
        if (logBuffer.getCount() >= 100) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(logBuffer.getCount(), "OplusBatchTrack-->", "SystemUi--Notification", ExifInterface$.ExternalSyntheticOutline1.m("logTag = ", str, ", eventId = ", str2, ", count = "));
            }
            send(context, str, str2, logBuffer);
            return true;
        }
        if (System.currentTimeMillis() - lastUploadTime > 86400000) {
            lastUploadTime = System.currentTimeMillis();
            Log.d(TAG, "sendAll: ");
            Iterator<T> it = map2.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (((LogBuffer) entry.getValue()).getCount() > 0) {
                    OplusBatchTrack oplusBatchTrack = INSTANCE;
                    String str3 = (String) ((Pair) entry.getKey()).getFirst();
                    String str4 = (String) ((Pair) entry.getKey()).getSecond();
                    LogBuffer logBuffer2 = (LogBuffer) entry.getValue();
                    oplusBatchTrack.getClass();
                    send(context, str3, str4, logBuffer2);
                }
            }
        }
        return true;
    }

    public static final boolean onCommonBatch(Context context, String str, String str2, List<? extends Map<String, String>> list, int i) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            onCommon(context, str, str2, (Map) it.next());
        }
        return true;
    }

    public static void send(Context context, String str, String str2, LogBuffer logBuffer) {
        String strJoinToString$default;
        List<String> keys;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TrackItem trackItem = itemsMap.get(str2);
        if (trackItem == null || (keys = trackItem.getKeys()) == null || (strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(keys, OplusPreventModeData.SEPARATOR, (CharSequence) null, (CharSequence) null, (Function1) null, 62)) == null) {
            strJoinToString$default = LockScreenStatisticUtil.REPORT_ERROR;
        }
        linkedHashMap.put("column_key", strJoinToString$default);
        linkedHashMap.put("row_value", logBuffer.toString());
        linkedHashMap.put("reason", logBuffer.getCount() >= 100 ? "count" : "time");
        OplusTrackProxyUtil.onCommon(context, str, str2, linkedHashMap);
        logBuffer.setCount(0);
        logBuffer.getLogs().clear();
    }
}
