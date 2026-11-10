package com.oplus.systemui.qs.user;

import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusUserSwitcherDialogCoordinator$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusUserSwitcherDialogCoordinator f$0;

    public final Object invoke() {
        OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator = this.f$0;
        return oplusUserSwitcherDialogCoordinator.detailInteractorFactory.create((DetailAdapter) oplusUserSwitcherDialogCoordinator.userDetailAdapterProvider.get());
    }
}
