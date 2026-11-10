package com.oplus.systemui.qs.scenario.domain.interactor;

import com.oplus.systemui.qs.scenario.domain.animation.ScenarioDialogAnimationManager;
import com.oplusos.systemui.common.scenario.QSScenarioAnimListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioViewInteractor$animationCallback$1 implements QSScenarioAnimListener {
    public /* synthetic */ ScenarioViewInteractor this$0;

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorEnd(float f) {
        ScenarioDialogAnimationManager scenarioDialogAnimationManager;
        ScenarioViewInteractor scenarioViewInteractor = this.this$0;
        if (f == 0.0f && (scenarioDialogAnimationManager = scenarioViewInteractor.dialogAnimationManager) != null && scenarioDialogAnimationManager.isLaunching) {
            scenarioViewInteractor.viewDialogInteractor.dismissDialog("instant");
        }
    }

    @Override // com.oplusos.systemui.common.scenario.QSScenarioAnimListener
    public final void onAnimatorStart(float f) {
        this.this$0.showDialog(false, null);
    }
}
