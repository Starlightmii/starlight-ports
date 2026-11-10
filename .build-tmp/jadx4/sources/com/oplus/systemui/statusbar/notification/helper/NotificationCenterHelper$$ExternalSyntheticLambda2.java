package com.oplus.systemui.statusbar.notification.helper;

import com.oplusos.notification.INotificationCenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCenterHelper$$ExternalSyntheticLambda2 implements Function1 {
    public /* synthetic */ String f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ int f$2;

    public final Object invoke(Object obj) {
        ((INotificationCenter) obj).setStowOption(this.f$0, this.f$1, this.f$2);
        return Unit.INSTANCE;
    }
}
