package com.oplus.systemui.preventmode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PreventModeSwipeView extends View {
    public static final Companion Companion = new Companion();
    private Paint mCirclePaint;
    public float mCircleRadius;
    public int mDefaultColor;
    public boolean mIignoreMoveEvent;
    private Float mInitTouchX;
    private Float mInitTouchY;
    public float mMaxSwipeDistanceInHorizontal;
    public float mMaxSwipeDistanceInVertical;
    private OplusPreventModeView mOplusPreventModeView;
    public float mSwipeViewCenterX;
    public float mSwipeViewCenterY;
    public float mTranslationX;
    public float mTranslationY;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PreventModeSwipeView(Context context) {
        super(context);
        Float fValueOf = Float.valueOf(0.0f);
        this.mInitTouchX = fValueOf;
        this.mInitTouchY = fValueOf;
        init(context);
    }

    public final void init(Context context) {
        this.mDefaultColor = context.getResources().getColor(2131103165);
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setAntiAlias(true);
        Paint paint2 = this.mCirclePaint;
        if (paint2 != null) {
            paint2.setColor(this.mDefaultColor);
        }
        Paint paint3 = this.mCirclePaint;
        if (paint3 != null) {
            paint3.setAlpha(102);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        float f = this.mSwipeViewCenterX;
        float f2 = this.mSwipeViewCenterY;
        float f3 = this.mCircleRadius;
        Paint paint = this.mCirclePaint;
        paint.getClass();
        canvas.drawCircle(f, f2, f3, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = measuredWidth / 2;
        this.mSwipeViewCenterX = f;
        this.mSwipeViewCenterY = measuredHeight / 2;
        this.mCircleRadius = f;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            this.mInitTouchX = motionEvent != null ? Float.valueOf(motionEvent.getRawX()) : null;
            this.mInitTouchY = motionEvent != null ? Float.valueOf(motionEvent.getRawY()) : null;
            return true;
        }
        if (numValueOf == null || numValueOf.intValue() != 2) {
            if (Math.abs(this.mTranslationY) >= this.mMaxSwipeDistanceInVertical) {
                ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusPreventMode().initiativeExit();
            }
            this.mIignoreMoveEvent = false;
            reset();
            return true;
        }
        if (this.mIignoreMoveEvent) {
            return true;
        }
        float rawX = motionEvent.getRawX();
        Float f = this.mInitTouchX;
        f.getClass();
        this.mTranslationX = rawX - f.floatValue();
        float rawY = motionEvent.getRawY();
        Float f2 = this.mInitTouchY;
        f2.getClass();
        float fFloatValue = rawY - f2.floatValue();
        this.mTranslationY = fFloatValue;
        if (fFloatValue > 0.0f || Math.abs(this.mTranslationX) > this.mMaxSwipeDistanceInHorizontal) {
            this.mIignoreMoveEvent = true;
            reset();
            return super.onTouchEvent(motionEvent);
        }
        float fAbs = Math.abs(this.mTranslationY);
        float f3 = this.mMaxSwipeDistanceInVertical;
        if (fAbs >= f3) {
            this.mTranslationY = -f3;
        }
        setTranslationY(this.mTranslationY);
        float fAbs2 = Math.abs(this.mTranslationY);
        int i = (int) ((fAbs2 / this.mMaxSwipeDistanceInHorizontal) * 255.0f);
        Paint paint = this.mCirclePaint;
        if (i < 102) {
            if (paint != null) {
                paint.setAlpha(102);
            }
        } else if (paint != null) {
            paint.setAlpha(i);
        }
        OplusPreventModeView oplusPreventModeView = this.mOplusPreventModeView;
        if (oplusPreventModeView != null) {
            oplusPreventModeView.updateArrowAlpha(fAbs2);
        }
        invalidate();
        setTranslationX(this.mTranslationX);
        return true;
    }

    public final void reset() {
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        this.mTranslationX = 0.0f;
        this.mTranslationY = 0.0f;
        Paint paint = this.mCirclePaint;
        if (paint != null) {
            paint.setAlpha(102);
        }
        OplusPreventModeView oplusPreventModeView = this.mOplusPreventModeView;
        if (oplusPreventModeView != null) {
            oplusPreventModeView.updateArrowAlpha(0.0f);
        }
        invalidate();
    }

    public final void setMaxSwipeDistanceInHorizontal(float f) {
        this.mMaxSwipeDistanceInHorizontal = f;
    }

    public final void setMaxSwipeDistanceInVertical(float f) {
        this.mMaxSwipeDistanceInVertical = f;
    }

    public final void setOplusPreventModeView(OplusPreventModeView oplusPreventModeView) {
        this.mOplusPreventModeView = oplusPreventModeView;
    }

    public PreventModeSwipeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Float fValueOf = Float.valueOf(0.0f);
        this.mInitTouchX = fValueOf;
        this.mInitTouchY = fValueOf;
        init(context);
    }
}
