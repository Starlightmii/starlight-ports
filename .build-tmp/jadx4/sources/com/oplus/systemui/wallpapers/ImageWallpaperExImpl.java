package com.oplus.systemui.wallpapers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.service.wallpaper.WallpaperService;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.wallpapers.CanvasEngineEx;
import com.android.systemui.wallpapers.CanvasEngineWrapper;
import com.android.systemui.wallpapers.ImageWallpaper;
import com.android.systemui.wallpapers.ImageWallpaperEx;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.systemui.keyguard.anim.OplusWallpaperAnimControllerImpl;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplus.systemui.keyguard.wallpaper.WallpaperServiceInteract;
import com.oplus.systemui.utils.WallpaperUtilKt;
import com.oplus.systemui.wallpapers.pictorialwallpaper.PictorialCanvasEngineExImpl;
import com.oplus.systemui.wallpapers.separatewallpaper.DisplayRemapCanvasEngineExImpl;
import com.oplus.systemui.wallpapers.separatewallpaper.StableDisplayCanvasEngineExImpl;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageWallpaperExImpl extends ImageWallpaperEx {
    public static final Companion Companion = new Companion();
    private static final String SETTINGS_SHOULD_ZOOM_WALLPAPER_KEY = "oplus_customize_settings_zoom_wallpaper_enable";
    private Context context;
    private CanvasEngineWrapper engineWrapper;
    private final Lazy immersiveController;
    public final boolean isDisableRemapDisplay;
    public boolean lastDrawInNightMode;
    private final OplusLockScreenWallpaperController lockScreenWallpaper;
    private final DelayableExecutor longExecutor;
    private final Lazy oplusWallpaperAnimControllerImpl;
    public int shouldZoomOutWallpaper;
    private final WallpaperServiceInteract wallpaperServiceInteract;
    public ImageWallpaperExImpl$initObserver$1 zoomObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ImageWallpaperExImpl(DelayableExecutor delayableExecutor, OplusLockScreenWallpaperController oplusLockScreenWallpaperController, WallpaperServiceInteract wallpaperServiceInteract, Lazy lazy, Lazy lazy2) {
        this.longExecutor = delayableExecutor;
        this.lockScreenWallpaper = oplusLockScreenWallpaperController;
        this.wallpaperServiceInteract = wallpaperServiceInteract;
        this.oplusWallpaperAnimControllerImpl = lazy;
        this.immersiveController = lazy2;
        int i = ImageWallpaperExt.$r8$clinit;
        this.isDisableRemapDisplay = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.fold_remap_display_disabled");
        this.shouldZoomOutWallpaper = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getZoomWallpaperValue() {
        int i = 0;
        if (!CustomizeFeatureOption.sIsWallpaperNewAnimDisable) {
            return 0;
        }
        if (CustomizeFeatureOption.sIsWallpaperScaleDefaultOn) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
                i = 1;
            }
        }
        Context context = this.context;
        return context != null ? Settings.System.getIntForUser(context.getContentResolver(), SETTINGS_SHOULD_ZOOM_WALLPAPER_KEY, i, -2) : i;
    }

    public CanvasEngineEx newCanvasEngineExt(CanvasEngineWrapper canvasEngineWrapper) {
        boolean zIsPictorialType = this.lockScreenWallpaper.isPictorialType();
        ImageWallpaper.CanvasEngine.1 r10 = (ImageWallpaper.CanvasEngine.1) canvasEngineWrapper;
        Context displayContext = r10.getEngine().getDisplayContext();
        Integer numValueOf = displayContext != null ? Integer.valueOf(displayContext.getDisplayId()) : null;
        boolean supportSeparateWallpaper = WallpaperUtilKt.getSupportSeparateWallpaper();
        FeatureOption.INSTANCE.getClass();
        boolean zIsFoldRemapDisabled = FeatureOption.isFoldRemapDisabled();
        int sourceFlag = r10.getSourceFlag();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("newCanvasEngineExt isPictorialType: ", " supportSeparateWallpaper: ", " FeatureOption.isFoldRemapDisabled: ", zIsPictorialType, supportSeparateWallpaper);
        PromptIconState$.ExternalSyntheticOutline0.m(sourceFlag, " engineWrapper.getSourceFlag(): ", " displayId: ", sbM, zIsFoldRemapDisabled);
        sbM.append(numValueOf);
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, "ImageWallpaperEx", sbM.toString(), null, 8);
        if (r10.getSourceFlag() == 2) {
            this.engineWrapper = r10;
        }
        boolean z = false;
        boolean z2 = zIsPictorialType && r10.getSourceFlag() == 2;
        if (!FeatureOption.isDragonfly()) {
            z = z2;
        } else if (numValueOf != null && numValueOf.intValue() == 0 && z2) {
            z = true;
        }
        if (z) {
            return new PictorialCanvasEngineExImpl(r10, this.lockScreenWallpaper, (OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get());
        }
        if (WallpaperUtilKt.getSupportSeparateWallpaper()) {
            return FeatureOption.isFoldRemapDisabled() ? new StableDisplayCanvasEngineExImpl(r10) : new DisplayRemapCanvasEngineExImpl(r10, this.longExecutor);
        }
        return new DefaultCanvasEngineExImpl(r10);
    }

    public final void onCommandRedrawNeeded(int i) {
        Context context;
        if (!this.lockScreenWallpaper.isPictorialType() || ((context = this.context) != null && OplusDarkModeUtil.isNightMode(context) == this.lastDrawInNightMode)) {
            ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).onWallpaperSurfaceChanged(i, "onCommand");
        } else {
            LogUtil.i$default(LogUtil.TAG_WALLPAPER, "ImageWallpaperEx", "isPictorialType and dark mode switching", null, 8);
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [com.oplus.systemui.wallpapers.ImageWallpaperExImpl$initObserver$1] */
    public void onCreate(Context context, HandlerThread handlerThread) {
        ContentResolver contentResolver;
        this.context = context;
        this.shouldZoomOutWallpaper = getZoomWallpaperValue();
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, "ImageWallpaperEx", "onCreate", null, 8);
        final Handler threadHandler = handlerThread.getThreadHandler();
        this.zoomObserver = new ContentObserver(threadHandler) { // from class: com.oplus.systemui.wallpapers.ImageWallpaperExImpl$initObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                ImageWallpaperExImpl imageWallpaperExImpl = this.this$0;
                imageWallpaperExImpl.shouldZoomOutWallpaper = imageWallpaperExImpl.getZoomWallpaperValue();
            }
        };
        Context context2 = this.context;
        if (context2 == null || (contentResolver = context2.getContentResolver()) == null) {
            return;
        }
        Uri uriFor = Settings.System.getUriFor(SETTINGS_SHOULD_ZOOM_WALLPAPER_KEY);
        ImageWallpaperExImpl$initObserver$1 imageWallpaperExImpl$initObserver$1 = this.zoomObserver;
        contentResolver.registerContentObserver(uriFor, false, imageWallpaperExImpl$initObserver$1 != null ? imageWallpaperExImpl$initObserver$1 : null, -2);
    }

    public final void onDestroy() {
        ContentResolver contentResolver;
        Context context = this.context;
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            ImageWallpaperExImpl$initObserver$1 imageWallpaperExImpl$initObserver$1 = this.zoomObserver;
            if (imageWallpaperExImpl$initObserver$1 == null) {
                imageWallpaperExImpl$initObserver$1 = null;
            }
            contentResolver.unregisterContentObserver(imageWallpaperExImpl$initObserver$1);
        }
        this.context = null;
    }

    public final void onDrawn() {
        Context context = this.context;
        if (context != null) {
            this.lastDrawInNightMode = OplusDarkModeUtil.isNightMode(context);
        }
    }

    public final void onEngineCreate(int i) {
        WallpaperService.Engine engine;
        Context displayContext;
        ImageWallpaper.CanvasEngine.1 r0 = this.engineWrapper;
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, "ImageWallpaperEx", "onEngineCreate sourceFlag: " + i + ", displayId: " + ((r0 == null || (engine = r0.getEngine()) == null || (displayContext = engine.getDisplayContext()) == null) ? null : Integer.valueOf(displayContext.getDisplayId())), null, 8);
        if (i == 2) {
            this.wallpaperServiceInteract.holdEngineWrapper(new WeakReference(this.engineWrapper));
        }
    }

    public final void onEngineDestroy(int i) {
        WallpaperService.Engine engine;
        Context displayContext;
        ImageWallpaper.CanvasEngine.1 r0 = this.engineWrapper;
        LogUtil.i$default(LogUtil.TAG_WALLPAPER, "ImageWallpaperEx", "onEngineDestroy sourceFlag: " + i + ", displayId: " + ((r0 == null || (engine = r0.getEngine()) == null || (displayContext = engine.getDisplayContext()) == null) ? null : Integer.valueOf(displayContext.getDisplayId())), null, 8);
        if (i == 2) {
            this.wallpaperServiceInteract.releaseEngineWrapper();
        }
    }

    public final void onOffsetsChanged(int i) {
        ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).onWallpaperSurfaceChanged(i, "offset_change");
    }

    public final void onSurfaceRedrawNeeded(int i) {
        ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).onWallpaperSurfaceChanged(i, "redraw");
    }

    public final void onVisibilityChanged(int i, boolean z) {
        ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).onVisibilityChanged(i, z);
    }

    public boolean shouldZoomOutWallpaper(Context context, int i) {
        boolean z = false;
        boolean z2 = (i & 2) != 0 && ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).isEnableZoomOutWallpaper();
        if ((context == null || context.getDisplayId() != 1 || !this.isDisableRemapDisplay) && (this.shouldZoomOutWallpaper == 1 || z2)) {
            z = true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("ImageWallpaperEx-->", "SystemUi--Wallpaper", KeyguardLogger$.ExternalSyntheticOutline0.m("shouldZoomOutWallpaper flag=", i, " wallpaperAnimEnable=", " isEnableZoomOut=", z2), z);
        }
        return z;
    }
}
