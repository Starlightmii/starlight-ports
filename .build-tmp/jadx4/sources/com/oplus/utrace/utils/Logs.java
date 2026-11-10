package com.oplus.utrace.utils;

import android.content.Context;
import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Logs {
    public static final Logs INSTANCE = new Logs();
    public static volatile boolean debugExternal;
    public static volatile boolean debugInternal;
    public static volatile boolean debuggable;
    private static ILogger logger;

    public static /* synthetic */ void setDebuggable$default(Logs logs, Boolean bool, Boolean bool2, int i) {
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            bool2 = null;
        }
        logs.setDebuggable(bool, bool2);
    }

    public final void d(String str, String str2, Throwable th) {
        String strTransformLogMessage;
        if (debuggable) {
            String str3 = "";
            if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
                str3 = strTransformLogMessage;
            }
            Log.d(str, str3, th);
            ILogger logger2 = INSTANCE.getLogger();
            if (logger2 == null) {
                return;
            }
            logger2.d(str, str3, th);
        }
    }

    public final void e(String str, String str2) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.e(str, str3);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.e(str, str3);
    }

    public final boolean getDebuggable() {
        return debuggable;
    }

    public final ILogger getLogger() {
        return logger;
    }

    public final void i(String str, String str2) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.i(str, str3);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.i(str, str3);
    }

    public final void init(Context context) {
    }

    public final void setDebuggable(Boolean bool, Boolean bool2) {
        if (bool2 != null) {
            debugInternal = bool2.booleanValue();
        }
        if (bool != null) {
            debugExternal = bool.booleanValue();
        }
        debuggable = debugInternal || debugExternal;
    }

    public final void setLogger(ILogger iLogger) {
        logger = iLogger;
    }

    public final void w(String str, String str2) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.w(str, str3);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.w(str, str3);
    }

    public final void e(String str, String str2, Throwable th) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.e(str, str3, th);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.e(str, str3, th);
    }

    public final void i(String str, String str2, Throwable th) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.i(str, str3, th);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.i(str, str3, th);
    }

    public final void w(String str, String str2, Throwable th) {
        String strTransformLogMessage;
        String str3 = "";
        if (str2 != null && (strTransformLogMessage = UtilsKt.transformLogMessage(str2)) != null) {
            str3 = strTransformLogMessage;
        }
        Log.w(str, str3, th);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.w(str, str3, th);
    }

    public final void d(String str, String str2) {
        if (!debuggable || str2 == null) {
            return;
        }
        String strTransformLogMessage = UtilsKt.transformLogMessage(str2);
        Log.d(str, strTransformLogMessage);
        ILogger logger2 = INSTANCE.getLogger();
        if (logger2 == null) {
            return;
        }
        logger2.d(str, strTransformLogMessage);
    }

    public final void setDebuggable(boolean z) {
        debuggable = z;
    }
}
