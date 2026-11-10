package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.hardware.SensorPrivacyManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.RotationLockTile;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRotationLockTile extends RotationLockTile {
    private final RotationLockController.RotationLockControllerCallback callback;
    private final RotationLockController rotationLockController;
    public boolean rotationLocked;

    public OplusRotationLockTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, RotationLockController rotationLockController, SensorPrivacyManager sensorPrivacyManager, BatteryController batteryController, SecureSettings secureSettings) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, rotationLockController, sensorPrivacyManager, batteryController, secureSettings);
        this.rotationLockController = rotationLockController;
        OplusRotationLockTile$callback$1 oplusRotationLockTile$callback$1 = new OplusRotationLockTile$callback$1();
        oplusRotationLockTile$callback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.callback = oplusRotationLockTile$callback$1;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.dump(printWriter, strArr);
        printWriter.print("    ");
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "rotationLocked：", this.rotationLocked);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237208);
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.DISPLAY_SETTINGS");
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955844);
    }

    public void handleClick(Expandable expandable) {
        this.rotationLockController.mRotationPolicy.setRotationLock(((QSTile.AdapterState) ((QSTileImpl) this).mState).value, "RotationLockTile#handleClick");
        refreshState((Object) null);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        this.rotationLockController.removeCallback(this.callback);
    }

    public final void handleInitialize() {
        super.handleInitialize();
        this.rotationLockController.addCallback(this.callback);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        this.rotationLocked = bool != null ? bool.booleanValue() : this.rotationLockController.mRotationPolicy.isRotationLocked();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("handleUpdateState: argBoolean=" + bool + " rotationLocked=" + this.rotationLocked);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        ((QSTile.AdapterState) booleanState).value = !this.rotationLocked;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955844);
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(this.rotationLocked ? 2131237208 : 2131237207);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951940);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m338newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_rotation_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_RED_COLOR;
        return booleanState;
    }
}
