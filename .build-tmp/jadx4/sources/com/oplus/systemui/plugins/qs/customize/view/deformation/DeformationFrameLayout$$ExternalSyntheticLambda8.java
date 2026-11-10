package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.widget.FrameLayout;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeformationFrameLayout$$ExternalSyntheticLambda8 implements Function1 {
    public /* synthetic */ DeformationFrameLayout f$0;
    public /* synthetic */ CellCalculatorManager.LayoutConstraint f$1;

    public final Object invoke(Object obj) {
        DeformationFrameLayout deformationFrameLayout = this.f$0;
        CellCalculatorManager.LayoutConstraint layoutConstraint = this.f$1;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) obj;
        DeformationFrameLayout.Companion companion = DeformationFrameLayout.Companion;
        int i = layoutParams.topMargin;
        int layoutMarginTop = layoutConstraint.getLayoutMarginTop() + deformationFrameLayout.removeMarginTop;
        layoutParams.topMargin = layoutMarginTop;
        return Boolean.valueOf(i != layoutMarginTop);
    }
}
