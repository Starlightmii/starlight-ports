package com.oplus.systemui.qs.tiles;

import android.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SubscriptionManager;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Switch;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.net.DataUsageController;
import com.android.systemui.Prefs;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class CellularTile extends QSTileImpl {
    public final NetworkController mController;
    public final DataUsageController mDataController;
    public final KeyguardStateController mKeyguard;
    public boolean mLastWifiVisible;
    public final CellSignalCallback mSignalCallback;
    public boolean mWifiVisible;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CallbackInfo {
        public CharSequence dataContentDescription;
        public CharSequence dataSubscriptionName;
        public boolean multipleSubs;
        public boolean noSim;
        public boolean roaming;
        public int subId;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CellSignalCallback implements SignalCallback {
        public CallbackInfo mInfo;
        public /* synthetic */ CellularTile this$0;

        public /* bridge */ /* synthetic */ void setCallIndicator(IconState iconState, int i) {
            super.setCallIndicator(iconState, i);
        }

        public /* bridge */ /* synthetic */ void setEthernetIndicators(IconState iconState) {
            super.setEthernetIndicators(iconState);
        }

        public /* bridge */ /* synthetic */ void setHasAnySimReady(boolean z) {
            super.setHasAnySimReady(z);
        }

        public /* bridge */ /* synthetic */ void setIsAirplaneMode(IconState iconState) {
            super.setIsAirplaneMode(iconState);
        }

        public void setMobileDataEnabled(boolean z) {
            this.this$0.toggleMobileDataEnabled(z);
        }

        public void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            CellularTile cellularTile = this.this$0;
            NetworkController networkController = cellularTile.mController;
            CallbackInfo callbackInfo = this.mInfo;
            if (mobileDataIndicators.qsIcon == null) {
                return;
            }
            callbackInfo.dataSubscriptionName = networkController.getMobileDataNetworkName();
            callbackInfo.dataContentDescription = mobileDataIndicators.qsDescription != null ? mobileDataIndicators.typeContentDescriptionHtml : null;
            callbackInfo.roaming = mobileDataIndicators.roaming;
            callbackInfo.multipleSubs = networkController.getNumberSubscriptions() > 1;
            callbackInfo.subId = mobileDataIndicators.subId;
            cellularTile.refreshState(callbackInfo);
        }

        public final void setNoSims(boolean z, boolean z2) {
            CellularTile cellularTile = this.this$0;
            LogUtil.d(LogUtil.TAG_QS, ((QSTileImpl) cellularTile).TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("setNoSims show:", " simDetected:", z, z2));
            CallbackInfo callbackInfo = this.mInfo;
            callbackInfo.noSim = z && !z2;
            cellularTile.refreshState(callbackInfo);
        }

        public /* bridge */ /* synthetic */ void setSubs(List list) {
            super.setSubs(list);
        }

        public void setWifiIndicators(WifiIndicators wifiIndicators) {
            CellularTile cellularTile = this.this$0;
            StatusBarFeatureOption.INSTANCE.getClass();
            if (StatusBarFeatureOption.isSystemUiExpSignalUi) {
                IconState iconState = wifiIndicators.statusIcon;
                if (iconState != null) {
                    cellularTile.mWifiVisible = iconState.visible;
                }
                if (cellularTile.mLastWifiVisible != cellularTile.mWifiVisible) {
                    cellularTile.refreshState(cellularTile.mSignalCallback.mInfo);
                }
                cellularTile.mLastWifiVisible = cellularTile.mWifiVisible;
            }
        }
    }

    public CellularTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, NetworkController networkController, KeyguardStateController keyguardStateController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        CellSignalCallback cellSignalCallback = new CellSignalCallback();
        cellSignalCallback.this$0 = this;
        cellSignalCallback.mInfo = new CallbackInfo();
        VarHandle.storeStoreFence();
        this.mSignalCallback = cellSignalCallback;
        this.mWifiVisible = false;
        this.mLastWifiVisible = false;
        this.mController = networkController;
        this.mKeyguard = keyguardStateController;
        this.mDataController = networkController.getMobileDataController();
    }

    public static Intent getCellularSettingIntent() {
        Intent intent = new Intent("android.settings.NETWORK_OPERATOR_SETTINGS");
        if (SubscriptionManager.getDefaultDataSubscriptionId() != -1) {
            intent.putExtra("android.provider.extra.SUB_ID", SubscriptionManager.getDefaultDataSubscriptionId());
        }
        return intent;
    }

    public CharSequence appendMobileDataType(CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence2)) {
            return Html.fromHtml(((String) charSequence).toString(), 0);
        }
        return TextUtils.isEmpty(charSequence) ? Html.fromHtml(charSequence2.toString(), 0) : Html.fromHtml(((QSTileImpl) this).mContext.getString(2131954608, charSequence, charSequence2), 0);
    }

    public Intent getLongClickIntent() {
        return ((QSTile.State) getState()).state == 0 ? new Intent("android.settings.WIRELESS_SETTINGS") : getCellularSettingIntent();
    }

    public int getMetricsCategory() {
        return 115;
    }

    public CharSequence getMobileDataContentName(CallbackInfo callbackInfo) {
        if (!callbackInfo.roaming || TextUtils.isEmpty(callbackInfo.dataContentDescription)) {
            return callbackInfo.roaming ? ((QSTileImpl) this).mContext.getString(2131952965) : callbackInfo.dataContentDescription;
        }
        return ((QSTileImpl) this).mContext.getString(2131954624, ((QSTileImpl) this).mContext.getString(2131952965), callbackInfo.dataContentDescription.toString());
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955756);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        DataUsageController dataUsageController = this.mDataController;
        if (!dataUsageController.getTelephonyManager().isDataEnabled()) {
            dataUsageController.setMobileDataEnabled(true);
            return;
        }
        if (Prefs.getBoolean(((QSTileImpl) this).mContext, "QsHasTurnedOffMobileData", false)) {
            dataUsageController.setMobileDataEnabled(false);
            return;
        }
        NetworkControllerImpl networkControllerImpl = this.mController;
        String mobileDataNetworkName = networkControllerImpl.getMobileDataNetworkName();
        boolean zIsMobileDataNetworkInService = networkControllerImpl.isMobileDataNetworkInService();
        if (TextUtils.isEmpty(mobileDataNetworkName) || !zIsMobileDataNetworkInService) {
            mobileDataNetworkName = ((QSTileImpl) this).mContext.getString(2131954614);
        }
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(((QSTileImpl) this).mContext).setTitle(2131954615).setMessage(((QSTileImpl) this).mContext.getString(2131954613, mobileDataNetworkName)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        CellularTile$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new CellularTile$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        AlertDialog alertDialogCreate = negativeButton.setPositiveButton(R.string.anr_title, (DialogInterface.OnClickListener) externalSyntheticLambda0).create();
        alertDialogCreate.getWindow().setType(2009);
        SystemUIDialog.setShowForAllUsers(alertDialogCreate, true);
        SystemUIDialog.registerDismissListener(alertDialogCreate, (Runnable) null);
        SystemUIDialog.setWindowOnTop(alertDialogCreate, this.mKeyguard.mShowing);
        alertDialogCreate.show();
    }

    public void handleDestroy() {
        super.handleDestroy();
        this.mController.removeCallback(this.mSignalCallback);
    }

    public void handleInitialize() {
        this.mController.addCallback(this.mSignalCallback);
    }

    public void handleSecondaryClick(Expandable expandable) {
        handleLongClick(expandable);
    }

    @Override // 
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        CallbackInfo callbackInfo = (CallbackInfo) obj;
        if (callbackInfo == null) {
            callbackInfo = this.mSignalCallback.mInfo;
        }
        Resources resources = ((QSTileImpl) this).mContext.getResources();
        ((QSTile.State) booleanState).label = resources.getString(2131954609);
        DataUsageController dataUsageController = this.mDataController;
        boolean z = dataUsageController.isMobileDataSupported() && dataUsageController.getTelephonyManager().isDataEnabled();
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        if (callbackInfo.noSim) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131234449);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131234569);
        }
        if (callbackInfo.noSim) {
            ((QSTile.State) booleanState).state = 0;
            ((QSTile.State) booleanState).secondaryLabel = resources.getString(2131953851);
        } else if (z) {
            ((QSTile.State) booleanState).state = 2;
            ((QSTile.State) booleanState).secondaryLabel = appendMobileDataType(callbackInfo.multipleSubs ? callbackInfo.dataSubscriptionName : "", getMobileDataContentName(callbackInfo));
        } else {
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).secondaryLabel = resources.getString(2131952592);
        }
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        if (((QSTile.State) booleanState).state == 1) {
            ((QSTile.State) booleanState).stateDescription = "";
        } else {
            ((QSTile.State) booleanState).stateDescription = ((QSTile.State) booleanState).secondaryLabel;
        }
    }

    public boolean isAvailable() {
        return this.mController.mHasMobileDataFeature && ((QSTileImpl) this).mHost.getUserContext().getUserId() == 0;
    }

    @Override // 
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState mo315newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }

    public void toggleMobileDataEnabled(boolean z) {
    }
}
