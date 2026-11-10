package com.oplus.systemui.notification.blur;

import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStackingBlurConfig implements BlurConfigsProvider {
    public static final Companion Companion = new Companion();
    public final float blurCornerRadius;
    private final Function0 blurModeProvider;
    public final int blurRadius;
    public final int cardBlurColor;
    private final CornerParams cornerParam;
    public final float radiusWeight;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final BlurConfigsProvider createKeyguardStackingBlurConfig(int i, float f, float f2, int i2, CornerParams cornerParams, Function0 function0) {
            return new KeyguardStackingBlurConfig(i, f, f2, i2, cornerParams, function0);
        }
    }

    public KeyguardStackingBlurConfig(int i, float f, float f2, int i2, CornerParams cornerParams, Function0 function0) {
        this.cardBlurColor = i;
        this.blurCornerRadius = f;
        this.radiusWeight = f2;
        this.blurRadius = i2;
        this.cornerParam = cornerParams;
        this.blurModeProvider = function0;
    }

    @Override // com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider
    public Map<Integer, BlurConfig> createBlurConfigs() {
        BlurMixConfig blurMixConfigCreatePlatformMixConfig = createPlatformMixConfig(1);
        GradientStrokeLineParams gradientStrokeLineParamsResolveGradientStrokeLineParams = resolveGradientStrokeLineParams(1);
        InnerShadowParams innerShadowParamsResolveInnerShadowParams = resolveInnerShadowParams(1);
        CornerParams cornerParams = this.cornerParam;
        float f = this.radiusWeight;
        BlurConfig blurConfig = new BlurConfig(this.blurRadius, this.cardBlurColor, Float.valueOf(f), gradientStrokeLineParamsResolveGradientStrokeLineParams, cornerParams, innerShadowParamsResolveInnerShadowParams, false, blurMixConfigCreatePlatformMixConfig, null, 0, null, 33487036);
        float f2 = this.blurCornerRadius;
        blurConfig.setCornerRadius(f2);
        ViewBlurManager.CardType cardType = ViewBlurManager.CardType.NOTIFICATION;
        blurConfig.setCardType(0);
        BlurMixConfig blurMixConfigCreatePlatformMixConfig2 = createPlatformMixConfig(2);
        GradientStrokeLineParams gradientStrokeLineParamsResolveGradientStrokeLineParams2 = resolveGradientStrokeLineParams(2);
        InnerShadowParams innerShadowParamsResolveInnerShadowParams2 = resolveInnerShadowParams(2);
        CornerParams cornerParams2 = this.cornerParam;
        BlurConfig blurConfig2 = new BlurConfig(this.blurRadius, this.cardBlurColor, Float.valueOf(f), gradientStrokeLineParamsResolveGradientStrokeLineParams2, cornerParams2, innerShadowParamsResolveInnerShadowParams2, false, blurMixConfigCreatePlatformMixConfig2, null, 0, null, 33487036);
        blurConfig2.setCornerRadius(f2);
        blurConfig2.setCardType(0);
        return MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(1, blurConfig), TuplesKt.to(2, blurConfig2)});
    }

    @Override // com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider
    public BlurMixConfig createPlatformMixConfig(int i) {
        return NotificationPlatFormBlurParamsManager.INSTANCE.keyguardStackingPlatformMixConfig(i, (NotificationBlurMode) this.blurModeProvider.invoke());
    }

    @Override // com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider
    public GradientStrokeLineParams resolveGradientStrokeLineParams(int i) {
        return NotificationPlatFormBlurParamsManager.INSTANCE.getKeyguardStackingGradientStrokeLineParams(i, (NotificationBlurMode) this.blurModeProvider.invoke());
    }

    @Override // com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider
    public InnerShadowParams resolveInnerShadowParams(int i) {
        return NotificationPlatFormBlurParamsManager.INSTANCE.getKeyguardStackingInnerShadowParams(i, (NotificationBlurMode) this.blurModeProvider.invoke());
    }
}
