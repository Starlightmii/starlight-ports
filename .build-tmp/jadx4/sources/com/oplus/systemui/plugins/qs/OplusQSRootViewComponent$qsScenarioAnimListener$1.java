package com.oplus.systemui.plugins.qs;

import androidx.appcompat.widget.ListPopupWindow$;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent$qsScenarioAnimListener$1 implements QSScenarioAnimListener {
    public /* synthetic */ OplusQSRootViewComponent this$0;

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorEnd(float f) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", ListPopupWindow$.ExternalSyntheticOutline0.m(f, "value:", " disallow:false"), null, 8);
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
        ((OplusQSRootView) oplusQSRootViewComponent.mView).requestDisallowInterceptTouchEvent(false);
        oplusQSRootViewComponent.setScenarioTouch(false);
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorStart(float f) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", ListPopupWindow$.ExternalSyntheticOutline0.m(f, "value:", " disallow:true"), null, 8);
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
        ((OplusQSRootView) oplusQSRootViewComponent.mView).requestDisallowInterceptTouchEvent(true);
        oplusQSRootViewComponent.setScenarioTouch(true);
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorUpdate(float f, float f2, boolean z) {
    }
}
