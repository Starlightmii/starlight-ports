package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.widget.TextSwitcher;
import com.coui.appcompat.animation.COUISpringInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewAnimatorLabel {
    public static final Companion Companion = new Companion();
    private static final COUISpringInterpolator LABEL_ACTIVE_INTERPOLATOR = new COUISpringInterpolator(0.9d, 0.0d);
    private final Context context;
    private AnimatorSet labelAnimatorSet;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSHighlightTileViewAnimatorLabel(Context context) {
        this.context = context;
    }

    public static ObjectAnimator createObjectAnimator(View view, Property property, float f, float f2, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, f, f2);
        objectAnimatorOfFloat.setDuration(!z ? 350L : 400L);
        objectAnimatorOfFloat.setInterpolator(LABEL_ACTIVE_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    public final void startLabelAnim(TextSwitcher textSwitcher, TextSwitcher textSwitcher2, float f, boolean z, AnimatorListenerAdapter animatorListenerAdapter) {
        if (textSwitcher == null || textSwitcher2 == null) {
            return;
        }
        AnimatorSet animatorSet = this.labelAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        float translationY = textSwitcher.getTranslationY();
        float translationY2 = textSwitcher2.getTranslationY();
        float alpha = textSwitcher2.getAlpha();
        Property property = View.TRANSLATION_Y;
        ObjectAnimator objectAnimatorCreateObjectAnimator = createObjectAnimator(textSwitcher, property, translationY, z ? 0.0f : f, z);
        if (z) {
            f = 0.0f;
        }
        animatorSet2.playTogether(objectAnimatorCreateObjectAnimator, createObjectAnimator(textSwitcher2, property, translationY2, f, z), createObjectAnimator(textSwitcher2, View.ALPHA, alpha, z ? 1.0f : 0.0f, z));
        animatorSet2.setStartDelay(z ? 17L : 0L);
        animatorSet2.addListener(animatorListenerAdapter);
        animatorSet2.start();
        this.labelAnimatorSet = animatorSet2;
    }
}
