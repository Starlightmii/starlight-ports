package com.oplus.systemui.panelanimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusPanelAnimationExImplKt {
    public static final float responseToStiffness(float f) {
        return (float) Math.pow(6.283185307179586d / (f == 0.0f ? 1.0d : f), 2.0d);
    }
}
