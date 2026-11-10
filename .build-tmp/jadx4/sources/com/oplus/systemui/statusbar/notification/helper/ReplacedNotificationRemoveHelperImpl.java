package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.helper.ReplacedNotificationRemoveHelper;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ReplacedNotificationRemoveHelperImpl extends ReplacedNotificationRemoveHelper {
    public static final Companion Companion = new Companion();
    private static final String FLUID_SERVICE_ID = "op_fluid_serviceId";
    private static final String TAG = "ReplacedNotificationRemoveHelperImpl";
    private final Context context;
    private final Map<String, NotificationRemoveData> notificationShowMap = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationRemoveData {
        private final List<NotificationListener.NotificationHandler> handlers;
        private final NotificationListener notificationListener;
        private StatusBarNotification statusBarNotification;

        public NotificationRemoveData(List<NotificationListener.NotificationHandler> list, StatusBarNotification statusBarNotification, NotificationListener notificationListener) {
            this.handlers = list;
            this.statusBarNotification = statusBarNotification;
            this.notificationListener = notificationListener;
        }

        public final List<NotificationListener.NotificationHandler> component1() {
            return this.handlers;
        }

        public final StatusBarNotification component2() {
            return this.statusBarNotification;
        }

        public final NotificationListener component3() {
            return this.notificationListener;
        }

        public final NotificationRemoveData copy(List<NotificationListener.NotificationHandler> list, StatusBarNotification statusBarNotification, NotificationListener notificationListener) {
            return new NotificationRemoveData(list, statusBarNotification, notificationListener);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NotificationRemoveData)) {
                return false;
            }
            NotificationRemoveData notificationRemoveData = (NotificationRemoveData) obj;
            return Intrinsics.areEqual(this.handlers, notificationRemoveData.handlers) && Intrinsics.areEqual(this.statusBarNotification, notificationRemoveData.statusBarNotification) && Intrinsics.areEqual(this.notificationListener, notificationRemoveData.notificationListener);
        }

        public final List<NotificationListener.NotificationHandler> getHandlers() {
            return this.handlers;
        }

        public final NotificationListener getNotificationListener() {
            return this.notificationListener;
        }

        public final StatusBarNotification getStatusBarNotification() {
            return this.statusBarNotification;
        }

        public final int hashCode() {
            int iHashCode = this.handlers.hashCode() * 31;
            StatusBarNotification statusBarNotification = this.statusBarNotification;
            return this.notificationListener.hashCode() + ((iHashCode + (statusBarNotification == null ? 0 : statusBarNotification.hashCode())) * 31);
        }

        public final void removeNotification() {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ReplacedNotificationRemoveHelperImpl-->".concat("removeNotification: " + this.statusBarNotification));
            }
            Iterator<NotificationListener.NotificationHandler> it = this.handlers.iterator();
            while (it.hasNext()) {
                it.next().onNotificationRemoved(this.statusBarNotification, this.notificationListener.getCurrentRanking());
            }
        }

        public final void setStatusBarNotification(StatusBarNotification statusBarNotification) {
            this.statusBarNotification = statusBarNotification;
        }

        public String toString() {
            return "NotificationRemoveData(handlers=" + this.handlers + ", statusBarNotification=" + this.statusBarNotification + ", notificationListener=" + this.notificationListener + ")";
        }
    }

    public ReplacedNotificationRemoveHelperImpl(Context context) {
        this.context = context;
    }

    public void checkShouldRemoveNotification(String str, int i, String str2) {
        NotificationRemoveData notificationRemoveDataRemove = this.notificationShowMap.remove(str + "|" + i + "|" + str2);
        if (notificationRemoveDataRemove == null) {
            return;
        }
        notificationRemoveDataRemove.removeNotification();
        notificationRemoveDataRemove.getHandlers().clear();
        NotificationStatisticUtil.collectFluidReplaceNotification(this.context, 1, str);
    }

    public void updateShowedNotificationData(List<? extends NotificationListener.NotificationHandler> list, StatusBarNotification statusBarNotification, NotificationListener notificationListener) {
        String str;
        String packageName = statusBarNotification.getPackageName();
        Bundle bundle = statusBarNotification.getNotification().extras;
        String string = bundle != null ? bundle.getString(FLUID_SERVICE_ID) : null;
        if (string != null) {
            str = packageName + "|" + statusBarNotification.getUserId() + "|" + string;
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        this.notificationShowMap.put(str, new NotificationRemoveData(arrayList, statusBarNotification, notificationListener));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ReplacedNotificationRemoveHelperImpl-->".concat("update notificationShowMap: " + this.notificationShowMap));
        }
    }
}
