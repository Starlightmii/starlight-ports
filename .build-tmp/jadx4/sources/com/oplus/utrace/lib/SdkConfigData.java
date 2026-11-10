package com.oplus.utrace.lib;

import android.os.Bundle;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.TimeSupplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SdkConfigData {
    public static final Companion Companion = new Companion();
    private static final FlowCtrl DEFAULT_OVERFLOW = new FlowCtrl(1000, Long.MAX_VALUE);
    public static final String EMPTY_JSON_ARRAY = "[]";
    private static final String KEY_BIND_WINDOW = "bind_window";
    private static final String KEY_EXPIRE_TIME = "expire_time";
    private static final String KEY_HLOG_CTRL = "hlog_ctrl";
    private static final String KEY_IS_ENABLED = "is_enabled";
    private static final String KEY_LOGS_DEBUGGABLE = "logs_debuggable";
    private static final String KEY_MAX_FLOW = "max_flow";
    private static final String KEY_OVERFLOW_MAX = "overflow_max";
    private static final String KEY_OVERFLOW_PT = "overflow_pt";
    private static final String KEY_OVERFLOW_WINDOW = "overflow_window";
    private static final String KEY_PATTERN = "pattern";
    private static final String KEY_TRACE_CACHE_METRICS = "trace_cache_metrics";
    private static final String KEY_TRACE_LOG_CTRL = "trace_log_ctrl";
    private static final String KEY_WINDOW = "window";
    public static final String METHOD_QUERY_SDK_CONFIG = "query_sdk_config";
    private static final String TAG = "UTrace.Lib.SdkConfigData";
    public final long bindWindow;
    public final long expireTime;
    private final Map<String, FlowCtrl> flowCtrlPt;
    private final HLogCtrl hLogCtrl;
    public final boolean isEnabled;
    public final boolean logsDebuggable;
    private final FlowCtrl overflow;
    private final String overflowPt;
    private final TraceCacheMetrics traceCacheMetrics;
    private final TraceLogCtrl traceLogCtrl;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static SdkConfigData create(boolean z, long j, long j2, String str, boolean z2, String str2, String str3, String str4, long j3, long j4) {
            return new SdkConfigData(z, new FlowCtrl(j >= 1000 ? j : 1000L, j2 <= 0 ? Long.MAX_VALUE : j2), str, z2, TraceCacheMetrics.Companion.fromJsonString$utrace_lib_release(str2), TraceLogCtrl.Companion.fromJsonString$utrace_lib_release(str3), HLogCtrl.Companion.fromJsonString(str4), j3 < 60000 ? 60000L : j3, j4);
        }

        public static /* synthetic */ void getDEFAULT_EXPIRE_WINDOW$annotations() {
        }

        public final SdkConfigData createFromBundle(Bundle bundle) {
            Object obj;
            try {
                boolean z = bundle.getBoolean(SdkConfigData.KEY_IS_ENABLED, false);
                long j = bundle.getLong(SdkConfigData.KEY_OVERFLOW_WINDOW, SdkConfigData.DEFAULT_OVERFLOW.getWindow());
                long j2 = bundle.getLong(SdkConfigData.KEY_OVERFLOW_MAX, SdkConfigData.DEFAULT_OVERFLOW.getMaxFlow());
                String string = bundle.getString(SdkConfigData.KEY_OVERFLOW_PT, SdkConfigData.EMPTY_JSON_ARRAY);
                boolean z2 = bundle.getBoolean(SdkConfigData.KEY_LOGS_DEBUGGABLE, Logs.INSTANCE.getDebuggable());
                String string2 = bundle.getString(SdkConfigData.KEY_TRACE_CACHE_METRICS, "");
                String string3 = bundle.getString(SdkConfigData.KEY_TRACE_LOG_CTRL, "");
                String string4 = bundle.getString(SdkConfigData.KEY_HLOG_CTRL, "");
                long j3 = bundle.getLong(SdkConfigData.KEY_BIND_WINDOW, 3600000L);
                TimeSupplier.Companion.getInstance().getClass();
                obj = Result.constructor-impl(create(z, j, j2, string, z2, string2, string3, string4, j3, bundle.getLong(SdkConfigData.KEY_EXPIRE_TIME, System.currentTimeMillis())));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs.INSTANCE.d(SdkConfigData.TAG, Intrinsics.stringPlus(th2.getMessage(), "createFromBundle() exception: "), th2);
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            return (SdkConfigData) obj;
        }

        public final SdkConfigData createFromJSONString(String str) {
            Object obj;
            try {
                JSONObject jSONObject = new JSONObject(str);
                boolean zOptBoolean = jSONObject.optBoolean(SdkConfigData.KEY_IS_ENABLED, false);
                long jOptLong = jSONObject.optLong(SdkConfigData.KEY_OVERFLOW_WINDOW, SdkConfigData.DEFAULT_OVERFLOW.getWindow());
                long jOptLong2 = jSONObject.optLong(SdkConfigData.KEY_OVERFLOW_MAX, SdkConfigData.DEFAULT_OVERFLOW.getMaxFlow());
                String strOptString = jSONObject.optString(SdkConfigData.KEY_OVERFLOW_PT, SdkConfigData.EMPTY_JSON_ARRAY);
                boolean zOptBoolean2 = jSONObject.optBoolean(SdkConfigData.KEY_LOGS_DEBUGGABLE, Logs.INSTANCE.getDebuggable());
                String strOptString2 = jSONObject.optString(SdkConfigData.KEY_TRACE_CACHE_METRICS, "");
                String strOptString3 = jSONObject.optString(SdkConfigData.KEY_TRACE_LOG_CTRL, "");
                String strOptString4 = jSONObject.optString(SdkConfigData.KEY_HLOG_CTRL, "");
                long jOptLong3 = jSONObject.optLong(SdkConfigData.KEY_BIND_WINDOW, 3600000L);
                TimeSupplier.Companion.getInstance().getClass();
                obj = Result.constructor-impl(create(zOptBoolean, jOptLong, jOptLong2, strOptString, zOptBoolean2, strOptString2, strOptString3, strOptString4, jOptLong3, jSONObject.optLong(SdkConfigData.KEY_EXPIRE_TIME, System.currentTimeMillis())));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs logs = Logs.INSTANCE;
                StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("createFromJSONString() string='", str, "' exception='");
                sbM.append((Object) th2.getMessage());
                sbM.append('\'');
                logs.d(SdkConfigData.TAG, sbM.toString(), th2);
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            return (SdkConfigData) obj;
        }

        public final long expireTime(boolean z, Float f) {
            long default_expire_window;
            if (z) {
                default_expire_window = 60000;
            } else {
                Long lValueOf = null;
                if (f != null) {
                    if (f.floatValue() <= 0.0f) {
                        f = null;
                    }
                    if (f != null) {
                        lValueOf = Long.valueOf((long) (f.floatValue() * 3600000.0f));
                    }
                }
                default_expire_window = lValueOf == null ? getDEFAULT_EXPIRE_WINDOW() : lValueOf.longValue();
            }
            TimeSupplier.Companion.getInstance().getClass();
            return System.currentTimeMillis() + default_expire_window;
        }

        public final long getDEFAULT_EXPIRE_WINDOW() {
            return 86400000L;
        }

        public final Map<String, FlowCtrl> parseOverflowPt$utrace_lib_release(String str) {
            Object obj;
            List arrayList;
            try {
                JSONArray jSONArray = new JSONArray(str);
                IntRange intRangeUntil = RangesKt___RangesKt.until(0, jSONArray.length());
                ArrayList arrayList2 = new ArrayList();
                IntIterator it = intRangeUntil.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(it.nextInt());
                    if (jSONObjectOptJSONObject != null) {
                        arrayList2.add(jSONObjectOptJSONObject);
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    JSONObject jSONObject = (JSONObject) obj2;
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(SdkConfigData.KEY_PATTERN);
                    FlowCtrlPt0 flowCtrlPt0 = null;
                    if (jSONArrayOptJSONArray == null) {
                        arrayList = null;
                    } else {
                        IntRange intRangeUntil2 = RangesKt___RangesKt.until(0, jSONArrayOptJSONArray.length());
                        arrayList = new ArrayList();
                        IntIterator it2 = intRangeUntil2.iterator();
                        while (it2.hasNext()) {
                            String strOptString = jSONArrayOptJSONArray.optString(it2.nextInt());
                            if (strOptString != null) {
                                arrayList.add(strOptString);
                            }
                        }
                    }
                    if (arrayList == null) {
                        arrayList = CollectionsKt__CollectionsKt.emptyList();
                    }
                    List list = arrayList;
                    long jOptLong = jSONObject.optLong(SdkConfigData.KEY_WINDOW);
                    long jOptLong2 = jSONObject.optLong(SdkConfigData.KEY_MAX_FLOW);
                    if (!list.isEmpty() && jOptLong >= 1000 && jOptLong2 > 0) {
                        flowCtrlPt0 = new FlowCtrlPt0(list, jOptLong, jOptLong2);
                    }
                    if (flowCtrlPt0 != null) {
                        arrayList3.add(flowCtrlPt0);
                    }
                }
                obj = Result.constructor-impl(arrayList3);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs logs = Logs.INSTANCE;
                StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("parseOverflowPt() string=", str, " exception=");
                sbM.append((Object) th2.getMessage());
                logs.d(SdkConfigData.TAG, sbM.toString(), th2);
            }
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            if (obj instanceof Result.Failure) {
                obj = listEmptyList;
            }
            HashMap map = new HashMap();
            for (FlowCtrlPt0 flowCtrlPt1 : (List) obj) {
                FlowCtrl flowCtrl = new FlowCtrl(flowCtrlPt1.getWindow(), flowCtrlPt1.getMaxFlow());
                Iterator<T> it3 = flowCtrlPt1.getPatterns().iterator();
                while (it3.hasNext()) {
                    map.put((String) it3.next(), flowCtrl);
                }
            }
            return map;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FlowCtrl {
        public final long maxFlow;
        public final long window;

        public FlowCtrl(long j, long j2) {
            this.window = j;
            this.maxFlow = j2;
        }

        public final FlowCtrl copy(long j, long j2) {
            return new FlowCtrl(j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlowCtrl)) {
                return false;
            }
            FlowCtrl flowCtrl = (FlowCtrl) obj;
            return this.window == flowCtrl.window && this.maxFlow == flowCtrl.maxFlow;
        }

        public final long getMaxFlow() {
            return this.maxFlow;
        }

        public final long getWindow() {
            return this.window;
        }

        public final int hashCode() {
            return Long.hashCode(this.maxFlow) + (Long.hashCode(this.window) * 31);
        }

        public String toString() {
            return "FlowCtrl(window=" + this.window + ", maxFlow=" + this.maxFlow + ')';
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FlowCtrlPt0 {
        public final long maxFlow;
        private final List<String> patterns;
        public final long window;

        public FlowCtrlPt0(List<String> list, long j, long j2) {
            this.patterns = list;
            this.window = j;
            this.maxFlow = j2;
        }

        public final List<String> component1() {
            return this.patterns;
        }

        public final FlowCtrlPt0 copy(List<String> list, long j, long j2) {
            return new FlowCtrlPt0(list, j, j2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlowCtrlPt0)) {
                return false;
            }
            FlowCtrlPt0 flowCtrlPt0 = (FlowCtrlPt0) obj;
            return Intrinsics.areEqual(this.patterns, flowCtrlPt0.patterns) && this.window == flowCtrlPt0.window && this.maxFlow == flowCtrlPt0.maxFlow;
        }

        public final long getMaxFlow() {
            return this.maxFlow;
        }

        public final List<String> getPatterns() {
            return this.patterns;
        }

        public final long getWindow() {
            return this.window;
        }

        public final int hashCode() {
            return Long.hashCode(this.maxFlow) + ChangeSize$.ExternalSyntheticOutline0.m(this.patterns.hashCode() * 31, 31, this.window);
        }

        public String toString() {
            return "FlowCtrlPt0(patterns=" + this.patterns + ", window=" + this.window + ", maxFlow=" + this.maxFlow + ')';
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HLogCtrl {
        public static final Companion Companion = new Companion();
        public final int expireDays;
        public final boolean logEnabled;
        public final int maxLogFileSizeMB;
        public final int maxLogFilesMB;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final HLogCtrl fromJsonString(String str) {
                Object obj;
                Integer num = null;
                if (StringsKt__StringsKt.isBlank(str)) {
                    str = null;
                }
                if (str == null) {
                    return null;
                }
                try {
                    obj = Result.constructor-impl(new JSONObject(str));
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (obj instanceof Result.Failure) {
                    obj = null;
                }
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject == null) {
                    return null;
                }
                boolean zOptBoolean = jSONObject.optBoolean(HLogConst.KEY_LOG_ENABLED, false);
                Integer numValueOf = Integer.valueOf(jSONObject.optInt(HLogConst.KEY_LOG_EXPIRE_DAYS, 0));
                if (numValueOf.intValue() <= 0) {
                    numValueOf = null;
                }
                int iIntValue = numValueOf == null ? 7 : numValueOf.intValue();
                Integer numValueOf2 = Integer.valueOf(jSONObject.optInt(HLogConst.KEY_MAX_LOG_FILES_MB, 0));
                if (numValueOf2.intValue() <= 0) {
                    numValueOf2 = null;
                }
                int iIntValue2 = numValueOf2 == null ? 500 : numValueOf2.intValue();
                Integer numValueOf3 = Integer.valueOf(jSONObject.optInt(HLogConst.KEY_MAX_LOG_FILE_SIZE_MB, 0));
                int iIntValue3 = numValueOf3.intValue();
                IntRange log_file_size_mb_range = HLogConst.INSTANCE.getLOG_FILE_SIZE_MB_RANGE();
                int first = log_file_size_mb_range.getFirst();
                if (iIntValue3 <= log_file_size_mb_range.getLast() && first <= iIntValue3) {
                    num = numValueOf3;
                }
                return new HLogCtrl(iIntValue, iIntValue2, num == null ? 1 : num.intValue(), zOptBoolean);
            }
        }

        public HLogCtrl(int i, int i2, int i3, boolean z) {
            this.logEnabled = z;
            this.expireDays = i;
            this.maxLogFilesMB = i2;
            this.maxLogFileSizeMB = i3;
        }

        public final HLogCtrl copy(boolean z, int i, int i2, int i3) {
            return new HLogCtrl(i, i2, i3, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof HLogCtrl)) {
                return false;
            }
            HLogCtrl hLogCtrl = (HLogCtrl) obj;
            return this.logEnabled == hLogCtrl.logEnabled && this.expireDays == hLogCtrl.expireDays && this.maxLogFilesMB == hLogCtrl.maxLogFilesMB && this.maxLogFileSizeMB == hLogCtrl.maxLogFileSizeMB;
        }

        public final int getExpireDays() {
            return this.expireDays;
        }

        public final boolean getLogEnabled() {
            return this.logEnabled;
        }

        public final int getMaxLogFileSizeMB() {
            return this.maxLogFileSizeMB;
        }

        public final int getMaxLogFilesMB() {
            return this.maxLogFilesMB;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v6 */
        public final int hashCode() {
            boolean z = this.logEnabled;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return Integer.hashCode(this.maxLogFileSizeMB) + MonoSpline$.ExternalSyntheticOutline0.m(this.maxLogFilesMB, MonoSpline$.ExternalSyntheticOutline0.m(this.expireDays, r0 * 31, 31), 31);
        }

        public final String toJsonString() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HLogConst.KEY_LOG_ENABLED, getLogEnabled());
            jSONObject.put(HLogConst.KEY_LOG_EXPIRE_DAYS, getExpireDays());
            jSONObject.put(HLogConst.KEY_MAX_LOG_FILES_MB, getMaxLogFilesMB());
            jSONObject.put(HLogConst.KEY_MAX_LOG_FILE_SIZE_MB, getMaxLogFileSizeMB());
            return jSONObject.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("HLogCtrl(logEnabled=");
            sb.append(this.logEnabled);
            sb.append(", expireDays=");
            sb.append(this.expireDays);
            sb.append(", maxLogFilesMB=");
            sb.append(this.maxLogFilesMB);
            sb.append(", maxLogFileSizeMB=");
            return FloatList$.ExternalSyntheticOutline0.m(sb, this.maxLogFileSizeMB, ')');
        }
    }

    public /* synthetic */ SdkConfigData(boolean z, FlowCtrl flowCtrl, String str, boolean z2, TraceCacheMetrics traceCacheMetrics, TraceLogCtrl traceLogCtrl, HLogCtrl hLogCtrl, long j, int i) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? DEFAULT_OVERFLOW : flowCtrl, (i & 4) != 0 ? EMPTY_JSON_ARRAY : str, (i & 8) != 0 ? Logs.INSTANCE.getDebuggable() : z2, (i & 16) != 0 ? new TraceCacheMetrics() : traceCacheMetrics, (i & 32) != 0 ? new TraceLogCtrl() : traceLogCtrl, (i & 64) != 0 ? null : hLogCtrl, (i & 128) != 0 ? 3600000L : j, (i & 256) != 0 ? Companion.expireTime(false, null) : 0L);
    }

    public final FlowCtrl component2() {
        return this.overflow;
    }

    public final String component3() {
        return this.overflowPt;
    }

    public final TraceCacheMetrics component5() {
        return this.traceCacheMetrics;
    }

    public final TraceLogCtrl component6() {
        return this.traceLogCtrl;
    }

    public final HLogCtrl component7() {
        return this.hLogCtrl;
    }

    public final SdkConfigData copy(boolean z, FlowCtrl flowCtrl, String str, boolean z2, TraceCacheMetrics traceCacheMetrics, TraceLogCtrl traceLogCtrl, HLogCtrl hLogCtrl, long j, long j2) {
        return new SdkConfigData(z, flowCtrl, str, z2, traceCacheMetrics, traceLogCtrl, hLogCtrl, j, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SdkConfigData)) {
            return false;
        }
        SdkConfigData sdkConfigData = (SdkConfigData) obj;
        return this.isEnabled == sdkConfigData.isEnabled && Intrinsics.areEqual(this.overflow, sdkConfigData.overflow) && Intrinsics.areEqual(this.overflowPt, sdkConfigData.overflowPt) && this.logsDebuggable == sdkConfigData.logsDebuggable && Intrinsics.areEqual(this.traceCacheMetrics, sdkConfigData.traceCacheMetrics) && Intrinsics.areEqual(this.traceLogCtrl, sdkConfigData.traceLogCtrl) && Intrinsics.areEqual(this.hLogCtrl, sdkConfigData.hLogCtrl) && this.bindWindow == sdkConfigData.bindWindow && this.expireTime == sdkConfigData.expireTime;
    }

    public final long getBindWindow() {
        return this.bindWindow;
    }

    public final long getExpireTime() {
        return this.expireTime;
    }

    public final Map<String, FlowCtrl> getFlowCtrlPt() {
        return this.flowCtrlPt;
    }

    public final HLogCtrl getHLogCtrl() {
        return this.hLogCtrl;
    }

    public final boolean getLogsDebuggable() {
        return this.logsDebuggable;
    }

    public final FlowCtrl getOverflow() {
        return this.overflow;
    }

    public final String getOverflowPt() {
        return this.overflowPt;
    }

    public final TraceCacheMetrics getTraceCacheMetrics() {
        return this.traceCacheMetrics;
    }

    public final TraceLogCtrl getTraceLogCtrl() {
        return this.traceLogCtrl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    public final int hashCode() {
        boolean z = this.isEnabled;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((this.overflow.hashCode() + (r1 * 31)) * 31, 31, this.overflowPt);
        boolean z2 = this.logsDebuggable;
        int iHashCode = (this.traceLogCtrl.hashCode() + ((this.traceCacheMetrics.hashCode() + ((iM + (z2 ? 1 : z2)) * 31)) * 31)) * 31;
        HLogCtrl hLogCtrl = this.hLogCtrl;
        return Long.hashCode(this.expireTime) + ChangeSize$.ExternalSyntheticOutline0.m((iHashCode + (hLogCtrl == null ? 0 : hLogCtrl.hashCode())) * 31, 31, this.bindWindow);
    }

    public final String toJSONString() throws JSONException {
        String jsonString;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_IS_ENABLED, this.isEnabled);
        jSONObject.put(KEY_OVERFLOW_MAX, getOverflow().getMaxFlow());
        jSONObject.put(KEY_OVERFLOW_WINDOW, getOverflow().getWindow());
        jSONObject.put(KEY_OVERFLOW_PT, getOverflowPt());
        jSONObject.put(KEY_LOGS_DEBUGGABLE, getLogsDebuggable());
        jSONObject.put(KEY_TRACE_CACHE_METRICS, getTraceCacheMetrics().toJsonString$utrace_lib_release());
        jSONObject.put(KEY_TRACE_LOG_CTRL, getTraceLogCtrl().toJsonString$utrace_lib_release());
        HLogCtrl hLogCtrl = getHLogCtrl();
        if (hLogCtrl != null && (jsonString = hLogCtrl.toJsonString()) != null) {
            jSONObject.put(KEY_HLOG_CTRL, jsonString);
        }
        jSONObject.put(KEY_BIND_WINDOW, getBindWindow());
        return jSONObject.put(KEY_EXPIRE_TIME, getExpireTime()).toString();
    }

    public String toString() {
        return "SdkConfigData(isEnabled=" + this.isEnabled + ", overflow=" + this.overflow + ", overflowPt=" + this.overflowPt + ", logsDebuggable=" + this.logsDebuggable + ", traceCacheMetrics=" + this.traceCacheMetrics + ", traceLogCtrl=" + this.traceLogCtrl + ", hLogCtrl=" + this.hLogCtrl + ", bindWindow=" + this.bindWindow + ", expireTime=" + this.expireTime + ')';
    }

    public final void writeToBundle(Bundle bundle) {
        String jsonString;
        bundle.putBoolean(KEY_IS_ENABLED, this.isEnabled);
        bundle.putLong(KEY_OVERFLOW_MAX, this.overflow.getMaxFlow());
        bundle.putLong(KEY_OVERFLOW_WINDOW, this.overflow.getWindow());
        bundle.putString(KEY_OVERFLOW_PT, this.overflowPt);
        bundle.putBoolean(KEY_LOGS_DEBUGGABLE, this.logsDebuggable);
        bundle.putString(KEY_TRACE_CACHE_METRICS, this.traceCacheMetrics.toJsonString$utrace_lib_release());
        bundle.putString(KEY_TRACE_LOG_CTRL, this.traceLogCtrl.toJsonString$utrace_lib_release());
        HLogCtrl hLogCtrl = this.hLogCtrl;
        if (hLogCtrl != null && (jsonString = hLogCtrl.toJsonString()) != null) {
            bundle.putString(KEY_HLOG_CTRL, jsonString);
        }
        bundle.putLong(KEY_BIND_WINDOW, this.bindWindow);
        bundle.putLong(KEY_EXPIRE_TIME, this.expireTime);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TraceLogCtrl {
        public static final Companion Companion = new Companion();
        private static final String KEY_CODE_USE_INFO = "key_code_use_info";
        private static final String KEY_INTENT_USE_INFO = "key_intent_use_info";
        public final boolean codeUseInfo;
        public final boolean intentUseInfo;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final TraceLogCtrl fromJsonString$utrace_lib_release(String str) {
                Object obj;
                TraceLogCtrl traceLogCtrl = null;
                if (StringsKt__StringsKt.isBlank(str)) {
                    str = null;
                }
                if (str != null) {
                    try {
                        obj = Result.constructor-impl(new JSONObject(str));
                    } catch (Throwable th) {
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (obj instanceof Result.Failure) {
                        obj = null;
                    }
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject != null) {
                        traceLogCtrl = new TraceLogCtrl(jSONObject.optBoolean(TraceLogCtrl.KEY_INTENT_USE_INFO, false), jSONObject.optBoolean(TraceLogCtrl.KEY_CODE_USE_INFO, true));
                    }
                }
                return traceLogCtrl == null ? new TraceLogCtrl() : traceLogCtrl;
            }
        }

        public TraceLogCtrl(boolean z, boolean z2) {
            this.intentUseInfo = z;
            this.codeUseInfo = z2;
        }

        public final TraceLogCtrl copy(boolean z, boolean z2) {
            return new TraceLogCtrl(z, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TraceLogCtrl)) {
                return false;
            }
            TraceLogCtrl traceLogCtrl = (TraceLogCtrl) obj;
            return this.intentUseInfo == traceLogCtrl.intentUseInfo && this.codeUseInfo == traceLogCtrl.codeUseInfo;
        }

        public final boolean getCodeUseInfo() {
            return this.codeUseInfo;
        }

        public final boolean getIntentUseInfo() {
            return this.intentUseInfo;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3, types: [int] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5 */
        public final int hashCode() {
            boolean z = this.intentUseInfo;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = r1 * 31;
            boolean z2 = this.codeUseInfo;
            return i + (z2 ? 1 : z2);
        }

        public final String toJsonString$utrace_lib_release() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_INTENT_USE_INFO, getIntentUseInfo());
            jSONObject.put(KEY_CODE_USE_INFO, getCodeUseInfo());
            return jSONObject.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("TraceLogCtrl(intentUseInfo=");
            sb.append(this.intentUseInfo);
            sb.append(", codeUseInfo=");
            return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.codeUseInfo, ')');
        }

        public /* synthetic */ TraceLogCtrl() {
            this(false, true);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TraceCacheMetrics {
        public static final Companion Companion = new Companion();
        private static final String KEY_DELAY_ADDTRACE_LIST = "key_delay_addtrace_list";
        private static final String KEY_MAX_PENDING_RECORDS_L1 = "key_max_pending_records_l1";
        private static final String KEY_MAX_PENDING_RECORDS_L2 = "key_max_pending_records_l2";
        public final long delayAddTraceList;
        public final int maxPendingRecordsL1;
        public final int maxPendingRecordsL2;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final TraceCacheMetrics fromJsonString$utrace_lib_release(String str) {
                Object obj;
                TraceCacheMetrics traceCacheMetrics = null;
                if (StringsKt__StringsKt.isBlank(str)) {
                    str = null;
                }
                if (str != null) {
                    try {
                        obj = Result.constructor-impl(new JSONObject(str));
                    } catch (Throwable th) {
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (obj instanceof Result.Failure) {
                        obj = null;
                    }
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject != null) {
                        int iOptInt = jSONObject.optInt(TraceCacheMetrics.KEY_MAX_PENDING_RECORDS_L1, 0);
                        int iOptInt2 = jSONObject.optInt(TraceCacheMetrics.KEY_MAX_PENDING_RECORDS_L2, 0);
                        long jOptLong = jSONObject.optLong(TraceCacheMetrics.KEY_DELAY_ADDTRACE_LIST, 0L);
                        if (iOptInt > 0 && iOptInt2 > 0 && jOptLong > 0) {
                            traceCacheMetrics = new TraceCacheMetrics(iOptInt, iOptInt2, jOptLong);
                        }
                    }
                }
                return traceCacheMetrics == null ? new TraceCacheMetrics() : traceCacheMetrics;
            }
        }

        public /* synthetic */ TraceCacheMetrics() {
            this(1000, 50, 500L);
        }

        public final TraceCacheMetrics copy(int i, int i2, long j) {
            return new TraceCacheMetrics(i, i2, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TraceCacheMetrics)) {
                return false;
            }
            TraceCacheMetrics traceCacheMetrics = (TraceCacheMetrics) obj;
            return this.maxPendingRecordsL1 == traceCacheMetrics.maxPendingRecordsL1 && this.maxPendingRecordsL2 == traceCacheMetrics.maxPendingRecordsL2 && this.delayAddTraceList == traceCacheMetrics.delayAddTraceList;
        }

        public final long getDelayAddTraceList() {
            return this.delayAddTraceList;
        }

        public final int getMaxPendingRecordsL1() {
            return this.maxPendingRecordsL1;
        }

        public final int getMaxPendingRecordsL2() {
            return this.maxPendingRecordsL2;
        }

        public final int hashCode() {
            return Long.hashCode(this.delayAddTraceList) + MonoSpline$.ExternalSyntheticOutline0.m(this.maxPendingRecordsL2, Integer.hashCode(this.maxPendingRecordsL1) * 31, 31);
        }

        public final String toJsonString$utrace_lib_release() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_MAX_PENDING_RECORDS_L1, getMaxPendingRecordsL1());
            jSONObject.put(KEY_MAX_PENDING_RECORDS_L2, getMaxPendingRecordsL2());
            jSONObject.put(KEY_DELAY_ADDTRACE_LIST, getDelayAddTraceList());
            return jSONObject.toString();
        }

        public String toString() {
            return "TraceCacheMetrics(maxPendingRecordsL1=" + this.maxPendingRecordsL1 + ", maxPendingRecordsL2=" + this.maxPendingRecordsL2 + ", delayAddTraceList=" + this.delayAddTraceList + ')';
        }

        public TraceCacheMetrics(int i, int i2, long j) {
            this.maxPendingRecordsL1 = i;
            this.maxPendingRecordsL2 = i2;
            this.delayAddTraceList = j;
        }
    }

    public SdkConfigData(boolean z, FlowCtrl flowCtrl, String str, boolean z2, TraceCacheMetrics traceCacheMetrics, TraceLogCtrl traceLogCtrl, HLogCtrl hLogCtrl, long j, long j2) {
        this.isEnabled = z;
        this.overflow = flowCtrl;
        this.overflowPt = str;
        this.logsDebuggable = z2;
        this.traceCacheMetrics = traceCacheMetrics;
        this.traceLogCtrl = traceLogCtrl;
        this.hLogCtrl = hLogCtrl;
        this.bindWindow = j;
        this.expireTime = j2;
        this.flowCtrlPt = Companion.parseOverflowPt$utrace_lib_release(str);
    }
}
