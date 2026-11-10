package com.oplus.systemui.plugins.qs.animator;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSPanelExpandFraction$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ float f$0;

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        float f = this.f$0;
        QSPanelExpandFraction.ExpandedFractionChangeListener expandedFractionChangeListener = (QSPanelExpandFraction.ExpandedFractionChangeListener) obj;
        switch (i) {
            case 0:
                expandedFractionChangeListener.onFractionChanged(f);
                break;
            case 1:
                expandedFractionChangeListener.onFractionChanged(f);
                break;
            case 2:
                expandedFractionChangeListener.onBlurFractionEnd(f == 1.0f);
                break;
            default:
                expandedFractionChangeListener.onFractionEnd(f == 1.0f);
                break;
        }
        return Unit.INSTANCE;
    }
}
