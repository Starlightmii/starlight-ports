package com.oplus.systemui.statusbar.seeding.anim;

import android.util.FloatProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CoordAnimIcon$Companion$TRANS_X_FOR_COORD$1 extends FloatProperty {
    @Override // android.util.Property
    public final Float get(Object obj) {
        return Float.valueOf(((CoordAnimIcon) obj).getTransXForCoord());
    }

    @Override // android.util.FloatProperty
    public final void setValue(Object obj, float f) {
        ((CoordAnimIcon) obj).setTransXForCoord(f);
    }
}
