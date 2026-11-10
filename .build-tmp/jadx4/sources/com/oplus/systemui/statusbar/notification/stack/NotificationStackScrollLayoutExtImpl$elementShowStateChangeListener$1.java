package com.oplus.systemui.statusbar.notification.stack;

import android.util.Log;
import android.view.ViewGroup;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderListListener;
import com.android.systemui.statusbar.notification.mergeanimator.OplusMergedAnimator;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.SeparateModeHelper;
import com.oplusos.systemui.common.rendercache.RenderBoosterStagger;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl$elementShowStateChangeListener$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OnAfterRenderListListener {
        public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

        public final void onAfterRenderList(List list) {
            NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.this$0;
            if (notificationStackScrollLayoutExtImpl.needDeferredRenderBooster) {
                if (notificationStackScrollLayoutExtImpl.shouldApplyRenderBooster()) {
                    notificationStackScrollLayoutExtImpl.applyRenderBoosterForVisibleChildren("onAfterRenderList", false);
                }
                notificationStackScrollLayoutExtImpl.needDeferredRenderBooster = false;
            }
        }
    }

    public final void onElementShowingChanged() {
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.this$0;
        SysuiStatusBarStateController sysuiStatusBarStateController = notificationStackScrollLayoutExtImpl.statusBarStateController;
        NotificationStackScrollLayout view = notificationStackScrollLayoutExtImpl.getView();
        if (view != null) {
            view.requestChildrenUpdate$1();
        }
        if (sysuiStatusBarStateController.getState() == 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->".concat("onElementShowingChanged " + notificationStackScrollLayoutExtImpl.getPanelAnimationEx().isElementShowing() + " , startScaleAnimation "));
            }
            NotificationStackScrollLayoutExtImpl.access$updateViewScale(notificationStackScrollLayoutExtImpl);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean zIsElementShowing = notificationStackScrollLayoutExtImpl.getPanelAnimationEx().isElementShowing();
            float rawFraction = notificationStackScrollLayoutExtImpl.getPanelAnimationEx().getPanelExpandFraction().getRawFraction();
            int state = sysuiStatusBarStateController.getState();
            boolean separateMode = SeparateModeHelper.INSTANCE.getSeparateMode();
            NotificationStackScrollLayout view2 = notificationStackScrollLayoutExtImpl.getView();
            Float fValueOf = view2 != null ? Float.valueOf(view2.getScaleX()) : null;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("onElementShowingChanged ", zIsElementShowing, " , no startScaleAnimation rawFraction=", rawFraction, " state=");
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(state, " separateMode=", " scaleX=", sbM, separateMode);
            sbM.append(fValueOf);
            Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->".concat(sbM.toString()));
        }
    }

    public final void onFirstElementShowingChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationStackScrollLayoutExtImpl-->onFirstElementShowingChanged");
        }
        NotificationStackScrollLayout view = this.this$0.getView();
        if (view != null) {
            view.requestChildrenUpdate$1();
        }
    }

    public final void onShadeShowingChanged(int i, boolean z) {
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.this$0;
        if (z) {
            NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
            notificationStackScrollLayoutExtImpl.ensureNsslHiddenWhenElementNotShowing();
            notificationStackScrollLayoutExtImpl.tryRequestRenderBoosterAfterRenderList("onShadeShowingChanged");
            return;
        }
        ViewGroup view = notificationStackScrollLayoutExtImpl.getView();
        if (view != null) {
            RenderBoosterStagger.INSTANCE.cancel(view);
        }
        notificationStackScrollLayoutExtImpl.rowBoosterBuffer.clear();
        notificationStackScrollLayoutExtImpl.hasBoosted = false;
        OplusMergedAnimator.Companion companion2 = OplusMergedAnimator.Companion;
        NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6 notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6 = new NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6(1);
        notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6.f$0 = notificationStackScrollLayoutExtImpl;
        VarHandle.storeStoreFence();
        companion2.clearAll(notificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6);
    }
}
