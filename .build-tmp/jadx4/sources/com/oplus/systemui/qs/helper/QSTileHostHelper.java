package com.oplus.systemui.qs.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.utils.OplusSwitchUserUtilEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.Prefs;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.tile.QSTileHostHelperEx;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.tiles.GoogleNowTile;
import com.oplus.systemui.qs.tiles.RingerModeTile;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile;
import com.oplus.systemui.qs.tiles.VibrationModeTile;
import com.oplus.systemui.qs.utils.AppQsTileBlackList;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileHostHelper extends QSTileHostHelperEx {
    public static final int FIRST_API_LEVEL = SystemProperties.getInt("ro.product.first_api_level", -1);
    public Context mContext;
    public QSHost mHost;
    public HashMap mTileMappingList;
    public HashMap mUpgradingMap;

    private List<String> getDfltUnsupportedTileString() {
        ArrayList arrayList = new ArrayList();
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isSupportsStealthMode()) {
            arrayList.add(RingerModeTile.TILE_SPEC);
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider()) {
                arrayList.add(RingerModeTile.TILE_SPEC);
                arrayList.add(VibrationModeTile.TILE_SPEC);
            } else {
                arrayList.add(VibrationModeTile.TILE_SPEC);
            }
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isPowerSaveCustomTileSupport) {
            arrayList.add("battery");
        } else {
            arrayList.add("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)");
        }
        ScreenRecorderTile.Companion.getClass();
        if (ScreenRecorderTile.Companion.useSysRecordTile()) {
            arrayList.add("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)");
            return arrayList;
        }
        arrayList.add(ScreenRecorderTile.TILE_SPEC);
        return arrayList;
    }

    public static boolean isComponentAvailable(Context context, ComponentName componentName) {
        try {
            OplusSwitchUserUtilEx oplusSwitchUserUtil = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusSwitchUserUtil();
            int targetUserId = oplusSwitchUserUtil.isMultiUserBeginSwitch() ? oplusSwitchUserUtil.getTargetUserId() : KeyguardUpdateMonitor.getCurrentUser();
            ServiceInfo serviceInfo = context.createContextAsUser(UserHandle.of(targetUserId), 0).getPackageManager().getServiceInfo(componentName, 786432);
            if (serviceInfo == null) {
                LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "Can't find component " + componentName + " id=" + targetUserId);
            }
            return serviceInfo != null;
        } catch (Exception unused) {
            LogUtil.w(LogUtil.TAG_QS, "QSTileHostHelper", "Can't find component " + componentName);
            return false;
        }
    }

    public final void checkDefaultCustomTileAvailable() {
        QSTileHostHelper$$ExternalSyntheticLambda0 qSTileHostHelper$$ExternalSyntheticLambda0 = new QSTileHostHelper$$ExternalSyntheticLambda0();
        qSTileHostHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        AsyncTask.execute(qSTileHostHelper$$ExternalSyntheticLambda0);
    }

    /* JADX WARN: Code duplicated, block: B:86:0x0196 A[PHI: r6
      0x0196: PHI (r6v7 boolean) = (r6v5 boolean), (r6v4 boolean) binds: [B:91:0x01a9, B:84:0x0193] A[DONT_GENERATE, DONT_INLINE]] */
    public final boolean filterSpecs(List list) {
        List<String> appQuickSettingsBlackList = AppQsTileBlackList.getAppQuickSettingsBlackList();
        String strJoin = TextUtils.join(OplusPreventModeData.SEPARATOR, getStockTileSpecs(this.mContext));
        if (list.contains("night")) {
            list.add(list.indexOf("night"), "custom(com.oplus.eyeprotect/.services.EyeProtectTileService)");
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isPowerSaveCustomTileSupport) {
            if (list.contains("battery")) {
                list.set(list.indexOf("battery"), "custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)");
            }
        } else if (list.contains("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)")) {
            list.set(list.indexOf("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)"), "battery");
        }
        if (isComponentAvailable(this.mContext, new ComponentName("com.oplus.linker", "com.oplus.linker.synergy.service.TVTileService")) && list.contains("cast")) {
            list.add(list.indexOf("cast"), "custom(com.oplus.linker/.synergy.service.TVTileService)");
        }
        if (list.contains("custom(com.oplus.eyeprotect/.services.AutoScreenTemperatureTileService)")) {
            list.add(list.indexOf("custom(com.oplus.eyeprotect/.services.AutoScreenTemperatureTileService)"), QSTileFilterHelper.AUTO_SCREEN_TEMPERATURE);
        }
        if (list.contains("dataswitch")) {
            list.add(list.indexOf("dataswitch"), "switchdata");
        }
        ScreenRecorderTile.Companion.getClass();
        if (ScreenRecorderTile.Companion.useSysRecordTile()) {
            if (list.contains("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)")) {
                list.set(list.indexOf("custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)"), ScreenRecorderTile.TILE_SPEC);
            }
        } else if (list.contains(ScreenRecorderTile.TILE_SPEC)) {
            list.set(list.indexOf(ScreenRecorderTile.TILE_SPEC), "custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)");
        }
        Context context = this.mContext;
        boolean z = false;
        if (context != null && !list.contains(QSTileFilterHelper.DISABLE_WINDOW_TILE) && !context.getSharedPreferences(context.getPackageName(), 0).getBoolean("QsHandledUpgrade", false) && FIRST_API_LEVEL < 34) {
            list.add(QSTileFilterHelper.DISABLE_WINDOW_TILE);
        }
        boolean z2 = true;
        if (!Prefs.getBoolean(this.mContext, "QsHandledUpgrade", false)) {
            Prefs.putBoolean(this.mContext, "QsHandledUpgrade", true);
        }
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (str.startsWith("custom(")) {
                String str2 = (String) this.mUpgradingMap.get(str);
                if (str2 != null) {
                    listIterator.set(str2);
                    z = true;
                    str = str2;
                }
                if (!AppInfoUtil.isPackageExist(this.mContext, str.substring(7, str.indexOf("/"))) || QSTileFilterHelper.INSTANCE.filterRemoveSpecs(this.mContext, str)) {
                    listIterator.remove();
                    z = true;
                } else if (this.mTileMappingList.containsKey(str)) {
                    listIterator.set((String) this.mTileMappingList.get(str));
                    z = true;
                }
            } else if (!strJoin.contains(str) || (appQuickSettingsBlackList != null && appQuickSettingsBlackList.contains(str))) {
                listIterator.remove();
                z = true;
            } else if (AppQsTileBlackList.isTabletBlackListTile(str)) {
                listIterator.remove();
                z = true;
            }
        }
        if (list.contains(GoogleNowTile.TILE_SPEC)) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isGoogleAssistantSupport) {
                list.remove(GoogleNowTile.TILE_SPEC);
                z = true;
            }
        }
        if (FeatureOption.INSTANCE.isDisablePowerSave(this.mContext) || QSFeatureOption.INSTANCE.isSuperPowerModeDisabled(this.mContext)) {
            if (list.contains("battery")) {
                list.remove("battery");
                z = true;
            }
            if (list.contains(SuperPowerSaveModeTile.TILE_SPEC)) {
                list.remove(SuperPowerSaveModeTile.TILE_SPEC);
            } else {
                z2 = z;
            }
        } else {
            z2 = z;
        }
        if (z2) {
            QsSettingsValueProxy.setTileSettings(this.mContext, TextUtils.join(OplusPreventModeData.SEPARATOR, list));
            LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "save tiles " + list + " blackList=" + appQuickSettingsBlackList);
        }
        return z2;
    }

    public final List getDefaultTileSpecs(Context context) {
        String string;
        Resources resources = context.getResources();
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isApplyLightOSQsTile) {
            LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "getDefaultTileSpecs isApplyLightOSQsTile");
            string = resources.getString(2131955865);
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet5G()) {
                LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "getDefaultTileSpecs isTablet5G");
                string = resources.getString(2131955862);
            } else if (FeatureOption.isTabletWifi()) {
                LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "getDefaultTileSpecs isTabletWifi");
                string = resources.getString(2131955863);
            } else {
                LogUtil.i(LogUtil.TAG_QS, "QSTileHostHelper", "getDefaultTileSpecs default");
                string = resources.getString(2131955860);
            }
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(string.split(OplusPreventModeData.SEPARATOR)));
        Iterator<String> it = getDfltUnsupportedTileString().iterator();
        while (it.hasNext()) {
            arrayList.remove(it.next());
        }
        return arrayList;
    }

    public final String getDefaultTileString(Context context) {
        return TextUtils.join(OplusPreventModeData.SEPARATOR, getDefaultTileSpecs(context));
    }

    public final List getExtraTileSpecs(Context context) {
        ArrayList arrayList = new ArrayList(Arrays.asList(context.getString(2131955864).split(OplusPreventModeData.SEPARATOR)));
        Iterator<String> it = getDfltUnsupportedTileString().iterator();
        while (it.hasNext()) {
            arrayList.remove(it.next());
        }
        return arrayList;
    }

    public final List getStockTileSpecs(Context context) {
        ArrayList arrayList = new ArrayList(Arrays.asList(context.getString(2131955868).split(OplusPreventModeData.SEPARATOR)));
        Iterator<String> it = getDfltUnsupportedTileString().iterator();
        while (it.hasNext()) {
            arrayList.remove(it.next());
        }
        return arrayList;
    }
}
