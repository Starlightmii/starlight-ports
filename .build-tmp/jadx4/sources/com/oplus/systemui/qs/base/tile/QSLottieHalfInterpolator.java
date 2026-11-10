package com.oplus.systemui.qs.base.tile;

import android.view.animation.AccelerateDecelerateInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSLottieHalfInterpolator extends AccelerateDecelerateInterpolator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSLottieHalfInterpolator";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // android.view.animation.AccelerateDecelerateInterpolator, android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return f <= 0.5f ? super.getInterpolation(f * 2.0f) : super.getInterpolation(1.0f);
    }
}
