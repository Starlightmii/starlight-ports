package com.oplus.systemui.shade;

import android.util.Log;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelNsslScaleAnimationManagerImpl implements NotificationPanelNsslScaleAnimationManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPanelNsslScaleAnimationManager";
    private final NsslAnimParams animParams;
    private final NotificationPanelNsslScaleAnimationManager.Config config;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationPanelNsslScaleAnimationManagerImpl(NotificationPanelNsslScaleAnimationManager.Config config) {
        NsslAnimParams nsslAnimParamsLegacyScaleOnly;
        this.config = config;
        int iOrdinal = config.ordinal();
        if (iOrdinal == 0) {
            nsslAnimParamsLegacyScaleOnly = NsslAnimParams.Companion.legacyScaleOnly();
        } else {
            if (iOrdinal != 1) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                throw null;
            }
            nsslAnimParamsLegacyScaleOnly = NsslAnimParams.Companion.bbPlusAlphaScale();
        }
        this.animParams = nsslAnimParamsLegacyScaleOnly;
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
        COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
        if (hideAlphaAnimation != null) {
            hideAlphaAnimation.cancel();
        }
        COUISpringAnimation hideScaleAnimation = getHideScaleAnimation(view);
        if (hideScaleAnimation != null) {
            hideScaleAnimation.cancel();
        }
        view.setTag(2131365564, null);
        view.setTag(2131365567, null);
        view.setTag(2131365559, null);
        view.setTag(2131365562, null);
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

    private final boolean getSupportsAlphaAnimation() {
        return this.config == NotificationPanelNsslScaleAnimationManager.Config.BB_PLUS_ALPHA_SCALE;
    }

    public final COUISpringAnimation alphaAnimateTo(View view, boolean z, float f, Function1 function1) {
        NsslAnimParams nsslAnimParams = this.animParams;
        float showAlpha = z ? nsslAnimParams.getShowAlpha() : nsslAnimParams.getHideAlpha();
        if (f == showAlpha) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "alphaAnimateTo isShow=" + z + ", startAlpha=" + f, null, 8);
        NsslAnimParams nsslAnimParams2 = this.animParams;
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(this.animParams.getSpringDamping()).setBounce(this.animParams.getAlphaBounce()).setResponse(z ? nsslAnimParams2.getAlphaShowResponse() : nsslAnimParams2.getAlphaHideResponse()));
        cOUISpringAnimation.setMinimumVisibleChange(this.animParams.getMinVisibleChange());
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1 notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1 = new NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1(0);
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$update = function1;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$startAlpha = f;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$endAlpha = showAlpha;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1);
        NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2 notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2 = new NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2(0);
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2.this$0 = this;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2);
        return cOUISpringAnimation;
    }

    public final void beginOverlappingOptimization(View view) {
        if (getSupportsAlphaAnimation()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationPanelNsslScaleAnimationManager-->beginOverlappingOptimization forceNoOverlappingRendering true");
            }
            NotificationStackScrollLayout notificationStackScrollLayout = view instanceof NotificationStackScrollLayout ? (NotificationStackScrollLayout) view : null;
            if (notificationStackScrollLayout != null) {
                notificationStackScrollLayout.mForceNoOverlappingRendering = true;
            }
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelNsslScaleAnimationManager
    public void cancelViewAnimation(View view, String str) {
        COUISpringAnimation showAlphaAnimation;
        COUISpringAnimation hideScaleAnimation;
        COUISpringAnimation hideAlphaAnimation;
        COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
        if ((showScaleAnimation == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning) && (((showAlphaAnimation = getShowAlphaAnimation(view)) == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) && (((hideScaleAnimation = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning) && ((hideAlphaAnimation = getHideAlphaAnimation(view)) == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning)))) {
            return;
        }
        cancelAllAnimation(view);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "cancelViewAnimation reason " + str, null, 8);
    }

    public final void endOverlappingOptimizationIfIdle(View view) {
        if (getSupportsAlphaAnimation()) {
            COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
            if (showAlphaAnimation == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) {
                COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
                if (hideAlphaAnimation == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationPanelNsslScaleAnimationManager-->endOverlappingOptimizationIfIdle forceNoOverlappingRendering false");
                    }
                    NotificationStackScrollLayout notificationStackScrollLayout = view instanceof NotificationStackScrollLayout ? (NotificationStackScrollLayout) view : null;
                    if (notificationStackScrollLayout != null) {
                        notificationStackScrollLayout.mForceNoOverlappingRendering = false;
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelNsslScaleAnimationManager
    public void hide(View view, boolean z, Function1 function1, Function1 function2, Function1 function3) {
        COUISpringAnimation hideScaleAnimation;
        COUISpringAnimation hideAlphaAnimation;
        COUISpringAnimation hideScaleAnimation2;
        if (!getSupportsAlphaAnimation()) {
            function2 = null;
        }
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(Float.valueOf(this.animParams.getHideAlpha()));
            }
            if (function3 != null) {
                function3.invoke(Float.valueOf(this.animParams.getHideScale()));
                return;
            }
            return;
        }
        COUISpringAnimation hideAlphaAnimation2 = getHideAlphaAnimation(view);
        if (hideAlphaAnimation2 == null || !((COUIDynamicAnimation) hideAlphaAnimation2).mRunning || (hideScaleAnimation2 = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation2).mRunning) {
            COUISpringAnimation showAlphaAnimation = getShowAlphaAnimation(view);
            if (showAlphaAnimation != null) {
                showAlphaAnimation.cancel();
            }
            COUISpringAnimation showScaleAnimation = getShowScaleAnimation(view);
            if (showScaleAnimation != null) {
                showScaleAnimation.cancel();
            }
            view.setTag(2131365564, null);
            view.setTag(2131365567, null);
            beginOverlappingOptimization(view);
            Pair pair = (Pair) function1.invoke(view);
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            if (function2 != null && ((hideAlphaAnimation = getHideAlphaAnimation(view)) == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning)) {
                view.setTag(2131365559, alphaAnimateTo(view, false, fFloatValue, function2));
            }
            if (function3 != null && ((hideScaleAnimation = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning)) {
                view.setTag(2131365562, scaleAnimateTo(view, false, fFloatValue2, function3));
            }
            COUISpringAnimation hideAlphaAnimation3 = getHideAlphaAnimation(view);
            if (hideAlphaAnimation3 != null) {
                if (((COUIDynamicAnimation) hideAlphaAnimation3).mRunning) {
                    hideAlphaAnimation3 = null;
                }
                if (hideAlphaAnimation3 != null) {
                    hideAlphaAnimation3.animateToFinalPosition(1.0f);
                }
            }
            COUISpringAnimation hideScaleAnimation3 = getHideScaleAnimation(view);
            if (hideScaleAnimation3 != null) {
                COUISpringAnimation cOUISpringAnimation = ((COUIDynamicAnimation) hideScaleAnimation3).mRunning ? null : hideScaleAnimation3;
                if (cOUISpringAnimation != null) {
                    cOUISpringAnimation.animateToFinalPosition(1.0f);
                }
            }
            endOverlappingOptimizationIfIdle(view);
        }
    }

    @Override // com.oplus.systemui.shade.NotificationPanelNsslScaleAnimationManager
    public boolean isAnimationRunning(View view) {
        COUISpringAnimation hideAlphaAnimation;
        COUISpringAnimation showAlphaAnimation;
        COUISpringAnimation hideScaleAnimation;
        COUISpringAnimation showScaleAnimation;
        return ((view == null || (showScaleAnimation = getShowScaleAnimation(view)) == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning) && (view == null || (hideScaleAnimation = getHideScaleAnimation(view)) == null || !((COUIDynamicAnimation) hideScaleAnimation).mRunning) && ((view == null || (showAlphaAnimation = getShowAlphaAnimation(view)) == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning) && (view == null || (hideAlphaAnimation = getHideAlphaAnimation(view)) == null || !((COUIDynamicAnimation) hideAlphaAnimation).mRunning))) ? false : true;
    }

    public final COUISpringAnimation scaleAnimateTo(View view, boolean z, float f, Function1 function1) {
        NsslAnimParams nsslAnimParams = this.animParams;
        float showScale = z ? nsslAnimParams.getShowScale() : nsslAnimParams.getHideScale();
        if (f == showScale) {
            return null;
        }
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "scaleAnimateTo isShow=" + z + ", startScale=" + f, null, 8);
        NsslAnimParams nsslAnimParams2 = this.animParams;
        float scaleShowResponse = z ? nsslAnimParams2.getScaleShowResponse() : nsslAnimParams2.getScaleHideResponse();
        NsslAnimParams nsslAnimParams3 = this.animParams;
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(this.animParams.getSpringDamping()).setBounce(z ? nsslAnimParams3.getScaleShowBounce() : nsslAnimParams3.getScaleHideBounce()).setResponse(scaleShowResponse));
        cOUISpringAnimation.setMinimumVisibleChange(this.animParams.getMinVisibleChange());
        cOUISpringAnimation.setStartValue(0.0f);
        NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1 notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1 = new NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1(1);
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$update = function1;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$startAlpha = f;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1.$endAlpha = showScale;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$1);
        NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2 notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2 = new NotificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2(1);
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2.this$0 = this;
        notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2.$view = view;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(notificationPanelNsslScaleAnimationManagerImpl$alphaAnimateTo$1$2);
        return cOUISpringAnimation;
    }

    @Override // com.oplus.systemui.shade.NotificationPanelNsslScaleAnimationManager
    public void show(View view, boolean z, Function1 function1, Function1 function2, Function1 function3) {
        COUISpringAnimation showScaleAnimation;
        COUISpringAnimation showAlphaAnimation;
        COUISpringAnimation showScaleAnimation2;
        if (!getSupportsAlphaAnimation()) {
            function2 = null;
        }
        if (!z) {
            cancelAllAnimation(view);
            if (function2 != null) {
                function2.invoke(Float.valueOf(this.animParams.getShowAlpha()));
            }
            if (function3 != null) {
                function3.invoke(Float.valueOf(this.animParams.getShowScale()));
                return;
            }
            return;
        }
        COUISpringAnimation showAlphaAnimation2 = getShowAlphaAnimation(view);
        if (showAlphaAnimation2 == null || !((COUIDynamicAnimation) showAlphaAnimation2).mRunning || (showScaleAnimation2 = getShowScaleAnimation(view)) == null || !((COUIDynamicAnimation) showScaleAnimation2).mRunning) {
            COUISpringAnimation hideAlphaAnimation = getHideAlphaAnimation(view);
            if (hideAlphaAnimation != null) {
                hideAlphaAnimation.cancel();
            }
            COUISpringAnimation hideScaleAnimation = getHideScaleAnimation(view);
            if (hideScaleAnimation != null) {
                hideScaleAnimation.cancel();
            }
            view.setTag(2131365559, null);
            view.setTag(2131365562, null);
            beginOverlappingOptimization(view);
            Pair pair = (Pair) function1.invoke(view);
            float fFloatValue = ((Number) pair.component1()).floatValue();
            float fFloatValue2 = ((Number) pair.component2()).floatValue();
            if (function2 != null && ((showAlphaAnimation = getShowAlphaAnimation(view)) == null || !((COUIDynamicAnimation) showAlphaAnimation).mRunning)) {
                view.setTag(2131365564, alphaAnimateTo(view, true, fFloatValue, function2));
            }
            if (function3 != null && ((showScaleAnimation = getShowScaleAnimation(view)) == null || !((COUIDynamicAnimation) showScaleAnimation).mRunning)) {
                view.setTag(2131365567, scaleAnimateTo(view, true, fFloatValue2, function3));
            }
            COUISpringAnimation showAlphaAnimation3 = getShowAlphaAnimation(view);
            if (showAlphaAnimation3 != null) {
                if (((COUIDynamicAnimation) showAlphaAnimation3).mRunning) {
                    showAlphaAnimation3 = null;
                }
                if (showAlphaAnimation3 != null) {
                    showAlphaAnimation3.animateToFinalPosition(1.0f);
                }
            }
            COUISpringAnimation showScaleAnimation3 = getShowScaleAnimation(view);
            if (showScaleAnimation3 != null) {
                COUISpringAnimation cOUISpringAnimation = ((COUIDynamicAnimation) showScaleAnimation3).mRunning ? null : showScaleAnimation3;
                if (cOUISpringAnimation != null) {
                    cOUISpringAnimation.animateToFinalPosition(1.0f);
                }
            }
            endOverlappingOptimizationIfIdle(view);
        }
    }
}
