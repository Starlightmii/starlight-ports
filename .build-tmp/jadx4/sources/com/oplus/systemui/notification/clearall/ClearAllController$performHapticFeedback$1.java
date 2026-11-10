package com.oplus.systemui.notification.clearall;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.os.WaveformEffect;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClearAllController$performHapticFeedback$1 implements Runnable {
    public /* synthetic */ ClearAllController this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        FeatureOption.INSTANCE.getClass();
        boolean zIsLinearVibratorSupport = FeatureOption.isLinearVibratorSupport();
        ClearAllController clearAllController = this.this$0;
        if (!zIsLinearVibratorSupport) {
            if (clearAllController.vibrator == null) {
                ClearAllController clearAllController2 = this.this$0;
                NotificationStackScrollLayout notificationStackScrollLayout = clearAllController2.stackScroller;
                Object systemService = (notificationStackScrollLayout == null || (context = notificationStackScrollLayout.getContext()) == null) ? null : context.getSystemService("vibrator");
                clearAllController2.vibrator = systemService instanceof Vibrator ? (Vibrator) systemService : null;
            }
            if (this.this$0.vibrator != null) {
                Vibrator vibrator = this.this$0.vibrator;
                vibrator.getClass();
                if (vibrator.hasVibrator()) {
                    Vibrator vibrator2 = this.this$0.vibrator;
                    vibrator2.getClass();
                    vibrator2.vibrate(this.this$0.clickPattern, -1);
                    return;
                }
                return;
            }
            return;
        }
        if (clearAllController.linearmotorVibrator == null) {
            ClearAllController clearAllController3 = this.this$0;
            NotificationStackScrollLayout notificationStackScrollLayout2 = clearAllController3.stackScroller;
            Object systemService2 = (notificationStackScrollLayout2 == null || (context2 = notificationStackScrollLayout2.getContext()) == null) ? null : context2.getSystemService("linearmotor");
            clearAllController3.linearmotorVibrator = systemService2 instanceof LinearmotorVibrator ? (LinearmotorVibrator) systemService2 : null;
        }
        if (this.this$0.linearmotorVibrator != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ClearAllController-->".concat("in colorOS 16, all isLuxunVibratorSupport, effectType: 471"));
            }
            WaveformEffect waveformEffectBuild = new WaveformEffect.Builder().setEffectType(471).build();
            LinearmotorVibrator linearmotorVibrator = this.this$0.linearmotorVibrator;
            if (linearmotorVibrator != null) {
                linearmotorVibrator.vibrate(waveformEffectBuild);
            }
        }
    }
}
