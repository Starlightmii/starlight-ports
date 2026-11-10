package com.oplus.systemui.plugins.qs.util;

import android.content.Context;
import android.os.Vibrator;
import android.os.VibratorManager;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VibratorHelper {
    public static final Companion Companion = new Companion();
    private static volatile VibratorHelper instance;
    private final Lazy vibrator$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final VibratorHelper getInstance(Context context) {
            VibratorHelper vibratorHelper;
            VibratorHelper vibratorHelper2 = VibratorHelper.instance;
            if (vibratorHelper2 != null) {
                return vibratorHelper2;
            }
            synchronized (this) {
                vibratorHelper = VibratorHelper.instance;
                if (vibratorHelper == null) {
                    vibratorHelper = new VibratorHelper(context.getApplicationContext());
                    VibratorHelper.instance = vibratorHelper;
                }
            }
            return vibratorHelper;
        }
    }

    public VibratorHelper(Context context) {
        VibratorHelper$$ExternalSyntheticLambda0 vibratorHelper$$ExternalSyntheticLambda0 = new VibratorHelper$$ExternalSyntheticLambda0();
        vibratorHelper$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.vibrator$delegate = LazyKt__LazyJVMKt.lazy(vibratorHelper$$ExternalSyntheticLambda0);
    }

    private final Object getVibrator() {
        return this.vibrator$delegate.getValue();
    }

    public final boolean hasVibrator() {
        Object vibrator = getVibrator();
        if (vibrator == null) {
            return false;
        }
        try {
            if (vibrator instanceof VibratorManager) {
                return ((VibratorManager) vibrator).getDefaultVibrator().hasVibrator();
            }
            if (vibrator instanceof Vibrator) {
                return ((Vibrator) vibrator).hasVibrator();
            }
            return false;
        } catch (SecurityException unused) {
        }
    }
}
