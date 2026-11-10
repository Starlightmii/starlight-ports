package com.oplus.systemui.notification.headsup;

import android.service.notification.StatusBarNotification;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.SwipeableView;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpCoreStartable$reuseSwipeHelper$build$1 implements NotificationSwipeHelper.NotificationCallback {
    public /* synthetic */ NotificationSwipeHelper.NotificationCallback $callback;
    public /* synthetic */ OplusHeadsUpCoreStartable this$0;

    public final boolean canChildBeDismissed(View view) {
        return this.$callback.canChildBeDismissed(view);
    }

    public final View getChildAtPosition(MotionEvent motionEvent) {
        NotificationEntry notificationEntryHeadsUpEntry = ((OplusHeadsUpNotificationInteractor) this.this$0.headsUpInteractor.get()).headsUpEntry();
        if (notificationEntryHeadsUpEntry != null) {
            return notificationEntryHeadsUpEntry.getRow();
        }
        return null;
    }

    public /* bridge */ /* synthetic */ int getConstrainSwipeStartPosition() {
        return super/*com.android.systemui.SwipeHelper.Callback*/.getConstrainSwipeStartPosition();
    }

    public float getFalsingThresholdFactor() {
        return this.$callback.getFalsingThresholdFactor();
    }

    public final ExpandableNotificationRow getSwipeTarget(ExpandableNotificationRow expandableNotificationRow) {
        return expandableNotificationRow;
    }

    public final float getTotalTranslationLength(View view) {
        return this.$callback.getTotalTranslationLength(view);
    }

    public final void handleChildViewDismissed(View view) {
        this.$callback.handleChildViewDismissed(view);
    }

    public final boolean handleSwipeableViewTranslation(SwipeableView swipeableView, float f) {
        return this.$callback.handleSwipeableViewTranslation(swipeableView, f);
    }

    public final boolean isAntiFalsingNeeded() {
        return this.$callback.isAntiFalsingNeeded();
    }

    public final void onBeginDrag(View view) {
        this.$callback.onBeginDrag(view);
    }

    public final void onChildDismissed(View view) {
        this.$callback.onChildDismissed(view);
    }

    public final void onChildSnapBackOvershoots() {
        this.$callback.onChildSnapBackOvershoots();
    }

    public final void onChildSnappedBack(float f, View view) {
        this.$callback.onChildSnappedBack(f, view);
    }

    public final void onDensityScaleChange(float f) {
        this.$callback.onDensityScaleChange(f);
    }

    public final void onDismiss() {
        this.$callback.onDismiss();
    }

    public final void onDragCancelled(View view) {
        this.$callback.onDragCancelled(view);
    }

    public final void onLongPressSent(View view) {
        this.$callback.onLongPressSent(view);
    }

    public final void onMagneticInteractionEnd(View view, float f, boolean z) {
        this.$callback.onMagneticInteractionEnd(view, f, z);
    }

    public final void onSnooze(StatusBarNotification statusBarNotification, NotificationSwipeActionHelper.SnoozeOption snoozeOption) {
        this.$callback.onSnooze(statusBarNotification, snoozeOption);
    }

    public final void resetMagneticStates() {
        this.$callback.resetMagneticStates();
    }

    public void setMagneticAndRoundableTargets(View view) {
        this.$callback.setMagneticAndRoundableTargets(view);
    }

    public final boolean shouldDismissQuickly() {
        return this.$callback.shouldDismissQuickly();
    }

    public final boolean updateSwipeProgress(View view, float f, boolean z) {
        return this.$callback.updateSwipeProgress(view, f, z);
    }
}
