package com.oplus.systemui.keyguard.watcher;

import android.graphics.Bitmap;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperBitmapUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$handleImmersiveTypeChange$1 implements Runnable {
    public /* synthetic */ int $loadGeneration;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$handleImmersiveTypeChange$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ int $loadGeneration;
        public /* synthetic */ Bitmap $loaded;
        public /* synthetic */ OplusKgdImmersiveController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.$loadGeneration;
            int i2 = this.this$0.typeChangeLoadGeneration;
            OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
            if (i != i2) {
                Bitmap bitmap = this.$loaded;
                oplusKgdImmersiveController.getClass();
                OplusKgdImmersiveController.recycleBitmap$1(bitmap);
                return;
            }
            Integer num = oplusKgdImmersiveController.currentImmersiveType;
            if ((num != null && num.intValue() == -1) || (num != null && num.intValue() == 1)) {
                this.this$0.updateImmersiveLockScreenWallpaper(this.$loaded, 3, num);
                return;
            }
            OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
            Bitmap bitmap2 = this.$loaded;
            oplusKgdImmersiveController2.getClass();
            OplusKgdImmersiveController.recycleBitmap$1(bitmap2);
            KgdLogUtil.w("OplusKgdImmersiveController", "ignore type change enter, type=" + num);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Integer num;
        Integer num2 = this.this$0.currentImmersiveType;
        if ((num2 != null && num2.intValue() == -1) || ((num = this.this$0.currentImmersiveType) != null && num.intValue() == 1)) {
            Bitmap bitmapLoadWallpaperBlurBitmap = OplusWallpaperBitmapUtil.loadWallpaperBlurBitmap(this.this$0.context);
            OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.this$0.handler;
            int i = this.$loadGeneration;
            OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$loadGeneration = i;
            anonymousClass1.this$0 = oplusKgdImmersiveController;
            anonymousClass1.$loaded = bitmapLoadWallpaperBlurBitmap;
            VarHandle.storeStoreFence();
            oplusKgdImmersiveController$handler$1.post(anonymousClass1);
        }
    }
}
