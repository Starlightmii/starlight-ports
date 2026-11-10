package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import com.oplus.systemui.statusbar.observer.OplusStylusSwipeEnableObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStylusSwipeEnableObserverStarter {
    private final OplusStylusSwipeEnableObserver stylusSwipeEnableObserver;

    public OplusStylusSwipeEnableObserverStarter(Context context) {
        OplusStylusSwipeEnableObserver oplusStylusSwipeEnableObserver = new OplusStylusSwipeEnableObserver();
        this.stylusSwipeEnableObserver = oplusStylusSwipeEnableObserver;
        oplusStylusSwipeEnableObserver.register(context, false);
    }

    public final boolean getState() {
        return this.stylusSwipeEnableObserver.getEnable();
    }
}
