package com.oplus.systemui.shade;

import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.util.SeparateModeHelper;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelSeparateAnimationManagerImpl implements NotificationPanelSeparateAnimationManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPanelSeparateAnimation";
    public static float compensationFactor = 1.0f;
    public boolean forbidDelayWhenInterrupt;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final float getCompensationFactor() {
            return NotificationPanelSeparateAnimationManagerImpl.compensationFactor;
        }

        public final void setCompensationFactor(float f) {
            NotificationPanelSeparateAnimationManagerImpl.compensationFactor = f;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelSeparateAnimationManagerImpl$scheduleHideAnimationsWithDelay$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Function2 $onAnimationStart;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 $runAnimations;
        public /* synthetic */ View $view;
        public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.$runAnimations.invoke(NotificationPanelSeparateAnimationManagerImpl.getHideAlphaAnimation(this.$view));
                    Function2 function2 = this.$onAnimationStart;
                    if (function2 != null) {
                        function2.invoke(this.$view, AnimationType.ALPHA);
                    }
                    break;
                default:
                    this.$runAnimations.invoke(NotificationPanelSeparateAnimationManagerImpl.getHideAlphaAnimation(this.$view));
                    this.$runAnimations.invoke(NotificationPanelSeparateAnimationManagerImpl.getHideScaleAnimation(this.$view));
                    Function2 function3 = this.$onAnimationStart;
                    if (function3 != null) {
                        function3.invoke(this.$view, AnimationType.ALPHA);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelSeparateAnimationManagerImpl$scheduleShowAnimationsWithDelay$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02831 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Function1 $runAnimations;
        public /* synthetic */ View $view;
        public /* synthetic */ NotificationPanelSeparateAnimationManagerImpl this$0;

        public /* synthetic */ RunnableC02831(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getShowAlphaAnimation(this.$view));
                    break;
                case 1:
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getShowScaleAnimation(this.$view));
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getShowSpacingAnimation(this.$view));
                    break;
                case 2:
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getShowAlphaAnimation(this.$view));
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getShowScaleAnimation(this.$view));
                    break;
                default:
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getHideScaleAnimation(this.$view));
                    ((NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0) this.$runAnimations).invoke(NotificationPanelSeparateAnimationManagerImpl.getHideSpacingAnimation(this.$view));
                    break;
            }
        }
    }

    public static boolean areHideAnimationsRunning(View view) {
        COUISpringAnimation hideScaleAnimation;
        COUISpringAnimation hideSpacingAnimation;
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        return hideAlphaAnimation != null && ((COUIDynamicAnimation) hideAlphaAnimation).mRunning && (hideScaleAnimation = getHideScaleAnimation(view)) != null && ((COUIDynamicAnimation) hideScaleAnimation).mRunning && (hideSpacingAnimation = getHideSpacingAnimation(view)) != null && ((COUIDynamicAnimation) hideSpacingAnimation).mRunning;
    }

    public static boolean areShowAnimationsRunning(View view) {
        COUISpringAnimation showScaleAnimation;
        COUISpringAnimation showSpacingAnimation;
        COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
        return showAlphaAnimation != null && ((COUIDynamicAnimation) showAlphaAnimation).mRunning && (showScaleAnimation = getShowScaleAnimation(view)) != null && ((COUIDynamicAnimation) showScaleAnimation).mRunning && (showSpacingAnimation = getShowSpacingAnimation(view)) != null && ((COUIDynamicAnimation) showSpacingAnimation).mRunning;
    }

    public static void cancelAllAnimation(View view) {
        COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
        if (showAlphaAnimation != null) {
            showAlphaAnimation.cancel();
        }
        COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
        if (showScaleAnimation != null) {
            showScaleAnimation.cancel();
        }
        COUISpringAnimation showSpacingAnimation = getShowSpacingAnimation(view);
        if (showSpacingAnimation != null) {
            showSpacingAnimation.cancel();
        }
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        if (hideAlphaAnimation != null) {
            hideAlphaAnimation.cancel();
        }
        COUISpringAnimation hideScaleAnimation = getHideScaleAnimation(view);
        if (hideScaleAnimation != null) {
            hideScaleAnimation.cancel();
        }
        COUISpringAnimation hideSpacingAnimation = getHideSpacingAnimation(view);
        if (hideSpacingAnimation != null) {
            hideSpacingAnimation.cancel();
        }
        view.setTag(2131365564, null);
        view.setTag(2131365567, null);
        view.setTag(2131365559, null);
        view.setTag(2131365562, null);
        view.setTag(2131365566, null);
        view.setTag(2131365561, null);
    }

    public static void cancelHideAnimation(View view) {
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        if (hideAlphaAnimation != null) {
            hideAlphaAnimation.cancel();
        }
        COUISpringAnimation hideScaleAnimation = getHideScaleAnimation(view);
        if (hideScaleAnimation != null) {
            hideScaleAnimation.cancel();
        }
        COUISpringAnimation hideSpacingAnimation = getHideSpacingAnimation(view);
        if (hideSpacingAnimation != null) {
            hideSpacingAnimation.cancel();
        }
        view.setTag(2131365559, null);
        view.setTag(2131365562, null);
        view.setTag(2131365561, null);
    }

    public static void cancelShowAnimation(View view) {
        COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
        if (showAlphaAnimation != null) {
            showAlphaAnimation.cancel();
        }
        COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
        if (showScaleAnimation != null) {
            showScaleAnimation.cancel();
        }
        COUISpringAnimation showSpacingAnimation = getShowSpacingAnimation(view);
        if (showSpacingAnimation != null) {
            showSpacingAnimation.cancel();
        }
        view.setTag(2131365564, null);
        view.setTag(2131365567, null);
        view.setTag(2131365566, null);
    }

    public static COUISpringAnimation getHideAlphaAnimation(View view) {
        Object tag = view.getTag(2131365559);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static COUISpringAnimation getHideScaleAnimation(View view) {
        Object tag = view.getTag(2131365562);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static COUISpringAnimation getHideSpacingAnimation(View view) {
        Object tag = view.getTag(2131365561);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static COUISpringAnimation getShowAlphaAnimation(View view) {
        Object tag = view.getTag(2131365564);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static COUISpringAnimation getShowScaleAnimation(View view) {
        Object tag = view.getTag(2131365567);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static COUISpringAnimation getShowSpacingAnimation(View view) {
        Object tag = view.getTag(2131365566);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static float mapVelocityToSpringS(float f) {
        float fMin = Math.min(1.0f, Math.max(0.0f, Math.abs(f) / 12000.0f));
        return (3.0f - (2.0f * fMin)) * fMin * fMin;
    }

    public final COUISpringAnimation alphaAnimateTo(View view, boolean z, float f, float f2, Function1 function1, Function3 function3) {
        float f3 = z ? 1.0f : 0.0f;
        if (f == f3) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        float fMapVelocityToSpringS = mapVelocityToSpringS(f2);
        float f4 = (z ? 0.35f : 0.16f) - (0.1f * fMapVelocityToSpringS);
        float f5 = (0.2f * fMapVelocityToSpringS) + 0.0f;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("alphaAnimateTo isShow=", z, ", startAlpha=", f, ", endMotionVel=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f2, ", s=", fMapVelocityToSpringS, ", response=");
        sbM.append(f4);
        sbM.append(", bounce=");
        sbM.append(f5);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce(f5).setResponse(f4));
        cOUISpringAnimation.setMinimumVisibleChange(0.002f);
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1 notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1 = new NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1(0);
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$startAlpha = f;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$endAlpha = f3;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$update = function1;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.this$0 = this;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1);
        NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2 notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2 = new NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2(0);
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.this$0 = this;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.$view = view;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.$onAnimationEnd = function3;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2);
        return cOUISpringAnimation;
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void cancelViewAnimation(View view, String str) {
        COUISpringAnimation showAlphaAnimation;
        COUISpringAnimation hideScaleAnimation;
        COUISpringAnimation hideAlphaAnimation;
        COUISpringAnimation showSpacingAnimation;
        COUISpringAnimation hideSpacingAnimation;
        COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
        if ((showScaleAnimation == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning) && (((showAlphaAnimation = getShowAlphaAnimation(view)) == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) && (((hideScaleAnimation = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning) && (((hideAlphaAnimation = getHideAlphaAnimation(view)) == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning) && (((showSpacingAnimation = getShowSpacingAnimation(view)) == null || !((COUIDynamicAnimation) showSpacingAnimation).mRunning) && ((hideSpacingAnimation = getHideSpacingAnimation(view)) == null || !((COUIDynamicAnimation) hideSpacingAnimation).mRunning)))))) {
            return;
        }
        cancelAllAnimation(view);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "cancelViewAnimation reason " + str, null, 8);
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void clearAllShowAlphaAnimateToPosition(View view, float f) {
        COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
        if (showAlphaAnimation == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) {
            return;
        }
        showAlphaAnimation.animateToFinalPosition(f);
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void clearAllhide(View view, boolean z, Function1 function1, Function1 function2, Function1 function3, Function3 function4) {
        COUISpringAnimation hideScaleAnimation;
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(Float.valueOf(0.0f));
            }
            if (function3 != null) {
                function3.invoke(Float.valueOf(0.85f));
            }
            if (function4 != null) {
                function4.invoke(view, AnimationType.BOTH, Float.valueOf(1.0f));
                return;
            }
            return;
        }
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        if (hideAlphaAnimation == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning || (hideScaleAnimation = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning) {
            cancelShowAnimation(view);
            Pair pair = (Pair) function1.invoke(view);
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            createAlphaAnimation(view, false, fFloatValue, 0.0f, function2, function4);
            createScaleAnimation(view, false, 0, fFloatValue2, 0.0f, function3, function4);
            scheduleHideAnimationsWithDelay(view, 0L, null);
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void clearAllshow(View view, boolean z, Function1 function1, Function1 function2, Function1 function3, Function3 function4) {
        COUISpringAnimation showScaleAnimation;
        Float fValueOf = Float.valueOf(1.0f);
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(fValueOf);
            }
            if (function3 != null) {
                function3.invoke(fValueOf);
            }
            if (function4 != null) {
                function4.invoke(view, AnimationType.BOTH, fValueOf);
                return;
            }
            return;
        }
        COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
        if (showAlphaAnimation == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning || (showScaleAnimation = getShowScaleAnimation(view)) == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning) {
            cancelHideAnimation(view);
            Pair pair = (Pair) function1.invoke(view);
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            createAlphaAnimation(view, true, fFloatValue, 0.0f, function2, function4);
            createScaleAnimation(view, true, 0, fFloatValue2, 0.0f, function3, function4);
            scheduleShowAnimationsWithDelay(0L, view);
        }
    }

    public final void createAlphaAnimation(View view, boolean z, float f, float f2, Function1 function1, Function3 function3) {
        if (z) {
            COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
            if (showAlphaAnimation == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) {
                view.setTag(2131365564, alphaAnimateTo(view, true, f, f2, function1, function3));
                return;
            }
            return;
        }
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        if (hideAlphaAnimation == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning) {
            view.setTag(2131365559, alphaAnimateTo(view, false, f, f2, function1, function3));
        }
    }

    public final void createScaleAnimation(View view, boolean z, int i, float f, float f2, Function1 function1, Function3 function3) {
        if (z) {
            COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
            if (showScaleAnimation == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning) {
                view.setTag(2131365567, scaleAnimateTo(view, true, i, f, f2, function1, function3));
                return;
            }
            return;
        }
        COUISpringAnimation hideScaleAnimation = getHideScaleAnimation(view);
        if (hideScaleAnimation == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning) {
            view.setTag(2131365562, scaleAnimateTo(view, false, i, f, f2, function1, function3));
        }
    }

    public final void createSpacingAnimation(View view, boolean z, int i, float f, Function1 function1) {
        if (z) {
            COUISpringAnimation showSpacingAnimation = getShowSpacingAnimation(view);
            if (showSpacingAnimation == null || !((COUIDynamicAnimation) showSpacingAnimation).mRunning) {
                view.setTag(2131365566, spacingAnimateTo(view, true, i, f, function1));
                return;
            }
            return;
        }
        COUISpringAnimation hideSpacingAnimation = getHideSpacingAnimation(view);
        if (hideSpacingAnimation == null || !((COUIDynamicAnimation) hideSpacingAnimation).mRunning) {
            view.setTag(2131365561, spacingAnimateTo(view, false, i, f, function1));
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void hide(View view, boolean z, boolean z2, long j, int i, float f, Function1 function1, Function1 function2, Function1 function3, float f2, Function1 function4, Function2 function5) {
        COUISpringAnimation showAlphaAnimation;
        int i2 = i - 1;
        if (z && !z2) {
            if (areHideAnimationsRunning(view)) {
                return;
            }
            long j2 = (this.forbidDelayWhenInterrupt && (showAlphaAnimation = getShowAlphaAnimation(view)) != null && ((COUIDynamicAnimation) showAlphaAnimation).mRunning) ? 0L : j;
            cancelShowAnimation(view);
            Pair pair = (Pair) function1.invoke(view);
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            createAlphaAnimation(view, false, fFloatValue, f, function2, null);
            createScaleAnimation(view, false, i2, fFloatValue2, f, function3, null);
            createSpacingAnimation(view, false, i2, f2, function4);
            scheduleHideAnimationsWithDelay(view, j2, function5);
            return;
        }
        cancelAllAnimation(view);
        if (function2 == null) {
            view.setTransitionAlpha(0.0f);
        } else {
            function2.invoke(Float.valueOf(0.0f));
        }
        if (function3 == null) {
            view.setScaleX(0.85f);
            view.setScaleY(0.85f);
        } else {
            function3.invoke(Float.valueOf(RangesKt___RangesKt.coerceIn((i2 * (-0.1f)) + 0.85f, 0.0f, 1.0f)));
        }
        if (function4 != null) {
            float f3 = i2;
            float f4 = ((-30.0f) * f3) + 0.0f;
            function4.invoke(Float.valueOf(f4 - (((1.0f - (0.85f - (f3 * 0.1f))) * Math.abs(f4)) * compensationFactor)));
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public boolean isHideAnimationsRunning(View view) {
        return areHideAnimationsRunning(view);
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public boolean isShowAnimationsRunning(View view) {
        return areShowAnimationsRunning(view);
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public boolean isSpacingAnimationRunning(View view) {
        COUISpringAnimation hideSpacingAnimation;
        COUISpringAnimation showSpacingAnimation = getShowSpacingAnimation(view);
        return (showSpacingAnimation != null && ((COUIDynamicAnimation) showSpacingAnimation).mRunning) || ((hideSpacingAnimation = getHideSpacingAnimation(view)) != null && ((COUIDynamicAnimation) hideSpacingAnimation).mRunning);
    }

    public final COUISpringAnimation scaleAnimateTo(View view, boolean z, int i, float f, float f2, Function1 function1, Function3 function3) {
        int iCoerceIn = RangesKt___RangesKt.coerceIn(i, 0, 6);
        float fCoerceIn = z ? 1.0f : RangesKt___RangesKt.coerceIn(0.85f - (i * 0.1f), 0.0f, 1.0f);
        if (f == fCoerceIn) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        float fMapVelocityToSpringS = mapVelocityToSpringS(f2);
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce((fMapVelocityToSpringS * 0.2f) + 0.15f).setResponse(((iCoerceIn * 0.05f) + 0.3f) - (0.1f * fMapVelocityToSpringS)));
        cOUISpringAnimation.setMinimumVisibleChange(0.002f);
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1 notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1 = new NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1(1);
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$startAlpha = f;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$endAlpha = fCoerceIn;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$update = function1;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.this$0 = this;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$1);
        NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2 notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2 = new NotificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2(1);
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.this$0 = this;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.$view = view;
        notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2.$onAnimationEnd = function3;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelSeparateAnimationManagerImpl$alphaAnimateTo$springAnimation$1$2);
        return cOUISpringAnimation;
    }

    public final void scheduleHideAnimationsWithDelay(View view, long j, Function2 function2) {
        if (getHideAlphaAnimation(view) == null && getHideScaleAnimation(view) == null && getHideSpacingAnimation(view) == null) {
            return;
        }
        int i = 1;
        NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 = new NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0(1);
        if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
            anonymousClass1.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
            anonymousClass1.this$0 = this;
            anonymousClass1.$view = view;
            anonymousClass1.$onAnimationStart = function2;
            VarHandle.storeStoreFence();
            view.postDelayed(anonymousClass1, j);
            return;
        }
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(0);
        anonymousClass2.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
        anonymousClass2.this$0 = this;
        anonymousClass2.$view = view;
        anonymousClass2.$onAnimationStart = function2;
        VarHandle.storeStoreFence();
        view.postDelayed(anonymousClass2, j);
        RunnableC02831 runnableC02831 = new RunnableC02831(3);
        runnableC02831.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
        runnableC02831.this$0 = this;
        runnableC02831.$view = view;
        VarHandle.storeStoreFence();
        view.post(runnableC02831);
    }

    public final void scheduleShowAnimationsWithDelay(long j, View view) {
        if (getShowAlphaAnimation(view) == null && getShowScaleAnimation(view) == null && getShowSpacingAnimation(view) == null) {
            return;
        }
        int i = 0;
        NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0 = new NotificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0(0);
        if (!SeparateModeHelper.INSTANCE.getSeparateMode()) {
            RunnableC02831 runnableC02831 = new RunnableC02831(2);
            runnableC02831.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
            runnableC02831.this$0 = this;
            runnableC02831.$view = view;
            VarHandle.storeStoreFence();
            view.postDelayed(runnableC02831, j);
            return;
        }
        RunnableC02831 runnableC02832 = new RunnableC02831(i);
        runnableC02832.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
        runnableC02832.this$0 = this;
        runnableC02832.$view = view;
        VarHandle.storeStoreFence();
        view.postDelayed(runnableC02832, j);
        RunnableC02831 runnableC02833 = new RunnableC02831(1);
        runnableC02833.$runAnimations = notificationPanelSeparateAnimationManagerImpl$$ExternalSyntheticLambda0;
        runnableC02833.this$0 = this;
        runnableC02833.$view = view;
        VarHandle.storeStoreFence();
        view.post(runnableC02833);
    }

    @Override // com.oplus.systemui.shade.NotificationPanelSeparateAnimationManager
    public void show(View view, boolean z, boolean z2, long j, int i, float f, Function1 function1, Function1 function2, Function1 function3, float f2, Function1 function4) {
        COUISpringAnimation hideAlphaAnimation;
        Float fValueOf = Float.valueOf(1.0f);
        if (!z) {
            cancelAllAnimation(view);
            if (function2 == null) {
                view.setTransitionAlpha(1.0f);
            } else {
                function2.invoke(fValueOf);
            }
            if (function3 == null) {
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
            } else {
                function3.invoke(fValueOf);
            }
            if (function4 != null) {
                function4.invoke(Float.valueOf(0.0f));
                return;
            }
            return;
        }
        if (areShowAnimationsRunning(view)) {
            return;
        }
        long j2 = (this.forbidDelayWhenInterrupt && (hideAlphaAnimation = getHideAlphaAnimation(view)) != null && ((COUIDynamicAnimation) hideAlphaAnimation).mRunning) ? 0L : j;
        cancelHideAnimation(view);
        int i2 = i - 1;
        Pair pair = (Pair) function1.invoke(view);
        float fFloatValue = ((Number) pair.component1()).floatValue();
        float fFloatValue2 = ((Number) pair.component2()).floatValue();
        createAlphaAnimation(view, true, fFloatValue, f, function2, null);
        createScaleAnimation(view, true, i2, fFloatValue2, f, function3, null);
        createSpacingAnimation(view, true, i2, f2, function4);
        scheduleShowAnimationsWithDelay(j2, view);
    }

    public final COUISpringAnimation spacingAnimateTo(View view, boolean z, int i, float f, Function1 function1) {
        float fAbs;
        if (z) {
            fAbs = 0.0f;
        } else {
            float f2 = i;
            float f3 = (-30.0f) * f2;
            fAbs = f3 - (((1.0f - (0.85f - (f2 * 0.1f))) * Math.abs(f3)) * compensationFactor);
        }
        if (f == fAbs) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce(0.2f).setResponse((i * 0.02f) + 0.35f));
        cOUISpringAnimation.setMinimumVisibleChange(0.002f);
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1 notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1 = new NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1();
        notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1.$startSpacing = f;
        notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1.$endSpacing = fAbs;
        notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1.$update = function1;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$1);
        NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2 notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2 = new NotificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2();
        notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2.this$0 = this;
        notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelSeparateAnimationManagerImpl$spacingAnimateTo$springAnimation$1$2);
        return cOUISpringAnimation;
    }
}
