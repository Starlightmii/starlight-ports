package com.oplus.zoom.ui.floathandle;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;
import android.view.View;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleAnimationUtil {
    protected static final float ALPHA_END = 1.0f;
    private static final float ALPHA_ICON_DISAPPEAR_END = 0.0f;
    private static final float ALPHA_ICON_DISAPPEAR_START = 1.0f;
    private static final float ALPHA_ICON_ENTER_END = 1.0f;
    private static final float ALPHA_ICON_ENTER_START = 0.0f;
    protected static final float ALPHA_START = 0.0f;
    protected static final int FRAME_END = 59;
    protected static final int FRAME_FROM_ARROW_TO_LINE_END = 27;
    protected static final int FRAME_FROM_ARROW_TO_LINE_START = 0;
    protected static final int FRAME_FROM_LINE_TO_ARROW_END = 59;
    protected static final int FRAME_FROM_LINE_TO_ARROW_START = 27;
    protected static final int FRAME_START = 0;
    protected static final int OFFSET_TIME_ENTER = 167;
    protected static final float SCALE_END = 1.0f;
    private static final float SCALE_ICON_DISAPPEAR_END = 0.5f;
    private static final float SCALE_ICON_DISAPPEAR_START = 1.0f;
    private static final float SCALE_ICON_ENTER_END = 1.0f;
    private static final float SCALE_ICON_ENTER_START = 0.5f;
    protected static final float SCALE_START = 0.5f;
    private static final String TAG = "FloatHandle";
    private static final int TIME_DURATION_ICON_DISAPPEAR = 250;
    private static final int TIME_DURATION_ICON_ENTER = 250;
    protected static final int TIME_FORM_ARROW_TO_LINE = 450;
    protected static final int TIME_FORM_LINE_TO_ARROW = 550;
    protected static final int TIME_PAN_DEFAULT = 100;
    protected static final int TIME_PAN_FULL_TO_HALF_HIDDEN = 450;
    protected static final int TIME_PAN_FULL_TO_NONE = 450;
    protected static final int TIME_PAN_HALF_HIDDEN_TO_FULL = 550;
    protected static final int TIME_PAN_HALF_HIDDEN_TO_NONE = 120;
    protected static final int TIME_PAN_NONE_TO_FULL = 550;
    protected static final int TIME_PAN_NONE_TO_HIDE_HIDDEN = 120;

    public static int computeAnimationTimeToPan(int i, int i2) {
        if (i == 1) {
            if (i2 == 2) {
                return 550;
            }
            return i2 == 4 ? 120 : 100;
        }
        if (i == 2) {
            return (i2 == 4 || i2 == 1) ? 450 : 100;
        }
        if (i != 4) {
            return 100;
        }
        if (i2 == 2) {
            return 550;
        }
        return i2 == 1 ? 120 : 100;
    }

    public static int computeMaxDistanceToScreen(FloatHandleStatus floatHandleStatus, int i, int i2, boolean z) {
        if (i2 == 1) {
            return floatHandleStatus.getMaxDistanceToScreenInNone();
        }
        if (i2 == 2) {
            int distanceToRebound = floatHandleStatus.getDistanceToRebound();
            return z ? distanceToRebound - floatHandleStatus.getDistanceToRebound() : distanceToRebound;
        }
        if (i2 != 4) {
            return 0;
        }
        int maxDistanceToScreenInHalfHidden = floatHandleStatus.getMaxDistanceToScreenInHalfHidden();
        return z ? floatHandleStatus.getDistanceToRebound() + maxDistanceToScreenInHalfHidden : maxDistanceToScreenInHalfHidden;
    }

    public static Animator getNewIconEnterAnimation(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f)).with(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 0.5f, 1.0f)).with(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 0.5f, 1.0f));
        animatorSet.setDuration(250L);
        animatorSet.setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.1f, 1.0f));
        return animatorSet;
    }

    public static Animator getOriginIconDisappearAnimation(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f)).with(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 1.0f, 0.5f)).with(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 1.0f, 0.5f));
        animatorSet.setDuration(250L);
        animatorSet.setInterpolator(new PathInterpolator(0.3f, 0.0f, 1.0f, 1.0f));
        return animatorSet;
    }

    public static ValueAnimator getPanAnimation(FloatHandleStatus floatHandleStatus, int i, int i2, int i3, int i4, boolean z) {
        int iComputeAnimationTimeToPan = computeAnimationTimeToPan(i3, i4);
        ValueAnimator valueAnimatorOfInt = i2 == 1 ? ValueAnimator.ofInt(i, i - (computeMaxDistanceToScreen(floatHandleStatus, i3, i4, z) + i)) : ValueAnimator.ofInt(i, (computeMaxDistanceToScreen(floatHandleStatus, i3, i4, z) - (floatHandleStatus.getMaxDistanceToScreenInNone() - (floatHandleStatus.getScreenWidth() - i))) + i);
        valueAnimatorOfInt.setDuration(iComputeAnimationTimeToPan);
        valueAnimatorOfInt.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f));
        return valueAnimatorOfInt;
    }

    public static ValueAnimator getReboundAnimation(FloatHandleStatus floatHandleStatus, int i, int i2, int i3, int i4, boolean z) {
        ValueAnimator valueAnimatorOfFloat;
        int i5 = z ? 550 : 450;
        int distanceToRebound = z ? floatHandleStatus.getDistanceToRebound() : -floatHandleStatus.getDistanceToRebound();
        if (i2 == 1) {
            int i6 = -computeMaxDistanceToScreen(floatHandleStatus, i3, i4, true);
            valueAnimatorOfFloat = ValueAnimator.ofFloat(i6, distanceToRebound + i6);
        } else {
            int iComputeMaxDistanceToScreen = computeMaxDistanceToScreen(floatHandleStatus, i3, i4, true);
            valueAnimatorOfFloat = ValueAnimator.ofFloat(floatHandleStatus.getScreenWidth() - (floatHandleStatus.getMaxDistanceToScreenInNone() - iComputeMaxDistanceToScreen), (floatHandleStatus.getScreenWidth() - (floatHandleStatus.getMaxDistanceToScreenInNone() - iComputeMaxDistanceToScreen)) - distanceToRebound);
        }
        valueAnimatorOfFloat.setDuration(i5);
        valueAnimatorOfFloat.setInterpolator(new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f));
        return valueAnimatorOfFloat;
    }
}
