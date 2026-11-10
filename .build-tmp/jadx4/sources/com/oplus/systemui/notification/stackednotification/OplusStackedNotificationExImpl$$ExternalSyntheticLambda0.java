package com.oplus.systemui.notification.stackednotification;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusStackedNotificationExImpl$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ OplusStackedNotificationExImpl f$0;

    public final Object invoke(Object obj) {
        OplusStackedNotificationExImpl oplusStackedNotificationExImpl = this.f$0;
        ExpandableView expandableView = (ExpandableView) obj;
        NotificationStackedRuler.Companion.requestStackedScale(expandableView.getViewState(), 1.0f);
        expandableView.getViewState().mExpandableViewStateEx.setNotificationStackedProgress(0.0f);
        if (oplusStackedNotificationExImpl.isStackedNotification$1() && !expandableView.isChildInGroup()) {
            expandableView.getViewState().setAlpha(1.0f, "OplusStackedNotificationExImpl.clearNotificationStackedState");
        }
        return Unit.INSTANCE;
    }
}
