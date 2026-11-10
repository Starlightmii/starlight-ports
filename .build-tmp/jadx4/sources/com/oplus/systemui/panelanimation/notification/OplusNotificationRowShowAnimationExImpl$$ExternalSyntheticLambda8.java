package com.oplus.systemui.panelanimation.notification;

import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerOverlayEx;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda8 implements Function2 {
    public /* synthetic */ OplusNotificationRowShowAnimationExImpl f$0;
    public /* synthetic */ boolean f$1;

    public final Object invoke(Object obj, Object obj2) {
        NotificationChildrenContainer childrenContainer;
        NotificationChildrenContainerOverlayEx notificationChildrenContainerOverlayEx;
        boolean z = this.f$1;
        ExpandableNotificationRow expandableNotificationRow = (View) obj;
        ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow instanceof ExpandableNotificationRow ? expandableNotificationRow : null;
        if (expandableNotificationRow2 != null && (childrenContainer = expandableNotificationRow2.getChildrenContainer()) != null && (notificationChildrenContainerOverlayEx = childrenContainer.mOverlayEx) != null) {
            notificationChildrenContainerOverlayEx.finishRunningGroupExpansionAnimationsForPanelTransition(z, expandableNotificationRow2);
        }
        return Unit.INSTANCE;
    }
}
