package com.oplus.systemui.statusbar.notification.stack;

import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.statusbar.notification.view.GroupOverlayContainerView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationChildrenContainerOverlayExImpl$materialColorChangeCallback$2$1 implements MaterialColorManager.MaterialColorChangeCallback {
    public /* synthetic */ OplusNotificationChildrenContainerOverlayExImpl this$0;

    @Override // com.oplus.systemui.notification.color.MaterialColorManager.MaterialColorChangeCallback
    public final void onMaterialColorChanged() {
        GroupOverlayContainerView groupOverlayContainerView = this.this$0.overlayContainer;
        if (groupOverlayContainerView != null) {
            groupOverlayContainerView.invalidate();
        }
    }
}
