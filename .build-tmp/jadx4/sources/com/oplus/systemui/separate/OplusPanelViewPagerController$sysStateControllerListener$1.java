package com.oplus.systemui.separate;

import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl;
import com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl$releasePanelPenetrateRateLimit$1;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$onSystemUiVisibilityChanged$1;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPagerController$sysStateControllerListener$1 implements SysUiStateController.ISysUiStateListener {
    public /* synthetic */ OplusPanelViewPagerController this$0;

    public final void onPanelExpandedChanged(boolean z, View view) {
        OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
        if (!z) {
            oplusPanelViewPagerController.finishAndResetHorizontalScroll("onPanelExpandedChanged", false);
        }
        if (oplusPanelViewPagerController.isKeyGuardShowing() || !z) {
            OsenseHelperImpl osenseHelperImpl = (OsenseHelperImpl) ((OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class));
            osenseHelperImpl.getClass();
            OsenseHelperImpl$releasePanelPenetrateRateLimit$1 osenseHelperImpl$releasePanelPenetrateRateLimit$1 = new OsenseHelperImpl$releasePanelPenetrateRateLimit$1(0);
            osenseHelperImpl$releasePanelPenetrateRateLimit$1.this$0 = osenseHelperImpl;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(osenseHelperImpl$releasePanelPenetrateRateLimit$1);
        } else {
            OsenseHelperImpl osenseHelperImpl2 = (OsenseHelperImpl) ((OsenseHelper) DependencyEx.sDependency.getDependency(OsenseHelper.class));
            osenseHelperImpl2.getClass();
            OsenseHelperImpl$releasePanelPenetrateRateLimit$1 osenseHelperImpl$releasePanelPenetrateRateLimit$2 = new OsenseHelperImpl$releasePanelPenetrateRateLimit$1(1);
            osenseHelperImpl$releasePanelPenetrateRateLimit$2.this$0 = osenseHelperImpl2;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(osenseHelperImpl$releasePanelPenetrateRateLimit$2);
        }
        OplusSysUiSchedMgr.Companion.getInstance().getClass();
        OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
        OplusSysUiSchedMgr$onSystemUiVisibilityChanged$1 oplusSysUiSchedMgr$onSystemUiVisibilityChanged$1 = new OplusSysUiSchedMgr$onSystemUiVisibilityChanged$1();
        oplusSysUiSchedMgr$onSystemUiVisibilityChanged$1.$isVisible = z;
        VarHandle.storeStoreFence();
        companion.runOnSubThread(oplusSysUiSchedMgr$onSystemUiVisibilityChanged$1);
    }
}
