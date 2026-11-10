package com.oplus.systemui.notification.lockscreen.capsule.event;

import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataOrderHandler;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollerHorizontal$endListener$1 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ CapsuleScrollerHorizontal this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        CapsuleScrollerHorizontal capsuleScrollerHorizontal = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(NotificationPanelViewController$.ExternalSyntheticOutline0.m("hScrollSpring, cancelled:", z, ", EndValue:", f, ", finalXAmount:"), capsuleScrollerHorizontal.finalXAmount, "CapsuleScrollerHorizontal-->", "SystemUi--Notification");
        }
        this.this$0.trySwitchBaseCard();
        this.this$0.scrollXAmount = 0.0f;
        cOUIDynamicAnimation.setStartValue(0.0f);
        CapsuleDataOrderHandler capsuleDataOrderHandler = CapsuleDataOrderHandler.INSTANCE;
        CapsuleDataOrderHandler.CapsuleNextModel capsuleNextModel = CapsuleDataOrderHandler.CapsuleNextModel.NATURAL;
        capsuleDataOrderHandler.setNextModel(capsuleNextModel);
        this.this$0.capsuleContainer.customOverScrollToX(0);
        Function0 pendingClickWhenFling = this.this$0.getPendingClickWhenFling();
        if (this.this$0.getCancelListener() == null || pendingClickWhenFling != null) {
            if (pendingClickWhenFling != null) {
                CapsuleScrollerHorizontal capsuleScrollerHorizontal2 = this.this$0;
                pendingClickWhenFling.invoke();
                capsuleScrollerHorizontal2.ignoreHandScrollUntilPointerUp = true;
            }
            this.this$0.setCancelListener(null);
            this.this$0.setPendingClickWhenFling(null);
            this.this$0.touchHandler.publishUnInterceptTask();
            CapsuleDataPinHandler.INSTANCE.onScrollFinished(capsuleDataOrderHandler.getNextModel() == capsuleNextModel);
        } else {
            Function0 cancelListener = this.this$0.getCancelListener();
            if (cancelListener != null) {
                cancelListener.invoke();
            }
            this.this$0.setCancelListener(null);
        }
        this.this$0.capsuleContainer.requestCancelTouchFeedback(false);
        this.this$0.getHostLayoutHelper().getAmbientState().setHorizontalFling(false);
        this.this$0.getHostLayoutHelper().getAmbientState().setHorizontalBackFling(false);
    }
}
