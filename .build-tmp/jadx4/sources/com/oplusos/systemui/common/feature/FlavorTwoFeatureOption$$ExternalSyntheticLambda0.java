package com.oplusos.systemui.common.feature;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FlavorTwoFeatureOption$$ExternalSyntheticLambda0 implements Function0 {
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object invoke() {
        boolean z;
        if (FeatureOption.INSTANCE.getFirstApiLevel() < 37) {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
