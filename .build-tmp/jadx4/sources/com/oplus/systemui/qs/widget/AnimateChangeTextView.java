package com.oplus.systemui.qs.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AnimateChangeTextView extends AppCompatTextView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mDoScaleAnima;
    public boolean mFadeChangeAnimationRunning;
    public final ObjectAnimator mFadeInAnimator;
    public final ObjectAnimator mFadeOutAnimator;
    public float mLastTextSize;
    public float mNewSizePx;
    public COUISpringAnimation mScaleAnimation;
    public boolean mSuppressFadeAnimation;
    public boolean mSuppressTextSizeAnimation;

    public AnimateChangeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, 1.0f, 0.0f);
        this.mFadeOutAnimator = objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, 0.0f, 1.0f);
        this.mFadeInAnimator = objectAnimatorOfFloat2;
        this.mFadeChangeAnimationRunning = false;
        this.mSuppressFadeAnimation = false;
        this.mSuppressTextSizeAnimation = false;
        this.mLastTextSize = 0.0f;
        this.mDoScaleAnima = false;
        this.mNewSizePx = 0.0f;
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
        objectAnimatorOfFloat2.setDuration(300L);
        objectAnimatorOfFloat2.setInterpolator(new COUIEaseInterpolator());
        objectAnimatorOfFloat2.setStartDelay(100L);
        this.mScaleAnimation = getScaleAnimation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void access$001(OplusSecondCarrierText oplusSecondCarrierText, CharSequence charSequence) {
        super/*android.widget.TextView*/.setText(charSequence, TextView.BufferType.NORMAL);
    }

    private COUISpringAnimation getScaleAnimation() {
        if (this.mScaleAnimation == null) {
            COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
            cOUISpringAnimation.setSpring(new COUISpringForce().setBounce(0.0f).setResponse(0.35f));
            cOUISpringAnimation.setMinimumVisibleChange(1.0E-4f);
            AnimateChangeTextView$$ExternalSyntheticLambda0 animateChangeTextView$$ExternalSyntheticLambda0 = new AnimateChangeTextView$$ExternalSyntheticLambda0();
            animateChangeTextView$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            cOUISpringAnimation.addUpdateListener(animateChangeTextView$$ExternalSyntheticLambda0);
            AnimateChangeTextView$$ExternalSyntheticLambda1 animateChangeTextView$$ExternalSyntheticLambda1 = new AnimateChangeTextView$$ExternalSyntheticLambda1();
            animateChangeTextView$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            cOUISpringAnimation.addEndListener(animateChangeTextView$$ExternalSyntheticLambda1);
            this.mScaleAnimation = cOUISpringAnimation;
        }
        return this.mScaleAnimation;
    }

    public final void cancelFadeAnimation() {
        LogUtil.i(LogUtil.TAG_QS, "AnimateChangeTextView", "cancelFadeAnimation");
        this.mFadeOutAnimator.cancel();
        this.mFadeInAnimator.cancel();
        this.mFadeChangeAnimationRunning = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDetachedFromWindow() {
        this.mScaleAnimation.cancelComplete();
        setScaleX(1.0f);
        setScaleY(1.0f);
        cancelFadeAnimation();
        setAlpha(1.0f);
        super.onDetachedFromWindow();
    }

    public void setSuppressTextSizeAnimation(boolean z) {
        this.mSuppressTextSizeAnimation = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setTextSize(int i, float f) {
        if (this.mSuppressTextSizeAnimation) {
            super.setTextSize(i, f);
            return;
        }
        if (!isAttachedToWindow()) {
            super.setTextSize(i, f);
            return;
        }
        float fApplyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
        StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(f, i, "setTextSize unit=", " size=", " newSizePx=");
        sbM.append(fApplyDimension);
        LogUtil.i(LogUtil.TAG_QS, "AnimateChangeTextView", sbM.toString());
        if (fApplyDimension == 0.0f) {
            super.setTextSize(i, f);
        } else {
            startScaleAnima(fApplyDimension);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setTextWithAnima(final CharSequence charSequence, float f) {
        if (this.mSuppressFadeAnimation) {
            super/*android.widget.TextView*/.setText(charSequence, TextView.BufferType.NORMAL);
            setAlpha(1.0f);
            return;
        }
        if (this.mFadeChangeAnimationRunning && TextUtils.equals(charSequence, getText())) {
            cancelFadeAnimation();
            super/*android.widget.TextView*/.setText(charSequence, TextView.BufferType.NORMAL);
            setAlpha(1.0f);
            return;
        }
        this.mFadeChangeAnimationRunning = true;
        cancelFadeAnimation();
        final OplusSecondCarrierText oplusSecondCarrierText = (OplusSecondCarrierText) this;
        this.mFadeOutAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.widget.AnimateChangeTextView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                oplusSecondCarrierText.mFadeOutAnimator.removeListener(this);
                oplusSecondCarrierText.mFadeChangeAnimationRunning = false;
            }

            /* JADX WARN: Type inference failed for: r6v5, types: [android.widget.TextView, com.oplus.systemui.qs.widget.AnimateChangeTextView, com.oplus.systemui.qs.widget.OplusSecondCarrierText] */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                oplusSecondCarrierText.mFadeOutAnimator.removeListener(this);
                OplusSecondCarrierText oplusSecondCarrierText2 = oplusSecondCarrierText;
                oplusSecondCarrierText2.mSuppressFadeAnimation = true;
                try {
                    CharSequence charSequence2 = charSequence;
                    TextView.BufferType bufferType = TextView.BufferType.NORMAL;
                    AnimateChangeTextView.access$001(oplusSecondCarrierText2, charSequence2);
                    final ?? r6 = oplusSecondCarrierText;
                    r6.mSuppressFadeAnimation = false;
                    LogUtil.i(LogUtil.TAG_QS, "AnimateChangeTextView", "startFadeInAnima");
                    r6.mFadeInAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.widget.AnimateChangeTextView.2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator2) {
                            r6.mFadeInAnimator.removeListener(this);
                            r6.mFadeChangeAnimationRunning = false;
                        }

                        /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.TextView, com.oplus.systemui.qs.widget.AnimateChangeTextView, com.oplus.systemui.qs.widget.OplusSecondCarrierText] */
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator2) {
                            r6.mFadeInAnimator.removeListener(this);
                            ?? r0 = r6;
                            r0.mFadeChangeAnimationRunning = false;
                            r0.setAlpha(1.0f);
                        }
                    });
                    r6.mFadeInAnimator.setFloatValues(r6.getAlpha(), 1.0f);
                    r6.mFadeInAnimator.start();
                    OplusSecondCarrierText oplusSecondCarrierText3 = oplusSecondCarrierText;
                    if (oplusSecondCarrierText3.mDoScaleAnima) {
                        oplusSecondCarrierText3.startScaleAnima(oplusSecondCarrierText3.mNewSizePx);
                    }
                } catch (Throwable th) {
                    oplusSecondCarrierText.mSuppressFadeAnimation = false;
                    throw th;
                }
            }
        });
        this.mDoScaleAnima = f > 0.0f;
        this.mNewSizePx = f;
        StringBuilder sb = new StringBuilder("setTextWithAnima  mNewSizePx=");
        sb.append(this.mNewSizePx);
        sb.append(" mDoScaleAnima=");
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "AnimateChangeTextView", sb, this.mDoScaleAnima);
        this.mFadeOutAnimator.setFloatValues(getAlpha(), 0.0f);
        this.mFadeOutAnimator.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startScaleAnima(float f) {
        LogUtil.i(LogUtil.TAG_QS, "AnimateChangeTextView", "startScaleAnima newPx=" + f);
        this.mScaleAnimation.cancelComplete();
        setScaleX(1.0f);
        setScaleY(1.0f);
        float textSize = this.mLastTextSize;
        if (textSize == 0.0f) {
            textSize = getTextSize();
        }
        this.mLastTextSize = f;
        float f2 = textSize / f;
        if (Float.isNaN(f2) || Float.isInfinite(f2) || Math.abs(f2 - 1.0f) < 0.01f) {
            f2 = -1.0f;
        }
        this.mSuppressTextSizeAnimation = true;
        try {
            super.setTextSize(0, f);
            this.mSuppressTextSizeAnimation = false;
            int width = getWidth();
            int height = getHeight();
            if (width <= 0 || height <= 0 || f2 < 0.0f) {
                return;
            }
            setPivotX(0.0f);
            setPivotY(height / 2.0f);
            setScaleX(f2);
            setScaleY(f2);
            this.mScaleAnimation.setStartValue(f2);
            this.mScaleAnimation.animateToFinalPosition(1.0f);
        } catch (Throwable th) {
            this.mSuppressTextSizeAnimation = false;
            throw th;
        }
    }

    public AnimateChangeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
