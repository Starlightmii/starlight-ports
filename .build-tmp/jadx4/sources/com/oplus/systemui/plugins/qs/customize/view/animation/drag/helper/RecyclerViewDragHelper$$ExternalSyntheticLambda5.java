package com.oplus.systemui.plugins.qs.customize.view.animation.drag.helper;

import com.oplus.systemui.plugins.qs.customize.view.animation.drag.data.CrossRegionDragData;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class RecyclerViewDragHelper$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ RecyclerViewDragHelper f$0;

    public /* synthetic */ RecyclerViewDragHelper$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        RecyclerViewDragHelper recyclerViewDragHelper = this.f$0;
        CrossRegionDragData crossRegionDragData = (CrossRegionDragData) obj;
        switch (i) {
            case 0:
                crossRegionDragData.setRegionFirstCrossed(recyclerViewDragHelper.isFirstCrossRegion());
                return Unit.INSTANCE;
            default:
                return RecyclerViewDragHelper.m164$r8$lambda$iQtQUgLYg9T5Zfdfi9MlwmIKt4(recyclerViewDragHelper, crossRegionDragData);
        }
    }
}
