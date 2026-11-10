package com.oplus.systemui.plugins.qs.animator;

import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorUtils {
    public static final AnimatorUtils INSTANCE = new AnimatorUtils();
    public static float extraFakeTranslationY;

    public static final float responseToStiffness(float f) {
        return (float) Math.pow(6.283185307179586d / (f == 0.0f ? 1.0d : f), 2.0d);
    }

    public static final void tryUpdateStiffness(COUISpringAnimation cOUISpringAnimation, float f) {
        COUISpringForce spring = cOUISpringAnimation.getSpring();
        if (spring == null || spring.getStiffness() == f) {
            return;
        }
        spring.setStiffness(f);
    }

    public final float getExtraFakeTranslationY() {
        return extraFakeTranslationY;
    }

    public final void setExtraFakeTranslationY(float f) {
        extraFakeTranslationY = f;
    }
}
