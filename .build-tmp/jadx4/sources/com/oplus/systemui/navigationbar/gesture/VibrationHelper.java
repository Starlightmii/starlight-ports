package com.oplus.systemui.navigationbar.gesture;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.os.WaveformEffect;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VibrationHelper {
    public static final long[] PATTERN_KEYBOARD_TOUCH = {0, 65};
    public static VibrationHelper sInstance = null;
    public Context mContext;
    public Vibrator mVibrator;

    public static synchronized VibrationHelper getInstance(Context context) {
        try {
            if (sInstance == null) {
                VibrationHelper vibrationHelper = new VibrationHelper();
                vibrationHelper.mVibrator = (Vibrator) context.getSystemService("vibrator");
                vibrationHelper.mContext = context.getApplicationContext();
                sInstance = vibrationHelper;
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }

    public final void doVibrateCustomized(Context context, int i, boolean z) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "doVibrateCustomized type=", LogUtil.TAG_NAVBAR, "VibrationHelper");
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), CommonSettingsValueProxy.KEY_HAPTIC_FEEDBACK, 0, -2) == 0 && !z) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "VibrationHelper", "vibrateCustomized hapticsDisabled!");
            return;
        }
        if (!this.mVibrator.hasVibrator()) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "VibrationHelper", "vibrateForGesture no vibrator!");
            return;
        }
        if (context == null) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "VibrationHelper", "vibrateByLinearmotorVibrator: context is null");
        } else {
            try {
                LinearmotorVibrator linearmotorVibrator = (LinearmotorVibrator) context.getSystemService("linearmotor");
                if (linearmotorVibrator != null) {
                    linearmotorVibrator.vibrate(new WaveformEffect.Builder().setEffectType(i).build());
                    return;
                }
            } catch (Exception e) {
                CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("vibrateByLinearmotorVibrator e:"), LogUtil.TAG_NAVBAR, "VibrationHelper");
            }
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isLinearVibratorSupport() || VibrationEffect.createOneShot(50L, 175) == null) {
            this.mVibrator.vibrate(350L);
        }
    }

    public final void onPerformHapticFeedback() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isLinearVibratorSupport()) {
            vibrateCustomized(this.mContext, 1, false);
        } else {
            performKeyboardTouchFeedback();
        }
    }

    public final void performKeyboardTouchFeedback() {
        LogUtil.d(LogUtil.TAG_NAVBAR, "VibrationHelper", "performKeyboardTouchFeedback: ");
        if (this.mVibrator.hasVibrator() && Settings.System.getIntForUser(this.mContext.getContentResolver(), CommonSettingsValueProxy.KEY_HAPTIC_FEEDBACK, 0, -2) != 0) {
            FeatureOption featureOption = FeatureOption.INSTANCE;
            featureOption.getClass();
            if (!FeatureOption.isLinearVibratorSupport()) {
                this.mVibrator.vibrate(PATTERN_KEYBOARD_TOUCH, -1);
                return;
            }
            featureOption.getClass();
            if (FeatureOption.isLinearVibratorSupport()) {
                VibrationEffect.createOneShot(50L, 175);
            }
        }
    }

    public final void vibrateCustomized(Context context, int i, boolean z) {
        LogUtil.d(LogUtil.TAG_NAVBAR, "VibrationHelper", KeyguardLogger$.ExternalSyntheticOutline0.m("vibrateCustomized type=", i, ",callers=", LogUtil.panicEnabled ? Debug.getCallers(3) : "***"));
        VibrationHelper$$ExternalSyntheticLambda0 vibrationHelper$$ExternalSyntheticLambda0 = new VibrationHelper$$ExternalSyntheticLambda0();
        vibrationHelper$$ExternalSyntheticLambda0.f$0 = this;
        vibrationHelper$$ExternalSyntheticLambda0.f$1 = context;
        vibrationHelper$$ExternalSyntheticLambda0.f$2 = i;
        vibrationHelper$$ExternalSyntheticLambda0.f$3 = z;
        VarHandle.storeStoreFence();
        AsyncTask.execute(vibrationHelper$$ExternalSyntheticLambda0);
    }
}
