package com.oplus.utrace.sdk.internal;

import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.UtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UTraceManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ UTraceManager$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                UTraceManager.INSTANCE.unBindService();
                break;
            case 1:
                UTraceManager.INSTANCE.unBindService();
                break;
            case 2:
                Logs logs = Logs.INSTANCE;
                logs.w("UTrace.Sdk.Manager", "onNullBinding() 因异常或云控开关关闭逻辑导致UTraceService返回null (proc=" + UtilsKt.getProcessName(UTraceApp.mContext) + ')');
                UTraceManager.INSTANCE.unBindService();
                if (UTraceManager.setExcpTimestamp()) {
                    if (logs.getDebuggable()) {
                        LinkedList linkedList = UTraceManager.mPendingRecords;
                        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                        Iterator it = linkedList.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((UTraceRecordV2) it.next()).getCurrent());
                        }
                        logs.d("UTrace.Sdk.Manager", Intrinsics.stringPlus(arrayList, "onNullBinding() clear mPendingRecords="));
                    }
                    UTraceManager.mPendingRecords.clear();
                }
                break;
            case 3:
                UTraceManager.INSTANCE.unBindService();
                break;
            default:
                UTraceManager.INSTANCE.getClass();
                UTraceManager.AddTraceResult addTraceResultTryProxyAddTrace2 = UTraceManager.tryProxyAddTrace2();
                Logs logs2 = Logs.INSTANCE;
                StringBuilder sb = new StringBuilder("MSG_ADDTRACE_LIST tryProxyAddTrace2 returns ");
                sb.append(addTraceResultTryProxyAddTrace2);
                sb.append(", clear mPendingRecords=");
                LinkedList linkedList2 = UTraceManager.mPendingRecords;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList2, 10));
                Iterator it2 = linkedList2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((UTraceRecordV2) it2.next()).getCurrent());
                }
                sb.append(arrayList2);
                logs2.d("UTrace.Sdk.Manager", sb.toString());
                UTraceManager.mPendingRecords.clear();
                break;
        }
    }
}
