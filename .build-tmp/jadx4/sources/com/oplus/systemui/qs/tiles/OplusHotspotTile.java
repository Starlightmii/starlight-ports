package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.text.BidiFormatter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.compose.animation.ChangeSize$;
import androidx.lifecycle.Lifecycle;
import androidx.slice.widget.RowView$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.wifi.WifiEnterpriseRestrictionUtils;
import com.android.systemui.animation.Expandable;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.data.utils.QSFlowExKt;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.HotspotTile;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.HotspotControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.qs.receiver.WifiApStateReceiver;
import com.oplus.systemui.qs.utils.QsOperatorUtils;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusHotspotTile extends HotspotTile {
    public final AirplaneModeRepository mAirplaneModeRepository;
    public final CoroutineContext mBgCoroutineContext;
    public final HotspotAndDataSaverCallbacks mCallbacks;
    public int mDefaultDataPhoneId;
    public int mDefaultDataSubscriptionId;
    public final HostSpotDetailAdapter mDetailAdapter;
    public final OplusQSDetailInteractor mDetailInteractor;
    public boolean mIsLocalHotspotRunning;
    public final KeyguardStateController mKeyguard;
    public final NetworkController mNetworkController;
    public boolean mNoSimError;
    public final SatellitePolicy mOplusSatellitePolicy;
    public final PanelInteractor mPanelInteractor;
    public final AnonymousClass1 mSatellitePolicyCallback;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final HotspotSignalCallback mSignalCallback;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public final VzAuthResult mVzAuthResult;
    public final OplusHotspotTile$$ExternalSyntheticLambda1 mWifiApStateChanged;
    public final WifiManager mWifiManager;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusHotspotTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements SatellitePolicy.IConnectionCallback {
        public /* synthetic */ OplusHotspotTile this$0;

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public /* bridge */ /* synthetic */ void onCarrierNameChanged(String str) {
            super.onCarrierNameChanged(str);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteConnected() {
            this.this$0.refreshState((Object) null);
        }

        @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
        public final void onSatelliteDisConnected() {
            this.this$0.refreshState((Object) null);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HostSpotDetailAdapter implements DetailAdapter, HotspotController.Callback {
        public OplusQSDetailItems mItems;
        public /* synthetic */ OplusHotspotTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            this.mItems = oplusQSDetailItemsConvertOrInflate;
            oplusQSDetailItemsConvertOrInflate.setTagSuffix(OplusQSHighlightTileViewLabelTextHandler.HOTSPOT);
            this.mItems.setTag(OplusQSHighlightTileViewLabelTextHandler.HOTSPOT);
            ((HotspotTile) this.this$0).mHotspotController.addCallback(this);
            updateItem();
            return this.mItems;
        }

        public int getMetricsCategory() {
            return 120;
        }

        public Intent getSettingsIntent() {
            return this.this$0.getLongClickIntent();
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.HOTSPOT;
        }

        public CharSequence getTitle() {
            return ((QSTileImpl) this.this$0).mContext.getString(2131955671);
        }

        public Boolean getToggleState() {
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) this.this$0).mState).value);
        }

        public final boolean hasFooterDivider() {
            return DeviceUtil.isLandscape(((QSTileImpl) this.this$0).mContext);
        }

        public final void onDismiss() {
            ((HotspotTile) this.this$0).mHotspotController.removeCallback(this);
        }

        public final void onHotspotChanged(int i, boolean z) {
            updateItem();
        }

        public void setToggleState(boolean z) {
            this.this$0.onDetailToggle(QSTileEx.mapToUiState(z));
        }

        public final void updateItem() {
            OplusHotspotTile oplusHotspotTile = this.this$0;
            HotspotController hotspotController = ((HotspotTile) oplusHotspotTile).mHotspotController;
            if (this.mItems == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            SoftApConfiguration softApConfiguration = oplusHotspotTile.mWifiManager.getSoftApConfiguration();
            OplusQSDetailItems.Item item = new OplusQSDetailItems.Item();
            item.line1 = BidiFormatter.getInstance().unicodeWrap(softApConfiguration.getSsid());
            item.line2 = ((QSTileImpl) oplusHotspotTile).mContext.getString(2131955627);
            item.viewType = 6;
            arrayList.add(item);
            if (!oplusHotspotTile.mKeyguard.mShowing || oplusHotspotTile.mUpdateMonitor.canSkipBouncer(oplusHotspotTile.mSelectedUserInteractor.getSelectedUserId())) {
                OplusQSDetailItems.Item item2 = new OplusQSDetailItems.Item();
                item2.line1 = BidiFormatter.getInstance().unicodeWrap(softApConfiguration.getPassphrase());
                item2.line2 = ((QSTileImpl) oplusHotspotTile).mContext.getString(2131955626);
                item2.viewType = 6;
                arrayList.add(item2);
            }
            OplusQSDetailItems.Item item3 = new OplusQSDetailItems.Item();
            item3.line1 = ((QSTileImpl) oplusHotspotTile).mContext.getResources().getQuantityString(2131820574, hotspotController.getNumConnectedDevices(), String.format(Locale.getDefault(), "%d", Integer.valueOf(hotspotController.getNumConnectedDevices())));
            item3.viewType = 6;
            arrayList.add(item3);
            this.mItems.setListItems(0, arrayList, false);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HotspotAndDataSaverCallbacks implements HotspotController.Callback, DataSaverController.Listener {
        public Info mInfo;
        public /* synthetic */ OplusHotspotTile this$0;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Info {
            public boolean isDataSaverEnabled;
            public boolean isHotspotEnabled;
            public int numConnectedDevices;

            public final String toString() {
                StringBuilder sb = new StringBuilder("CallbackInfo[isHotspotEnabled=");
                sb.append(this.isHotspotEnabled);
                sb.append(",numConnectedDevices=");
                sb.append(this.numConnectedDevices);
                sb.append(",isDataSaverEnabled=");
                return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.isDataSaverEnabled, ']');
            }
        }

        public final void onDataSaverChanged(boolean z) {
            this.mInfo.isDataSaverEnabled = z;
            this.this$0.refreshState((Object) null);
        }

        public final void onHotspotAvailabilityChanged(boolean z) {
            OplusHotspotTile oplusHotspotTile = this.this$0;
            if (z) {
                return;
            }
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) oplusHotspotTile).TAG, "Tile removed. Hotspot no longer available");
            ((QSTileImpl) oplusHotspotTile).mHost.removeTile(oplusHotspotTile.getTileSpec());
        }

        public final void onHotspotChanged(int i, boolean z) {
            Info info = this.mInfo;
            info.isHotspotEnabled = z;
            info.numConnectedDevices = i;
            OplusHotspotTile oplusHotspotTile = this.this$0;
            oplusHotspotTile.mDetailInteractor.updateSwitchState(z);
            oplusHotspotTile.getTileEx().onFwStateChanged(oplusHotspotTile, QSTileEx.mapToUiState(z), ((QSTileImpl) oplusHotspotTile).mClickHandler);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HotspotSignalCallback implements SignalCallback {
        public /* synthetic */ OplusHotspotTile this$0;

        public /* bridge */ /* synthetic */ void setCallIndicator(IconState iconState, int i) {
            super.setCallIndicator(iconState, i);
        }

        public /* bridge */ /* synthetic */ void setEthernetIndicators(IconState iconState) {
            super.setEthernetIndicators(iconState);
        }

        public void setHasAnySimReady(boolean z) {
            OplusHotspotTile oplusHotspotTile = this.this$0;
            QsOperatorUtils qsOperatorUtils = QsOperatorUtils.INSTANCE;
            if (!CustomizeFeatureOption.sIsShowSprintHotSpotDia) {
                int i = oplusHotspotTile.mDefaultDataPhoneId;
                if ((!CustomizeFeatureOption.checkSlotIdValid(i) ? -1 : CustomizeFeatureOption.sTetheringEntitlement[i]) == -1) {
                    return;
                }
            }
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("setHasAnySimReady / simReady:", LogUtil.TAG_QS, ((QSTileImpl) oplusHotspotTile).TAG, z);
            oplusHotspotTile.mNoSimError = !z;
            if (z) {
                oplusHotspotTile.refreshState((Object) null);
            } else {
                ((HotspotTile) oplusHotspotTile).mHotspotController.getClass();
            }
        }

        public /* bridge */ /* synthetic */ void setIsAirplaneMode(IconState iconState) {
            super.setIsAirplaneMode(iconState);
        }

        public /* bridge */ /* synthetic */ void setMobileDataEnabled(boolean z) {
            super.setMobileDataEnabled(z);
        }

        public /* bridge */ /* synthetic */ void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            super.setMobileDataIndicators(mobileDataIndicators);
        }

        public /* bridge */ /* synthetic */ void setSubs(List list) {
            super.setSubs(list);
        }

        public /* bridge */ /* synthetic */ void setWifiIndicators(WifiIndicators wifiIndicators) {
            super.setWifiIndicators(wifiIndicators);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VzAuthResult implements QsOperatorUtils.MhsAuthResultCallback {
        public /* synthetic */ OplusHotspotTile this$0;

        @Override // com.oplus.systemui.qs.utils.QsOperatorUtils.MhsAuthResultCallback
        public final void onAuthResult(boolean z) {
            OplusHotspotTile oplusHotspotTile = this.this$0;
            String str = ((QSTileImpl) oplusHotspotTile).TAG;
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("onAuthResult success=", ",isEnabled:", z);
            sbM.append(!((QSTile.AdapterState) ((QSTileImpl) oplusHotspotTile).mState).value);
            sbM.append(",subid:");
            sbM.append(oplusHotspotTile.mDefaultDataSubscriptionId);
            sbM.append(",phoneid: ");
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(oplusHotspotTile.mDefaultDataPhoneId, LogUtil.TAG_QS, str, sbM);
            if (!z) {
                oplusHotspotTile.closeHotspot();
                return;
            }
            boolean z2 = ((QSTile.AdapterState) ((QSTileImpl) oplusHotspotTile).mState).value;
            boolean z3 = !z2;
            oplusHotspotTile.refreshState(!z2 ? QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING : null);
            Settings.Global.putInt(((QSTileImpl) oplusHotspotTile).mContext.getContentResolver(), "wifi_ap_state", z3 ? 1 : 0);
            ((HotspotTile) oplusHotspotTile).mHotspotController.setHotspotEnabled(z3);
        }
    }

    public OplusHotspotTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, HotspotController hotspotController, DataSaverController dataSaverController, KeyguardStateController keyguardStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, NetworkController networkController, PanelInteractor panelInteractor, OplusQSDetailInteractor.Factory factory, SelectedUserInteractor selectedUserInteractor, SatellitePolicy satellitePolicy, AirplaneModeRepository airplaneModeRepository, CoroutineContext coroutineContext, UserManager userManager) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, hotspotController, dataSaverController, userManager);
        OplusHotspotTile$$ExternalSyntheticLambda1 oplusHotspotTile$$ExternalSyntheticLambda1 = new OplusHotspotTile$$ExternalSyntheticLambda1();
        oplusHotspotTile$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mWifiApStateChanged = oplusHotspotTile$$ExternalSyntheticLambda1;
        HotspotSignalCallback hotspotSignalCallback = new HotspotSignalCallback();
        hotspotSignalCallback.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSignalCallback = hotspotSignalCallback;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = anonymousClass1;
        HotspotAndDataSaverCallbacks hotspotAndDataSaverCallbacks = new HotspotAndDataSaverCallbacks();
        hotspotAndDataSaverCallbacks.this$0 = this;
        hotspotAndDataSaverCallbacks.mInfo = new HotspotAndDataSaverCallbacks.Info();
        VarHandle.storeStoreFence();
        this.mCallbacks = hotspotAndDataSaverCallbacks;
        this.mNoSimError = false;
        VzAuthResult vzAuthResult = new VzAuthResult();
        vzAuthResult.this$0 = this;
        VarHandle.storeStoreFence();
        this.mVzAuthResult = vzAuthResult;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mNetworkController = networkController;
        WifiApStateReceiver wifiApStateReceiver = WifiApStateReceiver.INSTANCE;
        wifiApStateReceiver.register(((QSTileImpl) this).mContext, UserHandle.ALL, (Handler) null);
        wifiApStateReceiver.addListener(oplusHotspotTile$$ExternalSyntheticLambda1);
        HostSpotDetailAdapter hostSpotDetailAdapter = new HostSpotDetailAdapter();
        hostSpotDetailAdapter.this$0 = this;
        VarHandle.storeStoreFence();
        this.mDetailAdapter = hostSpotDetailAdapter;
        this.mWifiManager = (WifiManager) ((QSTileImpl) this).mContext.getSystemService(OplusQSHighlightTileViewLabelTextHandler.WIFI);
        this.mKeyguard = keyguardStateController;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mPanelInteractor = panelInteractor;
        this.mDetailInteractor = factory.create(hostSpotDetailAdapter);
        this.mOplusSatellitePolicy = satellitePolicy;
        this.mAirplaneModeRepository = airplaneModeRepository;
        this.mBgCoroutineContext = coroutineContext;
    }

    public final void closeHotspot() {
        Settings.Global.putInt(((QSTileImpl) this).mContext.getContentResolver(), "wifi_ap_state", 0);
        ((HotspotTile) this).mHotspotController.setHotspotEnabled(false);
        refreshState((Object) null);
        this.mDetailInteractor.dismiss(true);
        PanelInteractorImpl panelInteractorImpl = this.mPanelInteractor;
        if (panelInteractorImpl != null) {
            panelInteractorImpl.collapsePanels();
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.dump(printWriter, strArr);
        printWriter.print("    ");
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("mIsLocalHotspotRunning："), this.mIsLocalHotspotRunning, printWriter, "mDefaultDataSubscriptionId："), this.mDefaultDataSubscriptionId, printWriter, "mDefaultDataPhoneId："), this.mDefaultDataPhoneId, printWriter, "isDataSaverEnabled： ");
        sbM.append(((HotspotTile) this).mDataSaverController.mPolicyManager.getRestrictBackground());
        printWriter.println(sbM.toString());
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237166);
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent("android.settings.OPLUS_WIFI_AP_SETTINGS");
        intent.addFlags(805339136);
        return intent;
    }

    public final String getSecondaryLabel(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        if (z2) {
            return ((QSTileImpl) this).mContext.getString(2131955816);
        }
        if (z3) {
            return ((QSTileImpl) this).mContext.getString(2131955814);
        }
        if (i < 0 || !z) {
            return null;
        }
        return ((QSTileImpl) this).mContext.getResources().getQuantityString(2131820575, i, Integer.valueOf(i));
    }

    public final void handleClick(Expandable expandable) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_HOTSPOT)) {
            return;
        }
        boolean z = ((QSTile.AdapterState) ((QSTileImpl) this).mState).value;
        boolean z2 = !z;
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("handleClick: enable=", LogUtil.TAG_QS, ((QSTileImpl) this).TAG, z2);
        if (handleUserOperationInternal$1(z2)) {
            refreshState(!z ? QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING : null);
        }
    }

    public final void handleDestroy() {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleDestroy();
        HotspotControllerImpl hotspotControllerImpl = ((HotspotTile) this).mHotspotController;
        hotspotControllerImpl.removeCallback(this.mDetailAdapter);
        HotspotAndDataSaverCallbacks hotspotAndDataSaverCallbacks = this.mCallbacks;
        hotspotControllerImpl.removeCallback(hotspotAndDataSaverCallbacks);
        ((HotspotTile) this).mDataSaverController.removeCallback(hotspotAndDataSaverCallbacks);
        WifiApStateReceiver wifiApStateReceiver = WifiApStateReceiver.INSTANCE;
        wifiApStateReceiver.removeListener(this.mWifiApStateChanged);
        wifiApStateReceiver.unregister(((QSTileImpl) this).mContext);
        this.mOplusSatellitePolicy.removeCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
    }

    public final void handleInitialize() {
        HotspotControllerImpl hotspotControllerImpl = ((HotspotTile) this).mHotspotController;
        HotspotAndDataSaverCallbacks hotspotAndDataSaverCallbacks = this.mCallbacks;
        hotspotControllerImpl.addCallback(hotspotAndDataSaverCallbacks);
        ((HotspotTile) this).mDataSaverController.addCallback(hotspotAndDataSaverCallbacks);
        this.mOplusSatellitePolicy.addCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
        Lifecycle lifecycle = getLifecycle();
        StateFlow stateFlowIsAirplaneMode = this.mAirplaneModeRepository.isAirplaneMode();
        OplusHotspotTile$$ExternalSyntheticLambda0 oplusHotspotTile$$ExternalSyntheticLambda0 = new OplusHotspotTile$$ExternalSyntheticLambda0();
        oplusHotspotTile$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        QSFlowExKt.alwaysCollectFlow(lifecycle, stateFlowIsAirplaneMode, this.mBgCoroutineContext, oplusHotspotTile$$ExternalSyntheticLambda0);
    }

    public final void handleSecondaryClick(Expandable expandable) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_HOTSPOT) || ((HotspotTile) this).mDataSaverController.mPolicyManager.getRestrictBackground() || this.mIsLocalHotspotRunning) {
            return;
        }
        this.mDetailInteractor.show(expandable != null ? expandable.getView() : null);
    }

    public final void handleSetListening(boolean z) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleSetListening(z);
        HotspotSignalCallback hotspotSignalCallback = this.mSignalCallback;
        NetworkControllerImpl networkControllerImpl = this.mNetworkController;
        if (z) {
            networkControllerImpl.addCallback(hotspotSignalCallback);
        } else {
            networkControllerImpl.removeCallback(hotspotSignalCallback);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0021  */
    public final void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z;
        boolean z2;
        int i;
        boolean z3 = obj == QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING;
        checkIfRestrictionEnforcedByAdminOnly("no_config_tethering", booleanState);
        if (z3) {
            z = true;
        } else {
            HotspotControllerImpl hotspotControllerImpl = ((HotspotTile) this).mHotspotController;
            if (hotspotControllerImpl.mWaitingForTerminalState || hotspotControllerImpl.mHotspotState == 12) {
                z = true;
            } else {
                z = false;
            }
        }
        HotspotAndDataSaverCallbacks hotspotAndDataSaverCallbacks = this.mCallbacks;
        ((QSTile.AdapterState) booleanState).value = z3 || hotspotAndDataSaverCallbacks.mInfo.isHotspotEnabled;
        HotspotAndDataSaverCallbacks.Info info = hotspotAndDataSaverCallbacks.mInfo;
        int i2 = info.numConnectedDevices;
        boolean z4 = info.isDataSaverEnabled;
        ((QSTile.State) booleanState).isTransient = z;
        if (WifiEnterpriseRestrictionUtils.hasUserRestrictionFromT(((QSTileImpl) this).mHost.getUserContext(), "no_wifi_tethering")) {
            Log.w("WifiEntResUtils", "Wi-Fi Tethering isn't available due to user restriction.");
            z2 = false;
        } else {
            z2 = true;
        }
        boolean z5 = z4 || this.mIsLocalHotspotRunning || !z2;
        boolean z6 = ((QSTile.AdapterState) booleanState).value;
        boolean z7 = z6 || ((QSTile.State) booleanState).isTransient;
        if (z5) {
            i = 2131237167;
        } else {
            i = z6 ? 2131237165 : 2131237166;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(i);
        if (z5) {
            ((QSTile.State) booleanState).state = 0;
        } else {
            ((QSTile.State) booleanState).state = z7 ? 2 : 1;
        }
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955671);
        String secondaryLabel = getSecondaryLabel(i2, z7, z, z4, z2);
        ((QSTile.State) booleanState).secondaryLabel = secondaryLabel;
        ((QSTile.State) booleanState).importantLabel = null;
        if (z7 && i2 > 0) {
            ((QSTile.State) booleanState).importantLabel = secondaryLabel;
        }
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        boolean zIsEmpty = TextUtils.isEmpty(((QSTile.State) booleanState).importantLabel);
        CharSequence charSequence = ((QSTile.State) booleanState).label;
        if (zIsEmpty) {
            ((QSTile.State) booleanState).contentDescription = charSequence;
        } else {
            ((QSTile.State) booleanState).contentDescription = String.valueOf(charSequence) + ((Object) ((QSTile.State) booleanState).importantLabel);
        }
        ((QSTile.State) booleanState).dualTarget = true;
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sb = new StringBuilder("handleUpdateState: state.state=");
        sb.append(((QSTile.State) booleanState).state);
        sb.append(", state.value=");
        sb.append(((QSTile.AdapterState) booleanState).value);
        sb.append(", state.isTransient=");
        sb.append(((QSTile.State) booleanState).isTransient);
        sb.append(", state.label=");
        sb.append((Object) ((QSTile.State) booleanState).label);
        sb.append(", state.secondaryLabel=");
        sb.append((Object) ((QSTile.State) booleanState).secondaryLabel);
        sb.append(", state.importantLabel=");
        sb.append((Object) ((QSTile.State) booleanState).importantLabel);
        sb.append(", isTileUnavailable=");
        sb.append(z5);
        sb.append(", isDataSaverEnabled=");
        sb.append(z4);
        sb.append(", mIsLocalHotspotRunning=");
        LogUtil.d(LogUtil.TAG_QS, str, KeyguardLogger$.ExternalSyntheticOutline0.m(sb, this.mIsLocalHotspotRunning, ", isWifiTetheringAllowed=", z2));
        if (this.mDetailInteractor.isShowing()) {
            this.mDetailAdapter.updateItem();
        }
    }

    public final boolean handleUserOperation(Object obj) {
        LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperation: operation=" + obj);
        return handleUserOperationInternal$1(QSTileEx.UiState.ON.equals(obj));
    }

    /* JADX WARN: Code duplicated, block: B:37:0x009e  */
    public final boolean handleUserOperationInternal$1(boolean z) {
        boolean zIsVZWHotspotUnAuth;
        if (this.mIsLocalHotspotRunning) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperationInternal: local hotspot running, ignore");
            return false;
        }
        if (z && ((HotspotTile) this).mDataSaverController.mPolicyManager.getRestrictBackground()) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperationInternal: data save enabled, ignore");
            return false;
        }
        HotspotControllerImpl hotspotControllerImpl = ((HotspotTile) this).mHotspotController;
        if (z) {
            if (((QSTile.State) ((QSTileImpl) this).mState).state == 0 || hotspotControllerImpl.isHotspotEnabled()) {
                zIsVZWHotspotUnAuth = false;
            } else {
                int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
                this.mDefaultDataSubscriptionId = defaultDataSubscriptionId;
                int phoneId = SubscriptionManager.getPhoneId(defaultDataSubscriptionId);
                this.mDefaultDataPhoneId = phoneId;
                QsOperatorUtils qsOperatorUtils = QsOperatorUtils.INSTANCE;
                boolean z2 = CustomizeFeatureOption.sIsShowSprintHotSpotDia;
                KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
                if (!z2) {
                    if ((!CustomizeFeatureOption.checkSlotIdValid(phoneId) ? -1 : CustomizeFeatureOption.sTetheringEntitlement[phoneId]) == -1) {
                        int i = this.mDefaultDataPhoneId;
                        if (!CustomizeFeatureOption.checkSlotIdValid(i) ? false : CustomizeFeatureOption.sVZWTethering[i]) {
                            zIsVZWHotspotUnAuth = QsOperatorUtils.isVZWHotspotUnAuth(((QSTileImpl) this).mContext, this.mVzAuthResult, this.mDefaultDataSubscriptionId, 0, keyguardStateControllerImpl.mShowing);
                        }
                    } else if (QsOperatorUtils.isATTHotspotUnAuth(((QSTileImpl) this).mContext, 0, this.mDefaultDataPhoneId)) {
                        closeHotspot();
                        zIsVZWHotspotUnAuth = true;
                    }
                    zIsVZWHotspotUnAuth = false;
                } else if (QsOperatorUtils.isSprintHotspotUnAuth(((QSTileImpl) this).mContext, this.mNoSimError, 0, keyguardStateControllerImpl.mShowing)) {
                    closeHotspot();
                    zIsVZWHotspotUnAuth = true;
                } else {
                    zIsVZWHotspotUnAuth = false;
                }
            }
            if (zIsVZWHotspotUnAuth) {
                LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperationInternal: operator un auth, ignore");
                return false;
            }
        }
        Settings.Global.putInt(((QSTileImpl) this).mContext.getContentResolver(), "wifi_ap_state", z ? 1 : 0);
        hotspotControllerImpl.setHotspotEnabled(z);
        return true;
    }

    public final boolean isAvailable() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTabletWifi()) {
            return false;
        }
        boolean zIsHotspotSupported = ((HotspotTile) this).mHotspotController.isHotspotSupported();
        if (!zIsHotspotSupported) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "HotspotSupported is false");
        }
        return zIsHotspotSupported;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public final QSTile.BooleanState m335newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_hotspot_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_GREEN_COLOR;
        return booleanState;
    }

    public final void preloadForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.prepareDetailView();
    }

    public final void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.releaseDetailView();
    }
}
