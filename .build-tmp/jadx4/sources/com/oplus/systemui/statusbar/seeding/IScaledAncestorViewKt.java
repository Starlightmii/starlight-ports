package com.oplus.systemui.statusbar.seeding;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class IScaledAncestorViewKt {
    public static final void applyAncestorViewScale(final View view, float f, boolean z, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, final Function0 function0) {
        Object tag = view.getTag(2131365586);
        ValueAnimator valueAnimator = tag instanceof ValueAnimator ? (ValueAnimator) tag : null;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator.removeAllListeners();
            valueAnimator.removeAllUpdateListeners();
        }
        if (!z) {
            view.setScaleX(f);
            view.setScaleY(f);
            view.setTag(2131365586, null);
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(view.getScaleX(), f);
        valueAnimatorOfFloat.setDuration(200L);
        IScaledAncestorViewKt$applyAncestorViewScale$animator$1$1 iScaledAncestorViewKt$applyAncestorViewScale$animator$1$1 = new IScaledAncestorViewKt$applyAncestorViewScale$animator$1$1();
        iScaledAncestorViewKt$applyAncestorViewScale$animator$1$1.$this_applyAncestorViewScale = view;
        iScaledAncestorViewKt$applyAncestorViewScale$animator$1$1.$animatorUpdateListener = animatorUpdateListener;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(iScaledAncestorViewKt$applyAncestorViewScale$animator$1$1);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.seeding.IScaledAncestorViewKt$applyAncestorViewScale$animator$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                view.setTag(2131365586, null);
                Function0 function1 = function0;
                if (function1 != null) {
                    function1.invoke();
                }
            }
        });
        view.setTag(2131365586, valueAnimatorOfFloat);
        valueAnimatorOfFloat.start();
    }

    public static /* synthetic */ void applyAncestorViewScale$default(View view, float f, boolean z, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i) {
        if ((i & 4) != 0) {
            animatorUpdateListener = null;
        }
        applyAncestorViewScale(view, f, z, animatorUpdateListener, null);
    }
}
