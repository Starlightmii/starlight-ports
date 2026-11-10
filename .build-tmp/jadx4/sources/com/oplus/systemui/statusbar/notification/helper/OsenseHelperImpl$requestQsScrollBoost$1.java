package com.oplus.systemui.statusbar.notification.helper;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.uah.UAHResClient;
import com.oplus.uah.info.UAHEventRequest;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OsenseHelperImpl$requestQsScrollBoost$1 implements Runnable {
    public /* synthetic */ boolean $enable;
    public /* synthetic */ OsenseHelperImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.$enable;
        OsenseHelperImpl osenseHelperImpl = this.this$0;
        int i = osenseHelperImpl.qsScrollBoostHandle;
        if (z) {
            if (i > 0) {
                return;
            }
            UAHResClient uAHResClient = osenseHelperImpl.mUAHResClient;
            osenseHelperImpl.qsScrollBoostHandle = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(448, "", 3000, (ArrayList) null)) : 0;
        } else {
            if (i <= 0) {
                LogUtil.i$default("OsenseHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "invalid release handle: "), null, null, 12);
                return;
            }
            UAHResClient uAHResClient2 = osenseHelperImpl.mUAHResClient;
            if (uAHResClient2 != null) {
                uAHResClient2.release(this.this$0.qsScrollBoostHandle);
            }
            this.this$0.qsScrollBoostHandle = 0;
        }
        LogUtil.i$default("OsenseHelper", KeyguardLogger$.ExternalSyntheticOutline0.m("requestQsScrollBoost handle: ", this.this$0.qsScrollBoostHandle, " enable:", this.$enable), null, null, 12);
    }
}
