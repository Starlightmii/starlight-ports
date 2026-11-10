package com.oplus.systemui.notification.lockscreen.capsule;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManagerCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainerControllerImpl$colorManagerCallback$1 implements OplusWallpaperColorManagerCallback {
    public /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

    public Rect getColorRect() {
        return this.this$0.getRectForColorChanged();
    }

    public /* bridge */ /* synthetic */ int getWallpaperColorScene() {
        return super.getWallpaperColorScene();
    }

    public /* bridge */ /* synthetic */ void onColorBitmapChanged(boolean z, Bitmap bitmap) {
        super.onColorBitmapChanged(z, bitmap);
    }

    public final void onColorChanged(int i, boolean z) {
        NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
        if (notificationCapsuleContainerControllerImpl.isLightColor != z) {
            notificationCapsuleContainerControllerImpl.isLightColor = z;
            ((NotificationCapsuleContainer) ((ViewController) notificationCapsuleContainerControllerImpl).mView).updateCapsuleContentColor(z);
        }
    }
}
