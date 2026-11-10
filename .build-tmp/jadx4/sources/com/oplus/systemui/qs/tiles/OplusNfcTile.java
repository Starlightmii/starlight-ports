package com.oplus.systemui.qs.tiles;

import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Switch;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.NfcTile;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.felicanetworks.felicaextra.FelicaAdapterExtra;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNfcTile extends NfcTile {
    private final SatellitePolicy mSatellitePolicy;
    private final SatellitePolicy.IConnectionCallback mSatellitePolicyCallback;

    public OplusNfcTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, BroadcastDispatcher broadcastDispatcher, SatellitePolicy satellitePolicy) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, broadcastDispatcher);
        this.mSatellitePolicy = satellitePolicy;
        OplusNfcTile$mSatellitePolicyCallback$1 oplusNfcTile$mSatellitePolicyCallback$1 = new OplusNfcTile$mSatellitePolicyCallback$1();
        oplusNfcTile$mSatellitePolicyCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSatellitePolicyCallback = oplusNfcTile$mSatellitePolicyCallback$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237196);
    }

    public void handleClick(Expandable expandable) {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("handleClick: mState.value=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), ((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
        }
        handleUserOperationInternal$2(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value);
    }

    public final void handleDestroy() {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleDestroy();
        this.mSatellitePolicy.removeCallback(this.mSatellitePolicyCallback);
    }

    public final void handleInitialize() {
        this.mSatellitePolicy.addCallback(this.mSatellitePolicyCallback);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        if (CustomizeFeatureOption.sIsSupportJapanFelica) {
            OplusNfcTileFelicaEx.Companion.getInstance(((QSTileImpl) this).mContext).handleUpdateState(booleanState);
        } else {
            NfcAdapter adapter = getAdapter();
            int i = 0;
            boolean z = QSTileEx.UiState.OFF != obj && (QSTileEx.UiState.ON == obj || (adapter != null && adapter.isEnabled()));
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("handleUpdateState: arg=" + obj + ", isEnabled=" + z);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            ((QSTile.AdapterState) booleanState).value = z;
            if (adapter != null) {
                i = z ? 2 : 1;
            }
            ((QSTile.State) booleanState).state = i;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131237195 : 2131237196);
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955829);
        ((QSTile.State) booleanState).labelDesc = (((QSTile.State) booleanState).state == 2 && QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS()) ? ((QSTileImpl) this).mContext.getString(2131955246) : "";
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
    }

    public boolean handleUserOperation(Object obj) {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("handleUserOperation: operation=" + obj);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        return handleUserOperationInternal$2(QSTileEx.UiState.ON == obj);
    }

    public final boolean handleUserOperationInternal$2(boolean z) {
        int adapterState;
        boolean rwP2pMode;
        if (CustomizeFeatureOption.sIsSupportJapanFelica) {
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->handleUserOperationInternal: JapanFelica", "SystemUi--QuickSettings");
            }
            FelicaAdapterExtra felicaAdapterExtraEnsureFelicaAdapterExtra = OplusNfcTileFelicaEx.Companion.getInstance(((QSTileImpl) this).mContext).ensureFelicaAdapterExtra();
            if (felicaAdapterExtraEnsureFelicaAdapterExtra != null && (adapterState = felicaAdapterExtraEnsureFelicaAdapterExtra.getAdapterState()) != 1 && adapterState != 4) {
                int adapterRwP2pState = felicaAdapterExtraEnsureFelicaAdapterExtra.getAdapterRwP2pState();
                if (adapterRwP2pState != 11) {
                    rwP2pMode = adapterRwP2pState != 13 ? false : felicaAdapterExtraEnsureFelicaAdapterExtra.setRwP2pMode(false);
                } else {
                    rwP2pMode = felicaAdapterExtraEnsureFelicaAdapterExtra.setRwP2pMode(true);
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", MobileInputLogger$.ExternalSyntheticOutline0.m("handleClick state=", adapterRwP2pState, " result=", "OplusNfcTileFelicaEx-->", rwP2pMode));
                }
            }
            return false;
        }
        if (getAdapter() == null) {
            String str2 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str2, "-->handleUserOperationInternal: NFC adapter is null", "SystemUi--QuickSettings");
            }
            return false;
        }
        if (z) {
            getAdapter().enable();
            String str3 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str3, "-->handleUserOperationInternal: set NFC enabled", "SystemUi--QuickSettings");
            }
        } else {
            getAdapter().disable();
            String str4 = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str4, "-->handleUserOperationInternal: set NFC disabled", "SystemUi--QuickSettings");
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m337newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "qs_nfc_lottie";
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR;
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
