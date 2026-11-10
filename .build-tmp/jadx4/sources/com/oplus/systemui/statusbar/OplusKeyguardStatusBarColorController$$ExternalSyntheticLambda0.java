package com.oplus.systemui.statusbar;

import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusKeyguardStatusBarColorController$$ExternalSyntheticLambda0 implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        float fFloatValue = ((Float) obj).floatValue();
        float fFloatValue2 = ((Float) obj2).floatValue();
        return Boolean.valueOf(0.0f <= fFloatValue && fFloatValue <= 20.0f && 90.0f <= fFloatValue2 && fFloatValue2 <= 100.0f);
    }
}
