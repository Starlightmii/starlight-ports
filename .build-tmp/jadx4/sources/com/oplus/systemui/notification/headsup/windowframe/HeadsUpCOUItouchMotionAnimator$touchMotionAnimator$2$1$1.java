package com.oplus.systemui.notification.headsup.windowframe;

import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUIIComposeMotionUnit;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpCOUItouchMotionAnimator$touchMotionAnimator$2$1$1 implements COUIComposeMotionAnimator.OnAnimationUpdateCallback {
    public /* synthetic */ HeadsUpCOUItouchMotionAnimator this$0;

    public final void onAnimationUpdate(COUIIComposeMotionUnit.ContributeSink contributeSink) {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.this$0;
        COUITouchMotionAnimator.TouchMotionCollector touchMotionCollector = (COUITouchMotionAnimator.TouchMotionCollector) contributeSink;
        headsUpCOUItouchMotionAnimator.touchMotionTranslationY = touchMotionCollector.getTranslateY();
        headsUpCOUItouchMotionAnimator.touchMotionScaleX = 1.0f / touchMotionCollector.getScaleY();
        headsUpCOUItouchMotionAnimator.touchMotionScaleY = touchMotionCollector.getScaleY();
        if (headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal) {
            return;
        }
        headsUpCOUItouchMotionAnimator.applyCurrentViewTransform();
    }
}
