package com.oplus.systemui.keyguard.watcher;

import android.graphics.Bitmap;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$updateImmersiveLockScreenWallpaper$1$2 implements Runnable {
    public /* synthetic */ Bitmap $bitmap;
    public /* synthetic */ Integer $effectiveType;
    public /* synthetic */ boolean $switchingFromMusicView;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    @Override // java.lang.Runnable
    public final void run() {
        if (((ILockScreenExtStateController.ExtStateEvent) ((LockScreenNotificationExtStateManager) this.this$0.lockScreenNotificationExtStateManager.get()).getExtStateEvent().getValue()).getReason() != 305) {
            boolean z = this.$switchingFromMusicView;
        }
        this.this$0.processImmersiveBitmap(this.$bitmap, this.$effectiveType);
    }
}
