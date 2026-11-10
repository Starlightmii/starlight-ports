package com.oplus.systemui.statusbar;

import android.os.Handler;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyShadeViewExImp$qsColorStateObserver$1 implements OplusQsColorManagerEx.QsColorStateObserver {
    public /* synthetic */ EmptyShadeViewExImp this$0;

    public final void onQsColorStateChanged(int i) {
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        EmptyShadeViewExImp emptyShadeViewExImp = this.this$0;
        1 r1 = new 1();
        r1.$qsColorState = i;
        r1.this$0 = emptyShadeViewExImp;
        VarHandle.storeStoreFence();
        mainHandler.post(r1);
    }
}
