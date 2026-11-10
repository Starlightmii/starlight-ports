package com.oplus.vfxsdk.common;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbsAnimatorKt {
    public static final COUISpringAnimation createSpringAnimation(float f, float f2, float f3) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation((Float) null);
        COUISpringForce cOUISpringForce = new COUISpringForce(100.0f);
        cOUISpringForce.setBounce(f);
        cOUISpringForce.setResponse(f2);
        cOUISpringAnimation.setStartVelocity(f3);
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.start();
        return cOUISpringAnimation;
    }
}
