package com.oplus.systemui.notification.row;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ NotificationMenuRowExtImpl this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        view.removeOnLayoutChangeListener(this);
        NotificationMenuRowMetaBallController notificationMenuRowMetaBallController = this.this$0.metaBallController;
        if (notificationMenuRowMetaBallController != null) {
            NotificationMenuRowMetaBallController notificationMenuRowMetaBallController2 = this.this$0.metaBallController;
            notificationMenuRowMetaBallController.updateMetaBallPosition(notificationMenuRowMetaBallController2 != null ? notificationMenuRowMetaBallController2.getLastTranslationX() : 0.0f);
        }
    }
}
