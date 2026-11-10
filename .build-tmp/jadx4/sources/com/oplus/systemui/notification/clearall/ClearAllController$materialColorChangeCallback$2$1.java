package com.oplus.systemui.notification.clearall;

import com.oplus.systemui.notification.color.MaterialColorManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClearAllController$materialColorChangeCallback$2$1 implements MaterialColorManager.MaterialColorChangeCallback {
    public /* synthetic */ ClearAllController this$0;

    @Override // com.oplus.systemui.notification.color.MaterialColorManager.MaterialColorChangeCallback
    public final void onMaterialColorChanged() {
        ClearAllController clearAllController = this.this$0;
        Integer num = clearAllController.statusBarState;
        if (num != null && num.intValue() == 0) {
            ClearAllController.updatePlatformBlurDrawable$default(clearAllController);
        }
    }
}
