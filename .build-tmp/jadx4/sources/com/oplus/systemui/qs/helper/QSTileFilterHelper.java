package com.oplus.systemui.qs.helper;

import android.content.Context;
import android.util.Log;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplusos.systemui.common.feature.ChargingFeatureOption;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileFilterHelper {
    public static final String AUTO_SCREEN_TEMPERATURE = "custom(com.android.settings/com.oplus.settings.feature.display.nightmode.AutoScreenTemperatureTileService)";
    public static final String DISABLE_WINDOW_TILE = "custom(com.microsoft.appmanager/.quicksettings.WindowsLinkTileService)";
    private static final String FLOAT_ASSISTANT = "custom(com.coloros.floatassistant/.FloatAssistantTileService)";
    public static final QSTileFilterHelper INSTANCE = new QSTileFilterHelper();
    private static final String IRIS5 = "custom(com.android.settings/com.oplus.settings.feature.display.video.Iris5TileService)";
    private static final String OSIE = "custom(com.android.settings/com.oplus.settings.feature.display.video.OsieTileService)";
    public static final String SPACE_GESTRUE_TILE = "custom(com.oplus.gesture/.spacegesture.SpaceGestureSwitchTileService)";
    private static final String TAG = "QSTileFilterHelper";
    private static final String WIRELESS_CHARGE = "custom(com.oplus.battery/com.oplus.powermanager.wirelesscharg.WirelessReverseControService)";

    public final boolean filterRemoveSpecs(Context context, String str) {
        if (Intrinsics.areEqual(str, context.getString(2131956481))) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isSmartDriveDisable()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove smart drive");
                return true;
            }
        } else if (Intrinsics.areEqual(str, AUTO_SCREEN_TEMPERATURE)) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isTemperatureSupport()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove auto screen temperature");
                return true;
            }
        } else if (Intrinsics.areEqual(str, WIRELESS_CHARGE)) {
            ChargingFeatureOption.INSTANCE.getClass();
            if (ChargingFeatureOption.isSupportWirelessCharging) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove wireless charge");
                return true;
            }
        } else if (Intrinsics.areEqual(str, IRIS5)) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isIris5ModeSupport()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove iris 5");
                return true;
            }
        } else if (Intrinsics.areEqual(str, OSIE)) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isIris5ModeSupport() && QSFeatureOption.isOsieModeSupport()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove osie");
                return true;
            }
        } else if (Intrinsics.areEqual(str, SPACE_GESTRUE_TILE)) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove space gestrue");
                return true;
            }
        } else if (Intrinsics.areEqual(str, SPACE_GESTRUE_TILE)) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isSupportActiveSpaceGesture()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove space gestrue");
                return true;
            }
        } else if (Intrinsics.areEqual(str, DISABLE_WINDOW_TILE)) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (!FlavorOneFeatureOption.isFlavorOneDevice() || !FlavorOneFeatureOption.sIsDisableWindowTile) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "QSTileFilterHelper-->remove window tile");
                return true;
            }
        } else if (Intrinsics.areEqual(str, FLOAT_ASSISTANT)) {
            if (!CustomizeFeatureOption.sFloatAssistantFeature) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove float assistant");
                return true;
            }
        } else {
            if (!Intrinsics.areEqual(str, "custom(com.oplus.screenrecorder/.floatwindow.services.QuickPanelService)")) {
                return false;
            }
            ScreenRecorderTile.Companion.getClass();
            if (!ScreenRecorderTile.Companion.useSysRecordTile()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "QSTileFilterHelper-->remove screen record custom tile");
            }
        }
        return true;
    }
}
