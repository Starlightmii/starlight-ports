package com.oplusos.systemui.common.util;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.log.LogBuffer;
import com.oplus.systemui.volume.utils.material.VolumePlatformBlurHostHelper;
import com.oplus.transition.OplusAnimationController;
import kotlin.Deprecated;
import kotlin.jvm.functions.Function0;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogUtil {
    private static final String DAILY_OTA_VERSION_KEY = "ro.oplus.dailyota";
    private static final String LEVEL_DEBUG = "systemui_debug";
    private static final String LEVEL_INTERNAL_DEBUG = "systemui_internal_debug";
    private static final String LOG_SWITCH = "log_switch_type";
    private static final String OTA_VERSION_KEY = "ro.build.version.ota";
    private static final String PANIC_KEY = "persist.sys.assert.panic";
    public static final String SEPARATOR = "-->";
    public static final String TAG = "SystemUi--";
    public static final String TAG_KEYBOARD = "KeyBoard";
    public static final String TAG_USB_DIALOG = "UsbDialog";
    public static Context context;
    public static int debugInternalStateValue;
    public static int debugStateValue;
    public static boolean isLogBufferDisabled;
    public static boolean isTrialVersion;
    public static boolean normalDebug;
    public static boolean panicEnabled;
    public static boolean sInternal;
    public static final LogUtil INSTANCE = new LogUtil();
    private static final Runnable resetLogBufferRunnable = LogUtil$resetLogBufferRunnable$1.INSTANCE;
    public static final String TAG_COMMON = "Common";
    public static final String TAG_KEYGUARD = "Keyguard";
    public static final String TAG_NOTIFICATION = "Notification";
    public static final String TAG_STATUSBAR = "Statusbar";
    public static final String TAG_NAVBAR = "NavBar";
    public static final String TAG_BACKUPRESTORE = "BackupRestore";
    public static final String TAG_RECOVERY = "Recovery";
    public static final String TAG_STACK_DIVIDER = "StackDivider";
    public static final String TAG_AOD = "Aod";
    public static final String TAG_EP = "EdgePanel";
    public static final String TAG_VOLUME = "Volume";
    public static final String TAG_AV = "AirView";
    public static final String TAG_CUSTOM = "Custom";
    public static final String TAG_GLOBALACTIONS = "GlobalActions";
    public static final String TAG_QS = "QuickSettings";
    public static final String TAG_WALLPAPER = "Wallpaper";
    public static final String TAG_ASSIST = "Assist";
    public static final String TAG_MEDIA = "Media";
    public static final String TAG_DEVICE = "Device_Panel";
    public static final String TAG_MINI_CAPSULE = "MiniCapsule";
    public static final String TAG_SEEDLING = "SeedlingPlugin";
    private static final String[] TAGS = {TAG_COMMON, TAG_KEYGUARD, TAG_NOTIFICATION, TAG_STATUSBAR, TAG_NAVBAR, TAG_BACKUPRESTORE, TAG_RECOVERY, TAG_STACK_DIVIDER, TAG_AOD, TAG_EP, TAG_VOLUME, TAG_AV, TAG_CUSTOM, TAG_GLOBALACTIONS, TAG_QS, TAG_WALLPAPER, TAG_ASSIST, TAG_MEDIA, TAG_DEVICE, TAG_MINI_CAPSULE, TAG_SEEDLING};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IDebug {
        String debugInfo();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LogObserver extends ContentObserver {
        public final Uri logSwitchUri;
        public final Uri uriDebug;
        public final Uri uriDebugInternal;

        public LogObserver(Handler handler) {
            super(handler);
            this.uriDebug = Settings.Global.getUriFor(LogUtil.LEVEL_DEBUG);
            this.uriDebugInternal = Settings.Global.getUriFor(LogUtil.LEVEL_INTERNAL_DEBUG);
            this.logSwitchUri = Settings.System.getUriFor(LogUtil.LOG_SWITCH);
        }

        public final void observer(Context context) {
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.registerContentObserver(this.uriDebug, false, this);
            contentResolver.registerContentObserver(this.uriDebugInternal, false, this);
            contentResolver.registerContentObserver(this.logSwitchUri, false, this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (uri == null) {
                return;
            }
            if (uri.equals(this.uriDebug)) {
                LogUtil.updateLevel();
                return;
            }
            if (uri.equals(this.uriDebugInternal)) {
                LogUtil.debugInternalStateValue = LogUtil.getDebugState(LogUtil.LEVEL_INTERNAL_DEBUG, true);
            } else if (uri.equals(this.logSwitchUri)) {
                LogUtil.updateLevel();
                LogUtil.debugInternalStateValue = LogUtil.getDebugState(LogUtil.LEVEL_INTERNAL_DEBUG, true);
            }
        }
    }

    public static final void d(String str, String str2, String str3, Throwable th) {
        if (normalDebug && isTagValid(str)) {
            Log.d(FileProvider$.ExternalSyntheticOutline0.m(TAG, str), str2 + SEPARATOR + str3, th);
        }
    }

    public static /* synthetic */ void d$default(int i, String str, String str2, String str3) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        d(str, str2, str3, (IDebug) null);
    }

    public static final void e(String str, String str2, String str3, IDebug iDebug) {
        if (isTagValid(str)) {
            String strM = FileProvider$.ExternalSyntheticOutline0.m(TAG, str);
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, SEPARATOR);
            if (str3 == null) {
                str3 = iDebug != null ? iDebug.debugInfo() : null;
            }
            sbM.append(str3);
            Log.e(strM, sbM.toString());
        }
    }

    public static /* synthetic */ void e$default(int i, String str, String str2, String str3) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        e(str, str2, str3, (IDebug) null);
    }

    public static final String encryptionNotificationKey(Object obj) {
        if (obj == null) {
            return "";
        }
        String string = obj instanceof String ? (String) obj : obj.toString();
        return TextUtils.isEmpty(string) ? "" : new Regex("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+").replace(string, "***encryption***");
    }

    @Deprecated
    public static final String getCallers(int i) {
        String str;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuffer stringBuffer = new StringBuffer("\n");
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 4 + i2;
            if (i3 >= stackTrace.length) {
                str = "<bottom of call stack>";
            } else {
                StackTraceElement stackTraceElement = stackTrace[i3];
                str = stackTraceElement.getClassName() + '.' + stackTraceElement.getMethodName() + ':' + stackTraceElement.getLineNumber();
            }
            stringBuffer.append(str);
            stringBuffer.append(" \n ");
        }
        return stringBuffer.toString();
    }

    @Deprecated
    private static /* synthetic */ void getDebugInternalStateValue$annotations() {
    }

    public static final int getDebugState(String str, boolean z) {
        if (z) {
            Context context2 = context;
            if (context2 == null) {
                context2 = null;
            }
            return Settings.Global.getInt(context2.getContentResolver(), str, 0);
        }
        if (str.equals(LEVEL_DEBUG)) {
            return debugStateValue;
        }
        if (str.equals(LEVEL_INTERNAL_DEBUG)) {
            return debugInternalStateValue;
        }
        Log.d(TAG, "getDebugState: is not appropriate debugType");
        return 0;
    }

    @Deprecated
    private static /* synthetic */ void getDebugStateValue$annotations() {
    }

    @Deprecated
    private static /* synthetic */ void getInternalKeyguard$annotations() {
    }

    @Deprecated
    private static /* synthetic */ void getInternalNotification$annotations() {
    }

    @Deprecated
    private static /* synthetic */ void getInternalOthers$annotations() {
    }

    @Deprecated
    private static /* synthetic */ void getInternalStatusBar$annotations() {
    }

    public static final boolean getLogInfoEnabled() {
        return true;
    }

    public static /* synthetic */ void getLogInfoEnabled$annotations() {
    }

    public static final CharSequence getLogSafeMessage(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return "";
        }
        if (charSequence.length() <= i + i2) {
            return charSequence;
        }
        return CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(charSequence.subSequence(0, i).toString(), StringsKt__StringsJVMKt.repeat("*", (charSequence.length() - i) - i2), charSequence.subSequence(charSequence.length() - i2, charSequence.length()).toString());
    }

    public static final boolean getNormalDebug() {
        return normalDebug;
    }

    public static /* synthetic */ void getNormalDebug$annotations() {
    }

    public static final boolean getSInternal() {
        return sInternal;
    }

    @Deprecated
    public static /* synthetic */ void getSInternal$annotations() {
    }

    @Deprecated
    private static /* synthetic */ void getTraceEnabled$annotations() {
    }

    public static final void i(String str, String str2, String str3, IDebug iDebug) {
        if (isTagValid(str)) {
            String strM = FileProvider$.ExternalSyntheticOutline0.m(TAG, str);
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, SEPARATOR);
            if (str3 == null) {
                str3 = iDebug != null ? iDebug.debugInfo() : null;
            }
            sbM.append(str3);
            Log.i(strM, sbM.toString());
        }
    }

    public static /* synthetic */ void i$default(String str, String str2, String str3, IDebug iDebug, int i) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            iDebug = null;
        }
        i(str, str2, str3, iDebug);
    }

    public static final void init(Context context2, Handler handler) {
        context = context2.getApplicationContext();
        isTrialVersion = StringsKt__StringsKt.contains(SystemProperties.get(OTA_VERSION_KEY, ""), "PRE", false) || SystemProperties.getBoolean(DAILY_OTA_VERSION_KEY, false);
        updateLevel();
        debugInternalStateValue = getDebugState(LEVEL_INTERNAL_DEBUG, true);
        new LogObserver(handler).observer(context2);
    }

    public static final boolean isTagValid(String str) {
        return true;
    }

    public static final void perf(String str) {
        ReflectionUtils.staticMethodInvoke("android.util.Log", "p", new Class[]{String.class, String.class}, new String[]{TAG, str});
    }

    public static final void setDebugState(int i, String str, boolean z) {
        int debugState = getDebugState(str, false);
        int i2 = z ? i | debugState : (~i) & debugState;
        Context context2 = context;
        if (context2 == null) {
            context2 = null;
        }
        Settings.Global.putInt(context2.getContentResolver(), str, i2);
    }

    @Deprecated
    public static final void setInternalDebugState(boolean z) {
        setDebugState(16, LEVEL_DEBUG, z);
    }

    @Deprecated
    public static final void setInternalKeyguardDebugState(boolean z) {
        setDebugState(16, LEVEL_INTERNAL_DEBUG, z);
    }

    @Deprecated
    public static final void setInternalNotificationDebugState(boolean z) {
        setDebugState(256, LEVEL_INTERNAL_DEBUG, z);
    }

    @Deprecated
    public static final void setInternalOthersDebugState(boolean z) {
        setDebugState(OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1, LEVEL_INTERNAL_DEBUG, z);
    }

    @Deprecated
    public static final void setInternalStatusbarDebugState(boolean z) {
        setDebugState(1, LEVEL_INTERNAL_DEBUG, z);
    }

    public static final void setLogBufferDisabled(boolean z) {
        if (isLogBufferDisabled == z || !ScrimUtil.isBAnimLevel() || panicEnabled) {
            return;
        }
        i$default(TAG_COMMON, TAG_COMMON, KeyguardLogger$.ExternalSyntheticOutline0.m("set isLogBufferDisabled ", z), null, 8);
        LogBuffer.Companion.setLogBufferDisabled(z);
        isLogBufferDisabled = z;
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        Runnable runnable = resetLogBufferRunnable;
        mainHandler.removeCallbacks(runnable);
        if (z) {
            SystemUIThreadUtil.getMainHandler().postDelayed(runnable, 2000L);
        }
    }

    @Deprecated
    public static final void setTraceDebugState(boolean z) {
        setDebugState(256, LEVEL_DEBUG, z);
    }

    @Deprecated
    public static final void updateInternal(String str) {
        try {
            sInternal = Integer.parseInt(str) > 0;
        } catch (NumberFormatException e) {
            Log.e(TAG, "updateInternal error", e);
        }
    }

    public static final void updateLevel() {
        boolean z = SystemProperties.getBoolean(PANIC_KEY, true);
        panicEnabled = z;
        normalDebug = z || isTrialVersion;
        int debugState = getDebugState(LEVEL_DEBUG, true);
        debugStateValue = debugState;
        boolean z2 = panicEnabled;
        sInternal = z2 && (16 & debugState) != 0;
        if (z2) {
            int i = debugState & 256;
        }
        StringBuilder sb = new StringBuilder("updateLevel(), debugState = ");
        sb.append(debugStateValue);
        sb.append(", sNormal = ");
        ChildHelper$.ExternalSyntheticOutline0.m(TAG, sb, normalDebug);
    }

    public static final void w(String str, String str2, String str3, IDebug iDebug) {
        if (isTagValid(str)) {
            String strM = FileProvider$.ExternalSyntheticOutline0.m(TAG, str);
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, SEPARATOR);
            if (str3 == null) {
                str3 = iDebug != null ? iDebug.debugInfo() : null;
            }
            ConstraintSet$.ExternalSyntheticOutline0.m(sbM, str3, strM);
        }
    }

    public static /* synthetic */ void w$default(int i, String str, String str2, String str3) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        w(str, str2, str3, (i & 8) == 0 ? VolumePlatformBlurHostHelper.requestMissingScreenshotCapture.1.INSTANCE : null);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class Proxy {
        public static final Companion Companion = new Companion();
        private static final String TAG = "Common";

        public static final void d(String str, String str2) {
            Companion.d(str, str2);
        }

        public static final void e(String str, String str2, Throwable th) {
            Companion.e(str, str2, th);
        }

        public static final void i(String str, String str2) {
            Companion.i(str, str2);
        }

        public static final void w(String str, String str2) {
            Companion.w(str, str2);
        }

        public static final void d(String str, String str2, Throwable th) {
            Companion.d(str, str2, th);
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final void d(String str, String str2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid("Common")) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    if (str2 == null) {
                        str2 = "";
                    }
                    ExifInterface$.ExternalSyntheticOutline1.m(sbM, str2, "SystemUi--Common");
                }
            }

            public final void e(String str, String str2, Throwable th) {
                LogUtil.e("Common", str, str2, th);
            }

            public final void i(String str, String str2) {
                LogUtil.i$default("Common", str, str2, null, 8);
            }

            public final void w(String str, String str2) {
                LogUtil.w$default(8, "Common", str, str2);
            }

            public final void d(String str, String str2, Throwable th) {
                LogUtil.d("Common", str, str2, th);
            }
        }
    }

    public static final void w(String str, String str2, String str3) {
        w$default(8, str, str2, str3);
    }

    public static final void w(String str, String str2) {
        w$default(12, str, str2, null);
    }

    public static final void e(String str, String str2, String str3) {
        e$default(8, str, str2, str3);
    }

    public static final void i(String str, String str2, String str3) {
        i$default(str, str2, str3, null, 8);
    }

    public static final void d(String str, String str2, String str3) {
        d$default(8, str, str2, str3);
    }

    public static final void e(String str, String str2) {
        e$default(12, str, str2, null);
    }

    public static final void i(String str, String str2) {
        i$default(str, str2, null, null, 12);
    }

    public static final void d(String str, String str2, Function0 function0) {
        if (getNormalDebug() && isTagValid(str)) {
            String strM = FileProvider$.ExternalSyntheticOutline0.m(TAG, str);
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, SEPARATOR);
            String str3 = (String) function0.invoke();
            if (str3 == null) {
                str3 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str3, strM);
        }
    }

    public static final void e(String str, String str2, String str3, Throwable th) {
        if (isTagValid(str)) {
            Log.e(FileProvider$.ExternalSyntheticOutline0.m(TAG, str), str2 + SEPARATOR + str3, th);
        }
    }

    public static final void d(String str, String str2, String str3, IDebug iDebug) {
        if (normalDebug && isTagValid(str)) {
            String strM = FileProvider$.ExternalSyntheticOutline0.m(TAG, str);
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, SEPARATOR);
            if (str3 == null) {
                str3 = iDebug != null ? iDebug.debugInfo() : null;
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str3, strM);
        }
    }

    public static final void d(String str, String str2) {
        d$default(12, str, str2, null);
    }

    public static final String getLogSafeMessage(CharSequence charSequence) {
        if (charSequence == null) {
            return "";
        }
        int length = charSequence.length();
        if (length > 7) {
            return getLogSafeMessage(charSequence, 3, 4).toString();
        }
        if (length > 2) {
            return getLogSafeMessage(charSequence, 1, 1).toString();
        }
        return charSequence.toString();
    }
}
