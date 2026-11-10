package com.oplus.systemui.statusbar.notification.helper;

import com.oplusos.notification.INotificationCenter;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCenterConnectImpl$runWithRestart$2$1$1 implements Function1 {
    public /* synthetic */ Function1 $callback;
    public /* synthetic */ CancellableContinuationImpl $consumer;

    public final Object invoke(Object obj) {
        this.$consumer.resumeWith(Result.constructor-impl(this.$callback.invoke((INotificationCenter) obj)));
        return Unit.INSTANCE;
    }
}
