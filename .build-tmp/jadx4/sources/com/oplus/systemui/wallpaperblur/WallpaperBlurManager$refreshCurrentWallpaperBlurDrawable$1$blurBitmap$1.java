package com.oplus.systemui.wallpaperblur;

import android.graphics.Bitmap;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref.BooleanRef $noChangeForBitmap;
    final /* synthetic */ Ref.ObjectRef<Bitmap> $originalBitmap;
    int label;
    final /* synthetic */ WallpaperBlurManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1(Ref.ObjectRef objectRef, WallpaperBlurManager wallpaperBlurManager, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$originalBitmap = objectRef;
        this.this$0 = wallpaperBlurManager;
        this.$noChangeForBitmap = booleanRef;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1(this.$originalBitmap, this.this$0, this.$noChangeForBitmap, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.$originalBitmap.element = this.this$0.oplusWallpaperManager.getBlurWallpaperBitmap(this.this$0.context, 500);
        if (!WallpaperBlurManager.access$shouldUpdateDrawable(this.this$0, (Bitmap) this.$originalBitmap.element) && this.this$0.currentWallpaperBlurDrawable != null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, "WallpaperBlurManager", "refreshCurrentWallpaperBlurDrawable bitmap no change, return", null, 8);
            this.$noChangeForBitmap.element = true;
            return this.this$0.currentWallpaperBitmap;
        }
        Bitmap bitmapCreateScaledBitmap = (Bitmap) this.$originalBitmap.element;
        if (bitmapCreateScaledBitmap != null) {
            if (!bitmapCreateScaledBitmap.isRecycled()) {
                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, Math.max(1, bitmapCreateScaledBitmap.getWidth() / 20), Math.max(1, bitmapCreateScaledBitmap.getHeight() / 20), true);
            }
            if (bitmapCreateScaledBitmap != null) {
                return bitmapCreateScaledBitmap;
            }
        }
        return (Bitmap) this.$originalBitmap.element;
    }
}
