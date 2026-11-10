package com.oplus.systemui.notification.interruption.fullscreenbanner.view;

import android.app.Notification;
import android.app.PendingIntent;
import android.view.View;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper$;
import com.oplus.systemui.notification.utlils.OplusNotificationUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FullScreenBanner$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public /* synthetic */ FullScreenBanner.Callback f$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        FullScreenBanner.Callback callback = this.f$0;
        FullScreenBanner.ReplySendListener replySendListener = FullScreenBanner.sSendBtnListener;
        FullScreenBannerHelper.AnonymousClass8 anonymousClass8 = (FullScreenBannerHelper.AnonymousClass8) callback;
        NotificationEntry notificationEntry = anonymousClass8.val$entry;
        FullScreenBannerHelper fullScreenBannerHelper = anonymousClass8.this$0;
        fullScreenBannerHelper.mBannerOperator = "1";
        if (fullScreenBannerHelper.mBannerZoom) {
            fullScreenBannerHelper.hideBanner(new Runnable[0]);
            fullScreenBannerHelper.pipReply(notificationEntry.getSbn(), null);
            return;
        }
        Notification notification = anonymousClass8.val$notification;
        PendingIntent pendingIntent = notification.fullScreenIntent;
        if (pendingIntent == null) {
            pendingIntent = notification.contentIntent;
        }
        if (pendingIntent != null) {
            FullScreenBannerHelper$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new FullScreenBannerHelper$.ExternalSyntheticLambda10();
            externalSyntheticLambda10.f$0 = fullScreenBannerHelper;
            externalSyntheticLambda10.f$1 = pendingIntent;
            VarHandle.storeStoreFence();
            fullScreenBannerHelper.hideBanner(externalSyntheticLambda10);
        } else {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "bannerClick intent == null");
            fullScreenBannerHelper.hideBanner(new Runnable[0]);
        }
        OplusNotificationUtil.notifyClickToNms(notificationEntry.getKey());
    }
}
