package com.oplus.systemui.volume.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioSystem;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.model.TimerSpan$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.volume.Util;
import com.android.systemui.Prefs;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.android.systemui.volume.Events;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.opthreekey.observer.ThreeKeyStealthModeObserver;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.ability.VibrateAbility;
import com.oplus.systemui.volume.repository.MultiMediaRepository;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.volume.VolumeSettingsValueProxy;
import com.oplusos.systemui.volume.utils.MultiMediaVolumeUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeRow {
    public static final String TAG = "OplusVolumeRow";
    private ObjectAnimator anim;
    public int animTargetProgress;
    private final Context context;
    public boolean defaultStream;
    private EffectiveAnimationView icon;
    public int iconMuteRes;
    public int iconRes;
    public int iconState;
    public boolean important;
    public boolean isMultiMediaVolume;
    public boolean isShowInPanel;
    public boolean isShowInSide;
    public boolean isSuperVolume;
    public boolean isVerticalLayout;
    public int lastIconRes;
    public int lastLevel;
    public int lastVlevel;
    private TextView nameText;
    public int playerId;
    private OplusVolumeSeekBar slider;
    private FrameLayout sliderFrame;
    private VolumeDialogController.StreamState ss;
    public int stream;
    public boolean tracking;
    public int uid;
    public long userAttempt;
    private View view;
    private final OplusVolumeDialogImpl volumeDialog;
    public int volumeListBoundTextColor;
    public static final Companion Companion = new Companion();
    private static ViewOutlineProvider appIconOutlineProvider = new OplusVolumeRow$Companion$appIconOutlineProvider$1();
    public int requestedLevel = -1;
    private String mediaPackageName = "";
    private String jsonRes = "";
    public int lastAudibleLevel = 1;
    public long startTimestamp = Long.MAX_VALUE;
    public boolean enable = true;
    public int volumeListBindDataSource = -1;
    public boolean touchable = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getImpliedLevel(OplusVolumeSeekBar oplusVolumeSeekBar, int i) {
            int max = oplusVolumeSeekBar.getMax();
            int i2 = max / 100;
            int i3 = i2 - 1;
            if (i == 0) {
                return 0;
            }
            return i == max ? i2 : ((int) ((i / max) * i3)) + 1;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VolumeSeekBarChangeListener implements OplusVolumeSeekBar.OnSeekBarChangeListener {
        public long mAdjustCount;
        public long mLastUpdateTime;
        private final OplusVolumeRow mRow;
        private final OplusVolumeDialogImpl volumeDialog;
        public int mRecordProgressWhenStartTouch = -1;
        private final Handler mSeekBarHandler = new Handler() { // from class: com.oplus.systemui.volume.view.OplusVolumeRow$VolumeSeekBarChangeListener$mSeekBarHandler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.arg1;
                if (message.what == 1) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    OplusVolumeRow.VolumeSeekBarChangeListener volumeSeekBarChangeListener = this.this$0;
                    if (jUptimeMillis - volumeSeekBarChangeListener.mLastUpdateTime < 30) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "ignore updateLevel:", "OplusVolumeRow-->", "SystemUi--Volume");
                        }
                        removeMessages(1);
                        Message messageObtainMessage = obtainMessage(1);
                        messageObtainMessage.arg1 = i;
                        sendMessageDelayed(messageObtainMessage, 30L);
                        return;
                    }
                    LogUtil.i$default(LogUtil.TAG_VOLUME, OplusVolumeRow.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "handle userLevel:"), null, 8);
                    volumeSeekBarChangeListener.mLastUpdateTime = SystemClock.uptimeMillis();
                    VolumeDialogController.StreamState ss = volumeSeekBarChangeListener.mRow.getSs();
                    if (ss == null || ss.level != i) {
                        Trace.beginSection("VolumeSeekBarChangeListener#userLevel");
                        volumeSeekBarChangeListener.updateLevel(i);
                        Trace.endSection();
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "mRow.ss.level == userLevel = ", " return", "OplusVolumeRow-->", "SystemUi--Volume");
                    }
                }
            }
        };

        public VolumeSeekBarChangeListener(OplusVolumeRow oplusVolumeRow, OplusVolumeRow oplusVolumeRow2, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
            this.mRow = oplusVolumeRow2;
            this.volumeDialog = oplusVolumeDialogImpl;
        }

        public final OplusVolumeDialogImpl getVolumeDialog() {
            return this.volumeDialog;
        }

        @Override // com.oplus.systemui.volume.OplusVolumeSeekBar.OnSeekBarChangeListener
        public void onProgressChanged(OplusVolumeSeekBar oplusVolumeSeekBar, int i, boolean z) {
            int i2;
            CharSequence text;
            int i3;
            int i4;
            CharSequence text2;
            if (this.mRow.getSs() != null) {
                if (!oplusVolumeSeekBar.mIsAnimating || z) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", "OplusVolumeRow-->".concat(AudioSystem.streamToString(this.mRow.getStream()) + "onProgressChanged " + i + ",fromUser=" + z + ", isIconClicked=" + this.volumeDialog.mIsIconClicked));
                    }
                    this.volumeDialog.mIsIconClicked = false;
                    FlavorOneFeatureOption.INSTANCE.getClass();
                    if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
                        OplusVolumeRow oplusVolumeRow = this.mRow;
                        if (oplusVolumeRow.isMultiMediaVolume) {
                            this.volumeDialog.mMultiMediaRepository.multiMediaProgressChange(oplusVolumeSeekBar, i, oplusVolumeRow);
                            int impliedLevel = OplusVolumeRow.Companion.getImpliedLevel(oplusVolumeSeekBar, i);
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLuxunVibratorSupport()) {
                                if (oplusVolumeSeekBar.getMax() == i || i == 0) {
                                    this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(154, false);
                                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                        Log.d("SystemUi--Volume", "OplusVolumeRow-->onProgressChanged, max or min , vibrateByLMVibrator WaveformEffect=154");
                                        return;
                                    }
                                    return;
                                }
                                this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(152, true);
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                    Log.d("SystemUi--Volume", "OplusVolumeRow-->onProgressChanged, vibrateByLMVibrator WaveformEffect=152");
                                    return;
                                }
                                return;
                            }
                            if (oplusVolumeSeekBar.getMax() != i && i != 0) {
                                VibrateAbility.Companion.getClass();
                                int vibratorStrengthWithLimit = VibrateAbility.Companion.getVibratorStrengthWithLimit(impliedLevel, 100, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION, 2400);
                                this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(152, vibratorStrengthWithLimit, true);
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(vibratorStrengthWithLimit, "onProgressChanged, vibrateByLMVibrator WaveformEffect=152 strength= ", "OplusVolumeRow-->", "SystemUi--Volume");
                                    return;
                                }
                                return;
                            }
                            VibrateAbility.Companion companion = VibrateAbility.Companion;
                            int max = oplusVolumeSeekBar.getMax();
                            companion.getClass();
                            int vibratorStrengthWithLimit2 = VibrateAbility.Companion.getVibratorStrengthWithLimit(impliedLevel, max, 800, 1200);
                            this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(154, vibratorStrengthWithLimit2, false);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(vibratorStrengthWithLimit2, "onProgressChanged, max or min ,vibrateByLMVibrator WaveformEffect=154 strength= ", "OplusVolumeRow-->", "SystemUi--Volume");
                                return;
                            }
                            return;
                        }
                    }
                    int impliedLevel2 = OplusVolumeRow.Companion.getImpliedLevel(oplusVolumeSeekBar, i);
                    OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeDialog;
                    int i5 = oplusVolumeDialogImpl.mActiveStream;
                    if ((i5 == 2 || i5 == 5) && impliedLevel2 > 0) {
                        oplusVolumeDialogImpl.getActiveRow().setLastLevel(impliedLevel2);
                    }
                    long jUptimeMillis = SystemClock.uptimeMillis() - this.mRow.getUserAttempt();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        long j = this.mAdjustCount;
                        long userAttempt = this.mRow.getUserAttempt();
                        StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(j, "mAdjustCount = ", " , delta = ");
                        sbM.append(jUptimeMillis);
                        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, " , mRow.userAttempt = ", userAttempt, " , userLevel = ");
                        MobileInputLogger$.ExternalSyntheticOutline0.m(impliedLevel2, "OplusVolumeRow-->", "SystemUi--Volume", sbM);
                    }
                    VolumeDialogController.StreamState ss = this.mRow.getSs();
                    if (ss != null && (!z || (this.mAdjustCount > 60 && this.mRow.getStream() == 2 && jUptimeMillis < 60 && ss.levelMin != impliedLevel2 && ss.levelMax != impliedLevel2))) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            Log.d("SystemUi--Volume", "OplusVolumeRow-->onProgressChanged return ");
                            return;
                        }
                        return;
                    }
                    VolumeDialogController.StreamState ss2 = this.mRow.getSs();
                    if (ss2 != null && (i3 = ss2.levelMin) > 0 && i < (i4 = i3 * 100)) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            TextView nameText = this.mRow.getNameText();
                            Log.d("SystemUi--Volume", "OplusVolumeRow-->".concat("onProgressChanged setProgress " + ((nameText == null || (text2 = nameText.getText()) == null) ? null : text2.toString()) + " " + i4));
                        }
                        oplusVolumeSeekBar.setProgress(i4);
                    }
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isOplusAlertSlider() && ((this.mRow.getStream() == 2 || this.mRow.getStream() == 5) && i == (i2 = OplusVolumeDialogImpl.RINGER_MODE_MIN_LEVEL * 100))) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            TextView nameText2 = this.mRow.getNameText();
                            Log.d("SystemUi--Volume", "OplusVolumeRow-->".concat("onProgressChanged mRingerModeMinLevel setProgress " + ((nameText2 == null || (text = nameText2.getText()) == null) ? null : text.toString()) + " " + i2));
                        }
                        oplusVolumeSeekBar.setProgress(i2);
                    }
                    VolumeDialogController.StreamState ss3 = this.mRow.getSs();
                    if (ss3 != null) {
                        if (ss3.level != impliedLevel2 || (ss3.muted && impliedLevel2 > ss3.levelMin)) {
                            this.mRow.setUserAttempt(SystemClock.uptimeMillis());
                            int i6 = ss3.level;
                            boolean z2 = ss3.muted;
                            long userAttempt2 = this.mRow.getUserAttempt();
                            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("onProgressChanged userLevel = ", impliedLevel2, i6, " mRow.ss.level = ", " mRow.ss.muted = ");
                            sbM2.append(z2);
                            sbM2.append(" mRow.userAttempt = ");
                            sbM2.append(userAttempt2);
                            LogUtil.i$default(LogUtil.TAG_VOLUME, OplusVolumeRow.TAG, sbM2.toString(), null, 8);
                            if (this.mRow.getRequestedLevel() != impliedLevel2) {
                                this.mSeekBarHandler.removeMessages(1);
                                Message messageObtainMessage = this.mSeekBarHandler.obtainMessage(1);
                                messageObtainMessage.arg1 = impliedLevel2;
                                this.mSeekBarHandler.sendMessage(messageObtainMessage);
                            }
                        }
                    }
                }
            }
        }

        @Override // com.oplus.systemui.volume.OplusVolumeSeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(OplusVolumeSeekBar oplusVolumeSeekBar) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("onStartTrackingTouch stream = ", this.mRow.getStream(), " , isMultiMediaVolume = ", "OplusVolumeRow-->", this.mRow.isMultiMediaVolume));
            }
            OplusVolumeRow oplusVolumeRow = this.mRow;
            if (oplusVolumeRow.isMultiMediaVolume) {
                return;
            }
            ObjectAnimator anim = oplusVolumeRow.getAnim();
            if (anim != null) {
                anim.cancel();
            }
            oplusVolumeSeekBar.setAnimating(false);
            this.volumeDialog.mController.setActiveStream(this.mRow.getStream(), false);
            this.mRow.setTracking(true);
            this.mRecordProgressWhenStartTouch = oplusVolumeSeekBar.getProgress();
        }

        @Override // com.oplus.systemui.volume.OplusVolumeSeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(OplusVolumeSeekBar oplusVolumeSeekBar) {
            int impliedLevel;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("onStopTrackingTouch: ", this.mRow.getStream(), ", isMultiMediaVolume = ", "OplusVolumeRow-->", this.mRow.isMultiMediaVolume));
            }
            if (this.mRow.isMultiMediaVolume) {
                return;
            }
            this.volumeDialog.getVolumeListAdapter().startBackAim();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider() && (this.mRow.getStream() == 2 || this.mRow.getStream() == 5)) {
                int progress = oplusVolumeSeekBar.getProgress();
                int i = OplusVolumeDialogImpl.RINGER_MODE_MIN_LEVEL * 100;
                if (progress <= i && this.mRecordProgressWhenStartTouch <= i) {
                    this.volumeDialog.isNeedToShowToastUi(3);
                }
            }
            VolumeDialogController.StreamState ss = this.mRow.getSs();
            if (ss != null && (ss.level != (impliedLevel = OplusVolumeRow.Companion.getImpliedLevel(oplusVolumeSeekBar, oplusVolumeSeekBar.getProgress())) || this.mRow.getRequestedLevel() != impliedLevel)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", NotificationPanelViewController$.ExternalSyntheticOutline0.m(this.mRow.getRequestedLevel(), oplusVolumeSeekBar.getProgress(), " progress=", "OplusVolumeRow-->", FloatList$.ExternalSyntheticOutline0.m("onStopTrackingTouch flush userLevel=", impliedLevel, ss.level, " ss.level=", " requestedLevel=")));
                }
                this.mSeekBarHandler.removeMessages(1);
                this.volumeDialog.mController.setActiveStream(this.mRow.getStream(), false);
                this.volumeDialog.mController.setStreamVolume(this.mRow.getStream(), impliedLevel, false);
                this.mRow.setRequestedLevel(impliedLevel);
                this.mRow.setUserAttempt(SystemClock.uptimeMillis());
            }
            this.mRow.setTracking(false);
            this.mAdjustCount = 0L;
            this.volumeDialog.mVibrateAbility.removeVibrate();
        }

        @Override // com.oplus.systemui.volume.OplusVolumeSeekBar.OnSeekBarChangeListener
        public void onTouchDown(OplusVolumeSeekBar oplusVolumeSeekBar) {
            this.volumeDialog.performTouchDownAnim();
        }

        @Override // com.oplus.systemui.volume.OplusVolumeSeekBar.OnSeekBarChangeListener
        public void onTouchUp(OplusVolumeSeekBar oplusVolumeSeekBar) {
            this.volumeDialog.performTouchUpAnim();
            this.mRow.setTracking(false);
        }

        public final void updateLevel(int i) {
            this.volumeDialog.mController.setActiveStream(this.mRow.getStream(), false);
            this.volumeDialog.mController.setStreamVolume(this.mRow.getStream(), i, false);
            this.mRow.setRequestedLevel(i);
            VolumeDialogController.StreamState ss = this.mRow.getSs();
            LogUtil.i$default(LogUtil.TAG_VOLUME, OplusVolumeRow.TAG, "updateLevel, mRow.ss.levelMin=" + (ss != null ? Integer.valueOf(ss.levelMin) : null) + " userLevel " + i, null, 8);
            VolumeDialogController.StreamState ss2 = this.mRow.getSs();
            if (ss2 != null) {
                if (ss2.levelMax == i || ss2.levelMin == i) {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isLuxunVibratorSupport()) {
                        VibrateAbility.Companion companion = VibrateAbility.Companion;
                        int i2 = ss2.levelMax;
                        companion.getClass();
                        int vibratorStrengthWithLimit = VibrateAbility.Companion.getVibratorStrengthWithLimit(i, i2, 800, 1200);
                        this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(154, vibratorStrengthWithLimit, false);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(vibratorStrengthWithLimit, "updateLevel, max or min ,vibrateByLMVibrator WaveformEffect=154strength= ", "OplusVolumeRow-->", "SystemUi--Volume");
                        }
                    } else {
                        this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(154, false);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            Log.d("SystemUi--Volume", "OplusVolumeRow-->updateLevel, max or min , vibrateByLMVibrator WaveformEffect=154");
                        }
                    }
                } else {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isLuxunVibratorSupport()) {
                        VibrateAbility.Companion companion2 = VibrateAbility.Companion;
                        int i3 = ss2.levelMax;
                        companion2.getClass();
                        int vibratorStrengthWithLimit2 = VibrateAbility.Companion.getVibratorStrengthWithLimit(i, i3, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION, 2400);
                        this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(152, vibratorStrengthWithLimit2, true);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(vibratorStrengthWithLimit2, "updateLevel,vibrateByLMVibrator WaveformEffect=152strength= ", "OplusVolumeRow-->", "SystemUi--Volume");
                        }
                    } else {
                        this.volumeDialog.mVibrateAbility.vibrateByLMVibrator(152, true);
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            Log.d("SystemUi--Volume", "OplusVolumeRow-->updateLevel,vibrateByLMVibrator WaveformEffect=152");
                        }
                    }
                }
            }
            this.mAdjustCount++;
            Events.writeEvent(9, new Object[]{Integer.valueOf(this.mRow.getStream()), Integer.valueOf(i)});
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeRow$initRow$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements View.OnTouchListener {
        public /* synthetic */ OplusVolumeRow this$0;

        /* JADX WARN: Code duplicated, block: B:55:0x0125  */
        /* JADX WARN: Code duplicated, block: B:69:0x0175  */
        /* JADX WARN: Code duplicated, block: B:71:0x017b  */
        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            OplusVolumeRow oplusVolumeRow;
            if (this.this$0.volumeDialog.getExpandedState().booleanValue()) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3) {
                    OplusVolumeRow oplusVolumeRow2 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow2.getStream(), "sliderFrame onTouch cancel/up, stream = ", "OplusVolumeRow-->", "SystemUi--Volume");
                    }
                    this.this$0.setTracking(false);
                    OplusVolumeRow oplusVolumeRowFindRow = this.this$0.volumeDialog.findRow(5);
                    OplusVolumeRow oplusVolumeRowFindRow2 = this.this$0.volumeDialog.findRow(2);
                    if (oplusVolumeRowFindRow == null) {
                        LogUtil.w$default(8, LogUtil.TAG_VOLUME, OplusVolumeRow.TAG, "notificationRow is null, no need to start animation");
                        return true;
                    }
                    if (oplusVolumeRowFindRow2 == null) {
                        LogUtil.w$default(8, LogUtil.TAG_VOLUME, OplusVolumeRow.TAG, "ringRow is null, no need to start animation");
                        return true;
                    }
                    OplusVolumeDialogImpl oplusVolumeDialogImpl = this.this$0.volumeDialog;
                    FrameLayout sliderFrame = oplusVolumeRowFindRow.getSliderFrame();
                    oplusVolumeDialogImpl.getClass();
                    if (!OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame)) {
                        OplusVolumeDialogImpl oplusVolumeDialogImpl2 = this.this$0.volumeDialog;
                        FrameLayout sliderFrame2 = oplusVolumeRowFindRow2.getSliderFrame();
                        oplusVolumeDialogImpl2.getClass();
                        if (OplusVolumeDialogImpl.inRangeOfView(motionEvent, sliderFrame2)) {
                            if (this.this$0.volumeDialog.mState == null && (this.this$0.volumeDialog.mState.ringerModeInternal == 0 || this.this$0.volumeDialog.mState.ringerModeInternal == 1)) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isOplusAlertSlider()) {
                                    this.this$0.volumeDialog.isNeedToShowToastUi((this.this$0.volumeDialog.mState.ringerModeInternal == 0 || ThreeKeyStealthModeObserver.Companion.getInstance().isStealthModeOn()) ? 4 : 5);
                                } else {
                                    oplusVolumeRow = this.this$0;
                                    if (!oplusVolumeRow.isMultiMediaVolume) {
                                        oplusVolumeRow.volumeDialog.mController.setActiveStream(this.this$0.getStream(), false);
                                        this.this$0.volumeDialog.getVolumeListAdapter().resetAnim();
                                    }
                                }
                            } else {
                                oplusVolumeRow = this.this$0;
                                if (!oplusVolumeRow.isMultiMediaVolume) {
                                    oplusVolumeRow.volumeDialog.mController.setActiveStream(this.this$0.getStream(), false);
                                    this.this$0.volumeDialog.getVolumeListAdapter().resetAnim();
                                }
                            }
                        }
                    } else if (this.this$0.volumeDialog.mState == null) {
                        oplusVolumeRow = this.this$0;
                        if (!oplusVolumeRow.isMultiMediaVolume) {
                            oplusVolumeRow.volumeDialog.mController.setActiveStream(this.this$0.getStream(), false);
                            this.this$0.volumeDialog.getVolumeListAdapter().resetAnim();
                        }
                    } else {
                        oplusVolumeRow = this.this$0;
                        if (!oplusVolumeRow.isMultiMediaVolume) {
                            oplusVolumeRow.volumeDialog.mController.setActiveStream(this.this$0.getStream(), false);
                            this.this$0.volumeDialog.getVolumeListAdapter().resetAnim();
                        }
                    }
                }
                OplusVolumeRow oplusVolumeRow3 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(KeyguardLogger$.ExternalSyntheticOutline0.m("onTouch event.getAction():", motionEvent.getAction(), " isAnimationStart:", " event.getX():", oplusVolumeRow3.volumeDialog.isAnimationStart), motionEvent.getX(), " event.getY():", motionEvent.getY(), "OplusVolumeRow-->"));
                }
            } else {
                if (this.this$0.getStream() != this.this$0.volumeDialog.mActiveStream || this.this$0.volumeDialog.getOnlyRow() == null || !this.this$0.getTouchable()) {
                    OplusVolumeRow oplusVolumeRow4 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", MobileInputLogger$.ExternalSyntheticOutline0.m("sliderFrame onTouch, stream = ", oplusVolumeRow4.getStream(), ", touchable = ", "OplusVolumeRow-->", oplusVolumeRow4.getTouchable()));
                    }
                    return false;
                }
                int action2 = motionEvent.getAction();
                if (action2 == 0) {
                    OplusVolumeRow oplusVolumeRow5 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow5.getStream(), "sliderFrame onTouch down, stream = ", "OplusVolumeRow-->", "SystemUi--Volume");
                    }
                    this.this$0.volumeDialog.performTouchDownAnim();
                    return true;
                }
                if (action2 == 1 || action2 == 3) {
                    OplusVolumeRow oplusVolumeRow6 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow6.getStream(), "sliderFrame onTouch cancel/up, stream = ", "OplusVolumeRow-->", "SystemUi--Volume");
                    }
                    this.this$0.volumeDialog.performTouchUpAnim();
                    return true;
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.view.OplusVolumeRow$initRow$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements View.OnClickListener {
        public /* synthetic */ OplusVolumeRow this$0;

        /* JADX WARN: Code duplicated, block: B:22:0x00a0  */
        /* JADX WARN: Code duplicated, block: B:24:0x00a9  */
        /* JADX WARN: Code duplicated, block: B:25:0x00b0  */
        /* JADX WARN: Code duplicated, block: B:28:0x00b9  */
        /* JADX WARN: Code duplicated, block: B:31:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:33:0x00cf  */
        /* JADX WARN: Code duplicated, block: B:34:0x00f6  */
        /* JADX WARN: Code duplicated, block: B:35:0x0121  */
        /* JADX WARN: Code duplicated, block: B:37:0x0127  */
        /* JADX WARN: Code duplicated, block: B:39:0x012f  */
        /* JADX WARN: Code duplicated, block: B:41:0x0133  */
        /* JADX WARN: Code duplicated, block: B:42:0x014b  */
        /* JADX WARN: Code duplicated, block: B:50:0x0193 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:51:0x0195  */
        /* JADX WARN: Code duplicated, block: B:52:0x019a  */
        /* JADX WARN: Code duplicated, block: B:54:0x01a0  */
        /* JADX WARN: Code duplicated, block: B:55:0x01a3  */
        /* JADX WARN: Code duplicated, block: B:58:0x0222  */
        /* JADX WARN: Code duplicated, block: B:61:0x022c  */
        /* JADX WARN: Code duplicated, block: B:62:0x0230  */
        /* JADX WARN: Code duplicated, block: B:64:0x0240  */
        /* JADX WARN: Code duplicated, block: B:65:0x0245  */
        /* JADX WARN: Code duplicated, block: B:67:0x024b  */
        /* JADX WARN: Code duplicated, block: B:68:0x024e  */
        /* JADX WARN: Code duplicated, block: B:74:0x0263  */
        /* JADX WARN: Code duplicated, block: B:76:0x026d  */
        /* JADX WARN: Code duplicated, block: B:78:0x0271  */
        /* JADX WARN: Code duplicated, block: B:79:0x0277 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:80:0x0279  */
        /* JADX WARN: Code duplicated, block: B:81:0x027e  */
        /* JADX WARN: Code duplicated, block: B:84:0x0289  */
        /* JADX WARN: Code duplicated, block: B:87:0x02a3  */
        /* JADX WARN: Code duplicated, block: B:89:0x02ac  */
        /* JADX WARN: Instruction removed from duplicated block: B:22:0x00a0, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:33:0x00cf, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:34:0x00f6, please report this as an issue */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            VolumeDialogController.StreamState ss;
            Integer numValueOf;
            boolean zAreEqual;
            OplusVolumeRow oplusVolumeRow;
            VolumeDialogController.StreamState ss2;
            OplusVolumeRow oplusVolumeRow2;
            OplusVolumeRow oplusVolumeRow3;
            OplusVolumeRow oplusVolumeRow4;
            OplusVolumeRow oplusVolumeRow5;
            VolumeDialogController.StreamState ss3;
            int lastAudibleLevel;
            VolumeDialogController.StreamState ss4;
            OplusVolumeRow oplusVolumeRow6;
            int lastAudibleLevel2;
            VolumeDialogController.StreamState ss5;
            int lastAudibleLevel3;
            VolumeDialogController.StreamState ss6;
            OplusVolumeSeekBar slider;
            Events.writeEvent(7, new Object[]{Integer.valueOf(this.this$0.getStream()), Integer.valueOf(this.this$0.getIconState())});
            this.this$0.volumeDialog.mController.setActiveStream(this.this$0.getStream(), false);
            int i = 1;
            this.this$0.volumeDialog.mIsIconClicked = true;
            OplusVolumeSeekBar slider2 = this.this$0.getSlider();
            if (slider2 != null) {
                slider2.endCustomDeformation();
                slider2.stopPhysicsMove();
            }
            if (this.this$0.getStream() == 2) {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isOplusAlertSlider()) {
                    ss = this.this$0.getSs();
                    if (ss != null) {
                        numValueOf = Integer.valueOf(ss.level);
                    } else {
                        numValueOf = null;
                    }
                    VolumeDialogController.StreamState ss7 = this.this$0.getSs();
                    zAreEqual = Intrinsics.areEqual(numValueOf, ss7 != null ? Integer.valueOf(ss7.levelMin) : null);
                    oplusVolumeRow = this.this$0;
                    if (zAreEqual) {
                        ss2 = oplusVolumeRow.getSs();
                        if (ss2 != null) {
                            oplusVolumeRow2 = this.this$0;
                            if (ss2.level > ss2.levelMin) {
                                if (oplusVolumeRow2.isMultiMediaVolume) {
                                    Prefs.putInt(oplusVolumeRow2.volumeDialog.mUserContextProvider.getUserContext(), Bundler$.ExternalSyntheticOutline0.m(oplusVolumeRow2.getMediaPackageName(), "_volume_lastAudibleLevel"), ss2.level);
                                } else {
                                    Prefs.putInt(oplusVolumeRow2.volumeDialog.mUserContextProvider.getUserContext(), Bundler$.ExternalSyntheticOutline0.m(AudioSystem.streamToString(oplusVolumeRow2.getStream()), "_lastAudibleLevel"), ss2.level);
                                }
                            }
                        }
                    } else if (oplusVolumeRow.isMultiMediaVolume) {
                        oplusVolumeRow.setLastAudibleLevel(Prefs.getInt(oplusVolumeRow.volumeDialog.mUserContextProvider.getUserContext(), this.this$0.getMediaPackageName() + "_volume_lastAudibleLevel", 1));
                    } else {
                        oplusVolumeRow.setLastAudibleLevel(Prefs.getInt(oplusVolumeRow.volumeDialog.mUserContextProvider.getUserContext(), AudioSystem.streamToString(this.this$0.getStream()) + "_lastAudibleLevel", 1));
                    }
                    oplusVolumeRow3 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("initRow: stream = ", oplusVolumeRow3.getStream(), oplusVolumeRow3.getLastAudibleLevel(), ",row.lastAudibleLevel = ", "OplusVolumeRow-->"));
                    }
                    oplusVolumeRow4 = this.this$0;
                    if (oplusVolumeRow4.isMultiMediaVolume) {
                        if (zAreEqual) {
                            lastAudibleLevel3 = oplusVolumeRow4.getLastAudibleLevel();
                        } else {
                            ss5 = oplusVolumeRow4.getSs();
                            if (ss5 != null) {
                                lastAudibleLevel3 = ss5.levelMin;
                            } else {
                                lastAudibleLevel3 = 0;
                            }
                        }
                        float f = lastAudibleLevel3 / 100.0f;
                        this.this$0.volumeDialog.mAudioManager.setParameters(MultiMediaRepository.MULTI_MEDIA_SET_TRACK_VOLUME + f + ":" + this.this$0.getUid());
                        this.this$0.volumeDialog.mAudioManager.setParameters(MultiMediaRepository.MULTI_MEDIA_SET_TRACK_VOLUME + f + ":" + MultiMediaVolumeUtil.getAppCloneUid(this.this$0.getUid()));
                        Settings.Global.putFloat(this.this$0.context.getContentResolver(), this.this$0.getMediaPackageName() + MultiMediaRepository.MULTI_MEDIA_PACKAGE_SUFFIX, f);
                        ss6 = this.this$0.getSs();
                        if (ss6 != null) {
                            ss6.level = lastAudibleLevel3;
                        }
                        slider = this.this$0.getSlider();
                        if (slider != null) {
                            slider.setProgress(lastAudibleLevel3, true);
                        }
                    } else {
                        VolumeDialogController volumeDialogController = oplusVolumeRow4.volumeDialog.mController;
                        int stream = this.this$0.getStream();
                        oplusVolumeRow5 = this.this$0;
                        if (zAreEqual) {
                            lastAudibleLevel = oplusVolumeRow5.getLastAudibleLevel();
                        } else {
                            ss3 = oplusVolumeRow5.getSs();
                            if (ss3 != null) {
                                lastAudibleLevel = ss3.levelMin;
                            } else {
                                lastAudibleLevel = 0;
                            }
                        }
                        volumeDialogController.setStreamVolume(stream, lastAudibleLevel, false);
                    }
                    if (this.this$0.getStream() != 2 || this.this$0.getStream() == 5) {
                        ss4 = this.this$0.getSs();
                        oplusVolumeRow6 = this.this$0;
                        if (ss4 == null) {
                            oplusVolumeRow6.setLastLevel(0);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow6.getLastLevel(), "onClickIcon ss= null，row.lastLevel=", "OplusVolumeRow-->", "SystemUi--Volume");
                            }
                        } else if (ss4.muted) {
                            oplusVolumeRow6.setLastLevel(ss4.levelMin);
                        } else {
                            if (zAreEqual) {
                                lastAudibleLevel2 = oplusVolumeRow6.getLastAudibleLevel();
                            } else {
                                lastAudibleLevel2 = ss4.levelMin;
                            }
                            oplusVolumeRow6.setLastLevel(lastAudibleLevel2);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                                Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onClickIcon row= ", oplusVolumeRow6.getStream(), oplusVolumeRow6.getLastLevel(), ",row.lastLevel=", "OplusVolumeRow-->"));
                            }
                        }
                    }
                } else {
                    boolean z = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(this.this$0.context) != 0;
                    if (this.this$0.volumeDialog.mState == null || this.this$0.volumeDialog.mState.ringerModeInternal != 2) {
                        i = 2;
                    } else if (!z) {
                        i = 0;
                    }
                    this.this$0.volumeDialog.mController.setRingerMode(i, false);
                    this.this$0.volumeDialog.mOplusRMChangeVibrateHelper.vibrateOnRMChanged(i, OplusVolumeRow.TAG);
                }
            } else {
                ss = this.this$0.getSs();
                if (ss != null) {
                    numValueOf = Integer.valueOf(ss.level);
                } else {
                    numValueOf = null;
                }
                VolumeDialogController.StreamState ss8 = this.this$0.getSs();
                zAreEqual = Intrinsics.areEqual(numValueOf, ss8 != null ? Integer.valueOf(ss8.levelMin) : null);
                oplusVolumeRow = this.this$0;
                if (zAreEqual) {
                    ss2 = oplusVolumeRow.getSs();
                    if (ss2 != null) {
                        oplusVolumeRow2 = this.this$0;
                        if (ss2.level > ss2.levelMin) {
                            if (oplusVolumeRow2.isMultiMediaVolume) {
                                Prefs.putInt(oplusVolumeRow2.volumeDialog.mUserContextProvider.getUserContext(), Bundler$.ExternalSyntheticOutline0.m(oplusVolumeRow2.getMediaPackageName(), "_volume_lastAudibleLevel"), ss2.level);
                            } else {
                                Prefs.putInt(oplusVolumeRow2.volumeDialog.mUserContextProvider.getUserContext(), Bundler$.ExternalSyntheticOutline0.m(AudioSystem.streamToString(oplusVolumeRow2.getStream()), "_lastAudibleLevel"), ss2.level);
                            }
                        }
                    }
                } else if (oplusVolumeRow.isMultiMediaVolume) {
                    oplusVolumeRow.setLastAudibleLevel(Prefs.getInt(oplusVolumeRow.volumeDialog.mUserContextProvider.getUserContext(), this.this$0.getMediaPackageName() + "_volume_lastAudibleLevel", 1));
                } else {
                    oplusVolumeRow.setLastAudibleLevel(Prefs.getInt(oplusVolumeRow.volumeDialog.mUserContextProvider.getUserContext(), AudioSystem.streamToString(this.this$0.getStream()) + "_lastAudibleLevel", 1));
                }
                oplusVolumeRow3 = this.this$0;
                if (LogUtil.getNormalDebug()) {
                    Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("initRow: stream = ", oplusVolumeRow3.getStream(), oplusVolumeRow3.getLastAudibleLevel(), ",row.lastAudibleLevel = ", "OplusVolumeRow-->"));
                }
                oplusVolumeRow4 = this.this$0;
                if (oplusVolumeRow4.isMultiMediaVolume) {
                    if (zAreEqual) {
                        lastAudibleLevel3 = oplusVolumeRow4.getLastAudibleLevel();
                    } else {
                        ss5 = oplusVolumeRow4.getSs();
                        if (ss5 != null) {
                            lastAudibleLevel3 = ss5.levelMin;
                        } else {
                            lastAudibleLevel3 = 0;
                        }
                    }
                    float f2 = lastAudibleLevel3 / 100.0f;
                    this.this$0.volumeDialog.mAudioManager.setParameters(MultiMediaRepository.MULTI_MEDIA_SET_TRACK_VOLUME + f2 + ":" + this.this$0.getUid());
                    this.this$0.volumeDialog.mAudioManager.setParameters(MultiMediaRepository.MULTI_MEDIA_SET_TRACK_VOLUME + f2 + ":" + MultiMediaVolumeUtil.getAppCloneUid(this.this$0.getUid()));
                    Settings.Global.putFloat(this.this$0.context.getContentResolver(), this.this$0.getMediaPackageName() + MultiMediaRepository.MULTI_MEDIA_PACKAGE_SUFFIX, f2);
                    ss6 = this.this$0.getSs();
                    if (ss6 != null) {
                        ss6.level = lastAudibleLevel3;
                    }
                    slider = this.this$0.getSlider();
                    if (slider != null) {
                        slider.setProgress(lastAudibleLevel3, true);
                    }
                } else {
                    VolumeDialogController volumeDialogController2 = oplusVolumeRow4.volumeDialog.mController;
                    int stream2 = this.this$0.getStream();
                    oplusVolumeRow5 = this.this$0;
                    if (zAreEqual) {
                        lastAudibleLevel = oplusVolumeRow5.getLastAudibleLevel();
                    } else {
                        ss3 = oplusVolumeRow5.getSs();
                        if (ss3 != null) {
                            lastAudibleLevel = ss3.levelMin;
                        } else {
                            lastAudibleLevel = 0;
                        }
                    }
                    volumeDialogController2.setStreamVolume(stream2, lastAudibleLevel, false);
                }
                if (this.this$0.getStream() != 2) {
                    ss4 = this.this$0.getSs();
                    oplusVolumeRow6 = this.this$0;
                    if (ss4 == null) {
                        oplusVolumeRow6.setLastLevel(0);
                        if (LogUtil.getNormalDebug()) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow6.getLastLevel(), "onClickIcon ss= null，row.lastLevel=", "OplusVolumeRow-->", "SystemUi--Volume");
                        }
                    } else if (ss4.muted) {
                        oplusVolumeRow6.setLastLevel(ss4.levelMin);
                    } else {
                        if (zAreEqual) {
                            lastAudibleLevel2 = oplusVolumeRow6.getLastAudibleLevel();
                        } else {
                            lastAudibleLevel2 = ss4.levelMin;
                        }
                        oplusVolumeRow6.setLastLevel(lastAudibleLevel2);
                        if (LogUtil.getNormalDebug()) {
                            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onClickIcon row= ", oplusVolumeRow6.getStream(), oplusVolumeRow6.getLastLevel(), ",row.lastLevel=", "OplusVolumeRow-->"));
                        }
                    }
                } else {
                    ss4 = this.this$0.getSs();
                    oplusVolumeRow6 = this.this$0;
                    if (ss4 == null) {
                        oplusVolumeRow6.setLastLevel(0);
                        if (LogUtil.getNormalDebug()) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow6.getLastLevel(), "onClickIcon ss= null，row.lastLevel=", "OplusVolumeRow-->", "SystemUi--Volume");
                        }
                    } else if (ss4.muted) {
                        oplusVolumeRow6.setLastLevel(ss4.levelMin);
                    } else {
                        if (zAreEqual) {
                            lastAudibleLevel2 = oplusVolumeRow6.getLastAudibleLevel();
                        } else {
                            lastAudibleLevel2 = ss4.levelMin;
                        }
                        oplusVolumeRow6.setLastLevel(lastAudibleLevel2);
                        if (LogUtil.getNormalDebug()) {
                            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onClickIcon row= ", oplusVolumeRow6.getStream(), oplusVolumeRow6.getLastLevel(), ",row.lastLevel=", "OplusVolumeRow-->"));
                        }
                    }
                }
            }
            this.this$0.setUserAttempt(0L);
        }
    }

    public static Unit $r8$lambda$8L13Z3MEocKq9fR7I2d0xlcJ0wA(OplusVolumeRow oplusVolumeRow, Drawable drawable) {
        EffectiveAnimationView effectiveAnimationView;
        if (drawable != null && (effectiveAnimationView = oplusVolumeRow.icon) != null) {
            effectiveAnimationView.setImageDrawable(drawable);
        }
        return Unit.INSTANCE;
    }

    public OplusVolumeRow(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl, int i, int i2, int i3, boolean z, boolean z2) {
        this.context = context;
        this.volumeDialog = oplusVolumeDialogImpl;
        this.stream = i;
        this.iconRes = i2;
        this.iconMuteRes = i3;
        this.important = z;
        this.defaultStream = z2;
    }

    public final ObjectAnimator getAnim() {
        return this.anim;
    }

    public final int getAnimTargetProgress() {
        return this.animTargetProgress;
    }

    public final boolean getDefaultStream() {
        return this.defaultStream;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final EffectiveAnimationView getIcon() {
        return this.icon;
    }

    public final int getIconMuteRes() {
        return this.iconMuteRes;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getIconState() {
        return this.iconState;
    }

    public final boolean getImportant() {
        return this.important;
    }

    public final String getJsonRes() {
        return this.jsonRes;
    }

    public final int getLastAudibleLevel() {
        return this.lastAudibleLevel;
    }

    public final int getLastIconRes() {
        return this.lastIconRes;
    }

    public final int getLastLevel() {
        return this.lastLevel;
    }

    public final int getLastVlevel() {
        return this.lastVlevel;
    }

    public final String getMediaPackageName() {
        return this.mediaPackageName;
    }

    public final TextView getNameText() {
        return this.nameText;
    }

    public final int getPlayerId() {
        return this.playerId;
    }

    public final int getRequestedLevel() {
        return this.requestedLevel;
    }

    public final OplusVolumeSeekBar getSlider() {
        return this.slider;
    }

    public final FrameLayout getSliderFrame() {
        return this.sliderFrame;
    }

    public final VolumeDialogController.StreamState getSs() {
        return this.ss;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final int getStream() {
        return this.stream;
    }

    public final boolean getTouchable() {
        return this.touchable;
    }

    public final boolean getTracking() {
        return this.tracking;
    }

    public final int getUid() {
        return this.uid;
    }

    public final long getUserAttempt() {
        return this.userAttempt;
    }

    public final View getView() {
        return this.view;
    }

    public final int getVolumeListBindDataSource() {
        return this.volumeListBindDataSource;
    }

    public final int getVolumeListBoundTextColor() {
        return this.volumeListBoundTextColor;
    }

    public final void initRow() {
        View viewInflate = this.volumeDialog.getDialogInflater().inflate(2131559975, (ViewGroup) null);
        this.view = viewInflate;
        this.slider = viewInflate != null ? (OplusVolumeSeekBar) viewInflate.findViewById(2131364971) : null;
        View view = this.view;
        this.sliderFrame = view != null ? (FrameLayout) view.findViewById(2131364972) : null;
        View view2 = this.view;
        this.nameText = view2 != null ? (TextView) view2.findViewById(2131364970) : null;
        View view3 = this.view;
        this.icon = view3 != null ? (EffectiveAnimationView) view3.findViewById(2131366037) : null;
        View view4 = this.view;
        if (view4 != null) {
            view4.setId(this.stream);
        }
        View view5 = this.view;
        if (view5 != null) {
            view5.setTag(this);
        }
        OplusVolumeSeekBar oplusVolumeSeekBar = this.slider;
        if (oplusVolumeSeekBar != null) {
            oplusVolumeSeekBar.setOnSeekBarChangeListener(new VolumeSeekBarChangeListener(this, this, this.volumeDialog));
        }
        OplusVolumeSeekBar oplusVolumeSeekBar2 = this.slider;
        if (oplusVolumeSeekBar2 != null) {
            oplusVolumeSeekBar2.setIcon(this.icon);
        }
        EffectiveAnimationView effectiveAnimationView = this.icon;
        if (effectiveAnimationView != null) {
            effectiveAnimationView.setClipToOutline(true);
        }
        EffectiveAnimationView effectiveAnimationView2 = this.icon;
        if (effectiveAnimationView2 != null) {
            effectiveAnimationView2.setOutlineProvider(appIconOutlineProvider);
        }
        boolean z = ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1;
        EffectiveAnimationView effectiveAnimationView3 = this.icon;
        if (effectiveAnimationView3 != null) {
            effectiveAnimationView3.setScaleType(z ? ImageView.ScaleType.FIT_END : ImageView.ScaleType.FIT_START);
        }
        EffectiveAnimationView effectiveAnimationView4 = this.icon;
        if (effectiveAnimationView4 != null) {
            effectiveAnimationView4.setFailureListener(OplusVolumeRow$initView$1.INSTANCE);
        }
        if (this.volumeDialog.mOplusVolumeDialogView.isSeekbarBgReplaced) {
            float dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131173981);
            OplusVolumeSeekBar oplusVolumeSeekBar3 = this.slider;
            if (oplusVolumeSeekBar3 != null) {
                oplusVolumeSeekBar3.setBackgroundRadius(dimensionPixelSize);
                oplusVolumeSeekBar3.setPaddingVertical(dimensionPixelSize);
                oplusVolumeSeekBar3.preCalcMaterialClipPath();
            }
        }
        int i = this.iconRes;
        this.lastIconRes = i;
        this.anim = null;
        this.isVerticalLayout = false;
        this.lastLevel = 0;
        this.jsonRes = "";
        this.lastVlevel = 0;
        if (this.isMultiMediaVolume) {
            String str = this.mediaPackageName;
            OplusVolumeRow$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusVolumeRow$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            Handler workerHandler = this.volumeDialog.mController.getWorkerHandler();
            loadPackageIconAsync.1 r8 = new loadPackageIconAsync.1(0);
            r8.$pkg = str;
            r8.$callback = externalSyntheticLambda0;
            VarHandle.storeStoreFence();
            workerHandler.post(r8);
            VolumeDialogController.StreamState streamState = this.ss;
            if (streamState != null) {
                Util.setText(this.nameText, streamState.remoteLabel);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    TextView textView = this.nameText;
                    CharSequence text = textView != null ? textView.getText() : null;
                    Log.d("SystemUi--Volume", "OplusVolumeRow-->".concat("initRow setProgress " + ((Object) text) + " " + streamState.level));
                }
                OplusVolumeSeekBar oplusVolumeSeekBar4 = this.slider;
                if (oplusVolumeSeekBar4 != null) {
                    oplusVolumeSeekBar4.setProgress(streamState.level);
                }
            }
        } else {
            EffectiveAnimationView effectiveAnimationView5 = this.icon;
            if (effectiveAnimationView5 != null) {
                effectiveAnimationView5.setImageResource(i);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            TextView textView2 = this.nameText;
            int i2 = this.stream;
            int i3 = this.lastLevel;
            VolumeDialogController.StreamState streamState2 = this.ss;
            Log.d("SystemUi--Volume", "OplusVolumeRow-->".concat("initRow row.nameText= " + textView2 + ",row.stream=" + i2 + ",row.lastLevel=" + i3 + " ,row.ss: " + streamState2 + " ,row.ss.level: " + (streamState2 != null ? Integer.valueOf(streamState2.level) : null)));
        }
        FrameLayout frameLayout = this.sliderFrame;
        if (frameLayout != null) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4();
            anonymousClass4.this$0 = this;
            VarHandle.storeStoreFence();
            frameLayout.setOnTouchListener(anonymousClass4);
        }
        int i4 = this.stream;
        EffectiveAnimationView effectiveAnimationView6 = this.icon;
        if (i4 == 10) {
            if (effectiveAnimationView6 != null) {
                effectiveAnimationView6.setImportantForAccessibility(2);
            }
        } else if (effectiveAnimationView6 != null) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5();
            anonymousClass5.this$0 = this;
            VarHandle.storeStoreFence();
            effectiveAnimationView6.setOnClickListener(anonymousClass5);
        }
    }

    public final boolean isVolumePanelExpanded() {
        return this.volumeDialog.getExpandedState().booleanValue();
    }

    public final void setAnim(ObjectAnimator objectAnimator) {
        this.anim = objectAnimator;
    }

    public final void setAnimTargetProgress(int i) {
        this.animTargetProgress = i;
    }

    public final void setDefaultStream(boolean z) {
        this.defaultStream = z;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    public final void setIcon(EffectiveAnimationView effectiveAnimationView) {
        this.icon = effectiveAnimationView;
    }

    public final void setIconMuteRes(int i) {
        this.iconMuteRes = i;
    }

    public final void setIconRes(int i) {
        this.iconRes = i;
    }

    public final void setIconState(int i) {
        this.iconState = i;
    }

    public final void setImportant(boolean z) {
        this.important = z;
    }

    public final void setJsonRes(String str) {
        this.jsonRes = str;
    }

    public final void setLastAudibleLevel(int i) {
        this.lastAudibleLevel = i;
    }

    public final void setLastIconRes(int i) {
        this.lastIconRes = i;
    }

    public final void setLastLevel(int i) {
        this.lastLevel = i;
    }

    public final void setLastVlevel(int i) {
        this.lastVlevel = i;
    }

    public final void setMediaPackageName(String str) {
        this.mediaPackageName = str;
    }

    public final void setMultiMediaVolume(boolean z) {
        this.isMultiMediaVolume = z;
    }

    public final void setNameText(TextView textView) {
        this.nameText = textView;
    }

    public final void setPlayerId(int i) {
        this.playerId = i;
    }

    public final void setRequestedLevel(int i) {
        this.requestedLevel = i;
    }

    public final void setShowInPanel(boolean z) {
        this.isShowInPanel = z;
    }

    public final void setShowInSide(boolean z) {
        this.isShowInSide = z;
    }

    public final void setSlider(OplusVolumeSeekBar oplusVolumeSeekBar) {
        this.slider = oplusVolumeSeekBar;
    }

    public final void setSliderFrame(FrameLayout frameLayout) {
        this.sliderFrame = frameLayout;
    }

    public final void setSs(VolumeDialogController.StreamState streamState) {
        this.ss = streamState;
    }

    public final void setStartTimestamp(long j) {
        this.startTimestamp = j;
    }

    public final void setStream(int i) {
        this.stream = i;
    }

    public final void setSuperVolume(boolean z) {
        this.isSuperVolume = z;
    }

    public final void setTouchable(boolean z) {
        this.touchable = z;
        OplusVolumeSeekBar oplusVolumeSeekBar = this.slider;
        if (oplusVolumeSeekBar != null) {
            oplusVolumeSeekBar.setTouchable(z);
        }
    }

    public final void setTracking(boolean z) {
        this.tracking = z;
    }

    public final void setUid(int i) {
        this.uid = i;
    }

    public final void setUserAttempt(long j) {
        this.userAttempt = j;
    }

    public final void setVerticalLayout(boolean z) {
        this.isVerticalLayout = z;
    }

    public final void setView(View view) {
        this.view = view;
    }

    public final void setVolumeListBindDataSource(int i) {
        this.volumeListBindDataSource = i;
    }

    public final void setVolumeListBoundTextColor(int i) {
        this.volumeListBoundTextColor = i;
    }
}
