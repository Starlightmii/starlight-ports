package com.oplus.systemui.separate;

import android.view.ViewGroup;
import com.android.systemui.panelanimation.PanelAnimationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSManagerImpl$panelTranslationYChangeListener$1 implements PanelAnimationEx.PanelTranslationYChangeListener {
    public /* synthetic */ OplusSimpleQSManagerImpl this$0;

    public final void onTranslationYChanged() {
        OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl = this.this$0;
        if (OplusSimpleQSManagerImpl.access$isShadeLockState(oplusSimpleQSManagerImpl)) {
            ViewGroup viewGroup = oplusSimpleQSManagerImpl.simpleQSContainer;
            if (viewGroup != null) {
                viewGroup.setTranslationY(0.0f);
                return;
            }
            return;
        }
        ViewGroup viewGroup2 = oplusSimpleQSManagerImpl.simpleQSContainer;
        if (viewGroup2 != null) {
            viewGroup2.setTranslationY(oplusSimpleQSManagerImpl.panelAnimationEx.getTranslationYValue().getTranslationYValue());
        }
    }
}
