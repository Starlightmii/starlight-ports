package com.oplusos.systemui.common.receiver;

import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ThemeReceiver$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        ThemeReceiver themeReceiver = new ThemeReceiver();
        ThemeReceiver.AnonymousClass1 anonymousClass1 = new ThemeReceiver.AnonymousClass1();
        anonymousClass1.this$0 = themeReceiver;
        VarHandle.storeStoreFence();
        themeReceiver.addListener(anonymousClass1);
        themeReceiver.addListener(new ThemeReceiver.AnonymousClass2());
        return themeReceiver;
    }
}
