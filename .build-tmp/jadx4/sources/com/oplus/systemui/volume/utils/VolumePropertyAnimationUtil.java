package com.oplus.systemui.volume.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;
import com.coui.appcompat.animation.COUISpringInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumePropertyAnimationUtil {
    public static final VolumePropertyAnimationUtil INSTANCE = new VolumePropertyAnimationUtil();
    private static final COUISpringInterpolator VOLUME_PANEL_EXPAND_INTERPOLATOR = new COUISpringInterpolator(0.3d, 0.0d);
    private static final COUISpringInterpolator VOLUME_ITEM_EXPAND_INTERPOLATOR = new COUISpringInterpolator(0.47d, 0.22d);
    private static final COUISpringInterpolator VOLUME_ITEM_COLLAPSE_INTERPOLATOR = new COUISpringInterpolator(0.47d, 0.0d);
    private static final COUISpringInterpolator VOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR = new COUISpringInterpolator(0.6d, 0.0d);

    public static final void setPivotYBottom(View view) {
        view.setPivotY(view.getHeight() * 0.9f);
    }

    public static final void setPivotYTop(View view) {
        view.setPivotY(0.0f);
    }

    public static final ViewPropertyAnimator viewPropertyAnimation(View view, long j, long j2, Interpolator interpolator, PropertyAnimationParams propertyAnimationParams, PropertyAnimationParams propertyAnimationParams2, Runnable runnable, Runnable runnable2, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        if (view == null) {
            return null;
        }
        view.setTranslationX(propertyAnimationParams.getTranslationX());
        view.setTranslationY(propertyAnimationParams.getTranslationY());
        view.setAlpha(propertyAnimationParams.getAlpha());
        view.setScaleX(propertyAnimationParams.getScale());
        view.setScaleY(propertyAnimationParams.getScale());
        ViewPropertyAnimator duration = view.animate().setStartDelay(j).setDuration(j2);
        if (interpolator != null) {
            duration.setInterpolator(interpolator);
        }
        if (runnable != null) {
            duration.withStartAction(runnable);
        }
        if (runnable2 != null) {
            duration.withEndAction(runnable2);
        }
        if (animatorUpdateListener != null) {
            duration.setUpdateListener(animatorUpdateListener);
        }
        duration.translationX(propertyAnimationParams2.getTranslationX());
        duration.translationY(propertyAnimationParams2.getTranslationY());
        duration.alpha(propertyAnimationParams2.getAlpha());
        duration.scaleX(propertyAnimationParams2.getScale());
        duration.scaleY(propertyAnimationParams2.getScale());
        duration.start();
        return duration;
    }

    public final COUISpringInterpolator getVOLUME_ITEM_COLLAPSE_INTERPOLATOR() {
        return VOLUME_ITEM_COLLAPSE_INTERPOLATOR;
    }

    public final COUISpringInterpolator getVOLUME_ITEM_EXPAND_INTERPOLATOR() {
        return VOLUME_ITEM_EXPAND_INTERPOLATOR;
    }

    public final COUISpringInterpolator getVOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR() {
        return VOLUME_ITEM_INTERRUPT_COLLAPSE_INTERPOLATOR;
    }

    public final COUISpringInterpolator getVOLUME_PANEL_EXPAND_INTERPOLATOR() {
        return VOLUME_PANEL_EXPAND_INTERPOLATOR;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PropertyAnimationParams {
        public float alpha;
        public float scale;
        public float translationX;
        public float translationY;

        public PropertyAnimationParams(float f, float f2, float f3, float f4) {
            this.translationX = f;
            this.translationY = f2;
            this.scale = f3;
            this.alpha = f4;
        }

        public final PropertyAnimationParams copy(float f, float f2, float f3, float f4) {
            return new PropertyAnimationParams(f, f2, f3, f4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PropertyAnimationParams)) {
                return false;
            }
            PropertyAnimationParams propertyAnimationParams = (PropertyAnimationParams) obj;
            return Float.compare(this.translationX, propertyAnimationParams.translationX) == 0 && Float.compare(this.translationY, propertyAnimationParams.translationY) == 0 && Float.compare(this.scale, propertyAnimationParams.scale) == 0 && Float.compare(this.alpha, propertyAnimationParams.alpha) == 0;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final float getScale() {
            return this.scale;
        }

        public final float getTranslationX() {
            return this.translationX;
        }

        public final float getTranslationY() {
            return this.translationY;
        }

        public final int hashCode() {
            return Float.hashCode(this.alpha) + ChangeSize$.ExternalSyntheticOutline0.m(this.scale, ChangeSize$.ExternalSyntheticOutline0.m(this.translationY, Float.hashCode(this.translationX) * 31, 31), 31);
        }

        public final void setAlpha(float f) {
            this.alpha = f;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final void setTranslationX(float f) {
            this.translationX = f;
        }

        public final void setTranslationY(float f) {
            this.translationY = f;
        }

        public String toString() {
            float f = this.translationX;
            float f2 = this.translationY;
            return SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("PropertyAnimationParams(translationX=", f, ", translationY=", f2, ", scale="), this.scale, ", alpha=", this.alpha, ")");
        }

        public /* synthetic */ PropertyAnimationParams(float f, float f2) {
            this(0.0f, 0.0f, f, f2);
        }
    }
}
