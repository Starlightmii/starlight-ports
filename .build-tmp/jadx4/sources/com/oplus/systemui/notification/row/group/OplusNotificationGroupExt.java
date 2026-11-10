package com.oplus.systemui.notification.row.group;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusNotificationGroupExt {
    default void flushIcon(NotificationChildrenContainer notificationChildrenContainer) {
    }

    default int getCollapsedBottomPadding(Resources resources, int i, boolean z) {
        return i;
    }

    default int getExpandedBottomPadding(int i, boolean z) {
        return 0;
    }

    default int getFirstYPosition(boolean z, float f, boolean z2, boolean z3, int i) {
        return 0;
    }

    default int getGroupTopMargin(boolean z, boolean z2, float f, int i, boolean z3, boolean z4, NotificationChildrenContainer notificationChildrenContainer) {
        return i;
    }

    default int getGroupTopPaddingWhenCollapsed(int i, boolean z, NotificationChildrenContainer notificationChildrenContainer) {
        return i;
    }

    default int getMinExpandHeight(HybridNotificationView hybridNotificationView, int i) {
        return i;
    }

    default ViewGroup getOplusExpandGroupHeaderView() {
        return null;
    }

    default int getScaleHeaderHeight(int i) {
        return i;
    }

    default void initDimens(Resources resources) {
    }

    default void layoutHeader(NotificationChildrenContainer notificationChildrenContainer) {
    }

    default void measureOplusExpandHeader(NotificationChildrenContainer notificationChildrenContainer, int i, int i2) {
    }

    default void onChildrenViewAdded(NotificationChildrenContainer notificationChildrenContainer, View view) {
    }

    default void onRemoveTransientView(View view) {
    }

    default void reInflate(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener) {
    }

    default void recreateOplusNotificationHeader(NotificationChildrenContainer notificationChildrenContainer, View.OnClickListener onClickListener, boolean z) {
    }

    default void setExpandButtonNumber(int i) {
    }

    default void updateGroupHeaderColor() {
    }

    default void updatePillBgAndNumberColor() {
    }

    default void updateSingleLinePadding(ExpandableNotificationRow expandableNotificationRow, int i) {
    }

    default void updateSummaryGroupBadgeShadow(float f) {
    }

    default void updateSummaryHeaderBlurRadius(float f) {
    }
}
