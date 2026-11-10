package com.oplus.systemui.statusbar.notification.helper;

import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.uah.UAHResClient;
import com.oplus.uah.info.UAHEventRequest;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OsenseHelperImpl$releasePanelPenetrateRateLimit$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OsenseHelperImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                int i = this.this$0.mPenetrateRateLimitHandle;
                if (i > 0) {
                    LogUtil.i$default("OsenseHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "releasePanelPenetrateRateLimit handle: "), null, null, 12);
                    UAHResClient uAHResClient = this.this$0.mUAHResClient;
                    if (uAHResClient != null) {
                        uAHResClient.release(this.this$0.mPenetrateRateLimitHandle);
                    }
                    this.this$0.mPenetrateRateLimitHandle = 0;
                } else {
                    LogUtil.i$default("OsenseHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "invalid release handle: "), null, null, 12);
                }
                break;
            default:
                OsenseHelperImpl osenseHelperImpl = this.this$0;
                if (osenseHelperImpl.mPenetrateRateLimitHandle <= 0) {
                    UAHResClient uAHResClient2 = osenseHelperImpl.mUAHResClient;
                    osenseHelperImpl.mPenetrateRateLimitHandle = uAHResClient2 != null ? uAHResClient2.acquireEvent(new UAHEventRequest(413, "", 3600000, (ArrayList) null)) : 0;
                    int i2 = this.this$0.mPenetrateRateLimitHandle;
                    if (i2 > 0) {
                        LogUtil.i$default("OsenseHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "requestPanelPenetrateRateLimit success handle:"), null, null, 12);
                    }
                    break;
                }
                break;
        }
    }
}
