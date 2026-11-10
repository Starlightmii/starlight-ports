package com.oplus.systemui.panelanimation;

import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPanelAnimationExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusPanelAnimationExImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return (LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class);
            case 1:
                return new OplusPanelAnimationExImpl.ElementShowState();
            case 2:
                return Float.valueOf(OplusPanelAnimationExImplKt.responseToStiffness(0.2f));
            default:
                return Float.valueOf(OplusPanelAnimationExImplKt.responseToStiffness(0.35f));
        }
    }
}
