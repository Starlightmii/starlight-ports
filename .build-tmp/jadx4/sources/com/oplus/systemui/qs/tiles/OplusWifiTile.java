package com.oplus.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import androidx.media3.common.Timeline$;
import androidx.slice.widget.RowView$;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.OplusAccessPointControllerEx;
import com.android.systemui.qs.OplusQuickSettingsEx;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.connectivity.OplusQSNetworkControllerEx;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractorImpl;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.AccessPointControllerImpl;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.WifiIcons;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.dialog.OplusQSDetailItems;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.detail.adapter.QSTileInlineDetailAdapter;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.qs.observer.PhoneAntiTheftLocationObserver;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplus.systemui.statusbar.pipeline.rus.OplusStatusBarRomUpdateManager;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper$configWifiDetailView$1;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusWifiTile extends WifiTile {
    public static final NetworkRequest REQUEST = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final ConnectivityManager mConnectivityManager;
    public String mCurConnectedSSID;
    public final DetailAdapter mDetailAdapter;
    public final OplusQSDetailInteractor mDetailInteractor;
    public boolean mDetailListening;
    public boolean mIsCaptivePortal;
    public final KeyguardStateController mKeyguard;
    public final AnonymousClass2 mNetworkCallback;
    public final SatellitePolicy mOplusSatellitePolicy;
    public final OplusAccessPointControllerEx mOplusWifiControllerExt;
    public final PanelInteractor mPanelInteractor;
    public final AnonymousClass3 mReceiver;
    public final AnonymousClass1 mSatellitePolicyCallback;
    public Network mTempNetWork;
    public final UserTracker mUserTracker;
    public boolean mWifi7HasLoad;
    public final WifiManager mWifiManager;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusWifiTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements SatellitePolicy.IConnectionCallback {
        public /* synthetic */ OplusWifiTile this$0;

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
    public final class ColorWifiDetailAdapter implements DetailAdapter, AccessPointController.AccessPointCallback, OplusQSDetailItems.Callback {
        public AccessPoint[] mAPs;
        public String mCacheConnectedSSID;
        public Handler mCloseRefreshHandler;
        public Network mCurrentNetwork;
        public boolean mIsScanning;
        public int mItemCount;
        public OplusQSDetailItems mItems;
        public /* synthetic */ OplusWifiTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            OplusQSDetailItems oplusQSDetailItemsConvertOrInflate = OplusQSDetailItems.convertOrInflate(context, viewGroup, z);
            this.mItems = oplusQSDetailItemsConvertOrInflate;
            oplusQSDetailItemsConvertOrInflate.setTagSuffix("Wifi");
            OplusQSDetailItems oplusQSDetailItems = this.mItems;
            oplusQSDetailItems.mActiveTitle = 2131955630;
            oplusQSDetailItems.mAvailableTitle = 2131955629;
            oplusQSDetailItems.mAvailableRefreshTitle = 2131955628;
            this.mItems.setCallback(this);
            this.mItems.setRefreshing(true);
            startRefreshWifi();
            return this.mItems;
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public int getMetricsCategory() {
            return 152;
        }

        public Intent getSettingsIntent() {
            return WifiTile.WIFI_SETTINGS;
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.WIFI;
        }

        public CharSequence getTitle() {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            return ((QSTileImpl) oplusWifiTile).mContext.getString(2131955875);
        }

        public Boolean getToggleState() {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) oplusWifiTile).mState).value);
        }

        public final boolean hasFooterDivider() {
            return true;
        }

        public final boolean isItemConnected(AccessPoint accessPoint) {
            OplusWifiTile oplusWifiTile = this.this$0;
            boolean z = false;
            try {
                NetworkInfo networkInfo = accessPoint.getNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    z = true;
                }
                NetworkRequest networkRequest = OplusWifiTile.REQUEST;
                LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "isItemConnected: isResult = " + z);
                return z;
            } catch (Exception unused) {
                NetworkRequest networkRequest2 = OplusWifiTile.REQUEST;
                LogUtil.e(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "get ItemConnected failed");
                return z;
            }
        }

        public final void onAccessPointsChanged(List list) {
            OplusWifiTile oplusWifiTile = this.this$0;
            List accessPoints = oplusWifiTile.mWifiController.getAccessPoints();
            if (accessPoints != null) {
                NetworkRequest networkRequest = OplusWifiTile.REQUEST;
                LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "AccessPoints :" + accessPoints.size());
                AccessPoint[] accessPointArr = (AccessPoint[]) accessPoints.toArray(new AccessPoint[accessPoints.size()]);
                this.mAPs = accessPointArr;
                if (accessPointArr != null) {
                    int i = 0;
                    for (AccessPoint accessPoint : accessPointArr) {
                        if (accessPoint.isReachable()) {
                            i++;
                        }
                    }
                    AccessPoint[] accessPointArr2 = this.mAPs;
                    if (i != accessPointArr2.length) {
                        this.mAPs = new AccessPoint[i];
                        int i2 = 0;
                        for (AccessPoint accessPoint2 : accessPointArr2) {
                            if (accessPoint2.isReachable()) {
                                this.mAPs[i2] = accessPoint2;
                                i2++;
                            }
                        }
                    }
                }
            }
            updateItems();
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemClick(OplusQSDetailItems.Item item) {
            Object obj;
            OplusWifiTile oplusWifiTile = this.this$0;
            if (item == null || (obj = item.tag) == null) {
                return;
            }
            AccessPoint accessPoint = (AccessPoint) obj;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "onDetailItemClick: accessPoint = " + LogUtil.getLogSafeMessage(accessPoint.getTitle()));
            if (accessPoint.isActive() || isItemConnected(accessPoint)) {
                if (accessPoint.isActive() && isItemConnected(accessPoint)) {
                    QSTileImpl.H h = ((QSTileImpl) oplusWifiTile).mHandler;
                    OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0(2);
                    externalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    h.post(externalSyntheticLambda0);
                    return;
                }
                return;
            }
            AccessPointControllerImpl accessPointControllerImpl = oplusWifiTile.mWifiController;
            WifiTracker wifiTracker = accessPointControllerImpl.mWifiTracker;
            WifiManager.ActionListener actionListener = accessPointControllerImpl.mConnectListener;
            if (!accessPoint.isSaved()) {
                int i = 0;
                if (!accessPoint.isOpenNetwork()) {
                    Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                    intent.putExtra("wifi_start_connect_ssid", accessPoint.getSsid());
                    intent.addFlags(268435456);
                    Intent wifiConnectIntent = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getAccessPointControllerEx().getWifiConnectIntent(accessPoint, intent);
                    ArrayList arrayList = accessPointControllerImpl.mCallbacks;
                    int size = arrayList.size();
                    while (i < size) {
                        Object obj2 = arrayList.get(i);
                        i++;
                        ((AccessPointController.AccessPointCallback) obj2).onSettingsActivityTriggered(wifiConnectIntent);
                    }
                    PanelInteractorImpl panelInteractorImpl = oplusWifiTile.mPanelInteractor;
                    if (panelInteractorImpl != null) {
                        panelInteractorImpl.collapsePanels();
                    }
                    oplusWifiTile.mDetailInteractor.dismiss(true);
                    return;
                }
                if (!accessPoint.isOpenNetwork()) {
                    Timeline$.ExternalSyntheticBUOutline0.m();
                    return;
                }
                if (accessPoint.mConfig == null) {
                    WifiConfiguration wifiConfiguration = new WifiConfiguration();
                    accessPoint.mConfig = wifiConfiguration;
                    wifiConfiguration.SSID = Bundler$.ExternalSyntheticOutline0.m("\"", accessPoint.ssid, "\"");
                    int i2 = accessPoint.security;
                    WifiConfiguration wifiConfiguration2 = accessPoint.mConfig;
                    if (i2 == 0) {
                        wifiConfiguration2.allowedKeyManagement.set(0);
                    } else {
                        wifiConfiguration2.allowedKeyManagement.set(9);
                        accessPoint.mConfig.requirePmf = true;
                    }
                }
                wifiTracker.getManager().connect(accessPoint.getConfig(), actionListener);
            } else if (!((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getAccessPointControllerEx().connect(accessPoint, actionListener, wifiTracker)) {
                wifiTracker.getManager().connect(accessPoint.getConfig().networkId, actionListener);
            }
            oplusWifiTile.mIsCaptivePortal = true;
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onDetailItemDisconnect(OplusQSDetailItems.Item item) {
        }

        public final void onDismiss() {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "mQsDialog is onStop");
            oplusWifiTile.setDetailListening(false);
            oplusWifiTile.mConnectivityManager.unregisterNetworkCallback(oplusWifiTile.mNetworkCallback);
        }

        @Override // com.oplus.systemui.qs.base.dialog.OplusQSDetailItems.Callback
        public final void onRefresh$1() {
            startRefreshWifi();
        }

        public final void onSettingsActivityTriggered(Intent intent) {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            ((QSTileImpl) oplusWifiTile).mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
        }

        public final void onShow() {
            this.mItems.fireScanStateChanged(true);
            updateItems();
            OplusWifiTile oplusWifiTile = this.this$0;
            oplusWifiTile.mConnectivityManager.registerNetworkCallback(OplusWifiTile.REQUEST, oplusWifiTile.mNetworkCallback);
        }

        public final void onWifiScan(boolean z) {
        }

        public void setItemsVisible(boolean z) {
            OplusQSDetailItems oplusQSDetailItems = this.mItems;
            if (oplusQSDetailItems == null) {
                return;
            }
            oplusQSDetailItems.setItemsVisible(z);
        }

        public void setToggleState(boolean z) {
            OplusWifiTile oplusWifiTile = this.this$0;
            QSTileEx.UiState uiStateMapToUiState = QSTileEx.mapToUiState(z);
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            oplusWifiTile.onDetailToggle(uiStateMapToUiState);
        }

        public final void startRefreshWifi() {
            OplusWifiTile oplusWifiTile = this.this$0;
            oplusWifiTile.mWifiController.scanForAccessPoints();
            Handler handler = this.mCloseRefreshHandler;
            handler.removeMessages(6);
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            handler.sendMessageDelayed(((QSTileImpl) oplusWifiTile).mHandler.obtainMessage(6, Boolean.FALSE), 3000L);
        }

        public final boolean turnOnSwitchWhenAttach() {
            return true;
        }

        public final void updateItems() {
            OplusWifiTile oplusWifiTile = this.this$0;
            QSTileImpl.H h = ((QSTileImpl) oplusWifiTile).mHandler;
            if (this.mAPs == null) {
                return;
            }
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "updateItems start， mItems = " + this.mItems);
            if (this.mItems == null || !oplusWifiTile.mDetailListening) {
                LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "updateItems end");
                return;
            }
            if (oplusWifiTile.mDetailInteractor.isShowing()) {
                OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0(0);
                externalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                h.postDelayed(externalSyntheticLambda0, 400L);
                if (!oplusWifiTile.mSignalCallback.mInfo.enabled) {
                    LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "updateItems end -> Wifi is off");
                    return;
                }
                OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusWifiTile$ColorWifiDetailAdapter$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                h.post(externalSyntheticLambda1);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusWifiSignalCallback extends WifiTile.WifiSignalCallback {
        public /* synthetic */ OplusWifiTile this$0;

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setCallIndicator(IconState iconState, int i) {
            super.setCallIndicator(iconState, i);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setEthernetIndicators(IconState iconState) {
            super.setEthernetIndicators(iconState);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setHasAnySimReady(boolean z) {
            super.setHasAnySimReady(z);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setIsAirplaneMode(IconState iconState) {
            super.setIsAirplaneMode(iconState);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setMobileDataEnabled(boolean z) {
            super.setMobileDataEnabled(z);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            super.setMobileDataIndicators(mobileDataIndicators);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public /* bridge */ /* synthetic */ void setSubs(List list) {
            super.setSubs(list);
        }

        @Override // com.oplus.systemui.qs.tiles.WifiTile.WifiSignalCallback
        public void setWifiIndicators(WifiIndicators wifiIndicators) {
            super.setWifiIndicators(wifiIndicators);
            OplusWifiTile oplusWifiTile = this.this$0;
            if (oplusWifiTile.mDetailInteractor.isShowing()) {
                DetailAdapter detailAdapter = oplusWifiTile.mDetailAdapter;
                if (detailAdapter instanceof ColorWifiDetailAdapter) {
                    ((ColorWifiDetailAdapter) detailAdapter).updateItems();
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WifiInlineDetailAdapter implements DetailAdapter {
        public QSTileInlineDetailAdapter mDelegate;
        public /* synthetic */ OplusWifiTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            return this.mDelegate.createDetailView(context, viewGroup, z);
        }

        public DetailAdapter.DetailHeightType getDetailHeightType() {
            return DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
        }

        public Intent getSettingsIntent() {
            return WifiTile.WIFI_SETTINGS;
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.WIFI;
        }

        public CharSequence getTitle() {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            return ((QSTileImpl) oplusWifiTile).mContext.getString(2131955875);
        }

        public Boolean getToggleState() {
            OplusWifiTile oplusWifiTile = this.this$0;
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) oplusWifiTile).mState).value);
        }

        public final boolean hasFooterDivider() {
            return true;
        }

        public void setToggleState(boolean z) {
            OplusWifiTile oplusWifiTile = this.this$0;
            QSTileEx.UiState uiStateMapToUiState = QSTileEx.mapToUiState(z);
            NetworkRequest networkRequest = OplusWifiTile.REQUEST;
            oplusWifiTile.onDetailToggle(uiStateMapToUiState);
        }

        public final boolean turnOnSwitchWhenAttach() {
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.oplus.systemui.qs.tiles.OplusWifiTile$2] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.oplus.systemui.qs.tiles.OplusWifiTile$3] */
    public OplusWifiTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, NetworkController networkController, AccessPointController accessPointController, WifiManager wifiManager, ConnectivityManager connectivityManager, PanelInteractor panelInteractor, SatellitePolicy satellitePolicy, KeyguardStateController keyguardStateController, UserTracker userTracker, OplusQSDetailInteractor.Factory factory, QSTileInlineDetailAdapter.Factory factory2, BroadcastDispatcher broadcastDispatcher) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, networkController, accessPointController);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = anonymousClass1;
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.oplus.systemui.qs.tiles.OplusWifiTile.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                OplusWifiTile oplusWifiTile = OplusWifiTile.this;
                NetworkRequest networkRequest = OplusWifiTile.REQUEST;
                QSTileImpl.H h = ((QSTileImpl) oplusWifiTile).mHandler;
                OplusWifiTile$2$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusWifiTile$2$.ExternalSyntheticLambda0();
                externalSyntheticLambda0.f$0 = this;
                externalSyntheticLambda0.f$1 = network;
                externalSyntheticLambda0.f$2 = networkCapabilities;
                VarHandle.storeStoreFence();
                h.post(externalSyntheticLambda0);
            }
        };
        this.mWifi7HasLoad = false;
        this.mIsCaptivePortal = false;
        this.mCurConnectedSSID = "";
        this.mReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.tiles.OplusWifiTile.3
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                int intExtra;
                if (!"android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction()) || (intExtra = intent.getIntExtra("wifi_state", 1)) == 4) {
                    return;
                }
                if (intExtra == 3) {
                    OplusWifiTile.this.mDetailInteractor.updateSwitchState(true);
                } else if (intExtra == 1) {
                    OplusWifiTile.this.mDetailInteractor.updateSwitchState(false);
                }
            }
        };
        this.mWifiManager = wifiManager;
        this.mConnectivityManager = connectivityManager;
        this.mOplusWifiControllerExt = ((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getAccessPointControllerEx();
        if (SystemUIInlineViewHelper.isSupportWifiDetail(((QSTileImpl) this).mContext)) {
            SystemUIInlineViewHelper.setComponentProvider("oplus.inlineui.category.QS_DETAIL_WIFI", SystemUIInlineViewHelper$configWifiDetailView$1.INSTANCE);
            SystemUIInlineViewHelper.setViewFactory$default("oplus.inlineui.category.QS_DETAIL_WIFI");
            QSTileInlineDetailAdapter qSTileInlineDetailAdapterCreate = factory2.create(OplusQSHighlightTileViewLabelTextHandler.WIFI, WifiTile.WIFI_SETTINGS);
            WifiInlineDetailAdapter wifiInlineDetailAdapter = new WifiInlineDetailAdapter();
            wifiInlineDetailAdapter.this$0 = this;
            wifiInlineDetailAdapter.mDelegate = qSTileInlineDetailAdapterCreate;
            VarHandle.storeStoreFence();
            this.mDetailAdapter = wifiInlineDetailAdapter;
        } else {
            ColorWifiDetailAdapter colorWifiDetailAdapter = new ColorWifiDetailAdapter();
            colorWifiDetailAdapter.this$0 = this;
            Looper mainLooper = Looper.getMainLooper();
            ColorWifiDetailAdapter.1 r5 = new ColorWifiDetailAdapter.1();
            r5.this$1 = colorWifiDetailAdapter;
            VarHandle.storeStoreFence();
            colorWifiDetailAdapter.mCloseRefreshHandler = new Handler(mainLooper, r5);
            colorWifiDetailAdapter.mCacheConnectedSSID = "";
            VarHandle.storeStoreFence();
            this.mDetailAdapter = colorWifiDetailAdapter;
        }
        this.mPanelInteractor = panelInteractor;
        this.mOplusSatellitePolicy = satellitePolicy;
        this.mKeyguard = keyguardStateController;
        this.mUserTracker = userTracker;
        this.mDetailInteractor = factory.create(this.mDetailAdapter);
        this.mBroadcastDispatcher = broadcastDispatcher;
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final WifiTile.WifiSignalCallback createSignalCallback() {
        OplusWifiSignalCallback oplusWifiSignalCallback = new OplusWifiSignalCallback();
        oplusWifiSignalCallback.this$0 = this;
        ((WifiTile.WifiSignalCallback) oplusWifiSignalCallback).this$0 = this;
        oplusWifiSignalCallback.mInfo = new WifiTile.CallbackInfo();
        VarHandle.storeStoreFence();
        return oplusWifiSignalCallback;
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.print("    ");
        OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("mDetailListening："), this.mDetailListening, printWriter);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237259);
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final void handleClick(Expandable expandable) {
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, new StringBuilder("handleClick: mState.value="), ((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
        handleUserOperationInternal$4(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final void handleDestroy() {
        super.handleDestroy();
        this.mOplusSatellitePolicy.removeCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
        this.mBroadcastDispatcher.unregisterReceiver(this.mReceiver);
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final void handleInitialize() {
        super.handleInitialize();
        this.mOplusSatellitePolicy.addCallback((SatellitePolicy.IConnectionCallback) this.mSatellitePolicyCallback);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.mBroadcastDispatcher.registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final void handleSecondaryClick(Expandable expandable) {
        if (!this.mWifiController.canConfigWifi()) {
            ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.WIFI_SETTINGS"), 0);
            return;
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isShowPhoneAntiTheftLocation() && PhoneAntiTheftLocationObserver.Companion.getInstance().mIsPhoneAntiTheftLocation && OsBinderCacheUtils.getCurrentUserId() == 0) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
            if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
                ActivityStarter activityStarter = ((QSTileImpl) this).mActivityStarter;
                OplusWifiTile$$ExternalSyntheticLambda2 oplusWifiTile$$ExternalSyntheticLambda2 = new OplusWifiTile$$ExternalSyntheticLambda2(0);
                oplusWifiTile$$ExternalSyntheticLambda2.f$0 = this;
                oplusWifiTile$$ExternalSyntheticLambda2.f$1 = expandable;
                VarHandle.storeStoreFence();
                activityStarter.postQSRunnableDismissingKeyguard(oplusWifiTile$$ExternalSyntheticLambda2);
                return;
            }
        }
        showOplusQSDetailDialogProvider(expandable);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (z || !this.mDetailListening) {
            return;
        }
        setDetailListening(false);
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    public final void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        WifiTile.CallbackInfo callbackInfo = this.mSignalCallback.mInfo;
        boolean z = false;
        boolean z2 = !QSTileEx.UiState.OFF.equals(obj) && (QSTileEx.UiState.ON.equals(obj) || callbackInfo.enabled);
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sb = new StringBuilder("handleUpdateState: arg: ");
        sb.append(obj);
        sb.append(", cb.enabled: ");
        LogUtil.d(LogUtil.TAG_QS, str, KeyguardLogger$.ExternalSyntheticOutline0.m(sb, callbackInfo.enabled, ", enabled: ", z2));
        OplusQSNetworkControllerEx qsNetworkControllerEx = getQuickSettingsEx().getQsNetworkControllerEx();
        if (callbackInfo.enabled) {
            int i = callbackInfo.wifiSignalIconId;
            if (i <= 0) {
                LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected wifiSignalIconId is less than 0");
            } else if (callbackInfo.ssid == null && (i == 17303107 || i == WifiIcons.QS_WIFI_DISCONNECTED)) {
                LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected cb.wifiSignalIconId than WifiIcons.QS_WIFI_NO_NETWORK equals:true");
            } else {
                if (qsNetworkControllerEx.getIsWifiToData()) {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isExpRegion()) {
                        OplusStatusBarRomUpdateManager companion = OplusStatusBarRomUpdateManager.Companion.getInstance(((QSTileImpl) this).mContext);
                        boolean showWifiIndicatorWhenEnabled = companion != null ? companion.getShowWifiIndicatorWhenEnabled() : true;
                        if (CustomizeFeatureOption.sWlanIconAlwaysOn && showWifiIndicatorWhenEnabled) {
                            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected showWifiIndicatorWhenEnabled");
                        }
                    } else {
                        LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected FeatureOption.INSTANCE.isExpRegion():false");
                    }
                } else {
                    LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected ext.getIsWifiToData:false");
                }
                z = true;
            }
        } else {
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "isWifiConnected cb.enable:false");
        }
        NetworkControllerImpl networkControllerImpl = this.mController;
        if (z) {
            String str2 = callbackInfo.statusLabel;
            if (!TextUtils.isEmpty(str2) && networkControllerImpl.hasWifiInternet()) {
                str2 = "";
            }
            ((QSTile.State) booleanState).secondaryLabel = str2;
        } else {
            ((QSTile.State) booleanState).secondaryLabel = "";
        }
        ((QSTile.State) booleanState).state = 2;
        ((QSTile.State) booleanState).dualTarget = !this.mUserTracker.getUserInfo().isGuest();
        ((QSTile.AdapterState) booleanState).value = z2;
        String str3 = ((QSTileImpl) this).TAG;
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("handleUpdateState: wifiConnected: ", ", cb.statusLabel: ", z);
        sbM.append(callbackInfo.statusLabel);
        sbM.append(", state.value: ");
        sbM.append(((QSTile.AdapterState) booleanState).value);
        sbM.append(" secondaryLabel: ");
        sbM.append((Object) ((QSTile.State) booleanState).secondaryLabel);
        sbM.append(" cb.wifiSignalIconId:");
        sbM.append(callbackInfo.wifiSignalIconId);
        LogUtil.d(LogUtil.TAG_QS, str3, sbM.toString());
        StringBuilder sb2 = new StringBuilder();
        Resources resources = ((QSTileImpl) this).mContext.getResources();
        this.mCurConnectedSSID = "";
        if (!((QSTile.AdapterState) booleanState).value) {
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
            ((QSTile.State) booleanState).labelDesc = "";
        } else if (z) {
            String passpointProviderFriendlyName = callbackInfo.ssid;
            if (passpointProviderFriendlyName == null) {
                NetworkControllerImpl networkControllerImpl2 = networkControllerImpl;
                WifiInfo wifiInfo = networkControllerImpl2.getDefaultNetworkCapabilities() != null ? (WifiInfo) networkControllerImpl2.getDefaultNetworkCapabilities().getTransportInfo() : null;
                if (wifiInfo != null) {
                    passpointProviderFriendlyName = (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) ? wifiInfo.getPasspointProviderFriendlyName() : wifiInfo.getSSID();
                }
            }
            String strRemoveDoubleQuotes = passpointProviderFriendlyName != null ? WifiTile.removeDoubleQuotes(passpointProviderFriendlyName) : "";
            this.mCurConnectedSSID = strRemoveDoubleQuotes;
            ((QSTile.State) booleanState).label = TextUtils.isEmpty(strRemoveDoubleQuotes) ? resources.getString(2131955875) : this.mCurConnectedSSID;
            if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
                ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955759);
            } else {
                ((QSTile.State) booleanState).labelDesc = "";
            }
        } else {
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
            if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
                ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955246);
            } else {
                ((QSTile.State) booleanState).labelDesc = "";
            }
        }
        if (((QSTile.AdapterState) booleanState).value) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237258);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237259);
        }
        sb2.append(((QSTileImpl) this).mContext.getString(2131955875));
        sb2.append(OplusPreventModeData.SEPARATOR);
        if (((QSTile.AdapterState) booleanState).value && z) {
            sb2.append(callbackInfo.wifiSignalContentDescription);
            sb2.append(OplusPreventModeData.SEPARATOR);
            sb2.append(WifiTile.removeDoubleQuotes(callbackInfo.ssid));
            if (!TextUtils.isEmpty(((QSTile.State) booleanState).secondaryLabel)) {
                sb2.append(OplusPreventModeData.SEPARATOR);
                sb2.append(((QSTile.State) booleanState).secondaryLabel);
            }
        }
        ((QSTile.State) booleanState).contentDescription = sb2.toString();
        ((QSTile.State) booleanState).dualLabelContentDescription = resources.getString(2131951935, getTileLabel());
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean handleUserOperation(Object obj) {
        LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperation: operation=" + obj);
        return handleUserOperationInternal$4(QSTileEx.UiState.ON.equals(obj));
    }

    public final boolean handleUserOperationInternal$4(boolean z) {
        if (!this.mWifiController.canConfigWifi()) {
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperationInternal: mWifiController.canConfigWifi() = false, go to Settings");
            ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.WIFI_SETTINGS"), 0);
            return false;
        }
        ((QSTileImpl) this).mState.copyTo(this.mStateBeforeClick);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isShowPhoneAntiTheftLocation() && PhoneAntiTheftLocationObserver.Companion.getInstance().mIsPhoneAntiTheftLocation && OsBinderCacheUtils.getCurrentUserId() == 0) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguard;
            if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing && !z) {
                LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperationInternal: postQSRunnableDismissingKeyguard to setWifiEnable false");
                ActivityStarter activityStarter = ((QSTileImpl) this).mActivityStarter;
                OplusWifiTile$$ExternalSyntheticLambda0 oplusWifiTile$$ExternalSyntheticLambda0 = new OplusWifiTile$$ExternalSyntheticLambda0(2);
                oplusWifiTile$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                activityStarter.postQSRunnableDismissingKeyguard(oplusWifiTile$$ExternalSyntheticLambda0);
                return false;
            }
        }
        this.mController.setWifiEnabled(z);
        return true;
    }

    @Override // com.oplus.systemui.qs.tiles.WifiTile
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public final QSTile.BooleanState mo340newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_wifi_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR;
        return booleanState;
    }

    public final void preloadForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.prepareDetailView();
    }

    public final void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.releaseDetailView();
    }

    public void setDetailListening(boolean z) {
        String str = ((QSTileImpl) this).TAG;
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("setDetailListening:", " dialog isShowing:", z);
        sbM.append(this.mDetailInteractor.isShowing());
        LogUtil.d(LogUtil.TAG_QS, str, sbM.toString());
        int i = 3;
        AccessPointControllerImpl accessPointControllerImpl = this.mWifiController;
        DetailAdapter detailAdapter = this.mDetailAdapter;
        QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        if (z) {
            OplusWifiTile$$ExternalSyntheticLambda0 oplusWifiTile$$ExternalSyntheticLambda0 = new OplusWifiTile$$ExternalSyntheticLambda0(0);
            oplusWifiTile$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            h.post(oplusWifiTile$$ExternalSyntheticLambda0);
            if (detailAdapter instanceof ColorWifiDetailAdapter) {
                accessPointControllerImpl.addAccessPointCallback((ColorWifiDetailAdapter) detailAdapter);
                if (!this.mWifi7HasLoad) {
                    OplusWifiTile$$ExternalSyntheticLambda0 oplusWifiTile$$ExternalSyntheticLambda1 = new OplusWifiTile$$ExternalSyntheticLambda0(i);
                    oplusWifiTile$$ExternalSyntheticLambda1.f$0 = this;
                    VarHandle.storeStoreFence();
                    h.post(oplusWifiTile$$ExternalSyntheticLambda1);
                }
            }
        } else {
            OplusWifiTile$$ExternalSyntheticLambda0 oplusWifiTile$$ExternalSyntheticLambda2 = new OplusWifiTile$$ExternalSyntheticLambda0(1);
            oplusWifiTile$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            h.post(oplusWifiTile$$ExternalSyntheticLambda2);
            if (detailAdapter instanceof ColorWifiDetailAdapter) {
                accessPointControllerImpl.removeAccessPointCallback((ColorWifiDetailAdapter) detailAdapter);
                if (!this.mWifi7HasLoad) {
                    OplusWifiTile$$ExternalSyntheticLambda0 oplusWifiTile$$ExternalSyntheticLambda3 = new OplusWifiTile$$ExternalSyntheticLambda0(i);
                    oplusWifiTile$$ExternalSyntheticLambda3.f$0 = this;
                    VarHandle.storeStoreFence();
                    h.post(oplusWifiTile$$ExternalSyntheticLambda3);
                }
            }
            this.mOplusWifiControllerExt.clear();
        }
        this.mDetailListening = z;
    }

    public final void showOplusQSDetailDialogProvider(Expandable expandable) {
        View view = expandable != null ? expandable.getView() : null;
        setDetailListening(true);
        DetailAdapter detailAdapter = this.mDetailAdapter;
        if (detailAdapter instanceof ColorWifiDetailAdapter) {
            ColorWifiDetailAdapter colorWifiDetailAdapter = (ColorWifiDetailAdapter) detailAdapter;
            OplusWifiTile oplusWifiTile = colorWifiDetailAdapter.this$0;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) oplusWifiTile).TAG, "checkAPsCacheAvailable -> mCacheID:" + ((Object) LogUtil.getLogSafeMessage(colorWifiDetailAdapter.mCacheConnectedSSID, 1, 1)) + " mCurID:" + ((Object) LogUtil.getLogSafeMessage(oplusWifiTile.mCurConnectedSSID, 1, 1)));
            if (!TextUtils.equals(colorWifiDetailAdapter.mCacheConnectedSSID, oplusWifiTile.mCurConnectedSSID)) {
                colorWifiDetailAdapter.mAPs = null;
                colorWifiDetailAdapter.mCacheConnectedSSID = "";
            }
        }
        this.mDetailInteractor.show(view);
    }
}
