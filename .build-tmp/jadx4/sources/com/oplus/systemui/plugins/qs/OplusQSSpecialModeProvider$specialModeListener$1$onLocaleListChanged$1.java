package com.oplus.systemui.plugins.qs;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSpecialModeProvider$specialModeListener$1$onLocaleListChanged$1 implements Runnable {
    public /* synthetic */ OplusQSSpecialModeProvider this$0;

    @Override // java.lang.Runnable
    public final void run() {
        OplusSeparateQSPlugin.IStub iStub = this.this$0.proxy;
        if (iStub != null) {
            iStub.onReloadQSRootView();
        }
    }
}
