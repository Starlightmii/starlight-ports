package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import android.os.SystemClock;
import android.os.UserHandle;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.oplus.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinatorExImpl;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTopCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationTopCoordinator";
    private final Context context;
    private final NotificationTopCoordinator$notificationTopFilter$1 notificationTopFilter;
    private final OnBeforeRenderListListener notificationTopIconRefreshListener;
    private final NotificationTopCoordinator$notificationTopListListener$1 notificationTopListListener;
    private final NotificationTopListManage notificationTopListManage;
    public int oldLayoutDirection;
    public NotifPipeline pipline;
    private final VisualStabilityCoordinatorExImpl visualStabilityCoordinatorExImpl;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationTopCoordinator(Context context, VisualStabilityCoordinatorExImpl visualStabilityCoordinatorExImpl) {
        this.context = context;
        this.visualStabilityCoordinatorExImpl = visualStabilityCoordinatorExImpl;
        this.notificationTopListManage = NotificationTopListManage.Companion.newInstance(context);
        NotificationTopCoordinator$notificationTopListListener$1 notificationTopCoordinator$notificationTopListListener$1 = new NotificationTopCoordinator$notificationTopListListener$1();
        notificationTopCoordinator$notificationTopListListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notificationTopListListener = notificationTopCoordinator$notificationTopListListener$1;
        this.notificationTopFilter = new NotificationTopCoordinator$notificationTopFilter$1("NotificationTopCoordinatortopNotifi");
        NotificationTopCoordinator$notificationTopIconRefreshListener$1 notificationTopCoordinator$notificationTopIconRefreshListener$1 = new NotificationTopCoordinator$notificationTopIconRefreshListener$1();
        notificationTopCoordinator$notificationTopIconRefreshListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notificationTopIconRefreshListener = notificationTopCoordinator$notificationTopIconRefreshListener$1;
    }

    public static final void access$resolveNotificationTopIcon(NotificationTopCoordinator notificationTopCoordinator, Set set) {
        NotificationViewWrapper notificationViewWrapper;
        if (set == null) {
            return;
        }
        NotifPipeline notifPipeline = notificationTopCoordinator.pipline;
        if (notifPipeline == null) {
            notifPipeline = null;
        }
        Collection allNotifs = notifPipeline.getAllNotifs();
        ArrayList arrayList = new ArrayList();
        for (Object obj : allNotifs) {
            NotificationEntry notificationEntry = (NotificationEntry) obj;
            if (set.contains(NotificationTopUtils.getNotificationTopKey(notificationEntry.getSbn().getPackageName(), Integer.valueOf(UserHandle.getUserId(notificationEntry.getSbn().getUid()))))) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            NotificationEntry notificationEntry2 = (NotificationEntry) obj2;
            ExpandableNotificationRow expandableNotificationRow = notificationEntry2.row;
            if (expandableNotificationRow == null || !expandableNotificationRow.mIsSummaryWithChildren) {
                ExpandableNotificationRow row = notificationEntry2.getRow();
                NotificationContentView[] layouts = row != null ? row.getLayouts() : null;
                if (layouts != null) {
                    for (NotificationContentView notificationContentView : layouts) {
                        NotificationViewWrapper visibleWrapper = notificationContentView.getVisibleWrapper(1);
                        if (visibleWrapper != null) {
                            visibleWrapper.onReinflated();
                        }
                        NotificationViewWrapper visibleWrapper2 = notificationContentView.getVisibleWrapper(2);
                        if (visibleWrapper2 != null) {
                            visibleWrapper2.onReinflated();
                        }
                        NotificationViewWrapper visibleWrapper3 = notificationContentView.getVisibleWrapper(0);
                        if (visibleWrapper3 != null) {
                            visibleWrapper3.onReinflated();
                        }
                    }
                }
            } else {
                ExpandableNotificationRow row2 = notificationEntry2.getRow();
                if (row2 != null && (notificationViewWrapper = row2.getNotificationViewWrapper()) != null) {
                    notificationViewWrapper.onReinflated();
                }
            }
        }
    }

    public static final void access$updateNotificationUI(NotificationTopCoordinator notificationTopCoordinator, Set set) {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            notificationTopCoordinator.visualStabilityCoordinatorExImpl.temporarilyAllowReOrder((String) it.next(), SystemClock.uptimeMillis());
        }
        notificationTopCoordinator.notificationTopFilter.invalidateList(TAG);
    }

    public void attach(NotifPipeline notifPipeline) {
        this.pipline = notifPipeline;
        if (notifPipeline == null) {
            notifPipeline = null;
        }
        notifPipeline.addFinalizeFilter(this.notificationTopFilter);
        NotifPipeline notifPipeline2 = this.pipline;
        (notifPipeline2 != null ? notifPipeline2 : null).addOnBeforeRenderListListener(this.notificationTopIconRefreshListener);
        NotificationTopListManage notificationTopListManage = this.notificationTopListManage;
        if (notificationTopListManage != null) {
            ((NotificationTopListManageImpl) notificationTopListManage).addNotificationTopListListener(this.notificationTopListListener);
        }
    }
}
