package com.oplus.systemui.statusbar.notification.row;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.NotificationTopLineView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.internal.widget.CallLayout;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.NotificationContentViewExt;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationDecoratedCustomViewWrapper;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationContentViewExtImp extends NotificationContentViewExt implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationContentViewExtImp";
    private final Context context;
    public boolean isWillLaunchActivity;
    public NotificationContentView notificationContentView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationContentViewExtImp(Context context) {
        super(context);
        this.context = context;
    }

    public void bindView(NotificationContentView notificationContentView) {
        this.notificationContentView = notificationContentView;
    }

    public final Context getContext() {
        return this.context;
    }

    public boolean isExpandable(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        NotificationEntry entry;
        if (expandableNotificationRow.isHeadsUpState() && this.isWillLaunchActivity) {
            return false;
        }
        if (expandableNotificationRow.getEntry() != null && expandableNotificationRow.getEntry().mNotificationEntryEx.getMcsInfo().getHideExpandButton()) {
            return false;
        }
        if (expandableNotificationRow.isChildInGroup() && (entry = expandableNotificationRow.getEntry()) != null) {
            entry.getEx();
        }
        return true;
    }

    public void setRemoteInputVisibleWhenApply(boolean z) {
        super.setRemoteInputVisibleWhenApply(z);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setRemoteInputVisibleWhenApply visible=", "NotificationContentViewExtImp-->", "SystemUi--Notification", z);
        }
        NotificationContentView notificationContentView = this.notificationContentView;
        if (notificationContentView == null) {
            notificationContentView = null;
        }
        notificationContentView.setRemoteInputVisible(z);
    }

    public void setWillLaunchActivity(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("launchActivity = ", "NotificationContentViewExtImp-->", "SystemUi--Notification", z);
        }
        this.isWillLaunchActivity = z;
    }

    public boolean shouldPreferContractedWhenHeightEqualsExpanded(ExpandableNotificationRow expandableNotificationRow, View view, View view2, boolean z) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        Bundle bundle;
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isExpRegion() || expandableNotificationRow == null || view == null || view2 == null || z || expandableNotificationRow.isExpanded(false) || NotificationDecoratedCustomViewWrapper.hasCustomView(view2) || NotificationDecoratedCustomViewWrapper.hasCustomView(view) || (entry = expandableNotificationRow.getEntry()) == null || (sbn = entry.getSbn()) == null || (notification = sbn.getNotification()) == null || (bundle = notification.extras) == null || TextUtils.isEmpty(bundle.getCharSequence("android.title")) || !TextUtils.isEmpty(bundle.getCharSequence("android.text")) || !TextUtils.isEmpty(bundle.getCharSequence("android.bigText"))) ? false : true;
    }

    public boolean skipQuickJudgeExpandedWhenHeadsup(boolean z, boolean z2, View view, boolean z3) {
        return (z || z2) && view != null && z3;
    }

    public void updateActionsButtonVisible(boolean z, View view) {
        View viewFindViewById = view != null ? view.findViewById(R.id.alias) : null;
        if (viewFindViewById == null && !z) {
            NotificationContentView notificationContentView = this.notificationContentView;
            if (notificationContentView == null) {
                notificationContentView = null;
            }
            View expandedChild = notificationContentView.getExpandedChild();
            if (expandedChild == null || (viewFindViewById = expandedChild.findViewById(R.id.alias)) == null) {
                return;
            }
        }
        if (viewFindViewById == null) {
            return;
        }
        FrameLayout frameLayout = this.notificationContentView;
        if ((frameLayout != null ? frameLayout : null).getId() == 2131363090) {
            return;
        }
        if (z) {
            viewFindViewById.setVisibility(4);
        } else {
            viewFindViewById.setVisibility(0);
        }
    }

    public final void updateClipping$1() {
        NotificationContentView notificationContentView = this.notificationContentView;
        if (notificationContentView == null) {
            notificationContentView = null;
        }
        int clipTopAmount = (int) (notificationContentView.getClipTopAmount() - notificationContentView.getTranslationY());
        int unrestrictedContentHeight = (int) ((notificationContentView.getUnrestrictedContentHeight() - notificationContentView.getClipBottomAmount()) - notificationContentView.getTranslationY());
        if (clipTopAmount >= unrestrictedContentHeight) {
            unrestrictedContentHeight = clipTopAmount;
        }
        if (unrestrictedContentHeight == clipTopAmount && notificationContentView.getClipRect().bottom == notificationContentView.getClipRect().top) {
            notificationContentView.getClipRect().set(0, clipTopAmount, notificationContentView.getWidth(), unrestrictedContentHeight);
        } else {
            notificationContentView.getClipRect().set(0, clipTopAmount, notificationContentView.getWidth(), unrestrictedContentHeight);
            notificationContentView.setClipBounds(notificationContentView.getClipRect());
        }
    }

    public final void updateExpandButtons(boolean z, boolean z2) {
        if (z) {
            return;
        }
        NotificationContentView notificationContentView = this.notificationContentView;
        if (notificationContentView == null) {
            notificationContentView = null;
        }
        notificationContentView.updateExpandButtonsDuringLayout(z2, false);
    }

    public void updateTitlePaddingWhenMeasure(View view) {
        if (view.findViewById(R.id.permission_icon) != null) {
            return;
        }
        NotificationTopLineView notificationTopLineViewFindViewById = view.findViewById(R.id.pin_message);
        if ((notificationTopLineViewFindViewById != null ? (TextView) notificationTopLineViewFindViewById.findViewById(R.id.title) : null) != null) {
            return;
        }
        Resources resources = this.context.getResources();
        int dimensionPixelOffset = resources != null ? resources.getDimensionPixelOffset(2131170635) : 0;
        View viewFindViewById = view.findViewById(R.id.flagNoExtractUi);
        View viewFindViewById2 = view.findViewById(R.id.title);
        int measuredWidth = viewFindViewById != null ? viewFindViewById.getMeasuredWidth() : 0;
        if (measuredWidth == (viewFindViewById2 != null ? viewFindViewById2.getPaddingEnd() : 0) || viewFindViewById2 == null) {
            return;
        }
        viewFindViewById2.setPaddingRelative(viewFindViewById2.getPaddingStart(), viewFindViewById2.getPaddingTop(), measuredWidth + dimensionPixelOffset, viewFindViewById2.getPaddingBottom());
    }

    public boolean useExpandedForMinHeight(View view, View view2, ExpandableNotificationRow expandableNotificationRow) {
        StatusBarNotification sbn;
        Notification notification;
        if ((view instanceof CallLayout) && view2 != null && expandableNotificationRow != null) {
            EntryAdapter entryAdapter = expandableNotificationRow.getEntryAdapter();
            Notification.Action[] actionArr = (entryAdapter == null || (sbn = entryAdapter.getSbn()) == null || (notification = sbn.getNotification()) == null) ? null : notification.actions;
            if ((actionArr != null ? actionArr.length : 0) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean useHeadsUpForMinHeight(View view, ExpandableNotificationRow expandableNotificationRow) {
        if (view != null) {
            return expandableNotificationRow.mPinnedStatus.isPinned() || expandableNotificationRow.mHeadsupDisappearRunning;
        }
        return false;
    }
}
