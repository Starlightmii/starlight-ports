package com.oplus.systemui.statusbar.notification.row;

import com.android.systemui.Dependency;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableViewExImpl$touchScaleAnimator$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ OplusExpandableViewExImpl this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        ExpandableViewState viewState;
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        RowAnimationStateEx rowAnimationStateEx;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController;
        NotificationStackScrollLayout view;
        OplusExpandableViewExImpl oplusExpandableViewExImpl = this.this$0;
        ExpandableView view2 = oplusExpandableViewExImpl.getView();
        if (view2 != null) {
            if (!oplusExpandableViewExImpl.isHasMetaBall()) {
                RowAnimationStateEx rowAnimationStateEx2 = OplusExpandableViewStateExKt.getRowAnimationStateEx(view2);
                if (rowAnimationStateEx2 != null) {
                    ((RowAnimationStateExImpl) rowAnimationStateEx2).requestRowScale("scale_for_touch", ScaleState.Companion.invoke(f));
                }
                oplusExpandableViewExImpl.processAnimationToViewOrigin();
                return;
            }
            FeatureOption.INSTANCE.getClass();
            if ((!FeatureOption.isExpRegion() && (notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) Dependency.sDependency.getDependencyInner(NotificationStackScrollLayoutController.class)) != null && (view = notificationStackScrollLayoutController.getView()) != null && view.mExpandingNotification) || (viewState = view2.getViewState()) == null || (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) == null || (rowAnimationStateEx = oplusExpandableViewStateEx.getRowAnimationStateEx()) == null) {
                return;
            }
            view2.getViewState().clipBottomAmount = view2.getClipBottomAmount();
            RowAnimationStateExImpl rowAnimationStateExImpl = (RowAnimationStateExImpl) rowAnimationStateEx;
            Object effectiveClipWidthSource = rowAnimationStateExImpl.getEffectiveClipWidthSource();
            if (effectiveClipWidthSource != null && !effectiveClipWidthSource.equals("clip_width_touch_scale")) {
                oplusExpandableViewExImpl.resetTouchScaleImmediately("clipWidth yield");
                return;
            }
            OplusExpandableViewEx oplusExpandableViewEx = view2.mExpandableViewEx;
            if (oplusExpandableViewEx != null) {
                oplusExpandableViewEx.setTargetClipWidth((int) (((double) view2.getWidth()) * 0.050000011920928955d));
            }
            OplusExpandableViewEx oplusExpandableViewEx2 = view2.mExpandableViewEx;
            if (oplusExpandableViewEx2 != null) {
                oplusExpandableViewEx2.setTargetClipHeight((int) (((double) view2.getActualHeight()) * 0.050000011920928955d));
            }
            rowAnimationStateExImpl.requestClipWidth("clip_width_touch_scale", (int) ((1.0d - ((double) f)) * ((double) view2.getWidth())));
            view2.getViewState().applyToView(view2);
        }
    }
}
