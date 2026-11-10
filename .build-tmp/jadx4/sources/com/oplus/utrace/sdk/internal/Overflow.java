package com.oplus.utrace.sdk.internal;

import android.util.LruCache;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import com.oplus.utrace.lib.SdkConfigData;
import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.utils.Logs;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Overflow {
    public static final Companion Companion = new Companion();
    private static final String TAG = "UTrace.Sdk.Overflow";
    private final LruCache<String, Boolean> overflowTraceIds = new LruCache<>(100);
    private final HashMap<String, FlowPt> flowPtMap = new HashMap<>();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class FlowKeyFrom {
        public static final /* synthetic */ FlowKeyFrom[] $VALUES;
        public static final FlowKeyFrom ERROR_CODE;
        public static final FlowKeyFrom ERROR_INFO;
        public static final FlowKeyFrom SPAN_NAME;

        static {
            FlowKeyFrom flowKeyFrom = new FlowKeyFrom("SPAN_NAME", 0);
            SPAN_NAME = flowKeyFrom;
            FlowKeyFrom flowKeyFrom2 = new FlowKeyFrom("ERROR_CODE", 1);
            ERROR_CODE = flowKeyFrom2;
            FlowKeyFrom flowKeyFrom3 = new FlowKeyFrom("ERROR_INFO", 2);
            ERROR_INFO = flowKeyFrom3;
            $VALUES = new FlowKeyFrom[]{flowKeyFrom, flowKeyFrom2, flowKeyFrom3};
        }

        public static FlowKeyFrom valueOf(String str) {
            return (FlowKeyFrom) Enum.valueOf(FlowKeyFrom.class, str);
        }

        public static FlowKeyFrom[] values() {
            return (FlowKeyFrom[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FlowPt {
        public long flow;
        public int idx;
        public boolean isOverflow;
        public long window;

        public FlowPt(int i, long j, long j2, boolean z) {
            this.window = j;
            this.flow = j2;
            this.isOverflow = z;
            this.idx = i;
        }

        public final FlowPt copy(long j, long j2, boolean z, int i) {
            return new FlowPt(i, j, j2, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlowPt)) {
                return false;
            }
            FlowPt flowPt = (FlowPt) obj;
            return this.window == flowPt.window && this.flow == flowPt.flow && this.isOverflow == flowPt.isOverflow && this.idx == flowPt.idx;
        }

        public final long getFlow() {
            return this.flow;
        }

        public final int getIdx() {
            return this.idx;
        }

        public final long getWindow() {
            return this.window;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v2, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public final int hashCode() {
            int iM = ChangeSize$.ExternalSyntheticOutline0.m(Long.hashCode(this.window) * 31, 31, this.flow);
            boolean z = this.isOverflow;
            ?? r2 = z;
            if (z) {
                r2 = 1;
            }
            return Integer.hashCode(this.idx) + ((iM + r2) * 31);
        }

        public final void setFlow(long j) {
            this.flow = j;
        }

        public final void setIdx(int i) {
            this.idx = i;
        }

        public final void setOverflow(boolean z) {
            this.isOverflow = z;
        }

        public final void setWindow(long j) {
            this.window = j;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("FlowPt(window=");
            sb.append(this.window);
            sb.append(", flow=");
            sb.append(this.flow);
            sb.append(", isOverflow=");
            sb.append(this.isOverflow);
            sb.append(", idx=");
            return FloatList$.ExternalSyntheticOutline0.m(sb, this.idx, ')');
        }
    }

    public final boolean checkIfOverflow(UTraceRecordV2 uTraceRecordV2) {
        if (this.overflowTraceIds.get(uTraceRecordV2.getTraceID()) != null) {
            Logs.INSTANCE.d(TAG, "checkIfOverflow() traceId=" + uTraceRecordV2.getTraceID() + " is marked as overflow");
            return true;
        }
        if (uTraceRecordV2.getType() == UTraceRecordV2.RecordType.TRACE_FLAG.getValue()) {
            return false;
        }
        boolean zUpdateAndCheckFlowPt = updateAndCheckFlowPt(uTraceRecordV2, FlowKeyFrom.SPAN_NAME);
        boolean zUpdateAndCheckFlowPt2 = updateAndCheckFlowPt(uTraceRecordV2, FlowKeyFrom.ERROR_CODE);
        boolean zUpdateAndCheckFlowPt3 = updateAndCheckFlowPt(uTraceRecordV2, FlowKeyFrom.ERROR_INFO);
        if (zUpdateAndCheckFlowPt && zUpdateAndCheckFlowPt2 && zUpdateAndCheckFlowPt3) {
            return false;
        }
        Logs.INSTANCE.d(TAG, "checkIfOverflow() overflow: record=" + uTraceRecordV2 + " overflow ( checkFlowPtSpanName=" + zUpdateAndCheckFlowPt + " checkFlowPtErrorCode=" + zUpdateAndCheckFlowPt2 + " checkFlowPtErrorInfo=" + zUpdateAndCheckFlowPt3 + ')');
        this.overflowTraceIds.put(uTraceRecordV2.getTraceID(), Boolean.TRUE);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:50:0x0125  */
    /* JADX WARN: Code duplicated, block: B:52:0x012f  */
    /* JADX WARN: Code duplicated, block: B:54:0x013b  */
    /* JADX WARN: Code duplicated, block: B:56:0x013f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0156  */
    public final boolean updateAndCheckFlowPt(UTraceRecordV2 uTraceRecordV2, FlowKeyFrom flowKeyFrom) {
        String spanName;
        String str;
        int iOrdinal;
        SdkConfigData.FlowCtrl flowCtrl;
        FlowPt flowPt;
        long jCurrentTimeMillis;
        Object next;
        SdkConfig sdkConfig = SdkConfig.INSTANCE;
        if (!sdkConfig.getOverflowPt().isEmpty()) {
            int i = WhenMappings.$EnumSwitchMapping$0[flowKeyFrom.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    if (uTraceRecordV2.getType() == UTraceRecordV2.RecordType.ERROR.getValue()) {
                        spanName = String.valueOf(uTraceRecordV2.getStatusCode());
                    }
                    iOrdinal = flowKeyFrom.ordinal();
                    if (str != null) {
                    }
                    if (flowCtrl != null) {
                        flowPt = this.flowPtMap.get(str);
                        if (flowPt == null) {
                            FlowPt flowPt2 = new FlowPt(iOrdinal, 0L, 0L, false);
                            this.flowPtMap.put(str, flowPt2);
                            flowPt = flowPt2;
                        }
                        jCurrentTimeMillis = System.currentTimeMillis() / flowCtrl.getWindow();
                        Logs.INSTANCE.d(TAG, "updateAndCheckFlowPt() from=" + flowKeyFrom + " flowKey=" + ((Object) str) + " currentFlow=" + flowPt + " now=" + jCurrentTimeMillis + " recordType=" + uTraceRecordV2.getType() + " flowCtrl=" + flowCtrl);
                        if (jCurrentTimeMillis != flowPt.getWindow()) {
                            flowPt.setWindow(jCurrentTimeMillis);
                            flowPt.setFlow(1L);
                            flowPt.setOverflow(false);
                            return true;
                        }
                        if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                            flowPt.setOverflow(true);
                            return false;
                        }
                        flowPt.setFlow(flowPt.getFlow() + 1);
                        flowPt.getFlow();
                        if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                            flowPt.setOverflow(true);
                            return false;
                        }
                    }
                } else {
                    if (i != 3) {
                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                        return false;
                    }
                    String info = uTraceRecordV2.getType() == UTraceRecordV2.RecordType.ERROR.getValue() ? uTraceRecordV2.getInfo() : null;
                    if (info != null) {
                        Iterator<T> it = sdkConfig.getOverflowPt().keySet().iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                        } while (!StringsKt__StringsKt.contains(info, (String) next, false));
                        str = (String) next;
                    }
                    iOrdinal = flowKeyFrom.ordinal();
                    flowCtrl = str != null ? SdkConfig.INSTANCE.getOverflowPt().get(str) : null;
                    if (flowCtrl != null) {
                        flowPt = this.flowPtMap.get(str);
                        if (flowPt == null) {
                            FlowPt flowPt3 = new FlowPt(iOrdinal, 0L, 0L, false);
                            this.flowPtMap.put(str, flowPt3);
                            flowPt = flowPt3;
                        }
                        jCurrentTimeMillis = System.currentTimeMillis() / flowCtrl.getWindow();
                        Logs.INSTANCE.d(TAG, "updateAndCheckFlowPt() from=" + flowKeyFrom + " flowKey=" + ((Object) str) + " currentFlow=" + flowPt + " now=" + jCurrentTimeMillis + " recordType=" + uTraceRecordV2.getType() + " flowCtrl=" + flowCtrl);
                        if (jCurrentTimeMillis != flowPt.getWindow()) {
                            flowPt.setWindow(jCurrentTimeMillis);
                            flowPt.setFlow(1L);
                            flowPt.setOverflow(false);
                            return true;
                        }
                        if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                            flowPt.setOverflow(true);
                            return false;
                        }
                        flowPt.setFlow(flowPt.getFlow() + 1);
                        flowPt.getFlow();
                        if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                            flowPt.setOverflow(true);
                            return false;
                        }
                    }
                }
                str = null;
                iOrdinal = flowKeyFrom.ordinal();
                if (str != null) {
                }
                if (flowCtrl != null) {
                    flowPt = this.flowPtMap.get(str);
                    if (flowPt == null) {
                        FlowPt flowPt4 = new FlowPt(iOrdinal, 0L, 0L, false);
                        this.flowPtMap.put(str, flowPt4);
                        flowPt = flowPt4;
                    }
                    jCurrentTimeMillis = System.currentTimeMillis() / flowCtrl.getWindow();
                    Logs.INSTANCE.d(TAG, "updateAndCheckFlowPt() from=" + flowKeyFrom + " flowKey=" + ((Object) str) + " currentFlow=" + flowPt + " now=" + jCurrentTimeMillis + " recordType=" + uTraceRecordV2.getType() + " flowCtrl=" + flowCtrl);
                    if (jCurrentTimeMillis != flowPt.getWindow()) {
                        flowPt.setWindow(jCurrentTimeMillis);
                        flowPt.setFlow(1L);
                        flowPt.setOverflow(false);
                        return true;
                    }
                    if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                        flowPt.setOverflow(true);
                        return false;
                    }
                    flowPt.setFlow(flowPt.getFlow() + 1);
                    flowPt.getFlow();
                    if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                        flowPt.setOverflow(true);
                        return false;
                    }
                }
            } else {
                spanName = uTraceRecordV2.getSpanName();
                int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(spanName, '(');
                int iLastIndexOf$default2 = StringsKt__StringsKt.lastIndexOf$default(spanName, ')');
                if (iLastIndexOf$default > 0 && iLastIndexOf$default < spanName.length() - 1 && iLastIndexOf$default2 > 0 && iLastIndexOf$default2 < spanName.length()) {
                    spanName = spanName.substring(iLastIndexOf$default + 1, iLastIndexOf$default2);
                }
            }
            str = spanName;
            iOrdinal = flowKeyFrom.ordinal();
            if (str != null) {
            }
            if (flowCtrl != null) {
                flowPt = this.flowPtMap.get(str);
                if (flowPt == null) {
                    FlowPt flowPt5 = new FlowPt(iOrdinal, 0L, 0L, false);
                    this.flowPtMap.put(str, flowPt5);
                    flowPt = flowPt5;
                }
                jCurrentTimeMillis = System.currentTimeMillis() / flowCtrl.getWindow();
                Logs.INSTANCE.d(TAG, "updateAndCheckFlowPt() from=" + flowKeyFrom + " flowKey=" + ((Object) str) + " currentFlow=" + flowPt + " now=" + jCurrentTimeMillis + " recordType=" + uTraceRecordV2.getType() + " flowCtrl=" + flowCtrl);
                if (jCurrentTimeMillis != flowPt.getWindow()) {
                    flowPt.setWindow(jCurrentTimeMillis);
                    flowPt.setFlow(1L);
                    flowPt.setOverflow(false);
                    return true;
                }
                if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                    flowPt.setOverflow(true);
                    return false;
                }
                flowPt.setFlow(flowPt.getFlow() + 1);
                flowPt.getFlow();
                if (flowPt.getFlow() > flowCtrl.getMaxFlow()) {
                    flowPt.setOverflow(true);
                    return false;
                }
            }
        }
        return true;
    }
}
