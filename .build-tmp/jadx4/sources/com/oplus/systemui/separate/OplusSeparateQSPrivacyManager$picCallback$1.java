package com.oplus.systemui.separate;

import com.android.systemui.privacy.PrivacyItemController;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPrivacyManager$picCallback$1 implements PrivacyItemController.Callback {
    public /* synthetic */ OplusSeparateQSPrivacyManager this$0;

    public final void onPrivacyItemsChanged(List list) {
        OplusSeparateQSPrivacyManager oplusSeparateQSPrivacyManager = this.this$0;
        Executor executor = oplusSeparateQSPrivacyManager.backgroundExecutor;
        OplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1 oplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1 = new OplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1();
        oplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1.$privacyItems = list;
        oplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1.this$0 = oplusSeparateQSPrivacyManager;
        VarHandle.storeStoreFence();
        executor.execute(oplusSeparateQSPrivacyManager$picCallback$1$onPrivacyItemsChanged$1);
    }
}
