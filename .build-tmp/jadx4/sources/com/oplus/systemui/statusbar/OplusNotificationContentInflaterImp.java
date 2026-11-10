package com.oplus.systemui.statusbar;

import android.app.Notification;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import com.android.systemui.statusbar.notification.row.OplusNotificationContentInflaterEx;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationContentInflaterImp implements OplusNotificationContentInflaterEx {
    public static final Companion Companion = new Companion();
    public static final String EXTRA_OPLUS_EXPREGION = "oplus.isExpRegion";
    public static final String TAG = "OplusNotificationContentInflaterImp";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusNotificationContentInflaterImp$addFieldsFromContext$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ ApplicationInfo $appInfo;
        public /* synthetic */ CountDownLatch $latch;
        public /* synthetic */ Notification $notification;

        @Override // java.lang.Runnable
        public final void run() {
            try {
                Notification.addFieldsFromContext(this.$appInfo, this.$notification);
                Bundle bundle = this.$notification.extras;
                FeatureOption.INSTANCE.getClass();
                bundle.putBoolean(OplusNotificationContentInflaterImp.EXTRA_OPLUS_EXPREGION, FeatureOption.isExpRegion());
            } finally {
                this.$latch.countDown();
            }
        }
    }

    public void addFieldsFromContext(ApplicationInfo applicationInfo, Notification notification) throws InterruptedException {
        if (applicationInfo == null || notification == null) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Handler handler = new Handler(SystemUIThreadUtil.getMainLooper());
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$appInfo = applicationInfo;
        anonymousClass1.$notification = notification;
        anonymousClass1.$latch = countDownLatch;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
        countDownLatch.await();
    }
}
