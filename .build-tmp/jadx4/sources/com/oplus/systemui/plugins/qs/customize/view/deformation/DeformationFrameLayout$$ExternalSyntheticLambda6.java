package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.widget.FrameLayout;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class DeformationFrameLayout$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ CellCalculatorManager.LayoutConstraint f$0;

    public /* synthetic */ DeformationFrameLayout$$ExternalSyntheticLambda6(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        boolean z = true;
        CellCalculatorManager.LayoutConstraint layoutConstraint = this.f$0;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) obj;
        switch (i) {
            case 0:
                DeformationFrameLayout.Companion companion = DeformationFrameLayout.Companion;
                int i2 = layoutParams.topMargin;
                int i3 = layoutParams.width;
                int i4 = layoutParams.height;
                layoutParams.topMargin = layoutConstraint.getLayoutMarginTop();
                layoutParams.width = layoutConstraint.getCapsuleWidth();
                int capsuleHeight = layoutConstraint.getCapsuleHeight();
                layoutParams.height = capsuleHeight;
                if (i2 == layoutParams.topMargin && i3 == layoutParams.width && i4 == capsuleHeight) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                DeformationFrameLayout.Companion companion2 = DeformationFrameLayout.Companion;
                int i5 = layoutParams.topMargin;
                int i6 = layoutParams.bottomMargin;
                layoutParams.topMargin = layoutConstraint.getCapsuleHeight() + layoutConstraint.getLayoutMarginTop();
                int labelMarginTop = layoutConstraint.getLabelMarginTop();
                layoutParams.bottomMargin = labelMarginTop;
                if (i5 == layoutParams.topMargin && i6 == labelMarginTop) {
                    z = false;
                }
                return Boolean.valueOf(z);
        }
    }
}
