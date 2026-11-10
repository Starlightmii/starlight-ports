package com.oplus.systemui.statusbar.seeding.anim;

import android.util.FloatProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CoordAnimIcon {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final FloatProperty<CoordAnimIcon> TRANS_X_FOR_COORD = new CoordAnimIcon$Companion$TRANS_X_FOR_COORD$1("transXForCoord");

        public final FloatProperty<CoordAnimIcon> getTRANS_X_FOR_COORD() {
            return TRANS_X_FOR_COORD;
        }
    }

    float getTransXForCoord();

    void setTransXForCoord(float f);
}
