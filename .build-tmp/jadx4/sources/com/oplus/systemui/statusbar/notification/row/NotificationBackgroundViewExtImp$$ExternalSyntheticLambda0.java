package com.oplus.systemui.statusbar.notification.row;

import android.graphics.drawable.Drawable;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationBackgroundViewExtImp$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ boolean f$0;
    public /* synthetic */ NotificationBackgroundViewExtImp f$1;
    public /* synthetic */ Drawable f$2;

    public final Object invoke() {
        boolean z = this.f$0;
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = this.f$1;
        Drawable drawable = this.f$2;
        if (!z) {
            NotificationBackgroundViewExtImp.Companion companion = NotificationBackgroundViewExtImp.Companion;
        } else if (notificationBackgroundViewExtImp.oplusPanoramicAodRepository.getPanoramicAodProgress() != 0.0f) {
            ExpandableView parent = notificationBackgroundViewExtImp.getBgView().getParent();
            ExpandableView expandableView = parent instanceof ExpandableView ? parent : null;
            if (expandableView != null && notificationBackgroundViewExtImp.oplusNotificationOverlayInteractor.isOverlayShowing(expandableView, true)) {
                drawable.getBounds().inset(1, 1);
            }
        }
        return Unit.INSTANCE;
    }
}
