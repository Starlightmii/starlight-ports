package com.oplus.systemui.volume.utils.material;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.DependencyEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.oplus.posteffect.BlendParam;
import com.oplus.posteffect.Blur;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.BlurParam;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.view.OplusVolumeSettingsButton;
import com.oplus.systemui.volume.view.OplusVolumeSideAccessoryButton;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.drawable.MaskDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeBlurManager {
    public static final VolumeBlurManager INSTANCE = new VolumeBlurManager();
    private static final String TAG = "VolumeBlurManager";
    public static volatile float barPanelBottomAmount;
    public static long lastSideBarCaptureUptimeMs;
    public static volatile boolean volumeSliderBlurWanted;

    public static final void activateVolumePlatformBlurEngine(Context context) {
        if (isVolumePlatformBlurEnabled(context)) {
            setVolumePlatformBlurActive(true);
        }
    }

    public static void applyPlatformBlurMixAfterConfigWrite(ViewBlurProxy viewBlurProxy, boolean z) {
        BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
        PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
        if (z || barPanelBottomAmount != 0.0f || cachePlatformStaticBlurDrawable == null) {
            if (cachePlatformStaticBlurDrawable == null || (!z && barPanelBottomAmount <= 0.0f)) {
                viewBlurProxy.setBlurAmount(1.0f);
                viewBlurProxy.applyBlurConfig();
                if (cachePlatformStaticBlurDrawable != null) {
                    cachePlatformStaticBlurDrawable.invalidateSelf();
                    return;
                }
                return;
            }
            viewBlurProxy.setBlurAmount(1.0f);
            BlurMixConfig platformMixConfig = blurConfig.getPlatformMixConfig();
            PlatformBlurDrawable.Companion companion = PlatformBlurDrawable.Companion;
            if (!cachePlatformStaticBlurDrawable.changeBlurColor(platformMixConfig, false)) {
                LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, Bundler$.ExternalSyntheticOutline0.m("changeBlurColor failed mix=", blurConfig.getPlatformMixConfig().getClass().getSimpleName(), ", keep Static"));
                return;
            }
            int iPlatformBlurRadiusForAmount = blurConfig.platformBlurRadiusForAmount(1.0f);
            BlurDrawable blurDrawable = cachePlatformStaticBlurDrawable.getBlurDrawable();
            BlurParam blurParams = blurDrawable.getBlurParams();
            if (blurParams.getBlurRadius() != iPlatformBlurRadiusForAmount) {
                blurDrawable.setBlurParams(BlurParam.copy$default(blurParams, (BlendParam) null, iPlatformBlurRadiusForAmount, 59));
            }
            hotUpdateStaticPlatformLightAndStroke(cachePlatformStaticBlurDrawable, blurConfig);
            cachePlatformStaticBlurDrawable.invalidateSelf();
        }
    }

    public static final void applySettingsButtonMixColors(View view) {
        AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable;
        if (view != null && isVolumePlatformBlurEnabled(view.getContext())) {
            INSTANCE.getClass();
            if ((barPanelBottomAmount <= 0.0f || barPanelBottomAmount >= 1.0f) && (autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground())) != null) {
                ViewBlurProxy viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy();
                if (Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                    VolumeBlurConfigWriter volumeBlurConfigWriter = VolumeBlurConfigWriter.INSTANCE;
                    volumeBlurConfigWriter.writeSettingsPlatform(viewBlurProxy.getBlurConfig(), view.getContext(), volumeBlurConfigWriter.cornerFromConfig(viewBlurProxy.getBlurConfig()), view);
                    viewBlurProxy.setBlurAmount(1.0f);
                    viewBlurProxy.applyBlurConfig();
                    PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
                    if (cachePlatformStaticBlurDrawable != null) {
                        cachePlatformStaticBlurDrawable.invalidateSelf();
                    }
                    view.invalidate();
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0090  */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [android.widget.AbsSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
    /* JADX WARN: Type inference failed for: r8v7 */
    public static final void applyVolumeBarPanelBottomAmount(View view, float f, boolean z) {
        View view2;
        ?? r8;
        OplusVolumeSeekBar oplusVolumeSeekBar;
        ViewBlurProxy viewBlurProxy;
        if (view != null && isVolumePlatformBlurEnabled(view.getContext())) {
            if (view instanceof OplusVolumeSettingsButton) {
                applySettingsButtonMixColors(view);
                return;
            }
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
            if (!z && fCoerceIn == 0.0f) {
                VolumeBlurManager volumeBlurManager = INSTANCE;
                Drawable background = view.getBackground();
                volumeBlurManager.getClass();
                AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(background);
                if (autoBlurDrawableFindPlatformAutoBlurDrawable != null && (viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy()) != null && Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && viewBlurProxy.getCachePlatformStaticBlurDrawable() != null) {
                    return;
                }
            }
            AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable2 = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground());
            if (autoBlurDrawableFindPlatformAutoBlurDrawable2 != null) {
                ViewBlurProxy viewBlurProxy2 = autoBlurDrawableFindPlatformAutoBlurDrawable2.getViewBlurProxy();
                if (Intrinsics.areEqual(viewBlurProxy2.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                    BlurConfig blurConfig = viewBlurProxy2.getBlurConfig();
                    VolumeBlurConfigWriter volumeBlurConfigWriter = VolumeBlurConfigWriter.INSTANCE;
                    VolumeCornerSpec volumeCornerSpecCornerFromConfig = volumeBlurConfigWriter.cornerFromConfig(blurConfig);
                    if (view instanceof OplusVolumeSideAccessoryButton) {
                        view2 = view;
                        volumeBlurConfigWriter.writeSideAccessoryPlatform(blurConfig, ((OplusVolumeSideAccessoryButton) view).getContext(), volumeCornerSpecCornerFromConfig, fCoerceIn, view2);
                    } else {
                        view2 = view;
                        volumeBlurConfigWriter.writeBarPlatform(blurConfig, view2.getContext(), volumeCornerSpecCornerFromConfig, fCoerceIn, view2);
                    }
                    INSTANCE.getClass();
                    applyPlatformBlurMixAfterConfigWrite(viewBlurProxy2, z);
                } else {
                    view2 = view;
                }
            } else {
                view2 = view;
            }
            if (view2 instanceof OplusVolumeSeekBar) {
                oplusVolumeSeekBar = (OplusVolumeSeekBar) view2;
            } else {
                r8 = 0;
            }
            if (r8 != 0) {
                r8 = oplusVolumeSeekBar;
                applyVolumeBarProgressMixColors(r8.mProgressBlurDrawable, r8.mContext, z);
                r8.invalidate();
            }
            r8 = oplusVolumeSeekBar;
            view2.invalidate();
        }
    }

    public static final void applyVolumeBarProgressMixColors(AutoBlurDrawable autoBlurDrawable, Context context, boolean z) {
        if (autoBlurDrawable != null && isVolumePlatformBlurEnabled(context)) {
            ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
            if (Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                if (z || barPanelBottomAmount != 0.0f || viewBlurProxy.getCachePlatformStaticBlurDrawable() == null) {
                    Rect bounds = autoBlurDrawable.getBounds();
                    int iWidth = bounds.width() > 0 ? bounds.width() : viewBlurProxy.getView().getWidth();
                    int iHeight = bounds.height() > 0 ? bounds.height() : viewBlurProxy.getView().getHeight();
                    VolumeBlurConfigWriter volumeBlurConfigWriter = VolumeBlurConfigWriter.INSTANCE;
                    BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
                    VolumeCornerSpec volumeCornerSpecCornerFromConfig = volumeBlurConfigWriter.cornerFromConfig(viewBlurProxy.getBlurConfig());
                    float f = barPanelBottomAmount;
                    Object view = viewBlurProxy.getView();
                    OplusVolumeSeekBar oplusVolumeSeekBar = view instanceof OplusVolumeSeekBar ? (OplusVolumeSeekBar) view : null;
                    volumeBlurConfigWriter.writeProgressPlatform(blurConfig, context, volumeCornerSpecCornerFromConfig, f, iWidth, iHeight, oplusVolumeSeekBar != null ? oplusVolumeSeekBar.getPlatformBlurStrokeWidthScale() : 1.0f);
                    INSTANCE.getClass();
                    applyPlatformBlurMixAfterConfigWrite(viewBlurProxy, z);
                }
            }
        }
    }

    public static final int getVolumeBarBlurStrengthMaxPx(Context context) {
        return GlobalActionsUtils.dp2px(context, 120.0f);
    }

    public static final int getVolumeBarBlurStrengthMinPx(Context context) {
        return GlobalActionsUtils.dp2px(context, 20.0f);
    }

    public static final float getVolumeBarPanelBottomAmount() {
        return barPanelBottomAmount;
    }

    public static final AutoBlurDrawable getVolumeBarProgressDrawable(Context context, View view, float f, Float f2) {
        if (!isVolumePlatformBlurEnabled(context)) {
            return null;
        }
        Drawable drawableCreateContent = VolumeSurfaceFactory.INSTANCE.createContent(new VolumeSurfaceFactory.CreateRequest(VolumeSurfaceKind.BAR_PROGRESS, VolumeMaterialCapability.Companion.resolve(context, true), context, view, (View) null, new VolumeCornerSpec(f, f2), barPanelBottomAmount, 16));
        if (drawableCreateContent instanceof AutoBlurDrawable) {
            return (AutoBlurDrawable) drawableCreateContent;
        }
        return null;
    }

    public static final boolean hasPlatformBlurScreenshot(Drawable drawable) {
        ViewBlurProxy viewBlurProxy;
        AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(drawable);
        return ((autoBlurDrawableFindPlatformAutoBlurDrawable == null || (viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy()) == null) ? null : viewBlurProxy.getCachePlatformStaticBlurDrawable()) != null;
    }

    public static void hotUpdateStaticPlatformLightAndStroke(PlatformBlurDrawable platformBlurDrawable, BlurConfig blurConfig) {
        try {
            BlurDrawable blurDrawable = platformBlurDrawable.getBlurDrawable();
            blurDrawable.setInnerShadowParams(blurConfig.getInnerShadowParams());
            blurDrawable.setOpticsParams(blurConfig.getOpticsParams());
            blurDrawable.setGradientStrokeLineParams(blurConfig.getGradientStrokeLineParam());
            if (blurConfig.getGradientStrokeLineParam() != null) {
                blurDrawable.setCornerParams(blurConfig.getGradientStrokeCornerParam());
                return;
            }
            Float radiusWeight = blurConfig.getRadiusWeight();
            if (radiusWeight != null) {
                blurDrawable.setSmoothCorner(blurConfig.cornerRadiusFloatArray(), radiusWeight.floatValue(), true);
            } else if (blurConfig.getEnableStaticBlurCorner()) {
                blurDrawable.setCornerRadius(blurConfig.cornerRadiusFloatArray());
            }
        } catch (Throwable th) {
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "hotUpdateStaticPlatformLightAndStroke failed: " + th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void invalidateVolumePlatformBlur(View view) {
        AutoBlurDrawable autoBlurDrawable;
        ViewBlurProxy viewBlurProxy;
        if (view != 0 && isVolumePlatformBlurEnabled(view.getContext())) {
            AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground());
            if (autoBlurDrawableFindPlatformAutoBlurDrawable != null && (viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy()) != null) {
                viewBlurProxy.invalidatePath();
                viewBlurProxy.applyBlurConfig();
                PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
                if (cachePlatformStaticBlurDrawable != null) {
                    cachePlatformStaticBlurDrawable.invalidateSelf();
                }
            }
            OplusVolumeSeekBar oplusVolumeSeekBar = view instanceof OplusVolumeSeekBar ? (OplusVolumeSeekBar) view : null;
            if (oplusVolumeSeekBar != null && (autoBlurDrawable = oplusVolumeSeekBar.mProgressBlurDrawable) != null) {
                PlatformBlurDrawable cachePlatformStaticBlurDrawable2 = autoBlurDrawable.getViewBlurProxy().getCachePlatformStaticBlurDrawable();
                AutoBlurDrawable autoBlurDrawable2 = oplusVolumeSeekBar.mProgressBlurDrawable;
                if (cachePlatformStaticBlurDrawable2 != null) {
                    autoBlurDrawable2.getViewBlurProxy().getCachePlatformStaticBlurDrawable().invalidateSelf();
                } else {
                    autoBlurDrawable2.getViewBlurProxy().invalidatePath();
                    oplusVolumeSeekBar.mProgressBlurDrawable.getViewBlurProxy().applyBlurConfig();
                }
            }
            view.invalidate();
        }
    }

    public static final boolean isVolumeMotionPlatformBlur(Context context) {
        return VolumeMaterialCapability.Companion.resolveMotionPlatform(context);
    }

    public static final boolean isVolumePlatformBlurEnabled(Context context) {
        return VolumeMaterialCapability.Companion.resolvePlatformBlurEnabled(context);
    }

    public static final float mapBarBlurRadiusToAmount(int i, Context context) {
        int volumeBarBlurStrengthMaxPx = getVolumeBarBlurStrengthMaxPx(context);
        if (volumeBarBlurStrengthMaxPx <= 0) {
            return 1.0f;
        }
        return RangesKt___RangesKt.coerceIn(i / volumeBarBlurStrengthMaxPx, 0.0f, 1.0f);
    }

    public static PlatformBlurManager platformBlurManagerOrNull() {
        try {
            return (PlatformBlurManager) DependencyEx.sDependency.getDependency(PlatformBlurManager.class);
        } catch (Throwable th) {
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "PlatformBlurManager unavailable: " + th);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.widget.AbsSeekBar, com.oplus.systemui.volume.OplusVolumeSeekBar] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public static final void refreshVolumePlatformMixColors(View view, Iterable<? extends View> iterable) {
        AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable;
        ?? r1;
        OplusVolumeSeekBar oplusVolumeSeekBar;
        float f = barPanelBottomAmount;
        for (View view2 : iterable) {
            applyVolumeBarPanelBottomAmount(view2, f, true);
            if (view2 instanceof OplusVolumeSeekBar) {
                oplusVolumeSeekBar = (OplusVolumeSeekBar) view2;
            } else {
                r1 = 0;
            }
            if (r1 != 0) {
                r1 = oplusVolumeSeekBar;
                applyVolumeBarProgressMixColors(r1.mProgressBlurDrawable, r1.mContext, true);
                r1.invalidate();
            } else {
                r1 = oplusVolumeSeekBar;
            }
        }
        if (view == null || !isVolumePlatformBlurEnabled(view.getContext()) || (autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground())) == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy();
        if (Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            float blurAmount = viewBlurProxy.getBlurAmount();
            VolumeBlurConfigWriter volumeBlurConfigWriter = VolumeBlurConfigWriter.INSTANCE;
            volumeBlurConfigWriter.writePanelPlatform(viewBlurProxy.getBlurConfig(), view.getContext(), volumeBlurConfigWriter.cornerFromConfig(viewBlurProxy.getBlurConfig()), isVolumeMotionPlatformBlur(view.getContext()));
            viewBlurProxy.setBlurAmount(blurAmount);
            viewBlurProxy.applyBlurConfig();
            PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
            if (cachePlatformStaticBlurDrawable != null) {
                cachePlatformStaticBlurDrawable.invalidateSelf();
            }
            view.invalidate();
        }
    }

    public static final void releaseVolumeBarProgressBlur(AutoBlurDrawable autoBlurDrawable) {
        if (autoBlurDrawable == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
        View view = viewBlurProxy.getView();
        releaseVolumeBarProgressBlur.1 r1 = new releaseVolumeBarProgressBlur.1(0);
        r1.$proxy = viewBlurProxy;
        VarHandle.storeStoreFence();
        view.post(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void releaseVolumePlatformAutoBlur(View view) {
        if (view == 0) {
            return;
        }
        if (view instanceof OplusVolumeSeekBar) {
            ((OplusVolumeSeekBar) view).releaseProgressPlatformBlur();
        }
        AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground());
        if (autoBlurDrawableFindPlatformAutoBlurDrawable == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy();
        view.setBackground(null);
        releaseVolumeBarProgressBlur.1 r1 = new releaseVolumeBarProgressBlur.1(1);
        r1.$proxy = viewBlurProxy;
        VarHandle.storeStoreFence();
        view.post(r1);
    }

    public static final void setVolumeBarPanelBottomAmount(float f) {
        barPanelBottomAmount = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
    }

    public static final void setVolumePlatformBlurActive(boolean z) {
        INSTANCE.getClass();
        PlatformBlurManager platformBlurManagerPlatformBlurManagerOrNull = platformBlurManagerOrNull();
        if (platformBlurManagerPlatformBlurManagerOrNull != null) {
            platformBlurManagerPlatformBlurManagerOrNull.setVolumeBlurActive(z);
        }
    }

    public static final void setVolumeSideIdleBlur(boolean z) {
        INSTANCE.getClass();
        PlatformBlurManager platformBlurManagerPlatformBlurManagerOrNull = platformBlurManagerOrNull();
        if (platformBlurManagerPlatformBlurManagerOrNull != null) {
            platformBlurManagerPlatformBlurManagerOrNull.setVolumeSideIdleBlur(z);
        }
    }

    public static final void setVolumeSliderBlurWanted(boolean z) {
        volumeSliderBlurWanted = z;
    }

    public static final void syncSideAccessoryElevationAndOutline(View view, boolean z, float f) {
        if (!z || f <= 0.0f) {
            return;
        }
        view.setOutlineProvider(new syncSideAccessoryElevationAndOutline.1(f));
        view.setClipToOutline(false);
        view.invalidateOutline();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void syncVolumeBarPlatformLightBounds(View view, int i, int i2, float f, Float f2) {
        AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable;
        if (view == 0 || !isVolumePlatformBlurEnabled(view.getContext()) || (autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground())) == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawableFindPlatformAutoBlurDrawable.getViewBlurProxy();
        if (Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            VolumeBlurConfigWriter volumeBlurConfigWriter = VolumeBlurConfigWriter.INSTANCE;
            BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
            Context context = view.getContext();
            VolumeCornerSpec volumeCornerSpec = new VolumeCornerSpec(f, f2);
            float f3 = barPanelBottomAmount;
            OplusVolumeSeekBar oplusVolumeSeekBar = view instanceof OplusVolumeSeekBar ? (OplusVolumeSeekBar) view : null;
            volumeBlurConfigWriter.writeBarPlatform(blurConfig, context, volumeCornerSpec, f3, i, i2, oplusVolumeSeekBar != null ? oplusVolumeSeekBar.getPlatformBlurStrokeWidthScale() : 1.0f);
            PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
            if (cachePlatformStaticBlurDrawable == null) {
                viewBlurProxy.applyBlurConfig();
                return;
            }
            INSTANCE.getClass();
            if (barPanelBottomAmount > 0.0f) {
                BlurMixConfig platformMixConfig = viewBlurProxy.getBlurConfig().getPlatformMixConfig();
                PlatformBlurDrawable.Companion companion = PlatformBlurDrawable.Companion;
                cachePlatformStaticBlurDrawable.changeBlurColor(platformMixConfig, false);
            }
            hotUpdateStaticPlatformLightAndStroke(cachePlatformStaticBlurDrawable, viewBlurProxy.getBlurConfig());
            cachePlatformStaticBlurDrawable.invalidateSelf();
        }
    }

    public static final void syncVolumeBarProgressCorner(AutoBlurDrawable autoBlurDrawable, float f, Float f2) {
        if (autoBlurDrawable == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
        Rect bounds = autoBlurDrawable.getBounds();
        int iWidth = bounds.width() > 0 ? bounds.width() : viewBlurProxy.getView().getWidth();
        int iHeight = bounds.height() > 0 ? bounds.height() : viewBlurProxy.getView().getHeight();
        VolumeBlurConfigWriter.INSTANCE.writeCorner(viewBlurProxy.getBlurConfig(), new VolumeCornerSpec(f, f2));
        if (viewBlurProxy.getBlurConfig().getGradientStrokeLineParam() != null) {
            VolumeBlurLightParams.INSTANCE.adaptVolumeStrokeCorner(viewBlurProxy.getBlurConfig(), f, f2, iWidth, iHeight, false);
        }
        PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
        if (cachePlatformStaticBlurDrawable == null) {
            viewBlurProxy.applyBlurConfig();
            return;
        }
        VolumeBlurManager volumeBlurManager = INSTANCE;
        BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
        volumeBlurManager.getClass();
        hotUpdateStaticPlatformLightAndStroke(cachePlatformStaticBlurDrawable, blurConfig);
        cachePlatformStaticBlurDrawable.invalidateSelf();
    }

    public static void updatePlatformStrokeWidthScale(AutoBlurDrawable autoBlurDrawable, Context context, float f, boolean z) {
        if (autoBlurDrawable == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
        if (Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            if (z) {
                VolumeBlurLightParams.INSTANCE.applyProgressStrokeWidthScale(viewBlurProxy.getBlurConfig(), context, barPanelBottomAmount, f, true);
            } else {
                VolumeBlurLightParams.INSTANCE.applyBarStrokeWidthScale(viewBlurProxy.getBlurConfig(), context, barPanelBottomAmount, f, true);
            }
            PlatformBlurDrawable cachePlatformStaticBlurDrawable = viewBlurProxy.getCachePlatformStaticBlurDrawable();
            if (cachePlatformStaticBlurDrawable == null) {
                return;
            }
            try {
                cachePlatformStaticBlurDrawable.getBlurDrawable().setGradientStrokeLineParams(viewBlurProxy.getBlurConfig().getGradientStrokeLineParam());
            } catch (Throwable th) {
                LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "hotUpdateStaticPlatformStrokeWidth failed: " + th);
            }
            cachePlatformStaticBlurDrawable.invalidateSelf();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void updateVolumeBarPlatformStrokeWidthScale(OplusVolumeSeekBar oplusVolumeSeekBar, float f) {
        if (isVolumePlatformBlurEnabled(oplusVolumeSeekBar.getContext())) {
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.01f, 1.0f);
            VolumeBlurManager volumeBlurManager = INSTANCE;
            AutoBlurDrawable autoBlurDrawableFindPlatformAutoBlurDrawable = OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(oplusVolumeSeekBar.getBackground());
            Context context = oplusVolumeSeekBar.getContext();
            volumeBlurManager.getClass();
            updatePlatformStrokeWidthScale(autoBlurDrawableFindPlatformAutoBlurDrawable, context, fCoerceIn, false);
            updatePlatformStrokeWidthScale(oplusVolumeSeekBar.mProgressBlurDrawable, oplusVolumeSeekBar.getContext(), fCoerceIn, true);
        }
    }

    public static final void updateVolumePlatformBlurCapture(View view, boolean z) {
        SurfaceControl surfaceControl;
        if (view != null && isVolumePlatformBlurEnabled(view.getContext()) && view.isAttachedToWindow()) {
            if (z) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (jUptimeMillis - lastSideBarCaptureUptimeMs < 16) {
                    return;
                } else {
                    lastSideBarCaptureUptimeMs = jUptimeMillis;
                }
            }
            ViewRootImpl viewRootImpl = view.getViewRootImpl();
            if (viewRootImpl == null || (surfaceControl = viewRootImpl.getSurfaceControl()) == null || !surfaceControl.isValid()) {
                return;
            }
            try {
                Blur.updateCapture(view.getContext().getApplicationContext(), surfaceControl);
            } catch (Throwable th) {
                LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "updateVolumePlatformBlurCapture failed: " + th);
            }
        }
    }

    public static final void warmPlatformBlurConfigOnly(AutoBlurDrawable autoBlurDrawable) {
        if (autoBlurDrawable == null) {
            return;
        }
        ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
        ViewBlurProxy.BlurType blurType = viewBlurProxy.getBlurType();
        ViewBlurProxy.BlurType.BlurTypePlatformStatic blurTypePlatformStatic = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
        if (!Intrinsics.areEqual(blurType, blurTypePlatformStatic)) {
            viewBlurProxy.setBlurType(blurTypePlatformStatic);
        }
        if (viewBlurProxy.getCachePlatformStaticBlurDrawable() != null) {
            return;
        }
        viewBlurProxy.applyBlurConfig();
    }

    public final Drawable getVolumeBarBackground(Context context, View view, View view2, float f, boolean z) {
        return VolumeSurfaceFactory.INSTANCE.createBackground(new VolumeSurfaceFactory.CreateRequest(VolumeSurfaceKind.BAR_BACKGROUND, VolumeMaterialCapability.Companion.resolve(context, z), context, view2, view, VolumeCornerSpec.Companion.bar(context, f, view2), barPanelBottomAmount));
    }

    public final Drawable getVolumePanelBackground(Context context, boolean z, View view, float f) {
        VolumeMaterialCapability volumeMaterialCapabilityResolve = VolumeMaterialCapability.Companion.resolve(context, z);
        if (volumeMaterialCapabilityResolve.getUsesSolidPaint()) {
            Drawable drawable = context.getDrawable(z ? 2131103131 : 2131237463);
            drawable.getClass();
            return drawable;
        }
        MixColor mixColor = new MixColor(4, context.getColor(2131103137), context.getColor(2131103136));
        boolean usesPlatformPaint = volumeMaterialCapabilityResolve.getUsesPlatformPaint();
        ViewBlurProxy.BlurType blurType = usesPlatformPaint ? ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE : ViewBlurProxy.BlurType.BlurTypeMotion.INSTANCE;
        VolumePlatformBlurParams.INSTANCE.getClass();
        BlurConfig blurConfig = new BlurConfig(250, usesPlatformPaint ? 0 : context.getColor(2131103134), null, null, null, null, true, null, null, 0, null, 33521660);
        VolumeCornerSpec volumeCornerSpecPanel = VolumeCornerSpec.Companion.panel(f);
        if (usesPlatformPaint) {
            VolumeBlurConfigWriter.INSTANCE.writePanelPlatform(blurConfig, context, volumeCornerSpecPanel, volumeMaterialCapabilityResolve.getMotionPlatform());
        } else {
            VolumeBlurConfigWriter.INSTANCE.writeCorner(blurConfig, volumeCornerSpecPanel);
            blurConfig.setMotionBlurMixConfig(new BlurMixConfig.BlurMixSingle(mixColor));
        }
        ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, blurConfig, null, 28);
        viewBlurProxy.setBlurType(blurType);
        viewBlurProxy.setBlurAmount(0.0f);
        return new AutoBlurDrawable(viewBlurProxy, usesPlatformPaint ? new MaskDrawable(blurConfig) : context.getDrawable(2131103135));
    }

    public final Drawable getVolumeSettingsButtonBackground(Context context, View view, View view2, boolean z) {
        return VolumeSurfaceFactory.INSTANCE.createBackground(new VolumeSurfaceFactory.CreateRequest(VolumeSurfaceKind.SETTINGS_BUTTON, VolumeMaterialCapability.Companion.resolve(context, z), context, view2, view, VolumeCornerSpec.Companion.settingsButton(context), 0.0f, 64));
    }

    public final Drawable getVolumeSideAccessoryBackground(Context context, View view, View view2, boolean z) {
        return VolumeSurfaceFactory.INSTANCE.createBackground(new VolumeSurfaceFactory.CreateRequest(VolumeSurfaceKind.SIDE_ACCESSORY, VolumeMaterialCapability.Companion.resolve(context, z), context, view2, view, VolumeCornerSpec.Companion.sideAccessory(context), barPanelBottomAmount));
    }

    public final boolean isGaussianBlurDisable(Context context) {
        return VolumeMaterialCapability.Companion.resolveBlurDisabled(context);
    }

    public final void setOutlineRect(Outline outline, Rect rect, float f, float f2) {
        OplusVolumeShapePathHelper.applyToOutline(outline, rect, f, f2, f2 > 0.0f);
    }

    public static final void warmPlatformBlurConfigOnly(View view) {
        warmPlatformBlurConfigOnly(OplusVolumeBlurDrawableFinder.findPlatformAutoBlurDrawable(view.getBackground()));
    }

    public static final AutoBlurDrawable getVolumeBarProgressDrawable(Context context, View view, float f) {
        return getVolumeBarProgressDrawable(context, view, f, null);
    }

    public static final void applyVolumeBarProgressMixColors(AutoBlurDrawable autoBlurDrawable, Context context) {
        applyVolumeBarProgressMixColors(autoBlurDrawable, context, false);
    }

    public static final void applyVolumeBarPanelBottomAmount(View view, float f) {
        applyVolumeBarPanelBottomAmount(view, f, false);
    }
}
