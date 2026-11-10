package com.oplus.utrace.sdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.oplus.utrace.lib.SdkConfigData;
import com.oplus.utrace.sdk.UTraceApp;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.Providers;
import com.oplus.utrace.utils.SharedPreferencesUtil;
import com.oplus.utrace.utils.TimeSupplier;
import com.oplus.utrace.utils.TraceUtil;
import com.oplus.utrace.utils.UtilsKt;
import java.lang.invoke.VarHandle;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SdkConfig {
    public static final SdkConfig INSTANCE = new SdkConfig();
    private static final String KEY_SDK_CONFIG_DATA = "key_sdk_config_data";
    private static final String TAG = "UTrace.Sdk.SdkConfig";
    private static volatile SdkConfigData configData;
    public static boolean coreState;

    /* JADX INFO: renamed from: $r8$lambda$UxLQhCuG4Am8spbs-A6YGY714SY, reason: not valid java name */
    public static void m510$r8$lambda$UxLQhCuG4Am8spbsA6YGY714SY(Context context) {
        Object obj;
        SdkConfigData sdkConfigDataCreateFromBundle;
        SdkConfigData sdkConfigData = configData;
        sdkConfigData.getClass();
        SdkConfigData.Companion companion = SdkConfigData.Companion;
        companion.getClass();
        TimeSupplier.Companion.getInstance().getClass();
        if (System.currentTimeMillis() > sdkConfigData.expireTime) {
            INSTANCE.getClass();
            try {
                Bundle bundleCallCoreProvider = Providers.INSTANCE.callCoreProvider(context, SdkConfigData.METHOD_QUERY_SDK_CONFIG, new Bundle());
                Logs.INSTANCE.d(TAG, Intrinsics.stringPlus(bundleCallCoreProvider, "querySdkConfig() result="));
                if (bundleCallCoreProvider != null) {
                    if (bundleCallCoreProvider.isEmpty()) {
                        bundleCallCoreProvider = null;
                    }
                    sdkConfigDataCreateFromBundle = bundleCallCoreProvider == null ? null : companion.createFromBundle(bundleCallCoreProvider);
                }
                obj = Result.constructor-impl(sdkConfigDataCreateFromBundle);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Logs.INSTANCE.w(TAG, Intrinsics.stringPlus(th2.getMessage(), "querySdkConfig() exception="), th2);
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            SdkConfigData sdkConfigData2 = (SdkConfigData) obj;
            coreState = sdkConfigData2 != null;
            if (sdkConfigData2 == null) {
                sdkConfigData2 = new SdkConfigData(configData.isEnabled, configData.getOverflow(), configData.getOverflowPt(), configData.getLogsDebuggable(), configData.getTraceCacheMetrics(), configData.getTraceLogCtrl(), configData.getHLogCtrl(), configData.getBindWindow(), 256);
            }
            configData = sdkConfigData2;
            Logs logs = Logs.INSTANCE;
            logs.i(TAG, "queryIfExpired() receive=" + configData + " coreState=" + coreState + " expireTime=(" + new Date(configData.getExpireTime()) + ')');
            INSTANCE.getClass();
            SdkConfigData sdkConfigData3 = configData;
            Context context2 = UTraceApp.mContext;
            if (context2 != null) {
                SharedPreferencesUtil.putString(context2, KEY_SDK_CONFIG_DATA, sdkConfigData3.toJSONString());
            }
            Logs.setDebuggable$default(logs, null, Boolean.valueOf(configData.getLogsDebuggable()), 1);
        }
    }

    static {
        String string;
        SdkConfigData sdkConfigDataCreateFromJSONString;
        Context context = UTraceApp.mContext;
        SdkConfigData sdkConfigData = null;
        if (context != null && (string = SharedPreferencesUtil.getString(context, KEY_SDK_CONFIG_DATA)) != null) {
            if (StringsKt__StringsKt.isBlank(string)) {
                string = null;
            }
            if (string != null && (sdkConfigDataCreateFromJSONString = SdkConfigData.Companion.createFromJSONString(string)) != null) {
                Logs.setDebuggable$default(Logs.INSTANCE, null, Boolean.valueOf(sdkConfigDataCreateFromJSONString.getLogsDebuggable()), 1);
                sdkConfigData = sdkConfigDataCreateFromJSONString;
            }
        }
        if (sdkConfigData == null) {
            sdkConfigData = new SdkConfigData(false, (SdkConfigData.FlowCtrl) null, (String) null, false, (SdkConfigData.TraceCacheMetrics) null, (SdkConfigData.TraceLogCtrl) null, (SdkConfigData.HLogCtrl) null, 0L, 255);
        }
        Logs logs = Logs.INSTANCE;
        if (logs.getDebuggable()) {
            logs.d(TAG, "loadFromSp() result=" + sdkConfigData + " expireTime=(" + new Date(sdkConfigData.getExpireTime()) + ')');
        }
        configData = sdkConfigData;
    }

    public static /* synthetic */ void getConfigData$utrace_sdk_liteRelease$annotations() {
    }

    public static boolean isEnabled() {
        queryIfExpired();
        return configData.isEnabled;
    }

    public static void queryIfExpired() {
        Context context = UTraceApp.mContext;
        if (context == null) {
            Logs.INSTANCE.d(TAG, "queryIfExpired() context==null ==> coreState=false");
            coreState = false;
            return;
        }
        SdkConfigData sdkConfigData = configData;
        sdkConfigData.getClass();
        SdkConfigData.Companion.getClass();
        TimeSupplier.Companion.getInstance().getClass();
        if (System.currentTimeMillis() > sdkConfigData.expireTime) {
            ExecutorService commonExecutor = TraceUtil.INSTANCE.getCommonExecutor();
            SdkConfig$$ExternalSyntheticLambda0 sdkConfig$$ExternalSyntheticLambda0 = new SdkConfig$$ExternalSyntheticLambda0();
            sdkConfig$$ExternalSyntheticLambda0.f$0 = context;
            VarHandle.storeStoreFence();
            UtilsKt.submitSafe(commonExecutor, sdkConfig$$ExternalSyntheticLambda0);
        }
    }

    public final long getBindWindow() {
        queryIfExpired();
        return configData.getBindWindow();
    }

    public final SdkConfigData getConfigData$utrace_sdk_liteRelease() {
        return configData;
    }

    public final boolean getCoreAvailable() {
        queryIfExpired();
        return coreState;
    }

    public final long getExpireTime() {
        return configData.getExpireTime();
    }

    public final SdkConfigData.HLogCtrl getHLogCtrl() {
        queryIfExpired();
        return configData.getHLogCtrl();
    }

    public final SdkConfigData.FlowCtrl getOverflow() {
        queryIfExpired();
        return configData.getOverflow();
    }

    public final Map<String, SdkConfigData.FlowCtrl> getOverflowPt() {
        queryIfExpired();
        return configData.getFlowCtrlPt();
    }

    public final SdkConfigData.TraceCacheMetrics getTraceCacheMetrics() {
        queryIfExpired();
        return configData.getTraceCacheMetrics();
    }

    public final SdkConfigData.TraceLogCtrl getTraceLogCtrl() {
        queryIfExpired();
        return configData.getTraceLogCtrl();
    }

    public final void setConfigData$utrace_sdk_liteRelease(SdkConfigData sdkConfigData) {
        configData = sdkConfigData;
    }
}
