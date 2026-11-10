package com.oplus.systemui.qs.widget;

import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSMultiUserSwitch$$ExternalSyntheticLambda1 implements Supplier {
    public /* synthetic */ OplusQSMultiUserSwitch f$0;

    /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.FrameLayout, com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch] */
    @Override // java.util.function.Supplier
    public final Object get() {
        ?? r2 = this.f$0;
        return Boolean.valueOf(r2.mUserManager.isUserSwitcherEnabled(r2.getResources().getBoolean(2131034323)));
    }
}
