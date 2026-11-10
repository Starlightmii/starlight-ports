package com.oplus.systemui.notification.stackednotification.transformer;

import android.util.Property;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StackedProgressSpringAnimator {
    public static final Companion Companion = new Companion();
    public static final Property STACKED_PROGRESS_ANIMATION_PROPERTY = View.ALPHA;
    public final int animatorTag;
    private final WeakHashMap<View, Boolean> couiGlobalEndListenerRegistered = new WeakHashMap<>();
    private final Function2 onProgressUpdated;
    public final float progressMinimumVisibleChange;
    public final float springBounce;
    public final float springResponse;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StackedProgressSpringAnimator(int i, float f, float f2, float f3, Function2 function2) {
        this.animatorTag = i;
        this.progressMinimumVisibleChange = f;
        this.springResponse = f2;
        this.springBounce = f3;
        this.onProgressUpdated = function2;
    }

    public final void animateTo(View view, float f, float f2, AnimationProperties animationProperties) {
        Consumer couiAnimationStartListener;
        if (f == f2) {
            cancel(view);
            return;
        }
        int i = this.animatorTag;
        Object tag = view.getTag(i);
        COUISpringAnimation cOUISpringAnimation = tag instanceof COUISpringAnimation ? (COUISpringAnimation) tag : null;
        if (cOUISpringAnimation == null) {
            cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
            cOUISpringAnimation.setMinimumVisibleChange(this.progressMinimumVisibleChange);
            cOUISpringAnimation.setSpring(new COUISpringForce().setResponse(this.springResponse).setBounce(this.springBounce));
            StackedProgressSpringAnimator$getOrCreateAnimator$2$1 stackedProgressSpringAnimator$getOrCreateAnimator$2$1 = new StackedProgressSpringAnimator$getOrCreateAnimator$2$1();
            stackedProgressSpringAnimator$getOrCreateAnimator$2$1.this$0 = this;
            stackedProgressSpringAnimator$getOrCreateAnimator$2$1.$view = view;
            VarHandle.storeStoreFence();
            cOUISpringAnimation.addUpdateListener(stackedProgressSpringAnimator$getOrCreateAnimator$2$1);
            view.setTag(i, cOUISpringAnimation);
        }
        if (animationProperties != null) {
            Boolean bool = this.couiGlobalEndListenerRegistered.get(view);
            Boolean bool2 = Boolean.TRUE;
            if (!Intrinsics.areEqual(bool, bool2)) {
                COUIDynamicAnimation.OnAnimationEndListener couiAnimationEndListener = animationProperties.getCouiAnimationEndListener(STACKED_PROGRESS_ANIMATION_PROPERTY);
                if (couiAnimationEndListener != null) {
                    cOUISpringAnimation.addEndListener(couiAnimationEndListener);
                }
                this.couiGlobalEndListenerRegistered.put(view, bool2);
            }
        }
        if (!((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
            cOUISpringAnimation.setStartValue(f);
            if (animationProperties != null && (couiAnimationStartListener = animationProperties.getCouiAnimationStartListener()) != null) {
                couiAnimationStartListener.accept(cOUISpringAnimation);
            }
        }
        cOUISpringAnimation.animateToFinalPosition(f2);
    }

    public final void cancel(View view) {
        Object tag = view.getTag(this.animatorTag);
        COUISpringAnimation cOUISpringAnimation = tag instanceof COUISpringAnimation ? (COUISpringAnimation) tag : null;
        if (cOUISpringAnimation != null) {
            COUISpringAnimation cOUISpringAnimation2 = ((COUIDynamicAnimation) cOUISpringAnimation).mRunning ? cOUISpringAnimation : null;
            if (cOUISpringAnimation2 != null) {
                cOUISpringAnimation2.cancel();
            }
        }
    }

    public final void cancelAndSet(View view, float f) {
        cancel(view);
        this.onProgressUpdated.invoke(view, Float.valueOf(f));
    }

    public final boolean isAnimating(View view) {
        Object tag = view.getTag(this.animatorTag);
        COUISpringAnimation cOUISpringAnimation = tag instanceof COUISpringAnimation ? (COUISpringAnimation) tag : null;
        return cOUISpringAnimation != null && ((COUIDynamicAnimation) cOUISpringAnimation).mRunning;
    }
}
