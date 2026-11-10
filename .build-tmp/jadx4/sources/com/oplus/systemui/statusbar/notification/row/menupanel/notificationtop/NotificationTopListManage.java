package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationTopListManage {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationTopListManage newInstance(Context context) {
            return NotificationTopListManageImpl.Companion.getInstance(context);
        }
    }

    void addNotificationTopListListener(NotificationTopListManageImpl.NotificationTopListListener notificationTopListListener);

    int getCurrentUserId();

    Set<String> getNotificationTopDiffSet();

    int getNotificationTopIndex(String str);

    Set<String> getNotificationTopList();

    int getNotificationTopNum();

    boolean isNotificationTop(String str, Integer num);

    void notificationTopBatchOperation(Context context, String str, Integer num, Set<String> set);

    void notificationTopOperation(Context context, String str, Integer num, boolean z);
}
