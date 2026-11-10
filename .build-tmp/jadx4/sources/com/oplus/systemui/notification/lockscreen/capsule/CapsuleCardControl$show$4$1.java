package com.oplus.systemui.notification.lockscreen.capsule;

import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleCardControl$show$4$1 implements Runnable {
    public /* synthetic */ boolean $animate;
    public /* synthetic */ OplusCustomRow $stackView;
    public /* synthetic */ CapsuleItemView $view;
    public /* synthetic */ CapsuleCardControl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        ((NotificationCapsuleContainerControllerImpl) this.this$0.capsuleContainerController.get()).generateImmersiveAnimation(this.$view, this.$stackView, this.$animate, false);
    }
}
