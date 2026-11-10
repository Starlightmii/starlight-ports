package com.oplusos.systemui.common.blurability.platformblur;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.systemui.notification.blur.KeyguardStackingBlurConfig;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiPlatformBlurHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MultiPlatformBlurHelper";
    private final BlurConfigsProvider blurConfigsProvider;
    private final Map<Integer, PlatformBlurHelper> blurDrawableMap;
    public boolean blurEnabled;
    private final Map<Integer, BlurConfig> configMap;
    private final Function1 excludeRules;
    private final View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$hLRZO7IQBq0I56ehAqTJ1n17ZIM(MultiPlatformBlurHelper multiPlatformBlurHelper, BlurConfig blurConfig, PlatformBlurDrawable platformBlurDrawable) {
        platformBlurDrawable.setCallback(multiPlatformBlurHelper.view);
        platformBlurDrawable.applyBlurConfig(blurConfig, 1.0f);
        return Unit.INSTANCE;
    }

    public MultiPlatformBlurHelper(View view, Function1 function1, BlurConfigsProvider blurConfigsProvider) {
        this.view = view;
        this.excludeRules = function1;
        this.blurConfigsProvider = blurConfigsProvider;
        this.configMap = blurConfigsProvider != null ? ((KeyguardStackingBlurConfig) blurConfigsProvider).createBlurConfigs() : null;
        this.blurDrawableMap = new LinkedHashMap();
        this.blurEnabled = true;
    }

    public final void applyBlurConfig() {
        Map<Integer, BlurConfig> map;
        if (this.blurEnabled && (map = this.configMap) != null) {
            for (Map.Entry<Integer, BlurConfig> entry : map.entrySet()) {
                int iIntValue = entry.getKey().intValue();
                BlurConfig value = entry.getValue();
                PlatformBlurHelper platformBlurHelper = this.blurDrawableMap.get(Integer.valueOf(iIntValue));
                if (platformBlurHelper == null) {
                    this.blurDrawableMap.put(Integer.valueOf(iIntValue), createPlatformHelper(value));
                } else {
                    PlatformBlurDrawable blurDrawable = platformBlurHelper.getBlurDrawable();
                    if (blurDrawable == null || blurDrawable.applyBlurConfig(value, 1.0f) == null) {
                        platformBlurHelper.ensureBlurDrawable();
                    }
                }
            }
        }
    }

    public final PlatformBlurHelper createPlatformHelper(BlurConfig blurConfig) {
        PlatformBlurHelper platformBlurHelper = new PlatformBlurHelper(this.view, this.excludeRules, null);
        MultiPlatformBlurHelper$$ExternalSyntheticLambda0 multiPlatformBlurHelper$$ExternalSyntheticLambda0 = new MultiPlatformBlurHelper$$ExternalSyntheticLambda0();
        multiPlatformBlurHelper$$ExternalSyntheticLambda0.f$0 = this;
        multiPlatformBlurHelper$$ExternalSyntheticLambda0.f$1 = blurConfig;
        VarHandle.storeStoreFence();
        platformBlurHelper.setOnBlurDrawableCreated$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(multiPlatformBlurHelper$$ExternalSyntheticLambda0);
        platformBlurHelper.setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this.blurEnabled);
        return platformBlurHelper;
    }

    public final void forEachStackBlurConfig(Function1 function1) {
        Map<Integer, BlurConfig> map = this.configMap;
        if (map != null) {
            Iterator<Map.Entry<Integer, BlurConfig>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                function1.invoke(it.next().getValue());
            }
        }
    }

    public final boolean getBlurEnabled() {
        return this.blurEnabled;
    }

    public final Drawable getStackBlurDrawable(int i, Drawable drawable) {
        Map<Integer, BlurConfig> map;
        BlurConfig blurConfig;
        if (this.blurEnabled && (map = this.configMap) != null && (blurConfig = map.get(Integer.valueOf(i))) != null) {
            Map<Integer, PlatformBlurHelper> map2 = this.blurDrawableMap;
            Integer numValueOf = Integer.valueOf(i);
            PlatformBlurHelper platformBlurHelperCreatePlatformHelper = map2.get(numValueOf);
            if (platformBlurHelperCreatePlatformHelper == null) {
                platformBlurHelperCreatePlatformHelper = createPlatformHelper(blurConfig);
                map2.put(numValueOf, platformBlurHelperCreatePlatformHelper);
            }
            PlatformBlurHelper platformBlurHelper = platformBlurHelperCreatePlatformHelper;
            platformBlurHelper.ensureBlurDrawable();
            PlatformBlurDrawable blurDrawable = platformBlurHelper.getBlurDrawable();
            if (blurDrawable != null) {
                return blurDrawable;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MultiPlatformBlurHelper-->getStackBlurDrawable blurDrawable is null");
            }
        }
        return drawable;
    }

    public final Drawable getStackCacheBlurDrawable(int i) {
        PlatformBlurHelper platformBlurHelper = this.blurDrawableMap.get(Integer.valueOf(i));
        if (platformBlurHelper != null) {
            return platformBlurHelper.getBlurDrawable();
        }
        return null;
    }

    public final void setBlurEnabled(boolean z) {
        if (this.blurEnabled != z) {
            this.blurEnabled = z;
            if (z) {
                applyBlurConfig();
                return;
            }
            Iterator<Map.Entry<Integer, PlatformBlurHelper>> it = this.blurDrawableMap.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().setBlurEnabled$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(false);
            }
        }
    }

    public final void setCallback(Drawable.Callback callback) {
        Iterator<Map.Entry<Integer, PlatformBlurHelper>> it = this.blurDrawableMap.entrySet().iterator();
        while (it.hasNext()) {
            PlatformBlurDrawable blurDrawable = it.next().getValue().getBlurDrawable();
            if (blurDrawable != null) {
                blurDrawable.setCallback(callback);
            }
        }
    }

    public String toString() {
        return "MultiPlatformBlurHelper(configMap=" + this.configMap + ", blurDrawableMap=" + this.blurDrawableMap + ", blurEnabled=" + this.blurEnabled;
    }

    public final void updateCardPlatformMixConfig(int i, float f, GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams) {
        BlurConfigsProvider blurConfigsProvider = this.blurConfigsProvider;
        if (blurConfigsProvider != null) {
            Map<Integer, BlurConfig> map = this.configMap;
            if (map != null) {
                for (Map.Entry<Integer, BlurConfig> entry : map.entrySet()) {
                    int iIntValue = entry.getKey().intValue();
                    BlurConfig value = entry.getValue();
                    value.setCornerRadius(f);
                    value.getGradientStrokeCornerParam().setRadius(f);
                    value.setBlurColor(i);
                    KeyguardStackingBlurConfig keyguardStackingBlurConfig = (KeyguardStackingBlurConfig) blurConfigsProvider;
                    value.setPlatformMixConfig(keyguardStackingBlurConfig.createPlatformMixConfig(iIntValue));
                    GradientStrokeLineParams gradientStrokeLineParamsResolveGradientStrokeLineParams = keyguardStackingBlurConfig.resolveGradientStrokeLineParams(iIntValue);
                    if (gradientStrokeLineParamsResolveGradientStrokeLineParams == null) {
                        gradientStrokeLineParamsResolveGradientStrokeLineParams = gradientStrokeLineParams;
                    }
                    value.setGradientStrokeLineParam(gradientStrokeLineParamsResolveGradientStrokeLineParams);
                    InnerShadowParams innerShadowParamsResolveInnerShadowParams = keyguardStackingBlurConfig.resolveInnerShadowParams(iIntValue);
                    if (innerShadowParamsResolveInnerShadowParams == null) {
                        innerShadowParamsResolveInnerShadowParams = innerShadowParams;
                    }
                    value.setInnerShadowParams(innerShadowParamsResolveInnerShadowParams);
                }
            }
            applyBlurConfig();
        }
    }

    public final boolean verifyDrawable(Drawable drawable) {
        Iterator<Map.Entry<Integer, PlatformBlurHelper>> it = this.blurDrawableMap.entrySet().iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getValue().getBlurDrawable(), drawable)) {
                return true;
            }
        }
        return false;
    }
}
