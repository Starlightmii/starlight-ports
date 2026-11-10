package com.oplus.systemui.navigationbar.gesture.sidegesture;

import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InvalidRegionControl$mNavStateListener$1 implements IObserverListener {
    public /* synthetic */ InvalidRegionControl this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        this.this$0.updateTouchBounds();
    }
}
