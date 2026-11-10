package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.content.Context;
import android.view.MotionEvent;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDragScaleHelper implements ICapsuleDragEventHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleDragScaleHelper";
    private final NotificationCapsuleContainer capsuleContainer;
    private final CapsuleMotionTracker capsuleMotionTracker;
    private final Context context;
    private final CapsuleDragScaleHelper$touchCallback$1 touchCallback;
    private final Lazy touchMotionAnimator$delegate;
    public volatile boolean touchScaleFlingWhenCardHScrolling;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$GVg_kAlm9s-pqmfL7IxdbIKL5wg, reason: not valid java name */
    public static COUITouchMotionAnimator m83$r8$lambda$GVg_kAlm9spqmfL7IxdbIKL5wg(CapsuleDragScaleHelper capsuleDragScaleHelper) {
        COUITouchMotionAnimator cOUITouchMotionAnimator = new COUITouchMotionAnimator(capsuleDragScaleHelper.context);
        cOUITouchMotionAnimator.setDeformationEnable(true);
        cOUITouchMotionAnimator.setRubberEnable(true);
        cOUITouchMotionAnimator.setPressScaleEnable(false);
        cOUITouchMotionAnimator.addOnAnimationUpdateCallback(capsuleDragScaleHelper.touchCallback);
        ((COUIComposeMotionAnimator) cOUITouchMotionAnimator).mAnimationEndListener = CapsuleDragScaleHelper$touchMotionAnimator$2$1$1.INSTANCE;
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        cOUITouchMotionAnimator.onSizeChange(capsuleConfig.getItemWidth(), capsuleConfig.getItemHeight());
        return cOUITouchMotionAnimator;
    }

    public CapsuleDragScaleHelper(Context context, NotificationCapsuleContainer notificationCapsuleContainer, CapsuleMotionTracker capsuleMotionTracker) {
        this.context = context;
        this.capsuleContainer = notificationCapsuleContainer;
        this.capsuleMotionTracker = capsuleMotionTracker;
        CapsuleDragScaleHelper$$ExternalSyntheticLambda0 capsuleDragScaleHelper$$ExternalSyntheticLambda0 = new CapsuleDragScaleHelper$$ExternalSyntheticLambda0();
        capsuleDragScaleHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.touchMotionAnimator$delegate = LazyKt__LazyJVMKt.lazy(capsuleDragScaleHelper$$ExternalSyntheticLambda0);
        CapsuleDragScaleHelper$touchCallback$1 capsuleDragScaleHelper$touchCallback$1 = new CapsuleDragScaleHelper$touchCallback$1();
        capsuleDragScaleHelper$touchCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.touchCallback = capsuleDragScaleHelper$touchCallback$1;
    }

    public static final boolean access$isMultiCardHorizontalDragging(CapsuleDragScaleHelper capsuleDragScaleHelper) {
        return CapsuleViewExpandKtKt.getCapsuleVisibleChildren(capsuleDragScaleHelper.capsuleContainer).size() > 1 && capsuleDragScaleHelper.capsuleMotionTracker.getHorizontalDragging();
    }

    public static final boolean access$isMultiCardVerticalDraggingAndStayZero(CapsuleDragScaleHelper capsuleDragScaleHelper) {
        return CapsuleViewExpandKtKt.getCapsuleVisibleChildren(capsuleDragScaleHelper.capsuleContainer).size() > 1 && capsuleDragScaleHelper.capsuleMotionTracker.getVerticalDragging() && capsuleDragScaleHelper.capsuleContainer.getStackY() == 0.0f;
    }

    private final COUITouchMotionAnimator getTouchMotionAnimator() {
        return (COUITouchMotionAnimator) this.touchMotionAnimator$delegate.getValue();
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler
    public void onCancelDragScale(String str) {
        getTouchMotionAnimator().reset(false);
        updateView(this.capsuleContainer.getWidth() / 2.0f, this.capsuleContainer.getHeight() / 2.0f, 1.0f, 1.0f, 0.0f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onCancelDragScale: reason=", str, "CapsuleDragScaleHelper-->", "SystemUi--Notification");
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler
    public void onCapsuleDragEnd(MotionEvent motionEvent) {
        getTouchMotionAnimator().handleTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler
    public final void onCapsuleDragExchangeToStack() {
        onCancelDragScale("exchangeToStack");
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler
    public void onCapsuleDragStart(MotionEvent motionEvent) {
        this.touchScaleFlingWhenCardHScrolling = false;
        getTouchMotionAnimator().reset(false);
        getTouchMotionAnimator().handleTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.event.ICapsuleDragEventHandler
    public void onCapsuleDragging(MotionEvent motionEvent) {
        getTouchMotionAnimator().handleTouchEvent(motionEvent);
    }

    public final void updateView(float f, float f2, float f3, float f4, float f5) {
        NotificationCapsuleContainer notificationCapsuleContainer = this.capsuleContainer;
        ViewExpandKtKt.setPivotXY(notificationCapsuleContainer, f, f2);
        ViewExpandKtKt.setScaleXY(notificationCapsuleContainer, f3, f4);
        notificationCapsuleContainer.setDragScaleTranslationX(f5);
        if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("OnCapsuleDragUpdate: scaleX:", f3, ", scaleY:", f4, ", transX:");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f5, ", pivotX:", f, ", pivotY:");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sbM, f2, "CapsuleDragScaleHelper-->", "SystemUi--Notification");
        }
    }
}
