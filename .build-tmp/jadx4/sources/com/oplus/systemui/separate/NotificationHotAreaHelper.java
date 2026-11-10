package com.oplus.systemui.separate;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationHotAreaHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationHotAreaHelper";
    private View clearAllButton;
    private NotificationPanelView notificationPanelView;
    private final NotificationPanelViewController panelViewController;
    private final NotificationStackScrollLayoutController stackLayoutController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationHotAreaHelper(NotificationPanelViewController notificationPanelViewController, NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.panelViewController = notificationPanelViewController;
        this.stackLayoutController = notificationStackScrollLayoutController;
        NotificationPanelView view = notificationPanelViewController.getView();
        NotificationPanelView notificationPanelView = view instanceof NotificationPanelView ? view : null;
        this.notificationPanelView = notificationPanelView;
        this.clearAllButton = notificationPanelView != null ? notificationPanelView.findViewById(2131362545) : null;
    }

    private final Rect getClearButtonArea() {
        View view = this.clearAllButton;
        if (view == null || view.getVisibility() != 0) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        return new Rect(i, iArr[1], view.getWidth() + i, view.getHeight() + iArr[1]);
    }

    private final Rect getFullScreenHotArea() {
        return new Rect(0, 0, this.panelViewController.getView().getWidth(), this.panelViewController.getView().getHeight());
    }

    private final Rect getNotificationArea() {
        float fMin;
        float topPadding = this.stackLayoutController.getView().mAmbientState.getTopPadding() + ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getTopMargin();
        ExpandableView lastVisibleChild = getLastVisibleChild(this.stackLayoutController.getView());
        if (lastVisibleChild != null) {
            fMin = (((lastVisibleChild.getScaleY() + 1.0f) * lastVisibleChild.getActualHeight()) / 2.0f) + lastVisibleChild.getTranslationY();
        } else {
            fMin = topPadding;
        }
        NotificationShelf notificationShelf = this.stackLayoutController.getView().mShelf;
        Float fValueOf = notificationShelf != null ? Float.valueOf(notificationShelf.getTranslationY()) : null;
        if (fValueOf != null) {
            fMin = Math.min(fMin, fValueOf.floatValue());
        }
        int width = this.panelViewController.getView().getWidth();
        return getSidePadding() > ColumnWidthUtil.dp2px(this.panelViewController.getView().getContext(), 16.0f) ? new Rect(getSidePadding(), (int) topPadding, width - getSidePadding(), (int) fMin) : new Rect(0, (int) topPadding, width, (int) fMin);
    }

    private final int getSidePadding() {
        return (this.panelViewController.getView().getWidth() - this.stackLayoutController.getView().getWidth()) / 2;
    }

    public final ExpandableView getLastVisibleChild(NotificationStackScrollLayout notificationStackScrollLayout) {
        int childCount = notificationStackScrollLayout.getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                return null;
            }
            ExpandableView childAt = notificationStackScrollLayout.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt != notificationStackScrollLayout.mShelf) {
                if (childAt instanceof ExpandableView) {
                    return childAt;
                }
                return null;
            }
        }
    }

    public final Pair<List<Rect>, List<Rect>> getNotificationScrollXHotZone() {
        StackScrollerDecorView stackScrollerDecorView = this.stackLayoutController.mView.mEmptyShadeView;
        if (stackScrollerDecorView == null ? false : stackScrollerDecorView.mIsVisible) {
            return TuplesKt.to(CollectionsKt__CollectionsJVMKt.listOf(getFullScreenHotArea()), CollectionsKt__CollectionsKt.emptyList());
        }
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(getFullScreenHotArea());
        List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new Rect[]{getNotificationArea()});
        Rect clearButtonArea = getClearButtonArea();
        if (clearButtonArea != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationHotAreaHelper-->".concat("add clear button area: " + clearButtonArea));
            }
            listMutableListOf.add(clearButtonArea);
        }
        return TuplesKt.to(listListOf, listMutableListOf);
    }
}
