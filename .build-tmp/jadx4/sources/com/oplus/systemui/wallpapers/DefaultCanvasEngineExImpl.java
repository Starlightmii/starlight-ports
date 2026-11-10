package com.oplus.systemui.wallpapers;

import android.app.WallpaperManager;
import android.app.WindowConfiguration;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Trace;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import androidx.collection.FloatList$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.wallpapers.CanvasEngineEx;
import com.android.systemui.wallpapers.CanvasEngineWrapper;
import com.android.systemui.wallpapers.ImageWallpaper;
import com.android.systemui.wallpapers.ImageWallpaper$CanvasEngine$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.utils.WallpaperUtilKt;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DefaultCanvasEngineExImpl extends CanvasEngineEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DefaultCanvasEngineEx";
    public boolean adaptScreenBoundsEnable;
    private final OplusFeatureConfigManager.OnFeatureActionObserver customizeWallpaperObserver;
    private final CanvasEngineWrapper engineWrapper;
    private SurfaceHolder surfaceHolder;
    private final AtomicReference<Rect> lastRequestSurfaceSize = new AtomicReference<>(new Rect(0, 0, 0, 0));
    private final Rect mLastBounds = new Rect();
    private final AtomicBoolean isNeedForceDraw = new AtomicBoolean(false);
    private final OplusFeatureConfigManager featureConfigManager = OplusFeatureConfigManager.getInstance();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DefaultCanvasEngineExImpl(CanvasEngineWrapper canvasEngineWrapper) {
        this.engineWrapper = canvasEngineWrapper;
        DefaultCanvasEngineExImpl$customizeWallpaperObserver$1 defaultCanvasEngineExImpl$customizeWallpaperObserver$1 = new DefaultCanvasEngineExImpl$customizeWallpaperObserver$1();
        defaultCanvasEngineExImpl$customizeWallpaperObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.customizeWallpaperObserver = defaultCanvasEngineExImpl$customizeWallpaperObserver$1;
    }

    public Bitmap getBitmap(WallpaperManager wallpaperManager, int i, int i2) {
        Bitmap bitmap;
        Trace.traceBegin(8L, "DefaultCanvasEngineEx#getBitmap");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if ((131072 & i2) == 0 || wallpaperManager.isLockscreenLiveWallpaperEnabled()) {
            FeatureOption.INSTANCE.getClass();
            bitmap = super.getBitmap(wallpaperManager, i, FeatureOption.isTablet() ? i2 | 64 : i2);
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->getBitmap call wpm getBitmapAsUser");
            }
            FeatureOption.INSTANCE.getClass();
            bitmap = wallpaperManager.getBitmapAsUser(i, false, FeatureOption.isTablet() ? 131137 : 131073);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->".concat("getBitmap: " + bitmap + " sourceFlag: " + i2 + " cast: " + (System.currentTimeMillis() - jCurrentTimeMillis)));
        }
        Trace.traceEnd(8L);
        return bitmap;
    }

    public final WallpaperService.Engine getEngine() {
        return this.engineWrapper.getEngine();
    }

    public final CanvasEngineWrapper getEngineWrapper() {
        return this.engineWrapper;
    }

    public final Rect getScreenBounds(Configuration configuration) {
        WindowConfiguration windowConfiguration;
        Resources resources;
        Rect maxBounds = null;
        if (configuration == null) {
            Context displayContext = getEngine().getDisplayContext();
            configuration = (displayContext == null || (resources = displayContext.getResources()) == null) ? null : resources.getConfiguration();
        }
        if (configuration != null && (windowConfiguration = configuration.windowConfiguration) != null) {
            maxBounds = windowConfiguration.getMaxBounds();
        }
        Rect rect = new Rect(maxBounds);
        if (rect.isEmpty()) {
            return rect;
        }
        int iWidth = rect.width();
        int iHeight = rect.height();
        return iWidth < iHeight ? new Rect(0, 0, iWidth, iHeight) : new Rect(0, 0, iHeight, iWidth);
    }

    public boolean isNeedForceDraw() {
        return this.isNeedForceDraw.get();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Rect surfaceFrame;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() || WallpaperUtilKt.isMTKPlatform()) {
            return;
        }
        if (!this.adaptScreenBoundsEnable) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->onConfigurationChanged, not adapt screen bounds");
                return;
            }
            return;
        }
        Rect screenBounds = getScreenBounds(configuration);
        if (screenBounds.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->onConfigurationChanged, window bounds is empty");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->".concat("onConfigurationChanged, newWindowBounds = " + screenBounds + ", mLastBounds = " + this.mLastBounds));
        }
        if (screenBounds.width() == this.mLastBounds.width() || screenBounds.height() == this.mLastBounds.height()) {
            return;
        }
        this.mLastBounds.set(screenBounds);
        this.mLastBounds.width();
        int iHeight = this.mLastBounds.height();
        SurfaceHolder surfaceHolder = this.surfaceHolder;
        if (surfaceHolder == null || (surfaceFrame = surfaceHolder.getSurfaceFrame()) == null) {
            return;
        }
        int iWidth = surfaceFrame.width();
        int iHeight2 = surfaceFrame.height();
        if (iHeight2 == 0) {
            return;
        }
        float f = (iWidth * (iHeight / iHeight2)) + 0.5f;
        if (this.isNeedForceDraw.compareAndSet(false, true)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->".concat("updateSurfaceSize, surfaceFrame = " + surfaceFrame + ", h = " + iHeight + " scaleWidth = " + f + " , surfaceHolder = " + this.surfaceHolder));
            }
            SurfaceHolder surfaceHolder2 = this.surfaceHolder;
            if (surfaceHolder2 != null) {
                updateRequestSurfaceSize(surfaceHolder2, (int) f, iHeight);
            }
        }
    }

    public void onCreate(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.featureConfigManager.registerFeatureActionObserver(this.customizeWallpaperObserver);
        setWallpaperTouchEnable(false);
    }

    public void onDestroy() {
        ImageWallpaper.CanvasEngine canvasEngine = this.engineWrapper.this$1;
        canvasEngine.mBitmapUsages = 0;
        ExecutorImpl executorImpl = canvasEngine.this$0.mLongExecutor;
        ImageWallpaper$CanvasEngine$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ImageWallpaper$CanvasEngine$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = canvasEngine;
        VarHandle.storeStoreFence();
        executorImpl.execute(externalSyntheticLambda0);
        this.featureConfigManager.unregisterFeatureActionObserver(this.customizeWallpaperObserver);
    }

    public Rect peekBitmapDimensions(WallpaperManager wallpaperManager, int i) {
        Context displayContext = getEngine().getDisplayContext();
        if (displayContext == null) {
            return new Rect(0, 0, 0, 0);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            i |= 64;
        }
        Rect rectPeekWallpaperDimensions = WallpaperUtilKt.peekWallpaperDimensions(displayContext, wallpaperManager, i, -2, 0);
        return rectPeekWallpaperDimensions == null ? new Rect(0, 0, 0, 0) : rectPeekWallpaperDimensions;
    }

    public void setFixedSize(SurfaceHolder surfaceHolder, int i, int i2, DelayableExecutor delayableExecutor) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() || WallpaperUtilKt.isMTKPlatform()) {
            super.setFixedSize(surfaceHolder, i, i2, delayableExecutor);
            return;
        }
        Rect screenBounds = getScreenBounds(null);
        this.adaptScreenBoundsEnable = i2 != 0 && screenBounds.height() > i2;
        if (screenBounds.isEmpty() || !this.adaptScreenBoundsEnable) {
            updateRequestSurfaceSize(surfaceHolder, i, i2);
            return;
        }
        this.mLastBounds.set(screenBounds);
        float fHeight = (i * (this.mLastBounds.height() / i2)) + 0.5f;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            int iHeight = this.mLastBounds.height();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("setFixedSize, width = ", i, i2, ", height = ", ", targetWidth = ");
            sbM.append(fHeight);
            sbM.append(", targetHeight = ");
            sbM.append(iHeight);
            Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->".concat(sbM.toString()));
        }
        if (delayableExecutor == null) {
            updateRequestSurfaceSize(surfaceHolder, (int) fHeight, this.mLastBounds.height());
            return;
        }
        setFixedSize.2.1 r7 = new setFixedSize.2.1();
        r7.this$0 = this;
        r7.$surfaceHolder = surfaceHolder;
        r7.$targetWidth = fHeight;
        VarHandle.storeStoreFence();
        ((ExecutorImpl) delayableExecutor).execute(r7);
    }

    public void setNeedForceDraw(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setNeedForceDraw, isForceDraw = ", "DefaultCanvasEngineEx-->", "SystemUi--Wallpaper", z);
        }
        this.isNeedForceDraw.set(z);
    }

    public final void setWallpaperTouchEnable(boolean z) {
        getEngine().onCommand("wallpaper.flag.notouch", 0, 0, 0, MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m(PictorialAppInfo.TAG_ENABLE, z), false);
    }

    public final void updateRequestSurfaceSize(SurfaceHolder surfaceHolder, int i, int i2) {
        Rect rect = this.lastRequestSurfaceSize.get();
        if (rect.width() != i || rect.height() != i2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateRequestSurfaceSize, width = ", i, i2, ", height = ", ", lastSize = ");
                sbM.append(rect);
                Log.d("SystemUi--Wallpaper", "DefaultCanvasEngineEx-->".concat(sbM.toString()));
            }
            if (rect.isEmpty()) {
                setNeedForceDraw(true);
            }
            this.lastRequestSurfaceSize.set(new Rect(0, 0, i, i2));
        }
        surfaceHolder.setFixedSize(i, i2);
    }

    public boolean wallpaperSupportsWcg(WallpaperManager wallpaperManager, int i, Bitmap bitmap) {
        if (wallpaperManager.shouldEnableWideColorGamut()) {
            return (bitmap == null || bitmap.getColorSpace() == null || Intrinsics.areEqual(bitmap.getColorSpace(), ColorSpace.get(ColorSpace.Named.SRGB)) || !wallpaperManager.getColorManagementProxy().getSupportedColorSpaces().contains(bitmap.getColorSpace())) ? false : true;
        }
        return false;
    }

    public Rect peekBitmapDimensions(WallpaperManager wallpaperManager) {
        return peekBitmapDimensions(wallpaperManager, 1);
    }
}
