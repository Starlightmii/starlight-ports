package com.oplus.systemui.statusbar.notification.stack;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1 implements OplusExpandableNotificationRowEx.ExpandedAnimationStateListener {
    public /* synthetic */ OplusNotificationChildrenContainerOverlayExImpl this$0;

    public final void onAnimationStateChanged(boolean z) {
        OplusNotificationChildrenContainerOverlayExImpl oplusNotificationChildrenContainerOverlayExImpl = this.this$0;
        if (oplusNotificationChildrenContainerOverlayExImpl.isGroupExpandAnimating == z) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            ExpandableNotificationRow containingNotification = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification();
            String key = containingNotification != null ? containingNotification.getKey() : null;
            ViewGroup viewGroup = oplusNotificationChildrenContainerOverlayExImpl.iconContainer;
            Float fValueOf = viewGroup != null ? Float.valueOf(viewGroup.getTranslationY()) : null;
            StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("onAnimationStateChanged, isAnimating ", ", row=", key, z, ",iconContainer?.translationY=");
            sbM.append(fValueOf);
            Log.d("SystemUi--Notification", "OplusNCCOverlayEx-->".concat(sbM.toString()));
        }
        oplusNotificationChildrenContainerOverlayExImpl.isGroupExpandAnimating = z;
        if (!z) {
            GroupOverlayContainerView groupOverlayContainerView = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
            if (groupOverlayContainerView != null) {
                groupOverlayContainerView.forceHasOverlappingRendering(true);
            }
            List attachedChildren = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getAttachedChildren();
            int size = attachedChildren.size();
            for (int maxAllowedVisibleChildren = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getMaxAllowedVisibleChildren(true); maxAllowedVisibleChildren < size; maxAllowedVisibleChildren++) {
                RenderBooster renderBooster = RenderBooster.Companion.renderBooster((View) attachedChildren.get(maxAllowedVisibleChildren));
                if (renderBooster != null) {
                    renderBooster.requestStop(0);
                }
            }
            return;
        }
        GroupOverlayContainerView groupOverlayContainerView2 = oplusNotificationChildrenContainerOverlayExImpl.overlayContainer;
        if (groupOverlayContainerView2 != null) {
            groupOverlayContainerView2.forceHasOverlappingRendering(false);
        }
        if (oplusNotificationChildrenContainerOverlayExImpl.overlayContainer != null) {
            oplusNotificationChildrenContainerOverlayExImpl.setOverlayVisibility("onAnimationStateChanged-" + z);
        }
        if (oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getContainingNotification().mChildrenExpanded) {
            int maxAllowedVisibleChildren2 = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getMaxAllowedVisibleChildren(false);
            List attachedChildren2 = oplusNotificationChildrenContainerOverlayExImpl.getChildrenContainer().getAttachedChildren();
            int iMin = Math.min(attachedChildren2.size(), maxAllowedVisibleChildren2);
            for (int i = 0; i < iMin; i++) {
                RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, (View) attachedChildren2.get(i), new OplusNotificationChildrenContainerOverlayExImpl$groupExpandAnimListener$1$onAnimationStateChanged$3(1, OplusExpandableNotificationRowExImpl.Companion, OplusExpandableNotificationRowExImpl.Companion.class, "isRenderExpensiveView", "isRenderExpensiveView(Landroid/view/View;)Z", 0), 1), 11, false, 4);
            }
        }
    }
}
