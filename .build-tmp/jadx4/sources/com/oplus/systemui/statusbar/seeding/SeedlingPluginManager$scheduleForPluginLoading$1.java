package com.oplus.systemui.statusbar.seeding;

import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$scheduleForPluginLoading$1 implements Runnable {
    public static final SeedlingPluginManager$scheduleForPluginLoading$1 INSTANCE = new SeedlingPluginManager$scheduleForPluginLoading$1();

    @Override // java.lang.Runnable
    public final void run() {
        OplusSysUiSchedMgr.Companion.getInstance().releaseSysResource(OplusSysUiSchedMgr.UafSceneEvent.SYSTEM_STATUS_ICON_ADDING);
    }
}
