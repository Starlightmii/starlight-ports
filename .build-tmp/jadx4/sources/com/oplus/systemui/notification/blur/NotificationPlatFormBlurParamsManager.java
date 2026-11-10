package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.util.Log;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColorWithShader;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotifiAndQsPlatformBlurExKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPlatFormBlurParamsManager {
    public static final NotificationPlatFormBlurParamsManager INSTANCE = new NotificationPlatFormBlurParamsManager();
    private static final String TAG = "NotificationPlatFormBlu";
    public static volatile float cachedBaseHeightPx;
    public static volatile float cachedBaseWidthPx;

    public static NotificationInnerShadowAdapter.NotificationInnerShadowGroup getCurrentNotificationInnerShadowGroup(NotificationBlurMode notificationBlurMode) {
        boolean light = notificationBlurMode.getLight();
        if (notificationBlurMode.getShade()) {
            return !light ? NotificationInnerShadowAdapter.NotificationInnerShadowDarkGroup.INSTANCE : NotificationInnerShadowAdapter.NotificationInnerShadowLightGroup.INSTANCE;
        }
        return isRegionA(notificationBlurMode) ? NotificationInnerShadowAdapter.KeyguardNotificationAInnerShadowGroup.INSTANCE : NotificationInnerShadowAdapter.KeyguardNotificationInnerShadowGroup.INSTANCE;
    }

    public static NotificationGradientStrokeLineAdapter.NotificationStrokeLineGroup getCurrentNotificationStrokeLineGroup(NotificationBlurMode notificationBlurMode) {
        boolean light = notificationBlurMode.getLight();
        if (notificationBlurMode.getShade()) {
            return !light ? NotificationGradientStrokeLineAdapter.NotificationStrokeLineDarkGroup.INSTANCE : NotificationGradientStrokeLineAdapter.NotificationStrokeLineLightGroup.INSTANCE;
        }
        return isRegionA(notificationBlurMode) ? NotificationGradientStrokeLineAdapter.KeyguardNotificationAStrokeLineGroup.INSTANCE : NotificationGradientStrokeLineAdapter.KeyguardNotificationBStrokeLineGroup.INSTANCE;
    }

    public static boolean isRegionA(NotificationBlurMode notificationBlurMode) {
        return NotificationBlurMode.WALLPAPER_A == notificationBlurMode;
    }

    public static Pair rectLineFadeForSize(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (f4 + f3) * 2.0f;
        float f8 = (f6 + f5) * 2.0f;
        float f9 = f + f2;
        float f10 = f9 * f7;
        if (f10 > f3) {
            float f11 = f2 * f7;
            return TuplesKt.to(Float.valueOf((((f10 - f3) + f5) - f11) / f8), Float.valueOf(f11 / f8));
        }
        float f12 = f3 - f10;
        if (f9 <= 0.0f) {
            return TuplesKt.to(Float.valueOf(f), Float.valueOf(f2));
        }
        float f13 = (f5 - f12) / f8;
        return TuplesKt.to(Float.valueOf((f * f13) / f9), Float.valueOf((f13 * f2) / f9));
    }

    /* JADX WARN: Code duplicated, block: B:17:0x007f  */
    public final boolean applyAodProgressToCapsule(GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams, float f, float f2, Float f3) {
        boolean z;
        if (innerShadowParams != null) {
            float offsetShadowMaxAlpha = innerShadowParams.getOffsetShadowMaxAlpha();
            float offsetShadowMinAlpha = innerShadowParams.getOffsetShadowMinAlpha();
            float noOffsetShadowAlpha = innerShadowParams.getNoOffsetShadowAlpha();
            float offsetShadowScaleX = innerShadowParams.getOffsetShadowScaleX();
            NotificationInnerShadowAdapter.CapsuleInnerShadowGroup capsuleInnerShadowGroup = NotificationInnerShadowAdapter.CapsuleInnerShadowGroup.INSTANCE;
            float fFloatValue = capsuleInnerShadowGroup.getAlpha().get().floatValue() * f2;
            innerShadowParams.setOffsetShadowMaxAlpha(capsuleInnerShadowGroup.getOffsetShadowMaxAlpha().get().floatValue() * fFloatValue);
            innerShadowParams.setOffsetShadowMinAlpha(capsuleInnerShadowGroup.getOffsetShadowMinAlpha().get().floatValue() * fFloatValue);
            innerShadowParams.setNoOffsetShadowAlpha(capsuleInnerShadowGroup.getNoOffsetShadowAlpha().get().floatValue() * fFloatValue);
            if (f3 != null) {
                innerShadowParams.setOffsetShadowScaleX(f3.floatValue());
            }
            if (offsetShadowMaxAlpha == innerShadowParams.getOffsetShadowMaxAlpha() && offsetShadowMinAlpha == innerShadowParams.getOffsetShadowMinAlpha() && noOffsetShadowAlpha == innerShadowParams.getNoOffsetShadowAlpha() && offsetShadowScaleX == innerShadowParams.getOffsetShadowScaleX()) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (gradientStrokeLineParams == null) {
            return z;
        }
        float strokeLineAlphaNear = gradientStrokeLineParams.getStrokeLineAlphaNear();
        float strokeLineAlphaFar = gradientStrokeLineParams.getStrokeLineAlphaFar();
        NotificationGradientStrokeLineAdapter.CapsuleStrokeLineGroup capsuleStrokeLineGroup = NotificationGradientStrokeLineAdapter.CapsuleStrokeLineGroup.INSTANCE;
        float fFloatValue2 = capsuleStrokeLineGroup.getAlpha().get().floatValue() * f;
        gradientStrokeLineParams.setStrokeLineAlphaNear(capsuleStrokeLineGroup.getStrokeLineAlphaNear().get().floatValue() * fFloatValue2);
        gradientStrokeLineParams.setStrokeLineAlphaFar(capsuleStrokeLineGroup.getStrokeLineAlphaFar().get().floatValue() * fFloatValue2);
        return (!z && strokeLineAlphaNear == gradientStrokeLineParams.getStrokeLineAlphaNear() && strokeLineAlphaFar == gradientStrokeLineParams.getStrokeLineAlphaFar()) ? false : true;
    }

    public final void applyAodProgressToNotificationBlurParams(GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams, float f, NotificationBlurMode notificationBlurMode) {
        if (gradientStrokeLineParams != null) {
            INSTANCE.getClass();
            NotificationGradientStrokeLineAdapter.NotificationStrokeLineGroup currentNotificationStrokeLineGroup = getCurrentNotificationStrokeLineGroup(notificationBlurMode);
            float fFloatValue = (1.0f - f) * currentNotificationStrokeLineGroup.getAlpha().get().floatValue();
            gradientStrokeLineParams.setStrokeLineAlphaNear(currentNotificationStrokeLineGroup.getStrokeLineAlphaNear().get().floatValue() * fFloatValue);
            gradientStrokeLineParams.setStrokeLineAlphaFar(currentNotificationStrokeLineGroup.getStrokeLineAlphaFar().get().floatValue() * fFloatValue);
        }
        if (innerShadowParams != null) {
            INSTANCE.getClass();
            NotificationInnerShadowAdapter.NotificationInnerShadowGroup currentNotificationInnerShadowGroup = getCurrentNotificationInnerShadowGroup(notificationBlurMode);
            float fFloatValue2 = (1.0f - f) * currentNotificationInnerShadowGroup.getAlpha().get().floatValue();
            innerShadowParams.setOffsetShadowMaxAlpha(currentNotificationInnerShadowGroup.getOffsetShadowMaxAlpha().get().floatValue() * fFloatValue2);
            innerShadowParams.setOffsetShadowMinAlpha(currentNotificationInnerShadowGroup.getOffsetShadowMinAlpha().get().floatValue() * fFloatValue2);
            innerShadowParams.setNoOffsetShadowAlpha(currentNotificationInnerShadowGroup.getNoOffsetShadowAlpha().get().floatValue() * fFloatValue2);
        }
    }

    public final BlurMixConfig.BlurMixSingleWithShader closeAllPlatformMixConfig(NotificationBlurMode notificationBlurMode) {
        int iResolveDecoratorsMixedTopLayerB;
        int iResolveDecoratorsMixedBottomLayerB;
        if (isRegionA(notificationBlurMode)) {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            iResolveDecoratorsMixedTopLayerB = NotificationBlurMixColorParams.resolveDecoratorsMixedTopLayerA();
        } else {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            iResolveDecoratorsMixedTopLayerB = NotificationBlurMixColorParams.resolveDecoratorsMixedTopLayerB();
        }
        if (isRegionA(notificationBlurMode)) {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            iResolveDecoratorsMixedBottomLayerB = NotificationBlurMixColorParams.resolveDecoratorsMixedBottomLayerA();
        } else {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            iResolveDecoratorsMixedBottomLayerB = NotificationBlurMixColorParams.resolveDecoratorsMixedBottomLayerB();
        }
        return new BlurMixConfig.BlurMixSingleWithShader(new MixColorWithShader(5, iResolveDecoratorsMixedTopLayerB, 3, iResolveDecoratorsMixedBottomLayerB));
    }

    public final BlurMixConfig.BlurMixMultiWithShader expandGroupHeaderBtnPlatformMixConfig(NotificationBlurMode notificationBlurMode, String str, boolean z) {
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig;
        boolean z2 = !notificationBlurMode.getLight();
        boolean shade = notificationBlurMode.getShade();
        if (isRegionA(notificationBlurMode)) {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig = NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(new MixColorWithShader(5, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedTopLayerA(), 3, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedBottomLayerA()), z2, shade);
        } else if (NotificationBlurMode.WALLPAPER_B == notificationBlurMode) {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig = NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(new MixColorWithShader(5, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedTopLayerB(), 3, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedBottomLayerB()), z2, shade);
        } else {
            NotificationBlurMixColorParams.INSTANCE.getClass();
            blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig = NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(new MixColorWithShader(5, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedTopLayer(z2), 3, NotificationBlurMixColorParams.resolveExpandHeaderBtnMixedBottomLayer(z2)), z2, shade);
        }
        INSTANCE.updateMirrorScale(ViewBlurManager.CardType.EXPAND_GROUP_BUTTON, notificationBlurMode, blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig, z);
        return blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig;
    }

    public final GradientStrokeLineParams getGradientStrokeLineParams(ViewBlurManager.CardType cardType, NotificationBlurMode notificationBlurMode) {
        NotificationEffectRepository.Companion.getClass();
        if (!NotificationEffectRepository.isSupportBlurStroke) {
            return null;
        }
        int iOrdinal = cardType.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 2) {
                return NotificationGradientStrokeLineAdapter.CapsuleStrokeLineGroup.INSTANCE.createStrokeLineParams();
            }
            if (iOrdinal == 3 || iOrdinal == 6) {
                return NotificationGradientStrokeLineAdapter.DecoratorsStrokeLineGroup.INSTANCE.createStrokeLineParams();
            }
            if (iOrdinal != 7) {
                return null;
            }
        }
        return getCurrentNotificationStrokeLineGroup(notificationBlurMode).createStrokeLineParams();
    }

    public final InnerShadowParams getInnerShadowParams(ViewBlurManager.CardType cardType, NotificationBlurMode notificationBlurMode) {
        NotificationEffectRepository.Companion.getClass();
        if (!NotificationEffectRepository.isSupportBlurStroke) {
            return null;
        }
        int iOrdinal = cardType.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 2) {
                return NotificationInnerShadowAdapter.CapsuleInnerShadowGroup.INSTANCE.createInnerShadowParams();
            }
            if (iOrdinal == 3 || iOrdinal == 6) {
                return NotificationInnerShadowAdapter.DecoratorsInnerShadowGroup.INSTANCE.createInnerShadowParams();
            }
            if (iOrdinal != 7) {
                return null;
            }
        }
        return getCurrentNotificationInnerShadowGroup(notificationBlurMode).createInnerShadowParams();
    }

    public final GradientStrokeLineParams getKeyguardStackingGradientStrokeLineParams(int i, NotificationBlurMode notificationBlurMode) {
        NotificationGradientStrokeLineAdapter.KeyguardNotificationAStrokeLineGroup keyguardNotificationAStrokeLineGroup;
        NotificationEffectRepository.Companion.getClass();
        if (!NotificationEffectRepository.isSupportBlurStroke) {
            return null;
        }
        if (i == 1) {
            keyguardNotificationAStrokeLineGroup = notificationBlurMode == NotificationBlurMode.WALLPAPER_A ? NotificationGradientStrokeLineAdapter.KeyguardFirstStackAStrokeLineGroup.INSTANCE : NotificationGradientStrokeLineAdapter.KeyguardFirstStackStrokeLineGroup.INSTANCE;
        } else if (i != 2) {
            keyguardNotificationAStrokeLineGroup = notificationBlurMode == NotificationBlurMode.WALLPAPER_A ? NotificationGradientStrokeLineAdapter.KeyguardNotificationAStrokeLineGroup.INSTANCE : NotificationGradientStrokeLineAdapter.KeyguardNotificationBStrokeLineGroup.INSTANCE;
        } else {
            keyguardNotificationAStrokeLineGroup = notificationBlurMode == NotificationBlurMode.WALLPAPER_A ? NotificationGradientStrokeLineAdapter.KeyguardSecondStackAStrokeLineGroup.INSTANCE : NotificationGradientStrokeLineAdapter.KeyguardSecondStackStrokeLineGroup.INSTANCE;
        }
        return keyguardNotificationAStrokeLineGroup.createStrokeLineParams();
    }

    public final InnerShadowParams getKeyguardStackingInnerShadowParams(int i, NotificationBlurMode notificationBlurMode) {
        NotificationEffectRepository.Companion.getClass();
        if (NotificationEffectRepository.isSupportBlurStroke) {
            return NotificationInnerShadowAdapter.INSTANCE.createKeyguardStackingInnerShadowParams(i, notificationBlurMode);
        }
        return null;
    }

    public final void initCache(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        cachedBaseWidthPx = 328.0f * f;
        cachedBaseHeightPx = 60.0f * f;
    }

    public final void initOlive(Context context) {
        NotificationBlurMixColorParams.INSTANCE.initOliveLink(context);
        NotificationGradientStrokeLineAdapter.INSTANCE.initOliveLink(context);
        NotificationInnerShadowAdapter.INSTANCE.initOliveLink(context);
    }

    public final BlurMixConfig keyguardStackingPlatformMixConfig(int i, NotificationBlurMode notificationBlurMode) {
        NotificationBlurMixColorParams.KeyguardStackingMixedColorsA.First first;
        if (isRegionA(notificationBlurMode)) {
            first = i == 1 ? NotificationBlurMixColorParams.KeyguardStackingMixedColorsA.First.INSTANCE : NotificationBlurMixColorParams.KeyguardStackingMixedColorsA.Second.INSTANCE;
        } else if (i == 1) {
            NotificationEffectRepository.Companion.getClass();
            first = NotificationEffectRepository.isSupportBlurStroke ? NotificationBlurMixColorParams.KeyguardStackingMixedColorsB.First.INSTANCE : NotificationBlurMixColorParams.KeyguardStackingMixedColorsB.FirstNoStroke.INSTANCE;
        } else {
            NotificationEffectRepository.Companion.getClass();
            first = NotificationEffectRepository.isSupportBlurStroke ? NotificationBlurMixColorParams.KeyguardStackingMixedColorsB.Second.INSTANCE : NotificationBlurMixColorParams.KeyguardStackingMixedColorsB.SecondNoStroke.INSTANCE;
        }
        return new BlurMixConfig.BlurMixMultiWithShader(new MixColorWithShader(5, first.getTopLayerColor(), 3, first.getBottomLayerColor()), MixColorWithShader.Companion.getEMPTY());
    }

    public final void mapGradientStrokeLineParamsForSize(GradientStrokeLineParams gradientStrokeLineParams, NotificationBlurMode notificationBlurMode, float f, float f2, float f3, float f4) {
        float fMax = Math.max(1.0f, f3);
        float fMax2 = Math.max(1.0f, f4);
        float fMax3 = Math.max(1.0f, f);
        float fMax4 = Math.max(1.0f, f2);
        NotificationGradientStrokeLineAdapter.NotificationStrokeLineGroup currentNotificationStrokeLineGroup = getCurrentNotificationStrokeLineGroup(notificationBlurMode);
        float fFloatValue = currentNotificationStrokeLineGroup.getStrokeLineTransverseNearSolid().get().floatValue();
        float fFloatValue2 = currentNotificationStrokeLineGroup.getStrokeLineTransverseNearFade().get().floatValue();
        float fFloatValue3 = currentNotificationStrokeLineGroup.getStrokeLineTransverseFarSolid().get().floatValue();
        float fFloatValue4 = currentNotificationStrokeLineGroup.getStrokeLineTransverseFarFade().get().floatValue();
        Pair pairRectLineFadeForSize = rectLineFadeForSize(fFloatValue, fFloatValue2, fMax3, fMax4, fMax, fMax2);
        float fFloatValue5 = ((Number) pairRectLineFadeForSize.component1()).floatValue();
        float fFloatValue6 = ((Number) pairRectLineFadeForSize.component2()).floatValue();
        Pair pairRectLineFadeForSize2 = rectLineFadeForSize(fFloatValue3, fFloatValue4, fMax3, fMax4, fMax, fMax2);
        float fFloatValue7 = ((Number) pairRectLineFadeForSize2.component1()).floatValue();
        float fFloatValue8 = ((Number) pairRectLineFadeForSize2.component2()).floatValue();
        gradientStrokeLineParams.setStrokeLineTransverseNearSolid(fFloatValue5);
        gradientStrokeLineParams.setStrokeLineTransverseNearFade(Math.max(fFloatValue6, 0.001f));
        gradientStrokeLineParams.setStrokeLineTransverseFarSolid(fFloatValue7);
        gradientStrokeLineParams.setStrokeLineTransverseFarFade(Math.max(fFloatValue8, 0.001f));
    }

    public final void mapInnerShadowParamsForSize(InnerShadowParams innerShadowParams, NotificationBlurMode notificationBlurMode, float f, float f2, float f3, float f4) {
        float fMax = Math.max(1.0f, f3);
        float fMax2 = Math.max(1.0f, f4);
        float fMax3 = Math.max(1.0f, f);
        float fMax4 = Math.max(1.0f, f2);
        NotificationInnerShadowAdapter.NotificationInnerShadowGroup currentNotificationInnerShadowGroup = getCurrentNotificationInnerShadowGroup(notificationBlurMode);
        float fFloatValue = currentNotificationInnerShadowGroup.getOffsetShadowScaleX().get().floatValue();
        float fFloatValue2 = currentNotificationInnerShadowGroup.getOffsetShadowScaleY().get().floatValue();
        innerShadowParams.setOffsetShadowScaleX((((fFloatValue - 1.0f) * fMax3) / fMax) + 1.0f);
        innerShadowParams.setOffsetShadowScaleY((((fFloatValue2 - 1.0f) * fMax4) / fMax2) + 1.0f);
    }

    public final BlurMixConfig.BlurMixMultiWithShader normalCardPlatformMixConfig(NotificationBlurMode notificationBlurMode, boolean z, String str) {
        NotificationBlurMixColorParams.NormalCardMixedColorsA normalCardMixedColorsA;
        boolean z2 = !notificationBlurMode.getLight();
        boolean shade = notificationBlurMode.getShade();
        if (isRegionA(notificationBlurMode)) {
            normalCardMixedColorsA = NotificationBlurMixColorParams.NormalCardMixedColorsA.INSTANCE;
        } else if (NotificationBlurMode.WALLPAPER_B == notificationBlurMode) {
            NotificationEffectRepository.Companion.getClass();
            normalCardMixedColorsA = NotificationEffectRepository.isSupportBlurStroke ? NotificationBlurMixColorParams.NormalCardMixedColorsB.INSTANCE : NotificationBlurMixColorParams.NormalCardMixedColorsBNoStroke.INSTANCE;
        } else if (notificationBlurMode.getLight()) {
            NotificationEffectRepository.Companion.getClass();
            normalCardMixedColorsA = NotificationEffectRepository.isSupportBlurStroke ? NotificationBlurMixColorParams.NormalCardMixedColorsLight.INSTANCE : NotificationBlurMixColorParams.NormalCardMixedColorsLightNoStroke.INSTANCE;
        } else {
            NotificationEffectRepository.Companion.getClass();
            normalCardMixedColorsA = NotificationEffectRepository.isSupportBlurStroke ? NotificationBlurMixColorParams.NormalCardMixedColorsDark.INSTANCE : NotificationBlurMixColorParams.NormalCardMixedColorsDarkNoStroke.INSTANCE;
        }
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig = NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(new MixColorWithShader(5, normalCardMixedColorsA.getTopLayerColor(), 3, normalCardMixedColorsA.getBottomLayerColor()), z2, shade);
        INSTANCE.updateMirrorScale(ViewBlurManager.CardType.NOTIFICATION, notificationBlurMode, blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig, z);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "NotificationPlatFormBlu-->".concat("[updateCardMixConfig] normalCardPlatformMixConfig blurMode=" + notificationBlurMode + ", reason=" + str + " " + blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig));
        }
        return blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig;
    }

    public final float notificationBaseHeightPx(Context context) {
        if (cachedBaseHeightPx == 0.0f) {
            initCache(context);
        }
        return cachedBaseHeightPx;
    }

    public final float notificationBaseWidthPx(Context context) {
        if (cachedBaseWidthPx == 0.0f) {
            initCache(context);
        }
        return cachedBaseWidthPx;
    }

    public final void registerOliveListen(Object obj, Function0 function0) {
        NotificationBlurMixColorParams.INSTANCE.registerChangeListener(obj, function0);
        NotificationGradientStrokeLineAdapter.INSTANCE.registerChangeListener(obj, function0);
        NotificationInnerShadowAdapter.INSTANCE.registerChangeListener(obj, function0);
    }

    public final void updateGradientStrokeLineParamsForNotificationSize(Context context, GradientStrokeLineParams gradientStrokeLineParams, float f, float f2, NotificationBlurMode notificationBlurMode) {
        if (gradientStrokeLineParams == null) {
            return;
        }
        NotificationEffectRepository.Companion.getClass();
        if (NotificationEffectRepository.isSupportBlurStroke) {
            mapGradientStrokeLineParamsForSize(gradientStrokeLineParams, notificationBlurMode, notificationBaseWidthPx(context), notificationBaseHeightPx(context), f, f2);
        }
    }

    public final void updateInnerShadowParamsForNotificationSize(Context context, InnerShadowParams innerShadowParams, float f, float f2, NotificationBlurMode notificationBlurMode) {
        if (innerShadowParams == null) {
            return;
        }
        NotificationEffectRepository.Companion.getClass();
        if (NotificationEffectRepository.isSupportBlurStroke) {
            mapInnerShadowParamsForSize(innerShadowParams, notificationBlurMode, notificationBaseWidthPx(context), notificationBaseHeightPx(context), f, f2);
        }
    }

    public final void updateMirrorScale(ViewBlurManager.CardType cardType, NotificationBlurMode notificationBlurMode, BlurMixConfig blurMixConfig, boolean z) {
        blurMixConfig.setMirrorScale((z && (((cardType == ViewBlurManager.CardType.MENU || cardType == ViewBlurManager.CardType.NOTIFICATION || cardType == ViewBlurManager.CardType.EXPAND_GROUP_BUTTON || cardType == ViewBlurManager.CardType.CUSTOM) && notificationBlurMode.getShade()) || cardType == ViewBlurManager.CardType.SETTINGS || cardType == ViewBlurManager.CardType.CLEAR_ALL)) ? 0.94f : 1.0f);
    }
}
