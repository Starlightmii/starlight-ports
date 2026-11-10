package com.oplus.systemui.media.model;

import com.android.systemui.media.controls.pipeline.OplusMediaDataManagerEx;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataModelImpl$innerOplusMediaDataCallBack$1 implements OplusMediaDataManagerEx.OplusMediaDataCallBack {
    public /* synthetic */ OplusMediaDataModelImpl this$0;

    public final void onPlayerInfoChange(List list) {
        Iterator it = this.this$0.mediaCallbackList.iterator();
        while (it.hasNext()) {
            ((OplusMediaDataManagerEx.OplusMediaDataCallBack) it.next()).onPlayerInfoChange(list);
        }
    }
}
