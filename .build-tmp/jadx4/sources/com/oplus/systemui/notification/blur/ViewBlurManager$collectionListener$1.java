package com.oplus.systemui.notification.blur;

import android.view.View;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewBlurManager$collectionListener$1 implements NotifCollectionListener {
    public /* synthetic */ ViewBlurManager this$0;

    public final void onEntryCleanUp(NotificationEntry notificationEntry) {
        View viewFindViewById;
        NotificationMenuRowPlugin provider;
        ArrayList menuItems;
        NotificationBackgroundView notificationBackgroundView;
        ExpandableNotificationRow row;
        ViewBlurManager viewBlurManager = this.this$0;
        ExpandableNotificationRow row2 = notificationEntry.getRow();
        int i = 0;
        if (row2 != null && (notificationBackgroundView = ((ActivatableNotificationView) row2).mBackgroundNormal) != null) {
            ExpandableNotificationRow row3 = notificationEntry.getRow();
            viewBlurManager.releaseBlurProxyForView(notificationBackgroundView, !((row3 != null && row3.mPinnedStatus.isPinned()) || ((row = notificationEntry.getRow()) != null && row.mHeadsupDisappearRunning)), "backgroundNormal EntryCleanUp");
        }
        ExpandableNotificationRow row4 = notificationEntry.getRow();
        if (row4 != null && (provider = row4.getProvider()) != null && (menuItems = provider.getMenuItems(viewBlurManager.context)) != null) {
            int size = menuItems.size();
            while (i < size) {
                Object obj = menuItems.get(i);
                i++;
                View menuView = ((NotificationMenuRowPlugin.MenuItem) obj).getMenuView();
                if (menuView != null) {
                    viewBlurManager.releaseBlurProxyForView(menuView, true, "menu EntryCleanUp");
                }
            }
        }
        ExpandableNotificationRow row5 = notificationEntry.getRow();
        if (row5 == null || (viewFindViewById = row5.findViewById(2131364385)) == null) {
            return;
        }
        viewBlurManager.releaseBlurProxyForView(viewFindViewById, true, "expandGroupHeader EntryCleanUp");
    }
}
