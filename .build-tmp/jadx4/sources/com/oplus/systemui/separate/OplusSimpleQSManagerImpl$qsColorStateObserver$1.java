package com.oplus.systemui.separate;

import android.os.Handler;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSManagerImpl$qsColorStateObserver$1 implements OplusQsColorManagerEx.QsColorStateObserver {
    public /* synthetic */ OplusSimpleQSManagerImpl this$0;

    public final void onQsColorStateChanged(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onQsColorStateChanged updateTextColor qsColorState: ", "OplusSimpleQSManagerImpl-->", "SystemUi--Notification");
        }
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl = this.this$0;
        2 r0 = new 2();
        r0.this$0 = oplusSimpleQSManagerImpl;
        VarHandle.storeStoreFence();
        mainHandler.post(r0);
    }
}
