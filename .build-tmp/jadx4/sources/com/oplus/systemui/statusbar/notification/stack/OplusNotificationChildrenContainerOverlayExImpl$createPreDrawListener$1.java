package com.oplus.systemui.statusbar.notification.stack;

import android.view.ViewTreeObserver;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl$createPreDrawListener$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ OplusNotificationChildrenContainerOverlayExImpl this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ExpandableNotificationRow containingNotification;
        GroupOverlayContainerView groupOverlayContainerView;
        GroupOverlayContainerView groupOverlayContainerView2 = this.this$0.overlayContainer;
        if ((groupOverlayContainerView2 == null || groupOverlayContainerView2.isShown()) && ((containingNotification = this.this$0.getChildrenContainer().getContainingNotification()) == null || containingNotification.isShown())) {
            this.this$0.updateOverlayTranslation("pre draw");
            if (this.this$0.shouldDrawStackMask() && (groupOverlayContainerView = this.this$0.overlayContainer) != null) {
                groupOverlayContainerView.invalidate();
            }
        }
        return true;
    }
}
