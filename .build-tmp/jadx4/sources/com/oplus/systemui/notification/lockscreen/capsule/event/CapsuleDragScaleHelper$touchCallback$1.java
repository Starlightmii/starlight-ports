package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUIIComposeMotionUnit;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemLocation;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDragScaleHelper$touchCallback$1 implements COUIComposeMotionAnimator.OnAnimationUpdateCallback {
    public /* synthetic */ CapsuleDragScaleHelper this$0;

    public final void onAnimationUpdate(COUIIComposeMotionUnit.ContributeSink contributeSink) {
        float f;
        float f2;
        float translateX;
        CapsuleItemLocation location;
        if (this.this$0.touchScaleFlingWhenCardHScrolling) {
            return;
        }
        if (this.this$0.capsuleContainer.getAnimatorHelper().getAmbientState().isHorizontalFling && !this.this$0.touchScaleFlingWhenCardHScrolling && ((COUITouchMotionAnimator.TouchMotionCollector) contributeSink).getScaleY() <= 1.0f) {
            this.this$0.touchScaleFlingWhenCardHScrolling = true;
            this.this$0.onCancelDragScale("touchScaleFlingWhenCardHScrolling");
            return;
        }
        if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            COUITouchMotionAnimator.TouchMotionCollector touchMotionCollector = (COUITouchMotionAnimator.TouchMotionCollector) contributeSink;
            Log.d("SystemUi--Notification", "CapsuleDragScaleHelper-->".concat(VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("scaleX:", touchMotionCollector.getScaleX(), ", scaleY:", touchMotionCollector.getScaleY(), ", transX:"), touchMotionCollector.getTranslateX(), ", transY:", touchMotionCollector.getTranslateY(), "OnCapsuleDragScaleAnimCallback: ")));
        }
        NotificationCapsuleContainer notificationCapsuleContainer = this.this$0.capsuleContainer;
        CapsuleDragScaleHelper capsuleDragScaleHelper = this.this$0;
        if (CapsuleDragScaleHelper.access$isMultiCardHorizontalDragging(capsuleDragScaleHelper) || CapsuleDragScaleHelper.access$isMultiCardVerticalDraggingAndStayZero(capsuleDragScaleHelper)) {
            float scaleY = ((COUITouchMotionAnimator.TouchMotionCollector) contributeSink).getScaleY();
            if (scaleY < 1.0f) {
                scaleY = 1.0f;
            }
            f = scaleY;
            f2 = 1.0f / scaleY;
            translateX = 0.0f;
        } else {
            COUITouchMotionAnimator.TouchMotionCollector touchMotionCollector2 = (COUITouchMotionAnimator.TouchMotionCollector) contributeSink;
            float scaleX = touchMotionCollector2.getScaleX();
            f2 = scaleX;
            translateX = touchMotionCollector2.getTranslateX();
            f = 1.0f / scaleX;
        }
        CapsuleItemView baseCard = notificationCapsuleContainer.getBaseCard();
        capsuleDragScaleHelper.updateView(notificationCapsuleContainer.getWidth() / 2.0f, (notificationCapsuleContainer.getStackY() + notificationCapsuleContainer.getHeight()) - (((baseCard == null || (location = baseCard.getLocation()) == null) ? CapsuleConfig.INSTANCE.getItemHeight() : location.getActualHeight()) / 2.0f), f2, f, translateX);
    }
}
