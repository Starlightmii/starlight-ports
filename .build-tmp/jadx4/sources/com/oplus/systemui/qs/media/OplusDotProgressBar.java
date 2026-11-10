package com.oplus.systemui.qs.media;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDotProgressBar extends FrameLayout {
    public static final Companion Companion = new Companion();
    public final long animationDuration;
    private final List<Animator> animators;
    private ValueAnimator dotAnimator;
    public final int dotBackground;
    private Drawable dotBackgroundDrawable;
    public LinearLayout dotProgressBar;
    public final int dotRadius;
    public final int margin;
    public final float maxScale;
    public final float minScale;
    public final int numberOfDots;
    private ValueAnimator primaryAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int convertDpToPixel(float f, Context context) {
            return (int) ((context.getResources().getDisplayMetrics().densityDpi / 160.0f) * f);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusDotProgressBar$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            switch (this.$r8$classId) {
                case 0:
                    if (!Intrinsics.areEqual(valueAnimator.getAnimatedValue(), Integer.valueOf(((OplusDotProgressBar) this.this$0).numberOfDots))) {
                        ((Animator) ((OplusDotProgressBar) this.this$0).animators.get(((Integer) valueAnimator.getAnimatedValue()).intValue())).start();
                    }
                    break;
                default:
                    ((View) this.this$0).setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    ((View) this.this$0).setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    break;
            }
        }
    }

    public OplusDotProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Companion companion = Companion;
        this.margin = companion.convertDpToPixel(1.0f, getContext());
        this.dotRadius = companion.convertDpToPixel(3.0f, getContext());
        this.numberOfDots = 3;
        this.animators = new ArrayList();
        this.animationDuration = 1000L;
        this.minScale = 0.5f;
        this.maxScale = 1.0f;
        this.dotBackground = 2131235957;
        this.dotBackgroundDrawable = ((FrameLayout) this).mContext.getDrawable(2131235957);
        init();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void init() {
        int i = 0;
        setClipChildren(false);
        setClipToPadding(false);
        this.dotProgressBar = new LinearLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = this.dotProgressBar;
        if (linearLayout == null) {
            linearLayout = null;
        }
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = this.dotProgressBar;
        if (linearLayout2 == null) {
            linearLayout2 = null;
        }
        linearLayout2.setClipChildren(false);
        LinearLayout linearLayout3 = this.dotProgressBar;
        if (linearLayout3 == null) {
            linearLayout3 = null;
        }
        linearLayout3.setClipToPadding(false);
        LinearLayout linearLayout4 = this.dotProgressBar;
        if (linearLayout4 == null) {
            linearLayout4 = null;
        }
        addView(linearLayout4);
        this.animators.clear();
        int i2 = this.numberOfDots;
        int i3 = 0;
        while (true) {
            int i4 = 1;
            if (i3 >= i2) {
                break;
            }
            View view = new View(getContext());
            int i5 = this.dotRadius * 2;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i5, i5);
            int i6 = this.margin;
            layoutParams2.setMargins(i6, i6, i6, i6);
            view.setLayoutParams(layoutParams2);
            view.setScaleX(this.minScale);
            view.setScaleY(this.minScale);
            view.setBackgroundResource(this.dotBackground);
            LinearLayout linearLayout5 = this.dotProgressBar;
            if (linearLayout5 == null) {
                linearLayout5 = null;
            }
            linearLayout5.addView(view);
            ValueAnimator valueAnimatorOfFloat = this.dotAnimator;
            if (valueAnimatorOfFloat == 0) {
                valueAnimatorOfFloat = ValueAnimator.ofFloat(this.minScale, this.maxScale);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(i4);
                anonymousClass1.this$0 = view;
                VarHandle.storeStoreFence();
                valueAnimatorOfFloat.addUpdateListener(anonymousClass1);
                valueAnimatorOfFloat.setDuration(this.animationDuration / ((long) this.numberOfDots));
                valueAnimatorOfFloat.setRepeatCount(1);
                valueAnimatorOfFloat.setRepeatMode(2);
                valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            }
            this.animators.add(valueAnimatorOfFloat);
            i3++;
        }
        ValueAnimator valueAnimator = this.primaryAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, this.numberOfDots);
        this.primaryAnimator = valueAnimatorOfInt;
        if (valueAnimatorOfInt != null) {
            AnonymousClass1 anonymousClass2 = new AnonymousClass1(i);
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfInt.addUpdateListener(anonymousClass2);
        }
        ValueAnimator valueAnimator2 = this.primaryAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.setRepeatMode(1);
        }
        ValueAnimator valueAnimator3 = this.primaryAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.setRepeatCount(-1);
        }
        ValueAnimator valueAnimator4 = this.primaryAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.setDuration(this.animationDuration);
        }
        ValueAnimator valueAnimator5 = this.primaryAnimator;
        if (valueAnimator5 != null) {
            valueAnimator5.setInterpolator(new LinearInterpolator());
        }
    }

    public final boolean isAnimationRunning() {
        ValueAnimator valueAnimator = this.primaryAnimator;
        if (valueAnimator != null) {
            return valueAnimator.isRunning();
        }
        return false;
    }

    public final void setDotBackground(int i) {
        Drawable drawable = this.dotBackgroundDrawable;
        if (drawable != null) {
            drawable.clearColorFilter();
        }
        Drawable drawable2 = this.dotBackgroundDrawable;
        if (drawable2 != null) {
            drawable2.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
        int i2 = this.numberOfDots;
        for (int i3 = 0; i3 < i2; i3++) {
            LinearLayout linearLayout = this.dotProgressBar;
            if (linearLayout == null) {
                linearLayout = null;
            }
            linearLayout.getChildAt(i3).setBackgroundDrawable(this.dotBackgroundDrawable);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i == 0) {
            startAnimation();
        } else {
            stopAnimation();
        }
        super.setVisibility(i);
    }

    public final void startAnimation() {
        ValueAnimator valueAnimator = this.primaryAnimator;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    public final void stopAnimation() {
        ValueAnimator valueAnimator = this.primaryAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public OplusDotProgressBar(Context context) {
        super(context);
        Companion companion = Companion;
        this.margin = companion.convertDpToPixel(1.0f, getContext());
        this.dotRadius = companion.convertDpToPixel(3.0f, getContext());
        this.numberOfDots = 3;
        this.animators = new ArrayList();
        this.animationDuration = 1000L;
        this.minScale = 0.5f;
        this.maxScale = 1.0f;
        this.dotBackground = 2131235957;
        this.dotBackgroundDrawable = ((FrameLayout) this).mContext.getDrawable(2131235957);
    }

    public OplusDotProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Companion companion = Companion;
        this.margin = companion.convertDpToPixel(1.0f, getContext());
        this.dotRadius = companion.convertDpToPixel(3.0f, getContext());
        this.numberOfDots = 3;
        this.animators = new ArrayList();
        this.animationDuration = 1000L;
        this.minScale = 0.5f;
        this.maxScale = 1.0f;
        this.dotBackground = 2131235957;
        this.dotBackgroundDrawable = ((FrameLayout) this).mContext.getDrawable(2131235957);
        init();
    }
}
