package com.oplus.systemui.statusbar.interpolator;

import android.view.animation.Interpolator;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViscousFluidInterpolator implements Interpolator {
    public static final Companion Companion;
    public static final float VISCOUS_FLUID_NORMALIZE;
    public static final float VISCOUS_FLUID_OFFSET;
    private static final Lazy instance$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final float access$viscousFluid(Companion companion, float f) {
            companion.getClass();
            float f2 = f * 8.0f;
            return f2 < 1.0f ? f2 - (1.0f - ((float) Math.exp(-f2))) : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(1.0f, (float) Math.exp(1.0f - f2), 0.63212055f, 0.36787945f);
        }

        public final ViscousFluidInterpolator getInstance() {
            return (ViscousFluidInterpolator) ViscousFluidInterpolator.instance$delegate.getValue();
        }
    }

    static {
        Companion companion = new Companion();
        Companion = companion;
        float fAccess$viscousFluid = 1.0f / Companion.access$viscousFluid(companion, 1.0f);
        VISCOUS_FLUID_NORMALIZE = fAccess$viscousFluid;
        VISCOUS_FLUID_OFFSET = 1.0f - (Companion.access$viscousFluid(companion, 1.0f) * fAccess$viscousFluid);
        instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new ViscousFluidInterpolator$$ExternalSyntheticLambda0());
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        float fAccess$viscousFluid = Companion.access$viscousFluid(Companion, f) * VISCOUS_FLUID_NORMALIZE;
        return fAccess$viscousFluid > 0.0f ? fAccess$viscousFluid + VISCOUS_FLUID_OFFSET : fAccess$viscousFluid;
    }
}
