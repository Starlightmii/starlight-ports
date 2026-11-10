package com.oplus.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
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
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SettingObserver;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.volume.utils.OplusRMChangeVibrateHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RingerModeTile extends QSTileImpl {
    private static final String MODE_RINGER_KEY = "mode_ringer";
    public static final String TILE_SPEC = "ringermode";
    private final AudioManager audioManager;
    private final Executor backgroundExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    public int lastMuteStateMode;
    private final OplusRMChangeVibrateHelper mRMVibrateHelper;
    public int muteStateMode;
    private final RingerModeTile$phoneMuteStateCustomObserver$1 phoneMuteStateCustomObserver;
    private final RingerModeTile$phoneRingerStateObserver$1 phoneRingerStateObserver;
    private final RingerModeTile$receiver$1 receiver;
    public static final Companion Companion = new Companion();
    private static final Object RESET_DO_ANIM = new Object();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Object getRESET_DO_ANIM() {
            return RingerModeTile.RESET_DO_ANIM;
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.oplus.systemui.qs.tiles.RingerModeTile$receiver$1] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.oplus.systemui.qs.tiles.RingerModeTile$phoneRingerStateObserver$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.oplus.systemui.qs.tiles.RingerModeTile$phoneMuteStateCustomObserver$1] */
    public RingerModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, final GlobalSettings globalSettings, BroadcastDispatcher broadcastDispatcher, Executor executor, OplusRMChangeVibrateHelper oplusRMChangeVibrateHelper) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.broadcastDispatcher = broadcastDispatcher;
        this.backgroundExecutor = executor;
        this.mRMVibrateHelper = oplusRMChangeVibrateHelper;
        this.audioManager = (AudioManager) ((QSTileImpl) this).mContext.getSystemService("audio");
        this.receiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.tiles.RingerModeTile$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if ("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(intent.getAction())) {
                    String str = ((QSTileImpl) this.this$0).TAG;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->onReceive INTERNAL_RINGER_MODE_CHANGED_ACTION", "SystemUi--QuickSettings");
                    }
                    this.this$0.refreshState((Object) null);
                }
            }
        };
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        this.phoneRingerStateObserver = new SettingObserver(globalSettings, h) { // from class: com.oplus.systemui.qs.tiles.RingerModeTile$phoneRingerStateObserver$1
            public final void handleValueChanged(int i, boolean z) {
                String str = ((QSTileImpl) this).TAG;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, " handleValueChanged INTERNAL_RINGER_MODE_CHANGED:ringState =", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
                }
                this.refreshState((Object) null);
            }
        };
        final QSTileImpl.H h2 = ((QSTileImpl) this).mHandler;
        this.phoneMuteStateCustomObserver = new SettingObserver(globalSettings, h2) { // from class: com.oplus.systemui.qs.tiles.RingerModeTile$phoneMuteStateCustomObserver$1
            public final void handleValueChanged(int i, boolean z) {
                this.updateCustomMuteState();
                this.refreshState((Object) null);
            }
        };
        this.muteStateMode = 2;
        this.lastMuteStateMode = 2;
    }

    public final AudioManager getAudioManager() {
        return this.audioManager;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237194);
    }

    public Intent getLongClickIntent() {
        if (getTileEx().isDisableVolumeMute$1()) {
            return null;
        }
        Intent intent = new Intent("android.settings.SOUND_SETTINGS");
        intent.addFlags(268468224);
        return intent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956570);
    }

    public void handleClick(Expandable expandable) {
        if (getTileEx().isDisableVolumeMute$1()) {
            return;
        }
        int ringerModeInternal = this.audioManager.getRingerModeInternal();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(ringerModeInternal, "setRingMode_ringMode:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
        }
        if (ringerModeInternal == 0 || 1 == ringerModeInternal) {
            this.audioManager.setRingerModeInternal(2);
            this.mRMVibrateHelper.vibrateOnRMChanged(2, ((QSTileImpl) this).TAG);
            return;
        }
        int vibrateWhenSilent = QsSettingsValueProxy.Companion.getVibrateWhenSilent(((QSTileImpl) this).mContext, 0);
        String str2 = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(vibrateWhenSilent, "setRingMode_vibrateWhenSilent:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR));
        }
        String str3 = ((QSTileImpl) this).TAG;
        if (vibrateWhenSilent == 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str3, "-->setRingMode_from normal to silent mode, we should change to silent mode!", "SystemUi--QuickSettings");
            }
            this.audioManager.setRingerModeInternal(0);
            this.mRMVibrateHelper.vibrateOnRMChanged(0, ((QSTileImpl) this).TAG);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str3, "-->setRingMode_from normal to vibrate mode, we should change to vibrate mode!", "SystemUi--QuickSettings");
        }
        this.audioManager.setRingerModeInternal(1);
        this.mRMVibrateHelper.vibrateOnRMChanged(1, ((QSTileImpl) this).TAG);
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
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.receiver, new IntentFilter("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"), this.backgroundExecutor, (UserHandle) null, 0, 56);
        updateCustomMuteState();
    }

    @Override // 
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        int ringerModeInternal = this.audioManager.getRingerModeInternal();
        int i = this.muteStateMode;
        if (i == 0 || i != this.lastMuteStateMode) {
            this.lastMuteStateMode = i;
            ringerModeInternal = i;
        }
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956570);
        ((QSTile.AdapterState) booleanState).value = ringerModeInternal != 2;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2 == ringerModeInternal ? 2131237194 : 2131237193);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956570);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    public final boolean isAvailable() {
        FeatureOption.INSTANCE.getClass();
        return !FeatureOption.isOplusAlertSlider();
    }

    @Override // 
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState mo344newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }

    public final void updateCustomMuteState() {
        boolean zIsDisableVolumeMute$1 = getTileEx().isDisableVolumeMute$1();
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("updateCustomMuteState isDisableVolumeMute = ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), zIsDisableVolumeMute$1);
        }
        this.muteStateMode = zIsDisableVolumeMute$1 ? 0 : 2;
    }
}
