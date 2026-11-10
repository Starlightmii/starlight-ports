package com.oplus.systemui.navigationbar.eximpl;

import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarExImpl$mChildModeListener$1 implements IObserverListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NavigationBarExImpl this$0;

    public /* synthetic */ NavigationBarExImpl$mChildModeListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        switch (this.$r8$classId) {
            case 0:
                NavigationBarExImpl.access$onChildrenModeChanged(this.this$0);
                break;
            default:
                NavigationBarExImpl.access$onStudyCenterModeChanged(this.this$0);
                break;
        }
    }
}
