package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.common.settings.hardwarenode.Charger;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneOtgTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    private static final String OPLUS_FEATURE_TYPEC_SUPPORT = "oplus.typecotg.connection.menu.support";
    private static final String OTHER_SETTIND_ACTION = "oplus.intent.action.settings.SPECIAL_FEATURE";
    private static final String SYS_OTG_ENABLE = "persist.sys.oplus.otg_support";
    private static final String SYS_OTG_SUPPORT = "persist.vendor.otg.switch";
    public static final String TILE_SPEC = "otg";
    private final BatteryController.BatteryStateChangeCallback mCallback;
    private final Charger mCharger;
    public boolean mIsCharging;
    public boolean mListening;
    private final ContentObserver mObserver;
    public int mOtgState;
    private final PanelInteractor mPanelInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FlavorOneOtgTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, final Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, PanelInteractor panelInteractor) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mPanelInteractor = panelInteractor;
        this.mObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.qs.tiles.FlavorOneOtgTile$mObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                this.refreshState((Object) null);
            }
        };
        this.mCharger = new Charger();
        FlavorOneOtgTile$mCallback$1 flavorOneOtgTile$mCallback$1 = new FlavorOneOtgTile$mCallback$1();
        flavorOneOtgTile$mCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = flavorOneOtgTile$mCallback$1;
        ((BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class)).addCallback(flavorOneOtgTile$mCallback$1);
    }

    private final boolean getOtgEnable() {
        int i = Settings.Global.getInt(((QSTileImpl) this).mContext.getContentResolver(), SYS_OTG_ENABLE, -1);
        int psyOtgSwitch = this.mCharger.getPsyOtgSwitch();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("getOtgStatus otgState : " + i + ",hidlState : " + psyOtgSwitch);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        return psyOtgSwitch == 1;
    }

    private final void setOtgEnable(boolean z) {
        this.mCharger.setPsyOtgSwitch(String.valueOf(z ? 1 : 0));
        Settings.Global.putInt(((QSTileImpl) this).mContext.getContentResolver(), SYS_OTG_ENABLE, z ? 1 : 0);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237201);
    }

    public Intent getLongClickIntent() {
        return new Intent(OTHER_SETTIND_ACTION);
    }

    public int getMetricsCategory() {
        return 0;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955838);
    }

    public void handleClick(Expandable expandable) {
        if (OplusFeatureConfigManager.getInstance().hasFeature(OPLUS_FEATURE_TYPEC_SUPPORT)) {
            int psyOtgOnline = this.mCharger.getPsyOtgOnline();
            this.mOtgState = psyOtgOnline;
            if (psyOtgOnline != 0) {
                LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(psyOtgOnline, "otg tile is unavailable to click for otg state:"), null, 8);
                PanelInteractorImpl panelInteractorImpl = this.mPanelInteractor;
                if (panelInteractorImpl != null) {
                    panelInteractorImpl.collapsePanels();
                }
                boolean z = this.mIsCharging;
                Context context = ((QSTileImpl) this).mContext;
                String string = z ? context.getString(2131955840) : context.getString(2131955839);
                string.getClass();
                Toast.makeText(((QSTileImpl) this).mContext, string, 0).show();
                return;
            }
        }
        setOtgEnable(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        ((BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class)).removeCallback(this.mCallback);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        if (!z) {
            ObserverDispatcher.getInstance().unregisterObserver(this.mObserver);
        } else {
            ObserverDispatcher.getInstance().registerObserver(Settings.Global.getUriFor(SYS_OTG_ENABLE), this.mObserver);
            refreshState((Object) null);
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean otgEnable = getOtgEnable();
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955838);
        ((QSTile.AdapterState) booleanState).value = otgEnable;
        ((QSTile.State) booleanState).state = otgEnable ? 2 : 1;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(otgEnable ? 2131237200 : 2131237201);
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("otgState:" + this.mOtgState + " isOtgEnable:" + otgEnable);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
    }

    public final boolean isAvailable() {
        Object obj;
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            try {
                obj = Result.constructor-impl(Boolean.valueOf(!OplusCustomizeRestrictionManager.getInstance(((QSTileImpl) this).mContext).isUSBOtgDisabled()));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "isUSBOtgDisabled: e = ", th2.getMessage(), LogUtil.TAG_QS, ((QSTileImpl) this).TAG);
            }
            Boolean bool = Boolean.FALSE;
            if (obj instanceof Result.Failure) {
                obj = bool;
            }
            if (((Boolean) obj).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m319newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
