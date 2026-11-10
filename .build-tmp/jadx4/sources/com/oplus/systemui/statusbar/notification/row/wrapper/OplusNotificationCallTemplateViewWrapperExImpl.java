package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationCallTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationCallTemplateViewWrapperEx;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationCallTemplateViewWrapperExImpl extends OplusNotificationTemplateViewWrapperExImpl implements OplusNotificationCallTemplateViewWrapperEx {
    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl
    public final void onFindViews() {
        View mActionsContainer;
        View view;
        super.onFindViews();
        NotificationCallTemplateViewWrapper notificationCallTemplateViewWrapperM459getBase = m459getBase();
        if (!Intrinsics.areEqual((notificationCallTemplateViewWrapperM459getBase == null || (view = ((NotificationViewWrapper) notificationCallTemplateViewWrapperM459getBase).mView) == null) ? null : view.getTag(), "call") || (mActionsContainer = getMActionsContainer()) == null || mActionsContainer.getVisibility() == 8) {
            return;
        }
        ViewParent parent = mActionsContainer.getParent();
        if ((parent instanceof LinearLayout ? (LinearLayout) parent : null) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = mActionsContainer.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        layoutParams2.gravity = 16;
        layoutParams2.height = -1;
        layoutParams2.topMargin = 0;
        layoutParams2.bottomMargin = 0;
        mActionsContainer.setLayoutParams(layoutParams2);
        View actionsContainerLayout = getActionsContainerLayout();
        if (actionsContainerLayout != null) {
            ViewGroup.LayoutParams layoutParams3 = actionsContainerLayout.getLayoutParams();
            FrameLayout.LayoutParams layoutParams4 = layoutParams3 instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams3 : null;
            if (layoutParams4 != null) {
                layoutParams4.gravity = 16;
                layoutParams4.height = -1;
                actionsContainerLayout.setLayoutParams(layoutParams4);
                if (actionsContainerLayout instanceof LinearLayout) {
                    LinearLayout linearLayout = (LinearLayout) actionsContainerLayout;
                    if (linearLayout.getGravity() != 8388629) {
                        linearLayout.setGravity(8388629);
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.row.wrapper.OplusNotificationTemplateViewWrapperExImpl
    public final void resolveActions() {
    }
}
