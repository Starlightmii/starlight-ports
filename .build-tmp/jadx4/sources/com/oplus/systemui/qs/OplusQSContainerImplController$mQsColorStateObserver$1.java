package com.oplus.systemui.qs;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.qs.QSNode;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController$mQsColorStateObserver$1 implements OplusQsColorManagerEx.QsColorStateObserver {
    public /* synthetic */ OplusQSContainerImplController this$0;

    public final void onQsColorStateChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSContainerImplController", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onQsColorStateChanged updateTextColor qsColorState: "), null, 8);
        Iterator it = this.this$0.mNodes.iterator();
        while (it.hasNext()) {
            ((QSNode) it.next()).onQsColorStateChanged(i);
        }
    }
}
