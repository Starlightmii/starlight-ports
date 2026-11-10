package com.oplus.systemui.plugins.qs.animator;

import android.util.Log;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BottomIndicatorAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BottomIndicatorAnimator";
    private AnimationMode animationMode = AnimationMode.NONE;
    private final Lazy fadeInSpringAnimation$delegate;
    private final Lazy fadeOutSpringAnimation$delegate;
    private final View indicator;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnimationMode {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimationMode[] $VALUES;
        public static final AnimationMode COMBINATION;
        public static final AnimationMode NONE;
        public static final AnimationMode REVERSING;

        static {
            AnimationMode animationMode = new AnimationMode("NONE", 0);
            NONE = animationMode;
            AnimationMode animationMode2 = new AnimationMode("COMBINATION", 1);
            COMBINATION = animationMode2;
            AnimationMode animationMode3 = new AnimationMode("REVERSING", 2);
            REVERSING = animationMode3;
            AnimationMode[] animationModeArr = {animationMode, animationMode2, animationMode3};
            $VALUES = animationModeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animationModeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimationMode valueOf(String str) {
            return (AnimationMode) Enum.valueOf(AnimationMode.class, str);
        }

        public static AnimationMode[] values() {
            return (AnimationMode[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnimationMode.values().length];
            try {
                AnimationMode animationMode = AnimationMode.NONE;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BottomIndicatorAnimator(View view) {
        this.indicator = view;
        BottomIndicatorAnimator$$ExternalSyntheticLambda0 bottomIndicatorAnimator$$ExternalSyntheticLambda0 = new BottomIndicatorAnimator$$ExternalSyntheticLambda0(0);
        bottomIndicatorAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.fadeInSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(bottomIndicatorAnimator$$ExternalSyntheticLambda0);
        BottomIndicatorAnimator$$ExternalSyntheticLambda0 bottomIndicatorAnimator$$ExternalSyntheticLambda1 = new BottomIndicatorAnimator$$ExternalSyntheticLambda0(1);
        bottomIndicatorAnimator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.fadeOutSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(bottomIndicatorAnimator$$ExternalSyntheticLambda1);
    }

    public static final void access$handleFadeInEnd(BottomIndicatorAnimator bottomIndicatorAnimator, float f) {
        if (WhenMappings.$EnumSwitchMapping$0[bottomIndicatorAnimator.animationMode.ordinal()] != 1) {
            bottomIndicatorAnimator.animationMode = AnimationMode.NONE;
            bottomIndicatorAnimator.indicator.setTransitionAlpha(1.0f);
        } else {
            if (f != 0.0f) {
                bottomIndicatorAnimator.indicator.setTransitionAlpha(1.0f);
                return;
            }
            bottomIndicatorAnimator.animationMode = AnimationMode.NONE;
            bottomIndicatorAnimator.getFadeInSpringAnimation().setStartValue(0.0f);
            bottomIndicatorAnimator.getFadeInSpringAnimation().animateToFinalPosition(1.0f);
        }
    }

    public static final void access$handleFadeOutEnd(BottomIndicatorAnimator bottomIndicatorAnimator, float f) {
        if (bottomIndicatorAnimator.animationMode == AnimationMode.COMBINATION && f == 0.0f) {
            bottomIndicatorAnimator.getFadeInSpringAnimation().setStartValue(0.0f);
            bottomIndicatorAnimator.getFadeInSpringAnimation().animateToFinalPosition(1.0f);
        } else {
            bottomIndicatorAnimator.indicator.setTransitionAlpha(1.0f);
        }
        bottomIndicatorAnimator.animationMode = AnimationMode.NONE;
    }

    private final COUISpringAnimation getFadeInSpringAnimation() {
        return (COUISpringAnimation) this.fadeInSpringAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getFadeOutSpringAnimation() {
        return (COUISpringAnimation) this.fadeOutSpringAnimation$delegate.getValue();
    }

    public final COUISpringAnimation createSpringAnimation(String str) {
        Spring springSafeSpring$default = OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, str, 0.0f, str.equals("indicatorFadeOut") ? 0.1f : 0.25f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(springSafeSpring$default.bounce());
        cOUISpringForce.setResponse(springSafeSpring$default.response());
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
        BottomIndicatorAnimator$createSpringAnimation$1$2 bottomIndicatorAnimator$createSpringAnimation$1$2 = new BottomIndicatorAnimator$createSpringAnimation$1$2();
        bottomIndicatorAnimator$createSpringAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(bottomIndicatorAnimator$createSpringAnimation$1$2);
        if (str.equals("indicatorFadeIn")) {
            BottomIndicatorAnimator$createSpringAnimation$1$3 bottomIndicatorAnimator$createSpringAnimation$1$3 = new BottomIndicatorAnimator$createSpringAnimation$1$3(0);
            bottomIndicatorAnimator$createSpringAnimation$1$3.this$0 = this;
            VarHandle.storeStoreFence();
            cOUISpringAnimation.addEndListener(bottomIndicatorAnimator$createSpringAnimation$1$3);
            return cOUISpringAnimation;
        }
        BottomIndicatorAnimator$createSpringAnimation$1$3 bottomIndicatorAnimator$createSpringAnimation$1$4 = new BottomIndicatorAnimator$createSpringAnimation$1$3(1);
        bottomIndicatorAnimator$createSpringAnimation$1$4.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(bottomIndicatorAnimator$createSpringAnimation$1$4);
        return cOUISpringAnimation;
    }

    public final void playAnimation(float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("playAnimation: startValue=", f, ", endValue=", f2, "BottomIndicatorAnimator-->"));
        }
        this.animationMode = AnimationMode.NONE;
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        if (f > 0.0f && f2 > 0.0f) {
            if (!((COUIDynamicAnimation) getFadeInSpringAnimation()).mRunning && !((COUIDynamicAnimation) getFadeOutSpringAnimation()).mRunning) {
                this.animationMode = AnimationMode.COMBINATION;
                getFadeOutSpringAnimation().setStartValue(1.0f);
                getFadeOutSpringAnimation().animateToFinalPosition(0.0f);
                return;
            } else if (((COUIDynamicAnimation) getFadeOutSpringAnimation()).mRunning) {
                getFadeOutSpringAnimation().animateToFinalPosition(1.0f);
                return;
            } else {
                if (((COUIDynamicAnimation) getFadeInSpringAnimation()).mRunning) {
                    this.animationMode = AnimationMode.REVERSING;
                    getFadeInSpringAnimation().animateToFinalPosition(0.0f);
                    return;
                }
                return;
            }
        }
        if (f == 0.0f && f2 > 0.0f) {
            if (((COUIDynamicAnimation) getFadeOutSpringAnimation()).mRunning) {
                getFadeOutSpringAnimation().animateToFinalPosition(1.0f);
                return;
            } else {
                getFadeInSpringAnimation().setStartValue(0.0f);
                getFadeInSpringAnimation().animateToFinalPosition(1.0f);
                return;
            }
        }
        if (f <= 0.0f || f2 != 0.0f) {
            return;
        }
        if (((COUIDynamicAnimation) getFadeInSpringAnimation()).mRunning) {
            getFadeInSpringAnimation().animateToFinalPosition(0.0f);
        } else {
            getFadeOutSpringAnimation().setStartValue(1.0f);
            getFadeOutSpringAnimation().animateToFinalPosition(0.0f);
        }
    }
}
