package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.graphics.Point;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.volume.OplusSafetyWarningDialog;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SafetyWarningRepository {
    public static final Companion Companion = new Companion();
    public static final String TAG = "RingModeRepository";
    private final Context context;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mSafetyVolumeWakeLock;
    private OplusSafetyWarningDialog safetyWarning;
    private final Object safetyWarningLock = new Object();
    private final OplusVolumeDialogImpl volumeInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SafetyWarningRepository(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        Object systemService = context.getSystemService("power");
        this.mPowerManager = systemService instanceof PowerManager ? (PowerManager) systemService : null;
    }

    public final OplusSafetyWarningDialog getSafetyWarning() {
        return this.safetyWarning;
    }

    public final Object getSafetyWarningLock() {
        return this.safetyWarningLock;
    }

    public final void setSafetyWarning(OplusSafetyWarningDialog oplusSafetyWarningDialog) {
        this.safetyWarning = oplusSafetyWarningDialog;
    }

    public final void setSafetyWarningPadding() {
        AlertDialog safetyWarningDialog;
        Window window;
        View decorView;
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131173940);
        int navBarHeight = NavBarUtils.getNavBarHeight(this.context);
        int i = this.context.getResources().getConfiguration().orientation;
        if (i == 1) {
            dimensionPixelSize = navBarHeight;
        } else if (i != 2) {
            dimensionPixelSize = 0;
        }
        synchronized (this.safetyWarningLock) {
            OplusSafetyWarningDialog oplusSafetyWarningDialog = this.safetyWarning;
            if (oplusSafetyWarningDialog != null && (safetyWarningDialog = oplusSafetyWarningDialog.getSafetyWarningDialog()) != null && (window = safetyWarningDialog.getWindow()) != null && (decorView = window.getDecorView()) != null) {
                decorView.setPaddingRelative(0, 0, 0, dimensionPixelSize);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m(", orientation =", i, dimensionPixelSize, " , safetyWarningPaddingBottom = ", "RingModeRepository-->"));
        }
    }

    public final void showSafetyWarningH(int i) {
        AlertDialog safetyWarningDialog;
        Window window;
        AlertDialog safetyWarningDialog2;
        Window window2;
        AlertDialog safetyWarningDialog3;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "RingModeRepository-->showSafetyWarningH");
        }
        if ((i & 1025) != 0 || this.volumeInteractor.isShowing()) {
            synchronized (this.safetyWarningLock) {
                try {
                    if (this.safetyWarning != null) {
                        return;
                    }
                    this.safetyWarning = new showSafetyWarningH.2.1(this, this.context, this.volumeInteractor.mController.getAudioManager());
                    setSafetyWarningPadding();
                    OplusSafetyWarningDialog oplusSafetyWarningDialog = this.safetyWarning;
                    if (oplusSafetyWarningDialog != null && (safetyWarningDialog3 = oplusSafetyWarningDialog.getSafetyWarningDialog()) != null) {
                        safetyWarningDialog3.show();
                    }
                    Display display = this.context.getDisplay();
                    Point point = new Point();
                    display.getRealSize(point);
                    OplusSafetyWarningDialog oplusSafetyWarningDialog2 = this.safetyWarning;
                    WindowManager.LayoutParams attributes = (oplusSafetyWarningDialog2 == null || (safetyWarningDialog2 = oplusSafetyWarningDialog2.getSafetyWarningDialog()) == null || (window2 = safetyWarningDialog2.getWindow()) == null) ? null : window2.getAttributes();
                    if (attributes != null) {
                        attributes.width = (int) Math.min(point.x, point.y);
                    }
                    OplusSafetyWarningDialog oplusSafetyWarningDialog3 = this.safetyWarning;
                    if (oplusSafetyWarningDialog3 != null && (safetyWarningDialog = oplusSafetyWarningDialog3.getSafetyWarningDialog()) != null && (window = safetyWarningDialog.getWindow()) != null) {
                        window.setAttributes(attributes);
                    }
                    PowerManager powerManager = this.mPowerManager;
                    if (powerManager != null && !powerManager.isInteractive()) {
                        turnOnScreenAndVibrate();
                    }
                    this.volumeInteractor.recheckH(null);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.volumeInteractor.rescheduleTimeoutH();
    }

    public final void turnOnScreenAndVibrate() {
        PowerManager powerManager = this.mPowerManager;
        if (powerManager != null) {
            powerManager.wakeUp(SystemClock.uptimeMillis(), 2, "safetyVolume");
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "RingModeRepository-->turn on screen for safety warning ");
            }
        }
        FeatureOption.INSTANCE.getClass();
        boolean zIsLinearVibratorSupport = FeatureOption.isLinearVibratorSupport();
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.volumeInteractor;
        if (!zIsLinearVibratorSupport) {
            oplusVolumeDialogImpl.mController.vibrate(VibrationEffect.get(5));
            return;
        }
        oplusVolumeDialogImpl.mVibrateAbility.vibrateByLMVibrator(2, true);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "RingModeRepository-->turnOnScreenAndVibrate, vibrateByLMVibrator WaveformEffect=2");
        }
    }
}
