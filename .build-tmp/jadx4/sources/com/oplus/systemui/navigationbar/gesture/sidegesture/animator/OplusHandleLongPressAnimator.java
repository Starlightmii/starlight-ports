package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.content.Context;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleLongPressAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HandleLongPressAnimator";
    private Context context;
    public boolean isEnlarge;
    private SpringAnimation longPressAnimator;
    private OplusNavigationHandle oplusNavigationHandle;

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleLongPressAnimator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends FloatPropertyCompat {
        public final float getValue(Object obj) {
            return ((OplusNavigationHandle) obj).getLongPressAnimProgress();
        }

        public final void setValue(Object obj, float f) {
            ((OplusNavigationHandle) obj).setLongPressAnimProgress(f);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleLongPressAnimator$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements DynamicAnimation.OnAnimationEndListener {
        public /* synthetic */ OplusHandleLongPressAnimator this$0;

        public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            if (z) {
                return;
            }
            this.this$0.oplusNavigationHandle.updatePaintColor();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusHandleLongPressAnimator(Context context, OplusNavigationHandle oplusNavigationHandle) {
        this.context = context;
        this.oplusNavigationHandle = oplusNavigationHandle;
        SpringAnimation springAnimation = new SpringAnimation(new AnonymousClass1("longPressAnimProgress"), oplusNavigationHandle);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(50.0f);
        springForce.setDampingRatio(1.0f);
        springAnimation.setMinimumVisibleChange(0.00390625f);
        springAnimation.setSpring(springForce);
        this.longPressAnimator = springAnimation;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addEndListener(anonymousClass2);
    }

    public final void animateLongPress(boolean z) {
        float f = z ? 50.0f : 180.0f;
        float f2 = z ? 1.0f : 0.6f;
        float f3 = z ? 1.0f : 0.0f;
        this.isEnlarge = z;
        SpringAnimation springAnimation = this.longPressAnimator;
        springAnimation.cancel();
        springAnimation.getSpring().setStiffness(f);
        springAnimation.getSpring().setDampingRatio(f2);
        springAnimation.animateToFinalPosition(f3);
    }

    public final void end() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("HandleLongPressAnimatorEnd, isEnlarge: ", "HandleLongPressAnimator-->", "SystemUi--NavBar", this.isEnlarge);
        }
        if (this.isEnlarge) {
            animateLongPress(false);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean isLongPressAnimRunning() {
        if (this.isEnlarge) {
            return true;
        }
        return ((DynamicAnimation) this.longPressAnimator).mRunning;
    }

    public final void setContext(Context context) {
        this.context = context;
    }
}
