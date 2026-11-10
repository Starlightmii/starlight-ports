package com.oplus.systemui.plugins.qs.quickentrance;

import android.provider.Settings;
import android.widget.TextView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object this$0;

    public /* synthetic */ OplusQSQuickEntranceComponent$rehideRealStatusRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusQSQuickEntranceComponent.showOrHideForScenarioPreview$default((OplusQSQuickEntranceComponent) this.this$0, false);
                String currentScenarioName = ((OplusQSQuickEntranceComponent) this.this$0).scenarioPreviewShowState.getCurrentScenarioName();
                if (currentScenarioName != null) {
                    OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = (OplusQSQuickEntranceComponent) this.this$0;
                    oplusQSQuickEntranceComponent.scenarioPreviewShowState.setValue(currentScenarioName, true);
                    ScenarioPreviewShowState scenarioPreviewShowState = oplusQSQuickEntranceComponent.scenarioPreviewShowState;
                    scenarioPreviewShowState.getClass();
                    scenarioPreviewShowState.lastShowTime = System.currentTimeMillis();
                    oplusQSQuickEntranceComponent.stub.setScenarioPreviewState(currentScenarioName, true);
                }
                ((OplusQSQuickEntranceComponent) this.this$0).getView().removeCallbacks(((OplusQSQuickEntranceComponent) this.this$0).reshowRealStatusRunnable);
                ((OplusQSQuickEntranceComponent) this.this$0).getView().postDelayed(((OplusQSQuickEntranceComponent) this.this$0).reshowRealStatusRunnable, 3000L);
                break;
            case 1:
                OplusQSQuickEntranceComponent.showOrHideForScenarioPreview$default((OplusQSQuickEntranceComponent) this.this$0, true);
                break;
            case 2:
                OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent2 = (OplusQSQuickEntranceComponent) this.this$0;
                OplusQSQuickEntranceComponent.Companion companion = OplusQSQuickEntranceComponent.Companion;
                Settings.System.putIntForUser(oplusQSQuickEntranceComponent2.getContext().getContentResolver(), "qs_editButton_has_open", 1, ((OplusQSQuickEntranceComponent) this.this$0).userId);
                break;
            default:
                ((TextView) this.this$0).setSelected(true);
                break;
        }
    }
}
