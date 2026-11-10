package com.oplus.systemui.statusbar.phone.policy;

import com.android.systemui.dump.DumpManager;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusPolicyObserver_Factory implements Factory {
    public static OplusPolicyObserver newInstance(DumpManager dumpManager) {
        return new OplusPolicyObserver(dumpManager);
    }
}
