package com.oplusos.systemui.common.shader;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class MetaballLightRenderer$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ MetaballLightRenderer f$0;

    public /* synthetic */ MetaballLightRenderer$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        MetaballLightRenderer metaballLightRenderer = this.f$0;
        switch (i) {
            case 0:
                return MetaballLightRenderer.m622$r8$lambda$6wH59fr__VH483BFsBLy0C1LLQ(metaballLightRenderer);
            default:
                metaballLightRenderer.startPlay();
                return Unit.INSTANCE;
        }
    }
}
