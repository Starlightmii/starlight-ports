package com.oplusos.systemui.common.sample;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DisplacementThresholdSampler {
    private final Function1 accept;
    private final Function0 displacementThresholdPx;
    public float lastNotifiedDisplacementInternal;
    public float previousRawDisplacement;
    public float sameDirection;
    private final Function0 shouldDownsample;

    public DisplacementThresholdSampler(Function0 function0, Function0 function1, Function1 function2) {
        this.displacementThresholdPx = function0;
        this.shouldDownsample = function1;
        this.accept = function2;
    }

    public static void flush$default(DisplacementThresholdSampler displacementThresholdSampler, float f, int i) {
        if ((i & 1) != 0) {
            f = displacementThresholdSampler.previousRawDisplacement;
        }
        boolean z = (i & 4) == 0;
        if (f == displacementThresholdSampler.lastNotifiedDisplacementInternal) {
            displacementThresholdSampler.previousRawDisplacement = f;
        } else {
            displacementThresholdSampler.lastNotifiedDisplacementInternal = f;
            displacementThresholdSampler.previousRawDisplacement = f;
            displacementThresholdSampler.accept.invoke(Float.valueOf(f));
        }
        if (!z) {
            displacementThresholdSampler.getClass();
            return;
        }
        displacementThresholdSampler.lastNotifiedDisplacementInternal = 0.0f;
        displacementThresholdSampler.previousRawDisplacement = 0.0f;
        displacementThresholdSampler.sameDirection = 0.0f;
    }

    public final float getCurrentRawDisplacement() {
        return this.previousRawDisplacement;
    }

    public final float getLastNotifiedDisplacement() {
        return this.lastNotifiedDisplacementInternal;
    }

    public final void sample(float f) {
        boolean zBooleanValue = ((Boolean) this.shouldDownsample.invoke()).booleanValue();
        float f2 = this.previousRawDisplacement;
        if (!zBooleanValue) {
            float f3 = f - f2;
            if (f3 != 0.0f) {
                float f4 = this.sameDirection;
                if (f4 != 0.0f) {
                    f3 += f4;
                }
                this.sameDirection = f3;
            }
            flush$default(this, f, 6);
            return;
        }
        float f5 = f - f2;
        float f6 = this.sameDirection;
        if (f6 != 0.0f && f5 != 0.0f && f6 * f5 < 0.0f) {
            this.sameDirection = f5;
            flush$default(this, f, 6);
            return;
        }
        if (f5 != 0.0f) {
            if (f6 != 0.0f) {
                f5 += f6;
            }
            this.sameDirection = f5;
        }
        if (Math.abs(f - this.lastNotifiedDisplacementInternal) >= ((Number) this.displacementThresholdPx.invoke()).floatValue()) {
            flush$default(this, f, 6);
        } else {
            this.previousRawDisplacement = f;
        }
    }
}
