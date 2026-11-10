package com.oplus.systemui.statusbar.util;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.OplusWifiManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WifiUtils {
    private static final String DUAL_STA_SETTINGS_ENABLED = "dual_sta_switch_on";
    public static final WifiUtils INSTANCE = new WifiUtils();
    private static final String PASSPOINT_STATE = "wifi_passpoint_enabled_state";
    private static final String TAG = "WifiUtils";
    private static OplusWifiManager sOplusWifiManager;
    private static Boolean sSupportPasspoint;
    private static WifiManager sWifiManager;

    public static final WifiInfo getOplusSta2ConnectionInfo(Context context) {
        OplusWifiManager oplusWifiManagerInstance = getOplusWifiManagerInstance(context);
        if (oplusWifiManagerInstance != null) {
            try {
                Class[] clsArr = new Class[0];
                Method method = oplusWifiManagerInstance.getClass().getMethod("getOplusSta2ConnectionInfo", null);
                if (method != null) {
                    WifiInfo wifiInfo = (WifiInfo) method.invoke(oplusWifiManagerInstance, null);
                    if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        return wifiInfo;
                    }
                    Log.d("SystemUi--Statusbar", "WifiUtils-->".concat("getOplusSta2ConnectionInfo, info = isPrimary: " + Boolean.valueOf(wifiInfo.isPrimary())));
                    return wifiInfo;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "WifiUtils-->getOplusSta2ConnectionInfo, not found method");
                    return null;
                }
            } catch (Error e) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "getOplusSta2ConnectionInfo. error = ", e);
            } catch (Exception e2) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "getOplusSta2ConnectionInfo, exception = ", e2);
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "WifiUtils-->getOplusSta2ConnectionInfo, wifimanager = null.");
            return null;
        }
        return null;
    }

    public static final OplusWifiManager getOplusWifiManagerInstance(Context context) {
        OplusWifiManager oplusWifiManager = sOplusWifiManager;
        if (oplusWifiManager != null) {
            return oplusWifiManager;
        }
        try {
            Constructor<?> declaredConstructor = Class.forName("android.net.wifi.OplusWifiManager").getDeclaredConstructor(Context.class);
            declaredConstructor.setAccessible(true);
            sOplusWifiManager = (OplusWifiManager) declaredConstructor.newInstance(context);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "getOplusWifiManagerInstance exception = " + e);
        }
        return sOplusWifiManager;
    }

    public static final WifiManager getWifiManagerInstance(Context context) {
        WifiManager wifiManager = sWifiManager;
        if (wifiManager != null) {
            return wifiManager;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            Object systemService = applicationContext.getSystemService(OplusQSHighlightTileViewLabelTextHandler.WIFI);
            sWifiManager = systemService instanceof WifiManager ? (WifiManager) systemService : null;
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "WifiUtils-->getWifiManagerInstance, application = null.");
        }
        return sWifiManager;
    }

    public static final boolean isDualStaSupport(Context context) {
        OplusWifiManager oplusWifiManagerInstance = getOplusWifiManagerInstance(context);
        if (oplusWifiManagerInstance != null) {
            try {
                Method declaredMethod = oplusWifiManagerInstance.getClass().getDeclaredMethod("isFeatureSupported", Long.TYPE);
                declaredMethod.setAccessible(true);
                boolean zBooleanValue = ((Boolean) declaredMethod.invoke(oplusWifiManagerInstance, 8L)).booleanValue();
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    return zBooleanValue;
                }
                Log.d("SystemUi--Statusbar", "WifiUtils-->".concat("isDualStaSupport, supported = " + zBooleanValue));
                return zBooleanValue;
            } catch (Error e) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "isDualStaSupport, error= ", e);
            } catch (Exception e2) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "isDualStaSupport, exception= ", e2);
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "WifiUtils-->isDualStaSupport, oplusWifiManager = null.");
            return false;
        }
        return false;
    }

    public static final boolean isDualWifiConnected(Context context) {
        WifiManager wifiManagerInstance;
        if (context == null || (wifiManagerInstance = getWifiManagerInstance(context)) == null) {
            return false;
        }
        try {
            if (!isDualStaSupport(context)) {
                return false;
            }
            try {
                if (Settings.Global.getInt(context.getContentResolver(), DUAL_STA_SETTINGS_ENABLED, 1) != 1) {
                    return false;
                }
                WifiInfo connectionInfo = wifiManagerInstance.getConnectionInfo();
                WifiInfo oplusSta2ConnectionInfo = getOplusSta2ConnectionInfo(context);
                if (connectionInfo == null || oplusSta2ConnectionInfo == null || "<unknown ssid>".equals(oplusSta2ConnectionInfo.getSSID())) {
                    return false;
                }
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    return true;
                }
                Log.d("SystemUi--Statusbar", "WifiUtils-->isDualWifiConnected = true");
                return true;
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "isDualStaEnabled, exception= ", e);
                return false;
            }
        } catch (Exception e2) {
            LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "isDualWifiConnected, exception= ", e2);
            return false;
        }
    }

    public static final boolean isHomeProviderNetwork(Context context) {
        if (context == null) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "Context is null isHomeProviderNetwork.");
            return false;
        }
        WifiManager wifiManagerInstance = getWifiManagerInstance(context);
        if (wifiManagerInstance != null) {
            WifiInfo connectionInfo = wifiManagerInstance.getConnectionInfo();
            if (connectionInfo != null) {
                List<WifiConfiguration> privilegedConfiguredNetworks = wifiManagerInstance.getPrivilegedConfiguredNetworks();
                privilegedConfiguredNetworks.getClass();
                for (WifiConfiguration wifiConfiguration : privilegedConfiguredNetworks) {
                    if (wifiConfiguration.isPasspoint() && wifiConfiguration.SSID.equals(connectionInfo.getSSID())) {
                        boolean z = wifiConfiguration.isHomeProviderNetwork;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("isHomeProviderNetwork = ", "WifiUtils-->", "SystemUi--Statusbar", z);
                        }
                        return z;
                    }
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "WifiUtils-->isHomeProviderNetwork, wifiInfo == null");
                return false;
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "WifiUtils-->isHomeProviderNetwork, wifimanager = null.");
            return false;
        }
        return false;
    }

    public static final boolean isPassPointAp(Context context) {
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.isSupportPasspoint) {
            if (context == null) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "Context is null isPasspointAp.");
                return false;
            }
            if (Settings.Global.getInt(context.getContentResolver(), PASSPOINT_STATE, 1) == 1) {
                WifiManager wifiManagerInstance = getWifiManagerInstance(context);
                if (wifiManagerInstance != null) {
                    WifiInfo connectionInfo = wifiManagerInstance.getConnectionInfo();
                    if (connectionInfo != null) {
                        return connectionInfo.isPasspointAp();
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "WifiUtils-->getOplusSta2ConnectionInfo, wifimanager = null.");
                    return false;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "WifiUtils-->isPasspointStateEnabled = false");
            }
        }
        return false;
    }

    public static final boolean isWifiStandardSupported(Context context, int i) {
        WifiManager wifiManagerInstance = getWifiManagerInstance(context);
        if (wifiManagerInstance != null) {
            return wifiManagerInstance.isWifiStandardSupported(i);
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
            return false;
        }
        Log.d("SystemUi--QuickSettings", "WifiUtils-->isWifiStandardSupported, wifimanager = null.");
        return false;
    }

    public static final boolean isWifiStateConnected(String str, NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("isWifiStateConnected, info= " + networkInfo);
            Log.d("SystemUi--Statusbar", sbM.toString());
        }
        return networkInfo.getType() == 1 && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED;
    }

    public static final void setWifiLowLatencyEnabled(Context context, boolean z) {
        if (context == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "WifiUtils-->setWifiLowLatencyEnabled, Context is null .");
                return;
            }
            return;
        }
        OplusWifiManager oplusWifiManagerInstance = getOplusWifiManagerInstance(context);
        if (oplusWifiManagerInstance != null) {
            ReflectionUtils.methodInvoke(oplusWifiManagerInstance, "setWifiLowLatencyEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "WifiUtils-->setWifiLowLatencyEnabled, oplusWifiManager = null.");
        }
    }
}
