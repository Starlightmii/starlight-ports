package com.oplus.systemui.notification.data;

import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.android.systemui.statusbar.notification.helper.ReplacedNotificationRemoveHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationListenerDelayImpl$onDelayNotificationPost$runnable$1 implements Runnable {
    public /* synthetic */ boolean $isSeedlingAnimating;
    public /* synthetic */ List $notificationHandlers;
    public /* synthetic */ NotificationListener $notificationListener;
    public /* synthetic */ StatusBarNotification $sbn;
    public /* synthetic */ boolean $shouldReplacedByFluid;
    public /* synthetic */ NotificationListenerDelayImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        StatusBarNotification statusBarNotification = this.$sbn;
        boolean z = this.$isSeedlingAnimating;
        boolean z2 = this.$shouldReplacedByFluid;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("NotificationListenerDelayImpl-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("onDelayNotificationPost delay execute for pkg: ", statusBarNotification.getPackageName(), ", isAnimating: ", z, ", shouldReplacedByFluid: "), z2);
        }
        ((NotificationApplyDelayHelper) this.this$0.getNotificationApplyDelayHelper().get()).removeNotificationApplyTask(this.$sbn.getKey());
        if (((NotificationApplyDelayHelper) this.this$0.getNotificationApplyDelayHelper().get()).isNotificationCardShow(this.$sbn)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationListenerDelayImpl-->isNotificationCardShow, return");
                return;
            }
            return;
        }
        if (this.this$0.onInterceptNotificationPosted(this.$sbn, this.$notificationListener.getCurrentRanking(), this.$notificationHandlers)) {
            return;
        }
        if (this.$shouldReplacedByFluid) {
            ((ReplacedNotificationRemoveHelper) this.this$0.getReplacedNotificationRemoveHelper().get()).updateShowedNotificationData(this.$notificationHandlers, this.$sbn, this.$notificationListener);
        }
        Iterator it = this.$notificationHandlers.iterator();
        while (it.hasNext()) {
            try {
                ((NotificationListener.NotificationHandler) it.next()).onNotificationPosted(this.$sbn, this.$notificationListener.getCurrentRanking());
            } catch (IllegalArgumentException unused) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "ranking map is wrong, key: ", this.$sbn.getKey(), LogUtil.TAG_NOTIFICATION, "NotificationListenerDelayImpl");
            }
        }
    }
}
