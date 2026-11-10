package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.safetycenter.SafetyCenterManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.MicrophoneToggleTile;
import com.android.systemui.qs.tiles.SensorPrivacyToggleTile;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMicrophoneToggleTile extends MicrophoneToggleTile {
    public static final String ACTION_SEARCH_TO_PERMISSIONAPPSACTIVITY = "android.settings.ACTION_SEARCH_TO_PERMISSIONAPPSACTIVITY";
    public static final Companion Companion = new Companion();
    public static final String EXTRA_PERMISSION_TAG = "mPermission";
    private final Consumer<Boolean> mStealModeCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMicrophoneToggleTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, MetricsLogger metricsLogger, FalsingManager falsingManager, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, IndividualSensorPrivacyController individualSensorPrivacyController, KeyguardStateController keyguardStateController, SafetyCenterManager safetyCenterManager) {
        super(qSHost, qsEventLogger, looper, handler, metricsLogger, falsingManager, statusBarStateController, activityStarter, qSLogger, individualSensorPrivacyController, keyguardStateController, safetyCenterManager);
        OplusMicrophoneToggleTile$mStealModeCallback$1 oplusMicrophoneToggleTile$mStealModeCallback$1 = new OplusMicrophoneToggleTile$mStealModeCallback$1();
        oplusMicrophoneToggleTile$mStealModeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStealModeCallback = oplusMicrophoneToggleTile$mStealModeCallback$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237187);
    }

    public final int getIconRes(boolean z) {
        return z ? 2131237188 : 2131237187;
    }

    public Intent getLongClickIntent() {
        if (((SensorPrivacyToggleTile) this).mIsSafetyCenterEnabled) {
            return new Intent("android.settings.PRIVACY_CONTROLS");
        }
        Intent intent = new Intent("android.settings.ACTION_SEARCH_TO_PERMISSIONAPPSACTIVITY");
        intent.putExtra("mPermission", "android.permission-group.MICROPHONE");
        return intent;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955822);
    }

    public int getUnavailableIconRes() {
        return 2131237189;
    }

    public final void handleDestroy() {
        super/*com.android.systemui.qs.tiles.SensorPrivacyToggleTile*/.handleDestroy();
        ThreeKeyStealthModeObserver.Companion.getInstance().removeObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
    }

    public final void handleSetListening(boolean z) {
        super/*com.android.systemui.qs.tileimpl.QSTileImpl*/.handleSetListening(z);
        if (z) {
            ThreeKeyStealthModeObserver.Companion.getInstance().addObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
        } else {
            ThreeKeyStealthModeObserver.Companion.getInstance().removeObserver(((QSTileImpl) this).mContext, this.mStealModeCallback);
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        super/*com.android.systemui.qs.tiles.SensorPrivacyToggleTile*/.handleUpdateState(booleanState, obj);
        ((QSTile.State) booleanState).secondaryLabel = "";
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUpdateState secondaryLabel empty string", null, 8);
    }

    public final boolean isTileUnavailable() {
        return ThreeKeyStealthModeObserver.Companion.isStealthModeOn(((QSTileImpl) this).mContext);
    }
}
