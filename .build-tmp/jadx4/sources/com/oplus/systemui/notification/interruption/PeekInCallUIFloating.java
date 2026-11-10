package com.oplus.systemui.notification.interruption;

import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.statusbar.observer.InCallUIFloatingWindowObserver;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekInCallUIFloating extends VisualInterruptionCondition {
    private final InCallUIFloatingWindowObserver inCallUIFloatingWindowObserver;

    public PeekInCallUIFloating(InCallUIFloatingWindowObserver inCallUIFloatingWindowObserver) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "suppress by os policy--suppressForCall");
        this.inCallUIFloatingWindowObserver = inCallUIFloatingWindowObserver;
    }

    public final boolean shouldSuppress() {
        return this.inCallUIFloatingWindowObserver.getShowing();
    }

    public final void start() {
        this.inCallUIFloatingWindowObserver.observe();
    }
}
