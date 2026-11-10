package com.oplus.systemui.notification.lockscreen.capsule;

import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleViewState$Companion$WIDTH_PROPERTY$2 implements Function {
    public static final CapsuleViewState$Companion$WIDTH_PROPERTY$2 INSTANCE = new CapsuleViewState$Companion$WIDTH_PROPERTY$2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Float.valueOf(((CapsuleItemView) obj).getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
    }
}
