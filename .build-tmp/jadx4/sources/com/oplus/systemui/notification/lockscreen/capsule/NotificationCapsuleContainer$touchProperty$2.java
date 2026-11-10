package com.oplus.systemui.notification.lockscreen.capsule;

import java.util.function.Function;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainer$touchProperty$2 implements Function {
    public /* synthetic */ NotificationCapsuleContainer this$0;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Intrinsics.areEqual(this.this$0.getAnimatorHelper().getAmbientState().getTouchView().get(), (CapsuleItemView) obj) ? Float.valueOf(this.this$0.getAnimatorHelper().getAmbientState().getTouchProgress() * 100.0f) : Float.valueOf(0.0f);
    }
}
