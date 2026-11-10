package com.oplus.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.SystemSettings;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VibrationModeTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "vibration";
    private final AudioManager audioManager;
    private final BroadcastDispatcher broadcastDispatcher;
    private final VibrationModeTile$receiver$1 receiver;
    private final VibrationModeTile$vibrateWhenRingingObserver$1 vibrateWhenRingingObserver;
    private final VibrationModeTile$vibrateWhenSilentObserver$1 vibrateWhenSilentObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.oplus.systemui.qs.tiles.VibrationModeTile$receiver$1] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.oplus.systemui.qs.tiles.VibrationModeTile$vibrateWhenRingingObserver$1] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.oplus.systemui.qs.tiles.VibrationModeTile$vibrateWhenSilentObserver$1] */
    public VibrationModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, final SystemSettings systemSettings, BroadcastDispatcher broadcastDispatcher) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.broadcastDispatcher = broadcastDispatcher;
        this.audioManager = (AudioManager) ((QSTileImpl) this).mContext.getSystemService(AudioManager.class);
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = qSHost.getUserContext().getUserId();
        this.vibrateWhenSilentObserver = new UserSettingObserver(systemSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.VibrationModeTile$vibrateWhenSilentObserver$1
            public final void handleValueChanged(int i, boolean z) {
                String str = ((QSTileImpl) this).TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "VibrateWhenSilent change to ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
                }
                this.refreshState((Object) null);
            }
        };
        final QSTileImpl.H h2 = ((QSTileImpl) this).mHandler;
        final int userId2 = qSHost.getUserContext().getUserId();
        this.vibrateWhenRingingObserver = new UserSettingObserver(systemSettings, h2, userId2) { // from class: com.oplus.systemui.qs.tiles.VibrationModeTile$vibrateWhenRingingObserver$1
            public final void handleValueChanged(int i, boolean z) {
                String str = ((QSTileImpl) this).TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "VibrateWhenRinging change tp to ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
                }
                if (this.isListening()) {
                    AudioManager audioManager = this.audioManager;
                    Integer numValueOf = audioManager != null ? Integer.valueOf(audioManager.getRingerModeInternal()) : null;
                    if (numValueOf != null && numValueOf.intValue() == 2) {
                        this.refreshState((Object) null);
                    }
                }
            }
        };
        this.receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.tiles.VibrationModeTile$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if ("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(intent.getAction())) {
                    this.this$0.refreshState((Object) null);
                }
            }
        };
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237235);
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent("android.settings.SOUND_SETTINGS");
        intent.addFlags(268468224);
        return intent;
    }

    public int getMetricsCategory() {
        return 111;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956573);
    }

    public void handleClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick", null, 8);
        AudioManager audioManager = this.audioManager;
        Integer numValueOf = audioManager != null ? Integer.valueOf(audioManager.getRingerModeInternal()) : null;
        if ((numValueOf == null || numValueOf.intValue() != 0) && (numValueOf == null || numValueOf.intValue() != 1)) {
            setValue(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value ? 1 : 0);
            return;
        }
        setValue(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value ? 1 : 0);
        AudioManager audioManager2 = this.audioManager;
        if (audioManager2 != null) {
            audioManager2.setRingerModeInternal(!((QSTile.AdapterState) ((QSTileImpl) this).mState).value ? 1 : 0);
        }
    }

    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
        setListening(false);
        this.broadcastDispatcher.unregisterReceiver(this.receiver);
    }

    public final void handleInitialize() {
        setListening(true);
        setListening(true);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (!z) {
            this.broadcastDispatcher.unregisterReceiver(this.receiver);
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, intentFilter, (Executor) null, (UserHandle) null, 0, 60);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956573);
        AudioManager audioManager = this.audioManager;
        Integer numValueOf = audioManager != null ? Integer.valueOf(audioManager.getRingerModeInternal()) : null;
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("handleUpdateState mode = " + numValueOf);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if ((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 1)) {
            ((QSTile.AdapterState) booleanState).value = getValue() == 1;
        } else {
            ((QSTile.AdapterState) booleanState).value = getValue() != 0;
        }
        if (((QSTile.AdapterState) booleanState).value) {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237234);
        } else {
            ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237235);
        }
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956573);
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    public final void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        setUserId(i);
        setUserId(i);
    }

    public final boolean isAvailable() {
        VolumeFeatureOption.INSTANCE.getClass();
        return VolumeFeatureOption.isSupportsStealthMode();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m348newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
