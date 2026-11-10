package com.oplus.systemui.plugins.qs.customize.view.animation.threestage;

import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.olivelink.Spring;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageScaleSpringAnimationHelper {
    private static final String TAG = "ThreeStageScaleSpringAnimationHelper";
    private final AnimationType animationType;
    private final SpringConfig config;
    private final View iconView;
    private final View lottieView;
    private final Lazy springAnim$delegate;
    public static final Companion Companion = new Companion();
    private static final Lazy springConfig$delegate = LazyKt__LazyJVMKt.lazy(new ThreeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda0());

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimationType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimationType[] $VALUES;
        public static final AnimationType SCALE_X;

        /* JADX INFO: Fake field, exist only in values array */
        AnimationType EF0;

        static {
            AnimationType animationType = new AnimationType("TRANSLATION_X", 0);
            AnimationType animationType2 = new AnimationType("TRANSLATION_Y", 1);
            AnimationType animationType3 = new AnimationType("SCALE_X", 2);
            SCALE_X = animationType3;
            AnimationType[] animationTypeArr = {animationType, animationType2, animationType3, new AnimationType("SCALE_Y", 3)};
            $VALUES = animationTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animationTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimationType valueOf(String str) {
            return (AnimationType) Enum.valueOf(AnimationType.class, str);
        }

        public static AnimationType[] values() {
            return (AnimationType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder {
        public AnimationType animationType;
        private final SpringConfig config = new SpringConfig(200.0f, 0.5f);
        private final View iconView;
        private final View lottieView;

        public Builder(View view, View view2) {
            this.iconView = view;
            this.lottieView = view2;
        }

        public final ThreeStageScaleSpringAnimationHelper build() {
            View view = this.iconView;
            View view2 = this.lottieView;
            AnimationType animationType = this.animationType;
            if (animationType == null) {
                animationType = null;
            }
            return new ThreeStageScaleSpringAnimationHelper(view, view2, animationType, this.config);
        }

        public final Builder setAnimationType(AnimationType animationType) {
            this.animationType = animationType;
            return this;
        }

        public final Builder setDamping(float f) {
            this.config.setDampingRatio(f);
            return this;
        }

        public final Builder setStiffness(float f) {
            this.config.setStiffness(f);
            return this;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringConfig() {
            return (Spring) ThreeStageScaleSpringAnimationHelper.springConfig$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpringConfig {
        public float dampingRatio;
        public float stiffness;

        public SpringConfig(float f, float f2) {
            this.stiffness = f;
            this.dampingRatio = f2;
        }

        public final SpringConfig copy(float f, float f2) {
            return new SpringConfig(f, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpringConfig)) {
                return false;
            }
            SpringConfig springConfig = (SpringConfig) obj;
            return Float.compare(this.stiffness, springConfig.stiffness) == 0 && Float.compare(this.dampingRatio, springConfig.dampingRatio) == 0;
        }

        public final float getDampingRatio() {
            return this.dampingRatio;
        }

        public final float getStiffness() {
            return this.stiffness;
        }

        public final int hashCode() {
            return Float.hashCode(this.dampingRatio) + (Float.hashCode(this.stiffness) * 31);
        }

        public final void setDampingRatio(float f) {
            this.dampingRatio = f;
        }

        public final void setStiffness(float f) {
            this.stiffness = f;
        }

        public String toString() {
            return ListPopupWindow$.ExternalSyntheticOutline0.m("SpringConfig(stiffness=", this.stiffness, ", dampingRatio=", this.dampingRatio, ")");
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$Njfw1cbeswE-orvVgmnFQPPI-RY, reason: not valid java name */
    public static COUISpringAnimation m165$r8$lambda$Njfw1cbeswEorvVgmnFQPPIRY(ThreeStageScaleSpringAnimationHelper threeStageScaleSpringAnimationHelper) {
        COUISpringAnimation cOUISpringAnimation;
        int iOrdinal = threeStageScaleSpringAnimationHelper.animationType.ordinal();
        if (iOrdinal == 0) {
            cOUISpringAnimation = new COUISpringAnimation(COUIDynamicAnimation.TRANSLATION_X, threeStageScaleSpringAnimationHelper.iconView);
        } else if (iOrdinal == 1) {
            cOUISpringAnimation = new COUISpringAnimation(COUIDynamicAnimation.TRANSLATION_Y, threeStageScaleSpringAnimationHelper.iconView);
        } else if (iOrdinal == 2) {
            cOUISpringAnimation = new COUISpringAnimation(COUIDynamicAnimation.SCALE_X, threeStageScaleSpringAnimationHelper.iconView);
        } else {
            if (iOrdinal != 3) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
            }
            cOUISpringAnimation = new COUISpringAnimation(COUIDynamicAnimation.SCALE_Y, threeStageScaleSpringAnimationHelper.iconView);
        }
        COUISpringForce cOUISpringForce = new COUISpringForce();
        Companion companion = Companion;
        cOUISpringForce.setBounce(companion.getSpringConfig().bounce());
        cOUISpringForce.setResponse(companion.getSpringConfig().response());
        cOUISpringAnimation.setSpring(cOUISpringForce);
        ThreeStageScaleSpringAnimationHelper$springAnim$2$1$1 threeStageScaleSpringAnimationHelper$springAnim$2$1$1 = new ThreeStageScaleSpringAnimationHelper$springAnim$2$1$1();
        threeStageScaleSpringAnimationHelper$springAnim$2$1$1.this$0 = threeStageScaleSpringAnimationHelper;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(threeStageScaleSpringAnimationHelper$springAnim$2$1$1);
        return cOUISpringAnimation;
    }

    public ThreeStageScaleSpringAnimationHelper(View view, View view2, AnimationType animationType, SpringConfig springConfig) {
        this.iconView = view;
        this.lottieView = view2;
        this.animationType = animationType;
        this.config = springConfig;
        ThreeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda1 threeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda1 = new ThreeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda1();
        threeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnim$delegate = LazyKt__LazyJVMKt.lazy(threeStageScaleSpringAnimationHelper$$ExternalSyntheticLambda1);
    }

    private final COUISpringAnimation getSpringAnim() {
        return (COUISpringAnimation) this.springAnim$delegate.getValue();
    }

    public final void animateTo(float f) {
        COUISpringForce spring = getSpringAnim().getSpring();
        if (spring != null) {
            spring.setFinalPosition(f);
        }
        getSpringAnim().start();
    }

    public final void setScale(float f) {
        this.iconView.setScaleX(f);
        this.iconView.setScaleY(f);
        this.lottieView.setScaleX(f);
        this.lottieView.setScaleY(f);
    }

    public final void setStartValue(float f) {
        getSpringAnim().setStartValue(f);
    }
}
