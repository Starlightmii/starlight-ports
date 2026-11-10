package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTopCoordinator$notificationTopIconRefreshListener$1 implements OnBeforeRenderListListener {
    public /* synthetic */ NotificationTopCoordinator this$0;

    public final void onBeforeRenderList(List list) {
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        NotificationTopCoordinator notificationTopCoordinator = this.this$0;
        int i = notificationTopCoordinator.oldLayoutDirection;
        if (i != layoutDirectionFromLocale) {
            ExifInterface$.ExternalSyntheticOutline1.m("oldLayoutDirection: ", i, layoutDirectionFromLocale, ", currentLayoutDirection: ", "NotificationTopCoordinator");
            notificationTopCoordinator.oldLayoutDirection = layoutDirectionFromLocale;
            NotificationTopListManage notificationTopListManage = notificationTopCoordinator.notificationTopListManage;
            NotificationTopCoordinator.access$resolveNotificationTopIcon(notificationTopCoordinator, notificationTopListManage != null ? ((NotificationTopListManageImpl) notificationTopListManage).getNotificationTopList() : null);
        }
    }
}
