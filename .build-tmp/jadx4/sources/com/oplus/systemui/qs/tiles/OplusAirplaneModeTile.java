package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.sysprop.TelephonyProperties;
import android.widget.Switch;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.data.utils.QSFlowExKt;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.AirplaneModeTile;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.pipeline.data.repository.AirplaneModeConfirmRepository;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAirplaneModeTile extends AirplaneModeTile {
    private final AirplaneModeConfirmRepository airplaneModeConfirmRepository;
    private final AirplaneModeRepository airplaneModeRepository;
    private final CoroutineContext bgCoroutineContext;
    private KeyguardStateController mKeyguard;
    private final SatellitePolicy.IConnectionCallback mSatellitePolicyCallback;
    private SatellitePolicy oplusSatellitePolicy;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusAirplaneModeTile$handleInitialize$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Consumer {
        public /* synthetic */ OplusAirplaneModeTile this$0;

        public final void accept(Object obj) {
            ((Boolean) obj).getClass();
            this.this$0.refreshState((Object) null);
        }
    }

    public OplusAirplaneModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BroadcastDispatcher broadcastDispatcher, Lazy lazy, GlobalSettings globalSettings, UserTracker userTracker, KeyguardStateController keyguardStateController, SatellitePolicy satellitePolicy, CoroutineContext coroutineContext, AirplaneModeRepository airplaneModeRepository, AirplaneModeConfirmRepository airplaneModeConfirmRepository) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, broadcastDispatcher, lazy, globalSettings, userTracker);
        this.mKeyguard = keyguardStateController;
        this.oplusSatellitePolicy = satellitePolicy;
        this.bgCoroutineContext = coroutineContext;
        this.airplaneModeRepository = airplaneModeRepository;
        this.airplaneModeConfirmRepository = airplaneModeConfirmRepository;
        OplusAirplaneModeTile$mSatellitePolicyCallback$1 oplusAirplaneModeTile$mSatellitePolicyCallback$1 = new OplusAirplaneModeTile$mSatellitePolicyCallback$1();
        oplusAirplaneModeTile$mSatellitePolicyCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = oplusAirplaneModeTile$mSatellitePolicyCallback$1;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.dump(printWriter, strArr);
        printWriter.println("OplusAirplaneModeTile Data:");
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("airplaneMode：", this.airplaneModeRepository.isAirplaneMode().getValue(), printWriter);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237085);
    }

    public Intent getLongClickIntent() {
        return QSFeatureOption.INSTANCE.isSupportSettingsAirplane(((QSTileImpl) this).mContext) ? new Intent("android.settings.SETTINGS") : new Intent("wireless.settings.WIRELESS_SETTINGS");
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955709);
    }

    public void handleClick(Expandable expandable) {
        KeyguardStateControllerImpl keyguardStateControllerImpl;
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_AIRPLANMODE) || this.airplaneModeConfirmRepository.sendConfirmEventWhenNecessary()) {
            return;
        }
        boolean z = ((QSTile.AdapterState) ((QSTileImpl) this).mState).value;
        boolean z2 = !z;
        MetricsLogger.action(((QSTileImpl) this).mContext, getMetricsCategory(), z2);
        if (!z && ((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue()) {
            QSTileEx tileEx = getTileEx();
            Intent intent = new Intent("android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS");
            QSTileEx.Companion companion = QSTileEx.Companion;
            tileEx.postStartActivityDismissingKeyguard(expandable, intent, false);
            return;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion() && (keyguardStateControllerImpl = this.mKeyguard) != null) {
            KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
            if (keyguardStateControllerImpl2.mSecure && keyguardStateControllerImpl2.mShowing) {
                handleClick.1 r5 = new handleClick.1();
                r5.this$0 = this;
                r5.$airplaneModeEnabled = z;
                VarHandle.storeStoreFence();
                ((QSTileImpl) this).mActivityStarter.postQSRunnableDismissingKeyguard(r5);
                return;
            }
        }
        setEnabled(z2);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        this.oplusSatellitePolicy.removeCallback(this.mSatellitePolicyCallback);
    }

    public final void handleInitialize() {
        this.oplusSatellitePolicy.addCallback(this.mSatellitePolicyCallback);
        Lifecycle lifecycle = getLifecycle();
        StateFlow stateFlowIsAirplaneMode = this.airplaneModeRepository.isAirplaneMode();
        CoroutineContext coroutineContext = this.bgCoroutineContext;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        QSFlowExKt.alwaysCollectFlow(lifecycle, stateFlowIsAirplaneMode, coroutineContext, anonymousClass1);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        checkIfRestrictionEnforcedByAdminOnly("no_airplane_mode", booleanState);
        boolean zBooleanValue = ((Boolean) this.airplaneModeRepository.isAirplaneMode().getValue()).booleanValue();
        ((QSTile.AdapterState) booleanState).value = zBooleanValue;
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleUpdateState state.state: ", ((QSTile.State) booleanState).state, " airplaneMode: ", zBooleanValue), null, 8);
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955709);
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(zBooleanValue ? 2131237084 : 2131237085);
        ((QSTile.State) booleanState).state = zBooleanValue ? 2 : 1;
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m326newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_airplane_lottie";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_ORANGE_COLOR;
        return booleanState;
    }
}
