package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.LruCache;
import com.oplus.systemui.notification.row.oplusgroup.AppIconGroupColorManager;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationResolverExImpl$mPackageChangeReceiver$1 implements IReceiverListener {
    public /* synthetic */ NotificationResolverExImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.NotificationResolverExImpl$mPackageChangeReceiver$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Intent $intent;
        public /* synthetic */ NotificationResolverExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            String encodedSchemeSpecificPart;
            AppIconGroupColorManager appIconGroupColorManager = this.this$0.appIconGroupColorManager;
            int userId = this.this$0.getUserTracker().getUserId();
            Uri data = this.$intent.getData();
            if (data == null || (encodedSchemeSpecificPart = data.getEncodedSchemeSpecificPart()) == null) {
                encodedSchemeSpecificPart = "";
            }
            appIconGroupColorManager.clear(userId, encodedSchemeSpecificPart);
        }
    }

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        String encodedSchemeSpecificPart;
        NotificationResolverExImpl notificationResolverExImpl = this.this$0;
        String action = intent.getAction();
        if (action != null && action.hashCode() == 525384130 && action.equals("android.intent.action.PACKAGE_REMOVED")) {
            LruCache lruCache = notificationResolverExImpl.packages;
            int userId = notificationResolverExImpl.getUserTracker().getUserId();
            Uri data = intent.getData();
            if (data == null || (encodedSchemeSpecificPart = data.getEncodedSchemeSpecificPart()) == null) {
                encodedSchemeSpecificPart = "";
            }
            lruCache.remove(userId + encodedSchemeSpecificPart);
            Handler handler = notificationResolverExImpl.mainHandler;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = notificationResolverExImpl;
            anonymousClass1.$intent = intent;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
        }
    }
}
