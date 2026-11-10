package com.oplus.systemui.statusbar.notification.helper;

import android.util.ArraySet;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SuperPowerNotificationHelper$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SuperPowerNotificationHelper f$0;

    @Override // java.lang.Runnable
    public final void run() {
        Iterator it = ((ArraySet) this.f$0.mAppEncryptListeners).iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }
}
