package com.oplus.systemui.wallpapers.pictorialwallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Trace;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.DependencyEx;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.wallpapers.CanvasEngineWrapper;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.anim.OplusWallpaperAnimControllerImpl;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialCanvasEngineExImpl extends DefaultCanvasEngineExImpl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PictorialCanvasEngineExImpl";
    private Rect cacheRect;
    private Bitmap curBitmap;
    public boolean isBitmapReloadRequired;
    public boolean isLastDrawBitmapFromPictorial;
    private final OplusLockScreenWallpaperController lockScreenWallpaper;
    private final OplusWallpaperAnimControllerImpl oplusWallpaperAnimController;
    private PictorialMediator pictorialMediator;
    private SurfaceHolder surfaceHolder;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PictorialCanvasEngineExImpl(CanvasEngineWrapper canvasEngineWrapper, OplusLockScreenWallpaperController oplusLockScreenWallpaperController, OplusWallpaperAnimControllerImpl oplusWallpaperAnimControllerImpl) {
        super(canvasEngineWrapper);
        this.lockScreenWallpaper = oplusLockScreenWallpaperController;
        this.oplusWallpaperAnimController = oplusWallpaperAnimControllerImpl;
    }

    public final Bitmap callSuperGetBitmap(WallpaperManager wallpaperManager, int i, int i2) {
        Bitmap bitmapCreateCenterCropBitmapIfNeedForPhone;
        SurfaceHolder surfaceHolder;
        Bitmap bitmap = super.getBitmap(wallpaperManager, i, i2);
        if (bitmap == null) {
            return null;
        }
        if (this.lockScreenWallpaper.isPictorialReboot()) {
            LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "need forbid redraw", null, 8);
            return bitmap;
        }
        Rect rect = this.cacheRect;
        if (rect != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "PictorialCanvasEngineExImpl-->".concat("targetRect=" + rect));
            }
            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeed(bitmap, rect.width(), rect.height(), bitmap.getConfig());
        } else {
            bitmapCreateCenterCropBitmapIfNeedForPhone = PictorialUtils.createCenterCropBitmapIfNeedForPhone(getEngine().getDisplayContext(), bitmap, bitmap.getConfig());
        }
        if ((bitmap.getWidth() != bitmapCreateCenterCropBitmapIfNeedForPhone.getWidth() || bitmap.getHeight() != bitmapCreateCenterCropBitmapIfNeedForPhone.getHeight()) && (surfaceHolder = this.surfaceHolder) != null) {
            setFixedSize(surfaceHolder, bitmapCreateCenterCropBitmapIfNeedForPhone.getWidth(), bitmapCreateCenterCropBitmapIfNeedForPhone.getHeight(), null);
        }
        if (!bitmap.equals(bitmapCreateCenterCropBitmapIfNeedForPhone)) {
            bitmap.recycle();
        }
        return bitmapCreateCenterCropBitmapIfNeedForPhone;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0062  */
    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Bitmap getBitmap(WallpaperManager wallpaperManager, int i, int i2) {
        Bitmap bitmapCallSuperGetBitmap;
        boolean z;
        Trace.traceBegin(8L, "PictorialCanvasEngineExImpl#getBitmap");
        boolean z2 = this.lockScreenWallpaper.isPictorialType() && (i2 & 2) != 0;
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("getBitmap begin sourceFlag: ", i2, ", useKeyguardBitmap: ", z2), null, 8);
        if (z2) {
            bitmapCallSuperGetBitmap = getCurrentWallpaperBitmap(false);
            if (bitmapCallSuperGetBitmap == null) {
                KeyguardWallpaperInfo currentWallpaperInfo = this.lockScreenWallpaper.getCurrentWallpaperInfo();
                if (currentWallpaperInfo != null && currentWallpaperInfo.isPictorialType()) {
                    z = false;
                }
                if (bitmapCallSuperGetBitmap == null) {
                    bitmapCallSuperGetBitmap = callSuperGetBitmap(wallpaperManager, i, i2);
                }
            } else if (bitmapCallSuperGetBitmap.getWidth() != getEngine().getSurfaceHolder().getSurfaceFrame().width()) {
                LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "size not match, wait for surface changed", null, 8);
                return null;
            }
            z = true;
            if (bitmapCallSuperGetBitmap == null) {
                bitmapCallSuperGetBitmap = callSuperGetBitmap(wallpaperManager, i, i2);
            }
        } else {
            bitmapCallSuperGetBitmap = callSuperGetBitmap(wallpaperManager, i, i2);
            z = true;
        }
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "getBitmap=" + bitmapCallSuperGetBitmap + " w=" + (bitmapCallSuperGetBitmap != null ? Integer.valueOf(bitmapCallSuperGetBitmap.getWidth()) : null) + " h=" + (bitmapCallSuperGetBitmap != null ? Integer.valueOf(bitmapCallSuperGetBitmap.getHeight()) : null) + " s=" + (bitmapCallSuperGetBitmap != null ? Integer.valueOf(bitmapCallSuperGetBitmap.getAllocationByteCount()) : null), null, 8);
        Trace.traceEnd(8L);
        if (z) {
            this.isBitmapReloadRequired = false;
        }
        Bitmap bitmap = this.curBitmap;
        boolean z3 = bitmap == null && this.isLastDrawBitmapFromPictorial;
        boolean z4 = bitmap != null;
        this.isLastDrawBitmapFromPictorial = z4;
        PictorialMediator pictorialMediator = this.pictorialMediator;
        if (pictorialMediator != null) {
            pictorialMediator.setLastDrawBitmapFromPictorial(z4);
        }
        if (z3) {
            LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "clear wallpaper info and notify wallpaper color change", null, 8);
            this.lockScreenWallpaper.clearWallpaperInfo();
            Context displayContext = getEngineWrapper().getEngine().getDisplayContext();
            if (displayContext != null) {
                LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "notify wallpaper change by system wallpaper", null, 8);
                OplusWallpaperColorManager.INSTANCE.onWallpaperChange(displayContext, 0, false, this.lockScreenWallpaper);
            }
        }
        return bitmapCallSuperGetBitmap;
    }

    public final Bitmap getCurrentWallpaperBitmap(boolean z) {
        Bitmap bitmap;
        if (z || (bitmap = this.curBitmap) == null || bitmap.isRecycled()) {
            this.curBitmap = this.lockScreenWallpaper.copyWallpaperBitmap();
        }
        Bitmap bitmap2 = this.curBitmap;
        if (bitmap2 != null && bitmap2.isRecycled()) {
            this.curBitmap = null;
        }
        Bitmap bitmap3 = this.curBitmap;
        Boolean boolValueOf = bitmap3 != null ? Boolean.valueOf(bitmap3.isRecycled()) : null;
        Bitmap bitmap4 = this.curBitmap;
        Integer numValueOf = bitmap4 != null ? Integer.valueOf(bitmap4.getWidth()) : null;
        Bitmap bitmap5 = this.curBitmap;
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "getCurrentWallpaperBitmap curBitmap=" + bitmap3 + " forceRefresh=" + z + "recycle=" + boolValueOf + " w=" + numValueOf + " h=" + (bitmap5 != null ? Integer.valueOf(bitmap5.getHeight()) : null), null, 8);
        return this.curBitmap;
    }

    public boolean isBitmapReloadRequired(Bitmap bitmap) {
        return this.isBitmapReloadRequired;
    }

    public final boolean isNeedForbidRedraw() {
        return this.lockScreenWallpaper.isPictorialReboot();
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public final boolean isNeedForceDraw() {
        return true;
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void onCreate(SurfaceHolder surfaceHolder) {
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "onCreate", null, 8);
        this.surfaceHolder = surfaceHolder;
        setWallpaperTouchEnable(false);
        this.pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public final void onDestroy() {
        super.onDestroy();
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, "onDestroy", null, 8);
    }

    public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.onSurfaceChanged(surfaceHolder, i, i2, i3);
        this.oplusWallpaperAnimController.onPictorialWallpaperSurfaceChanged();
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public Rect peekBitmapDimensions(WallpaperManager wallpaperManager, int i) {
        int i2;
        Bitmap currentWallpaperBitmap;
        boolean z = this.lockScreenWallpaper.isPictorialType() && (i & 2) != 0;
        Rect rect = (!z || (currentWallpaperBitmap = getCurrentWallpaperBitmap(true)) == null) ? null : new Rect(0, 0, currentWallpaperBitmap.getWidth(), currentWallpaperBitmap.getHeight());
        Rect rectPeekBitmapDimensions = super.peekBitmapDimensions(wallpaperManager, i);
        if (rect == null || rect.height() == 0 || rect.width() == 0) {
            Context displayContext = getEngineWrapper().getEngine().getDisplayContext();
            if (displayContext != null) {
                StringBuilder sb = PictorialUtils.sStringBuilder;
                KeyguardUtils.Companion companion = KeyguardUtils.Companion;
                int realScreenWidth = companion.getRealScreenWidth();
                int realScreenHeight = companion.getRealScreenHeight();
                WindowManager windowManager = (WindowManager) displayContext.getSystemService("window");
                if (windowManager != null) {
                    Point point = new Point();
                    windowManager.getDefaultDisplay().getRealSize(point);
                    realScreenWidth = Math.min(point.x, point.y);
                    realScreenHeight = Math.max(point.x, point.y);
                    KgdLogUtil.d("PictorialUtils", "getScreenDimensions sW=" + realScreenWidth + " sH=" + realScreenHeight);
                }
                rect = new Rect(0, 0, realScreenWidth, realScreenHeight);
            } else {
                rect = null;
            }
            if (rect != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                    Log.d("SystemUi--Wallpaper", "PictorialCanvasEngineExImpl-->".concat("defaultRect=" + rectPeekBitmapDimensions + ", resultRect=" + rect));
                }
                int i3 = rectPeekBitmapDimensions.right;
                if (i3 != 0 && (i2 = rectPeekBitmapDimensions.bottom) != 0) {
                    float fMax = Math.max(rect.right / i3, rect.bottom / i2);
                    rect.right = (int) (rectPeekBitmapDimensions.right * fMax);
                    rect.bottom = (int) (rectPeekBitmapDimensions.bottom * fMax);
                    this.cacheRect = rect;
                }
            }
        }
        if (rect == null || rect.height() == 0 || rect.width() == 0) {
            this.cacheRect = null;
        } else {
            rectPeekBitmapDimensions = rect;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "PictorialCanvasEngineExImpl-->".concat("peekWallpaperDimensions rect=" + rectPeekBitmapDimensions + " , isPictorial=" + z));
        }
        return rectPeekBitmapDimensions;
    }

    public final void resetBitmap() {
        this.curBitmap = null;
        this.lockScreenWallpaper.releaseDrawable();
        this.cacheRect = null;
    }

    public final void setBitmapReloadRequired(boolean z) {
        if (z != this.isBitmapReloadRequired) {
            this.isBitmapReloadRequired = z;
            LogUtil.i$default(LogUtil.TAG_WALLPAPER, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setBitmapReloadRequired to ", z), null, 8);
        }
    }

    @Override // com.oplus.systemui.wallpapers.DefaultCanvasEngineExImpl
    public void setFixedSize(SurfaceHolder surfaceHolder, int i, int i2, DelayableExecutor delayableExecutor) {
        surfaceHolder.setFixedSize(i, i2);
    }
}
