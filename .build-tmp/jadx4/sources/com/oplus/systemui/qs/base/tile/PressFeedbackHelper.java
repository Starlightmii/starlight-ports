package com.oplus.systemui.qs.base.tile;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import com.coui.appcompat.pressfeedback.COUIPressFeedbackHelper;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplusos.systemui.common.view.ViewExtKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PressFeedbackHelper {
    public static final PressFeedbackHelper INSTANCE = new PressFeedbackHelper();
    private static final PathInterpolator PRESS_FEEDBACK_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private static final PathInterpolator PRESS_RESUME_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class QsTilePressFeedback implements View.OnTouchListener {
        private final COUIPressFeedbackHelper feedbackHelper;
        public boolean mActionDown;

        public QsTilePressFeedback(View view) {
            this.feedbackHelper = new COUIPressFeedbackHelper(view, 0);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                this.mActionDown = true;
                this.feedbackHelper.executeFeedbackAnimator(true);
                return false;
            }
            if ((numValueOf == null || numValueOf.intValue() != 3) && (numValueOf == null || numValueOf.intValue() != 1)) {
                return false;
            }
            reset();
            return false;
        }

        public final void reset() {
            if (this.mActionDown) {
                this.mActionDown = false;
                this.feedbackHelper.executeFeedbackAnimator(false);
            }
        }

        public final void setTargetView(View view) {
            this.feedbackHelper.setTargetView(view);
        }
    }

    public static final QsTilePressFeedback attachPressFeedback(View view, View view2) {
        QsTilePressFeedback qsTilePressFeedback = new QsTilePressFeedback(view2);
        ViewExtKt.addOnTouchListener(view, qsTilePressFeedback, false);
        return qsTilePressFeedback;
    }

    public static final void clearPressFeedback(View view) {
        ViewExtKt.clearOnTouchListener(view);
    }

    public static final void onTouchPressEvent(MotionEvent motionEvent, QsTilePressFeedback qsTilePressFeedback) {
        if (qsTilePressFeedback != null) {
            qsTilePressFeedback.onTouch(null, motionEvent);
        }
    }

    public final ScaleAnimation generatePressAnimation(View view) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return null;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.92f, 1.0f, 0.92f, view.getWidth() / 2.0f, view.getHeight() / 2.0f);
        scaleAnimation.setDuration(200L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return scaleAnimation;
    }

    public final ScaleAnimation generatePressAnimation$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(View view, float f) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return null;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, f, 1.0f, f, view.getWidth() / 2.0f, view.getHeight() / 2.0f);
        scaleAnimation.setDuration(200L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return scaleAnimation;
    }

    public final AnimatorSet generatePressAnimationForProperty(View view) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, MultiAnimViewDelegate.PROPERTY_SCALE_X, 1.0f, 0.92f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, MultiAnimViewDelegate.PROPERTY_SCALE_Y, 1.0f, 0.92f);
        animatorSet.setDuration(200L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return animatorSet;
    }

    public final ValueAnimator generatePressAnimationRecord() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.92f);
        valueAnimatorOfFloat.setDuration(200L);
        valueAnimatorOfFloat.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return valueAnimatorOfFloat;
    }

    public final ScaleAnimation generateResumeAnimation(View view, float f) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return null;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, 1.0f, f, 1.0f, view.getWidth() / 2.0f, view.getHeight() / 2.0f);
        scaleAnimation.setDuration(340L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return scaleAnimation;
    }

    public final AnimatorSet generateResumeAnimationForProperty(View view, float f) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, MultiAnimViewDelegate.PROPERTY_SCALE_X, f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, MultiAnimViewDelegate.PROPERTY_SCALE_Y, f, 1.0f);
        animatorSet.setDuration(340L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return animatorSet;
    }

    public final float getGuaranteedAnimationValue(View view) {
        if (view == null) {
            Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
            return 0.0f;
        }
        if (view.getHeight() >= 600) {
            return 0.9944f;
        }
        return view.getHeight() >= 156 ? 0.972f : 0.992f;
    }

    public static final void attachPressFeedback(View view, QsTilePressFeedback qsTilePressFeedback) {
        ViewExtKt.addOnTouchListener(view, qsTilePressFeedback, false);
    }

    public final ValueAnimator generatePressAnimationRecord(long j, float f) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, f);
        valueAnimatorOfFloat.setDuration(j);
        valueAnimatorOfFloat.setInterpolator(PRESS_FEEDBACK_INTERPOLATOR);
        return valueAnimatorOfFloat;
    }

    public final ScaleAnimation generateResumeAnimation(View view, float f, long j) {
        if (view != null) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(f, 1.0f, f, 1.0f, view.getWidth() / 2.0f, view.getHeight() / 2.0f);
            scaleAnimation.setDuration(j);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setInterpolator(PRESS_RESUME_INTERPOLATOR);
            return scaleAnimation;
        }
        Path$.ExternalSyntheticBUOutline0.m("The given view is empty. Please provide a valid view.");
        return null;
    }
}
