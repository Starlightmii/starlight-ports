package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.utils.QSFlowExKt;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.oplus.systemui.statusbar.policy.dagger.OplusKeyguardStateControllerExImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.observer.HighTempSafetyObserver;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveModeTile extends QSTileImpl {
    private static final String BATTERY_PACKAGE_NAME = "com.oplus.battery";
    public static final Companion Companion = new Companion();
    private static final String POWER_SAVE_CLASS_NAME = "com.oplus.powermanager.fuelgaue.PowerConsumptionActivity";
    public static final String TILE_SPEC = "superpowersave";
    private final CoroutineContext bgCoroutineContext;
    private final KeyguardRepository keyguardRepository;
    private final SuperPowerSaveModeTile$mObserverListener$1 mObserverListener;
    private final Handler mainHandler;
    private final Lazy oplusKeyguardStateControllerExImpl;
    private final SelectedUserInteractor selectedUserInteractor;
    private final SuperPowerSaveRepository superPowerSaveRepository;
    private final Runnable toggleStateRunnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile$handleClick$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ SuperPowerSaveModeTile$toggleStateRunnable$1 $runnable;
        public /* synthetic */ SuperPowerSaveModeTile this$0;

        @Override // java.lang.Runnable
        public final void run() {
            ((QSTileImpl) this.this$0).mActivityStarter.executeRunnableDismissingKeyguard(this.$runnable, (Runnable) null, true, true, false);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.SuperPowerSaveModeTile$handleInitialize$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02741 implements Consumer {
        public /* synthetic */ SuperPowerSaveModeTile this$0;

        public final void accept(Object obj) {
            ((Boolean) obj).getClass();
            this.this$0.refreshState((Object) null);
        }
    }

    public SuperPowerSaveModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardRepository keyguardRepository, SelectedUserInteractor selectedUserInteractor, SuperPowerSaveRepository superPowerSaveRepository, CoroutineContext coroutineContext, Lazy lazy) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mainHandler = handler;
        this.keyguardRepository = keyguardRepository;
        this.selectedUserInteractor = selectedUserInteractor;
        this.superPowerSaveRepository = superPowerSaveRepository;
        this.bgCoroutineContext = coroutineContext;
        this.oplusKeyguardStateControllerExImpl = lazy;
        SuperPowerSaveModeTile$toggleStateRunnable$1 superPowerSaveModeTile$toggleStateRunnable$1 = new SuperPowerSaveModeTile$toggleStateRunnable$1(0);
        superPowerSaveModeTile$toggleStateRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.toggleStateRunnable = superPowerSaveModeTile$toggleStateRunnable$1;
        SuperPowerSaveModeTile$mObserverListener$1 superPowerSaveModeTile$mObserverListener$1 = new SuperPowerSaveModeTile$mObserverListener$1();
        superPowerSaveModeTile$mObserverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mObserverListener = superPowerSaveModeTile$mObserverListener$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237225);
    }

    public Intent getLongClickIntent() {
        if (HighTempSafetyObserver.getInstance().mHighTempSafetyState || ((QSTile.State) ((QSTileImpl) this).mState).state == 0) {
            return null;
        }
        Intent intent = new Intent();
        intent.setClassName(BATTERY_PACKAGE_NAME, POWER_SAVE_CLASS_NAME);
        intent.putExtra(":settings:fragment_args_key", "super_power_save_mode_pref");
        return intent;
    }

    public int getMetricsCategory() {
        return 261;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956572);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick Tile.STATE_UNAVAILABLE", null, 8);
            return;
        }
        if (QSFeatureOption.INSTANCE.isSuperPowerModeDisabled(((QSTileImpl) this).mContext) || HighTempSafetyObserver.getInstance().mHighTempSafetyState) {
            LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick isSuperPowerModeDisabled or HighTempSafetyState", null, 8);
            return;
        }
        if (this.keyguardRepository.isKeyguardShowing()) {
            ((OplusKeyguardStateControllerExImpl) this.oplusKeyguardStateControllerExImpl.get()).clickSuperPowerEnableFromModeTileWhileKgdShowing();
        }
        SuperPowerSaveModeTile$toggleStateRunnable$1 superPowerSaveModeTile$toggleStateRunnable$1 = new SuperPowerSaveModeTile$toggleStateRunnable$1(1);
        superPowerSaveModeTile$toggleStateRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        Handler handler = this.mainHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$runnable = superPowerSaveModeTile$toggleStateRunnable$1;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        HighTempSafetyObserver.getInstance().removeListener(this.mObserverListener);
    }

    public final void handleInitialize() {
        Lifecycle lifecycle = getLifecycle();
        Flow flowMerge = FlowKt.merge(new Flow[]{this.superPowerSaveRepository.getSuperPowerSaveMode(), this.superPowerSaveRepository.isRelatedTileAvailable()});
        CoroutineContext coroutineContext = this.bgCoroutineContext;
        C02741 c02741 = new C02741();
        c02741.this$0 = this;
        VarHandle.storeStoreFence();
        QSFlowExKt.alwaysCollectFlow(lifecycle, flowMerge, coroutineContext, c02741);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (z) {
            HighTempSafetyObserver.getInstance().addListener(this.mObserverListener);
        } else {
            HighTempSafetyObserver.getInstance().removeListener(this.mObserverListener);
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956572);
        boolean inSuperPowerSaveMode = this.superPowerSaveRepository.getInSuperPowerSaveMode();
        ((QSTile.AdapterState) booleanState).value = inSuperPowerSaveMode;
        if (inSuperPowerSaveMode) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237224);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237225);
        }
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956572);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
        if (!((Boolean) this.superPowerSaveRepository.isRelatedTileAvailable().getValue()).booleanValue() || HighTempSafetyObserver.getInstance().mHighTempSafetyState) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237226);
        }
    }

    public final boolean isAvailable() {
        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
        qSFeatureOption.getClass();
        return QSFeatureOption.isSuperPowerModeSupport && this.selectedUserInteractor.getSelectedUserId() == 0 && !FeatureOption.INSTANCE.isDisablePowerSave(((QSTileImpl) this).mContext) && !qSFeatureOption.isSuperPowerModeDisabled(((QSTileImpl) this).mContext);
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m347newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
