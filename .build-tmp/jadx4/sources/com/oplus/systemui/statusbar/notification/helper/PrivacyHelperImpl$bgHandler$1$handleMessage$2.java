package com.oplus.systemui.statusbar.notification.helper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyHelperImpl$bgHandler$1$handleMessage$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PrivacyHelperImpl this$0;

    public /* synthetic */ PrivacyHelperImpl$bgHandler$1$handleMessage$2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (this.this$0.getAppLockEnabled()) {
                    this.this$0.notifyAppEncryptListener();
                }
                break;
            case 1:
                PrivacyHelperImpl privacyHelperImpl = this.this$0;
                if (privacyHelperImpl.appHiddenEnabled) {
                    privacyHelperImpl.notifyAppHideListener();
                }
                break;
            case 2:
                PrivacyHelperImpl.access$updateEncryptPkgs(this.this$0);
                PrivacyHelperImpl.access$updateHidePkgs(this.this$0);
                break;
            default:
                this.this$0.notifyAppEncryptListener();
                break;
        }
    }
}
