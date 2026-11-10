package com.oplus.systemui.statusbar.phone.fragment;

import android.graphics.Rect;
import com.oplus.systemui.statusbar.controller.SystemPromptController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CollapsedStatusBarViewModelEx$systemPromptListener$1 implements SystemPromptController.IconVisibleChange {
    public /* synthetic */ CollapsedStatusBarViewModelEx this$0;

    @Override // com.oplus.systemui.statusbar.controller.SystemPromptController.IconVisibleChange
    public /* bridge */ /* synthetic */ Rect getIconRect() {
        return super.getIconRect();
    }

    @Override // com.oplus.systemui.statusbar.controller.SystemPromptController.IconVisibleChange
    public final void updateIcon(int i, boolean z, int i2, String str) {
        this.this$0.setHasPrompt(z);
    }
}
