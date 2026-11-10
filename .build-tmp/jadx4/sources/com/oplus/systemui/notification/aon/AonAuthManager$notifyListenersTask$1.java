package com.oplus.systemui.notification.aon;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthManager$notifyListenersTask$1 implements Runnable {
    public /* synthetic */ AonAuthManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        CopyOnWriteArraySet copyOnWriteArraySet = this.this$0.authStateListeners;
        AonAuthManager aonAuthManager = this.this$0;
        Iterator it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            ((AonAuthManager.IAonAuthStateListener) it.next()).onAuthStateChange(aonAuthManager.getAonAuthResult());
        }
    }
}
