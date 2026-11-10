package com.oplus.systemui.notification.data;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.NotificationListenerController;
import com.android.systemui.statusbar.NotificationListener;
import com.oplus.systemui.notification.smallreply.SmallWindowReplyController;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationListenerPlugin implements CoreStartable, NotificationListenerController {
    private final Context context;
    private final List<StatusBarNotification> needDelayList = new ArrayList();
    private final Lazy notificationCenterHelper;
    private final NotificationListener notificationListener;
    private final NotificationListenerDelayImpl notificationListenerExt;
    private final SmallWindowReplyController smallWindowReplyController;

    public OplusNotificationListenerPlugin(NotificationListener notificationListener, Context context, Lazy lazy, NotificationListenerDelayImpl notificationListenerDelayImpl, SmallWindowReplyController smallWindowReplyController) {
        this.notificationListener = notificationListener;
        this.context = context;
        this.notificationCenterHelper = lazy;
        this.notificationListenerExt = notificationListenerDelayImpl;
        this.smallWindowReplyController = smallWindowReplyController;
    }

    public StatusBarNotification[] getActiveNotifications(StatusBarNotification[] statusBarNotificationArr) {
        if (this.needDelayList.isEmpty()) {
            return super.getActiveNotifications(statusBarNotificationArr);
        }
        StatusBarNotification[] activeNotifications = super.getActiveNotifications(statusBarNotificationArr);
        activeNotifications.getClass();
        List mutableList = ArraysKt___ArraysKt.toMutableList(activeNotifications);
        getActiveNotifications.1 r0 = new getActiveNotifications.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        mutableList.removeIf(r0);
        this.needDelayList.clear();
        return (StatusBarNotification[]) mutableList.toArray(new StatusBarNotification[0]);
    }

    public void onListenerConnected(NotificationListenerController.NotificationProvider notificationProvider) {
        StatusBarNotification[] activeNotifications;
        this.needDelayList.clear();
        if (notificationProvider != null && (activeNotifications = notificationProvider.getActiveNotifications()) != null) {
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                NotificationListenerDelayImpl notificationListenerDelayImpl = this.notificationListenerExt;
                statusBarNotification.getClass();
                NotificationListener notificationListener = this.notificationListener;
                if (notificationListenerDelayImpl.onDelayNotificationPost(statusBarNotification, notificationListener.mNotificationHandlers, notificationListener)) {
                    this.needDelayList.add(statusBarNotification);
                }
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.needDelayList.size(), "onListenerConnected::check need delay size: ", "OplusNotificationListenerPlugin-->", "SystemUi--Notification");
        }
    }

    public boolean onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
        if (i != 1) {
            return false;
        }
        ((NotificationCenterHelper) this.notificationCenterHelper.get()).notifyChannelOrGroupAdded(str, userHandle, notificationChannel, i);
        return false;
    }

    public boolean onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        NotificationListenerDelayImpl notificationListenerDelayImpl = this.notificationListenerExt;
        NotificationListener notificationListener = this.notificationListener;
        return notificationListenerDelayImpl.onDelayNotificationPost(statusBarNotification, notificationListener.mNotificationHandlers, notificationListener) || this.notificationListenerExt.onInterceptNotificationPosted(statusBarNotification, rankingMap, this.notificationListener.mNotificationHandlers);
    }

    public boolean onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        NotificationListenerDelayImpl notificationListenerDelayImpl = this.notificationListenerExt;
        NotificationListener notificationListener = this.notificationListener;
        if (notificationListenerDelayImpl.onDelayNotificationRemove(statusBarNotification, notificationListener.mNotificationHandlers, notificationListener)) {
            return true;
        }
        this.notificationListenerExt.onNotificationRemovedBefore(statusBarNotification);
        return false;
    }

    public final void start() {
        this.notificationListener.onPluginConnected(this);
        this.smallWindowReplyController.start();
    }
}
