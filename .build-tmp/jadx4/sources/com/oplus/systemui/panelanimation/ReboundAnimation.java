package com.oplus.systemui.panelanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ReboundAnimation implements LevelPositionAlgorithm.LevelPosition {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ReboundAnimation";
    private final Set<LevelPositionAlgorithm.LevelPositionAnimationListener> callbacks = new LinkedHashSet();
    private ValueAnimator reboundAnimator;
    private NotificationStackScrollLayout stackScrollLayout;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static final void access$notifyAnimationEnd(ReboundAnimation reboundAnimation) {
        synchronized (reboundAnimation.callbacks) {
            Iterator<T> it = reboundAnimation.callbacks.iterator();
            while (it.hasNext()) {
                ((LevelPositionAlgorithm.LevelPositionAnimationListener) it.next()).onAnimationEnd();
            }
        }
    }

    public void addCallback(LevelPositionAlgorithm.LevelPositionAnimationListener levelPositionAnimationListener) {
        synchronized (this.callbacks) {
            this.callbacks.add(levelPositionAnimationListener);
        }
    }

    public final void cancelReboundAnimation() {
        ValueAnimator valueAnimator = this.reboundAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public final void initStackScrollLayout(NotificationStackScrollLayout notificationStackScrollLayout) {
        if (notificationStackScrollLayout != null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "initStackScrollLayout", null, 8);
            this.stackScrollLayout = notificationStackScrollLayout;
        }
    }

    public final boolean isInReboundAnimation() {
        ValueAnimator valueAnimator = this.reboundAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void removeCallback(LevelPositionAlgorithm.LevelPositionAnimationListener levelPositionAnimationListener) {
        synchronized (this.callbacks) {
            this.callbacks.remove(levelPositionAnimationListener);
        }
    }

    public final void startAnimation(float f, float f2) {
        double dAbs;
        if (f == 0.0f) {
            return;
        }
        if (this.reboundAnimator == null) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(new float[0]);
            this.reboundAnimator = valueAnimatorOfFloat;
            if (valueAnimatorOfFloat != null) {
                valueAnimatorOfFloat.setDuration(800L);
            }
            ValueAnimator valueAnimator = this.reboundAnimator;
            if (valueAnimator != null) {
                ReboundAnimation$initAnimation$1 reboundAnimation$initAnimation$1 = new ReboundAnimation$initAnimation$1();
                reboundAnimation$initAnimation$1.this$0 = this;
                VarHandle.storeStoreFence();
                valueAnimator.addUpdateListener(reboundAnimation$initAnimation$1);
            }
            ValueAnimator valueAnimator2 = this.reboundAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.panelanimation.ReboundAnimation$initAnimation$2
                    public boolean cancelled;

                    public final boolean getCancelled() {
                        return this.cancelled;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationCancel(Animator animator) {
                        LogUtil.i$default(LogUtil.TAG_COMMON, "ReboundAnimation", "reboundAnimator onAnimationCancel", null, 8);
                        this.cancelled = true;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        LogUtil.i$default(LogUtil.TAG_COMMON, "ReboundAnimation", KeyguardLogger$.ExternalSyntheticOutline0.m("reboundAnimator onAnimationEnd, cancelled: ", this.cancelled), null, 8);
                        ReboundAnimation.access$notifyAnimationEnd(this.this$0);
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        this.cancelled = false;
                    }

                    public final void setCancelled(boolean z) {
                        this.cancelled = z;
                    }
                });
            }
        }
        ValueAnimator valueAnimator3 = this.reboundAnimator;
        if (valueAnimator3 != null) {
            if (f2 > 0.0f) {
                dAbs = 0.0d;
            } else {
                dAbs = Math.abs(f2);
                if (dAbs > 20000.0d) {
                    dAbs = 20000.0d;
                }
            }
            valueAnimator3.setInterpolator(new SpringInterpolator(0.6d, 0.0d, dAbs, 6666.0f));
        }
        ValueAnimator valueAnimator4 = this.reboundAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.setFloatValues(f, 0.0f);
        }
        ValueAnimator valueAnimator5 = this.reboundAnimator;
        if (valueAnimator5 != null && valueAnimator5.isRunning()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "startAnimation, cancel rebound animator", null, 8);
            ValueAnimator valueAnimator6 = this.reboundAnimator;
            if (valueAnimator6 != null) {
                valueAnimator6.cancel();
            }
        }
        ValueAnimator valueAnimator7 = this.reboundAnimator;
        if (valueAnimator7 != null) {
            valueAnimator7.start();
        }
    }
}
