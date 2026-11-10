package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.LocationTile;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplus.systemui.qs.OplusLocationTileEx;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLocationTile extends LocationTile {
    private final OplusLocationTile$locationModeSettingsObserver$1 locationModeSettingsObserver;
    private OplusLocationTileEx mOplusLocationTileEx;
    private final SatellitePolicy.IConnectionCallback mSatellitePolicyCallback;
    private final Consumer<Boolean> mStealModeCallback;
    private SatellitePolicy oplusSatellitePolicy;

    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.qs.tiles.OplusLocationTile$locationModeSettingsObserver$1] */
    public OplusLocationTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, LocationController locationController, KeyguardStateController keyguardStateController, PanelInteractor panelInteractor, final SecureSettings secureSettings, SatellitePolicy satellitePolicy) {
        Context userContext;
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, locationController, keyguardStateController, panelInteractor);
        this.oplusSatellitePolicy = satellitePolicy;
        OplusLocationTile$mStealModeCallback$1 oplusLocationTile$mStealModeCallback$1 = new OplusLocationTile$mStealModeCallback$1();
        oplusLocationTile$mStealModeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStealModeCallback = oplusLocationTile$mStealModeCallback$1;
        OplusLocationTile$mSatellitePolicyCallback$1 oplusLocationTile$mSatellitePolicyCallback$1 = new OplusLocationTile$mSatellitePolicyCallback$1();
        oplusLocationTile$mSatellitePolicyCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = oplusLocationTile$mSatellitePolicyCallback$1;
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = (qSHost == null || (userContext = qSHost.getUserContext()) == null) ? 0 : userContext.getUserId();
        this.locationModeSettingsObserver = new UserSettingObserver(secureSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.OplusLocationTile$locationModeSettingsObserver$1
            public final void handleValueChanged(int i, boolean z) {
                String str = ((QSTileImpl) this).TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    sbM.append("[location settings] handleValueChanged: value = " + i + ", changed = " + z);
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                if (z) {
                    this.refreshState((Object) null);
                }
            }
        };
    }

    public final OplusLocationTileEx ensureLocationTileEx() {
        OplusLocationTileEx oplusLocationTileEx = this.mOplusLocationTileEx;
        if (oplusLocationTileEx != null) {
            return oplusLocationTileEx;
        }
        OplusLocationTileEx oplusLocationTileEx2 = new OplusLocationTileEx(((QSTileImpl) this).mContext, ((LocationTile) this).mController, ((LocationTile) this).mKeyguard);
        this.mOplusLocationTileEx = oplusLocationTileEx2;
        return oplusLocationTileEx2;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237180);
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956558);
    }

    public void handleClick(Expandable expandable) {
        if (ensureLocationTileEx().shouldGPSTileShowVoWifiDialog()) {
            Runnable runnable = new isForbiddenClick.1();
            ((isForbiddenClick.1) runnable).this$0 = this;
            VarHandle.storeStoreFence();
            if (((QSTileImpl) this).mUiHandler.post(runnable)) {
                return;
            }
        }
        super.handleClick(expandable);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
        ThreeKeyStealthModeObserver.Companion.getInstance().removeObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
        this.oplusSatellitePolicy.removeCallback(this.mSatellitePolicyCallback);
    }

    public final void handleInitialize() {
        super.handleInitialize();
        this.oplusSatellitePolicy.addCallback(this.mSatellitePolicyCallback);
        setListening(true);
    }

    public final void handleSetListening(boolean z) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleSetListening(z);
        if (!z) {
            ThreeKeyStealthModeObserver.Companion.getInstance().removeObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return;
        }
        ThreeKeyStealthModeObserver.Companion.getInstance().addObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion() && ThreeKeyStealthModeObserver.Companion.isStealthModeOn(((QSTileImpl) this).mContext)) {
            ((QSTile.AdapterState) booleanState).value = false;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237181);
            ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956558);
            ((QSTile.State) booleanState).secondaryLabel = ((QSTileImpl) this).mContext.getString(2131955715);
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951932);
            ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
            return;
        }
        ((QSTile.State) booleanState).secondaryLabel = null;
        boolean zIsLocationEnabled = ((LocationTile) this).mController.isLocationEnabled();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("locationEnabled: ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), zIsLocationEnabled);
        }
        ((QSTile.AdapterState) booleanState).value = zIsLocationEnabled;
        checkIfRestrictionEnforcedByAdminOnly("no_share_location", booleanState);
        if (!((QSTile.State) booleanState).disabledByPolicy) {
            checkIfRestrictionEnforcedByAdminOnly("no_config_location", booleanState);
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(zIsLocationEnabled ? 2131237179 : 2131237180);
        Context context = ((QSTileImpl) this).mContext;
        if (zIsLocationEnabled) {
            ((QSTile.State) booleanState).label = context.getString(2131956558);
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951933);
        } else {
            ((QSTile.State) booleanState).label = context.getString(2131956558);
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951932);
        }
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m336newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_location_lottie";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR;
        return booleanState;
    }

    public final void userSwitch(int i) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.userSwitch(i);
        setUserId(i);
    }
}
