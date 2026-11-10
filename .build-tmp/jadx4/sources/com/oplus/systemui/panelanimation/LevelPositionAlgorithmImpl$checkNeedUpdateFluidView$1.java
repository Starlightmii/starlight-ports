package com.oplus.systemui.panelanimation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class LevelPositionAlgorithmImpl$checkNeedUpdateFluidView$1 extends FunctionReferenceImpl implements Function1 {
    public final Object invoke(Object obj) {
        float f;
        int iIntValue = ((Number) obj).intValue();
        LevelPositionAlgorithmImpl levelPositionAlgorithmImpl = (LevelPositionAlgorithmImpl) ((CallableReference) this).receiver;
        int index = levelPositionAlgorithmImpl.getIndex(iIntValue, 1);
        if (iIntValue == 0) {
            f = 0.0f;
        } else {
            float gapWithIndex = levelPositionAlgorithmImpl.getGapWithIndex(index);
            levelPositionAlgorithmImpl.fluidDelta += gapWithIndex;
            f = gapWithIndex;
        }
        return Float.valueOf(f);
    }
}
