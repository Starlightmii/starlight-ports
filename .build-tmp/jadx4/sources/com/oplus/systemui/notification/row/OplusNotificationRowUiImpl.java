package com.oplus.systemui.notification.row;

import android.app.Notification;
import android.content.Context;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationRowUiImpl extends OplusNotificationRowUi {
    public static final Companion Companion = new Companion();
    public static final String EXTRA_BIG_CONTENT_NULL_KEY = "EXTRA_VERSION_CODES_RESULT_NULL";
    private final Lazy notificationMaterialColorEx;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationRowUiImpl(Lazy lazy) {
        this.notificationMaterialColorEx = lazy;
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationRowUi
    public RemoteViews onExpandedViewCreated(NotificationEntry notificationEntry, Notification.Builder builder, RemoteViews remoteViews) {
        OplusNotificationEntryEx ex;
        UserHandle user;
        StatusBarNotification sbn;
        Notification notification;
        OplusNotificationEntryEx ex2;
        if (notificationEntry != null && (ex2 = notificationEntry.getEx()) != null) {
            ex2.setOldWrapper(builder.getExtras().getBoolean(EXTRA_BIG_CONTENT_NULL_KEY, false));
        }
        boolean z = ((notificationEntry == null || (sbn = notificationEntry.getSbn()) == null || (notification = sbn.getNotification()) == null) ? null : notification.contentView) != null;
        if (notificationEntry == null || (ex = notificationEntry.getEx()) == null || !((OplusNotificationEntryExImpl) ex).isOldWrapper) {
            return remoteViews;
        }
        StatusBarNotification sbn2 = notificationEntry.getSbn();
        if (OplusMultiAppUtils.isOplusMultiAppUser((sbn2 == null || (user = sbn2.getUser()) == null) ? null : Integer.valueOf(user.getIdentifier())) || z) {
            return null;
        }
        return remoteViews;
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationRowUi
    public void resolveHybridTextColor(Context context, HybridNotificationView hybridNotificationView) {
        if (hybridNotificationView != null) {
            if (!((Boolean) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).isMaterialColor().getValue()).booleanValue()) {
                TextView titleView = hybridNotificationView.getTitleView();
                if (titleView != null) {
                    titleView.setTextColor(NotificationColorUtil.getCouiColorPrimaryNeutral(context));
                }
                TextView textView = hybridNotificationView.getTextView();
                if (textView != null) {
                    textView.setTextColor(NotificationColorUtil.getCouiColorSecondaryNeutral(context));
                    return;
                }
                return;
            }
            int primaryColor = NotificationColorPalette.Companion.material().getPrimaryColor();
            TextView titleView2 = hybridNotificationView.getTitleView();
            if (titleView2 != null) {
                titleView2.setTextColor(primaryColor);
            }
            TextView textView2 = hybridNotificationView.getTextView();
            if (textView2 != null) {
                textView2.setTextColor(primaryColor);
            }
        }
    }
}
