package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.BatterySaverTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBatterySaverTile extends BatterySaverTile {
    private static final String BATTERY_PACKAGE_NAME = "com.oplus.battery";
    public static final Companion Companion = new Companion();
    private static final String POWER_SAVE_CLASS_NAME = "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity";
    private static final String SETTINGS_BATTERY_MODE_PREF = "battery_mode_pref";
    private static final String SETTINGS_FRAGMENT_ARGS_KEY = ":settings:fragment_args_key";

    public OplusBatterySaverTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BatteryController batteryController, SecureSettings secureSettings) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, batteryController, secureSettings);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237094);
    }

    public Intent getLongClickIntent() {
        Intent longClickIntent = super.getLongClickIntent();
        if (longClickIntent != null) {
            longClickIntent.setClassName(BATTERY_PACKAGE_NAME, POWER_SAVE_CLASS_NAME);
            longClickIntent.putExtra(SETTINGS_FRAGMENT_ARGS_KEY, SETTINGS_BATTERY_MODE_PREF);
        }
        return longClickIntent;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956566);
    }

    public void handleClick(Expandable expandable) {
        super.handleClick(expandable);
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleClick state=", ((QSTile.State) getState()).state, ", mPowerSave=", ((BatterySaverTile) this).mPowerSave), null, 8);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i;
        int i2 = ((BatterySaverTile) this).mPowerSave ? 2 : 1;
        ((QSTile.State) booleanState).state = i2;
        if (i2 != 1) {
            i = i2 != 2 ? 2131237095 : 2131237093;
        } else {
            i = 2131237094;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(i);
        String string = ((QSTileImpl) this).mContext.getString(2131956566);
        ((QSTile.State) booleanState).label = string;
        ((QSTile.State) booleanState).contentDescription = string;
        ((QSTile.AdapterState) booleanState).value = ((BatterySaverTile) this).mPowerSave;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean isAvailable() {
        if (FeatureOption.INSTANCE.isDisablePowerSave(((QSTileImpl) this).mContext)) {
            return false;
        }
        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
        if (qSFeatureOption.isSuperPowerModeDisabled(((QSTileImpl) this).mContext)) {
            return false;
        }
        qSFeatureOption.getClass();
        return !QSFeatureOption.isPowerSaveCustomTileSupport;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m327newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_battery_lottie";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_YELLOW_COLOR;
        return booleanState;
    }

    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("onBatteryLevelChanged level=", i, ", pluggedIn=", ", charging=", z);
        sbM.append(z2);
        LogUtil.i$default(LogUtil.TAG_QS, str, sbM.toString(), null, 8);
        super.onBatteryLevelChanged(i, z, z2);
    }

    public final void onPowerSaveChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onPowerSaveChanged isPowerSave=", z), null, 8);
        super.onPowerSaveChanged(z);
    }
}
