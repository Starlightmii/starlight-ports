package com.oplus.systemui.separate;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.plugins.qs.QSFgs;
import com.oplus.systemui.qs.widget.OplusFgsManagerController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSFgsManager$fgsNumberCallback$1 implements OplusFgsManagerController.OnNumberOfPackagesChangedListener {
    public /* synthetic */ OplusSeparateQSFgsManager this$0;

    @Override // com.oplus.systemui.qs.widget.OplusFgsManagerController.OnNumberOfPackagesChangedListener
    public final void onNumberOfPackagesChanged(int i) {
        OplusSeparateQSFgsManager oplusSeparateQSFgsManager = this.this$0;
        Executor executor = oplusSeparateQSFgsManager.backgroundExecutor;
        OplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1 oplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1 = new OplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1();
        oplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1.$numPackages = i;
        oplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1.this$0 = oplusSeparateQSFgsManager;
        VarHandle.storeStoreFence();
        executor.execute(oplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1);
    }

    @Override // com.oplus.systemui.qs.widget.OplusFgsManagerController.OnNumberOfPackagesChangedListener
    public final void onRunningAppsChanged(List list) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSPrivacyManager", ListPopupWindow$.ExternalSyntheticOutline0.m(list.size(), "onRunningAppsChanged runningApps "), null, 8);
        OplusSeparateQSFgsManager oplusSeparateQSFgsManager = this.this$0;
        oplusSeparateQSFgsManager.qsRunningApps = list;
        Iterator it = oplusSeparateQSFgsManager.qsFgsCallBacks.iterator();
        while (it.hasNext()) {
            ((QSFgs.CallBack) it.next()).onRunningAppsChanged(OplusSeparateQSFgsManager.access$convertRunningApp(oplusSeparateQSFgsManager, list));
        }
    }
}
