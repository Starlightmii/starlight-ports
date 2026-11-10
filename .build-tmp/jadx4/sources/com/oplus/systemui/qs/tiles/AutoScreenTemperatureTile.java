package com.oplus.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.SystemSettings;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.external.InnerCustomTileConnection;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AutoScreenTemperatureTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String SETTING_AUTO_SCREEN_TEMPERATURE = "setting_enable_color_temperature_regulation";
    public static final String TILE_SPEC = "autoscreentemperature";
    private final InnerCustomTileConnection customTileConnection;
    public final ComponentName mComponentName;
    private final AutoScreenTemperatureTile$mObserver$1 mObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile$mObserver$1] */
    public AutoScreenTemperatureTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, Context context, InnerCustomTileConnection.Factory factory, final SystemSettings systemSettings) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        ComponentName componentFromSpec = CustomTile.getComponentFromSpec(QSTileFilterHelper.AUTO_SCREEN_TEMPERATURE);
        this.mComponentName = componentFromSpec;
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = qSHost.getUserContext().getUserId();
        this.mObserver = new UserSettingObserver(systemSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile$mObserver$1
            public final void handleValueChanged(int i, boolean z) {
                LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleValueChanged value = ", i, ", changed = ", z), null, 8);
                this.refreshState((Object) null);
            }
        };
        this.customTileConnection = factory.create(TILE_SPEC, componentFromSpec, context.getUser());
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.println("AutoScreenTemperatureTile Data:");
        printWriter.println("AutoScreenTemperatureTile.mObserver Data:");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("mObserver.value = ", getValue(), printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "mObserver.isListening = ", ((UserSettingObserver) this.mObserver).mListening);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("mObserver.currentUser = ", getCurrentUser(), printWriter);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237282);
    }

    public Intent getLongClickIntent() {
        Intent intentResolveIntent = QsUtil.resolveIntent(((QSTileImpl) this).mContext, new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES").setPackage(this.mComponentName.getPackageName()));
        if (intentResolveIntent == null) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mComponentName.getPackageName(), null));
        }
        intentResolveIntent.putExtra("android.intent.extra.COMPONENT_NAME", this.mComponentName);
        return intentResolveIntent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955175);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        this.customTileConnection.onClick();
    }

    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
    }

    public final void handleInitialize() {
        setListening(true);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        this.customTileConnection.setListening(z);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int value = getValue();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(value, "handleUpdateState settingsProviderValue = ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
        }
        boolean z = value == 1;
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(z ? 2131237283 : 2131237282);
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955175);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
        if (value == -1) {
            this.customTileConnection.onClick();
        }
    }

    public final void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        setUserId(i);
    }

    public final boolean isAvailable() {
        return this.customTileConnection.checkComponentState();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m312newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_screen_temperature_lottie";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
