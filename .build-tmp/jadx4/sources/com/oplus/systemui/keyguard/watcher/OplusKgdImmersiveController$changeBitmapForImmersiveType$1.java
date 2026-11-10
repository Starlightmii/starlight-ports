package com.oplus.systemui.keyguard.watcher;

import android.graphics.Bitmap;
import com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperBitmapUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$changeBitmapForImmersiveType$1 implements Runnable {
    public /* synthetic */ Object $from;
    public /* synthetic */ int $newType;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $to;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController$changeBitmapForImmersiveType$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $from;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $to;
        public /* synthetic */ OplusKgdImmersiveController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.getMusicArtHostContainer().setVisibility(8);
                    ((OplusKgdImmersiveMaskView) this.this$0.oplusKgdImmersiveMaskView.get()).setVisibility(0);
                    ((OplusKgdImmersiveMaskView) this.this$0.oplusKgdImmersiveMaskView.get()).startImmersiveChangeTransition((Bitmap) this.$from, (Bitmap) this.$to);
                    this.this$0.cropBitmap = (Bitmap) this.$to;
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode = OplusKgdImmersiveController.ImmersiveRenderMode.NONE;
                    oplusKgdImmersiveController.immersiveRenderMode = OplusKgdImmersiveController.ImmersiveRenderMode.BITMAP;
                    OplusKgdImmersiveController.access$ensureBitmapImmersiveSurfaceVisible(this.this$0);
                    break;
                default:
                    Ref.BooleanRef booleanRef = (Ref.BooleanRef) this.$from;
                    if (!booleanRef.element) {
                        booleanRef.element = true;
                        if (this.this$0.isBitmapLockEnterValid()) {
                            this.this$0.setSurfaceAlpha(1.0f);
                            ((OplusKgdImmersiveController$.ExternalSyntheticLambda12) this.$to).invoke();
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public /* synthetic */ OplusKgdImmersiveController$changeBitmapForImmersiveType$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                int i = this.$newType;
                if (i == -1 || i == 1) {
                    OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$1 = this.this$0.handler;
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    Bitmap bitmap = (Bitmap) this.$from;
                    Bitmap bitmap2 = (Bitmap) this.$to;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
                    anonymousClass1.this$0 = oplusKgdImmersiveController;
                    anonymousClass1.$from = bitmap;
                    anonymousClass1.$to = bitmap2;
                    VarHandle.storeStoreFence();
                    oplusKgdImmersiveController$handler$1.post(anonymousClass1);
                }
                break;
            default:
                Bitmap bitmapLoadDesktopWallpaperBitmap = OplusWallpaperBitmapUtil.loadDesktopWallpaperBitmap(this.this$0.context, this.this$0.userTracker.getUserId());
                OplusKgdImmersiveController$handler$1 oplusKgdImmersiveController$handler$2 = this.this$0.handler;
                int i2 = this.$newType;
                OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
                Function0 function0 = (Function0) this.$from;
                Function1 function1 = (Function1) this.$to;
                OplusKgdImmersiveController.requestDesktopWallpaperBitmap.3.1 r2 = new OplusKgdImmersiveController.requestDesktopWallpaperBitmap.3.1();
                r2.$generation = i2;
                r2.this$0 = oplusKgdImmersiveController2;
                r2.$bitmap = bitmapLoadDesktopWallpaperBitmap;
                r2.$onFailed = function0;
                r2.$onReady = function1;
                VarHandle.storeStoreFence();
                oplusKgdImmersiveController$handler$2.post(r2);
                break;
        }
    }
}
