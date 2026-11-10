package com.oplus.systemui.wallpapers.separatewallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Trace;
import android.view.SurfaceHolder;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.wallpapers.CanvasEngineWrapper;
import com.oplus.systemui.utils.WallpaperUtilKt;
import com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StableDisplayCanvasEngineExImpl extends DefaultCanvasEngineExImpl {
    private final Lazy displayFlag$delegate;

    public StableDisplayCanvasEngineExImpl(CanvasEngineWrapper canvasEngineWrapper) {
        super(canvasEngineWrapper);
        StableDisplayCanvasEngineExImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new StableDisplayCanvasEngineExImpl$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.displayFlag$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
    }

    private final int getDisplayFlag() {
        return ((Number) this.displayFlag$delegate.getValue()).intValue();
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Bitmap getBitmap(WallpaperManager wallpaperManager, int i, int i2) {
        Trace.traceBegin(8L, "StableDisplayEngineEx#getBitmap");
        Bitmap bitmapAsUser = wallpaperManager.getBitmapAsUser(i, false, getDisplayFlag() | i2);
        Trace.traceEnd(8L);
        return bitmapAsUser;
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Rect peekBitmapDimensions(WallpaperManager wallpaperManager, int i) {
        Context displayContext = getEngine().getDisplayContext();
        if (displayContext == null) {
            LogUtil.w$default(8, LogUtil.TAG_WALLPAPER, "StableDisplayEngineEx", "peekWallpaperDimensions fail with no context");
            return new Rect(0, 0, 0, 0);
        }
        Rect rectPeekWallpaperDimensions = WallpaperUtilKt.peekWallpaperDimensions(displayContext, wallpaperManager, i, -2, getDisplayFlag());
        return rectPeekWallpaperDimensions == null ? new Rect(0, 0, 0, 0) : rectPeekWallpaperDimensions;
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void setFixedSize(SurfaceHolder surfaceHolder, int i, int i2, DelayableExecutor delayableExecutor) {
        surfaceHolder.setFixedSize(i, i2);
    }
}
