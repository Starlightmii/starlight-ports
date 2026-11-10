package com.oplus.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.util.JankUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableNotificationRowExImpl$onZoomTransitionAnimationStart$jankTask$1 implements Runnable {
    public /* synthetic */ ExpandableNotificationRow $notification;

    @Override // java.lang.Runnable
    public final void run() {
        JankUtils.gfxSceneBegin$default(this.$notification.getContext(), 47, "BANNER_NF_SW_OPEN", 4L);
    }
}
