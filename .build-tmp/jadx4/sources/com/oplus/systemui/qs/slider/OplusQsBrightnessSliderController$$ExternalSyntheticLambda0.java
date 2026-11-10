package com.oplus.systemui.qs.slider;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsBrightnessSliderController$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsBrightnessSliderController f$0;

    public /* synthetic */ OplusQsBrightnessSliderController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.f$0;
        ((Boolean) obj).getClass();
        switch (i) {
            case 0:
                return Boolean.valueOf(OplusQsBrightnessSliderController.m307$r8$lambda$Hr3tX3lJhJWEDZdRvBdKNeuknA(oplusQsBrightnessSliderController));
            default:
                oplusQsBrightnessSliderController.updateViewEnable();
                return Unit.INSTANCE;
        }
    }
}
