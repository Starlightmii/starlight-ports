package com.oplus.utrace.sdk;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplus.utrace.hlog.ULoggerImpl;
import com.oplus.utrace.sdk.internal.UTraceManager;
import com.oplus.utrace.utils.ExceptionProtectUtil;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.TraceUtil;
import com.oplus.utrace.utils.UserUnlockManager;
import com.oplus.utrace.utils.UtilsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UTraceApp {
    public static final int FLAG_ENABLE_CORE = 1;
    public static final int FLAG_ENABLE_LOG_DEBUG = 3;
    public static final int FLAG_IN_SEEDLING_PLUGIN = 2;
    public static final UTraceApp INSTANCE = new UTraceApp();
    private static Context mContext = null;
    public static boolean mEnabled = true;
    public static boolean mInSeedlingPlugin = false;
    private static String mPkgName = "";
    private static String moduleName = "";

    public static final void init(Context context, String str) {
        if (mContext != null) {
            Log.d("UTrace.Sdk.App", Intrinsics.stringPlus(str, "init() redundant call. moduleName="));
            return;
        }
        mContext = context;
        mPkgName = context.getPackageName();
        moduleName = str.length() == 0 ? "default" : str;
        Logs logs = Logs.INSTANCE;
        StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("init() moduleName=", str, " process=");
        sbM.append(UtilsKt.getProcessName(context));
        sbM.append(" version=1.4.18-f23f716-20240927-165914");
        logs.i("UTrace.Sdk.App", sbM.toString());
        ExceptionProtectUtil.setContext(context);
        UserUnlockManager.INSTANCE.init(context);
        INSTANCE.getClass();
        logs.init(context);
        logs.i("UTrace.Sdk.App", TraceUtil.generateLogPrefix$utrace_sdk_liteRelease(null) + " initLog() context=" + context + " loader=" + UTraceApp.class.getClassLoader());
        ULoggerImpl.Companion.loadAndQuery(context);
    }

    public static final void setFlag(int i, int i2) {
        Logs logs = Logs.INSTANCE;
        logs.d("UTrace.Sdk.App", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "setFlag flag = ", ", value = "));
        if (i == 1) {
            mEnabled = i2 != 0;
        } else if (i == 2) {
            mInSeedlingPlugin = i2 != 0;
        } else if (i == 3) {
            Logs.setDebuggable$default(logs, Boolean.valueOf(i2 != 0), null, 2);
        }
    }

    public static final void uninit() {
        Logs logs = Logs.INSTANCE;
        logs.i("UTrace.Sdk.App", "uninit() moduleName=" + moduleName + " version=1.4.18-f23f716-20240927-165914");
        UserUnlockManager.INSTANCE.getClass();
        UserUnlockManager.release();
        UTraceManager.INSTANCE.uninit$utrace_sdk_liteRelease();
        INSTANCE.getClass();
        logs.i("UTrace.Sdk.App", TraceUtil.generateLogPrefix$utrace_sdk_liteRelease(null) + " uninit() mLogger=" + ULog.INSTANCE.getMLogger$utrace_sdk_liteRelease() + " loader=" + UTraceApp.class.getClassLoader());
        ULog.releaseLogger$utrace_sdk_liteRelease();
        ULoggerImpl.Companion.getClass();
        TraceUtil traceUtil = TraceUtil.INSTANCE;
        traceUtil.getCommonExecutor().shutdown();
        Looper commonLooper = traceUtil.getCommonLooper();
        Looper looper = Intrinsics.areEqual(commonLooper.getThread(), Looper.getMainLooper().getThread()) ? null : commonLooper;
        if (looper == null) {
            return;
        }
        looper.quit();
    }

    public final boolean getMEnabled$utrace_sdk_liteRelease() {
        return mEnabled;
    }

    public final void setMEnabled$utrace_sdk_liteRelease(boolean z) {
        mEnabled = z;
    }

    public static final void init(Context context) {
        init(context, "");
    }
}
