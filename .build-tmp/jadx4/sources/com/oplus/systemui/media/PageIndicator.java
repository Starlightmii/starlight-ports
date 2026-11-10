package com.oplus.systemui.media;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.compose.material3.internal.colorUtil.Cam$;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PageIndicator extends FrameLayout {
    public int mActivePage;
    public final AnonymousClass2 mAnimCycleListener;
    public ObjectAnimator mAnimator;
    public final Paint mCircleActivePaint;
    public final Paint mCirclePaint;
    public float mCurrentPosition;
    public final float mDotGap;
    public float mDotRadius;
    public float mDotSize;
    public final float mDotStrokeWidth;
    public float mFinalPosition;
    public final Interpolator mInterpolator;
    public int mNumPages;
    public static final RectF TEMP_RECT = new RectF();
    public static final AnonymousClass1 CURRENT_POSITION = new AnonymousClass1(Float.TYPE, "current_position");

    /* JADX INFO: renamed from: com.oplus.systemui.media.PageIndicator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends Property {
        @Override // android.util.Property
        public final Object get(Object obj) {
            return Float.valueOf(((PageIndicator) obj).mCurrentPosition);
        }

        @Override // android.util.Property
        public final void set(Object obj, Object obj2) {
            PageIndicator pageIndicator = (PageIndicator) obj;
            pageIndicator.mCurrentPosition = ((Float) obj2).floatValue();
            pageIndicator.invalidate();
            pageIndicator.invalidateOutline();
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [com.oplus.systemui.media.PageIndicator$2] */
    public PageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimCycleListener = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.media.PageIndicator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                PageIndicator pageIndicator = PageIndicator.this;
                pageIndicator.mAnimator = null;
                pageIndicator.animateToPosition(pageIndicator.mFinalPosition);
            }
        };
        this.mInterpolator = new PathInterpolator(0.25f, 0.1f, 0.25f, 1.0f);
        this.mNumPages = 1;
        this.mDotSize = getResources().getDimension(2131170124);
        this.mDotGap = getResources().getDimension(2131170123);
        float fMin = Math.min(getResources().getDimension(2131170125), this.mDotSize * 0.5f);
        this.mDotStrokeWidth = fMin;
        this.mDotRadius = this.mDotSize * 0.5f;
        Paint paint = new Paint(1);
        this.mCirclePaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setColor(context.getColor(2131102725));
        paint.setStrokeWidth(fMin);
        Paint paint2 = new Paint(1);
        this.mCircleActivePaint = paint2;
        paint2.setStyle(style);
        paint2.setColor(context.getColor(2131102724));
        paint2.setStrokeWidth(fMin);
        setWillNotDraw(false);
    }

    private RectF getActiveRect() {
        float f = this.mCurrentPosition;
        float f2 = (int) f;
        float f3 = f - f2;
        float f4 = this.mDotSize + this.mDotGap;
        float width = ((getWidth() - (this.mNumPages * f4)) + this.mDotGap + this.mDotStrokeWidth) * 0.5f;
        RectF rectF = TEMP_RECT;
        rectF.top = (getHeight() * 0.5f) - this.mDotRadius;
        float height = getHeight() * 0.5f;
        float f5 = this.mDotRadius;
        rectF.bottom = height + f5;
        float f6 = (f2 * f4) + width;
        rectF.left = f6;
        float f7 = (f5 * 2.0f) + f6;
        rectF.right = f7;
        if (f3 < 0.5f) {
            rectF.right = Cam$.ExternalSyntheticOutline0.m(f3, f4, 2.0f, f7);
        } else {
            rectF.right = f7 + f4;
            rectF.left = ((f3 - 0.5f) * f4 * 2.0f) + f6;
        }
        if (getLayoutDirection() == 1) {
            float fWidth = rectF.width();
            float width2 = getWidth() - rectF.left;
            rectF.right = width2;
            rectF.left = width2 - fWidth;
        }
        return rectF;
    }

    public final void animateToPosition(float f) {
        this.mFinalPosition = f;
        if (Math.abs(this.mCurrentPosition - f) < 0.1f) {
            this.mCurrentPosition = this.mFinalPosition;
        }
        if (this.mAnimator != null || Float.compare(this.mCurrentPosition, this.mFinalPosition) == 0) {
            return;
        }
        float f2 = this.mCurrentPosition;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, CURRENT_POSITION, f2 > this.mFinalPosition ? f2 - 0.5f : f2 + 0.5f);
        this.mAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.addListener(this.mAnimCycleListener);
        this.mAnimator.setInterpolator(this.mInterpolator);
        this.mAnimator.setDuration(120L);
        this.mAnimator.start();
    }

    public int getActiveColor() {
        return this.mCircleActivePaint.getColor();
    }

    public int getInActiveColor() {
        return this.mCirclePaint.getColor();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f = this.mDotSize + this.mDotGap;
        float width = (((getWidth() - (this.mNumPages * f)) + this.mDotGap + this.mDotStrokeWidth) * 0.5f) + this.mDotRadius;
        float height = getHeight() * 0.5f;
        for (int i = 0; i < this.mNumPages; i++) {
            canvas.drawCircle(width, height, this.mDotRadius, this.mCirclePaint);
            width += f;
        }
        RectF activeRect = getActiveRect();
        float f2 = this.mDotRadius;
        canvas.drawRoundRect(activeRect, f2, f2, this.mCircleActivePaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            size = View.MeasureSpec.getSize(i);
        } else {
            int i3 = this.mNumPages;
            size = (int) (((i3 + 1) * this.mDotGap) + (i3 * this.mDotSize));
        }
        setMeasuredDimension(size, View.MeasureSpec.getMode(i2) == 1073741824 ? View.MeasureSpec.getSize(i2) : (int) (this.mDotSize * 2.0f));
    }

    public void setActiveColor(int i) {
        this.mCircleActivePaint.setColor(i);
    }

    public void setActiveMarker(int i) {
        if (this.mActivePage != i) {
            this.mActivePage = i;
            invalidate();
        }
    }

    public void setDotSize(float f) {
        this.mDotSize = f;
        this.mDotRadius = (f - this.mDotStrokeWidth) * 0.5f;
        if (!isInLayout()) {
            requestLayout();
            return;
        }
        PageIndicator$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new PageIndicator$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        post(externalSyntheticLambda0);
    }

    public void setInActiveColor(int i) {
        this.mCirclePaint.setColor(i);
    }

    public void setMarkersCount(int i) {
        this.mNumPages = i;
        setVisibility(i > 1 ? 0 : 8);
        if (this.mNumPages == 1) {
            this.mActivePage = 0;
        }
        requestLayout();
    }

    public final void updatePageIndicatorColor() {
        setActiveColor(((FrameLayout) this).mContext.getColor(2131102724));
        setInActiveColor(((FrameLayout) this).mContext.getColor(2131102725));
        invalidate();
    }

    public PageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PageIndicator(Context context) {
        this(context, null);
    }
}
