package com.oplus.systemui.qs.tiles;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.FlashlightTile;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$;
import com.android.systemui.util.settings.SystemSettings;
import com.oplus.systemui.OplusVibrateHelper;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.detail.flashlight.FlashlightDetailCommandDispatcher;
import com.oplus.systemui.qs.detail.flashlight.OplusQSFlashlightDetailProvider;
import com.oplus.systemui.qs.detail.flashlight.viewmodel.FlashlightDetailViewModel$Factory;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFlashlightTile extends FlashlightTile {
    private final FlashlightDetailCommandDispatcher commandDispatcher;
    private final SysUIViewDialogInteractor dialogInteractor;
    private final OplusFlashlightTile$flashlightAvailableObserver$1 flashlightAvailableObserver;
    private final FlashlightController mFlashlightController;
    private final OplusQSFlashlightDetailProvider mFlashlightDialog;
    private final OplusSeparateNotificationAndQSEx separateNotificationAndQSEx;
    private final OplusVibrateHelper vibrateHelper;
    private final SysUIViewDialogInteractor viewDialogInteractor;
    private final FlashlightDetailViewModel$Factory viewModelFactory;

    /* JADX WARN: Type inference failed for: r15v1, types: [com.oplus.systemui.qs.tiles.OplusFlashlightTile$flashlightAvailableObserver$1] */
    public OplusFlashlightTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, final SystemSettings systemSettings, SysUIViewDialogInteractor sysUIViewDialogInteractor, FlashlightController flashlightController, FlashlightDetailViewModel$Factory flashlightDetailViewModel$Factory, FlashlightDetailCommandDispatcher flashlightDetailCommandDispatcher, SysUIViewDialogInteractor sysUIViewDialogInteractor2, OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx, OplusVibrateHelper oplusVibrateHelper) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, flashlightController);
        this.dialogInteractor = sysUIViewDialogInteractor;
        this.mFlashlightController = flashlightController;
        this.viewModelFactory = flashlightDetailViewModel$Factory;
        this.commandDispatcher = flashlightDetailCommandDispatcher;
        this.viewDialogInteractor = sysUIViewDialogInteractor2;
        this.separateNotificationAndQSEx = oplusSeparateNotificationAndQSEx;
        this.vibrateHelper = oplusVibrateHelper;
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = qSHost.getUserContext().getUserId();
        this.flashlightAvailableObserver = new UserSettingObserver(systemSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.OplusFlashlightTile$flashlightAvailableObserver$1
            public final void handleValueChanged(int i, boolean z) {
                this.refreshState((Object) null);
                if (this.separateNotificationAndQSEx.enableSeparateNotificationAndQS() && this.mFlashlightDialog.isShowing()) {
                    this.dialogInteractor.dismissDialog("instant");
                }
            }
        };
        this.mFlashlightDialog = new OplusQSFlashlightDetailProvider(((QSTileImpl) this).mContext, flashlightDetailViewModel$Factory);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237155);
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956557);
    }

    public void handleClick(Expandable expandable) {
        if (ActivityManager.isUserAMonkey()) {
            return;
        }
        QSTile.BooleanState booleanState = ((QSTileImpl) this).mState;
        if (((QSTile.State) booleanState).state == 0) {
            shouldTipsWhenUnavailable();
            return;
        }
        boolean z = !((QSTile.AdapterState) booleanState).value;
        refreshState(Boolean.valueOf(z));
        this.commandDispatcher.setFlashlightEnable(z);
    }

    public final void handleDestroy() {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleDestroy();
        setListening(false);
        this.mFlashlightController.removeCallback(this);
    }

    public final void handleInitialize() {
        this.mFlashlightController.addCallback(this);
        setListening(true);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getOplusFlashLightControllerEx().setFlashLightContrller(this.mFlashlightController);
        }
    }

    public void handleLongClick(Expandable expandable) {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleLongClick()", "SystemUi--QuickSettings");
        }
        if (!this.mFlashlightController.mLevelAdjustSupport || ActivityManager.isUserAMonkey()) {
            handleClick(expandable);
            return;
        }
        if (((QSTile.State) ((QSTileImpl) this).mState).state == 0) {
            shouldTipsWhenUnavailable();
            handleClick(expandable);
        } else {
            this.mFlashlightDialog.setExpandableView(expandable != null ? expandable.getView() : null);
            this.viewDialogInteractor.showDialog(this.mFlashlightDialog);
            QsStatisticUtil.collectQsFlashlightSecondDialogEvent(((QSTileImpl) this).mContext, "enter_flashlight_secondary_dialog_by_long_press");
        }
    }

    public void handleSecondaryClick(Expandable expandable) {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleSecondaryClick()", "SystemUi--QuickSettings");
        }
        if (!this.mFlashlightController.mLevelAdjustSupport || ActivityManager.isUserAMonkey()) {
            return;
        }
        if (((QSTile.State) ((QSTileImpl) this).mState).state == 0) {
            shouldTipsWhenUnavailable();
            return;
        }
        if (!QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_TILE_FLASHLIGHT)) {
            this.mFlashlightDialog.setExpandableView(expandable != null ? expandable.getView() : null);
            this.viewDialogInteractor.showDialog(this.mFlashlightDialog);
            QsStatisticUtil.collectQsFlashlightSecondDialogEvent(((QSTileImpl) this).mContext, "enter_flashlight_second_dialog");
        } else {
            String str2 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str2, "-->handleSecondaryClick isFastClick", "SystemUi--QuickSettings");
            }
        }
    }

    public final void handleSetListening(boolean z) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleSetListening(z);
        if (z) {
            FlashlightControllerImpl flashlightControllerImpl = this.mFlashlightController;
            if (flashlightControllerImpl.mCameraId.get() == null) {
                flashlightControllerImpl.useNoLazy = true;
                LogUtil.d(LogUtil.TAG_QS, "FlashlightController", "initCamera: ");
                Executor executor = flashlightControllerImpl.mExecutor;
                FlashlightControllerImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FlashlightControllerImpl$.ExternalSyntheticLambda0(2);
                externalSyntheticLambda0.f$0 = flashlightControllerImpl;
                VarHandle.storeStoreFence();
                executor.execute(externalSyntheticLambda0);
            }
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956557);
        ((QSTile.State) booleanState).secondaryLabel = "";
        ((QSTile.State) booleanState).stateDescription = "";
        ((QSTile.State) booleanState).dualTarget = this.mFlashlightController.mLevelAdjustSupport;
        boolean z = getValue() == 1;
        if (this.mFlashlightController.isAvailable() && !z) {
            if (!(obj instanceof Boolean)) {
                ((QSTile.AdapterState) booleanState).value = this.mFlashlightController.isEnabled();
            } else if (obj.equals(Boolean.valueOf(((QSTile.AdapterState) booleanState).value))) {
                return;
            } else {
                ((QSTile.AdapterState) booleanState).value = ((Boolean) obj).booleanValue();
            }
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131237153 : 2131237155);
            if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
                ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955246);
            } else {
                ((QSTile.State) booleanState).labelDesc = "";
            }
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131955793);
            ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
            ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
            return;
        }
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            boolean zIsAvailable = this.mFlashlightController.isAvailable();
            StringBuilder sb = new StringBuilder("handleUpdateState: torch available is ");
            sb.append(zIsAvailable);
            sb.append(", flashlight available[settings] is ");
            sb.append(!z);
            sbM.append(sb.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237157);
        String string = ((QSTileImpl) this).mContext.getString(2131955792);
        ((QSTile.State) booleanState).secondaryLabel = string;
        ((QSTile.State) booleanState).stateDescription = string;
        ((QSTile.State) booleanState).state = 0;
    }

    public final void handleUserSwitch(int i) {
        setUserId(i);
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m334newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_flashlight_lottie";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_VIOLET_COLOR;
        return booleanState;
    }

    public void preloadForSecondaryClick(Expandable expandable) {
        this.mFlashlightDialog.prepareFlashLightDetailView();
    }

    public void releasePreloadedForSecondaryClick(Expandable expandable) {
        if (this.mFlashlightDialog.isShowing()) {
            return;
        }
        this.mFlashlightDialog.releaseFlashLightDetailView();
    }

    public void setFlashlightLevel(int i) {
        QsStatisticUtil.collectQsFlashlightSecondDialogEvent(((QSTileImpl) this).mContext, "change_flashlight_level");
    }

    public final void shouldTipsWhenUnavailable() {
        if (this.separateNotificationAndQSEx.enableSeparateNotificationAndQS()) {
            String string = ((QSTileImpl) this).mContext.getString(getValue() == 1 ? 2131955659 : 2131955658);
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("show flashlight temporarily info text = " + string);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            TemporarilyInfoManager.Companion.getInstance().show(string, null, "flashUnavailable", new TemporarilyInfoManager.InfoShowStyle(QsColorUtil.getFooterTextColor(((QSTileImpl) this).mContext), 11));
        }
    }
}
