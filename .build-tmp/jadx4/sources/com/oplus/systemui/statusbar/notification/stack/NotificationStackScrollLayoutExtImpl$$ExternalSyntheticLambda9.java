package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda9(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return ((NotificationStackScrollLayoutExtImpl) obj2).getCurrentAlphaAndScale();
            case 1:
                return ((NotificationStackScrollLayoutExtImpl) obj2).getCurrentAlphaAndScale();
            default:
                float fFloatValue = ((Float) obj).floatValue();
                NotificationStackScrollLayoutExtImpl.Companion companion = NotificationStackScrollLayoutExtImpl.Companion;
                ((NotificationStackScrollLayout) obj2).setAlpha(fFloatValue);
                return Unit.INSTANCE;
        }
    }
}
