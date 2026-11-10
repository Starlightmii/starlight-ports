package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda8 implements Function1 {
    public /* synthetic */ NotificationStackScrollLayoutExtImpl f$0;
    public /* synthetic */ NotificationStackScrollLayout f$1;

    public final Object invoke(Object obj) {
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.f$0;
        NotificationStackScrollLayout notificationStackScrollLayout = this.f$1;
        float fFloatValue = ((Float) obj).floatValue();
        NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
        notificationStackScrollLayoutExtImpl.updatePivot();
        notificationStackScrollLayout.setScaleX(fFloatValue);
        notificationStackScrollLayout.setScaleY(fFloatValue);
        return Unit.INSTANCE;
    }
}
