package com.oplus.systemui.statusbar.notification.customcard;

import android.view.View;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowControllerManager$setCustomContainerControllerList$3$test$lambda$5$$inlined$doOnDetach$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ NotificationBackgroundView $this_doOnDetach;
    public /* synthetic */ OplusCustomRowControllerManager this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.$this_doOnDetach.removeOnAttachStateChangeListener(this);
        this.this$0.viewBlurManager.releaseBlurProxyForView(view, true, "customBackgroundNormal remove");
    }
}
