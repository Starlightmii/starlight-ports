package com.oplus.systemui.wallpapers.separatewallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceHolder;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.wallpapers.CanvasEngineWrapper;
import com.android.systemui.wallpapers.ImageWallpaper;
import com.android.systemui.wallpapers.ImageWallpaper$CanvasEngine$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.utils.WallpaperUtilKt;
import com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl;
import com.oplus.zoom.common.ZoomUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DisplayRemapCanvasEngineExImpl extends DefaultCanvasEngineExImpl {
    private Bitmap cacheBitmap;
    public int cacheBitmapWallpaperId;
    private final AtomicInteger currentDisplayFlag;
    public boolean drawFrameOnSurfaceChange;
    public boolean foldStatusChange;
    public boolean isBitmapReloadRequired;
    public boolean isLockscreenLiveWallpaperEnabled;
    private final AtomicBoolean isPreBitmapRecycleRunnableScheduled;
    private final AtomicReference<Rect> lastDrawnSurfaceSize;
    private final AtomicReference<Rect> lastRequestSurfaceSize;
    private final DelayableExecutor longExecutor;
    private final AtomicInteger pendingDisplayFlag;
    private Bitmap preloadBitmap;
    private Runnable preloadBitmapRecycleRunnable;
    public SurfaceHolder surfaceHolder;
    public WallpaperManager wallpaperManager;

    public DisplayRemapCanvasEngineExImpl(CanvasEngineWrapper canvasEngineWrapper, DelayableExecutor delayableExecutor) {
        super(canvasEngineWrapper);
        this.longExecutor = delayableExecutor;
        this.pendingDisplayFlag = new AtomicInteger(0);
        this.currentDisplayFlag = new AtomicInteger(0);
        this.lastRequestSurfaceSize = new AtomicReference<>(new Rect(0, 0, 0, 0));
        this.lastDrawnSurfaceSize = new AtomicReference<>(new Rect(0, 0, 0, 0));
        this.isPreBitmapRecycleRunnableScheduled = new AtomicBoolean(false);
        preloadBitmapRecycleRunnable.1 r4 = new preloadBitmapRecycleRunnable.1();
        r4.$engineWrapper = canvasEngineWrapper;
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.preloadBitmapRecycleRunnable = r4;
    }

    private final int getFlatDisplaySystemWallpaperId() {
        WallpaperManager wallpaperManager = this.wallpaperManager;
        if (wallpaperManager == null) {
            wallpaperManager = null;
        }
        return wallpaperManager.getWallpaperIdForUser(17, -2);
    }

    private final Bitmap getPreloadBitmap() {
        Bitmap bitmap = this.preloadBitmap;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("getPreloadBitmap: " + bitmap + " " + this.isPreBitmapRecycleRunnableScheduled.get()));
        }
        if (this.isPreBitmapRecycleRunnableScheduled.compareAndSet(false, true)) {
            HandlerThread workThread = getEngineWrapper().getWorkThread();
            if (workThread != null) {
                workThread.getThreadHandler().postDelayed(this.preloadBitmapRecycleRunnable, 1000L);
            } else {
                this.preloadBitmap = null;
            }
        }
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return bitmap;
    }

    public final boolean canResetForceDraw() {
        Rect rect = this.lastDrawnSurfaceSize.get();
        Rect rect2 = this.lastRequestSurfaceSize.get();
        boolean z = !rect.isEmpty() && rect.equals(rect2);
        if (!z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            int wallpaperFlags = getEngine().getWallpaperFlags();
            StringBuilder sb = new StringBuilder("canResetForceDraw target: ");
            sb.append(rect2);
            sb.append(" dest: ");
            sb.append(rect);
            sb.append(" wallpaperType: ");
            MobileInputLogger$.ExternalSyntheticOutline0.m(wallpaperFlags, "DisplayRemapEngineEx-->", "SystemUi--Wallpaper", sb);
        }
        return z;
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Bitmap getBitmap(WallpaperManager wallpaperManager, int i, int i2) {
        int i3 = this.currentDisplayFlag.get();
        if (!this.isLockscreenLiveWallpaperEnabled) {
            i2 = 1;
        }
        Bitmap preloadBitmap = getPreloadBitmap();
        if (preloadBitmap != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->getBitmap use preload");
            }
            return preloadBitmap;
        }
        Bitmap cacheBitmap = getCacheBitmap(i3);
        if (cacheBitmap == null) {
            return getBitmapInternal(wallpaperManager, i, i2, i3);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->getBitmap use cache");
        }
        return cacheBitmap;
    }

    public final Bitmap getBitmapInternal(WallpaperManager wallpaperManager, int i, int i2, int i3) {
        Bitmap bitmap;
        Trace.traceBegin(8L, "DisplayRemapEngineEx#getBitmapInternal");
        int i4 = i2 | i3;
        Bitmap bitmapAsUser = wallpaperManager.getBitmapAsUser(i, false, i4);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("which = 0x", Integer.toHexString(i4), "DisplayRemapEngineEx-->", "SystemUi--Wallpaper");
        }
        if (bitmapAsUser != null && i3 == 16) {
            if (bitmapAsUser.isRecycled()) {
                LogUtil.w$default(8, LogUtil.TAG_WALLPAPER, "DisplayRemapEngineEx", "setCacheBitmap bitmap is recycled");
            } else {
                if (!Intrinsics.areEqual(this.cacheBitmap, bitmapAsUser) && (bitmap = this.cacheBitmap) != null) {
                    bitmap.recycle();
                }
                this.cacheBitmap = bitmapAsUser;
                this.cacheBitmapWallpaperId = getFlatDisplaySystemWallpaperId();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                    Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("setCacheBitmap set cache with id=" + this.cacheBitmapWallpaperId + " cacheBitmap=" + this.cacheBitmap));
                }
            }
        }
        Trace.traceEnd(8L);
        return bitmapAsUser;
    }

    public final Bitmap getCacheBitmap(int i) {
        if (i != 16) {
            return null;
        }
        Bitmap bitmap = this.cacheBitmap;
        int i2 = this.cacheBitmapWallpaperId;
        if (bitmap == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->getCacheBitmap no cache");
            }
            return null;
        }
        if (!bitmap.isRecycled()) {
            if (i2 == getFlatDisplaySystemWallpaperId()) {
                return bitmap;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->getCacheBitmap cache is expired");
            }
            return null;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("getCacheBitmap cache is recycled cacheBitmap=" + this.cacheBitmap));
        }
        this.cacheBitmap = null;
        return null;
    }

    public boolean getFoldStatusChange() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("getFoldStatusChange : ", "DisplayRemapEngineEx-->", "SystemUi--Wallpaper", this.foldStatusChange);
        }
        return this.foldStatusChange;
    }

    public boolean isBitmapReloadRequired(Bitmap bitmap) {
        boolean z = this.isBitmapReloadRequired;
        this.isBitmapReloadRequired = false;
        if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->isBitmapReloadRequired=true");
        }
        return z;
    }

    public boolean isHoldingBitmap(Bitmap bitmap) {
        return Intrinsics.areEqual(bitmap, this.cacheBitmap) || Intrinsics.areEqual(bitmap, this.preloadBitmap);
    }

    public final void notifyEngineDrawingState(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("notifyEngineDrawingState drawing: ", getEngine().getWallpaperFlags(), " wallpaperType: ", "DisplayRemapEngineEx-->", z));
        }
        if (z) {
            getEngine().onCommand("wallpaper.wait.engine.drawn", 1, 0, 0, null, false);
        } else {
            getEngine().onCommand("wallpaper.wait.engine.drawn", 0, 0, 0, null, false);
        }
    }

    public Pair<Boolean, Bundle> onCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
        boolean z2;
        if (Intrinsics.areEqual(str, "switchPhysicalDisplay")) {
            z2 = true;
            if (i == 16 || i == 32) {
                setFoldStatusChange(true);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                    Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("onSwitchingDisplay foldStatusChange=" + this.foldStatusChange + " "));
                }
                notifyEngineDrawingState(true);
                this.pendingDisplayFlag.set(i);
                DelayableExecutor delayableExecutor = this.longExecutor;
                onDestroy.1 r2 = new onDestroy.1(1);
                r2.this$0 = this;
                VarHandle.storeStoreFence();
                delayableExecutor.executeAtTime(r2, 0L);
            } else {
                LogUtil.w$default(8, LogUtil.TAG_WALLPAPER, "DisplayRemapEngineEx", "onSwitchingDisplay ignore illegal flag=" + i);
            }
        } else {
            z2 = false;
        }
        return new Pair<>(Boolean.valueOf(z2), (Object) null);
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void onCreate(SurfaceHolder surfaceHolder) {
        super.onCreate(surfaceHolder);
        this.surfaceHolder = surfaceHolder;
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getEngine().getDisplayContext());
        this.wallpaperManager = wallpaperManager;
        if (wallpaperManager == null) {
            wallpaperManager = null;
        }
        this.isLockscreenLiveWallpaperEnabled = wallpaperManager.isLockscreenLiveWallpaperEnabled();
        Context displayContext = getEngine().getDisplayContext();
        int i = 16;
        if (displayContext != null && Settings.Global.getInt(displayContext.getContentResolver(), ZoomUtil.FOLD_MODE, 1) != 1) {
            i = 32;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "initDisplayFlag=", "DisplayRemapEngineEx-->", "SystemUi--Wallpaper");
        }
        this.currentDisplayFlag.set(i);
        this.pendingDisplayFlag.set(i);
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public final void onDestroy() {
        ExecutorImpl executorImpl = this.longExecutor;
        onDestroy.1 r1 = new onDestroy.1(0);
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        executorImpl.execute(r1);
    }

    public final void onDisplayFlagChanged() {
        boolean z;
        Handler threadHandler;
        Trace.traceBegin(8L, "DisplayRemapEngineEx#onDisplayFlagChanged");
        int i = this.pendingDisplayFlag.get();
        int i2 = this.currentDisplayFlag.get();
        if (i == i2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(i, "onDisplayFlagChange flag=", " isn't changed", "DisplayRemapEngineEx-->", "SystemUi--Wallpaper");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onDisplayFlagChange from ", i2, i, " to ", "DisplayRemapEngineEx-->"));
        }
        this.currentDisplayFlag.set(i);
        boolean z2 = true;
        this.isBitmapReloadRequired = true;
        Rect rect = this.lastRequestSurfaceSize.get();
        WallpaperManager wallpaperManager = this.wallpaperManager;
        if (wallpaperManager == null) {
            wallpaperManager = null;
        }
        int sourceFlag = getEngineWrapper().getSourceFlag();
        if (!this.isLockscreenLiveWallpaperEnabled) {
            sourceFlag = 1;
        }
        Rect rectPeekBitmapDimensions = peekBitmapDimensions(wallpaperManager, sourceFlag);
        if (Intrinsics.areEqual(rectPeekBitmapDimensions, rect)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("updateSurfaceSize size=" + rectPeekBitmapDimensions + " isn't change"));
            }
            z = false;
        } else {
            rect.set(rectPeekBitmapDimensions);
            z = true;
        }
        HandlerThread workThread = getEngineWrapper().getWorkThread();
        if (workThread != null && (threadHandler = workThread.getThreadHandler()) != null) {
            threadHandler.removeCallbacks(this.preloadBitmapRecycleRunnable);
        }
        this.preloadBitmapRecycleRunnable.run();
        if (z || this.drawFrameOnSurfaceChange) {
            this.drawFrameOnSurfaceChange = true;
            int i3 = this.currentDisplayFlag.get();
            int sourceFlag2 = getEngineWrapper().getSourceFlag();
            if (!this.isLockscreenLiveWallpaperEnabled) {
                sourceFlag2 = 1;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("preloadBitmap user=-2 display=", i3, sourceFlag2, " type=", "DisplayRemapEngineEx-->"));
            }
            Bitmap cacheBitmap = getCacheBitmap(i3);
            if (cacheBitmap == null || cacheBitmap.isRecycled()) {
                WallpaperManager wallpaperManager2 = this.wallpaperManager;
                if (wallpaperManager2 == null) {
                    wallpaperManager2 = null;
                }
                cacheBitmap = getBitmapInternal(wallpaperManager2, -2, sourceFlag2, i3);
                z2 = false;
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("getCacheBitmap cacheBitmap=" + cacheBitmap));
            }
            this.preloadBitmap = cacheBitmap;
            if (z2) {
                notifyEngineDrawingState(false);
            }
        } else {
            notifyEngineDrawingState(false);
            getEngineWrapper().this$1.drawFrameInternal();
        }
        if (z || this.drawFrameOnSurfaceChange) {
            Rect rect2 = this.lastRequestSurfaceSize.get();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("onDisplayFlagChange setFixedSize " + rect2));
            }
            SurfaceHolder surfaceHolder = this.surfaceHolder;
            (surfaceHolder != null ? surfaceHolder : null).setFixedSize(rect2.width(), rect2.height());
        }
        Trace.traceEnd(8L);
    }

    public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Rect rect = this.lastRequestSurfaceSize.get();
        if (this.drawFrameOnSurfaceChange && rect.width() == i2 && rect.height() == i3) {
            this.drawFrameOnSurfaceChange = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onSurfaceChanged draw frame, width:", i2, i3, " height:", "DisplayRemapEngineEx-->"));
            }
            setNeedForceDraw(true);
            ImageWallpaper.CanvasEngine canvasEngine = getEngineWrapper().this$1;
            ExecutorImpl executorImpl = canvasEngine.this$0.mLongExecutor;
            ImageWallpaper$CanvasEngine$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ImageWallpaper$CanvasEngine$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = canvasEngine;
            VarHandle.storeStoreFence();
            executorImpl.execute(externalSyntheticLambda0);
        }
    }

    public final void onSurfaceSizeSet(int i, int i2) {
        this.lastRequestSurfaceSize.set(new Rect(0, 0, i, i2));
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Rect peekBitmapDimensions(WallpaperManager wallpaperManager, int i) {
        Context displayContext = getEngine().getDisplayContext();
        if (displayContext == null) {
            LogUtil.w$default(8, LogUtil.TAG_WALLPAPER, "DisplayRemapEngineEx", "peekWallpaperDimensions fail with no context");
            return new Rect(0, 0, 0, 0);
        }
        Rect rectPeekWallpaperDimensions = WallpaperUtilKt.peekWallpaperDimensions(displayContext, wallpaperManager, i, -2, this.currentDisplayFlag.get());
        return rectPeekWallpaperDimensions == null ? new Rect(0, 0, 0, 0) : rectPeekWallpaperDimensions;
    }

    public void reportFinishEngineDrawn(Rect rect) {
        if (Intrinsics.areEqual(rect, this.lastRequestSurfaceSize.get())) {
            notifyEngineDrawingState(false);
        }
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void setFixedSize(SurfaceHolder surfaceHolder, int i, int i2, DelayableExecutor delayableExecutor) {
        surfaceHolder.setFixedSize(i, i2);
    }

    public void setFoldStatusChange(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setFoldStatusChange : ", "DisplayRemapEngineEx-->", "SystemUi--Wallpaper", z);
        }
        this.foldStatusChange = z;
    }

    public void setLastDrawnSurfaceSize(Rect rect) {
        Rect rect2 = this.lastDrawnSurfaceSize.get();
        if (Intrinsics.areEqual(rect, rect2)) {
            return;
        }
        rect2.set(rect);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DisplayRemapEngineEx-->".concat("setLastDrawnSurfaceSize: " + rect));
        }
    }
}
