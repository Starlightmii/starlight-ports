package com.oplus.systemui.qs.widget;

import android.os.RemoteException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFgsManagerController$init$1$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusFgsManagerController this$0;

    public /* synthetic */ OplusFgsManagerController$init$1$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                try {
                    this.this$0.mActivityManager.registerForegroundServiceObserver(this.this$0);
                    return;
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer().getClass();
                    return;
                }
            case 1:
                Object obj = this.this$0.mLock;
                OplusFgsManagerController oplusFgsManagerController = this.this$0;
                synchronized (obj) {
                    oplusFgsManagerController.updateAppItemsLocked();
                }
                return;
            default:
                this.this$0.mFgsListAdapter.updateItems();
                return;
        }
    }
}
