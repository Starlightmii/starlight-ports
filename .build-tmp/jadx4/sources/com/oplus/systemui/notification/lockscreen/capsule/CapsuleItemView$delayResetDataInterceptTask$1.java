package com.oplus.systemui.notification.lockscreen.capsule;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemView$delayResetDataInterceptTask$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object this$0;

    public /* synthetic */ CapsuleItemView$delayResetDataInterceptTask$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (((CapsuleItemView) this.this$0).bindStackView == null) {
                    ((CapsuleItemView) this.this$0).getBindDataInterceptor().unIntercept();
                }
                break;
            default:
                CapsuleItemView.CapsuleItemProxy.ExtStateUnbindWatchdog.access$handleUnbindTimeout((CapsuleItemView.CapsuleItemProxy.ExtStateUnbindWatchdog) this.this$0);
                break;
        }
    }
}
