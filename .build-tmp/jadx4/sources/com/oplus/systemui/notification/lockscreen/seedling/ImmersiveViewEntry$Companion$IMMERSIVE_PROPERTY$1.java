package com.oplus.systemui.notification.lockscreen.seedling;

import android.util.IntProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImmersiveViewEntry$Companion$IMMERSIVE_PROPERTY$1 extends IntProperty {
    @Override // android.util.Property
    public final Integer get(Object obj) {
        return Integer.valueOf(((ImmersiveViewEntry) obj).getImmersiveState());
    }

    @Override // android.util.IntProperty
    public final void setValue(Object obj, int i) {
        ((ImmersiveViewEntry) obj).setImmersiveState(i);
    }
}
