package com.oplus.systemui.keyguard.watcher;

import com.oplus.systemui.keyguard.view.OplusKgdImmersiveLockMaskView;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$replaceAOD$applyMask$1 implements Runnable {
    public /* synthetic */ Float $lockMaskRatio;
    public /* synthetic */ float $maskRatio;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Integer num;
        if (this.this$0.immersiveRenderMode == OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW && (num = this.this$0.currentImmersiveType) != null && num.intValue() == 2) {
            this.this$0.setMusicBlackMaskRatio(this.$maskRatio);
        } else {
            ((OplusKgdImmersiveMaskView) this.this$0.oplusKgdImmersiveMaskView.get()).setBlackMaskRatio(this.$maskRatio);
        }
        Float f = this.$lockMaskRatio;
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        if (f != null) {
            ((OplusKgdImmersiveLockMaskView) oplusKgdImmersiveController.oplusKgdImmersiveLockMaskView.get()).setBlackMaskRatio(f.floatValue());
        } else {
            ((OplusKgdImmersiveLockMaskView) oplusKgdImmersiveController.oplusKgdImmersiveLockMaskView.get()).updateBlackMaskAlpha(this.$maskRatio);
        }
    }
}
