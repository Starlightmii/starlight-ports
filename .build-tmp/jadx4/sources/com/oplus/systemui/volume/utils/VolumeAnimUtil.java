package com.oplus.systemui.volume.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.PathInterpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.volume.OplusVolumeSeekBar$$ExternalSyntheticLambda4;
import com.oplus.util.OplusPlatformLevelUtils;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class VolumeAnimUtil {
    public static final int CUR_ANIM_LEVEL;
    public static final long DURATION_MULT_ROWS_SHOW;
    public static final long DURATION_ONE_ROW_SHOW;
    public static final long DURATION_ROW_HIDE;
    public static final long DURATION_UPDATE_ANIMATION;
    public static final PathInterpolator SUPER_VOLUME_MAIN_INTERPOLATOR;
    public static final PathInterpolator SUPER_VOLUME_SLIDER_COLOR_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_DEFORMATION_BACK_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_DEFORMATION_MOVE_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_END_THICK_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_HIDE_INTERPOLATOR;
    public static final PathInterpolator VOLUME_MAIN_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_SHOW_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_START_THICK_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_TOUCH_DOWN_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_TOUCH_UP_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_TRANS_BACK_INTERPOLATOR;
    public static final COUISpringInterpolator VOLUME_TRANS_MOVE_INTERPOLATOR;
    public static float sAnimationScale;

    static {
        new PathInterpolator(0.21f, 0.07f, 0.0f, 1.0f);
        new PathInterpolator(0.22f, 0.3f, 0.51f, 0.85f);
        new PathInterpolator(0.21f, 0.06f, 0.0f, 1.0f);
        new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
        new PathInterpolator(0.17f, 0.17f, 0.83f, 0.83f);
        new PathInterpolator(0.27f, 0.0f, 0.38f, 1.0f);
        new PathInterpolator(0.28f, 0.21f, 0.3f, 1.0f);
        new PathInterpolator(0.33f, 0.0f, 0.14f, 1.0f);
        new PathInterpolator(0.27f, 0.06f, 0.5f, 0.75f);
        VOLUME_MAIN_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        SUPER_VOLUME_MAIN_INTERPOLATOR = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);
        new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        SUPER_VOLUME_SLIDER_COLOR_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        int animLevel = ScrimUtil.getAnimLevel();
        CUR_ANIM_LEVEL = animLevel;
        VOLUME_SHOW_INTERPOLATOR = animLevel > 3 ? new COUISpringInterpolator(0.9d, 0.0d) : new COUISpringInterpolator(0.7d, 0.2d);
        VOLUME_HIDE_INTERPOLATOR = animLevel > 3 ? new COUISpringInterpolator(0.9d, 0.0d) : new COUISpringInterpolator(0.7d, 0.0d);
        DURATION_MULT_ROWS_SHOW = animLevel > 3 ? 300L : 900L;
        DURATION_ONE_ROW_SHOW = animLevel > 3 ? 300L : 700L;
        DURATION_ROW_HIDE = animLevel > 3 ? 250L : 500L;
        DURATION_UPDATE_ANIMATION = animLevel > 3 ? 80L : 300L;
        VOLUME_TOUCH_DOWN_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_TOUCH_UP_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_DEFORMATION_MOVE_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_DEFORMATION_BACK_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_START_THICK_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_END_THICK_INTERPOLATOR = new COUISpringInterpolator(0.6d, 0.3d);
        VOLUME_TRANS_MOVE_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        VOLUME_TRANS_BACK_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.1d);
        sAnimationScale = 1.0f;
    }

    public static boolean enableComplexAnim(Context context) {
        if (sAnimationScale <= 0.0f) {
            return false;
        }
        int i = CUR_ANIM_LEVEL;
        return i == 1 || i == 2 || OplusPlatformLevelUtils.getInstance(context).getPlatformLevel(2) == 3;
    }

    public static void resetShowOrHideAnimateView(View view, float f, boolean z, int i, Boolean bool) {
        if (view == null) {
            return;
        }
        boolean z2 = (i & 2) != 0;
        if (z) {
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            view.setAlpha(1.0f);
        } else {
            view.setTranslationX(f);
            view.setScaleX(0.5f);
            view.setScaleY(0.5f);
            if (z2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(1.0f);
            }
        }
        if (bool.booleanValue()) {
            view.setTranslationY(0.0f);
        }
    }

    public static ViewPropertyAnimator startViewPropertyAnimation(View view, long j, long j2, COUISpringInterpolator cOUISpringInterpolator, float f, float f2, float f3, float f4, Runnable runnable, Runnable runnable2, OplusVolumeSeekBar$$ExternalSyntheticLambda4 oplusVolumeSeekBar$$ExternalSyntheticLambda4, int i) {
        if (view == null) {
            return null;
        }
        ViewPropertyAnimator interpolator = view.animate().setStartDelay(j).setDuration(j2).setInterpolator(cOUISpringInterpolator);
        interpolator.translationX(f);
        if ((i & 2) != 0) {
            interpolator.translationY(f2);
        }
        if ((i & 4) != 0) {
            interpolator.scaleX(f3).scaleY(f3);
        }
        if ((i & 8) != 0) {
            interpolator.alpha(f4);
        }
        if (runnable != null) {
            interpolator.withStartAction(runnable);
        }
        if (runnable2 != null) {
            interpolator.withEndAction(runnable2);
        }
        interpolator.setUpdateListener(oplusVolumeSeekBar$$ExternalSyntheticLambda4);
        interpolator.start();
        return interpolator;
    }
}
