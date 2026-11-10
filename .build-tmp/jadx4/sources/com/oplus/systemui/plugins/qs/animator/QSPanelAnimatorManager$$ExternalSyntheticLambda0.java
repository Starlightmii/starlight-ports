package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class QSPanelAnimatorManager$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Function3 f$0;
    public /* synthetic */ View f$1;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Function3 function3 = this.f$0;
                View view = this.f$1;
                Float f = (Float) obj;
                f.floatValue();
                function3.invoke(f, Float.valueOf(view.getScaleX()), Float.valueOf(-1.0f));
                break;
            case 1:
                Function3 function4 = this.f$0;
                View view2 = this.f$1;
                Float f2 = (Float) obj;
                f2.floatValue();
                function4.invoke(Float.valueOf(view2.getTransitionAlpha()), f2, Float.valueOf(-1.0f));
                break;
            default:
                Function3 function5 = this.f$0;
                View view3 = this.f$1;
                Float f3 = (Float) obj;
                f3.floatValue();
                function5.invoke(Float.valueOf(view3.getTransitionAlpha()), Float.valueOf(view3.getScaleX()), f3);
                break;
        }
        return Unit.INSTANCE;
    }
}
