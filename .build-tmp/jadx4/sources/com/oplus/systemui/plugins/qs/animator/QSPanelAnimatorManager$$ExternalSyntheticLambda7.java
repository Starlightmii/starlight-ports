package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSPanelAnimatorManager$$ExternalSyntheticLambda7 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ IQSExpandOrCollapseAnimation f$0;
    public /* synthetic */ View f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ int f$3;

    public /* synthetic */ QSPanelAnimatorManager$$ExternalSyntheticLambda7(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation = this.f$0;
                View view = this.f$1;
                iQSExpandOrCollapseAnimation.updateState(this.f$2, this.f$3, view.getTransitionAlpha(), ((Float) obj).floatValue(), -1.0f);
                break;
            case 1:
                IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation2 = this.f$0;
                View view2 = this.f$1;
                iQSExpandOrCollapseAnimation2.updateState(this.f$2, this.f$3, view2.getTransitionAlpha(), view2.getScaleX(), ((Float) obj).floatValue());
                break;
            default:
                this.f$0.updateState(this.f$2, this.f$3, ((Float) obj).floatValue(), this.f$1.getScaleX(), -1.0f);
                break;
        }
        return Unit.INSTANCE;
    }
}
