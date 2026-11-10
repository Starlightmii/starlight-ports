package com.oplus.utrace.sdk.internal;

import android.content.Context;
import com.oplus.utrace.lib.IUTraceInterface;
import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UTraceManager$mConnection$1$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ IUTraceInterface f$0;

    @Override // java.lang.Runnable
    public final void run() {
        UTraceManager.mAgentProxy = this.f$0;
        UTraceManager.mIsBinding = false;
        UTraceManager.mAgentLevel = Integer.MAX_VALUE;
        UTraceManager.INSTANCE.resetUnbindMessage();
        UTraceManager.excpCount = 0;
        UTraceManager.excpTS = 0L;
        Context context = UTraceApp.mContext;
        if (context != null) {
            SharedPreferencesUtil.remove(context, "exception_timestamp");
        }
        UTraceManager.AddTraceResult addTraceResultTryProxyAddTrace2 = UTraceManager.tryProxyAddTrace2();
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            StringBuilder sb = new StringBuilder("onServiceConnected() tryProxyAddTrace2 returns ");
            sb.append(addTraceResultTryProxyAddTrace2);
            sb.append(", clear mPendingRecords=");
            LinkedList linkedList = UTraceManager.mPendingRecords;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                arrayList.add(((UTraceRecordV2) it.next()).getCurrent());
            }
            sb.append(arrayList);
            logs.d("UTrace.Sdk.Manager", sb.toString());
        }
        UTraceManager.mPendingRecords.clear();
        UTraceManager.INSTANCE.getMHandler().removeMessages(2);
    }
}
