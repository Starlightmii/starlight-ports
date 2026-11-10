package com.oplus.systemui.keyguard.watcher;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManagerCallback;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$wallpaperChangeCallback$1 implements OplusWallpaperColorManagerCallback {
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public /* bridge */ /* synthetic */ Rect getColorRect() {
        return super.getColorRect();
    }

    public /* bridge */ /* synthetic */ int getWallpaperColorScene() {
        return super.getWallpaperColorScene();
    }

    public final void onColorBitmapChanged(boolean z, Bitmap bitmap) {
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        OplusKgdImmersiveController.RunnableC01792 runnableC01792 = new OplusKgdImmersiveController.RunnableC01792(3);
        runnableC01792.this$0 = oplusKgdImmersiveController;
        runnableC01792.$uiState = bitmap;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(runnableC01792);
    }
}
