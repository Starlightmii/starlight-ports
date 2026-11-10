package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import com.oplus.systemui.notification.notificationcenter.NotificationServiceCaller;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationCenterConnect extends NotificationServiceCaller {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationCenterConnect create(Context context, long j) {
            return new NotificationCenterConnectImpl(context, j);
        }
    }

    void onUserChanged(int i, Context context);

    void runAction(Function1 function1);

    @Override // com.oplus.systemui.notification.notificationcenter.NotificationServiceCaller
    /* synthetic */ Object runAndWaitResult(Object obj, Function1 function1);
}
