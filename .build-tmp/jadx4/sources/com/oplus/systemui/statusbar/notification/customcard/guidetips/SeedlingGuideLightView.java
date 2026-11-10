package com.oplus.systemui.statusbar.notification.customcard.guidetips;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatImageView;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingGuideLightView extends AppCompatImageView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeedlingGuideLightView";
    private GuideTipsAnimatorListener animatorListener;
    private final Animator endAnimator;
    private final Animator startAnimation;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SeedlingGuideLightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable = context.getResources().getDrawable(2131236469);
        this.startAnimation = getStartAnimation();
        this.endAnimator = getEndAnimator();
        setImageDrawable(drawable);
        setAlpha(0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Animator getEndAnimator() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(getAlpha(), 0.0f);
        valueAnimatorOfFloat.setDuration(300L);
        valueAnimatorOfFloat.setRepeatCount(1);
        SeedlingGuideLightView$getEndAnimator$endAnimator$1$1 seedlingGuideLightView$getEndAnimator$endAnimator$1$1 = new SeedlingGuideLightView$getEndAnimator$endAnimator$1$1(0);
        seedlingGuideLightView$getEndAnimator$endAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(seedlingGuideLightView$getEndAnimator$endAnimator$1$1);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.notification.customcard.guidetips.SeedlingGuideLightView$getEndAnimator$endAnimator$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SeedlingGuideLightView seedlingGuideLightView = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "SeedlingGuideLightView-->".concat("endAnimator onAnimationEnd, animatorListener=" + seedlingGuideLightView.animatorListener));
                }
                GuideTipsAnimatorListener guideTipsAnimatorListener = this.this$0.animatorListener;
                if (guideTipsAnimatorListener != null) {
                    ((CustomCardGuideTipsViewController) guideTipsAnimatorListener).onGuideTipsAnimatorEnd();
                }
                this.this$0.setVisibility(4);
            }
        });
        return valueAnimatorOfFloat;
    }

    private final Animator getStartAnimation() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(1000L);
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(2);
        SeedlingGuideLightView$getEndAnimator$endAnimator$1$1 seedlingGuideLightView$getEndAnimator$endAnimator$1$1 = new SeedlingGuideLightView$getEndAnimator$endAnimator$1$1(1);
        seedlingGuideLightView$getEndAnimator$endAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(seedlingGuideLightView$getEndAnimator$endAnimator$1$1);
        return valueAnimatorOfFloat;
    }

    public final void endAnimator() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "SeedlingGuideLightView-->endAnimator");
        }
        if (this.startAnimation.isRunning()) {
            this.startAnimation.cancel();
        }
        this.endAnimator.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.widget.ImageView*/.onDetachedFromWindow();
        this.startAnimation.cancel();
        this.endAnimator.cancel();
    }

    public final void removeAnimatorListener() {
        this.animatorListener = null;
    }

    public final void setAnimatorListener(GuideTipsAnimatorListener guideTipsAnimatorListener) {
        this.animatorListener = guideTipsAnimatorListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setPosition(Rect rect) {
        float translationX = getTranslationX() + (getWidth() / 2.0f) + getLeft();
        float translationY = getTranslationY() + (getHeight() / 2.0f) + getTop();
        float fExactCenterX = rect.exactCenterX() - translationX;
        float fExactCenterY = rect.exactCenterY() - translationY;
        setTranslationX(getTranslationX() + fExactCenterX);
        setTranslationY(getTranslationY() + fExactCenterY);
    }

    public final void startAnimator() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "SeedlingGuideLightView-->startAnimator");
        }
        this.startAnimation.start();
    }
}
