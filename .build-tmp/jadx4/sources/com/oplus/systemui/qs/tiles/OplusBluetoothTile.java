package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.BluetoothTile;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.qs.receiver.BluetoothStateReceiver;
import com.oplus.sysuiinlineview.host.SysUiInlineViewHost;
import com.oplus.sysuiinlineview.host.controller.SysUiInlineHostViewController;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper$configBluetoothDetailView$1;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusBluetoothTile extends BluetoothTile implements IReceiverListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AnonymousClass1 mCallback;
    public final OplusQSDetailInteractor mDetailInteractor;
    public final PanelInteractor mPanelInteractor;
    public final OplusBluetoothTile$$ExternalSyntheticLambda0 mRecheckRunnable;
    public final UserTracker mUserTracker;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusBluetoothTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BluetoothController.Callback {
        public /* synthetic */ OplusBluetoothTile this$0;

        public final void onBluetoothDevicesChanged() {
            LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", "onBluetoothDevicesChanged");
            this.this$0.refreshState((Object) null);
        }

        public final void onBluetoothStateChange(boolean z) {
            OplusBluetoothTile oplusBluetoothTile = this.this$0;
            boolean zIsShowing = oplusBluetoothTile.mDetailInteractor.isShowing();
            int bluetoothState = ((BluetoothTile) oplusBluetoothTile).mController.getBluetoothState();
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onBluetoothStateChange: enabled=", OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, z, zIsShowing);
            sbM.append(bluetoothState);
            LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", sbM.toString());
            oplusBluetoothTile.getTileEx().onFwStateChanged(oplusBluetoothTile, QSTileEx.mapToUiState(z), ((QSTileImpl) oplusBluetoothTile).mClickHandler);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ColorBluetoothDetailAdapter implements DetailAdapter, OplusQSDetailItems.Callback {
        public /* synthetic */ OplusBluetoothTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusBluetoothTile oplusBluetoothTile = this.this$0;
            SysUiInlineViewHost bluetoothDetailHost = SystemUIInlineViewHelper.getBluetoothDetailHost(context, oplusBluetoothTile.mUserTracker.getUserHandle());
            SysUiInlineHostView sysUiInlineHostViewBuildHostView = bluetoothDetailHost != null ? bluetoothDetailHost.buildHostView(context, viewGroup, z) : null;
            SysUiInlineHostViewController controller = sysUiInlineHostViewBuildHostView != null ? sysUiInlineHostViewBuildHostView.getController() : null;
            Bundle baseExtras = controller != null ? controller.getBaseExtras() : null;
            if (baseExtras != null) {
                SystemUIInlineViewHelper.setQSDetailOldDarkTheme(baseExtras, oplusBluetoothTile.mDetailInteractor.isDarkTheme());
                SystemUIInlineViewHelper.setQSDetailOldThirdTheme(baseExtras, QsColorUtil.isGlobalThemeApplied(context));
                baseExtras.putBoolean(SystemUIInlineViewHelper.BUNDLE_KEY_IS_SUPPORT_LIGHT, BaseQSResPool.isSupportSpotLight());
            }
            return sysUiInlineHostViewBuildHostView;
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public int getMetricsCategory() {
            return 150;
        }

        public Intent getSettingsIntent() {
            int i = OplusBluetoothTile.$r8$clinit;
            return BluetoothTile.BLUETOOTH_SETTINGS;
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH;
        }

        public CharSequence getTitle() {
            OplusBluetoothTile oplusBluetoothTile = this.this$0;
            int i = OplusBluetoothTile.$r8$clinit;
            return ((QSTileImpl) oplusBluetoothTile).mContext.getString(2131955728);
        }

        public Boolean getToggleState() {
            OplusBluetoothTile oplusBluetoothTile = this.this$0;
            int i = OplusBluetoothTile.$r8$clinit;
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) oplusBluetoothTile).mState).value);
        }

        public final boolean hasFooterDivider() {
            return true;
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemClick(OplusQSDetailItems.Item item) {
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemDisconnect(OplusQSDetailItems.Item item) {
        }

        public void setToggleState(boolean z) {
            OplusBluetoothTile oplusBluetoothTile = this.this$0;
            QSTileImpl.H h = ((QSTileImpl) oplusBluetoothTile).mHandler;
            OplusBluetoothTile$$ExternalSyntheticLambda0 oplusBluetoothTile$$ExternalSyntheticLambda0 = oplusBluetoothTile.mRecheckRunnable;
            QSTileEx.UiState uiStateMapToUiState = QSTileEx.mapToUiState(z);
            int i = OplusBluetoothTile.$r8$clinit;
            oplusBluetoothTile.onDetailToggle(uiStateMapToUiState);
            if (z) {
                LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", "stopRecheckGuardTimer");
                if (h.hasCallbacks(oplusBluetoothTile$$ExternalSyntheticLambda0)) {
                    h.removeCallbacks(oplusBluetoothTile$$ExternalSyntheticLambda0);
                    return;
                }
                return;
            }
            LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", "startRecheckGuardTimer");
            if (h.hasCallbacks(oplusBluetoothTile$$ExternalSyntheticLambda0)) {
                h.removeCallbacks(oplusBluetoothTile$$ExternalSyntheticLambda0);
            }
            h.postDelayed(oplusBluetoothTile$$ExternalSyntheticLambda0, 2000L);
        }

        public final boolean turnOnSwitchWhenAttach() {
            return true;
        }
    }

    public OplusBluetoothTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BluetoothController bluetoothController, FeatureFlags featureFlags, Lazy lazy, PanelInteractor panelInteractor, UserTracker userTracker, OplusQSDetailInteractor.Factory factory) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, bluetoothController, featureFlags, lazy);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = anonymousClass1;
        OplusBluetoothTile$$ExternalSyntheticLambda0 oplusBluetoothTile$$ExternalSyntheticLambda0 = new OplusBluetoothTile$$ExternalSyntheticLambda0();
        oplusBluetoothTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mRecheckRunnable = oplusBluetoothTile$$ExternalSyntheticLambda0;
        this.mUserTracker = userTracker;
        BluetoothStateReceiver bluetoothStateReceiver = BluetoothStateReceiver.INSTANCE;
        bluetoothStateReceiver.register(((QSTileImpl) this).mContext, UserHandle.ALL, (Handler) null);
        bluetoothStateReceiver.addListener(this);
        this.mPanelInteractor = panelInteractor;
        SystemUIInlineViewHelper systemUIInlineViewHelper = SystemUIInlineViewHelper.INSTANCE;
        SystemUIInlineViewHelper.setComponentProvider("oplus.inlineui.category.QS_DETAIL_BLUETOOTH", SystemUIInlineViewHelper$configBluetoothDetailView$1.INSTANCE);
        SystemUIInlineViewHelper.setViewFactory$default("oplus.inlineui.category.QS_DETAIL_BLUETOOTH");
        ColorBluetoothDetailAdapter colorBluetoothDetailAdapter = new ColorBluetoothDetailAdapter();
        colorBluetoothDetailAdapter.this$0 = this;
        VarHandle.storeStoreFence();
        this.mDetailInteractor = factory.create(colorBluetoothDetailAdapter);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237097);
    }

    public final void handleClick(Expandable expandable) {
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusBluetoothTile", new StringBuilder("handleClick: mState.value="), ((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
        boolean z = !((QSTile.AdapterState) ((QSTileImpl) this).mState).value;
        OplusBluetoothTile$$ExternalSyntheticLambda1 oplusBluetoothTile$$ExternalSyntheticLambda1 = new OplusBluetoothTile$$ExternalSyntheticLambda1();
        oplusBluetoothTile$$ExternalSyntheticLambda1.f$0 = this;
        oplusBluetoothTile$$ExternalSyntheticLambda1.f$1 = z;
        VarHandle.storeStoreFence();
        ((QSTileImpl) this).mHandler.post(oplusBluetoothTile$$ExternalSyntheticLambda1);
    }

    public final void handleDestroy() {
        ((BluetoothTile) this).mController.removeCallback(this.mCallback);
        BluetoothStateReceiver bluetoothStateReceiver = BluetoothStateReceiver.INSTANCE;
        bluetoothStateReceiver.removeListener(this);
        bluetoothStateReceiver.unregister(((QSTileImpl) this).mContext);
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleDestroy();
    }

    public final void handleInitialize() {
        ((BluetoothTile) this).mController.addCallback(this.mCallback);
    }

    public final void handleSecondaryClick(Expandable expandable) {
        if (!((BluetoothTile) this).mController.canConfigBluetooth()) {
            ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.BLUETOOTH_SETTINGS"), 0);
        } else if (SystemUIInlineViewHelper.isSupportBluetoothDetail(((QSTileImpl) this).mContext)) {
            this.mDetailInteractor.show(expandable != null ? expandable.getView() : null);
        } else {
            handleLongClick(expandable);
        }
    }

    public final void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean zEquals = QSTileEx.UiState.OFF.equals(obj);
        BluetoothControllerImpl bluetoothControllerImpl = ((BluetoothTile) this).mController;
        boolean z = !zEquals && (QSTileEx.UiState.ON.equals(obj) || bluetoothControllerImpl.mEnabled);
        if (((QSTile.AdapterState) booleanState).value != z) {
            this.mDetailInteractor.updateSwitchState(z);
        }
        int i = bluetoothControllerImpl.mConnectionState;
        boolean z2 = i == 2;
        boolean z3 = i == 1;
        StringBuilder sb = new StringBuilder("uiState=");
        sb.append(obj);
        sb.append(" enabled=");
        sb.append(z);
        sb.append(" connected=");
        LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z2, " connecting=", z3));
        ((QSTile.State) booleanState).dualTarget = true;
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955728);
        ((QSTile.State) booleanState).secondaryLabel = TextUtils.emptyIfNull(getSecondaryLabel(z, z3, z2, false, false));
        ((QSTile.State) booleanState).labelDesc = TextUtils.emptyIfNull(getSecondaryLabel(z, z3, z2, false, true));
        if (z) {
            if (z2) {
                if (!TextUtils.isEmpty(bluetoothControllerImpl.getConnectedDeviceName())) {
                    ((QSTile.State) booleanState).label = bluetoothControllerImpl.getConnectedDeviceName();
                    if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
                        ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955759);
                    } else {
                        ((QSTile.State) booleanState).labelDesc = ((QSTile.State) booleanState).label;
                    }
                }
                ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951734, ((QSTile.State) booleanState).label) + ", " + ((Object) ((QSTile.State) booleanState).secondaryLabel);
            } else {
                ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951919) + OplusPreventModeData.SEPARATOR + ((QSTileImpl) this).mContext.getString(2131951890);
                if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
                    ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955246);
                }
            }
            ((QSTile.State) booleanState).state = 2;
        } else {
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951919);
            ((QSTile.State) booleanState).state = 1;
        }
        if (((QSTile.AdapterState) booleanState).value) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237096);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237097);
        }
        if (((QSTile.State) booleanState).icon.getDrawable(((QSTileImpl) this).mContext) == null) {
            LogUtil.i(LogUtil.TAG_QS, "OplusBluetoothTile", "handleUpdateState：Icon Drawable is null");
        }
        ((QSTile.State) booleanState).dualLabelContentDescription = ((QSTileImpl) this).mContext.getResources().getString(2131951935, getTileLabel());
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean handleUserOperation(Object obj) {
        LogUtil.i(LogUtil.TAG_QS, "OplusBluetoothTile", "handleUserOperation: operation=" + obj);
        boolean zEquals = QSTileEx.UiState.ON.equals(obj);
        OplusBluetoothTile$$ExternalSyntheticLambda1 oplusBluetoothTile$$ExternalSyntheticLambda1 = new OplusBluetoothTile$$ExternalSyntheticLambda1();
        oplusBluetoothTile$$ExternalSyntheticLambda1.f$0 = this;
        oplusBluetoothTile$$ExternalSyntheticLambda1.f$1 = zEquals;
        VarHandle.storeStoreFence();
        ((QSTileImpl) this).mHandler.post(oplusBluetoothTile$$ExternalSyntheticLambda1);
        return true;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public final QSTile.BooleanState m328newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_bluetooth_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR;
        return booleanState;
    }

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        LogUtil.d(LogUtil.TAG_QS, "OplusBluetoothTile", "Receive: " + intent.getAction());
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            refreshState((Object) null);
            return;
        }
        if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(intent.getAction())) {
            OplusQSDetailInteractor oplusQSDetailInteractor = this.mDetailInteractor;
            if (oplusQSDetailInteractor != null) {
                oplusQSDetailInteractor.dismiss(true);
            }
            PanelInteractorImpl panelInteractorImpl = this.mPanelInteractor;
            if (panelInteractorImpl != null) {
                panelInteractorImpl.collapsePanels();
            }
        }
    }

    public final void preloadForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.prepareDetailView();
    }

    public final void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.releaseDetailView();
    }
}
