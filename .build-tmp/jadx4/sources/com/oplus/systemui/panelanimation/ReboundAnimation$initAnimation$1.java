package com.oplus.systemui.panelanimation;

import android.animation.ValueAnimator;
import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.systemui.qs.OplusQSContainerImpl;
import com.oplus.systemui.qs.OplusQSContainerImplController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ReboundAnimation$initAnimation$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ ReboundAnimation this$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("update animationFraction: ", fFloatValue, "ReboundAnimation-->", "SystemUi--Common");
        }
        ((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class)).updateFraction(fFloatValue, true, false);
        NotificationStackScrollLayout notificationStackScrollLayout = this.this$0.stackScrollLayout;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.requestChildrenAndTopPaddingUpdate();
        }
        OplusQSContainerImpl oplusQSContainerImpl = QSFragmentHelper.getInstance().mContainer;
        if (oplusQSContainerImpl == null) {
            return;
        }
        OplusQSContainerImplController controller = oplusQSContainerImpl.getController();
        controller.getClass();
        if (!((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class)).isInPanelAnimation() || LargeScreenUtils.shouldUseSplitNotificationShade(controller.getResources())) {
            return;
        }
        controller.updatePosition$2();
    }
}
