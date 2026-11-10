package com.oplus.utrace.sdk;

import android.text.TextUtils;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.transition.OplusAnimationController;
import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.lib.SdkConfigData;
import com.oplus.utrace.lib.SpanType;
import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.sdk.internal.SdkConfig;
import com.oplus.utrace.sdk.internal.UTraceManager;
import com.oplus.utrace.utils.Interval;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.TraceUtil;
import com.oplus.zoom.common.ZoomParameterHelper;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTrace {
    public static final UTrace INSTANCE = new UTrace();
    private static final AtomicInteger nodeSeq = new AtomicInteger(0);
    private static final Interval logInterval1 = new Interval();
    private static final Interval logInterval2 = new Interval();
    private static final ThreadLocal<UTraceContext> currentContext = new ThreadLocal<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CompletionType.values().length];
            iArr[CompletionType.COMPLETE.ordinal()] = 1;
            iArr[CompletionType.RETURN.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void addSpanTags(UTraceContext uTraceContext, Map<String, String> map) {
        Object obj;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d("UTrace.Sdk", "addSpanTags() myCtx=" + uTraceContext + " tags=" + map);
        }
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            traceLog(uTraceContextV2CheckCtx, 4, Intrinsics.stringPlus(map, "addSpanTags, tags: "));
            if (uTraceContextV2CheckCtx.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && TraceUtil.switchOn$utrace_sdk_liteRelease()) {
                UTraceManager.INSTANCE.addTrace(uTraceContextV2CheckCtx, 0L, System.currentTimeMillis(), UTraceRecordV2.Status.START, UTraceRecordV2.RecordType.SPAN_TAG, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : map);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "addSpanTags failure "), th2);
    }

    public static final void addTraceTags(UTraceContext uTraceContext, Map<String, String> map) {
        Object obj;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d("UTrace.Sdk", "addTraceTags() myCtx=" + uTraceContext + " tags=" + map);
        }
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            traceLog(uTraceContextV2CheckCtx, 4, Intrinsics.stringPlus(map, "addTraceTags, tags: "));
            if (uTraceContextV2CheckCtx.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && TraceUtil.switchOn$utrace_sdk_liteRelease()) {
                UTraceManager.INSTANCE.addTrace(uTraceContextV2CheckCtx, 0L, System.currentTimeMillis(), UTraceRecordV2.Status.START, UTraceRecordV2.RecordType.TRACE_TAG, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : map);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "addTraceTags failure "), th2);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    /* JADX WARN: Code duplicated, block: B:16:0x003a  */
    /* JADX WARN: Code duplicated, block: B:18:0x003e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0041  */
    public static UTraceContextV2 checkCtx(UTraceContext uTraceContext) {
        if (uTraceContext != null && !TextUtils.isEmpty(uTraceContext.getTraceID$utrace_sdk_liteRelease())) {
            if (uTraceContext.getParent$utrace_sdk_liteRelease() != null) {
                NodeID parent$utrace_sdk_liteRelease = uTraceContext.getParent$utrace_sdk_liteRelease();
                parent$utrace_sdk_liteRelease.getClass();
                if (NodeID.getSpanID$default(parent$utrace_sdk_liteRelease, false, 1, null).length() <= 20) {
                    if (NodeID.getSpanID$default(uTraceContext.getCurrent$utrace_sdk_liteRelease(), false, 1, null).length() <= 20) {
                        return uTraceContext instanceof UTraceContextV2 ? (UTraceContextV2) uTraceContext : UTraceContextV2.Companion.fromLegacyObj$utrace_sdk_liteRelease(uTraceContext);
                    }
                }
            } else if (NodeID.getSpanID$default(uTraceContext.getCurrent$utrace_sdk_liteRelease(), false, 1, null).length() <= 20) {
                if (uTraceContext instanceof UTraceContextV2) {
                }
            }
        }
        return null;
    }

    public static final void clearContext() {
        currentContext.remove();
    }

    public static final void end(UTraceContext uTraceContext, CompletionType completionType, boolean z) {
        Object obj;
        UTraceRecordV2.Status status;
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            traceLog(uTraceContextV2CheckCtx, 4, Intrinsics.stringPlus(completionType, "end, completionType: "));
            if (uTraceContextV2CheckCtx.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && TraceUtil.switchOn$utrace_sdk_liteRelease()) {
                UTraceManager uTraceManager = UTraceManager.INSTANCE;
                long jCurrentTimeMillis = System.currentTimeMillis();
                int i = WhenMappings.$EnumSwitchMapping$0[completionType.ordinal()];
                if (i != 1) {
                    status = i != 2 ? UTraceRecordV2.Status.END_GO_AHEAD : UTraceRecordV2.Status.END_RETURN;
                } else {
                    status = UTraceRecordV2.Status.END_COMPLETE;
                }
                uTraceManager.addTrace(uTraceContextV2CheckCtx, 0L, jCurrentTimeMillis, status, UTraceRecordV2.RecordType.END, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : null);
            }
            clearContext();
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "UTrace.end failure "), th2);
    }

    public static /* synthetic */ void end$default(UTraceContext uTraceContext, CompletionType completionType, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            completionType = CompletionType.GOAHEAD;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        end(uTraceContext, completionType, z);
    }

    @Deprecated
    public static final void error(UTraceContext uTraceContext, String str) {
        Object obj;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d("UTrace.Sdk", "error() myCtx=" + uTraceContext + " errorInfo=" + str);
            logs.w("UTrace.Sdk", "error(UTraceContext, String) 方法已废弃，请调用 error(UTraceContext, Int, String) 方法。", new IllegalAccessException("本方法已废弃"));
        }
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            errorImpl(uTraceContextV2CheckCtx, StringsKt__StringsKt.isBlank(str) ? -1 : -3, str);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "error() failure "), th2);
    }

    public static void errorImpl(UTraceContextV2 uTraceContextV2, int i, String str) {
        String strSubstring = str;
        traceLog(uTraceContextV2, 6, KeyguardLogger$.ExternalSyntheticOutline0.m("error, errorCode: ", i, ", errorInfo: ", strSubstring));
        if (uTraceContextV2.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && TraceUtil.switchOn$utrace_sdk_liteRelease()) {
            UTraceManager uTraceManager = UTraceManager.INSTANCE;
            long jCurrentTimeMillis = System.currentTimeMillis();
            UTraceRecordV2.Status status = UTraceRecordV2.Status.START;
            UTraceRecordV2.RecordType recordType = UTraceRecordV2.RecordType.ERROR;
            if (strSubstring.length() > 500) {
                strSubstring = strSubstring.substring(0, 500);
            }
            uTraceManager.addTrace(uTraceContextV2, 0L, jCurrentTimeMillis, status, recordType, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : i, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : strSubstring, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : UTraceRecordV2.StatusError.ERROR.getValue(), (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : null);
        }
    }

    public static NodeID generateNodeID(String str) {
        String strGenSpanId;
        if (str == null) {
            strGenSpanId = TraceUtil.genSpanId();
        } else {
            if (str.length() > 20) {
                str = str.substring(0, 20);
            }
            strGenSpanId = TraceUtil.genSpanId(str);
        }
        return new NodeID(strGenSpanId, nodeSeq.getAndIncrement() & Integer.MAX_VALUE);
    }

    public static final UTraceContext getContext() {
        return currentContext.get();
    }

    public static final void setContext(UTraceContext uTraceContext) {
        currentContext.set(uTraceContext);
    }

    public static final void setFlag(UTraceContext uTraceContext, int i, long j) {
        Object obj;
        String str = "setFlags() myCtx=" + uTraceContext + " flag=" + i + " value=" + j;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d("UTrace.Sdk", str);
        } else {
            logInterval2.exec(new setFlag.1.1(str));
        }
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            if (uTraceContextV2CheckCtx.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && TraceUtil.switchOn$utrace_sdk_liteRelease()) {
                UTraceManager.INSTANCE.addTrace(uTraceContextV2CheckCtx, 0L, System.currentTimeMillis(), UTraceRecordV2.Status.START, UTraceRecordV2.RecordType.TRACE_FLAG, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : MapsKt__MapsJVMKt.mapOf(TuplesKt.to(String.valueOf(i), String.valueOf(j))));
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "setFlag failure "), th2);
    }

    public static final UTraceContext start(UTraceContext uTraceContext, String str, String str2) {
        Object obj;
        INSTANCE.getClass();
        UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
        String traceID$utrace_sdk_liteRelease = uTraceContextV2CheckCtx == null ? null : uTraceContextV2CheckCtx.getTraceID$utrace_sdk_liteRelease();
        if (traceID$utrace_sdk_liteRelease == null) {
            traceID$utrace_sdk_liteRelease = TraceUtil.genTraceId();
        }
        NodeID nodeIDGenerateNodeID = generateNodeID(str);
        NodeID current$utrace_sdk_liteRelease = uTraceContextV2CheckCtx == null ? null : uTraceContextV2CheckCtx.getCurrent$utrace_sdk_liteRelease();
        Integer numValueOf = uTraceContextV2CheckCtx == null ? null : Integer.valueOf(uTraceContextV2CheckCtx.getSpanType$utrace_sdk_liteRelease());
        int value = numValueOf == null ? SpanType.CodeSpans.getValue() : numValueOf.intValue();
        Integer numValueOf2 = uTraceContextV2CheckCtx == null ? null : Integer.valueOf(uTraceContextV2CheckCtx.getValid$utrace_sdk_liteRelease());
        UTraceContextV2 uTraceContextV2 = new UTraceContextV2(traceID$utrace_sdk_liteRelease, nodeIDGenerateNodeID, current$utrace_sdk_liteRelease, value, numValueOf2 == null ? UTraceContextV2.Valid.INVALID.getValue() : numValueOf2.intValue(), uTraceContextV2CheckCtx != null ? uTraceContextV2CheckCtx.getTag() : null);
        setContext(uTraceContextV2);
        traceLog(uTraceContextV2, 4, Intrinsics.stringPlus(str2, "start, spanName: "));
        try {
            boolean zSwitchOn$utrace_sdk_liteRelease = TraceUtil.switchOn$utrace_sdk_liteRelease();
            StringBuilder sb = new StringBuilder("start() parentCtx=");
            sb.append(uTraceContextV2.getParent$utrace_sdk_liteRelease());
            sb.append(" myCtx=");
            sb.append(uTraceContextV2);
            sb.append(" UTraceApp.mEnabled=");
            sb.append(UTraceApp.INSTANCE.getMEnabled$utrace_sdk_liteRelease());
            sb.append(" switchOn=");
            sb.append(zSwitchOn$utrace_sdk_liteRelease);
            sb.append(" SdkConfig.isEnabled=");
            SdkConfig sdkConfig = SdkConfig.INSTANCE;
            sdkConfig.getClass();
            sb.append(SdkConfig.isEnabled());
            sb.append(" SdkConfig.coreAvailable=");
            sb.append(sdkConfig.getCoreAvailable());
            final String string = sb.toString();
            Logs logs = Logs.INSTANCE;
            if (logs.getDebuggable()) {
                logs.d("UTrace.Sdk", string);
            } else {
                logInterval1.exec(new Function0() { // from class: com.oplus.utrace.sdk.UTrace$start$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    public final Object invoke() {
                        Logs.INSTANCE.i("UTrace.Sdk", string);
                        return Unit.INSTANCE;
                    }
                });
            }
            if (uTraceContextV2.getValid$utrace_sdk_liteRelease() == UTraceContextV2.Valid.VALID.getValue() && zSwitchOn$utrace_sdk_liteRelease) {
                try {
                    UTraceManager.INSTANCE.addTrace(uTraceContextV2, System.currentTimeMillis(), 0L, UTraceRecordV2.Status.START, UTraceRecordV2.RecordType.START, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : str2, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : null);
                    uTraceContextV2 = uTraceContextV2;
                } catch (Throwable th) {
                    th = th;
                    uTraceContextV2 = uTraceContextV2;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable th3 = Result.exceptionOrNull-impl(obj);
        if (th3 != null) {
            Logs.INSTANCE.e("UTrace.Sdk", th3.getMessage(), th3);
        }
        return uTraceContextV2;
    }

    public static /* synthetic */ UTraceContext start$default(UTraceContext uTraceContext, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return start(uTraceContext, str, str2);
    }

    public static final UTraceContext startHead(String str, String str2, SpanType spanType) {
        Object obj;
        String strGenTraceId = TraceUtil.genTraceId();
        INSTANCE.getClass();
        final UTraceContextV2 uTraceContextV2 = new UTraceContextV2(strGenTraceId, generateNodeID(str), null, spanType.getValue(), 0, null, 48, null);
        setContext(uTraceContextV2);
        traceLog(uTraceContextV2, 4, "startHead, spanName: " + ((Object) str2) + ", spanType: " + spanType);
        try {
            final boolean zSwitchOn$utrace_sdk_liteRelease = TraceUtil.switchOn$utrace_sdk_liteRelease();
            Logs logs = Logs.INSTANCE;
            if (logs.getDebuggable()) {
                logs.d("UTrace.Sdk", "startHead() myCtx=" + uTraceContextV2 + " UTraceApp.mEnabled=" + UTraceApp.INSTANCE.getMEnabled$utrace_sdk_liteRelease() + " switchOn=" + zSwitchOn$utrace_sdk_liteRelease);
            } else {
                logInterval1.exec(new Function0() { // from class: com.oplus.utrace.sdk.UTrace$startHead$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    public final Object invoke() {
                        Logs logs2 = Logs.INSTANCE;
                        StringBuilder sb = new StringBuilder("startHead() myCtx=");
                        sb.append(uTraceContextV2);
                        sb.append(" UTraceApp.mEnabled=");
                        sb.append(UTraceApp.INSTANCE.getMEnabled$utrace_sdk_liteRelease());
                        sb.append(" switchOn=");
                        sb.append(zSwitchOn$utrace_sdk_liteRelease);
                        sb.append(" SdkConfig.isEnabled=");
                        SdkConfig sdkConfig = SdkConfig.INSTANCE;
                        sdkConfig.getClass();
                        sb.append(SdkConfig.isEnabled());
                        sb.append(" SdkConfig.coreAvailable=");
                        sb.append(sdkConfig.getCoreAvailable());
                        logs2.i("UTrace.Sdk", sb.toString());
                        return Unit.INSTANCE;
                    }
                });
            }
            if (zSwitchOn$utrace_sdk_liteRelease) {
                try {
                    UTraceManager.INSTANCE.addTrace(uTraceContextV2, System.currentTimeMillis(), 0L, UTraceRecordV2.Status.START, UTraceRecordV2.RecordType.START, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 32) != 0 ? 0 : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 64) != 0 ? "" : null, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 128) != 0 ? UTraceRecordV2.StatusError.NO_ERROR.getValue() : 0, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & 256) != 0 ? null : str2, (ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? MapsKt__MapsKt.emptyMap() : null);
                    uTraceContextV2 = uTraceContextV2;
                } catch (Throwable th) {
                    th = th;
                    uTraceContextV2 = uTraceContextV2;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable th3 = Result.exceptionOrNull-impl(obj);
        if (th3 != null) {
            Logs.INSTANCE.e("UTrace.Sdk", th3.getMessage(), th3);
        }
        return uTraceContextV2;
    }

    public static /* synthetic */ UTraceContext startHead$default(String str, String str2, SpanType spanType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            spanType = SpanType.CodeSpans;
        }
        return startHead(str, str2, spanType);
    }

    public static void traceLog(UTraceContextV2 uTraceContextV2, int i, String str) {
        ULog.INSTANCE.getTempCtx$utrace_sdk_liteRelease().set(uTraceContextV2);
        int spanType$utrace_sdk_liteRelease = uTraceContextV2.getSpanType$utrace_sdk_liteRelease();
        SdkConfigData.TraceLogCtrl traceLogCtrl = SdkConfig.INSTANCE.getTraceLogCtrl();
        if (!(spanType$utrace_sdk_liteRelease == SpanType.CodeSpans.getValue() ? traceLogCtrl.getCodeUseInfo() : traceLogCtrl.getIntentUseInfo())) {
            i = 3;
        }
        ULog.println(i, "UTrace.Sdk", str);
    }

    public static final void error(UTraceContext uTraceContext, int i, String str) {
        Object obj;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d("UTrace.Sdk", "error() myCtx=" + uTraceContext + " errorCode=" + i + " errorInfo=" + str);
        }
        try {
            INSTANCE.getClass();
            UTraceContextV2 uTraceContextV2CheckCtx = checkCtx(uTraceContext);
            if (uTraceContextV2CheckCtx == null) {
                return;
            }
            errorImpl(uTraceContextV2CheckCtx, i, str);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        Logs.INSTANCE.e("UTrace.Sdk", Intrinsics.stringPlus(th2.getMessage(), "UTrace.error failure "), th2);
    }

    public static final void end(UTraceContext uTraceContext, CompletionType completionType) {
        end$default(uTraceContext, completionType, false, 4, null);
    }

    public static final void end(UTraceContext uTraceContext) {
        end$default(uTraceContext, null, false, 6, null);
    }

    public static final UTraceContext startHead(String str) {
        return startHead$default(str, null, null, 6, null);
    }

    public static final UTraceContext startHead(String str, String str2) {
        return startHead$default(str, str2, null, 4, null);
    }

    public static final UTraceContext startHead() {
        return startHead$default(null, null, null, 7, null);
    }

    public static final UTraceContext start(UTraceContext uTraceContext, String str) {
        return start$default(uTraceContext, str, null, 4, null);
    }

    public static final UTraceContext start(UTraceContext uTraceContext) {
        return start$default(uTraceContext, null, null, 6, null);
    }
}
