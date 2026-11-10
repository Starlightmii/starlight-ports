package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.animation.PathInterpolator;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.GestureHomeHandleEventController;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleOldAnimator {
    public static final String TAG = "OplusHandleOldAnimator";
    private Context context;
    private ValueAnimator longPressAnimation;
    public final float maxTransitionDistance;
    private OplusNavigationHandle oplusNavigationHandle;
    public final long resetDuration;
    private ValueAnimator restorePressAnimation;
    public final long startDuration;
    public static final Companion Companion = new Companion();
    private static final PathInterpolator START_ANIMATION_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.1f, 1.0f);
    private static final PathInterpolator RESTORE_ANIMATION_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
    private OplusHandleOldAnimType transitionAnimType = OplusHandleOldAnimType.NoAnim;
    public float longPressAnimateValue = 1.0f;
    public long longPressAnimateTime = 450;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$G4Lt5QZgwfvXWDfZTU-psxo_uMY, reason: not valid java name */
    public static Unit m31$r8$lambda$G4Lt5QZgwfvXWDfZTUpsxo_uMY(OplusHandleOldAnimator oplusHandleOldAnimator) {
        oplusHandleOldAnimator.transitionAnimType = OplusHandleOldAnimType.NoAnim;
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$xNPRhg-edUXFwoYJ8o-zN3SEzCs, reason: not valid java name */
    public static Unit m32$r8$lambda$xNPRhgedUXFwoYJ8ozN3SEzCs(OplusHandleOldAnimator oplusHandleOldAnimator) {
        oplusHandleOldAnimator.transitionAnimType = OplusHandleOldAnimType.NoAnim;
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$xtlOFTBzEpqMp-RN8QHSdzGiVU4, reason: not valid java name */
    public static Unit m33$r8$lambda$xtlOFTBzEpqMpRN8QHSdzGiVU4(OplusHandleOldAnimator oplusHandleOldAnimator) {
        oplusHandleOldAnimator.transitionAnimType = OplusHandleOldAnimType.NoAnim;
        return Unit.INSTANCE;
    }

    public OplusHandleOldAnimator(Context context, OplusNavigationHandle oplusNavigationHandle) {
        this.context = context;
        this.oplusNavigationHandle = oplusNavigationHandle;
        this.maxTransitionDistance = context.getResources().getDimension(2131170533);
        boolean z = KeyguardFeatureOption.INSTANCE.getSPlatforAnimationLevel() <= 1;
        this.startDuration = z ? 100L : 200L;
        this.resetDuration = z ? 200L : 400L;
    }

    public static final void access$longPressReset(OplusHandleOldAnimator oplusHandleOldAnimator) {
        oplusHandleOldAnimator.longPressAnimation = null;
        oplusHandleOldAnimator.longPressAnimateValue = 1.0f;
        oplusHandleOldAnimator.longPressAnimateTime = 450L;
        oplusHandleOldAnimator.oplusNavigationHandle.updatePaintColor();
    }

    public static final void access$startPressRestoreAnimation(OplusHandleOldAnimator oplusHandleOldAnimator) {
        if (oplusHandleOldAnimator.longPressAnimation != null) {
            ValueAnimator valueAnimator = oplusHandleOldAnimator.restorePressAnimation;
            float f = oplusHandleOldAnimator.longPressAnimateValue;
            int i = 1;
            if (valueAnimator != null) {
                valueAnimator.setFloatValues(f, 0.0f);
                valueAnimator.setDuration(oplusHandleOldAnimator.longPressAnimateTime);
                valueAnimator.start();
                return;
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, 0.0f);
            valueAnimatorOfFloat.setDuration(oplusHandleOldAnimator.longPressAnimateTime);
            valueAnimatorOfFloat.setInterpolator(new COUIMoveEaseInterpolator());
            OplusHandleOldAnimator$startPressAnimation$2$1 oplusHandleOldAnimator$startPressAnimation$2$1 = new OplusHandleOldAnimator$startPressAnimation$2$1(i);
            oplusHandleOldAnimator$startPressAnimation$2$1.$this_apply = valueAnimatorOfFloat;
            oplusHandleOldAnimator$startPressAnimation$2$1.this$0 = oplusHandleOldAnimator;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(oplusHandleOldAnimator$startPressAnimation$2$1);
            valueAnimatorOfFloat.addListener(new startPressAnimation.2.2(oplusHandleOldAnimator, 1));
            valueAnimatorOfFloat.start();
            oplusHandleOldAnimator.restorePressAnimation = valueAnimatorOfFloat;
        }
    }

    public final void doTransitionAnimator(OplusHandleOldAnimType oplusHandleOldAnimType) {
        if (this.transitionAnimType == OplusHandleOldAnimType.NoAnim) {
            this.transitionAnimType = oplusHandleOldAnimType;
            int iOrdinal = oplusHandleOldAnimType.ordinal();
            if (iOrdinal == 0) {
                startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, 0.0f, this.maxTransitionDistance, this.startDuration, START_ANIMATION_INTERPOLATOR, null);
                return;
            }
            float f = this.maxTransitionDistance;
            if (iOrdinal == 1) {
                startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, 0.0f, -f, this.startDuration, START_ANIMATION_INTERPOLATOR, null);
            } else {
                if (iOrdinal != 2) {
                    return;
                }
                startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, this.oplusNavigationHandle.getTranslationY() != 0.0f ? this.oplusNavigationHandle.getTranslationY() : 0.0f, -f, this.startDuration, START_ANIMATION_INTERPOLATOR, null);
            }
        }
    }

    public final void doTransitionResetAnimator() {
        OplusHandleOldAnimType oplusHandleOldAnimType = this.transitionAnimType;
        if (oplusHandleOldAnimType != OplusHandleOldAnimType.NoAnim) {
            int iOrdinal = oplusHandleOldAnimType.ordinal();
            if (iOrdinal == 0) {
                PathInterpolator pathInterpolator = RESTORE_ANIMATION_INTERPOLATOR;
                OplusHandleOldAnimator$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusHandleOldAnimator$.ExternalSyntheticLambda0(2);
                externalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, this.maxTransitionDistance, 0.0f, this.resetDuration, pathInterpolator, externalSyntheticLambda0);
                return;
            }
            float f = this.maxTransitionDistance;
            if (iOrdinal == 1) {
                PathInterpolator pathInterpolator2 = RESTORE_ANIMATION_INTERPOLATOR;
                OplusHandleOldAnimator$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new OplusHandleOldAnimator$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, -f, 0.0f, this.resetDuration, pathInterpolator2, externalSyntheticLambda1);
                return;
            }
            if (iOrdinal != 2) {
                return;
            }
            float currentBurnInMove = this.oplusNavigationHandle.getCurrentBurnInMove();
            PathInterpolator pathInterpolator3 = RESTORE_ANIMATION_INTERPOLATOR;
            OplusHandleOldAnimator$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new OplusHandleOldAnimator$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            startAnimator(MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, -f, currentBurnInMove, this.resetDuration, pathInterpolator3, externalSyntheticLambda2);
        }
    }

    public final void endLongPressAnimation() {
        ValueAnimator valueAnimator = this.longPressAnimation;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.longPressAnimateValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.longPressAnimateTime = valueAnimator.getCurrentPlayTime();
        valueAnimator.cancel();
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isLongPressAnimRunning() {
        return this.longPressAnimation != null;
    }

    public final boolean isTransitionAnimRunning() {
        return this.transitionAnimType != OplusHandleOldAnimType.NoAnim;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void startAnimator(String str, float f, float f2, long j, PathInterpolator pathInterpolator, Function0 function0) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.oplusNavigationHandle, str, f, f2);
        objectAnimatorOfFloat.setDuration(j);
        objectAnimatorOfFloat.setInterpolator(pathInterpolator);
        OplusHandleOldAnimator$startAnimator$lambda$5$lambda$4$.inlined.doOnEnd.1 r3 = new OplusHandleOldAnimator$startAnimator$lambda$5$lambda$4$.inlined.doOnEnd.1();
        r3.$onEnd$inlined = function0;
        VarHandle.storeStoreFence();
        objectAnimatorOfFloat.addListener(r3);
        objectAnimatorOfFloat.start();
    }

    public final void startPressAnimation() {
        if (!GestureHomeHandleEventController.INSTANCE.hasListeners()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "OplusHandleOldAnimator-->no gesture home handle event, do not need start press animation");
                return;
            }
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(450L);
        valueAnimatorOfFloat.setInterpolator(new COUIMoveEaseInterpolator());
        OplusHandleOldAnimator$startPressAnimation$2$1 oplusHandleOldAnimator$startPressAnimation$2$1 = new OplusHandleOldAnimator$startPressAnimation$2$1(0);
        oplusHandleOldAnimator$startPressAnimation$2$1.$this_apply = valueAnimatorOfFloat;
        oplusHandleOldAnimator$startPressAnimation$2$1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(oplusHandleOldAnimator$startPressAnimation$2$1);
        valueAnimatorOfFloat.addListener(new startPressAnimation.2.2(this, 0));
        this.longPressAnimation = valueAnimatorOfFloat;
        ValueAnimator valueAnimator = this.restorePressAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.end();
        }
        ValueAnimator valueAnimator2 = this.longPressAnimation;
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }
}
