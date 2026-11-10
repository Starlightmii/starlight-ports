package com.oplus.systemui.navigationbar.gesture;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.providers.AppSettings;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AppSettingsHandler {
    public static volatile AppSettingsHandler sInstance;
    public final Executor mBgExecutor;
    public final AnonymousClass1 mContentObserver;
    public final Context mContext;
    public volatile int mDisableFlags;
    public final HashMap mDisableFlagsMap;
    public volatile String mDisablePkg;
    public volatile int mGestureRegionReduceEnableFlag;
    public final HashMap mGestureRegionReduceEnableMap;
    public volatile String mGestureRegionReduceEnablePkg;
    public final AnonymousClass1 mGestureRegionReduceEnableStateObserver;
    public volatile boolean mRegistered;

    public static void $r8$lambda$yN_SWR9ER6LInxdglP6do805j_o(AppSettingsHandler appSettingsHandler) {
        if (appSettingsHandler.mRegistered) {
            return;
        }
        String disableStateFromSettings = appSettingsHandler.getDisableStateFromSettings();
        String gestureRegionReduceEnableState = appSettingsHandler.getGestureRegionReduceEnableState();
        LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", CursorUtil$.ExternalSyntheticOutline0.m("register enter,disableSettings = ", disableStateFromSettings, "gestureRegionReduceEnableSettings = ", gestureRegionReduceEnableState));
        LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "disableFlagsStringTokenizer, str = " + disableStateFromSettings);
        updateMapFromString(disableStateFromSettings, appSettingsHandler.mDisableFlagsMap);
        LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "gestureRegionReduceStringTokenizer, str = " + gestureRegionReduceEnableState);
        updateMapFromString(gestureRegionReduceEnableState, appSettingsHandler.mGestureRegionReduceEnableMap);
        appSettingsHandler.setDisable();
        appSettingsHandler.setGestureRegionReduceEnable();
        ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
        Uri uri = AppSettings.Nonpersist.CONTENT_URI;
        observerDispatcher.registerObserver(Uri.withAppendedPath(uri, "disable_gestures"), appSettingsHandler.mContentObserver, -1);
        ObserverDispatcher.getInstance().registerObserver(Uri.withAppendedPath(uri, "oplus_gesture_region_reduce_enable"), appSettingsHandler.mGestureRegionReduceEnableStateObserver, -1);
        appSettingsHandler.mRegistered = true;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.navigationbar.gesture.AppSettingsHandler$1] */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.oplus.systemui.navigationbar.gesture.AppSettingsHandler$1] */
    public AppSettingsHandler(Context context) {
        Handler handler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        final int i = 0;
        this.mRegistered = false;
        this.mGestureRegionReduceEnableStateObserver = new ContentObserver(this, handler) { // from class: com.oplus.systemui.navigationbar.gesture.AppSettingsHandler.1
            public final /* synthetic */ AppSettingsHandler this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        String gestureRegionReduceEnableState = this.this$0.getGestureRegionReduceEnableState();
                        LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "onChange,mGestureRegionReduceEnableSettings = " + gestureRegionReduceEnableState);
                        AppSettingsHandler appSettingsHandler = this.this$0;
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("gestureRegionReduceStringTokenizer, str = ", gestureRegionReduceEnableState, LogUtil.TAG_NAVBAR, "AppSettingsHandler");
                        AppSettingsHandler.updateMapFromString(gestureRegionReduceEnableState, appSettingsHandler.mGestureRegionReduceEnableMap);
                        this.this$0.setGestureRegionReduceEnable();
                        break;
                    default:
                        String disableStateFromSettings = this.this$0.getDisableStateFromSettings();
                        LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "onChange, disableSettings = " + disableStateFromSettings);
                        AppSettingsHandler appSettingsHandler2 = this.this$0;
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("disableFlagsStringTokenizer, str = ", disableStateFromSettings, LogUtil.TAG_NAVBAR, "AppSettingsHandler");
                        AppSettingsHandler.updateMapFromString(disableStateFromSettings, appSettingsHandler2.mDisableFlagsMap);
                        this.this$0.setDisable();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mContentObserver = new ContentObserver(this, handler) { // from class: com.oplus.systemui.navigationbar.gesture.AppSettingsHandler.1
            public final /* synthetic */ AppSettingsHandler this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        String gestureRegionReduceEnableState = this.this$0.getGestureRegionReduceEnableState();
                        LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "onChange,mGestureRegionReduceEnableSettings = " + gestureRegionReduceEnableState);
                        AppSettingsHandler appSettingsHandler = this.this$0;
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("gestureRegionReduceStringTokenizer, str = ", gestureRegionReduceEnableState, LogUtil.TAG_NAVBAR, "AppSettingsHandler");
                        AppSettingsHandler.updateMapFromString(gestureRegionReduceEnableState, appSettingsHandler.mGestureRegionReduceEnableMap);
                        this.this$0.setGestureRegionReduceEnable();
                        break;
                    default:
                        String disableStateFromSettings = this.this$0.getDisableStateFromSettings();
                        LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "onChange, disableSettings = " + disableStateFromSettings);
                        AppSettingsHandler appSettingsHandler2 = this.this$0;
                        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("disableFlagsStringTokenizer, str = ", disableStateFromSettings, LogUtil.TAG_NAVBAR, "AppSettingsHandler");
                        AppSettingsHandler.updateMapFromString(disableStateFromSettings, appSettingsHandler2.mDisableFlagsMap);
                        this.this$0.setDisable();
                        break;
                }
            }
        };
        this.mContext = context;
        this.mDisableFlagsMap = new HashMap();
        this.mGestureRegionReduceEnableMap = new HashMap();
        this.mBgExecutor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
    }

    private int getDisableFlags() {
        return getFlags("disableFlags", this.mDisableFlagsMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDisableStateFromSettings() {
        try {
            return AppSettings.Nonpersist.sNameValueCache.getStringForUser(this.mContext.getContentResolver(), "disable_gestures", KeyguardUpdateMonitor.getCurrentUser());
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("fail to getDisableState from appSettings "), LogUtil.TAG_NAVBAR, "AppSettingsHandler");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getGestureRegionReduceEnableState() {
        try {
            return AppSettings.Nonpersist.sNameValueCache.getStringForUser(this.mContext.getContentResolver(), "oplus_gesture_region_reduce_enable", KeyguardUpdateMonitor.getCurrentUser());
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("fail to getDisableState from appSettings "), LogUtil.TAG_NAVBAR, "AppSettingsHandler");
            return null;
        }
    }

    public static AppSettingsHandler getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppSettingsHandler.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new AppSettingsHandler(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private int getOplusGestureRegionReduceEnableFlag() {
        return getFlags("oplusGestureRegionReduceEnableFlag", this.mGestureRegionReduceEnableMap);
    }

    private void setDisableFlag(int i) {
        NavBarSettingsValueProxy.setDisableState(this.mContext, i);
    }

    private void setGestureRegionReduceEnableFlag(int i) {
        NavBarSettingsValueProxy.setGestureRegionReduceEnableState(this.mContext, i);
    }

    public static void updateMapFromString(String str, Map map) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        StringBuffer stringBuffer = new StringBuffer("");
        int i = 0;
        String str2 = null;
        while (stringTokenizer.hasMoreElements()) {
            String strNextToken = stringTokenizer.nextToken();
            if (i == 0) {
                str2 = strNextToken;
            } else if (i == 1) {
                stringBuffer.append(strNextToken);
            } else if (i == 2) {
                stringBuffer.append(" " + strNextToken);
            }
            i++;
        }
        map.put(str2, stringBuffer.toString());
        LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "updateMapFromString out, values = " + stringBuffer.toString());
    }

    public final int getFlags(String str, Map map) {
        Iterator it = ((HashMap) map).entrySet().iterator();
        String str2 = null;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            String str3 = (String) ((Map.Entry) it.next()).getValue();
            if (str3 != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(str3);
                int i3 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    String strNextToken = stringTokenizer.nextToken();
                    LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", str + ", value = " + strNextToken);
                    if (strNextToken != null && !strNextToken.isEmpty()) {
                        if (i3 == 0) {
                            try {
                                LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", str + " case 0, getInteger = {" + Integer.parseInt(strNextToken) + "}");
                                i2 |= Integer.parseInt(strNextToken);
                            } catch (NumberFormatException e) {
                                LogUtil.e(LogUtil.TAG_NAVBAR, "AppSettingsHandler", "fail to parseInt " + e.getMessage());
                            }
                        } else if (i3 == 1) {
                            str2 = strNextToken;
                        }
                    }
                    i3++;
                }
                if (i3 == 1) {
                    if ("disableFlags".equals(str)) {
                        this.mDisablePkg = str2;
                    }
                    if ("oplusGestureRegionReduceEnableFlag".equals(str)) {
                        this.mGestureRegionReduceEnablePkg = str2;
                    }
                } else if (str2 != null) {
                    if ("disableFlags".equals(str)) {
                        this.mDisablePkg = str2;
                    }
                    if ("oplusGestureRegionReduceEnableFlag".equals(str)) {
                        this.mGestureRegionReduceEnablePkg = str2;
                    }
                }
                i = i2;
            }
        }
        LogUtil.d(LogUtil.TAG_NAVBAR, "AppSettingsHandler", str + " out, flag = " + i + " pkg = " + str2);
        return i;
    }

    public void setCurrentTopPackageName(String str) {
        if (this.mDisablePkg != null) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("setCurrentTopPackageName: ", str, " mDisablePkg: ");
            sbM.append(this.mDisablePkg);
            LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", sbM.toString());
            boolean zEquals = str.equals(this.mDisablePkg);
            int i = this.mDisableFlags;
            if (zEquals) {
                if (i == 0) {
                    setDisable();
                }
            } else if (i != 0) {
                this.mDisableFlags = 0;
                setDisableFlag(this.mDisableFlags);
                EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mDisableFlags, LogUtil.TAG_NAVBAR, "AppSettingsHandler", new StringBuilder("resetDisableFlag,mDisableFlags = "));
            }
        }
    }

    public final void setDisable() {
        this.mDisableFlags = getDisableFlags();
        setDisableFlag(this.mDisableFlags);
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mDisableFlags, LogUtil.TAG_NAVBAR, "AppSettingsHandler", new StringBuilder("setDisable out,mDisableFlags = "));
    }

    public final void setGestureRegionReduceEnable() {
        this.mGestureRegionReduceEnableFlag = getOplusGestureRegionReduceEnableFlag();
        setGestureRegionReduceEnableFlag(this.mGestureRegionReduceEnableFlag);
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mDisableFlags, LogUtil.TAG_NAVBAR, "AppSettingsHandler", new StringBuilder("setDisable out,mDisableFlags = "));
    }

    public void setGestureRegionReduceEnablePkgName(String str) {
        if (this.mGestureRegionReduceEnablePkg != null) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("setGestureRegionReduceEnablePkgName: ", str, ",mGestureRegionReduceEnablePkg: ");
            sbM.append(this.mGestureRegionReduceEnablePkg);
            LogUtil.i(LogUtil.TAG_NAVBAR, "AppSettingsHandler", sbM.toString());
            boolean zEquals = str.equals(this.mGestureRegionReduceEnablePkg);
            int i = this.mGestureRegionReduceEnableFlag;
            if (zEquals) {
                if (i == 0) {
                    setGestureRegionReduceEnable();
                }
            } else if (i != 0) {
                this.mGestureRegionReduceEnableFlag = 0;
                setGestureRegionReduceEnableFlag(this.mGestureRegionReduceEnableFlag);
                EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mDisableFlags, LogUtil.TAG_NAVBAR, "AppSettingsHandler", new StringBuilder("resetGestureRegionReduceEnableFlag,mGestureRegionReduceEnableFlag = "));
            }
        }
    }
}
