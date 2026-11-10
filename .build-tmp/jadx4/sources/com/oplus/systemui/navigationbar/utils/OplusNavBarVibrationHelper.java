package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.os.CombinedVibration;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.VibratorManager;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.os.WaveformEffect;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.HapticSettingObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNavBarVibrationHelper {
    private final Context context;
    public boolean hasActiveVibration;
    private LinearmotorVibrator linearmotorVibrator;
    private VibratorManager vibratorManager;
    public static final Companion Companion = new Companion(new OplusNavBarVibrationHelper$Companion$$ExternalSyntheticLambda0());
    public static final String TAG = "OplusNavBarVibrationHelper";
    private static final long[] ERM_TIMINGS = {600, 65};
    private static final int[] ERM_AMPLITUPES = {0, 1};

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion extends SingletonHolderWithParam {
    }

    public OplusNavBarVibrationHelper(Context context) {
        this.context = context;
        Object systemService = context.getSystemService("linearmotor");
        this.linearmotorVibrator = systemService instanceof LinearmotorVibrator ? (LinearmotorVibrator) systemService : null;
        Object systemService2 = context.getSystemService("vibrator_manager");
        this.vibratorManager = systemService2 instanceof VibratorManager ? (VibratorManager) systemService2 : null;
    }

    public final void cancelVibrate() {
        if (this.hasActiveVibration) {
            this.hasActiveVibration = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(TAG, "-->cancelVibrate", "SystemUi--Common");
            }
            VibratorManager vibratorManager = this.vibratorManager;
            if (vibratorManager != null) {
                vibratorManager.cancel();
            }
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void performVibrate() {
        if (HapticSettingObserver.getInstance().mSystemHapticEnabled) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(TAG, "-->performVibrate", "SystemUi--Common");
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isLinearVibratorSupport()) {
                WaveformEffect waveformEffectBuild = new WaveformEffect.Builder().setEffectType(527).setUsageHint(13).setStrengthSettingEnabled(true).build();
                LinearmotorVibrator linearmotorVibrator = this.linearmotorVibrator;
                if (linearmotorVibrator != null) {
                    linearmotorVibrator.vibrate(waveformEffectBuild);
                }
            } else {
                CombinedVibration combinedVibrationCreateParallel = CombinedVibration.createParallel(VibrationEffect.createWaveform(ERM_TIMINGS, ERM_AMPLITUPES, -1));
                VibrationAttributes vibrationAttributesCreateForUsage = VibrationAttributes.createForUsage(18);
                VibratorManager vibratorManager = this.vibratorManager;
                if (vibratorManager != null) {
                    vibratorManager.vibrate(combinedVibrationCreateParallel, vibrationAttributesCreateForUsage);
                }
            }
            this.hasActiveVibration = true;
        }
    }
}
