package com.oplus.systemui.statusbar.notification;

import com.oplus.systemui.notification.color.MaterialColorManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCloseAllController$materialColorChangeCallback$2$1 implements MaterialColorManager.MaterialColorChangeCallback {
    public /* synthetic */ OplusCloseAllController this$0;

    @Override // com.oplus.systemui.notification.color.MaterialColorManager.MaterialColorChangeCallback
    public final void onMaterialColorChanged() {
        OplusCloseAllController oplusCloseAllController = this.this$0;
        if (oplusCloseAllController.statusBarStateController.getState() != 0) {
            OplusCloseAllController.updatePlatformBlurDrawable$default(oplusCloseAllController);
        }
    }
}
