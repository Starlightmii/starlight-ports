package com.oplus.systemui.qs.impl;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.customize.OplusCustomizeConnectivityManager;
import android.util.ArraySet;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.systemui.DependencyEx;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.qs.OplusAccessPointControllerEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.connectivity.OplusQSNetworkControllerEx;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import com.oplus.systemui.qs.utils.QsWifiIcons;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusAccessPointControllerExImpl extends OplusAccessPointControllerEx {
    public final Context mContext;
    public boolean mDeviceSupportWiFi7 = false;
    public final WeakHashMap mAccessPointState = new WeakHashMap();
    public final WeakHashMap mAPState = new WeakHashMap();

    public OplusAccessPointControllerExImpl(Context context) {
        this.mContext = context;
    }

    public final void clear() {
        this.mAccessPointState.clear();
        this.mAPState.clear();
    }

    public final boolean connect(AccessPoint accessPoint, WifiManager.ActionListener actionListener, WifiTracker wifiTracker) {
        if (accessPoint.getConfig().networkId >= 0) {
            return false;
        }
        LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusAccessPointControllerExt", "networkId is negative in saved ap");
        if (!accessPoint.isPasspoint()) {
            return true;
        }
        LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusAccessPointControllerExt", "connect passpoint");
        wifiTracker.getManager().connect(accessPoint.getConfig(), actionListener);
        return true;
    }

    public final List filterUnsavedAPs(List list) {
        if (!OplusCustomizeConnectivityManager.getInstance(this.mContext).isWifiEditDisabled()) {
            return list;
        }
        LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusAccessPointControllerExt", "Enterprise customization isWifiEditDisabled = true, filter unsaved APs");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AccessPoint accessPoint = (AccessPoint) it.next();
            if (accessPoint.isSaved()) {
                arrayList.add(accessPoint);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0061  */
    public final int getIcon(AccessPoint accessPoint) {
        int connectWifiStandard;
        int level = accessPoint.getLevel() >= 0 ? accessPoint.getLevel() : 0;
        OplusQSNetworkControllerEx qsNetworkControllerEx = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getQsNetworkControllerEx();
        if (accessPoint.isActive() && qsNetworkControllerEx.isWifiDouble()) {
            return 2131237280;
        }
        if (accessPoint.isPasspoint() || (accessPoint.mPasspointUniqueId != null && accessPoint.mConfig == null)) {
            if (CustomizeFeatureOption.sIsSupportCustomizePasspointIcon) {
                return accessPoint.getConfig().isHomeProviderNetwork ? QsWifiIcons.INSTANCE.getIconPassPointH()[level] : QsWifiIcons.INSTANCE.getIconPassPointR()[level];
            }
            return QsWifiIcons.INSTANCE.getIconPassPoint()[level];
        }
        if (qsNetworkControllerEx.isWifiTechnicalEnable()) {
            if (accessPoint.isActive()) {
                try {
                    NetworkInfo networkInfo = accessPoint.getNetworkInfo();
                    if (networkInfo == null || !networkInfo.isConnected()) {
                        connectWifiStandard = 0;
                    } else {
                        connectWifiStandard = qsNetworkControllerEx.getConnectWifiStandard();
                    }
                } catch (Exception unused) {
                    LogUtil.d(LogUtil.TAG_STATUSBAR, "OplusAccessPointControllerExt", "get ItemConnected failed");
                }
            } else {
                connectWifiStandard = 0;
            }
            if (connectWifiStandard <= 0) {
                AccessPointState accessPointState = (AccessPointState) this.mAPState.get(accessPoint);
                connectWifiStandard = accessPointState != null ? accessPointState.mWifiGeneration : 0;
            }
            if (connectWifiStandard == 6) {
                return !accessPoint.isOpenNetwork() ? QsWifiIcons.INSTANCE.getIconWifi6Locked()[level] : QsWifiIcons.INSTANCE.getIconWifi6Open()[level];
            }
            if (connectWifiStandard == 8) {
                if (this.mDeviceSupportWiFi7) {
                    return !accessPoint.isOpenNetwork() ? QsWifiIcons.INSTANCE.getIconWifi7Locked()[level] : QsWifiIcons.INSTANCE.getIconWifi7Open()[level];
                }
                return !accessPoint.isOpenNetwork() ? QsWifiIcons.INSTANCE.getIconWifi6Locked()[level] : QsWifiIcons.INSTANCE.getIconWifi6Open()[level];
            }
        }
        return !accessPoint.isOpenNetwork() ? QsWifiIcons.INSTANCE.getIconLocked()[level] : QsWifiIcons.INSTANCE.getIcon()[level];
    }

    public final Intent getWifiConnectIntent(AccessPoint accessPoint, Intent intent) {
        String string = this.mContext.getString(2131957289);
        if (string.isEmpty()) {
            return intent;
        }
        Intent intent2 = new Intent(string);
        Bundle bundle = new Bundle();
        if (accessPoint.ssid != null) {
            bundle.putString("key_ssid", accessPoint.getSsidStr());
        }
        bundle.putInt("key_security", accessPoint.security);
        bundle.putInt("key_speed", accessPoint.mSpeed);
        bundle.putInt("key_psktype", accessPoint.pskType);
        bundle.putInt("eap_psktype", accessPoint.mEapType);
        WifiConfiguration wifiConfiguration = accessPoint.mConfig;
        if (wifiConfiguration != null) {
            bundle.putParcelable("key_config", wifiConfiguration);
        }
        bundle.putParcelable("key_wifiinfo", accessPoint.mInfo);
        synchronized (accessPoint.mLock) {
            ArraySet arraySet = accessPoint.mScanResults;
            bundle.putParcelableArray("key_scanresults", (Parcelable[]) arraySet.toArray(new Parcelable[arraySet.size() + accessPoint.mExtraScanResults.size()]));
        }
        bundle.putParcelableArrayList("key_scorednetworkcache", new ArrayList<>(accessPoint.mScoredNetworkCache.values()));
        NetworkInfo networkInfo = accessPoint.mNetworkInfo;
        if (networkInfo != null) {
            bundle.putParcelable("key_networkinfo", networkInfo);
        }
        String str = accessPoint.mPasspointUniqueId;
        if (str != null) {
            bundle.putString("key_passpoint_unique_id", str);
        }
        String str2 = accessPoint.mFqdn;
        if (str2 != null) {
            bundle.putString("key_fqdn", str2);
        }
        String str3 = accessPoint.mProviderFriendlyName;
        if (str3 != null) {
            bundle.putString("key_provider_friendly_name", str3);
        }
        bundle.putLong("key_subscription_expiration_time_in_millis", accessPoint.mSubscriptionExpirationTimeInMillis);
        bundle.putInt("key_passpoint_configuration_version", accessPoint.mPasspointConfigurationVersion);
        bundle.putBoolean("key_is_psk_sae_transition_mode", accessPoint.mIsPskSaeTransitionMode);
        bundle.putBoolean("key_is_owe_transition_mode", accessPoint.mIsOweTransitionMode);
        bundle.putBoolean(this.mContext.getString(2131957293), false);
        bundle.putBoolean(this.mContext.getString(2131957292), false);
        bundle.putBoolean(this.mContext.getString(2131957294), false);
        bundle.putBoolean(this.mContext.getString(2131957295), false);
        bundle.putBoolean(this.mContext.getString(2131957291), false);
        intent2.putExtras(bundle);
        intent2.addFlags(268435456);
        return intent2;
    }

    public final boolean is24GHzWifi(WifiEntry wifiEntry) {
        AccessPointState accessPointState = (AccessPointState) this.mAccessPointState.get(wifiEntry);
        if (accessPointState != null) {
            return accessPointState.mIs24GHz;
        }
        return false;
    }

    public final boolean is5GHzWifi(WifiEntry wifiEntry) {
        AccessPointState accessPointState = (AccessPointState) this.mAccessPointState.get(wifiEntry);
        if (accessPointState != null) {
            return accessPointState.mIs5GHz;
        }
        return false;
    }

    public final boolean is6GHzWifi(AccessPoint accessPoint) {
        AccessPointState accessPointState = (AccessPointState) this.mAPState.get(accessPoint);
        if (accessPointState != null) {
            return accessPointState.mIs6GHz;
        }
        return false;
    }

    public final void isDeviceSupportWiFi7(boolean z) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("isDeviceSupportWiFi7: ", LogUtil.TAG_STATUSBAR, "OplusAccessPointControllerExt", z);
        this.mDeviceSupportWiFi7 = z;
    }

    public final boolean scanForAccessPoints(Handler handler, WifiPickerTracker wifiPickerTracker, WifiTracker wifiTracker, Function1 function1, OplusAccessPointControllerEx.ScanForAccessPointsCallback scanForAccessPointsCallback) {
        OplusAccessPointControllerExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusAccessPointControllerExImpl$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = wifiPickerTracker;
        externalSyntheticLambda0.f$2 = function1;
        externalSyntheticLambda0.f$3 = wifiTracker;
        externalSyntheticLambda0.f$4 = scanForAccessPointsCallback;
        externalSyntheticLambda0.f$5 = handler;
        VarHandle.storeStoreFence();
        handler.post(externalSyntheticLambda0);
        return true;
    }

    public final void updateAccessPointState(AccessPoint accessPoint) {
        boolean z;
        boolean z2;
        int i;
        Set<ScanResult> scanResults = accessPoint.getScanResults();
        AccessPointState accessPointState = new AccessPointState();
        boolean z3 = false;
        if (scanResults == null || scanResults.size() <= 0) {
            z = false;
            z2 = false;
            i = 0;
        } else {
            boolean z4 = false;
            z = false;
            z2 = false;
            i = 0;
            for (ScanResult scanResult : scanResults) {
                if (scanResult != null) {
                    int i2 = scanResult.frequency;
                    if (!z4) {
                        z4 = i2 >= 2412 && i2 <= 2484;
                    }
                    if (!z) {
                        z = i2 >= 5160 && i2 <= 5885;
                    }
                    if (this.mDeviceSupportWiFi7 && !z2) {
                        z2 = i2 == 5935 || (i2 >= 5955 && i2 <= 7115);
                    }
                    int wifiStandard = scanResult.getWifiStandard();
                    if (wifiStandard > i) {
                        i = wifiStandard;
                    }
                }
            }
            z3 = z4;
        }
        accessPointState.mIs24GHz = z3;
        accessPointState.mIs5GHz = z;
        accessPointState.mIs6GHz = z2;
        accessPointState.mWifiGeneration = i;
        this.mAPState.put(accessPoint, accessPointState);
    }

    public final boolean is24GHzWifi(AccessPoint accessPoint) {
        AccessPointState accessPointState = (AccessPointState) this.mAPState.get(accessPoint);
        if (accessPointState != null) {
            return accessPointState.mIs24GHz;
        }
        return false;
    }

    public final boolean is5GHzWifi(AccessPoint accessPoint) {
        AccessPointState accessPointState = (AccessPointState) this.mAPState.get(accessPoint);
        if (accessPointState != null) {
            return accessPointState.mIs5GHz;
        }
        return false;
    }

    public final void updateAccessPointState(WifiEntry wifiEntry) {
        AccessPointState accessPointState = new AccessPointState();
        accessPointState.mWifiGeneration = 0;
        this.mAccessPointState.put(wifiEntry, accessPointState);
    }
}
