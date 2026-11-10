package com.oplus.systemui.notification.row.material;

import com.oplus.systemui.keyguard.wallpaper.OplusLockscreenWallpaperDimmingHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1 implements OplusLockscreenWallpaperDimmingHelper.WallpaperDimmingSnapshotListener {
    public /* synthetic */ NotificationMaterialColorModeController this$0;

    public final void onSnapshotUpdated(OplusLockscreenWallpaperDimmingHelper.WallpaperDimmingSnapshot wallpaperDimmingSnapshot, String str) {
        NotificationMaterialColorModeController.access$updateReverseColorState(this.this$0, wallpaperDimmingSnapshot.getWallpaperHsbRegion(), str);
    }
}
