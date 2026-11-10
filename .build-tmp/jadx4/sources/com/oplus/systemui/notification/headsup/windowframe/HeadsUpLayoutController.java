package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import com.android.systemui.shade.ShadeHeadsUpTracker;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManager;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpLayoutController implements ShadeHeadsUpTracker, HeadsUpTouchHelper.HeadsUpNotificationViewController, HeadsUpTouchHelper.Callback {
    private final Context context;
    private HeadsUpAppearanceController headsUpAppearanceController;
    private final HeadsUpLayout headsUpLayout;
    private final Lazy interactor;
    private ExpandableNotificationRow trackedHeadsUpNotificationRow;
    private final ArrayList<BiConsumer<ExpandableNotificationRow, String>> trackingHeadsUpListeners = new ArrayList<>();

    public HeadsUpLayoutController(Context context, Lazy lazy, HeadsUpLayout headsUpLayout, GroupExpansionManager groupExpansionManager) {
        this.context = context;
        this.interactor = lazy;
        this.headsUpLayout = headsUpLayout;
        ((GroupExpansionManagerImpl) groupExpansionManager).mOnGroupChangeListeners.add(headsUpLayout);
    }

    public void addTrackingHeadsUpListener(BiConsumer<ExpandableNotificationRow, String> biConsumer) {
        this.trackingHeadsUpListeners.add(biConsumer);
    }

    public ExpandableView getChildAtRawPosition(float f, float f2) {
        NotificationEntry notificationEntryHeadsUpEntry = ((OplusHeadsUpNotificationInteractor) this.interactor.get()).headsUpEntry();
        if (notificationEntryHeadsUpEntry != null) {
            return notificationEntryHeadsUpEntry.getRow();
        }
        return null;
    }

    public Context getContext() {
        return this.context;
    }

    public int getSplitStatusBarHeight() {
        return 0;
    }

    public ExpandableNotificationRow getTrackedHeadsUpNotification() {
        return this.trackedHeadsUpNotificationRow;
    }

    public final boolean hasHeadsUpFocus() {
        return this.headsUpLayout.hasHeadsUpFocus();
    }

    public final HeadsUpLayout headsUpLayout() {
        return this.headsUpLayout;
    }

    public final boolean isExpanded() {
        return false;
    }

    public final boolean isTrackingHeadsUp() {
        return this.headsUpLayout.isTrackingHeadsUp();
    }

    public final void notifyGroupChildAdded(ExpandableView expandableView) {
        this.headsUpLayout.notifyGroupChildAdded(expandableView);
    }

    public final void notifyGroupChildRemoved(ExpandableNotificationRow expandableNotificationRow) {
        this.headsUpLayout.notifyGroupChildRemoved(expandableNotificationRow);
    }

    public final void notifyMaterialColorChanged() {
        this.headsUpLayout.onMaterialColorChanged();
    }

    public void removeTrackingHeadsUpListener(BiConsumer<ExpandableNotificationRow, String> biConsumer) {
        this.trackingHeadsUpListeners.remove(biConsumer);
    }

    public void setHeadsUpAppearanceController(HeadsUpAppearanceController headsUpAppearanceController) {
        this.headsUpAppearanceController = headsUpAppearanceController;
    }

    public void setHeadsUpDraggingStartingHeight(int i) {
    }

    public void setNoticesClick(boolean z) {
    }

    public void setTrackedHeadsUp(ExpandableNotificationRow expandableNotificationRow, String str) {
        this.trackedHeadsUpNotificationRow = expandableNotificationRow;
    }

    public void setTrackingHeadsUpGroupBehaviorChild(boolean z) {
    }

    public final void startExpand(float f, float f2, float f3) {
    }

    public final void updateSensitiveness() {
        HeadsUpLayout headsUpLayout = this.headsUpLayout;
        if (headsUpLayout.needsAnimation) {
            headsUpLayout.hideSensitiveNeedsAnimation = true;
            headsUpLayout.requestChildrenUpdate();
        }
    }
}
