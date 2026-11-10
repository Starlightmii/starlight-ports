package com.oplus.utrace.sdk.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import androidx.car.app.model.TimerSpan$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.core.view.ViewKt$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.oplus.utrace.lib.IUTraceInterface;
import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.lib.PackageNames;
import com.oplus.utrace.lib.SdkConfigData;
import com.oplus.utrace.lib.UTraceRecordV2;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.sdk.UTraceContextV2;
import com.oplus.utrace.utils.Interval;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.SharedPreferencesUtil;
import com.oplus.utrace.utils.TraceUtil;
import com.oplus.utrace.utils.UtilsKt;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceManager {
    private static final String KEY_EXP_TS = "exception_timestamp";
    private static final String TAG = "UTrace.Sdk.Manager";
    private static String bindInfo = "";
    public static int excpCount = 0;
    public static long excpTS = -1;
    public static int mAgentLevel = Integer.MAX_VALUE;
    private static volatile IUTraceInterface mAgentProxy;
    public static volatile boolean mIsBinding;
    public static final UTraceManager INSTANCE = new UTraceManager();
    private static final LinkedList<UTraceRecordV2> mPendingRecords = new LinkedList<>();
    private static final ConcurrentHashMap<NodeID, String> mSpanNamesL1 = new ConcurrentHashMap<>();
    private static final LruCache<NodeID, String> mSpanNamesL2 = new LruCache<>(32);
    private static final Object mSpanNamesLock = new Object();
    private static final Overflow overflow = new Overflow();
    public static final ExecutorService mExecutor = Executors.newSingleThreadExecutor(new UTraceManager$$ExternalSyntheticLambda0());
    private static final Interval logInterval1 = new Interval();
    private static final Interval logInterval2 = new Interval();
    private static final Lazy mHandler$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.sdk.internal.UTraceManager$mHandler$2

        /* JADX INFO: renamed from: com.oplus.utrace.sdk.internal.UTraceManager$mHandler$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 extends Handler {
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                UTraceManager.INSTANCE.getClass();
                ExecutorService executorService = UTraceManager.mExecutor;
                if (executorService.isShutdown()) {
                    Logs.INSTANCE.i("UTrace.Sdk.Manager", Intrinsics.stringPlus(message, "handleMessage() isShutdown=true ignore msg="));
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    Logs.INSTANCE.d("UTrace.Sdk.Manager", "handleMessage: 触发了MSG_UNBIND消息");
                    UtilsKt.submitSafe(executorService, new UTraceManager$$ExternalSyntheticLambda2(3));
                } else {
                    if (i != 2) {
                        return;
                    }
                    UtilsKt.submitSafe(executorService, new UTraceManager$$ExternalSyntheticLambda2(4));
                }
            }
        }

        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final AnonymousClass1 m511invoke() {
            return new AnonymousClass1(TraceUtil.INSTANCE.getCommonLooper());
        }
    });
    private static ServiceConnection mConnection = new UTraceManager$mConnection$1();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AddTraceResult {
        public static final /* synthetic */ AddTraceResult[] $VALUES;
        public static final AddTraceResult NEED_REBIND;
        public static final AddTraceResult NO_CACHE;
        public static final AddTraceResult OK;
        public static final AddTraceResult REJECT;
        public static final AddTraceResult TIMEOUT;

        static {
            AddTraceResult addTraceResult = new AddTraceResult("OK", 0);
            OK = addTraceResult;
            AddTraceResult addTraceResult2 = new AddTraceResult("REJECT", 1);
            REJECT = addTraceResult2;
            AddTraceResult addTraceResult3 = new AddTraceResult("TIMEOUT", 2);
            TIMEOUT = addTraceResult3;
            AddTraceResult addTraceResult4 = new AddTraceResult("NEED_REBIND", 3);
            NEED_REBIND = addTraceResult4;
            AddTraceResult addTraceResult5 = new AddTraceResult("NO_CACHE", 4);
            NO_CACHE = addTraceResult5;
            $VALUES = new AddTraceResult[]{addTraceResult, addTraceResult2, addTraceResult3, addTraceResult4, addTraceResult5};
        }

        public static AddTraceResult valueOf(String str) {
            return (AddTraceResult) Enum.valueOf(AddTraceResult.class, str);
        }

        public static AddTraceResult[] values() {
            return (AddTraceResult[]) $VALUES.clone();
        }
    }

    public static String genSpanName(UTraceContextV2 uTraceContextV2, UTraceRecordV2.Status status, String str) {
        String str2;
        NodeID current$utrace_sdk_liteRelease = uTraceContextV2.getCurrent$utrace_sdk_liteRelease();
        if (str == null || StringsKt__StringsKt.isBlank(str)) {
            synchronized (mSpanNamesLock) {
                str2 = mSpanNamesL1.get(current$utrace_sdk_liteRelease);
                if (str2 == null) {
                    str2 = mSpanNamesL2.get(current$utrace_sdk_liteRelease);
                }
            }
        } else {
            str2 = str;
        }
        String str3 = UTraceApp.mPkgName;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            str3 = str3 + '(' + ((Object) str2) + ')';
        }
        synchronized (mSpanNamesLock) {
            try {
                if (status == UTraceRecordV2.Status.START) {
                    if (str == null || StringsKt__StringsKt.isBlank(str)) {
                        str = null;
                    }
                    if (str != null) {
                        mSpanNamesL1.put(current$utrace_sdk_liteRelease, str);
                    }
                } else {
                    String strRemove = mSpanNamesL1.remove(current$utrace_sdk_liteRelease);
                    if (strRemove != null) {
                        mSpanNamesL2.put(current$utrace_sdk_liteRelease, strRemove);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UTraceManager$mHandler$2.AnonymousClass1 getMHandler() {
        return (UTraceManager$mHandler$2.AnonymousClass1) mHandler$delegate.getValue();
    }

    private static /* synthetic */ void getMSpanNamesL2$annotations() {
    }

    public static boolean setExcpTimestamp() {
        int i = excpCount;
        if (i < 3) {
            excpCount = i + 1;
        }
        Logs logs = Logs.INSTANCE;
        logs.i(TAG, Intrinsics.stringPlus(Integer.valueOf(excpCount), "setExcpTimestamp() excpCount="));
        if (excpCount < 3) {
            return false;
        }
        excpTS = System.currentTimeMillis();
        Context context = UTraceApp.mContext;
        if (context == null) {
            return true;
        }
        UTraceManager uTraceManager = INSTANCE;
        logs.i(TAG, Intrinsics.stringPlus(Long.valueOf(uTraceManager.getExcpTS()), "setExcpTimestamp() excpTS="));
        SharedPreferencesUtil.putLong(context, KEY_EXP_TS, uTraceManager.getExcpTS());
        return true;
    }

    public static AddTraceResult tryProxyAddTrace2() {
        Object obj;
        Object obj2;
        boolean zCheckInternal = logInterval2.checkInternal();
        if (mAgentProxy == null) {
            if (zCheckInternal) {
                Logs.INSTANCE.i(TAG, "tryProxyAddTrace2 proxy is null. return NEED_REBIND");
            } else {
                Logs.INSTANCE.d(TAG, "tryProxyAddTrace2 proxy is null. return NEED_REBIND");
            }
            return AddTraceResult.NEED_REBIND;
        }
        IUTraceInterface iUTraceInterface = mAgentProxy;
        if (iUTraceInterface == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type com.oplus.utrace.lib.IUTraceInterface");
            return null;
        }
        LinkedList<UTraceRecordV2> linkedList = mPendingRecords;
        if (linkedList.isEmpty()) {
            if (zCheckInternal) {
                Logs.INSTANCE.i(TAG, "tryProxyAddTrace2 mPendingRecords is empty, return NO_CACHE");
            } else {
                Logs.INSTANCE.d(TAG, "tryProxyAddTrace2 mPendingRecords is empty, return NO_CACHE");
            }
            return AddTraceResult.NO_CACHE;
        }
        if (mAgentLevel >= 3) {
            try {
                Logs logs = Logs.INSTANCE;
                if (logs.getDebuggable()) {
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                    Iterator<T> it = linkedList.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((UTraceRecordV2) it.next()).getCurrent());
                    }
                    logs.d(TAG, Intrinsics.stringPlus(arrayList, "tryProxyAddTrace2 addTraces records="));
                }
                LinkedList<UTraceRecordV2> linkedList2 = mPendingRecords;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList2, 10));
                Iterator<T> it2 = linkedList2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((UTraceRecordV2) it2.next()).toJsonString());
                }
                int iAddTraces = iUTraceInterface.addTraces(arrayList2);
                String strStringPlus = Intrinsics.stringPlus(Integer.valueOf(iAddTraces), "tryProxyAddTrace2 addTraces returns ");
                if (zCheckInternal) {
                    Logs.INSTANCE.i(TAG, strStringPlus);
                } else {
                    Logs.INSTANCE.d(TAG, strStringPlus);
                }
                if (iAddTraces != 0) {
                    return AddTraceResult.OK;
                }
                obj = Result.constructor-impl(Unit.INSTANCE);
                Throwable th = Result.exceptionOrNull-impl(obj);
                if (th != null) {
                    Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th.getMessage(), "tryProxyAddTrace, addTraces: "));
                    if (th instanceof SecurityException) {
                        return AddTraceResult.REJECT;
                    }
                }
                mAgentLevel = 2;
            } catch (Throwable th2) {
                obj = Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        for (UTraceRecordV2 uTraceRecordV2 : mPendingRecords) {
            try {
                if (mAgentLevel == 2) {
                    Logs logs2 = Logs.INSTANCE;
                    logs2.d(TAG, Intrinsics.stringPlus(uTraceRecordV2.getCurrent(), "tryProxyAddTrace2 addTrace2 record="));
                    int iAddTrace2 = iUTraceInterface.addTrace2(uTraceRecordV2.toJsonString());
                    String strStringPlus2 = Intrinsics.stringPlus(Integer.valueOf(iAddTrace2), "tryProxyAddTrace2 addTrace2 returns ");
                    if (zCheckInternal) {
                        logs2.i(TAG, strStringPlus2);
                    } else {
                        logs2.d(TAG, strStringPlus2);
                    }
                    if (iAddTrace2 == 0) {
                        mAgentLevel = 1;
                    }
                }
                if (mAgentLevel == 1) {
                    if (uTraceRecordV2.getType() != UTraceRecordV2.RecordType.START.getValue() && uTraceRecordV2.getType() != UTraceRecordV2.RecordType.ERROR.getValue() && uTraceRecordV2.getType() != UTraceRecordV2.RecordType.END.getValue()) {
                        uTraceRecordV2 = null;
                    }
                    if (uTraceRecordV2 != null) {
                        String strStringPlus3 = Intrinsics.stringPlus(uTraceRecordV2.getCurrent(), "tryProxyAddTrace2 addTrace record=");
                        if (zCheckInternal) {
                            Logs.INSTANCE.i(TAG, strStringPlus3);
                        } else {
                            Logs.INSTANCE.d(TAG, strStringPlus3);
                        }
                        iUTraceInterface.addTrace(uTraceRecordV2.toLegacyObj());
                    }
                }
                obj2 = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th3) {
                obj2 = Result.constructor-impl(ResultKt.createFailure(th3));
            }
            Throwable th4 = Result.exceptionOrNull-impl(obj2);
            if (th4 != null) {
                Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th4.getMessage(), "tryProxyAddTrace, addTraces: "));
                return th4 instanceof SecurityException ? AddTraceResult.REJECT : AddTraceResult.NEED_REBIND;
            }
        }
        return AddTraceResult.OK;
    }

    public final void addTrace(UTraceContextV2 uTraceContextV2, long j, long j2, UTraceRecordV2.Status status, UTraceRecordV2.RecordType recordType, int i, String str, int i2, String str2, Map<String, String> map) {
        Logs logs = Logs.INSTANCE;
        StringBuilder sb = new StringBuilder("addTrace() myCtx=");
        sb.append(uTraceContextV2);
        sb.append(" status=");
        sb.append(status);
        sb.append(" hasError=");
        sb.append(i2);
        sb.append(" recordType=");
        sb.append(recordType);
        sb.append(" info=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(i, str, " statusCode=", " startTime=", sb);
        sb.append(j);
        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sb, " endTime=", j2, " spanName=");
        sb.append((Object) str2);
        logs.d(TAG, sb.toString());
        ExecutorService executorService = mExecutor;
        UTraceManager$$ExternalSyntheticLambda1 uTraceManager$$ExternalSyntheticLambda1 = new UTraceManager$$ExternalSyntheticLambda1();
        uTraceManager$$ExternalSyntheticLambda1.f$0 = uTraceContextV2;
        uTraceManager$$ExternalSyntheticLambda1.f$1 = status;
        uTraceManager$$ExternalSyntheticLambda1.f$2 = str2;
        uTraceManager$$ExternalSyntheticLambda1.f$3 = j;
        uTraceManager$$ExternalSyntheticLambda1.f$4 = j2;
        uTraceManager$$ExternalSyntheticLambda1.f$5 = str;
        uTraceManager$$ExternalSyntheticLambda1.f$6 = i2;
        uTraceManager$$ExternalSyntheticLambda1.f$7 = i;
        uTraceManager$$ExternalSyntheticLambda1.f$8 = recordType;
        uTraceManager$$ExternalSyntheticLambda1.f$9 = map;
        VarHandle.storeStoreFence();
        UtilsKt.submitSafe(executorService, uTraceManager$$ExternalSyntheticLambda1);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:31:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:53:0x017b  */
    /* JADX WARN: Code duplicated, block: B:56:0x019f A[LOOP:1: B:54:0x0199->B:56:0x019f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:58:0x01b5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:59:0x01b7  */
    public final void addTraceInThread(UTraceRecordV2 uTraceRecordV2) {
        boolean zCheckInternal;
        long j;
        long jCurrentTimeMillis;
        long j2;
        long j3;
        long j4;
        ArrayList arrayList;
        Iterator<T> it;
        Unit unit;
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d(TAG, "addTraceInThread() proxy=" + mAgentProxy + " isBinding=" + mIsBinding + " record=" + uTraceRecordV2);
            zCheckInternal = false;
        } else {
            zCheckInternal = logInterval1.checkInternal();
        }
        if (zCheckInternal) {
            logs.i(TAG, "addTraceInThread() proxy=" + mAgentProxy + " isBinding=" + mIsBinding + " bindInfo=" + bindInfo + " record=" + uTraceRecordV2 + " mPendingRecords.size=" + mPendingRecords.size() + " mAgentLevel=" + mAgentLevel);
        }
        if (overflow.checkIfOverflow(uTraceRecordV2)) {
            return;
        }
        SdkConfig sdkConfig = SdkConfig.INSTANCE;
        SdkConfigData.TraceCacheMetrics traceCacheMetrics = sdkConfig.getTraceCacheMetrics();
        LinkedList<UTraceRecordV2> linkedList = mPendingRecords;
        linkedList.add(uTraceRecordV2);
        if (linkedList.size() > traceCacheMetrics.getMaxPendingRecordsL1()) {
            linkedList.removeFirst();
        }
        if (mAgentProxy != null) {
            resetUnbindMessage();
            logs.d(TAG, "addTraceInThread() mPendingRecords.size=" + linkedList.size() + " mAgentLevel=" + mAgentLevel);
            if (linkedList.size() < traceCacheMetrics.getMaxPendingRecordsL2() && mAgentLevel >= 3) {
                if (linkedList.size() == 1) {
                    if (mExecutor.isShutdown()) {
                        logs.i(TAG, "addTraceInThread() isShutdown=true #1");
                        return;
                    } else {
                        getMHandler().sendEmptyMessageDelayed(2, traceCacheMetrics.getDelayAddTraceList());
                        return;
                    }
                }
                return;
            }
            logs.d(TAG, "addTraceInThread() tryProxyAddTrace2 invoke");
            AddTraceResult addTraceResultTryProxyAddTrace2 = tryProxyAddTrace2();
            logs.d(TAG, Intrinsics.stringPlus(addTraceResultTryProxyAddTrace2, "addTraceInThread tryProxyAddTrace2 returns "));
            getMHandler().removeMessages(2);
            if (addTraceResultTryProxyAddTrace2 != AddTraceResult.NEED_REBIND) {
                if (logs.getDebuggable()) {
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                    Iterator<T> it2 = linkedList.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((UTraceRecordV2) it2.next()).getCurrent());
                    }
                    logs.d(TAG, Intrinsics.stringPlus(arrayList2, "addTraceInThread() clear mPendingRecords="));
                }
                mPendingRecords.clear();
                return;
            }
            unBindService();
            if (bindService()) {
                return;
            }
            if (logs.getDebuggable()) {
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                Iterator<T> it3 = linkedList.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(((UTraceRecordV2) it3.next()).getCurrent());
                }
                logs.d(TAG, Intrinsics.stringPlus(arrayList3, "addTraceInThread() clear mPendingRecords="));
            }
            mPendingRecords.clear();
            return;
        }
        if (mIsBinding) {
            return;
        }
        if (excpTS != -1) {
            j = excpTS;
            if (j > 0) {
                logs.d(TAG, Intrinsics.stringPlus(Long.valueOf(j), "insideExpTimeWindow return false, excpTS="));
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
                long bindWindow = sdkConfig.getBindWindow();
                j2 = jCurrentTimeMillis / bindWindow;
                j3 = excpTS;
                j4 = j3 / bindWindow;
                if (jCurrentTimeMillis >= j3 || j2 == j4) {
                    StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(jCurrentTimeMillis, "insideExpTimeWindow return true. nowTime:", ",excpTS:");
                    sbM.append(excpTS);
                    DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ",nowTimeEXP:", j2, ",expTimeEXP:");
                    sbM.append(j4);
                    String string = sbM.toString();
                    logs.d(TAG, string);
                    bindInfo = string;
                    if (logs.getDebuggable()) {
                        logs.d(TAG, Intrinsics.stringPlus(UtilsKt.getProcessName(UTraceApp.mContext), "addTraceInThread() insideExpTimeWindow returns true. proc="));
                        arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                        it = linkedList.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((UTraceRecordV2) it.next()).getCurrent());
                        }
                        logs.d(TAG, Intrinsics.stringPlus(arrayList, "addTraceInThread() clear mPendingRecords="));
                    } else if (zCheckInternal) {
                        logs.i(TAG, Intrinsics.stringPlus(UtilsKt.getProcessName(UTraceApp.mContext), "addTraceInThread() insideExpTimeWindow=true. proc="));
                    }
                    mPendingRecords.clear();
                    return;
                }
                excpCount = 0;
                excpTS = 0L;
                Context context = UTraceApp.mContext;
                if (context != null) {
                    SharedPreferencesUtil.remove(context, KEY_EXP_TS);
                }
            }
        } else {
            Context context2 = UTraceApp.mContext;
            if (context2 == null) {
                unit = null;
            } else {
                INSTANCE.setExcpTS(SharedPreferencesUtil.getLong(context2, KEY_EXP_TS));
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                logs.w(TAG, Intrinsics.stringPlus(Long.valueOf(getExcpTS()), "insideExpTimeWindow return false, context=null excpTS="));
            } else {
                j = excpTS;
                if (j > 0) {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    long bindWindow2 = sdkConfig.getBindWindow();
                    j2 = jCurrentTimeMillis / bindWindow2;
                    j3 = excpTS;
                    j4 = j3 / bindWindow2;
                    if (jCurrentTimeMillis >= j3) {
                    }
                    StringBuilder sbM2 = TimerSpan$.ExternalSyntheticOutline0.m(jCurrentTimeMillis, "insideExpTimeWindow return true. nowTime:", ",excpTS:");
                    sbM2.append(excpTS);
                    DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM2, ",nowTimeEXP:", j2, ",expTimeEXP:");
                    sbM2.append(j4);
                    String string2 = sbM2.toString();
                    logs.d(TAG, string2);
                    bindInfo = string2;
                    if (logs.getDebuggable()) {
                        logs.d(TAG, Intrinsics.stringPlus(UtilsKt.getProcessName(UTraceApp.mContext), "addTraceInThread() insideExpTimeWindow returns true. proc="));
                        arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
                        it = linkedList.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((UTraceRecordV2) it.next()).getCurrent());
                        }
                        logs.d(TAG, Intrinsics.stringPlus(arrayList, "addTraceInThread() clear mPendingRecords="));
                    } else if (zCheckInternal) {
                        logs.i(TAG, Intrinsics.stringPlus(UtilsKt.getProcessName(UTraceApp.mContext), "addTraceInThread() insideExpTimeWindow=true. proc="));
                    }
                    mPendingRecords.clear();
                    return;
                }
                logs.d(TAG, Intrinsics.stringPlus(Long.valueOf(j), "insideExpTimeWindow return false, excpTS="));
            }
        }
        if (bindService()) {
            return;
        }
        if (logs.getDebuggable()) {
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedList, 10));
            Iterator<T> it4 = linkedList.iterator();
            while (it4.hasNext()) {
                arrayList4.add(((UTraceRecordV2) it4.next()).getCurrent());
            }
            logs.d(TAG, Intrinsics.stringPlus(arrayList4, "addTraceInThread() clear mPendingRecords="));
        }
        mPendingRecords.clear();
    }

    public final boolean bindService() {
        Object obj;
        boolean z;
        Object obj2;
        if (mExecutor.isShutdown()) {
            Logs.INSTANCE.i(TAG, "bindService() isShutdown=true");
            return false;
        }
        String[] core_apps = PackageNames.INSTANCE.getCORE_APPS();
        ArrayList arrayList = new ArrayList(core_apps.length);
        int length = core_apps.length;
        int i = 0;
        while (i < length) {
            String str = core_apps[i];
            i++;
            arrayList.add(new Intent().setComponent(new ComponentName(str, "com.oplus.utrace.core.UTraceService")));
        }
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            obj = null;
            if (i2 >= size) {
                break;
            }
            Object obj3 = arrayList.get(i2);
            i2++;
            Intent intent = (Intent) obj3;
            Logs.INSTANCE.d(TAG, Intrinsics.stringPlus(intent, "当前尝试解析的intent为 "));
            INSTANCE.getClass();
            Context context = UTraceApp.mContext;
            PackageManager packageManager = context == null ? null : context.getPackageManager();
            ResolveInfo resolveInfoResolveService = packageManager == null ? null : packageManager.resolveService(intent, 4);
            if (resolveInfoResolveService != null) {
                obj = resolveInfoResolveService.serviceInfo;
            }
            if (obj != null) {
                obj = obj3;
                break;
            }
        }
        Intent intent2 = (Intent) obj;
        bindInfo = Intrinsics.stringPlus(intent2, "binding:");
        if (intent2 == null) {
            Logs.INSTANCE.i(TAG, "当前没有与之匹配的intent，开始记录当前时间戳");
            return !setExcpTimestamp();
        }
        Logs.INSTANCE.i(TAG, "当前满足条件的intent为 " + intent2 + " (version=1.4.18-f23f716-20240927-165914) context=" + UTraceApp.mContext);
        try {
            Context context2 = UTraceApp.mContext;
            if (context2 != null) {
                mIsBinding = true;
                resetUnbindMessage();
                context2.bindService(intent2, mConnection, 1);
            }
            try {
                obj2 = Result.constructor-impl(Unit.INSTANCE);
                z = true;
            } catch (Throwable th) {
                th = th;
                z = true;
                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        Throwable th3 = Result.exceptionOrNull-impl(obj2);
        if (th3 == null) {
            return z;
        }
        mIsBinding = false;
        INSTANCE.getMHandler().removeMessages(1);
        Logs.INSTANCE.e(TAG, Intrinsics.stringPlus(th3.getMessage(), "bindService() exception="), th3);
        boolean z2 = !setExcpTimestamp();
        bindInfo = "failure:" + z2 + '|' + th3;
        return z2;
    }

    public final void crossDevice$utrace_sdk_liteRelease(String str) {
    }

    public final int getExcpCount() {
        return excpCount;
    }

    public final long getExcpTS() {
        return excpTS;
    }

    public final void resetUnbindMessage() {
        getMHandler().removeMessages(1);
        if (mExecutor.isShutdown()) {
            Logs.INSTANCE.i(TAG, "resetUnbindMessage() isShutdown=true");
        } else {
            getMHandler().sendEmptyMessageDelayed(1, 10000L);
        }
        Logs.INSTANCE.d(TAG, "resetUnbindMessage:Reset the MSG_UNBIND message");
    }

    public final void setExcpCount(int i) {
        excpCount = i;
    }

    public final void setExcpTS(long j) {
        excpTS = j;
    }

    public final void unBindService() {
        Object obj;
        Logs.INSTANCE.d(TAG, "unBindService()");
        bindInfo = "unbinding";
        boolean z = true;
        getMHandler().removeMessages(1);
        if (!mIsBinding && mAgentProxy == null) {
            z = false;
        }
        Unit unit = null;
        mAgentProxy = null;
        mIsBinding = false;
        if (z) {
            try {
                Context context = UTraceApp.mContext;
                if (context != null) {
                    context.unbindService(mConnection);
                    unit = Unit.INSTANCE;
                }
                obj = Result.constructor-impl(unit);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 == null) {
                return;
            }
            Logs.INSTANCE.e(TAG, "unBindService() error: " + ((Object) th2.getMessage()) + '.');
        }
    }

    public final void uninit$utrace_sdk_liteRelease() {
        Logs.INSTANCE.i(TAG, "uninit()");
        getMHandler().removeMessages(1);
        getMHandler().removeMessages(2);
        ExecutorService executorService = mExecutor;
        UtilsKt.submitSafe(executorService, new UTraceManager$$ExternalSyntheticLambda2(0));
        executorService.shutdown();
    }
}
