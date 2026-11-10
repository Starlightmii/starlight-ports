package com.oplus.vfxsdk.common;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class COEParseKt {
    private static final float[] DefaultSpring = {0.0f, 0.6f, 0.0f};
    private static final float[] DefaultBezier = {0.3f, 0.0f, 0.1f, 1.0f};

    public static final void correctBezier(float[] fArr) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                float f = fArr[i];
                if (f < 0.0f) {
                    f = 0.0f;
                }
                fArr[i] = f;
            }
        }
    }

    public static final float[] getDefaultBezier() {
        return DefaultBezier;
    }

    public static final float[] getDefaultSpring() {
        return DefaultSpring;
    }
}
