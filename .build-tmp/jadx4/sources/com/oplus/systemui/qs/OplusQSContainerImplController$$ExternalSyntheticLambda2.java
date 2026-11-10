package com.oplus.systemui.qs;

import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.TransCalculator;
import com.android.systemui.panelanimation.LevelPositionAlgorithm;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSContainerImplController$$ExternalSyntheticLambda2 implements Function1 {
    public /* synthetic */ boolean f$0;
    public /* synthetic */ OplusQSContainerImplController f$1;

    public final Object invoke(Object obj) {
        float space;
        boolean z = this.f$0;
        OplusQSContainerImplController oplusQSContainerImplController = this.f$1;
        int iIntValue = ((Integer) obj).intValue();
        if (z) {
            space = 0.0f;
        } else {
            float paddingWithIndex = ((LevelPositionAlgorithm) DependencyEx.sDependency.getDependency(LevelPositionAlgorithm.class)).getPaddingWithIndex(iIntValue, 0, (View) null);
            TransCalculator transCalculator = TransCalculator.INSTANCE;
            float f = oplusQSContainerImplController.mPanelHeight;
            transCalculator.getClass();
            space = TransCalculator.getSpace(f, iIntValue) + paddingWithIndex;
        }
        return Float.valueOf(space);
    }
}
