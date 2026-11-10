package com.oplus.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.qs.external.InnerCustomTileConnection;
import com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver;
import com.oplus.systemui.qs.utils.Iris5Util;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Iris5Tile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "lris";
    private final InnerCustomTileConnection customTileConnection;
    public final ComponentName mComponentName;
    private final Iris5SwitchAndTemperatureControlObserver.Iris5MotionFluencySwitchCallback mIris5MotionFluencySwitchCallback;
    private final Iris5SwitchAndTemperatureControlObserver.Iris5VideoDisplaySwitchCallback mIris5VideoDisplaySwitchCallback;
    private final Iris5SwitchAndTemperatureControlObserver.Iris5VideoSuperResolutionSwitchCallback mIris5VideoSuperResolutionSwitchCallback;
    private final Iris5SwitchAndTemperatureControlObserver.PowerTemperatureControlFluencyCallback mPowerTemperatureControlFluencyCallback;
    private final Iris5SwitchAndTemperatureControlObserver.PowerTemperatureControlOsieCallback mPowerTemperatureControlOsieCallback;
    private final Iris5SwitchAndTemperatureControlObserver.PowerTemperatureControlVideosrCallback mPowerTemperatureControlVideosrCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public Iris5Tile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, Context context, InnerCustomTileConnection.Factory factory) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        ComponentName componentFromSpec = CustomTile.getComponentFromSpec("custom(com.android.settings/com.oplus.settings.feature.display.video.Iris5TileService)");
        this.mComponentName = componentFromSpec;
        this.customTileConnection = factory.create(TILE_SPEC, componentFromSpec, context.getUser());
        Iris5Tile$mPowerTemperatureControlVideosrCallback$1 iris5Tile$mPowerTemperatureControlVideosrCallback$1 = new Iris5Tile$mPowerTemperatureControlVideosrCallback$1();
        iris5Tile$mPowerTemperatureControlVideosrCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPowerTemperatureControlVideosrCallback = iris5Tile$mPowerTemperatureControlVideosrCallback$1;
        Iris5Tile$mPowerTemperatureControlOsieCallback$1 iris5Tile$mPowerTemperatureControlOsieCallback$1 = new Iris5Tile$mPowerTemperatureControlOsieCallback$1();
        iris5Tile$mPowerTemperatureControlOsieCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPowerTemperatureControlOsieCallback = iris5Tile$mPowerTemperatureControlOsieCallback$1;
        Iris5Tile$mPowerTemperatureControlFluencyCallback$1 iris5Tile$mPowerTemperatureControlFluencyCallback$1 = new Iris5Tile$mPowerTemperatureControlFluencyCallback$1();
        iris5Tile$mPowerTemperatureControlFluencyCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPowerTemperatureControlFluencyCallback = iris5Tile$mPowerTemperatureControlFluencyCallback$1;
        Iris5Tile$mIris5VideoDisplaySwitchCallback$1 iris5Tile$mIris5VideoDisplaySwitchCallback$1 = new Iris5Tile$mIris5VideoDisplaySwitchCallback$1();
        iris5Tile$mIris5VideoDisplaySwitchCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mIris5VideoDisplaySwitchCallback = iris5Tile$mIris5VideoDisplaySwitchCallback$1;
        Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1 iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1 = new Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1();
        iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mIris5VideoSuperResolutionSwitchCallback = iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1;
        Iris5Tile$mIris5MotionFluencySwitchCallback$1 iris5Tile$mIris5MotionFluencySwitchCallback$1 = new Iris5Tile$mIris5MotionFluencySwitchCallback$1();
        iris5Tile$mIris5MotionFluencySwitchCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mIris5MotionFluencySwitchCallback = iris5Tile$mIris5MotionFluencySwitchCallback$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131236074);
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
        FlavorTwoFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorTwoDevice = FlavorTwoFeatureOption.isFlavorTwoDevice();
        Context context = ((QSTileImpl) this).mContext;
        if (zIsFlavorTwoDevice) {
            String string = context.getString(2131955177);
            string.getClass();
            return string;
        }
        String string2 = context.getString(2131955176);
        string2.getClass();
        return string2;
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        this.customTileConnection.onClick();
    }

    public final void handleDestroy() {
        super.handleDestroy();
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mPowerTemperatureControlVideosrCallback);
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mPowerTemperatureControlOsieCallback);
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mPowerTemperatureControlFluencyCallback);
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mIris5VideoDisplaySwitchCallback);
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mIris5VideoSuperResolutionSwitchCallback);
        Iris5SwitchAndTemperatureControlObserver.getInstance().unRegister(this.mIris5MotionFluencySwitchCallback);
    }

    public final void handleInitialize() {
        if (Iris5Util.isIris5VideoSuperResolutionSwitchSupport()) {
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mPowerTemperatureControlVideosrCallback);
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mIris5VideoSuperResolutionSwitchCallback);
        }
        if (Iris5Util.isOsieVideoDisplaySwitchSupport()) {
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mPowerTemperatureControlOsieCallback);
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mIris5VideoDisplaySwitchCallback);
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportIris5()) {
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mPowerTemperatureControlFluencyCallback);
            Iris5SwitchAndTemperatureControlObserver.getInstance().register(((QSTileImpl) this).mContext, this.mIris5MotionFluencySwitchCallback);
        }
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        this.customTileConnection.setListening(z);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.AdapterState) booleanState).value = Iris5Util.isIris5Switch(((QSTileImpl) this).mContext);
        FlavorTwoFeatureOption.INSTANCE.getClass();
        boolean zIsFlavorTwoDevice = FlavorTwoFeatureOption.isFlavorTwoDevice();
        Context context = ((QSTileImpl) this).mContext;
        ((QSTile.State) booleanState).label = zIsFlavorTwoDevice ? context.getString(2131955177) : context.getString(2131955176);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        Context context2 = ((QSTileImpl) this).mContext;
        int i = (!(Iris5Util.isIris5VideoSuperResolutionSwitchSupport() && Settings.Secure.getIntForUser(context2.getContentResolver(), "customize_power_temperature_control_videosr", 0, 0) == 1) && Iris5Util.isIris5VideoSuperResolutionSwitchSupport()) ? 0 : 1;
        if ((Iris5Util.isOsieVideoDisplaySwitchSupport() && Settings.Secure.getIntForUser(context2.getContentResolver(), "customize_power_temperature_control_osie", 0, 0) == 1) || !Iris5Util.isOsieVideoDisplaySwitchSupport()) {
            i++;
        }
        QSFeatureOption.INSTANCE.getClass();
        if ((QSFeatureOption.isSupportIris5() && Settings.Secure.getIntForUser(context2.getContentResolver(), "customize_power_temperature_control_motion_fluency", 0, 0) == 1) || !QSFeatureOption.isSupportIris5()) {
            i++;
        }
        if (i != 3) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131236075 : 2131236074);
            ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131236076);
            ((QSTile.State) booleanState).state = 0;
            LogUtil.w$default(8, LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUpdateState, Iris enter temperature control");
        }
    }

    public final boolean isAvailable() {
        Iris5Util iris5Util = Iris5Util.INSTANCE;
        return OsBinderCacheUtils.getCurrentUserId() == 0 && this.customTileConnection.checkComponentState() && (!StringsKt__StringsKt.contains(QSFeatureOption.INSTANCE.getDisableQSTileList(), TILE_SPEC, false) || StringsKt__StringsKt.contains(TextUtils.emptyIfNull(Settings.Secure.getString(((QSTileImpl) this).mContext.getContentResolver(), "sysui_qs_tiles")), TILE_SPEC, false));
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m322newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
