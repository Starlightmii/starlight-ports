package com.oplus.systemui.statusbar.phone.dynamic.icon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.systemui.Dependency;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIcon;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SecurePaymentIconView extends OplusStatusBarIconView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mCircleAlpha;
    public ValueAnimator mCircleFadeAnimator;
    public Drawable mContentDrawable;
    public float mDarkIntensity;
    public final int mDiameter;
    public boolean mDoCircleFadeAnimation;
    public boolean mDoCircleRotateAnimation;
    public boolean mDoRiskRecoveryAnimation;
    public Drawable mFillingRiskDrawable;
    public int mHeight;
    public int mIconTint;
    public int mLayoutLeft;
    public int mMode;
    public float mNoRiskSymbolScale;
    public Paint mPaint;
    public int mPaintColor;
    public Path mPath;
    public final Interpolator mPathInterpolator;
    public RectF mRectF;
    public int mRiskDetection;
    public float mRiskSymbolScale;
    public float mRotateAngle;
    public float mRotateRadian;
    public float mRotationX;
    public float mRotationY;
    public int mShieldAlpha;
    public Drawable mShieldDrawable;
    public long mStartTime;
    public ValueAnimator mSymbolScaleAnimator;
    public final ArrayList mTintArea;
    public int mWidth;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ SecurePaymentIconView this$0;

        /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            ?? r2 = this.this$0;
            if (r2.mDoRiskRecoveryAnimation) {
                r2.mNoRiskSymbolScale = fFloatValue;
            } else {
                int i = r2.mMode;
                if (i == 2) {
                    r2.mRiskSymbolScale = fFloatValue;
                } else if (i == 3) {
                    r2.mNoRiskSymbolScale = fFloatValue;
                }
            }
            r2.postInvalidate();
        }
    }

    public SecurePaymentIconView(Context context, String str, boolean z) {
        super(context, str, z);
        this.mPathInterpolator = new PathInterpolator(0.2f, 0.0f, 0.3f, 1.0f);
        this.mTintArea = new ArrayList();
        this.mMode = 0;
        this.mIconTint = -1;
        this.mLayoutLeft = -1;
        this.mDiameter = 19;
        this.mRotateRadian = 0.0f;
        this.mRotateAngle = 0.0f;
        this.mDoCircleRotateAnimation = false;
        this.mDoCircleFadeAnimation = false;
        this.mDoRiskRecoveryAnimation = false;
        this.mCircleAlpha = 255;
        this.mRiskSymbolScale = 0.0f;
        this.mNoRiskSymbolScale = 0.0f;
        this.mShieldAlpha = 178;
        this.mRiskDetection = 0;
        init$3$1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView
    public final void applyDynamicIcon(DynamicIcon dynamicIcon, boolean z) {
        super.applyDynamicIcon(dynamicIcon, z);
        if ((dynamicIcon.getIconSource() instanceof IconSource.StateLinkIconSource) && dynamicIcon.getVisible() && !getBlocked()) {
            switch (dynamicIcon.getIconSource().getIconState()) {
                case 1000:
                    this.mStartTime = System.currentTimeMillis();
                    this.mCircleAlpha = 255;
                    this.mShieldAlpha = 178;
                    this.mMode = 1;
                    this.mRiskDetection = 1;
                    this.mDoCircleRotateAnimation = true;
                    invalidate();
                    break;
                case 1001:
                    LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "startRiskDiscoveryAnimation()");
                    clearAnimations();
                    this.mMode = 2;
                    this.mRiskSymbolScale = 0.0f;
                    this.mNoRiskSymbolScale = 0.0f;
                    this.mDoRiskRecoveryAnimation = false;
                    this.mCircleFadeAnimator.start();
                    break;
                case 1002:
                    LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "startRiskRecoveryAnimation()");
                    clearAnimations();
                    this.mDoRiskRecoveryAnimation = true;
                    this.mRiskSymbolScale = 1.0f;
                    this.mNoRiskSymbolScale = 0.0f;
                    this.mSymbolScaleAnimator.start();
                    break;
                case 1004:
                    LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "startNoRiskDiscoveryAnimation()");
                    clearAnimations();
                    this.mMode = 3;
                    this.mRiskSymbolScale = 0.0f;
                    this.mNoRiskSymbolScale = 0.0f;
                    this.mDoRiskRecoveryAnimation = false;
                    this.mCircleFadeAnimator.start();
                    break;
            }
        }
    }

    public final void clearAnimations() {
        if (this.mCircleFadeAnimator.isRunning()) {
            this.mCircleFadeAnimator.cancel();
        }
        if (this.mSymbolScaleAnimator.isRunning()) {
            this.mSymbolScaleAnimator.cancel();
        }
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView
    public /* bridge */ /* synthetic */ int getSlotTag() {
        return super.getSlotTag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void init$3$1() {
        int i = this.mDiameter;
        float f = i - 3;
        this.mRectF = new RectF(3.0f, 3.0f, f, f);
        float f2 = (i / 2.0f) + 0.0f;
        this.mRotationX = f2;
        this.mRotationY = f2;
        this.mPath = new Path();
        this.mPaint = new Paint();
        float f3 = this.mRotationX;
        final int i2 = 0;
        final int i3 = 1;
        this.mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{f3, f3, 200.0f}, 0.7f, 5.0f, 2.0f));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mShieldDrawable = getContext().getDrawable(2131236737);
        this.mFillingRiskDrawable = getContext().getDrawable(2131236738);
        this.mContentDrawable = getContext().getDrawable(2131236739);
        this.mHeight = this.mShieldDrawable.getIntrinsicHeight();
        this.mWidth = this.mShieldDrawable.getIntrinsicWidth();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mSymbolScaleAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.mSymbolScaleAnimator.addListener(new AnimatorListenerAdapter(this) { // from class: com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView.1
            public final /* synthetic */ SecurePaymentIconView this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Type inference failed for: r4v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i2) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "onAnimationCancel");
                        ?? r4 = this.this$0;
                        if (r4.mDoRiskRecoveryAnimation) {
                            r4.mDoRiskRecoveryAnimation = false;
                            r4.mMode = 3;
                        }
                        r4.postInvalidate();
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "onAnimationCancel");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = false;
                        securePaymentIconView.mCircleAlpha = 0;
                        securePaymentIconView.mShieldAlpha = 255;
                        break;
                }
            }

            /* JADX WARN: Type inference failed for: r4v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        StringBuilder sb = new StringBuilder("mSymbolScaleAnimator onAnimationEnd 22 mMode=");
                        sb.append(this.this$0.mMode);
                        sb.append(", mDoRiskRecoveryAnimation=");
                        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", sb, this.this$0.mDoRiskRecoveryAnimation);
                        ?? r4 = this.this$0;
                        if (r4.mDoRiskRecoveryAnimation) {
                            r4.mDoRiskRecoveryAnimation = false;
                            r4.mMode = 3;
                        }
                        r4.postInvalidate();
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mCircleFadeAnimator onAnimationEnd");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = false;
                        securePaymentIconView.mSymbolScaleAnimator.start();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                switch (i2) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mSymbolScaleAnimator onAnimationStart");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mCircleFadeAnimator onAnimationStart");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = true;
                        securePaymentIconView.mDoCircleRotateAnimation = false;
                        break;
                }
            }
        });
        ValueAnimator valueAnimator = this.mSymbolScaleAnimator;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator.addUpdateListener(anonymousClass2);
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mCircleFadeAnimator = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(200L);
        this.mCircleFadeAnimator.addListener(new AnimatorListenerAdapter(this) { // from class: com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView.1
            public final /* synthetic */ SecurePaymentIconView this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Type inference failed for: r4v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i3) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "onAnimationCancel");
                        ?? r4 = this.this$0;
                        if (r4.mDoRiskRecoveryAnimation) {
                            r4.mDoRiskRecoveryAnimation = false;
                            r4.mMode = 3;
                        }
                        r4.postInvalidate();
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "onAnimationCancel");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = false;
                        securePaymentIconView.mCircleAlpha = 0;
                        securePaymentIconView.mShieldAlpha = 255;
                        break;
                }
            }

            /* JADX WARN: Type inference failed for: r4v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i3) {
                    case 0:
                        StringBuilder sb = new StringBuilder("mSymbolScaleAnimator onAnimationEnd 22 mMode=");
                        sb.append(this.this$0.mMode);
                        sb.append(", mDoRiskRecoveryAnimation=");
                        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", sb, this.this$0.mDoRiskRecoveryAnimation);
                        ?? r4 = this.this$0;
                        if (r4.mDoRiskRecoveryAnimation) {
                            r4.mDoRiskRecoveryAnimation = false;
                            r4.mMode = 3;
                        }
                        r4.postInvalidate();
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mCircleFadeAnimator onAnimationEnd");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = false;
                        securePaymentIconView.mSymbolScaleAnimator.start();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                switch (i3) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mSymbolScaleAnimator onAnimationStart");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_STATUSBAR, "SecurePaymentIconView", "mCircleFadeAnimator onAnimationStart");
                        SecurePaymentIconView securePaymentIconView = this.this$0;
                        securePaymentIconView.mDoCircleFadeAnimation = true;
                        securePaymentIconView.mDoCircleRotateAnimation = false;
                        break;
                }
            }
        });
        ValueAnimator valueAnimator2 = this.mCircleFadeAnimator;
        SecurePaymentIconView$$ExternalSyntheticLambda0 securePaymentIconView$$ExternalSyntheticLambda0 = new SecurePaymentIconView$$ExternalSyntheticLambda0();
        securePaymentIconView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator2.addUpdateListener(securePaymentIconView$$ExternalSyntheticLambda0);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((DarkIconDispatcher) Dependency.sDependency.getDependencyInner(DarkIconDispatcher.class)).addDarkReceiver(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView
    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        boolean z = (i == this.mIconTint && f == this.mDarkIntensity && this.mTintArea.equals(arrayList)) ? false : true;
        this.mIconTint = i;
        this.mDarkIntensity = f;
        this.mTintArea.clear();
        this.mTintArea.addAll(arrayList);
        if (z && isAttachedToWindow()) {
            int tint = DarkIconDispatcher.getTint(this.mTintArea, this, this.mIconTint);
            this.mPaintColor = tint;
            Drawable drawable = this.mShieldDrawable;
            if (drawable == null || this.mContentDrawable == null) {
                return;
            }
            drawable.setTintList(ColorStateList.valueOf(tint));
            postInvalidate();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((DarkIconDispatcher) Dependency.sDependency.getDependencyInner(DarkIconDispatcher.class)).removeDarkReceiver(this);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x00d2  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView
    public final void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        if (this.mDoCircleRotateAnimation || this.mDoCircleFadeAnimation) {
            this.mPaint.setColor(this.mPaintColor);
            if (this.mDoCircleRotateAnimation) {
                long jCurrentTimeMillis = (System.currentTimeMillis() - this.mStartTime) % 1200;
                if (jCurrentTimeMillis <= 1200) {
                    float f = jCurrentTimeMillis;
                    this.mRotateAngle = ((PathInterpolator) this.mPathInterpolator).getInterpolation(Math.max(0.0f, Math.min(1.0f, f / 1200.0f))) * 720.0f;
                    if (jCurrentTimeMillis <= 600) {
                        this.mRotateRadian = ((PathInterpolator) this.mPathInterpolator).getInterpolation(Math.max(0.0f, Math.min(1.0f, f / 600.0f))) * 60.0f;
                    } else {
                        this.mRotateRadian = ((PathInterpolator) this.mPathInterpolator).getInterpolation(1.0f - Math.max(0.0f, Math.min(1.0f, (jCurrentTimeMillis - 600) / 600.0f))) * 60.0f;
                    }
                }
                this.mPath.reset();
                Path path = this.mPath;
                RectF rectF = this.mRectF;
                float f2 = this.mRotateRadian;
                path.arcTo(rectF, 110.0f + f2, 140.0f - (f2 * 2.0f));
            }
            this.mPaint.setAlpha(this.mCircleAlpha);
            canvas.save();
            int i = this.mWidth;
            int i2 = this.mDiameter;
            canvas.translate((i - i2) / 2, (this.mHeight - i2) / 2);
            canvas.rotate(this.mRotateAngle, this.mRotationX, this.mRotationY);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.rotate(180.0f, this.mRotationX, this.mRotationY);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.restore();
            if (this.mDoCircleRotateAnimation) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (this.mFillingRiskDrawable != null && this.mMode == 2) {
            canvas.save();
            float f3 = this.mRiskSymbolScale;
            canvas.scale(f3, f3, this.mWidth / 2, this.mHeight / 2);
            this.mFillingRiskDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            this.mFillingRiskDrawable.draw(canvas);
            canvas.restore();
        } else if (this.mMode == 3 || this.mDoRiskRecoveryAnimation) {
            canvas.save();
            float f4 = this.mNoRiskSymbolScale;
            canvas.scale(f4, f4, this.mWidth / 2, this.mHeight / 2);
            this.mContentDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            this.mContentDrawable.draw(canvas);
            canvas.restore();
        } else if (this.mShieldDrawable != null) {
            canvas.save();
            this.mShieldDrawable.setAlpha(this.mShieldAlpha);
            this.mShieldDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            this.mShieldDrawable.draw(canvas);
            canvas.restore();
        }
        if (z) {
            postInvalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int[] locationOnScreen;
        super/*android.widget.ImageView*/.onLayout(z, i, i2, i3, i4);
        if (this.mRiskDetection == 0 || (locationOnScreen = getLocationOnScreen()) == null || locationOnScreen.length <= 0) {
            return;
        }
        int i5 = this.mLayoutLeft;
        int i6 = locationOnScreen[0];
        if (i5 != i6) {
            this.mLayoutLeft = i6;
            int tint = DarkIconDispatcher.getTint(this.mTintArea, this, this.mIconTint);
            this.mPaintColor = tint;
            Drawable drawable = this.mShieldDrawable;
            if (drawable == null || this.mContentDrawable == null) {
                return;
            }
            drawable.setTintList(ColorStateList.valueOf(tint));
            postInvalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        if (getBlocked()) {
            super/*android.widget.ImageView*/.onMeasure(i, i2);
        } else {
            setMeasuredDimension(this.mWidth, this.mHeight);
        }
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView
    public /* bridge */ /* synthetic */ void setVisibleState(int i) {
        super.setVisibleState(i);
    }

    public SecurePaymentIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPathInterpolator = new PathInterpolator(0.2f, 0.0f, 0.3f, 1.0f);
        this.mTintArea = new ArrayList();
        this.mMode = 0;
        this.mIconTint = -1;
        this.mLayoutLeft = -1;
        this.mDiameter = 19;
        this.mRotateRadian = 0.0f;
        this.mRotateAngle = 0.0f;
        this.mDoCircleRotateAnimation = false;
        this.mDoCircleFadeAnimation = false;
        this.mDoRiskRecoveryAnimation = false;
        this.mCircleAlpha = 255;
        this.mRiskSymbolScale = 0.0f;
        this.mNoRiskSymbolScale = 0.0f;
        this.mShieldAlpha = 178;
        this.mRiskDetection = 0;
        init$3$1();
    }
}
