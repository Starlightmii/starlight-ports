package com.oplus.systemui.shade;

import android.content.Context;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.keyguard.OplusLockIconView$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.SeparateModeHelper;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelChildAppearAnimationManagerImpl implements NotificationPanelChildAppearAnimationManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPanelChildAppearAnimationManager";
    public final boolean forbidDelayWhenInterrupt;
    private final Lazy panelAnimationEx$delegate = LazyKt__LazyJVMKt.lazy(new NotificationPanelChildAppearAnimationManagerImpl$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl$hide$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ View $view;
        public /* synthetic */ NotificationPanelChildAppearAnimationManagerImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            COUISpringAnimation hideAnimation = NotificationPanelChildAppearAnimationManagerImpl.getHideAnimation(this.$view);
            if (hideAnimation != null) {
                hideAnimation.animateToFinalPosition(1.0f);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManagerImpl$show$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02821 implements Runnable {
        public /* synthetic */ boolean $isFirst;
        public /* synthetic */ View $view;
        public /* synthetic */ NotificationPanelChildAppearAnimationManagerImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            COUISpringAnimation showAnimation = NotificationPanelChildAppearAnimationManagerImpl.getShowAnimation(this.$view);
            if (showAnimation == null || !((COUIDynamicAnimation) showAnimation).mRunning) {
                if (this.$isFirst) {
                    NotificationPanelChildAppearAnimationManagerImpl.access$startStatusBarElementShow(this.this$0, this.$view.getContext());
                }
                COUISpringAnimation showAnimation2 = NotificationPanelChildAppearAnimationManagerImpl.getShowAnimation(this.$view);
                if (showAnimation2 != null) {
                    showAnimation2.animateToFinalPosition(1.0f);
                }
            }
        }
    }

    public NotificationPanelChildAppearAnimationManagerImpl(boolean z) {
        this.forbidDelayWhenInterrupt = z;
    }

    public static final void access$startStatusBarElementShow(NotificationPanelChildAppearAnimationManagerImpl notificationPanelChildAppearAnimationManagerImpl, Context context) {
        if (notificationPanelChildAppearAnimationManagerImpl.getPanelAnimationEx().isStarPullDown()) {
            SeparateModeHelper separateModeHelper = SeparateModeHelper.INSTANCE;
            JankUtils.gfxSceneBegin$default(context, separateModeHelper.getSeparateMode() ? 6 : 1, separateModeHelper.getSeparateMode() ? "pull down separate notification" : "pull down statusbar", 4L);
        }
    }

    public static void cancelAllAnimation(View view) {
        COUISpringAnimation hideAnimation = getHideAnimation(view);
        if (hideAnimation != null) {
            hideAnimation.cancel();
        }
        view.setTag(2131365560, null);
        COUISpringAnimation showAnimation = getShowAnimation(view);
        if (showAnimation != null) {
            showAnimation.cancel();
        }
        view.setTag(2131365565, null);
    }

    public static COUISpringAnimation getHideAnimation(View view) {
        Object tag = view.getTag(2131365560);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    private final PanelAnimationEx getPanelAnimationEx() {
        return (PanelAnimationEx) this.panelAnimationEx$delegate.getValue();
    }

    public static COUISpringAnimation getShowAnimation(View view) {
        Object tag = view.getTag(2131365565);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public final COUISpringAnimation animateTo(View view, boolean z, int i, float f, Function1 function1, Function2 function2) {
        Pair pair = (Pair) function1.invoke(view);
        float fFloatValue = ((Number) pair.component1()).floatValue();
        float fFloatValue2 = ((Number) pair.component2()).floatValue();
        float f2 = z ? 1.0f : 0.0f;
        float f3 = z ? 1.0f : 0.8f;
        if (fFloatValue == f2 && fFloatValue2 == f3) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        float fMin = Math.min(1.0f, Math.max(0.0f, Math.abs(f) / 12000.0f));
        float f4 = (3.0f - (2.0f * fMin)) * fMin * fMin;
        float f5 = ((i * 0.01f) + (z ? 0.2f : 0.15f)) - (0.1f * f4);
        float f6 = (0.2f * f4) + 0.0f;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("[animateTo] isShow=", i, ", delayIndex=", " finalResponse=", z);
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f5, ", finalBounce=", f6, " endMotionVel=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, " s=", f4, ", ");
        sbM.append(view);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce(f6).setResponse(f5));
        cOUISpringAnimation.setMinimumVisibleChange(0.001f);
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1 notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1 = new NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1();
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$startAlpha = fFloatValue;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$endAlpha = f2;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$startScale = fFloatValue2;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$endScale = f3;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$update = function2;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.this$0 = this;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$1);
        NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2 notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2 = new NotificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2();
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2.$isShow = z;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2.$view = view;
        notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelChildAppearAnimationManagerImpl$animateTo$springAnimation$1$2);
        return cOUISpringAnimation;
    }

    @Override // com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager
    public void cancelViewAnimation(View view, boolean z, String str) {
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("cancelViewAnimation, isShowAnimation: ", ", reason: ", str, z, ", ");
        sbM.append(view);
        sbM.append(" ");
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (z) {
            COUISpringAnimation showAnimation = getShowAnimation(view);
            if (showAnimation != null) {
                showAnimation.cancel();
            }
            view.setTag(2131365565, null);
            return;
        }
        COUISpringAnimation hideAnimation = getHideAnimation(view);
        if (hideAnimation != null) {
            hideAnimation.cancel();
        }
        view.setTag(2131365560, null);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    @Override // com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager
    public void hide(View view, boolean z, long j, int i, float f, Function1 function1, Function2 function2) {
        boolean z2;
        COUISpringAnimation showAnimation;
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(Float.valueOf(0.0f), Float.valueOf(0.8f));
                return;
            }
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
            view.setTransitionAlpha(0.0f);
            return;
        }
        if (getHideAnimation(view) != null) {
            return;
        }
        if (this.forbidDelayWhenInterrupt && (showAnimation = getShowAnimation(view)) != null) {
            z2 = ((COUIDynamicAnimation) showAnimation).mRunning;
        }
        long j2 = (SeparateModeHelper.INSTANCE.getSeparateMode() || z2) ? 0L : j;
        int i2 = j == 0 ? i : 0;
        cancelAllAnimation(view);
        view.setTag(2131365560, animateTo(view, false, i2, f, function1, function2));
        if (getHideAnimation(view) != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$view = view;
            VarHandle.storeStoreFence();
            view.postDelayed(anonymousClass1, j2);
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelChildAppearAnimationManager
    public void show(View view, boolean z, long j, int i, boolean z2, float f, Function1 function1, Function2 function2) {
        COUISpringAnimation hideAnimation;
        Float fValueOf = Float.valueOf(1.0f);
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(fValueOf, fValueOf);
                return;
            }
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            view.setTransitionAlpha(1.0f);
            return;
        }
        if (getShowAnimation(view) != null) {
            return;
        }
        long j2 = (this.forbidDelayWhenInterrupt && (hideAnimation = getHideAnimation(view)) != null && ((COUIDynamicAnimation) hideAnimation).mRunning) ? 0L : j;
        if (j != 0) {
            i = 0;
        }
        int i2 = i;
        cancelAllAnimation(view);
        view.setTag(2131365565, animateTo(view, true, i2, f, function1, function2));
        if (getShowAnimation(view) != null) {
            RunnableC02821 runnableC02821 = new RunnableC02821();
            runnableC02821.this$0 = this;
            runnableC02821.$view = view;
            runnableC02821.$isFirst = z2;
            VarHandle.storeStoreFence();
            view.postDelayed(runnableC02821, j2);
        }
    }
}
