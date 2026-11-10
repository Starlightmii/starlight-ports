package com.oplus.utrace.sdk;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.oplus.utrace.lib.NodeID;
import com.oplus.utrace.utils.Logs;
import com.oplus.utrace.utils.UtilsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ULog {
    private static volatile IULogger mLogger;
    public static final ULog INSTANCE = new ULog();
    private static final ThreadLocal<UTraceContext> tempCtx = new ThreadLocal<>();

    public static final int d(String str, String str2, boolean z) {
        int iD = 0;
        if (Logs.INSTANCE.getDebuggable()) {
            ULog uLog = INSTANCE;
            if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
                if (str == null) {
                    str = "";
                }
                String message = formatMessage(str2);
                IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
                iD = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.d(str, message) : 0;
                if (!z) {
                    return Log.d(str, message);
                }
            }
        }
        return iD;
    }

    public static final int e(String str, String str2, boolean z) {
        ULog uLog = INSTANCE;
        int iE = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iE = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.e(str, message) : 0;
            if (!z) {
                return Log.e(str, message);
            }
        }
        return iE;
    }

    public static final String formatMessage(String str) {
        UTraceContext context = UTrace.getContext();
        if (context == null) {
            context = tempCtx.get();
        }
        if (context != null) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, " [");
            sbM.append(context.getTraceID$utrace_sdk_liteRelease());
            sbM.append('|');
            NodeID parent$utrace_sdk_liteRelease = context.getParent$utrace_sdk_liteRelease();
            sbM.append((Object) (parent$utrace_sdk_liteRelease == null ? null : parent$utrace_sdk_liteRelease.getSpanID(true)));
            sbM.append('|');
            sbM.append(context.getCurrent$utrace_sdk_liteRelease().getSpanID(true));
            sbM.append('|');
            sbM.append(UTraceApp.mPkgName);
            sbM.append(']');
            str = sbM.toString();
        }
        String strTransformLogMessage = UtilsKt.transformLogMessage(str);
        INSTANCE.getTempCtx$utrace_sdk_liteRelease().remove();
        return strTransformLogMessage;
    }

    public static final int i(String str, String str2, boolean z) {
        ULog uLog = INSTANCE;
        int i = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            i = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.i(str, message) : 0;
            if (!z) {
                return Log.i(str, message);
            }
        }
        return i;
    }

    public static final int println(int i, String str, String str2, boolean z) {
        int iPrintln = 0;
        if (!Logs.INSTANCE.getDebuggable() && i == 3) {
            return 0;
        }
        ULog uLog = INSTANCE;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iPrintln = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.println(i, str, message) : 0;
            if (!z) {
                return Log.println(i, str, message);
            }
        }
        return iPrintln;
    }

    public static final void releaseLogger$utrace_sdk_liteRelease() {
        IULogger iULogger = mLogger;
        mLogger = null;
        if (iULogger == null) {
            return;
        }
        iULogger.release();
    }

    public static final int v(String str, String str2, Throwable th, boolean z) {
        ULog uLog = INSTANCE;
        int iV = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iV = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.v(str, message, th) : 0;
            if (!z) {
                return Log.v(str, message, th);
            }
        }
        return iV;
    }

    public static final int w(String str, Throwable th, boolean z) {
        ULog uLog = INSTANCE;
        String strValueOf = th == null ? null : String.valueOf(th);
        int iW = 0;
        if (strValueOf != null && !StringsKt__StringsKt.isBlank(strValueOf)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(strValueOf);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iW = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.w(str, message, th) : 0;
            if (!z) {
                return Log.w(str, th);
            }
        }
        return iW;
    }

    public final IULogger getMLogger$utrace_sdk_liteRelease() {
        return mLogger;
    }

    public final ThreadLocal<UTraceContext> getTempCtx$utrace_sdk_liteRelease() {
        return tempCtx;
    }

    public final void setMLogger$utrace_sdk_liteRelease(IULogger iULogger) {
        mLogger = iULogger;
    }

    public static final int e(String str, String str2) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.e(str, message);
        }
        return Log.e(str, message);
    }

    public static final int i(String str, String str2) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.i(str, message);
        }
        return Log.i(str, message);
    }

    public static final int v(String str, String str2, boolean z) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        int iV = mLogger$utrace_sdk_liteRelease == null ? 0 : mLogger$utrace_sdk_liteRelease.v(str, message);
        if (z) {
            return iV;
        }
        return 0;
    }

    public static final int v(String str, String str2, Throwable th) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.v(str, message, th);
        }
        return Log.v(str, message, th);
    }

    public static final int e(String str, String str2, Throwable th) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.e(str, message, th);
        }
        return Log.e(str, message, th);
    }

    public static final int i(String str, String str2, Throwable th) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.i(str, message, th);
        }
        return Log.i(str, message, th);
    }

    public static final int d(String str, String str2) {
        if (Logs.INSTANCE.getDebuggable()) {
            ULog uLog = INSTANCE;
            if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
                if (str == null) {
                    str = "";
                }
                String message = formatMessage(str2);
                IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
                if (mLogger$utrace_sdk_liteRelease != null) {
                    mLogger$utrace_sdk_liteRelease.d(str, message);
                }
                return Log.d(str, message);
            }
        }
        return 0;
    }

    public static final int w(String str, String str2, boolean z) {
        ULog uLog = INSTANCE;
        int iW = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iW = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.w(str, message) : 0;
            if (!z) {
                return Log.w(str, message);
            }
        }
        return iW;
    }

    public static final int println(int i, String str, String str2) {
        if (!Logs.INSTANCE.getDebuggable() && i == 3) {
            return 0;
        }
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.println(i, str, message);
        }
        return Log.println(i, str, message);
    }

    public static final int v(String str, String str2) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease == null) {
            return 0;
        }
        mLogger$utrace_sdk_liteRelease.v(str, message);
        return 0;
    }

    public static final int e(String str, String str2, Throwable th, boolean z) {
        ULog uLog = INSTANCE;
        int iE = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iE = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.e(str, message, th) : 0;
            if (!z) {
                return Log.e(str, message, th);
            }
        }
        return iE;
    }

    public static final int i(String str, String str2, Throwable th, boolean z) {
        ULog uLog = INSTANCE;
        int i = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            i = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.i(str, message, th) : 0;
            if (!z) {
                return Log.i(str, message, th);
            }
        }
        return i;
    }

    public static final int w(String str, Throwable th) {
        ULog uLog = INSTANCE;
        String strValueOf = th == null ? null : String.valueOf(th);
        if (strValueOf == null || StringsKt__StringsKt.isBlank(strValueOf)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(strValueOf);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.w(str, message, th);
        }
        return Log.w(str, th);
    }

    public static final int d(String str, String str2, Throwable th) {
        if (Logs.INSTANCE.getDebuggable()) {
            ULog uLog = INSTANCE;
            if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
                if (str == null) {
                    str = "";
                }
                String message = formatMessage(str2);
                IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
                if (mLogger$utrace_sdk_liteRelease != null) {
                    mLogger$utrace_sdk_liteRelease.d(str, message, th);
                }
                return Log.d(str, message, th);
            }
        }
        return 0;
    }

    public static final int println(int i, int i2, String str, String str2) {
        return println(i2, str, str2);
    }

    public static final int w(String str, String str2) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.w(str, message);
        }
        return Log.w(str, message);
    }

    public static final int d(String str, String str2, Throwable th, boolean z) {
        int iD = 0;
        if (Logs.INSTANCE.getDebuggable()) {
            ULog uLog = INSTANCE;
            if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
                if (str == null) {
                    str = "";
                }
                String message = formatMessage(str2);
                IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
                iD = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.d(str, message, th) : 0;
                if (!z) {
                    return Log.d(str, message, th);
                }
            }
        }
        return iD;
    }

    public static final int w(String str, String str2, Throwable th) {
        ULog uLog = INSTANCE;
        if (str2 == null || StringsKt__StringsKt.isBlank(str2)) {
            return 0;
        }
        if (str == null) {
            str = "";
        }
        String message = formatMessage(str2);
        IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
        if (mLogger$utrace_sdk_liteRelease != null) {
            mLogger$utrace_sdk_liteRelease.w(str, message, th);
        }
        return Log.w(str, message, th);
    }

    public static final int w(String str, String str2, Throwable th, boolean z) {
        ULog uLog = INSTANCE;
        int iW = 0;
        if (str2 != null && !StringsKt__StringsKt.isBlank(str2)) {
            if (str == null) {
                str = "";
            }
            String message = formatMessage(str2);
            IULogger mLogger$utrace_sdk_liteRelease = uLog.getMLogger$utrace_sdk_liteRelease();
            iW = mLogger$utrace_sdk_liteRelease != null ? mLogger$utrace_sdk_liteRelease.w(str, message, th) : 0;
            if (!z) {
                return Log.w(str, message, th);
            }
        }
        return iW;
    }
}
