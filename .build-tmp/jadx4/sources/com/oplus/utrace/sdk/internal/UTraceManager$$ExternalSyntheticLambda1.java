package com.oplus.utrace.sdk.internal;

import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.sdk.UTraceContextV2;
import com.oplus.utrace.utils.Logs;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UTraceManager$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ UTraceContextV2 f$0;
    public /* synthetic */ UTraceRecordV2.Status f$1;
    public /* synthetic */ String f$2;
    public /* synthetic */ long f$3;
    public /* synthetic */ long f$4;
    public /* synthetic */ String f$5;
    public /* synthetic */ int f$6;
    public /* synthetic */ int f$7;
    public /* synthetic */ UTraceRecordV2.RecordType f$8;
    public /* synthetic */ Map f$9;

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        UTraceContextV2 uTraceContextV2 = this.f$0;
        UTraceRecordV2.Status status = this.f$1;
        String str = this.f$2;
        long j = this.f$3;
        long j2 = this.f$4;
        String str2 = this.f$5;
        int i = this.f$6;
        int i2 = this.f$7;
        UTraceRecordV2.RecordType recordType = this.f$8;
        Map map = this.f$9;
        UTraceManager uTraceManager = UTraceManager.INSTANCE;
        try {
            String traceID$utrace_sdk_liteRelease = uTraceContextV2.getTraceID$utrace_sdk_liteRelease();
            NodeID current$utrace_sdk_liteRelease = uTraceContextV2.getCurrent$utrace_sdk_liteRelease();
            NodeID parent$utrace_sdk_liteRelease = uTraceContextV2.getParent$utrace_sdk_liteRelease();
            uTraceManager.getClass();
            uTraceManager.addTraceInThread(new UTraceRecordV2(traceID$utrace_sdk_liteRelease, current$utrace_sdk_liteRelease, parent$utrace_sdk_liteRelease, UTraceManager.genSpanName(uTraceContextV2, status, str), j, j2, status.getValue(), str2, i, i2, recordType.getValue(), uTraceContextV2.getSpanType$utrace_sdk_liteRelease(), map));
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk.Manager", Intrinsics.stringPlus(th2.getMessage(), "addTrace error: "), th2);
    }
}
