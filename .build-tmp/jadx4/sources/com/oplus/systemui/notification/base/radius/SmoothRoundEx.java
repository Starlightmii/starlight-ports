package com.oplus.systemui.notification.base.radius;

import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.CornerType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmoothRoundEx {
    public static final SmoothRoundEx INSTANCE = new SmoothRoundEx();
    private static final Lazy DEVICE_SUPPORT_SMOOTH$delegate = LazyKt__LazyJVMKt.lazy(new SmoothRoundEx$$ExternalSyntheticLambda0());
    public static final float defaultWeight = 1.1f;

    public final CornerParams createCapsuleCornerParams(float f) {
        return new CornerParams(CornerType.G2, f, 2.0f);
    }

    public final CornerParams createCornerParams(float f, boolean z) {
        return !z ? new CornerParams(CornerType.FULL, f, 1.0f) : new CornerParams(CornerType.CONIC, f, 1.1f);
    }

    public final CornerParams createDecoratorCornerParams(float f) {
        return createCapsuleCornerParams(f);
    }

    public final CornerParams createMetaBallsCornerParams(float f) {
        return new CornerParams(CornerType.CONIC, f, 1.1f);
    }

    public final boolean getDEVICE_SUPPORT_SMOOTH() {
        return ((Boolean) DEVICE_SUPPORT_SMOOTH$delegate.getValue()).booleanValue();
    }

    public final float getDefaultWeight() {
        return defaultWeight;
    }
}
