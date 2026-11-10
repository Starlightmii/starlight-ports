package com.oplus.systemui.separate;

import android.view.ViewGroup;
import com.android.systemui.panelanimation.PanelAnimationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController$panelTranslationYChangeListener$1 implements PanelAnimationEx.PanelTranslationYChangeListener {
    public /* synthetic */ OplusSimpleQSFakeController this$0;

    public final void onTranslationYChanged() {
        ViewGroup viewGroup;
        OplusSimpleQSFakeController oplusSimpleQSFakeController = this.this$0;
        if (OplusSimpleQSFakeController.access$isShadeLockState(oplusSimpleQSFakeController)) {
            ViewGroup viewGroup2 = oplusSimpleQSFakeController.oplusQSFakeContainer;
            if (viewGroup2 != null) {
                viewGroup2.setTranslationY(0.0f);
                return;
            }
            return;
        }
        if (oplusSimpleQSFakeController.isNotSupportSeamlessTransfer() || (viewGroup = oplusSimpleQSFakeController.oplusQSFakeContainer) == null) {
            return;
        }
        viewGroup.setTranslationY(oplusSimpleQSFakeController.panelAnimationEx.getTranslationYValue().getTranslationYValue());
    }
}
