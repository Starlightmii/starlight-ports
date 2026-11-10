package com.oplus.systemui.qs.tiles;

import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import androidx.appcompat.widget.ListPopupWindow$;
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
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.volume.utils.OplusRMChangeVibrateHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageRingerModeTile extends RingerModeTile {
    private final AudioManager mAudioManager;
    private final OplusRMChangeVibrateHelper mRMVibrateHelper;

    public ThreeStageRingerModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, GlobalSettings globalSettings, BroadcastDispatcher broadcastDispatcher, Executor executor, OplusRMChangeVibrateHelper oplusRMChangeVibrateHelper) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, globalSettings, broadcastDispatcher, executor, oplusRMChangeVibrateHelper);
        this.mRMVibrateHelper = oplusRMChangeVibrateHelper;
        this.mAudioManager = (AudioManager) ((QSTileImpl) this).mContext.getSystemService("audio");
    }

    private final void setRingMode(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setRingMode_ringMode:"), null, 8);
        AudioManager audioManager = this.mAudioManager;
        if (i == 0) {
            audioManager.setRingerModeInternal(2);
            this.mRMVibrateHelper.vibrateOnRMChanged(2, ((QSTileImpl) this).TAG);
        } else if (i != 1) {
            audioManager.setRingerModeInternal(1);
            this.mRMVibrateHelper.vibrateOnRMChanged(1, ((QSTileImpl) this).TAG);
        } else {
            audioManager.setRingerModeInternal(0);
            this.mRMVibrateHelper.vibrateOnRMChanged(0, ((QSTileImpl) this).TAG);
        }
    }

    private final void setRingModeByTag(int i) {
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "setRingMode: ringMode=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
        }
        refreshState(Integer.valueOf(i));
        setRingModeByTag.2 r0 = new setRingModeByTag.2();
        r0.this$0 = this;
        r0.$ringMode = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(r0);
        this.mRMVibrateHelper.vibrateOnRMChanged(i, ((QSTileImpl) this).TAG);
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    public QSTile.Icon getDefaultIcon() {
        int i;
        int ringerModeInternal = this.mAudioManager.getRingerModeInternal();
        if (ringerModeInternal == 0) {
            i = 2131237193;
        } else if (ringerModeInternal != 1) {
            i = ringerModeInternal != 2 ? -1 : 2131237194;
        } else {
            i = 2131237236;
        }
        return QSTileImpl.ResourceIcon.get(i);
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    public int getMetricsCategory() {
        return 119;
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    public CharSequence getTileLabel() {
        CharSequence charSequence = ((QSTile.State) ((QSTileImpl) this).mState).label;
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("getTileLabel:label=" + ((Object) (charSequence == null ? "null" : charSequence)));
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        CharSequence charSequence2 = ((QSTile.State) ((QSTileImpl) this).mState).label;
        if (charSequence2 == null || charSequence2.length() == 0) {
            return ((QSTileImpl) this).mContext.getString(2131956570);
        }
        charSequence.getClass();
        return charSequence;
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    public void handleClick(Expandable expandable) {
        if (getTileEx().isDisableVolumeMute$1()) {
            return;
        }
        if (((QSTile.AdapterState) ((QSTileImpl) this).mState).value) {
            refreshState(RingerModeTile.Companion.getRESET_DO_ANIM());
        }
        View view = expandable != null ? expandable.getView() : null;
        Object tag = view != null ? view.getTag(2131364805) : null;
        Integer num = tag instanceof Integer ? (Integer) tag : null;
        if (num != null) {
            setRingModeByTag(num.intValue());
        } else {
            setRingMode(this.mAudioManager.getRingerModeInternal());
        }
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        String str;
        int i;
        int i2;
        if (obj == RingerModeTile.Companion.getRESET_DO_ANIM()) {
            return;
        }
        int ringerModeInternal = getTileEx().isDisableVolumeMute$1() ? 0 : this.mAudioManager.getRingerModeInternal();
        String str2 = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            sbM.append("handleUpdateState :mode= " + ringerModeInternal + ", arg = " + obj);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        ((QSTile.AdapterState) booleanState).value = false;
        if (ringerModeInternal == 0) {
            FeatureOption.INSTANCE.getClass();
            str = FeatureOption.isTablet() ? "qs_three_stage_normal_to_silent_lottie" : "qs_three_stage_vibrate_to_silent_lottie";
        } else if (ringerModeInternal != 1) {
            str = ringerModeInternal != 2 ? "" : "qs_three_stage_silent_to_normal_lottie";
        } else {
            str = "qs_three_stage_normal_to_vibrate_lottie";
        }
        ((QSTile.State) booleanState).lottiePrefix = str;
        int i3 = 2131956570;
        if (ringerModeInternal == 0) {
            i = 2131956570;
        } else if (ringerModeInternal != 1) {
            i = ringerModeInternal != 2 ? -1 : 2131956567;
        } else {
            i = 2131956568;
        }
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(i);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).state = 2;
        if (ringerModeInternal == 0) {
            i2 = 2131237193;
        } else if (ringerModeInternal != 1) {
            i2 = ringerModeInternal != 2 ? -1 : 2131237194;
        } else {
            i2 = 2131237236;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(i2);
        if (ringerModeInternal != 0) {
            if (ringerModeInternal != 1) {
                i3 = ringerModeInternal != 2 ? -1 : 2131956567;
            } else {
                i3 = 2131956568;
            }
        }
        String string = ((QSTileImpl) this).mContext.getString(i3);
        ((QSTile.State) booleanState).label = string;
        ((QSTile.State) booleanState).functionName.setMainText(string);
        ((QSTile.State) booleanState).threeStageMode = ringerModeInternal;
    }

    @Override // com.oplus.systemui.qs.tiles.RingerModeTile
    /* JADX INFO: renamed from: newTileState */
    public QSTile.BooleanState mo344newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = "";
        ((QSTile.State) booleanState).lottieSupport = 16;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_RED_COLOR;
        return booleanState;
    }
}
