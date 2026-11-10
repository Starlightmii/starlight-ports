package com.oplus.systemui.plugins.qs.animator;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsElementsChain$statusAndQuickEntranceSpringListener$1 extends SimpleSpringListener {
    public /* synthetic */ QsElementsChain this$0;

    public final void onSpringUpdate(Spring spring) {
        QsElementsChain qsElementsChain = this.this$0;
        if (qsElementsChain.isRelease) {
            return;
        }
        float currentValue = (float) spring.getCurrentValue();
        if (AnimatorUtils.INSTANCE.getExtraFakeTranslationY() == 0.0f) {
            qsElementsChain.statusBarContainer.setTranslationY(currentValue);
            qsElementsChain.separateQsFakeLayout.setTranslationY(currentValue);
            ((IQSExpandOrCollapseAnimation) qsElementsChain.qsComponents.get(0)).setCusTranslationY(currentValue, 0, 0);
            ((IQSExpandOrCollapseAnimation) qsElementsChain.qsComponents.get(1)).setCusTranslationY(currentValue, 0, 0);
        }
        qsElementsChain.curTranslationYArray[0] = currentValue;
        qsElementsChain.lastTranslationYArray[0] = currentValue;
    }
}
