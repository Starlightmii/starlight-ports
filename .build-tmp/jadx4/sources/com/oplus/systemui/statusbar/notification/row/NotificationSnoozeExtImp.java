package com.oplus.systemui.statusbar.notification.row;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager$;
import com.android.systemui.statusbar.notification.row.NotificationSnooze;
import com.android.systemui.statusbar.notification.row.NotificationSnoozeExt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSnoozeExtImp extends NotificationSnoozeExt {
    public NotificationSnoozeExtImp(Context context) {
        super(context);
    }

    public void expandAnimationEnd(ViewGroup viewGroup, boolean z, boolean z2) {
        NotificationSnooze snooze = getSnooze();
        if (z || z2) {
            return;
        }
        snooze.mSnoozeOptionContainer.setVisibility(4);
        snooze.mSnoozeOptionContainer.setAlpha(0.0f);
        snooze.mSnoozeView.setPadding(getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding());
    }

    public final boolean initRes() {
        setSnoozeViewCollapsedPadding(getContext().getResources().getDimensionPixelSize(2131170699));
        setSnoozeViewExpandedPadding(getContext().getResources().getDimensionPixelSize(2131170700));
        return true;
    }

    public final void onExpandAnimationStart(boolean z, boolean z2) {
        if (z || z2) {
            return;
        }
        getSnooze().mSnoozeView.setPadding(getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding(), getSnoozeViewCollapsedPadding());
    }

    public void playTogether(AnimatorSet animatorSet, ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2) {
        animatorSet.playTogether(objectAnimator2);
    }

    public ObjectAnimator setDividerAnimIfNeed(View view, boolean z) {
        return null;
    }

    public final boolean showSnoozeOptions(boolean z) {
        NotificationGutsManager$.ExternalSyntheticLambda0 externalSyntheticLambda0;
        NotificationSnooze snooze = getSnooze();
        if (snooze.mExpanded == z) {
            return true;
        }
        snooze.mExpanded = z;
        snooze.animateSnoozeOptions(z);
        NotificationGuts notificationGuts = snooze.mGutsContainer;
        if (notificationGuts == null || (externalSyntheticLambda0 = notificationGuts.mHeightListener) == null) {
            return true;
        }
        NotificationGutsManager$.ExternalSyntheticLambda0 externalSyntheticLambda1 = externalSyntheticLambda0;
        NotificationGutsManager notificationGutsManager = externalSyntheticLambda1.f$0;
        ExpandableNotificationRow expandableNotificationRow = externalSyntheticLambda1.f$1;
        notificationGutsManager.mListContainer.onHeightChanged(expandableNotificationRow, expandableNotificationRow.isShown(), "NGM.initializeSnoozeView");
        return true;
    }
}
