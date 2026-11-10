package com.oplus.systemui.panelanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSpringAnimation extends ValueAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSpringAnimation";
    public float animatedValue;
    private final OplusSpringAnimation$expandedFractionChangeListener$1 expandedFractionChangeListener;
    public boolean isAnimationRunning;
    private final NotificationPanelViewController notificationPanelViewController;
    private final OplusPanelAnimationExImpl oplusPanelAnimationExImpl;
    public final float startExpandFraction;
    public final float startRawFraction;
    public final float targetValue;
    private ArrayList<ValueAnimator.AnimatorUpdateListener> updateListeners = new ArrayList<>();
    public final float vel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSpringAnimation(float f, float f2, float f3, OplusPanelAnimationExImpl oplusPanelAnimationExImpl, float f4, NotificationPanelViewController notificationPanelViewController) {
        this.targetValue = f;
        this.startRawFraction = f2;
        this.startExpandFraction = f3;
        this.oplusPanelAnimationExImpl = oplusPanelAnimationExImpl;
        this.vel = f4;
        this.notificationPanelViewController = notificationPanelViewController;
        OplusSpringAnimation$expandedFractionChangeListener$1 oplusSpringAnimation$expandedFractionChangeListener$1 = new OplusSpringAnimation$expandedFractionChangeListener$1();
        oplusSpringAnimation$expandedFractionChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.expandedFractionChangeListener = oplusSpringAnimation$expandedFractionChangeListener$1;
    }

    @Override // android.animation.ValueAnimator
    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.updateListeners.add(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void cancel() {
        ArrayList<Animator.AnimatorListener> listeners = getListeners();
        int size = listeners.size();
        int i = 0;
        while (i < size) {
            Animator.AnimatorListener animatorListener = listeners.get(i);
            i++;
            animatorListener.onAnimationCancel(this);
        }
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.oplusPanelAnimationExImpl;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "PanelAnimationEx", OplusLockIconView$.ExternalSyntheticOutline0.m("animationCancel--isBreakCollapse :", " ", oplusPanelAnimationExImpl.isBreakCollapse), null, 8);
        oplusPanelAnimationExImpl.getPanelExpandFraction().cancel$1(oplusPanelAnimationExImpl.isBreakCollapse);
        oplusPanelAnimationExImpl.getTranslationYValue().cancel();
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void end() {
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.oplusPanelAnimationExImpl;
        oplusPanelAnimationExImpl.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "PanelAnimationEx", "animationEnd", null, 8);
        oplusPanelAnimationExImpl.getPanelExpandFraction().skipToEnd();
        oplusPanelAnimationExImpl.getTranslationYValue().skipToEnd();
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(this.animatedValue);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final boolean isRunning() {
        return this.isAnimationRunning;
    }

    public final void removeCallback() {
        this.oplusPanelAnimationExImpl.getPanelExpandFraction().removeCallback((PanelAnimationEx.ExpandedFractionChangeListener) this.expandedFractionChangeListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void start() {
        float f = this.targetValue;
        float f2 = this.vel;
        boolean z = this.isAnimationRunning;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("start targetValue ", f, " vel ", f2, " isAnimationRunning ");
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        this.oplusPanelAnimationExImpl.getPanelExpandFraction().addCallback((PanelAnimationEx.ExpandedFractionChangeListener) this.expandedFractionChangeListener);
        this.isAnimationRunning = true;
        this.oplusPanelAnimationExImpl.expandedTo(this.targetValue, this.vel);
        ArrayList<Animator.AnimatorListener> listeners = getListeners();
        int size = listeners.size();
        int i = 0;
        while (i < size) {
            Animator.AnimatorListener animatorListener = listeners.get(i);
            i++;
            animatorListener.onAnimationStart(this);
        }
    }
}
