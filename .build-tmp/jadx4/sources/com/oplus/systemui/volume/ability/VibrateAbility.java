package com.oplus.systemui.volume.ability;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import androidx.compose.material3.internal.colorUtil.Cam$;
import com.android.systemui.plugins.VolumeDialogController;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.os.LinearmotorVibrator;
import com.oplus.os.WaveformEffect;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.HapticSettingObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VibrateAbility {
    public static final Companion Companion = new Companion();
    private static final String TAG = "VibrateAbility";
    private final Context context;
    private final Lazy linearmotorVibrator$delegate;
    private final WorkHandler worker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static int getVibratorStrengthWithLimit(int i, int i2, int i3, int i4) {
            int iM;
            double d = (((double) i) * 1.0d) / ((double) i2);
            if (d < 0.7d) {
                iM = (int) ((d * ((double) (i4 - i3)) * 1.1d) + ((double) i3));
            } else {
                double d2 = i4 - i3;
                iM = (int) Cam$.ExternalSyntheticOutline0.m(d2, 0.30000000000000004d, 0.7d, (d * d2 * 0.8d) + ((double) i3));
            }
            return (int) Math.max(i3, Math.min(iM, i4));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class WorkHandler extends OCDHandler {
        private Context context;

        public WorkHandler(Context context, OCDMessageQueue oCDMessageQueue) {
            super(oCDMessageQueue);
            this.context = context;
        }

        public final Context getContext() {
            return this.context;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 18) {
                new VibrateAbility(this.context).onVibrateByLMVibratorW(message.arg1, message.arg2 != 0);
            } else {
                if (i != 19) {
                    return;
                }
                new VibrateAbility(this.context).onVibrateByLMVibratorW(message.arg1, ((Integer) message.obj).intValue(), message.arg2 != 0);
            }
        }

        public final void setContext(Context context) {
            this.context = context;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.volume.ability.VibrateAbility$removeVibrate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ VibrateAbility this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    if (this.this$0.worker.hasMessages(18)) {
                        this.this$0.worker.removeMessages(18);
                    }
                    break;
                default:
                    if (this.this$0.worker.hasMessages(19)) {
                        this.this$0.worker.removeMessages(19);
                    }
                    break;
            }
        }
    }

    public static LinearmotorVibrator $r8$lambda$lOcUqlV65ZOXQWyJpPhN1Bv2nIg(VibrateAbility vibrateAbility) {
        Object systemService = vibrateAbility.context.getSystemService("linearmotor");
        if (systemService instanceof LinearmotorVibrator) {
            return (LinearmotorVibrator) systemService;
        }
        return null;
    }

    public VibrateAbility(Context context) {
        this.context = context;
        this.worker = new WorkHandler(context, SystemUIThreadUtil.getWorkLooper());
        VibrateAbility$$ExternalSyntheticLambda0 vibrateAbility$$ExternalSyntheticLambda0 = new VibrateAbility$$ExternalSyntheticLambda0();
        vibrateAbility$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.linearmotorVibrator$delegate = LazyKt__LazyJVMKt.lazy(vibrateAbility$$ExternalSyntheticLambda0);
    }

    private final LinearmotorVibrator getLinearmotorVibrator() {
        return (LinearmotorVibrator) this.linearmotorVibrator$delegate.getValue();
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isVibrateWhenRingingOn(VolumeDialogController.State state) {
        return state.ringingModeState != 0;
    }

    public final void onVibrateByLMVibratorW(int i, int i2, boolean z) {
        if (getLinearmotorVibrator() == null || !HapticSettingObserver.getInstance().mSystemHapticEnabled) {
            return;
        }
        if (i <= 154 && i >= 152) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isXLinearVibratorSupport()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", "VibrateAbility-->This project doesn't have a x_linear vibrator.");
                    return;
                }
                return;
            }
        }
        WaveformEffect waveformEffectBuild = new WaveformEffect.Builder().setStrengthSettingEnabled(false).setEffectStrength(i2).setEffectType(i).setAsynchronous(z).build();
        LinearmotorVibrator linearmotorVibrator = getLinearmotorVibrator();
        if (linearmotorVibrator != null) {
            linearmotorVibrator.vibrate(waveformEffectBuild);
        }
    }

    public final void removeVibrate() {
        WorkHandler workHandler = this.worker;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        workHandler.postDelayed(anonymousClass1, 50L);
        WorkHandler workHandler2 = this.worker;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        workHandler2.postDelayed(anonymousClass2, 50L);
    }

    public final void vibrateByLMVibrator(int i, int i2, boolean z) {
        this.worker.obtainMessage(19, i, z ? 1 : 0, Integer.valueOf(i2)).sendToTarget();
    }

    public final void vibrateByLMVibrator(int i, boolean z) {
        this.worker.obtainMessage(18, i, z ? 1 : 0).sendToTarget();
    }

    public final void onVibrateByLMVibratorW(int i, boolean z) {
        if (getLinearmotorVibrator() == null || !HapticSettingObserver.getInstance().mSystemHapticEnabled) {
            return;
        }
        if (i <= 154 && i >= 152) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isXLinearVibratorSupport()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", "VibrateAbility-->This project doesn't have a x_linear vibrator.");
                    return;
                }
                return;
            }
        }
        WaveformEffect waveformEffectBuild = new WaveformEffect.Builder().setAsynchronous(z).setEffectType(i).build();
        LinearmotorVibrator linearmotorVibrator = getLinearmotorVibrator();
        if (linearmotorVibrator != null) {
            linearmotorVibrator.vibrate(waveformEffectBuild);
        }
    }
}
