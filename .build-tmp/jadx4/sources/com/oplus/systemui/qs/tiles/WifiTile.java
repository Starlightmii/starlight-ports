package com.oplus.systemui.qs.tiles;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Switch;
import androidx.compose.animation.ChangeSize$;
import androidx.recyclerview.widget.ChildHelper$;
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
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.Objects;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class WifiTile extends QSTileImpl {
    public static final Intent WIFI_SETTINGS = new Intent("android.settings.WIFI_SETTINGS");
    public final NetworkController mController;
    public boolean mExpectDisabled;
    public final WifiSignalCallback mSignalCallback;
    public final QSTile.BooleanState mStateBeforeClick;
    public final AccessPointController mWifiController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CallbackInfo {
        public boolean activityIn;
        public boolean activityOut;
        public boolean connected;
        public boolean enabled;
        public boolean isCaptivePortal;
        public boolean isTransient;
        public String ssid;
        public String statusLabel;
        public String wifiSignalContentDescription;
        public int wifiSignalIconId;

        public final String toString() {
            StringBuilder sb = new StringBuilder("CallbackInfo[enabled=");
            sb.append(this.enabled);
            sb.append(",connected=");
            sb.append(this.connected);
            sb.append(",wifiSignalIconId=");
            sb.append(this.wifiSignalIconId);
            sb.append(",ssid=");
            sb.append(this.ssid);
            sb.append(",activityIn=");
            sb.append(this.activityIn);
            sb.append(",activityOut=");
            sb.append(this.activityOut);
            sb.append(",wifiSignalContentDescription=");
            sb.append(this.wifiSignalContentDescription);
            sb.append(",isTransient=");
            sb.append(this.isTransient);
            sb.append(",isCaptivePortal=");
            return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.isCaptivePortal, ']');
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class WifiSignalCallback implements SignalCallback {
        public CallbackInfo mInfo;
        public /* synthetic */ OplusWifiTile this$0;

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

        public /* bridge */ /* synthetic */ void setMobileDataEnabled(boolean z) {
            super.setMobileDataEnabled(z);
        }

        public /* bridge */ /* synthetic */ void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            super.setMobileDataIndicators(mobileDataIndicators);
        }

        public /* bridge */ /* synthetic */ void setSubs(List list) {
            super.setSubs(list);
        }

        public void setWifiIndicators(WifiIndicators wifiIndicators) {
            OplusWifiTile oplusWifiTile = this.this$0;
            Intent intent = WifiTile.WIFI_SETTINGS;
            if (((QSTileImpl) oplusWifiTile).DEBUG) {
                ChildHelper$.ExternalSyntheticOutline0.m(((QSTileImpl) oplusWifiTile).TAG, new StringBuilder("onWifiSignalChanged enabled="), wifiIndicators.enabled);
            }
            IconState iconState = wifiIndicators.qsIcon;
            if (iconState == null) {
                return;
            }
            boolean z = wifiIndicators.enabled;
            boolean z2 = iconState.visible;
            int i = iconState.icon;
            String str = wifiIndicators.description;
            boolean z3 = wifiIndicators.activityIn;
            boolean z4 = wifiIndicators.activityOut;
            String str2 = iconState.contentDescription;
            boolean z5 = wifiIndicators.isTransient;
            String str3 = wifiIndicators.statusLabel;
            boolean z6 = wifiIndicators.isCaptivePortal;
            CallbackInfo callbackInfo = this.mInfo;
            if (callbackInfo == null) {
                Path$.ExternalSyntheticBUOutline0.m$1();
                return;
            }
            if (!CallbackInfo.class.equals(CallbackInfo.class)) {
                Path$.ExternalSyntheticBUOutline0.m$1();
                return;
            }
            boolean z7 = (Boolean.valueOf(callbackInfo.enabled).equals(Boolean.valueOf(z)) && Boolean.valueOf(callbackInfo.connected).equals(Boolean.valueOf(z2)) && Integer.valueOf(callbackInfo.wifiSignalIconId).equals(Integer.valueOf(i)) && Objects.equals(callbackInfo.ssid, str) && Boolean.valueOf(callbackInfo.activityIn).equals(Boolean.valueOf(z3)) && Boolean.valueOf(callbackInfo.activityOut).equals(Boolean.valueOf(z4)) && Objects.equals(callbackInfo.wifiSignalContentDescription, str2) && Boolean.valueOf(callbackInfo.isTransient).equals(Boolean.valueOf(z5)) && Objects.equals(callbackInfo.statusLabel, str3) && Boolean.valueOf(callbackInfo.isCaptivePortal).equals(Boolean.valueOf(z6))) ? false : true;
            callbackInfo.enabled = z;
            callbackInfo.connected = z2;
            callbackInfo.wifiSignalIconId = i;
            callbackInfo.ssid = str;
            callbackInfo.activityIn = z3;
            callbackInfo.activityOut = z4;
            callbackInfo.wifiSignalContentDescription = str2;
            callbackInfo.isTransient = z5;
            callbackInfo.statusLabel = str3;
            callbackInfo.isCaptivePortal = z6;
            if (z7) {
                oplusWifiTile.getTileEx().onFwStateChanged(oplusWifiTile, QSTileEx.mapToUiState(z), ((QSTileImpl) oplusWifiTile).mClickHandler);
            }
        }
    }

    public WifiTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, NetworkController networkController, AccessPointController accessPointController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        QSTile.BooleanState booleanStateMo340newTileState = mo340newTileState();
        this.mStateBeforeClick = booleanStateMo340newTileState;
        this.mSignalCallback = createSignalCallback();
        this.mController = networkController;
        this.mWifiController = accessPointController;
        ((QSTile.State) booleanStateMo340newTileState).spec = OplusQSHighlightTileViewLabelTextHandler.WIFI;
    }

    public static String removeDoubleQuotes(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    public WifiSignalCallback createSignalCallback() {
        return this.mSignalCallback;
    }

    public Intent getLongClickIntent() {
        return WIFI_SETTINGS;
    }

    public int getMetricsCategory() {
        return 126;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955875);
    }

    public void handleClick(Expandable expandable) {
        ((QSTileImpl) this).mState.copyTo(this.mStateBeforeClick);
        boolean z = ((QSTile.AdapterState) ((QSTileImpl) this).mState).value;
        refreshState(z ? null : QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING);
        this.mController.setWifiEnabled(!z);
        this.mExpectDisabled = z;
        if (z) {
            Runnable externalSyntheticLambda0 = new WifiTile$.ExternalSyntheticLambda0();
            ((WifiTile$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = this;
            VarHandle.storeStoreFence();
            ((QSTileImpl) this).mHandler.postDelayed(externalSyntheticLambda0, 350L);
        }
    }

    public void handleDestroy() {
        super.handleDestroy();
        this.mController.removeCallback(this.mSignalCallback);
    }

    public void handleInitialize() {
        this.mController.addCallback(this.mSignalCallback);
    }

    public void handleSecondaryClick(Expandable expandable) {
        if (!this.mWifiController.canConfigWifi()) {
            ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.WIFI_SETTINGS"), 0);
        } else {
            if (((QSTile.AdapterState) ((QSTileImpl) this).mState).value) {
                return;
            }
            this.mController.setWifiEnabled(true);
        }
    }

    @Override // 
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int i;
        if (((QSTileImpl) this).DEBUG) {
            Log.d(((QSTileImpl) this).TAG, "handleUpdateState arg=" + obj);
        }
        CallbackInfo callbackInfo = this.mSignalCallback.mInfo;
        if (this.mExpectDisabled) {
            if (callbackInfo.enabled) {
                return;
            } else {
                this.mExpectDisabled = false;
            }
        }
        boolean z = obj == QSTileImpl.ARG_SHOW_TRANSIENT_ENABLING;
        boolean z2 = callbackInfo.enabled && (i = callbackInfo.wifiSignalIconId) > 0 && !(callbackInfo.ssid == null && i == 17303107);
        boolean z3 = callbackInfo.ssid == null && callbackInfo.wifiSignalIconId == 17303107;
        boolean z4 = z || callbackInfo.isTransient;
        String string = callbackInfo.statusLabel;
        if (z4) {
            boolean zEnableSeparateNotificationAndQS = ((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS();
            Context context = ((QSTileImpl) this).mContext;
            string = zEnableSeparateNotificationAndQS ? context.getString(2131955246) : context.getString(2131955876);
        }
        ((QSTile.State) booleanState).secondaryLabel = string;
        ((QSTile.State) booleanState).state = 2;
        ((QSTile.State) booleanState).dualTarget = true;
        ((QSTile.AdapterState) booleanState).value = z || callbackInfo.enabled;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Resources resources = ((QSTileImpl) this).mContext.getResources();
        if (z4) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(R.drawable.ic_volume_small);
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
        } else if (!((QSTile.AdapterState) booleanState).value) {
            ((QSTile.State) booleanState).state = 1;
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(R.drawable.input_extract_action_bg_material_dark);
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
        } else if (z2) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(callbackInfo.wifiSignalIconId);
            String str = callbackInfo.ssid;
            ((QSTile.State) booleanState).label = str != null ? removeDoubleQuotes(str) : getTileLabel();
        } else if (z3) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(R.drawable.input_extract_action_bg_material_dark);
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(R.drawable.input_extract_action_bg_material_dark);
            ((QSTile.State) booleanState).label = resources.getString(2131955875);
        }
        sb.append(((QSTileImpl) this).mContext.getString(2131955875));
        sb.append(OplusPreventModeData.SEPARATOR);
        if (((QSTile.AdapterState) booleanState).value && z2) {
            sb2.append(callbackInfo.wifiSignalContentDescription);
            sb.append(removeDoubleQuotes(callbackInfo.ssid));
            if (!TextUtils.isEmpty(((QSTile.State) booleanState).secondaryLabel)) {
                sb.append(OplusPreventModeData.SEPARATOR);
                sb.append(((QSTile.State) booleanState).secondaryLabel);
            }
        }
        ((QSTile.State) booleanState).stateDescription = sb2.toString();
        ((QSTile.State) booleanState).contentDescription = sb.toString();
        ((QSTile.State) booleanState).dualLabelContentDescription = resources.getString(2131951935, getTileLabel());
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean isAvailable() {
        return ((QSTileImpl) this).mContext.getPackageManager().hasSystemFeature("android.hardware.wifi");
    }

    @Override // 
    /* JADX INFO: renamed from: newTileState */
    public QSTile.BooleanState mo340newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.AdapterState) booleanState).forceExpandIcon = true;
        return booleanState;
    }
}
