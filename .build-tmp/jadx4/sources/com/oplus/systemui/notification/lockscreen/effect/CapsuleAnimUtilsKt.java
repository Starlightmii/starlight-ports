package com.oplus.systemui.notification.lockscreen.effect;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.View;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.olivelink.Spring;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleAnimUtilsKt {

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.effect.CapsuleAnimUtilsKt$addUpdater$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Function1 $updater;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
            switch (this.$r8$classId) {
                case 0:
                    this.$updater.invoke(Float.valueOf(f));
                    break;
                default:
                    Function1 function1 = this.$updater;
                    if (function1 != null) {
                        function1.invoke(Float.valueOf(f));
                    }
                    break;
            }
        }
    }

    public static final void addUpdater(COUISpringAnimation cOUISpringAnimation, Function1 function1) {
        if (cOUISpringAnimation == null) {
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.$updater = function1;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(anonymousClass1);
    }

    public static final void applyBlurToView(View view, float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f <= 0.0f) {
            view.setRenderEffect(null);
        } else {
            view.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        }
    }

    public static final COUISpringAnimation floatAnim(Spring spring, float f, float f2, float f3, Float f4, Function1 function1) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setResponse(spring.response()).setBounce(spring.bounce()).setFinalPosition(f2));
        cOUISpringAnimation.setStartValue(f);
        cOUISpringAnimation.setStartVelocity(f4 != null ? f4.floatValue() : spring.velocity());
        cOUISpringAnimation.setMinimumVisibleChange(f3);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.$updater = function1;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(anonymousClass1);
        return cOUISpringAnimation;
    }

    public static /* synthetic */ COUISpringAnimation floatAnim$default(Spring spring, float f, float f2, float f3, Float f4, CapsuleAnimUtilsKt$.ExternalSyntheticLambda0 externalSyntheticLambda0, int i) {
        if ((i & 4) != 0) {
            f3 = 1.0f;
        }
        return floatAnim(spring, f, f2, f3, (i & 8) != 0 ? null : f4, (i & 16) != 0 ? null : externalSyntheticLambda0);
    }

    public static final COUISpringAnimation viewAlphaAnim(Spring spring, View view, float f, float f2, float f3) {
        return viewAnim(spring, view, COUIDynamicAnimation.ALPHA, f, f2, f3);
    }

    public static final COUISpringAnimation viewAnim(Spring spring, View view, FloatPropertyCompat floatPropertyCompat, float f, float f2, float f3) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(view, floatPropertyCompat, f2);
        cOUISpringAnimation.getSpring().setResponse(spring.response()).setBounce(spring.bounce());
        cOUISpringAnimation.setStartValue(f);
        cOUISpringAnimation.setStartVelocity(spring.velocity());
        cOUISpringAnimation.setMinimumVisibleChange(f3);
        return cOUISpringAnimation;
    }

    public static final COUISpringAnimation viewBlurAnim(Spring spring, View view, float f, float f2, float f3) {
        CapsuleAnimUtilsKt$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new CapsuleAnimUtilsKt$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = view;
        VarHandle.storeStoreFence();
        return floatAnim$default(spring, f, f2, f3, null, externalSyntheticLambda0, 8);
    }

    public static final COUISpringAnimation viewScaleXAnim(Spring spring, View view, float f, float f2, float f3) {
        return viewAnim(spring, view, COUIDynamicAnimation.SCALE_X, f, f2, f3);
    }

    public static final COUISpringAnimation viewScaleYAnim(Spring spring, View view, float f, float f2, float f3) {
        return viewAnim(spring, view, COUIDynamicAnimation.SCALE_Y, f, f2, f3);
    }

    public static final COUISpringAnimation viewTransYAnim(Spring spring, View view, float f, float f2, float f3) {
        return viewAnim(spring, view, COUIDynamicAnimation.TRANSLATION_Y, f, f2, f3);
    }
}
