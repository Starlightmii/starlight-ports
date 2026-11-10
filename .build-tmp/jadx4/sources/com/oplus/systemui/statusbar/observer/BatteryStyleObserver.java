package com.oplus.systemui.statusbar.observer;

import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BatteryStyleObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String DISPLAY_BATTERY_STYLE = "display_battery_style";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getDISPLAY_BATTERY_STYLE() {
            return BatteryStyleObserver.DISPLAY_BATTERY_STYLE;
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return DISPLAY_BATTERY_STYLE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }
}
