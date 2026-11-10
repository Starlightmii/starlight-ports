package com.oplus.systemui.statusbar.notification.helper;

import android.content.Intent;
import com.oplusos.notification.INotificationCenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCenterHelper$$ExternalSyntheticLambda1 implements Function1 {
    public /* synthetic */ String f$0;
    public /* synthetic */ Intent f$1;

    public final Object invoke(Object obj) {
        ((INotificationCenter) obj).notifyBrodcast(this.f$0, this.f$1);
        return Unit.INSTANCE;
    }
}
