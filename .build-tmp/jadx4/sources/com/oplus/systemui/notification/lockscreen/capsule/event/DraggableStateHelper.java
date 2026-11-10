package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.content.Context;
import android.content.res.Resources;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.clockstyle.IOplusKeyguardStyleBaseUIController;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DraggableStateHelper {
    private static final String TAG = "DraggableStateHelper";
    private static final Lazy oplusKeyguardStyleBaseUIController$delegate;
    private static final Lazy resources$delegate;
    public static final DraggableStateHelper INSTANCE = new DraggableStateHelper();
    private static final KeyguardSpringParam downToCapsuleSpring = new KeyguardSpringParam(0.6f);
    private static final KeyguardSpringParam hScrollSpring = new KeyguardSpringParam(0.7f);
    private static final KeyguardSpringParam upToStackSpring = new KeyguardSpringParam(0.6f);
    public static boolean enableKeyguardDrag = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class KeyguardSpringParam {
        public final float dampingRatio;

        public KeyguardSpringParam(float f) {
            this.dampingRatio = f;
        }
    }

    static {
        final int i = 0;
        resources$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.event.DraggableStateHelper$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        Object dependency = DependencyEx.sDependency.getDependency(Context.class);
                        dependency.getClass();
                        return ((Context) dependency).getResources();
                    default:
                        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardStyleBaseUIController();
                }
            }
        });
        final int i2 = 1;
        oplusKeyguardStyleBaseUIController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.event.DraggableStateHelper$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        Object dependency = DependencyEx.sDependency.getDependency(Context.class);
                        dependency.getClass();
                        return ((Context) dependency).getResources();
                    default:
                        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardStyleBaseUIController();
                }
            }
        });
    }

    private final IOplusKeyguardStyleBaseUIController getOplusKeyguardStyleBaseUIController() {
        return (IOplusKeyguardStyleBaseUIController) oplusKeyguardStyleBaseUIController$delegate.getValue();
    }

    private final Resources getResources() {
        return (Resources) resources$delegate.getValue();
    }

    public final boolean getEnableKeyguardDrag() {
        return enableKeyguardDrag;
    }

    public final void onHorizontalScroll(float f) {
        if (enableKeyguardDrag) {
            float fAbs = Math.abs(f) / CapsuleConfig.INSTANCE.getItemWidthMultiCard();
            if (fAbs > 0.99999f) {
                fAbs = 0.99999f;
            }
            int i = f > 0.0f ? -1 : 1;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("onHorizontalScroll.hScrollFactor: ", fAbs, "DraggableStateHelper-->", "SystemUi--Notification");
            }
            getOplusKeyguardStyleBaseUIController().updateDraggableState(new IOplusKeyguardStyleBaseUIController.DraggableState(1, i, fAbs, 56));
        }
    }

    public final void onHorizontalScrollFinished(float f, float f2) {
        if (enableKeyguardDrag) {
            float fFloatValue = CapsuleSpringAnimParams.KEYGUARD.INSTANCE.getHScrollMaxVelocity().get().floatValue();
            float fAbs = Math.abs(f2);
            if (fAbs < 0.0f) {
                fAbs = 0.0f;
            }
            float f3 = fAbs > fFloatValue ? fFloatValue : fAbs;
            int i = f > 0.0f ? -1 : 1;
            KeyguardSpringParam keyguardSpringParam = hScrollSpring;
            keyguardSpringParam.getClass();
            getOplusKeyguardStyleBaseUIController().updateDraggableState(new IOplusKeyguardStyleBaseUIController.DraggableState(2, i, 1.0f, f3, 109.66f, keyguardSpringParam.dampingRatio));
        }
    }

    public final void onVerticalScroll(float f) {
        if (enableKeyguardDrag) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("NotificationCapsuleContainerTouchHandler.onVerticalScroll.vScrollProgress: ", f, "DraggableStateHelper-->", "SystemUi--Notification");
            }
            if (f > 0.99999f) {
                f = 0.99999f;
            }
            getOplusKeyguardStyleBaseUIController().updateDraggableState(new IOplusKeyguardStyleBaseUIController.DraggableState(1, 2, f, 56));
        }
    }

    public final void onVerticalScrollFinished(float f) {
        if (enableKeyguardDrag) {
            float fFloatValue = CapsuleSpringAnimParams.KEYGUARD.INSTANCE.getUpToStackMaxVelocity().get().floatValue();
            float fAbs = Math.abs(f);
            if (fAbs < 0.0f) {
                fAbs = 0.0f;
            }
            if (fAbs <= fFloatValue) {
                fFloatValue = fAbs;
            }
            IOplusKeyguardStyleBaseUIController oplusKeyguardStyleBaseUIController = getOplusKeyguardStyleBaseUIController();
            KeyguardSpringParam keyguardSpringParam = upToStackSpring;
            keyguardSpringParam.getClass();
            oplusKeyguardStyleBaseUIController.startSpringAnim(fFloatValue, 109.66f, keyguardSpringParam.dampingRatio, 2);
        }
    }

    public final void oneShotStackToCapsule() {
        if (enableKeyguardDrag) {
            float fFloatValue = CapsuleSpringAnimParams.KEYGUARD.INSTANCE.getDownToCapsuleVelocity().get().floatValue();
            IOplusKeyguardStyleBaseUIController oplusKeyguardStyleBaseUIController = getOplusKeyguardStyleBaseUIController();
            KeyguardSpringParam keyguardSpringParam = downToCapsuleSpring;
            keyguardSpringParam.getClass();
            oplusKeyguardStyleBaseUIController.startSpringAnim(fFloatValue, 109.66f, keyguardSpringParam.dampingRatio, 3);
        }
    }

    public final void reset(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("DraggableStateHelperReset.reason:", str, "DraggableStateHelper-->", "SystemUi--Notification");
        }
        getOplusKeyguardStyleBaseUIController().updateDraggableState(new IOplusKeyguardStyleBaseUIController.DraggableState(0, 0, 0.0f, 62));
    }

    public final void setEnableKeyguardDrag(boolean z) {
        enableKeyguardDrag = z;
    }
}
