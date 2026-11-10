package com.oplus.systemui.statusbar.controller;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemPromptController$updateIconRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SystemPromptController this$0;

    public /* synthetic */ SystemPromptController$updateIconRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "SystemPromptController", KeyguardLogger$.ExternalSyntheticOutline0.m("timeout ", this.this$0.oplusSeedlingCardController.isSystemCapsuleShow()), null, 8);
                if (!this.this$0.oplusSeedlingCardController.isSystemCapsuleShow() || this.this$0.isInCallMode()) {
                    this.this$0.updatePromptIcon();
                }
                break;
            default:
                SystemPromptController.access$changeInCallState(this.this$0);
                break;
        }
    }
}
