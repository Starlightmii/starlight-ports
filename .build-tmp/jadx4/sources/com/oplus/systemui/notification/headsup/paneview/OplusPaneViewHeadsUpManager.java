package com.oplus.systemui.notification.headsup.paneview;

import android.graphics.Region;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.AnimationStateHandler;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpPhoneListenerChange;
import com.android.systemui.statusbar.notification.headsup.PinnedStatus;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import java.io.PrintWriter;
import java.util.stream.Stream;
import kotlin.NotImplementedError;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPaneViewHeadsUpManager implements HeadsUpManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusPaneViewHeadsUpMan";
    private final HeadsUpManager origin;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPaneViewHeadsUpManager(HeadsUpManager headsUpManager) {
        this.origin = headsUpManager;
    }

    public static void assertNotImpl() {
        throw new UnsupportedOperationException("An operation is not implemented: Not yet implemented");
    }

    public void addHeadsUpPhoneListener(OnHeadsUpPhoneListenerChange onHeadsUpPhoneListenerChange) {
        assertNotImpl();
        throw null;
    }

    public void addListener(OnHeadsUpChangedListener onHeadsUpChangedListener) {
    }

    public void addSwipedOutNotification(String str) {
        assertNotImpl();
        throw null;
    }

    public boolean canRemoveImmediately(String str) {
        assertNotImpl();
        throw null;
    }

    public void cleanSnoozePkgs(String str) {
        assertNotImpl();
        throw null;
    }

    public int compare(NotificationEntry notificationEntry, NotificationEntry notificationEntry2) {
        assertNotImpl();
        throw null;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        assertNotImpl();
        throw null;
    }

    public final void extendHeadsUp() {
        assertNotImpl();
        throw null;
    }

    public Stream<NotificationEntry> getAllEntries() {
        assertNotImpl();
        throw null;
    }

    public long getEarliestRemovalTime(String str) {
        assertNotImpl();
        throw null;
    }

    public NotificationEntry getTopEntry() {
        assertNotImpl();
        throw null;
    }

    public Region getTouchableRegion() {
        assertNotImpl();
        throw null;
    }

    public boolean hasMismatchedEntry(NotificationEntry notificationEntry) {
        assertNotImpl();
        throw null;
    }

    public final boolean hasPinnedHeadsUp() {
        return false;
    }

    public final boolean isHeadsUpAnimatingAwayValue() {
        assertNotImpl();
        throw null;
    }

    public boolean isHeadsUpEntry(String str) {
        assertNotImpl();
        throw null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public boolean isPinnedByUser(String str) throws NotImplementedError {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public boolean isSnoozed(String str) {
        assertNotImpl();
        throw null;
    }

    public boolean isSticky(String str) {
        assertNotImpl();
        throw null;
    }

    public StateFlow isTrackingHeadsUp() {
        return StateFlowKt.MutableStateFlow(Boolean.FALSE);
    }

    public void onEntryAnimatingAwayEnded(NotificationEntry notificationEntry) {
        assertNotImpl();
        throw null;
    }

    public final void onExpandingFinished() {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public PinnedStatus pinnedHeadsUpStatus() throws NotImplementedError {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public void releaseAllImmediately(String str) {
        assertNotImpl();
        throw null;
    }

    public void removeListener(OnHeadsUpChangedListener onHeadsUpChangedListener) {
        assertNotImpl();
        throw null;
    }

    public boolean removeNotification(String str, boolean z, String str2) {
        assertNotImpl();
        throw null;
    }

    public void setAnimationStateHandler(AnimationStateHandler animationStateHandler) {
        assertNotImpl();
        throw null;
    }

    public void setExpanded(String str, ExpandableNotificationRow expandableNotificationRow, boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setGutsShown(NotificationEntry notificationEntry, boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setHeadsUpAnimatingAway(boolean z) {
        assertNotImpl();
        throw null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public void setHeadsUpDismissTimerPaused(String str, boolean z) throws NotImplementedError {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public void setHeadsUpGoingAwayAnimationsAllowed(boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setPulled(NotificationEntry notificationEntry, boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setRemoteInputActive(NotificationEntry notificationEntry, boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setTrackingHeadsUp(boolean z) {
        assertNotImpl();
        throw null;
    }

    public void setUser(int i) {
        assertNotImpl();
        throw null;
    }

    public void setUserActionMayIndirectlyRemove(String str) {
        assertNotImpl();
        throw null;
    }

    public boolean shouldSwallowClick(String str) {
        assertNotImpl();
        throw null;
    }

    public void showNotification(NotificationEntry notificationEntry, boolean z) {
        assertNotImpl();
        throw null;
    }

    public final void snooze() {
        assertNotImpl();
        throw null;
    }

    public void unpinAll(boolean z, String str) {
        this.origin.unpinAll(z, str);
    }

    public void unpinAllExceptPickedChild(boolean z, NotificationEntry notificationEntry) {
        assertNotImpl();
        throw null;
    }

    public void updateNotification(String str, PinnedStatus pinnedStatus) {
        assertNotImpl();
        throw null;
    }

    public boolean isSnoozed(StatusBarNotification statusBarNotification) {
        assertNotImpl();
        throw null;
    }

    public boolean removeNotification(String str, boolean z, boolean z2, String str2) {
        assertNotImpl();
        throw null;
    }

    public void snooze(StatusBarNotification statusBarNotification) {
        assertNotImpl();
        throw null;
    }
}
