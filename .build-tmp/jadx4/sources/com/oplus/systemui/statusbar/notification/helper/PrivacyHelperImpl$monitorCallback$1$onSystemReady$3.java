package com.oplus.systemui.statusbar.notification.helper;

import com.oplus.systemui.notification.aon.AonAuthManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyHelperImpl$monitorCallback$1$onSystemReady$3 implements AonAuthManager.IAonAuthStateListener {
    public /* synthetic */ PrivacyHelperImpl this$0;

    @Override // com.oplus.systemui.notification.aon.AonAuthManager.IAonAuthStateListener
    public final void onAuthStateChange(Boolean bool) {
        this.this$0.notifyAppEncryptListener();
    }
}
