package com.oplusos.systemui.common.sched;

import com.android.keyguard.OplusLockIconView$;
import com.oplus.android.internal.util.OplusFrameworkStatsLog;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSysUiSchedMgr$onSystemUiVisibilityChanged$1 implements Runnable {
    public /* synthetic */ boolean $isVisible;

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.$isVisible;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onSystemUiVisibilityChanged, uiType: 1, isVisible: ", "OplusSysUiSchedMgr-->", "SystemUi--Common", z);
        }
        OplusFrameworkStatsLog.write(100270, 1, this.$isVisible, System.currentTimeMillis());
    }
}
