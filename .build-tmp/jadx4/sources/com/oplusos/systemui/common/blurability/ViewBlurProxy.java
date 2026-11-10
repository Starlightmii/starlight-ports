package com.oplusos.systemui.common.blurability;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import android.view.View;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.slice.widget.RowView$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.internal.graphics.drawable.BackgroundBlurDrawableAddon;
import com.android.internal.graphics.drawable.IBackgroundBlurDrawableExt;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.oplus.graphics.OplusBlurParam;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.view.OplusSmoothRoundedManager;
import com.oplusos.systemui.common.blurability.drawable.MaskDrawable;
import com.oplusos.systemui.common.blurability.motion.BlurUtils;
import com.oplusos.systemui.common.blurability.motion.MotionBlurHelper;
import com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider;
import com.oplusos.systemui.common.blurability.platformblur.MultiPlatformBlurHelper;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurHelper;
import com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager;
import com.oplusos.systemui.common.blurability.staticblur.StaticBlurDrawable;
import com.oplusos.systemui.common.blurability.wallpaper.BlendWallpaperBlurDrawable;
import com.oplusos.systemui.common.blurability.wallpaper.BlendWallpaperBlurHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ViewBlurProxy {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ViewBlurProxy";
    private final Lazy blendWallpaperBlurHelper$delegate;
    public float blurAmount;
    private final BlurConfig blurConfig;
    private final BlurConfigsProvider blurConfigsProvider;
    public boolean blurEnabled;
    private BlurType blurType;
    private String contrastBlurAmount;
    private Bitmap currentWallpaperBlurBitmap;
    private final Function1 excludeRules;
    private final Lazy fullWindowBlurHelper$delegate;
    public int lastRadius;
    private final Lazy maskDrawable$delegate;
    private final Lazy motionBlurHelper$delegate;
    private final Lazy multiPlatformBlurHelper$delegate;
    private Drawable.Callback myCallback;
    public boolean needSyncWindowBlur;
    private final Lazy platformBlurHelper$delegate;
    private final Lazy staticBlurDrawable$delegate;
    private StaticBlurManager staticBlurManager;
    private final View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class BlurType {

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypeBlendWallpaper extends BlurType {
            public static final BlurTypeBlendWallpaper INSTANCE = new BlurTypeBlendWallpaper();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypeBlendWallpaper);
            }

            public final int hashCode() {
                return 243779202;
            }

            public String toString() {
                return "BlurTypeBlendWallpaper";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypeMotion extends BlurType {
            public static final BlurTypeMotion INSTANCE = new BlurTypeMotion();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypeMotion);
            }

            public final int hashCode() {
                return -250048889;
            }

            public String toString() {
                return "BlurTypeMotion";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypeNon extends BlurType {
            public static final BlurTypeNon INSTANCE = new BlurTypeNon();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypeNon);
            }

            public final int hashCode() {
                return -1046104676;
            }

            public String toString() {
                return "BlurTypeNon";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypePlatformStatic extends BlurType {
            public static final BlurTypePlatformStatic INSTANCE = new BlurTypePlatformStatic();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypePlatformStatic);
            }

            public final int hashCode() {
                return -2035994990;
            }

            public String toString() {
                return "BlurTypePlatformStatic";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypeStatic extends BlurType {
            public static final BlurTypeStatic INSTANCE = new BlurTypeStatic();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypeStatic);
            }

            public final int hashCode() {
                return -74212033;
            }

            public String toString() {
                return "BlurTypeStatic";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class BlurTypeWindowBg extends BlurType {
            public static final BlurTypeWindowBg INSTANCE = new BlurTypeWindowBg();

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof BlurTypeWindowBg);
            }

            public final int hashCode() {
                return -737016090;
            }

            public String toString() {
                return "BlurTypeWindowBg";
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static PlatformBlurHelper $r8$lambda$6wVV7UJblBPmpqjgk8hFyJ6Bfcc(ViewBlurProxy viewBlurProxy) {
        PlatformBlurHelper platformBlurHelper = new PlatformBlurHelper(viewBlurProxy.view, viewBlurProxy.excludeRules, viewBlurProxy.blurConfig);
        ViewBlurProxy$$ExternalSyntheticLambda7 viewBlurProxy$$ExternalSyntheticLambda7 = new ViewBlurProxy$$ExternalSyntheticLambda7(1);
        viewBlurProxy$$ExternalSyntheticLambda7.f$0 = viewBlurProxy;
        VarHandle.storeStoreFence();
        platformBlurHelper.setOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(viewBlurProxy$$ExternalSyntheticLambda7);
        return platformBlurHelper;
    }

    /* JADX INFO: renamed from: $r8$lambda$8-t_XuFii7VSfJ9jUUqbRHiRid0, reason: not valid java name */
    public static Unit m607$r8$lambda$8t_XuFii7VSfJ9jUUqbRHiRid0(ViewBlurProxy viewBlurProxy, PlatformBlurDrawable platformBlurDrawable) {
        Function1 onBlurDrawableCreated = viewBlurProxy.blurConfig.getOnBlurDrawableCreated();
        if (onBlurDrawableCreated != null) {
            onBlurDrawableCreated.invoke(platformBlurDrawable.getBlurDrawable());
        }
        platformBlurDrawable.setCallback(viewBlurProxy.myCallback);
        viewBlurProxy.applyConfigToPlatformBlur();
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$CVSfGTb0Z8Id5x6lk4e0U1OeOGM(ViewBlurProxy viewBlurProxy, BackgroundBlurDrawable backgroundBlurDrawable) {
        backgroundBlurDrawable.setCallback(viewBlurProxy.myCallback);
        viewBlurProxy.applyConfigToMotionBlur();
        return Unit.INSTANCE;
    }

    public static BlurUtils $r8$lambda$V2fSM12CWa7HbEDhn0dh7wlIRKk(ViewBlurProxy viewBlurProxy) {
        WindowBlurConfig windowBlurConfig = viewBlurProxy.blurConfig.getWindowBlurConfig();
        int minWindowBlurRadius = windowBlurConfig != null ? windowBlurConfig.getMinWindowBlurRadius() : 0;
        WindowBlurConfig windowBlurConfig2 = viewBlurProxy.blurConfig.getWindowBlurConfig();
        return new BlurUtils(minWindowBlurRadius, windowBlurConfig2 != null ? windowBlurConfig2.getMaxWindowBlurRadius() : 0);
    }

    public static StaticBlurDrawable $r8$lambda$_AxEXM7NntCwx0iZ2tmbcBTkMWw(ViewBlurProxy viewBlurProxy) {
        boolean z = viewBlurProxy.blurConfig.isFullScreen;
        Float fValueOf = Float.valueOf(1.0f);
        StaticBlurDrawable staticBlurDrawable = new StaticBlurDrawable(null, z, new Pair(fValueOf, fValueOf));
        StaticBlurManager staticBlurManager = viewBlurProxy.staticBlurManager;
        if (staticBlurManager != null) {
            staticBlurManager.registerStaticBlurDrawable(staticBlurDrawable);
            staticBlurDrawable.setBitmapAndScale(staticBlurManager.getScreenBlurredBitmap(), staticBlurManager.getScalePair());
        }
        return staticBlurDrawable;
    }

    /* JADX INFO: renamed from: $r8$lambda$dTGcCI7seGr2Sb273qOT4-HVWXM, reason: not valid java name */
    public static MultiPlatformBlurHelper m608$r8$lambda$dTGcCI7seGr2Sb273qOT4HVWXM(ViewBlurProxy viewBlurProxy) {
        return new MultiPlatformBlurHelper(viewBlurProxy.view, viewBlurProxy.excludeRules, viewBlurProxy.blurConfigsProvider);
    }

    public static BlendWallpaperBlurHelper $r8$lambda$eXwDOtkpNvJGGeFMM90Ms7SmwJ0(ViewBlurProxy viewBlurProxy) {
        BlendWallpaperBlurHelper blendWallpaperBlurHelper = new BlendWallpaperBlurHelper(viewBlurProxy.view, viewBlurProxy.excludeRules);
        ViewBlurProxy$$ExternalSyntheticLambda7 viewBlurProxy$$ExternalSyntheticLambda7 = new ViewBlurProxy$$ExternalSyntheticLambda7(3);
        viewBlurProxy$$ExternalSyntheticLambda7.f$0 = viewBlurProxy;
        VarHandle.storeStoreFence();
        blendWallpaperBlurHelper.setOnBlendDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(viewBlurProxy$$ExternalSyntheticLambda7);
        return blendWallpaperBlurHelper;
    }

    public static Unit $r8$lambda$f4VnhX1oSYzlYSd2QzggJs25mNg(ViewBlurProxy viewBlurProxy, BlendWallpaperBlurDrawable blendWallpaperBlurDrawable) {
        blendWallpaperBlurDrawable.setCallback(viewBlurProxy.myCallback);
        viewBlurProxy.applyConfigToBlendWallpaper();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$l47_3qDJg3yT8evsa-r36L2aqgY, reason: not valid java name */
    public static MaskDrawable m609$r8$lambda$l47_3qDJg3yT8evsar36L2aqgY(ViewBlurProxy viewBlurProxy) {
        return new MaskDrawable(viewBlurProxy.blurConfig);
    }

    /* JADX INFO: renamed from: $r8$lambda$oMEzl8MDFQgSlMcuH-0vCXOHIQU, reason: not valid java name */
    public static MotionBlurHelper m610$r8$lambda$oMEzl8MDFQgSlMcuH0vCXOHIQU(ViewBlurProxy viewBlurProxy) {
        MotionBlurHelper motionBlurHelper = new MotionBlurHelper(viewBlurProxy.view, viewBlurProxy.blurConfig);
        ViewBlurProxy$$ExternalSyntheticLambda7 viewBlurProxy$$ExternalSyntheticLambda7 = new ViewBlurProxy$$ExternalSyntheticLambda7(2);
        viewBlurProxy$$ExternalSyntheticLambda7.f$0 = viewBlurProxy;
        VarHandle.storeStoreFence();
        motionBlurHelper.setOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(viewBlurProxy$$ExternalSyntheticLambda7);
        return motionBlurHelper;
    }

    public ViewBlurProxy(View view, BlurConfig blurConfig, StaticBlurManager staticBlurManager, Function1 function1, BlurConfigsProvider blurConfigsProvider) {
        this.view = view;
        this.blurConfig = blurConfig;
        this.staticBlurManager = staticBlurManager;
        this.excludeRules = function1;
        this.blurConfigsProvider = blurConfigsProvider;
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda0 = new ViewBlurProxy$$ExternalSyntheticLambda0(0);
        viewBlurProxy$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.staticBlurDrawable$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda0);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda1 = new ViewBlurProxy$$ExternalSyntheticLambda0(1);
        viewBlurProxy$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.motionBlurHelper$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda1);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda2 = new ViewBlurProxy$$ExternalSyntheticLambda0(2);
        viewBlurProxy$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.platformBlurHelper$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda2);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda3 = new ViewBlurProxy$$ExternalSyntheticLambda0(3);
        viewBlurProxy$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.blendWallpaperBlurHelper$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda3);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda4 = new ViewBlurProxy$$ExternalSyntheticLambda0(4);
        viewBlurProxy$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.multiPlatformBlurHelper$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda4);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda5 = new ViewBlurProxy$$ExternalSyntheticLambda0(5);
        viewBlurProxy$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.fullWindowBlurHelper$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda5);
        ViewBlurProxy$$ExternalSyntheticLambda0 viewBlurProxy$$ExternalSyntheticLambda6 = new ViewBlurProxy$$ExternalSyntheticLambda0(6);
        viewBlurProxy$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.maskDrawable$delegate = LazyKt__LazyJVMKt.lazy(viewBlurProxy$$ExternalSyntheticLambda6);
        this.blurEnabled = true;
        this.blurAmount = 1.0f;
        this.blurType = BlurType.BlurTypeNon.INSTANCE;
        this.contrastBlurAmount = "";
        setMyCallback$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(view);
    }

    public static OplusBlurParam buildMotionBlurParam(MixColor mixColor, Float f) {
        OplusBlurParam oplusBlurParam = new OplusBlurParam();
        if (mixColor != null) {
            oplusBlurParam.setMaterialParams(mixColor.getMode(), colorToFloatArray(mixColor.getBottomLayerColor()), colorToFloatArray(mixColor.getTopLayerColor()));
        }
        oplusBlurParam.setBlurType(2);
        if (f != null && f.floatValue() > 0.0f) {
            oplusBlurParam.setSmoothCornerWeight(f.floatValue());
        }
        oplusBlurParam.setSmoothCornerType(OplusSmoothRoundedManager.getG2CornerType());
        return oplusBlurParam;
    }

    public static float[] colorToFloatArray(int i) {
        return new float[]{Color.red(i) / 255.0f, Color.green(i) / 255.0f, Color.blue(i) / 255.0f, Color.alpha(i) / 255.0f};
    }

    private final BlurUtils getFullWindowBlurHelper() {
        return (BlurUtils) this.fullWindowBlurHelper$delegate.getValue();
    }

    private final MaskDrawable getMaskDrawable() {
        return (MaskDrawable) this.maskDrawable$delegate.getValue();
    }

    private final void setCallbackInner(Drawable.Callback callback) {
        getMultiPlatformBlurHelper().setCallback(null);
        getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setCallback(null);
        getMaskDrawable().setCallback(null);
        BackgroundBlurDrawable blurDrawable = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
        if (blurDrawable != null) {
            blurDrawable.setCallback((Drawable.Callback) null);
        }
        PlatformBlurDrawable blurDrawable2 = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
        if (blurDrawable2 != null) {
            blurDrawable2.setCallback(null);
        }
        BlurType blurType = this.blurType;
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeStatic.INSTANCE)) {
            getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setCallback(callback);
            return;
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeMotion.INSTANCE)) {
            BackgroundBlurDrawable blurDrawable3 = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
            if (blurDrawable3 != null) {
                blurDrawable3.setCallback(callback);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
            getMaskDrawable().setCallback(callback);
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
            PlatformBlurDrawable blurDrawable4 = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
            if (blurDrawable4 != null) {
                blurDrawable4.setCallback(callback);
            }
            getMultiPlatformBlurHelper().setCallback(callback);
        }
    }

    public static /* synthetic */ void updateMultiPlatformMixConfig$default(ViewBlurProxy viewBlurProxy, float f, int i) {
        if ((i & 1) != 0) {
            f = viewBlurProxy.blurConfig.getCornerRadius();
        }
        viewBlurProxy.updateMultiPlatformMixConfig(f, viewBlurProxy.blurConfig.getGradientStrokeLineParam(), viewBlurProxy.blurConfig.getInnerShadowParams());
    }

    public final void applyBlurConfig() {
        BlurType blurType = this.blurType;
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeStatic.INSTANCE)) {
            applyConfigToStaticBlur();
            return;
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeMotion.INSTANCE)) {
            applyConfigToMotionBlur();
            return;
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
            applyWindowBgBlur();
            this.view.invalidate();
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
            applyConfigToPlatformBlur();
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
            applyConfigToBlendWallpaper();
        } else {
            this.view.invalidate();
        }
    }

    public final void applyConfigToBlendWallpaper() {
        BlendWallpaperBlurDrawable blendDrawable = getBlendWallpaperBlurHelper().getBlendDrawable();
        if (blendDrawable == null || blendDrawable.applyBlurConfig(this.blurConfig, this.blurAmount, this.currentWallpaperBlurBitmap) == null) {
            getBlendWallpaperBlurHelper().ensureBlendDrawable();
        }
    }

    public final void applyConfigToMotionBlur() {
        BackgroundBlurDrawable blurDrawable = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
        if (blurDrawable != null) {
            int motionBlurRadius = this.blurConfig.getMotionBlurRadius();
            if (motionBlurRadius <= 0) {
                motionBlurRadius = this.blurConfig.getBlurRadius();
            }
            int i = 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sb = new StringBuilder("[applyConfigToMotionBlur] for ");
                sb.append(DebugToolKt.getName(this.view, false));
                sb.append(",blurAmount=");
                ChildHelper$.ExternalSyntheticOutline0.m(this.blurAmount, motionBlurRadius, ",configRadius=", ",enableMotionSmoothCorner=", sb);
                sb.append(this.blurConfig.getEnableMotionSmoothCorner());
                Log.d("SystemUi--Common", "ViewBlurProxy-->".concat(sb.toString()));
            }
            blurDrawable.setAlpha((int) (255.0f * this.blurAmount));
            blurDrawable.setBlurRadius((int) (motionBlurRadius * this.blurAmount));
            blurDrawable.setCornerRadius(this.blurConfig.getLeftTopCornerRadius(), this.blurConfig.getRightTopCornerRadius(), this.blurConfig.getLeftBottomCornerRadius(), this.blurConfig.getRightBottomCornerRadius());
            BlurMixConfig.BlurMixSingle motionBlurMixConfig = this.blurConfig.getMotionBlurMixConfig();
            MixColor mixColor = motionBlurMixConfig != null ? motionBlurMixConfig.getMixColor() : null;
            boolean enableMotionSmoothCorner = this.blurConfig.getEnableMotionSmoothCorner();
            BlurConfig blurConfig = this.blurConfig;
            if (enableMotionSmoothCorner) {
                blurDrawable.setColor(blurConfig.getMotionBlurMixConfig() == null ? this.blurConfig.getBlurColor() : 0);
                IBackgroundBlurDrawableExt extImpl = BackgroundBlurDrawableAddon.oplusWrapper(blurDrawable).getExtImpl();
                if (extImpl == null) {
                    LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[applyMotionSmoothCorner] extImpl is null");
                } else {
                    Float radiusWeight = this.blurConfig.getRadiusWeight();
                    extImpl.setBlurParams(buildMotionBlurParam(mixColor, null));
                    if (radiusWeight != null && radiusWeight.floatValue() > 0.0f) {
                        extImpl.setBlurParams(buildMotionBlurParam(mixColor, radiusWeight));
                    }
                }
            } else {
                BlurMixConfig.BlurMixSingle motionBlurMixConfig2 = blurConfig.getMotionBlurMixConfig();
                MixColor mixColor2 = motionBlurMixConfig2 != null ? motionBlurMixConfig2.getMixColor() : null;
                IBackgroundBlurDrawableExt extImpl2 = BackgroundBlurDrawableAddon.oplusWrapper(blurDrawable).getExtImpl();
                if (extImpl2 != null) {
                    OplusBlurParam oplusBlurParam = new OplusBlurParam();
                    if (mixColor2 != null) {
                        oplusBlurParam.setMaterialParams(mixColor2.getMode(), colorToFloatArray(mixColor2.getBottomLayerColor()), colorToFloatArray(mixColor2.getTopLayerColor()));
                    }
                    oplusBlurParam.setBlurType(2);
                    Float radiusWeight2 = this.blurConfig.getRadiusWeight();
                    if (radiusWeight2 != null) {
                        oplusBlurParam.setSmoothCornerWeight(radiusWeight2.floatValue());
                    }
                    extImpl2.setBlurParams(oplusBlurParam);
                }
                blurDrawable.setColor(motionBlurMixConfig2 == null ? this.blurConfig.getBlurColor() : 0);
            }
            if (this.blurConfig.getEnableMotionAmountWithAlphaToUser()) {
                MotionBlurHelper motionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
                ViewBlurProxy$$ExternalSyntheticLambda7 viewBlurProxy$$ExternalSyntheticLambda7 = new ViewBlurProxy$$ExternalSyntheticLambda7(i);
                viewBlurProxy$$ExternalSyntheticLambda7.f$0 = this;
                VarHandle.storeStoreFence();
                motionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon.setOnViewAlphaToUser$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(viewBlurProxy$$ExternalSyntheticLambda7);
            } else {
                getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setOnViewAlphaToUser$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(null);
                setBlurAmount(1.0f);
            }
            blurDrawable.invalidateSelf();
        }
    }

    public final void applyConfigToPlatformBlur() {
        String blurAmount = formatBlurAmount(this.blurAmount);
        if (!this.contrastBlurAmount.equals(blurAmount)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sb = new StringBuilder("[applyConfigToPlatformBlur] for ");
                sb.append(DebugToolKt.getName(this.view, true));
                sb.append(", blurAmount: ");
                sb.append(this.blurAmount);
                sb.append(", blurConfig: ");
                sb.append(this.blurConfig);
                sb.append(", drawableId: ");
                PlatformBlurDrawable blurDrawable = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
                sb.append(blurDrawable != null ? Integer.valueOf(blurDrawable.getDrawableId()) : null);
                Log.d("SystemUi--Common", "ViewBlurProxy-->".concat(sb.toString()));
            }
            this.contrastBlurAmount = blurAmount;
        }
        PlatformBlurDrawable blurDrawable2 = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
        if (blurDrawable2 == null || blurDrawable2.applyBlurConfig(this.blurConfig, this.blurAmount) == null) {
            getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().ensureBlurDrawable();
        }
    }

    public final void applyConfigToStaticBlur() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "ViewBlurProxy-->".concat("[applyConfigToStaticBlur] for " + DebugToolKt.getName(this.view, false)));
        }
        StaticBlurDrawable staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon.setAlpha((int) (255.0f * this.blurAmount));
        staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon.setBlurMaskColor(this.blurConfig.getBlurColor());
        if (this.blurConfig.getEnableStaticBlurCorner()) {
            staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon.setCornerRadius(this.blurConfig.getLeftTopCornerRadius(), this.blurConfig.getRightTopCornerRadius(), this.blurConfig.getLeftBottomCornerRadius(), this.blurConfig.getRightBottomCornerRadius());
        }
    }

    public final void applyWindowBgBlur() {
        if (this.blurEnabled) {
            BlurUtils fullWindowBlurHelper = getFullWindowBlurHelper();
            float f = this.blurAmount;
            float fLerp = 0.0f;
            if (f == 0.0f) {
                fullWindowBlurHelper.getClass();
            } else {
                fLerp = MathUtils.lerp(fullWindowBlurHelper.minBlurRadius, fullWindowBlurHelper.maxBlurRadius, f);
            }
            int i = (int) fLerp;
            WindowBlurConfig windowBlurConfig = this.blurConfig.getWindowBlurConfig();
            int mixedColor = windowBlurConfig != null ? windowBlurConfig.getMixedColor() : 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sb = new StringBuilder("[applyWindowBgBlur] for ");
                sb.append(DebugToolKt.getName(this.view, false));
                sb.append(", radius=");
                sb.append(i);
                sb.append("mixedColor=");
                sb.append(mixedColor);
                sb.append(", needSyncWindowBlur=");
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("ViewBlurProxy-->", "SystemUi--Common", sb, this.needSyncWindowBlur);
            }
            BlurUtils.applyBlur$default(getFullWindowBlurHelper(), this.view.getViewRootImpl(), i, mixedColor, this.needSyncWindowBlur, 0.0f, 64);
        }
    }

    public final String formatBlurAmount(float f) {
        double d = f;
        if (d >= 0.1d) {
            return String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        }
        return d >= 0.01d ? String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1)) : String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
    }

    public final BlendWallpaperBlurHelper getBlendWallpaperBlurHelper() {
        return (BlendWallpaperBlurHelper) this.blendWallpaperBlurHelper$delegate.getValue();
    }

    public final float getBlurAmount() {
        return this.blurAmount;
    }

    public final BlurConfig getBlurConfig() {
        return this.blurConfig;
    }

    public final Drawable getBlurDrawable(Drawable drawable) {
        if (this.blurEnabled) {
            BlurType blurType = this.blurType;
            if (Intrinsics.areEqual(blurType, BlurType.BlurTypeStatic.INSTANCE)) {
                if (getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().isValid()) {
                    drawable = getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
                }
            } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeMotion.INSTANCE)) {
                if (getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable() != null) {
                    drawable = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
                }
            } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
                drawable = getMaskDrawable();
            } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
                getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().ensureBlurDrawable();
                if (getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable() != null) {
                    drawable = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[getBlurDrawable] for " + this + " ensurePlatformStaticBlurDrawable failed");
                }
            } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
                getBlendWallpaperBlurHelper().ensureBlendDrawable();
                if (getBlendWallpaperBlurHelper().getBlendDrawable() != null) {
                    drawable = getBlendWallpaperBlurHelper().getBlendDrawable();
                } else {
                    LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[getBlurDrawable] for " + this + " ensureBlendWallpaperBlurDrawable failed");
                }
            }
        }
        if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "ViewBlurProxy-->".concat("[getBlurDrawable] " + drawable + " for " + this));
        }
        return drawable;
    }

    public final boolean getBlurEnabled() {
        return this.blurEnabled;
    }

    public final BlurType getBlurType() {
        return this.blurType;
    }

    public final PlatformBlurDrawable getCachePlatformStaticBlurDrawable() {
        return getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
    }

    public final Drawable getCacheStackBlurDrawable(int i) {
        return getMultiPlatformBlurHelper().getStackCacheBlurDrawable(i);
    }

    public final String getContrastBlurAmount() {
        return this.contrastBlurAmount;
    }

    public final Bitmap getCurrentWallpaperBlurBitmap() {
        return this.currentWallpaperBlurBitmap;
    }

    public final MotionBlurHelper getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return (MotionBlurHelper) this.motionBlurHelper$delegate.getValue();
    }

    public final MultiPlatformBlurHelper getMultiPlatformBlurHelper() {
        return (MultiPlatformBlurHelper) this.multiPlatformBlurHelper$delegate.getValue();
    }

    public final Drawable.Callback getMyCallback$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.myCallback;
    }

    public final boolean getNeedSyncWindowBlur() {
        return this.needSyncWindowBlur;
    }

    public final PlatformBlurHelper getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return (PlatformBlurHelper) this.platformBlurHelper$delegate.getValue();
    }

    public final Drawable getStackBlurDrawable(int i, Drawable drawable) {
        if (this.blurEnabled) {
            return Intrinsics.areEqual(this.blurType, BlurType.BlurTypePlatformStatic.INSTANCE) ? getMultiPlatformBlurHelper().getStackBlurDrawable(i, drawable) : getBlurDrawable(drawable);
        }
        return drawable;
    }

    public final StaticBlurDrawable getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return (StaticBlurDrawable) this.staticBlurDrawable$delegate.getValue();
    }

    public final View getView() {
        return this.view;
    }

    public final void invalidatePath() {
        PlatformBlurDrawable blurDrawable;
        BlurDrawable blurDrawable2;
        if (!Intrinsics.areEqual(this.blurType, BlurType.BlurTypePlatformStatic.INSTANCE) || (blurDrawable = getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable()) == null || (blurDrawable2 = blurDrawable.getBlurDrawable()) == null) {
            return;
        }
        blurDrawable2.updatePath(false);
    }

    public final void onVisibilityAggregated(boolean z) {
        getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().onVisibilityAggregated(z);
    }

    public final void setBlurAmount(float f) {
        LogBuffer logBuffer;
        if (this.blurAmount == f) {
            return;
        }
        boolean z = false;
        if (LogUtil.panicEnabled && (logBuffer = DebugTool.INSTANCE.getLogBuffer()) != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "[setBlurAmount] " + f + " for " + DebugToolKt.getName(this.view, false), (Throwable) null);
        }
        this.blurAmount = f;
        int iPlatformBlurRadiusForAmount = this.blurConfig.platformBlurRadiusForAmount(f);
        if (this.blurConfig.getScaleBlurRadiusWithAmount() && f != 0.0f && f != 1.0f && Math.abs(iPlatformBlurRadiusForAmount - this.lastRadius) < 1) {
            z = true;
        }
        this.lastRadius = iPlatformBlurRadiusForAmount;
        if (z) {
            return;
        }
        BlurType blurType = this.blurType;
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeMotion.INSTANCE)) {
            BackgroundBlurDrawable blurDrawable = getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable();
            if (blurDrawable != null && getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurEnabled()) {
                blurDrawable.setAlpha((int) (this.blurAmount * 255.0f));
                int motionBlurRadius = this.blurConfig.getMotionBlurRadius();
                if (motionBlurRadius <= 0) {
                    motionBlurRadius = this.blurConfig.getBlurRadius();
                }
                blurDrawable.setBlurRadius((int) (motionBlurRadius * this.blurAmount));
            }
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeStatic.INSTANCE)) {
            getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setAlpha((int) (this.blurAmount * 255.0f));
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
            applyWindowBgBlur();
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
            applyConfigToPlatformBlur();
        } else if (Intrinsics.areEqual(blurType, BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
            applyConfigToBlendWallpaper();
        }
        this.view.invalidate();
    }

    public final void setBlurEnabled(boolean z) {
        if (this.blurEnabled != z) {
            boolean z2 = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("[setBlurEnabled] ", " for ", z);
                sbM.append(DebugToolKt.getName(this.view, false));
                Log.d("SystemUi--Common", "ViewBlurProxy-->".concat(sbM.toString()));
            }
            this.blurEnabled = z;
            if (Intrinsics.areEqual(this.blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
                if (this.blurEnabled) {
                    applyWindowBgBlur();
                } else {
                    BlurUtils.applyBlur$default(getFullWindowBlurHelper(), this.view.getViewRootImpl(), 0, 0, this.needSyncWindowBlur, 0.0f, 76);
                }
            }
            getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled && Intrinsics.areEqual(this.blurType, BlurType.BlurTypeMotion.INSTANCE));
            getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled && Intrinsics.areEqual(this.blurType, BlurType.BlurTypePlatformStatic.INSTANCE));
            MultiPlatformBlurHelper multiPlatformBlurHelper = getMultiPlatformBlurHelper();
            if (this.blurEnabled && Intrinsics.areEqual(this.blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
                z2 = true;
            }
            multiPlatformBlurHelper.setBlurEnabled(z2);
            this.view.invalidate();
        }
    }

    public final void setBlurType(BlurType blurType) {
        LogBuffer logBuffer;
        if (Intrinsics.areEqual(this.blurType, blurType)) {
            return;
        }
        if (LogUtil.panicEnabled && (logBuffer = DebugTool.INSTANCE.getLogBuffer()) != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "[setBlurType] " + blurType + " for " + DebugToolKt.getName(this.view, false), (Throwable) null);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "ViewBlurProxy-->".concat("[setBlurType] " + blurType + " for " + DebugToolKt.getName(this.view, false)));
        }
        BlurType blurType2 = this.blurType;
        BlurType.BlurTypeWindowBg blurTypeWindowBg = BlurType.BlurTypeWindowBg.INSTANCE;
        if (Intrinsics.areEqual(blurType2, blurTypeWindowBg)) {
            BlurUtils.applyBlur$default(getFullWindowBlurHelper(), this.view.getViewRootImpl(), 0, 0, this.needSyncWindowBlur, 0.0f, 76);
        } else if (Intrinsics.areEqual(this.blurType, BlurType.BlurTypeMotion.INSTANCE)) {
            if (this.blurConfig.getEnableMotionAmountWithAlphaToUser()) {
                setBlurAmount(1.0f);
            }
            getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().release("blurType changed");
        }
        this.blurType = blurType;
        getMultiPlatformBlurHelper().setBlurEnabled(false);
        getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(false);
        getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(false);
        BlurType blurType3 = this.blurType;
        if (Intrinsics.areEqual(blurType3, BlurType.BlurTypeStatic.INSTANCE)) {
            applyConfigToStaticBlur();
        } else if (Intrinsics.areEqual(blurType3, BlurType.BlurTypeMotion.INSTANCE)) {
            getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled);
            applyConfigToMotionBlur();
        } else if (Intrinsics.areEqual(blurType3, blurTypeWindowBg)) {
            applyWindowBgBlur();
        } else if (Intrinsics.areEqual(blurType3, BlurType.BlurTypePlatformStatic.INSTANCE)) {
            getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled);
            getMultiPlatformBlurHelper().setBlurEnabled(this.blurEnabled);
            getMultiPlatformBlurHelper().applyBlurConfig();
            applyConfigToPlatformBlur();
        } else if (Intrinsics.areEqual(blurType3, BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
            getBlendWallpaperBlurHelper().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled);
            applyConfigToBlendWallpaper();
        }
        setCallbackInner(this.myCallback);
        this.view.invalidate();
    }

    public final void setContrastBlurAmount(String str) {
        this.contrastBlurAmount = str;
    }

    public final void setCurrentWallpaperBlurBitmap(Bitmap bitmap) {
        this.currentWallpaperBlurBitmap = bitmap;
    }

    public final void setMyCallback$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(Drawable.Callback callback) {
        if (Intrinsics.areEqual(this.myCallback, callback)) {
            return;
        }
        this.myCallback = callback;
        setCallbackInner(callback);
    }

    public final void setNeedSyncWindowBlur(boolean z) {
        this.needSyncWindowBlur = z;
    }

    public final void setStaticBlurManager(StaticBlurManager staticBlurManager) {
        if (Intrinsics.areEqual(this.staticBlurManager, staticBlurManager)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "ViewBlurProxy-->".concat("[setStaticBlurManager] " + staticBlurManager));
        }
        StaticBlurManager staticBlurManager2 = this.staticBlurManager;
        if (staticBlurManager2 != null) {
            staticBlurManager2.removeStaticBlurDrawable(getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon());
        }
        StaticBlurDrawable staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon = getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
        StaticBlurDrawable.Companion companion = StaticBlurDrawable.Companion;
        Float fValueOf = Float.valueOf(1.0f);
        staticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon.setBitmapAndScale(null, new Pair<>(fValueOf, fValueOf));
        if (staticBlurManager != null && staticBlurManager.registerStaticBlurDrawable(getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon())) {
            getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBitmapAndScale(staticBlurManager.getScreenBlurredBitmap(), staticBlurManager.getScalePair());
        }
        this.staticBlurManager = staticBlurManager;
    }

    public String toString() {
        return "ViewBlurProxy{view=" + DebugToolKt.getName(this.view, false) + ", blurEnabled=" + this.blurEnabled + ",blurType=" + this.blurType + ", blurAmount=" + this.blurAmount + ", staticBlurDrawable=" + getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() + "}, backgroundBlurDrawable=" + getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable() + ",platformBlurDrawable=" + getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable() + ", blurConfig=" + this.blurConfig + ", multiPlatformBlurHelper=" + getMultiPlatformBlurHelper();
    }

    public final void updateMultiPlatformMixConfig(float f, GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams) {
        getMultiPlatformBlurHelper().updateCardPlatformMixConfig(this.blurConfig.getBlurColor(), f, gradientStrokeLineParams, innerShadowParams);
    }

    public final boolean verifyDrawable(Drawable drawable) {
        BlurType blurType = this.blurType;
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeStatic.INSTANCE)) {
            return Intrinsics.areEqual(drawable, getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon());
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeMotion.INSTANCE)) {
            return Intrinsics.areEqual(drawable, getMotionBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable());
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeWindowBg.INSTANCE)) {
            return Intrinsics.areEqual(drawable, getMaskDrawable());
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypePlatformStatic.INSTANCE)) {
            return Intrinsics.areEqual(drawable, getPlatformBlurHelper$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().getBlurDrawable()) || getMultiPlatformBlurHelper().verifyDrawable(drawable);
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
            return Intrinsics.areEqual(drawable, getBlendWallpaperBlurHelper().getBlendDrawable());
        }
        if (Intrinsics.areEqual(blurType, BlurType.BlurTypeNon.INSTANCE)) {
            return false;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return false;
    }

    public /* synthetic */ ViewBlurProxy(View view, BlurConfig blurConfig, StaticBlurManager staticBlurManager, int i) {
        this(view, blurConfig, (i & 4) != 0 ? null : staticBlurManager, null, null);
    }
}
