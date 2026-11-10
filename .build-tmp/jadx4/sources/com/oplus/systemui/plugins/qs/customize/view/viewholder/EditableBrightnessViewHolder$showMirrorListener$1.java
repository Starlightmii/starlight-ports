package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.systemui.plugins.qs.QSBrightness;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.util.AnimatableProperty;
import com.oplus.systemui.plugins.qs.util.AnimationProperties;
import com.oplus.systemui.plugins.qs.util.Interpolators;
import com.oplus.systemui.plugins.qs.util.PropertyAnimator$;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder$showMirrorListener$1 implements QSBrightness.ShowMirrorListener {
    public /* synthetic */ EditableBrightnessViewHolder this$0;

    public final void onShowMirror(int i, boolean z, Function0 function0) {
        EditableBrightnessViewHolder editableBrightnessViewHolder = this.this$0;
        final OplusQSRootView oplusQSRootViewAccess$findQSRootView = EditableBrightnessViewHolder.access$findQSRootView(editableBrightnessViewHolder);
        if (oplusQSRootViewAccess$findQSRootView == null) {
            return;
        }
        editableBrightnessViewHolder.endAction = function0;
        AnimatableProperty animatableProperty = editableBrightnessViewHolder.panelAlphaAnimator;
        float f = i;
        AnimationProperties animationProperties = i == 255 ? editableBrightnessViewHolder.panelAlphaInPropertiesAnimation : editableBrightnessViewHolder.panelAlphaOutPropertiesAnimator;
        if (!(oplusQSRootViewAccess$findQSRootView.getTag(animatableProperty.getAnimatorTag()) instanceof ValueAnimator) && !z) {
            animatableProperty.getProperty().set(oplusQSRootViewAccess$findQSRootView, Float.valueOf(f));
            return;
        }
        if (!z) {
            animationProperties = null;
        }
        Property<View, Float> property = animatableProperty.getProperty();
        final int animationStartTag = animatableProperty.getAnimationStartTag();
        final int animationEndTag = animatableProperty.getAnimationEndTag();
        Float f2 = (Float) oplusQSRootViewAccess$findQSRootView.getTag(animationEndTag);
        if ((f2 == null || f2.floatValue() != f) && animationProperties != null) {
            final int animatorTag = animatableProperty.getAnimatorTag();
            ValueAnimator valueAnimator = (ValueAnimator) oplusQSRootViewAccess$findQSRootView.getTag(animatorTag);
            Float f3 = property.get(oplusQSRootViewAccess$findQSRootView);
            float fFloatValue = f3.floatValue();
            Consumer consumer = animationProperties.mAnimationEndAction;
            AnimatorListenerAdapter anonymousClass1 = (consumer == null && animationProperties.mAnimationCancelAction == null) ? null : new AnimatorListenerAdapter() { // from class: com.oplus.systemui.plugins.qs.util.AnimationProperties.1
                public boolean mCancelled;
                public final /* synthetic */ Consumer val$cancelAction;
                public final /* synthetic */ Consumer val$endAction;
                public final /* synthetic */ Property val$property;

                public AnonymousClass1() {
                    consumer = consumer;
                    property = property;
                    consumer = consumer;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.mCancelled = true;
                    Consumer consumer2 = consumer;
                    if (consumer2 != null) {
                        consumer2.accept(property);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    Consumer consumer2;
                    if (this.mCancelled || (consumer2 = consumer) == null) {
                        return;
                    }
                    consumer2.accept(property);
                }
            };
            if (fFloatValue == f) {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                if (anonymousClass1 != null) {
                    anonymousClass1.onAnimationEnd(null);
                    return;
                }
                return;
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, f);
            ValueAnimator.AnimatorUpdateListener externalSyntheticLambda0 = new PropertyAnimator$.ExternalSyntheticLambda0();
            ((PropertyAnimator$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$0 = property;
            ((PropertyAnimator$.ExternalSyntheticLambda0) externalSyntheticLambda0).f$1 = oplusQSRootViewAccess$findQSRootView;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(externalSyntheticLambda0);
            ArrayMap arrayMap = animationProperties.mInterpolatorMap;
            TimeInterpolator timeInterpolator = arrayMap != null ? (Interpolator) arrayMap.get(property) : null;
            if (timeInterpolator == null) {
                timeInterpolator = Interpolators.FAST_OUT_SLOW_IN;
            }
            valueAnimatorOfFloat.setInterpolator(timeInterpolator);
            long jMax = animationProperties.duration;
            if (valueAnimator != null) {
                jMax = Math.max(valueAnimator.getDuration() - valueAnimator.getCurrentPlayTime(), jMax);
                valueAnimator.cancel();
            }
            valueAnimatorOfFloat.setDuration(jMax);
            if (animationProperties.delay > 0 && (valueAnimator == null || valueAnimator.getAnimatedFraction() == 0.0f)) {
                valueAnimatorOfFloat.setStartDelay(animationProperties.delay);
            }
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.plugins.qs.util.PropertyAnimator$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    if (((Animator) oplusQSRootViewAccess$findQSRootView.getTag(animatorTag)) != animator) {
                        Log.e("PropertyAnimator", "Unexpected Animator set during onAnimationEnd. Not cleaning up.");
                        return;
                    }
                    oplusQSRootViewAccess$findQSRootView.setTag(animatorTag, null);
                    oplusQSRootViewAccess$findQSRootView.setTag(animationStartTag, null);
                    oplusQSRootViewAccess$findQSRootView.setTag(animationEndTag, null);
                }
            });
            if (anonymousClass1 != null) {
                valueAnimatorOfFloat.addListener(anonymousClass1);
            }
            if (anonymousClass1 != null) {
                anonymousClass1.onAnimationStart(valueAnimatorOfFloat);
            }
            valueAnimatorOfFloat.start();
            oplusQSRootViewAccess$findQSRootView.setTag(animatorTag, valueAnimatorOfFloat);
            oplusQSRootViewAccess$findQSRootView.setTag(animationStartTag, f3);
            oplusQSRootViewAccess$findQSRootView.setTag(animationEndTag, Float.valueOf(f));
        }
    }
}
