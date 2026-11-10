package com.oplus.systemui.statusbar.phone.dynamic.icon;

import com.android.systemui.Dumpable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarIconView$setVisibleState$visibleRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusStatusBarIconView this$0;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Dumpable dumpable = this.this$0;
                OplusStatusBarIconView.Companion companion = OplusStatusBarIconView.Companion;
                dumpable.setVisibility(0);
                break;
            default:
                Dumpable dumpable2 = this.this$0;
                OplusStatusBarIconView.Companion companion2 = OplusStatusBarIconView.Companion;
                dumpable2.setVisibility(8);
                break;
        }
    }
}
