package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.content.Context;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.OplusNotificationViewWrapperEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationViewWrapperExImpl implements OplusNotificationViewWrapperEx {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusNotificationViewWrapperExImpl";
    private NotificationViewWrapper base;
    public Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public void bindContext(Context context) {
        setContext(context);
    }

    public Context getBindContext() {
        return getContext();
    }

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        return null;
    }

    public void onReinflated() {
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    /* JADX INFO: renamed from: getBase, reason: merged with bridge method [inline-methods] */
    public NotificationViewWrapper m459getBase() {
        return this.base;
    }

    public void setBase(NotificationViewWrapper notificationViewWrapper) {
        this.base = notificationViewWrapper;
    }
}
