package com.oplus.systemui.statusbar.seeding.livealert;

import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LiteUmsLoader$userListener$2$1 implements IMultiUserListener {
    public /* synthetic */ LiteUmsLoader this$0;

    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onUnlocked() {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "LiteUmsLoader", "onUnlocked", null, 8);
        LiteUmsLoader liteUmsLoader = this.this$0;
        LiteUmsLoader.access$resetState(liteUmsLoader);
        liteUmsLoader.getNetworkMonitor().start();
    }
}
