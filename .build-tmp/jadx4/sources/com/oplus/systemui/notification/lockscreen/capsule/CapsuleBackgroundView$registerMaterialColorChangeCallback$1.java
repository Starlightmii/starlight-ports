package com.oplus.systemui.notification.lockscreen.capsule;

import com.oplus.systemui.notification.color.MaterialColorManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleBackgroundView$registerMaterialColorChangeCallback$1 implements MaterialColorManager.MaterialColorChangeCallback {
    public /* synthetic */ CapsuleBackgroundView this$0;

    @Override // com.oplus.systemui.notification.color.MaterialColorManager.MaterialColorChangeCallback
    public final void onMaterialColorChanged() {
        CapsuleBackgroundView capsuleBackgroundView = this.this$0;
        if (capsuleBackgroundView.isAttachedToWindow()) {
            capsuleBackgroundView.invalidate();
        }
    }
}
