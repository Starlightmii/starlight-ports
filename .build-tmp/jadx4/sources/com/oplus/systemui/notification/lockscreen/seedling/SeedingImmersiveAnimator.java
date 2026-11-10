package com.oplus.systemui.notification.lockscreen.seedling;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.plugins.anim.AnimListener;
import com.android.systemui.plugins.anim.SpringAnimWrapper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import java.util.ArrayList;
import java.util.Iterator;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedingImmersiveAnimator extends Animator implements AnimListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeedingImmersiveBgAnimator";
    public boolean isRunning;
    private final SpringAnimWrapper stateAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SeedingImmersiveAnimator(SpringAnimWrapper springAnimWrapper) {
        this.stateAnimator = springAnimWrapper;
    }

    public final void animateToState(int i, int i2, int i3, int i4, Rect rect, Rect rect2) {
        Bundle bundleM = CommunalWidgetHost$.ExternalSyntheticOutline0.m(i, "animType");
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        companion.getClass();
        if (i2 == 3) {
            i2 = 0;
        }
        bundleM.putInt("fromState", i2);
        companion.getClass();
        if (i3 == 3) {
            i3 = 0;
        }
        bundleM.putInt("toState", i3);
        bundleM.putInt("cardSize", i4);
        if (rect != null) {
            bundleM.putParcelable("cardBounds", rect);
        }
        if (rect2 != null) {
            bundleM.putParcelable("capsuleBounds", rect2);
        }
        this.isRunning = true;
        this.stateAnimator.animateToState(bundleM);
    }

    @Override // android.animation.Animator
    public final void cancel() {
        Log.d(TAG, "cancel:");
        this.isRunning = false;
        Iterator<Animator.AnimatorListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAnimationCancel(this);
        }
        this.stateAnimator.cancel();
    }

    @Override // android.animation.Animator
    public long getDuration() {
        return 300L;
    }

    @Override // android.animation.Animator
    public long getStartDelay() {
        return 0L;
    }

    public final SpringAnimWrapper getStateAnimator() {
        return this.stateAnimator;
    }

    @Override // android.animation.Animator
    public final boolean isRunning() {
        return this.isRunning;
    }

    public void onAnimationEnd(SpringAnimWrapper springAnimWrapper, boolean z, float f, float f2) {
        Log.d(TAG, "onAnimationEnd: " + springAnimWrapper + ", " + z);
        this.isRunning = false;
        Iterator<Animator.AnimatorListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAnimationEnd(this);
        }
        this.stateAnimator.removeListener(this);
    }

    public void onAnimationUpdate(SpringAnimWrapper springAnimWrapper, float f, float f2) {
    }

    @Override // android.animation.Animator
    public Animator setDuration(long j) {
        return this;
    }

    @Override // android.animation.Animator
    public void setInterpolator(TimeInterpolator timeInterpolator) {
    }

    @Override // android.animation.Animator
    public void setStartDelay(long j) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.animation.Animator
    public final void start() {
        Log.d(TAG, "start:" + this.stateAnimator);
        if (this.isRunning) {
            Path$.ExternalSyntheticBUOutline0.m$1("has animator is running ");
            return;
        }
        this.isRunning = true;
        this.stateAnimator.addListener(this);
        ArrayList<Animator.AnimatorListener> listeners = getListeners();
        int size = listeners.size();
        int i = 0;
        while (i < size) {
            Animator.AnimatorListener animatorListener = listeners.get(i);
            i++;
            animatorListener.onAnimationStart(this);
        }
        this.stateAnimator.start();
    }
}
