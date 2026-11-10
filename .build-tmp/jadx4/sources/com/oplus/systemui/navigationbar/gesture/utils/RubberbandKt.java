package com.oplus.systemui.navigationbar.gesture.utils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RubberbandKt {
    public static final float calculateRubberBand(float f, float f2, int i) {
        float fAbs = Math.abs(f2) * f;
        float f3 = i;
        return Math.signum(f2) * (1.0f - (1.0f / ((fAbs / f3) + 1.0f))) * f3;
    }
}
