package com.oplus.systemui.statusbar.pipeline.battery.ui.binder;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryViewBinder$setupLayoutTransition$layoutTransition$1$1 implements LayoutTransition.TransitionListener {
    public /* synthetic */ String $moduleTag;
    public /* synthetic */ ViewGroup $view;

    @Override // android.animation.LayoutTransition.TransitionListener
    public final void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
        if (Intrinsics.areEqual(view, this.$view)) {
            BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
            String str = this.$moduleTag;
            batteryViewBinder.getClass();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "BatteryViewBinder-->".concat(str + " stopCoordinateTransition: " + view));
            }
            Object tag = view.getTag(2131365596);
            Animator animator = tag instanceof Animator ? (Animator) tag : null;
            if (animator != null) {
                animator.cancel();
            }
        }
    }

    @Override // android.animation.LayoutTransition.TransitionListener
    public final void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
        if (Intrinsics.areEqual(view, this.$view)) {
            BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
            ViewGroup viewGroup2 = this.$view;
            String str = this.$moduleTag;
            batteryViewBinder.getClass();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "BatteryViewBinder-->".concat(str + " startCoordinateTransition: " + viewGroup2 + " " + viewGroup2.getMeasuredWidth() + " " + viewGroup2.getWidth()));
            }
            Object tag = viewGroup2.getTag(2131365596);
            Animator animator = tag instanceof Animator ? (Animator) tag : null;
            if (animator != null) {
                animator.cancel();
            }
            ViewParent parent = viewGroup2.getParent();
            ViewGroup viewGroup3 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup3 == null) {
                return;
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(200L);
            BatteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1 batteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1 = new BatteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1();
            batteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1.$container = viewGroup3;
            batteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1.$changedView = viewGroup2;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(batteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1);
            BatteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1 batteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1 = new BatteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1();
            batteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1.$container$inlined = viewGroup3;
            batteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1.$changedView$inlined = viewGroup2;
            batteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1.$moduleTag$inlined = str;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addListener(batteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1);
            viewGroup2.setTag(2131365596, valueAnimatorOfFloat);
            valueAnimatorOfFloat.start();
        }
    }
}
