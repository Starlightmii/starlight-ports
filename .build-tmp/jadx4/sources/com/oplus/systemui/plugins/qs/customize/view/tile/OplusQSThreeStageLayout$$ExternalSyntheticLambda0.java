package com.oplus.systemui.plugins.qs.customize.view.tile;

import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSThreeStageLayout$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusQSThreeStageLayout$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                OplusQSThreeStageLayout.Companion companion = OplusQSThreeStageLayout.Companion;
                return Unit.INSTANCE;
            case 1:
                OplusQSThreeStageLayout.Companion companion2 = OplusQSThreeStageLayout.Companion;
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "三段式开关拖拽跟手动画", 0.0f, 0.1f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
            default:
                OplusQSThreeStageLayout.Companion companion3 = OplusQSThreeStageLayout.Companion;
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "三段式开关点击位移动画", 0.25f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
        }
    }
}
