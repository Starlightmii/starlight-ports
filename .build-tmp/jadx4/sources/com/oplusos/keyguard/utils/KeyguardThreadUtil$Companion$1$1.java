package com.oplusos.keyguard.utils;

import android.os.Process;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardThreadUtil$Companion$1$1 implements Runnable {
    public static final KeyguardThreadUtil$Companion$1$1 INSTANCE = new KeyguardThreadUtil$Companion$1$1();

    @Override // java.lang.Runnable
    public final void run() {
        OplusSysUiSchedMgr.Companion.getInstance().reportKeyThread("KeyguardWorkerThread", Process.myTid(), OplusSysUiSchedMgr.UafSceneEvent.KEYGUARD_GOING_AWAY, false);
    }
}
