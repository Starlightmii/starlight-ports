package com.oplus.systemui.statusbar.phone;

import android.view.View;
import com.android.systemui.statusbar.phone.NotificationIconContainer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSideContainerController$layoutListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ StartSideContainerController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        NotificationIconContainer notificationIconContainer;
        int iAccess$endSideContainerRealWidth = StartSideContainerController.access$endSideContainerRealWidth(this.this$0);
        int width = this.this$0.endSideContent.getWidth();
        StartSideContainerController startSideContainerController = this.this$0;
        int i9 = 6;
        if (iAccess$endSideContainerRealWidth != width) {
            if (iAccess$endSideContainerRealWidth <= startSideContainerController.endSideContent.getWidth() || this.this$0.endSideContent.getVisibility() == 8 || (notificationIconContainer = this.this$0.notificationIconContainer) == null) {
                return;
            }
            StartSideContainerController startSideContainerController2 = this.this$0;
            int width2 = (notificationIconContainer.getWidth() + (iAccess$endSideContainerRealWidth - startSideContainerController2.endSideContent.getWidth())) / startSideContainerController2.iconSize;
            if (width2 <= 6) {
                i9 = width2 < 3 ? 3 : width2;
            }
            StartSideContainerController.access$updateNotificationsWidth(startSideContainerController2, i9);
            return;
        }
        NotificationIconContainer notificationIconContainer2 = startSideContainerController.notificationIconContainer;
        if (notificationIconContainer2 != null) {
            StartSideContainerController startSideContainerController3 = this.this$0;
            int width3 = (notificationIconContainer2.getWidth() - startSideContainerController3.getNotVisibleSystemIconWidth()) / startSideContainerController3.iconSize;
            if (width3 <= 3) {
                i9 = 3;
            } else if (width3 <= 6) {
                i9 = width3;
            }
            StartSideContainerController.access$updateNotificationsWidth(startSideContainerController3, i9);
        }
    }
}
