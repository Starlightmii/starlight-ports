package com.oplus.systemui.notification.row;

import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusShelfEx {
    void tryForceDisableFeature(NotificationShelf notificationShelf, NotificationIconContainer notificationIconContainer);

    default boolean updateAppearanceJustClipChildren(NotificationShelf notificationShelf, NotificationStackScrollLayout notificationStackScrollLayout, AmbientState ambientState, int i, Function3 function3, Function1 function1) {
        return false;
    }
}
