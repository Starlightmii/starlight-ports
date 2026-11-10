package com.oplus.systemui.separate;

import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSQuickEntranceManager$isMultiUserEnabled$1 implements Supplier {
    public /* synthetic */ OplusSeparateQSQuickEntranceManager this$0;

    @Override // java.util.function.Supplier
    public final Boolean get() {
        return Boolean.valueOf(this.this$0.userManager.isUserSwitcherEnabled(this.this$0.context.getResources().getBoolean(2131034323)));
    }
}
