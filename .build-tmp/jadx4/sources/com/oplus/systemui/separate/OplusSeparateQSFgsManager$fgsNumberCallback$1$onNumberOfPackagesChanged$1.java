package com.oplus.systemui.separate;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.plugins.qs.QSFgs;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSFgsManager$fgsNumberCallback$1$onNumberOfPackagesChanged$1 implements Runnable {
    public /* synthetic */ int $numPackages;
    public /* synthetic */ OplusSeparateQSFgsManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusSeparateQSPrivacyManager", ListPopupWindow$.ExternalSyntheticOutline0.m(this.$numPackages, "onNumberOfPackagesChanged numPackages "), null, 8);
        OplusSeparateQSFgsManager oplusSeparateQSFgsManager = this.this$0;
        oplusSeparateQSFgsManager.qsNumPackages = this.$numPackages;
        CopyOnWriteArrayList copyOnWriteArrayList = oplusSeparateQSFgsManager.qsFgsCallBacks;
        int i = this.$numPackages;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            ((QSFgs.CallBack) it.next()).onNumberOfPackagesChanged(i, false);
        }
    }
}
