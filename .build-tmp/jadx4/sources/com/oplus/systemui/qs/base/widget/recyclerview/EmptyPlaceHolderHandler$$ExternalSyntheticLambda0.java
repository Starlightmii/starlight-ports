package com.oplus.systemui.qs.base.widget.recyclerview;

import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EmptyPlaceHolderHandler$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ EmptyPlaceHolderHandler$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                EmptyPlaceHolderHandler.Companion companion = EmptyPlaceHolderHandler.Companion;
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "empty_placeholder_alpha_transition", 0.0f, 0.2f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
            case 1:
                EmptyPlaceHolderHandler.Companion companion2 = EmptyPlaceHolderHandler.Companion;
                return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, "空白占位渐隐渐显动画", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008);
            case 2:
                EmptyPlaceHolderHandler.Companion companion3 = EmptyPlaceHolderHandler.Companion;
                return new ArrayList();
            default:
                return new EmptyPlaceHolderHandler.ProgressMapper(0.0f, 1.0f, 0.0f);
        }
    }
}
