package com.oplus.systemui.notification.row;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.oplus.systemui.notification.row.click.NotificationClickEffectControllerImpl;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationClickEffectImpl extends OplusNotificationClickEffect {
    private final NotificationClickEffectControllerImpl clickEffectController;

    public OplusNotificationClickEffectImpl(Context context, NotificationClickEffectControllerImpl notificationClickEffectControllerImpl) {
        this.clickEffectController = notificationClickEffectControllerImpl;
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationClickEffect
    public View confirmTouchViewClickEffect(View view) {
        if (this.clickEffectController.getEffectingRow() == null || Intrinsics.areEqual(view, this.clickEffectController.getEffectingRow())) {
            return view;
        }
        ExpandableNotificationRow effectingRow = this.clickEffectController.getEffectingRow();
        effectingRow.getClass();
        return effectingRow;
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationClickEffect
    public void handleClickAnimation(ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2, String str, Runnable runnable) {
        this.clickEffectController.startClickAnimation(expandableNotificationRow, z, z2, str, runnable);
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationClickEffect
    public void resetClickAnimationIfNeed(NotificationContentView notificationContentView, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (notificationContentView.getParent() instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow parent = notificationContentView.getParent();
            boolean zIsChildInGroup = parent.isChildInGroup();
            boolean z = zIsChildInGroup && parent.getNotificationParent().isGroupExpanded();
            boolean z2 = parent.getVisibility() == 0 && parent.getAlpha() == 1.0f;
            if ((z || !zIsChildInGroup) && z2 && action == 0) {
                this.clickEffectController.startClickAnimation(parent, false, false, "REASON_NORMAL_ROW_OR_CHILD_ROW_IN_GROUP", null);
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationClickEffect
    public void resetClickAnimationIfNeedOnShadeTouch(boolean z, MotionEvent motionEvent) {
        if (z || motionEvent.getAction() != 1 || this.clickEffectController.getEffectingRow() == null) {
            return;
        }
        NotificationClickEffectControllerImpl notificationClickEffectControllerImpl = this.clickEffectController;
        notificationClickEffectControllerImpl.startClickAnimation(notificationClickEffectControllerImpl.getEffectingRow(), true, false, "REVERT_NSWV", null);
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationClickEffect
    public void revertRowClickAnim(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        int childCount = notificationStackScrollLayoutController.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableView) notificationStackScrollLayoutController.mView.getChildAt(i);
            ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
            if (expandableNotificationRow2 != null) {
                if (expandableNotificationRow2.mRowEx.getHasHandleDownEventAnim()) {
                    this.clickEffectController.startClickAnimation(expandableNotificationRow2, true, false, "REVERT_NSSLC_1", null);
                    return;
                }
                if (expandableNotificationRow2.mIsSummaryWithChildren && expandableNotificationRow2.isGroupExpanded()) {
                    List attachedChildren = expandableNotificationRow2.getAttachedChildren();
                    int size = attachedChildren.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) attachedChildren.get(i2);
                        if (expandableNotificationRow3.mRowEx.getHasHandleDownEventAnim()) {
                            this.clickEffectController.startClickAnimation(expandableNotificationRow3, true, false, "REVERT_NSSLC_2", null);
                            return;
                        }
                    }
                }
            }
        }
    }
}
