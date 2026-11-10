package com.oplus.systemui.notification.smallreply;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.systemui.statusbar.seeding.livealert.LiveAlertService;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationActionReceiver$$ExternalSyntheticLambda0 implements IReceiverListener {
    public /* synthetic */ NotificationActionReceiver f$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        NotificationActionReceiver notificationActionReceiver = this.f$0;
        notificationActionReceiver.getClass();
        String action = intent.getAction();
        Log.i("NotificationActionReceiver", "handleReceive: receive action: " + action);
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "NotificationActionReceiver", "receive action: " + action);
        if (!TextUtils.isEmpty(action) && "oppo.intent.action.NOTIFICATION_ACTION_CLICK".equals(intent.getAction())) {
            PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pendingIntent");
            String stringExtra = intent.getStringExtra(LiveAlertService.EXTRA_PACKAGE_NAME);
            int intExtra = intent.getIntExtra("userId", 0);
            String stringExtra2 = intent.getStringExtra("entryKey");
            Handler handler = new Handler(Looper.getMainLooper());
            NotificationActionReceiver$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new NotificationActionReceiver$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = notificationActionReceiver;
            externalSyntheticLambda1.f$1 = pendingIntent;
            externalSyntheticLambda1.f$2 = context;
            externalSyntheticLambda1.f$3 = stringExtra;
            externalSyntheticLambda1.f$4 = intExtra;
            externalSyntheticLambda1.f$5 = intent;
            externalSyntheticLambda1.f$6 = stringExtra2;
            VarHandle.storeStoreFence();
            handler.post(externalSyntheticLambda1);
        }
    }
}
