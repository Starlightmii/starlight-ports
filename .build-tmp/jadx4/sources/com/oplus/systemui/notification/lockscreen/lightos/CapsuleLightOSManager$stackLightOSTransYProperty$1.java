package com.oplus.systemui.notification.lockscreen.lightos;

import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLightOSManager$stackLightOSTransYProperty$1 implements BiConsumer {
    public /* synthetic */ CapsuleLightOSManager this$0;

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        float fFloatValue = ((Number) obj2).floatValue();
        CapsuleLightOSManager capsuleLightOSManager = this.this$0;
        capsuleLightOSManager.stackTranslationYState = fFloatValue;
        CapsuleLightOSManager.access$updateStackNSSLTranslationY(capsuleLightOSManager, fFloatValue);
    }
}
