package com.oplus.systemui.panelanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSpringAnimation$expandedFractionChangeListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ OplusSpringAnimation this$0;

    public final void onFractionChanged(float f) {
        OplusSpringAnimation oplusSpringAnimation = this.this$0;
        if (oplusSpringAnimation.isAnimationRunning) {
            float f2 = oplusSpringAnimation.targetValue;
            float f3 = oplusSpringAnimation.startRawFraction;
            if (f2 != f3) {
                float fCoerceIn = f2 == 1.0f ? RangesKt___RangesKt.coerceIn(f - f3, 0.0f, 1.0f) : f - f3;
                float f4 = oplusSpringAnimation.targetValue;
                float f5 = oplusSpringAnimation.startExpandFraction;
                f = (((f4 - f5) / (f4 - oplusSpringAnimation.startRawFraction)) * fCoerceIn) + f5;
            }
            oplusSpringAnimation.animatedValue = f * oplusSpringAnimation.notificationPanelViewController.getMaxPanelTransitionDistance();
            ArrayList arrayList = oplusSpringAnimation.updateListeners;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ValueAnimator.AnimatorUpdateListener animatorUpdateListener = (ValueAnimator.AnimatorUpdateListener) obj;
                if (animatorUpdateListener != null) {
                    animatorUpdateListener.onAnimationUpdate(oplusSpringAnimation);
                }
            }
        }
    }

    public final void onFractionEnd(float f) {
        OplusSpringAnimation oplusSpringAnimation = this.this$0;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusSpringAnimation", "onFractionEnd, endFraction=" + f + " isAnimationRunning " + oplusSpringAnimation.isAnimationRunning, null, 8);
        if (oplusSpringAnimation.isAnimationRunning) {
            ArrayList<Animator.AnimatorListener> listeners = oplusSpringAnimation.getListeners();
            int size = listeners.size();
            int i = 0;
            while (i < size) {
                Animator.AnimatorListener animatorListener = listeners.get(i);
                i++;
                animatorListener.onAnimationEnd(oplusSpringAnimation);
            }
            oplusSpringAnimation.isAnimationRunning = false;
            oplusSpringAnimation.removeCallback();
        }
    }
}
