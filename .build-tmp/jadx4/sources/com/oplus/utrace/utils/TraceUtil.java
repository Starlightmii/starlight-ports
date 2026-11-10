package com.oplus.utrace.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.Base64;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.sdk.internal.SdkConfig;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TraceUtil {
    public static final TraceUtil INSTANCE = new TraceUtil();
    private static final AtomicInteger entryCounter = new AtomicInteger((int) (Math.random() * 32767.0d));
    private static final Lazy pid$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.utils.TraceUtil$pid$2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Integer m515invoke() {
            return Integer.valueOf(Process.myPid());
        }
    });
    private static final Lazy defaultSpanId$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.utils.TraceUtil$defaultSpanId$2
        public final String invoke() {
            return TraceUtil.access$generateSpanId();
        }
    });
    private static final Lazy commonExecutor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.utils.TraceUtil$commonExecutor$2
        public final Object invoke() {
            return Executors.newSingleThreadExecutor(new TraceUtil$commonExecutor$2$.ExternalSyntheticLambda0());
        }
    });
    private static final Lazy commonLooper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.utils.TraceUtil$commonLooper$2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Looper m512invoke() {
            Object obj;
            TraceUtil traceUtil = TraceUtil.INSTANCE;
            Object mainLooper = Looper.getMainLooper();
            try {
                HandlerThread handlerThread = new HandlerThread("UTrace.Sdk.CommonLooper");
                handlerThread.start();
                obj = Result.constructor-impl(handlerThread.getLooper());
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs.INSTANCE.e("UTrace.Sdk.Utils", "startNewLooper(UTrace.Sdk.CommonLooper): " + th2);
            }
            if (!(obj instanceof Result.Failure)) {
                mainLooper = obj;
            }
            return (Looper) mainLooper;
        }
    });
    private static final Lazy commonThreadHandler$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.utrace.utils.TraceUtil$commonThreadHandler$2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Handler m513invoke() {
            return new Handler(TraceUtil.INSTANCE.getCommonLooper());
        }
    });

    public static final String access$generateSpanId() {
        TraceUtil traceUtil = INSTANCE;
        Class<?> cls = traceUtil.getClass();
        ClassLoader classLoader = cls.getClassLoader();
        Object obj = cls;
        if (classLoader != null) {
            obj = classLoader;
        }
        return "p_" + traceUtil.getPid() + '_' + ((Object) Integer.toHexString(System.identityHashCode(obj)));
    }

    public static final byte[] decodeToBytes(String str) {
        Object obj;
        try {
            obj = Result.constructor-impl(Base64.decode(str, 0));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        byte[] bArr = new byte[0];
        if (obj instanceof Result.Failure) {
            obj = bArr;
        }
        return (byte[]) obj;
    }

    public static final String encodeToString(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static final String genSpanId(String str) {
        return str == null ? INSTANCE.getDefaultSpanId() : Intrinsics.stringPlus(str, "s_");
    }

    public static final String genTraceId() {
        return String.format(Locale.ENGLISH, "7f000001%d%d%05d", Arrays.copyOf(new Object[]{Long.valueOf(System.currentTimeMillis()), Integer.valueOf((entryCounter.getAndIncrement() & 4095) + 1000), Integer.valueOf(INSTANCE.getPid())}, 3));
    }

    public static final String generateLogPrefix$utrace_sdk_liteRelease(Object obj) {
        return "[" + UTraceApp.moduleName + '|' + UtilsKt.getProcessName(UTraceApp.mContext) + ']';
    }

    private final String getDefaultSpanId() {
        return (String) defaultSpanId$delegate.getValue();
    }

    private final int getPid() {
        return ((Number) pid$delegate.getValue()).intValue();
    }

    public static final boolean isTraceId(String str) {
        return StringsKt__StringsJVMKt.startsWith(str, "7f000001", false);
    }

    public static final byte[] marshall(Parcelable parcelable) {
        Parcel parcelObtain = Parcel.obtain();
        parcelable.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static final boolean switchOn$utrace_sdk_liteRelease() {
        if (!UTraceApp.INSTANCE.getMEnabled$utrace_sdk_liteRelease() || UTraceApp.mContext == null) {
            return false;
        }
        return SdkConfig.INSTANCE.getCoreAvailable() ? SdkConfig.isEnabled() : ExceptionProtectUtil.checkUserSwitch();
    }

    public static final <T> T unmarshall(Parcelable.Creator<T> creator, byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = creator.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }

    public final ExecutorService getCommonExecutor() {
        return (ExecutorService) commonExecutor$delegate.getValue();
    }

    public final Looper getCommonLooper() {
        return (Looper) commonLooper$delegate.getValue();
    }

    public final Handler getCommonThreadHandler() {
        return (Handler) commonThreadHandler$delegate.getValue();
    }

    public static final String genSpanId() {
        return INSTANCE.getDefaultSpanId();
    }
}
