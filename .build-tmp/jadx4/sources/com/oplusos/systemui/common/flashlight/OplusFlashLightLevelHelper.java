package com.oplusos.systemui.common.flashlight;

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFlashLightLevelHelper {
    public static final OplusFlashLightLevelHelper INSTANCE = new OplusFlashLightLevelHelper();
    private static final String TAG = "OplusFlashLightLevelHelper";
    public static final int defaultLightLevel;
    public static final int maxFlashLightLevel;
    public static final int superSaveLightLevel;

    static {
        maxFlashLightLevel = FeatureOption.isSupportMultiModeFlashLight ? 50 : 4;
        superSaveLightLevel = mapToLevel$default(1.0f, 0.5f);
        defaultLightLevel = mapToLevel$default(1.0f, 0.5f);
    }

    public static final int getDefaultLightLevel() {
        return defaultLightLevel;
    }

    public static /* synthetic */ void getDefaultLightLevel$annotations() {
    }

    public static final int getSuperSaveLightLevel() {
        return superSaveLightLevel;
    }

    public static /* synthetic */ void getSuperSaveLightLevel$annotations() {
    }

    public static float mapDragAxisToEffect(float f) {
        if (f <= 0.0f) {
            return mapLinearSegment(f, -0.05f, 0.0f, 0.0f, 0.05f);
        }
        return f <= 1.0f ? mapLinearSegment(f, 0.0f, 1.0f, 0.05f, 0.95f) : mapLinearSegment(f, 1.0f, 1.05f, 0.95f, 1.0f);
    }

    public static final Pair<Float, Float> mapDragProgressToEffectHost(float f, float f2) {
        INSTANCE.getClass();
        float fMapDragAxisToEffect = mapDragAxisToEffect(f2);
        double d = fMapDragAxisToEffect;
        return TuplesKt.to(Float.valueOf(mapDragAxisToEffect(f) * RangesKt___RangesKt.coerceIn((float) (((d * 0.953d) + ((0.125d * d) * d)) - 0.093d), 0.0f, 1.0f)), Float.valueOf(fMapDragAxisToEffect));
    }

    public static float mapLinearSegment(float f, float f2, float f3, float f4, float f5) {
        if (f <= f2) {
            return f4;
        }
        return f >= f3 ? f5 : DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f5, f4, (f - f2) / (f3 - f2), f4);
    }

    public static int mapToLevel$default(float f, float f2) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        float fCoerceIn2 = RangesKt___RangesKt.coerceIn(f2, 0.0f, 1.0f);
        if (FeatureOption.isSupportMultiModeFlashLight) {
            INSTANCE.getClass();
            if (fCoerceIn2 > 0.0f) {
                return RangesKt___RangesKt.coerceIn(((int) Math.ceil(fCoerceIn * 5.0f)) - 1, 0, 4) + 1 + ((RangesKt___RangesKt.coerceIn((int) Math.ceil(fCoerceIn2 * 10.0f), 1, 10) - 1) * 5);
            }
        } else {
            INSTANCE.getClass();
            if (fCoerceIn2 > 0.0f) {
                return RangesKt___RangesKt.coerceIn((int) Math.ceil(fCoerceIn2 * 4.0f), 0, 4);
            }
        }
        return 0;
    }

    public final int getMaxFlashLightLevel() {
        return maxFlashLightLevel;
    }

    public final Pair<Float, Float> mapFromLevel(int i) {
        Pair pair;
        int iCoerceIn = RangesKt___RangesKt.coerceIn(i, 0, maxFlashLightLevel);
        if (FeatureOption.isSupportMultiModeFlashLight) {
            Float fValueOf = Float.valueOf(0.0f);
            if (iCoerceIn <= 0) {
                pair = TuplesKt.to(fValueOf, fValueOf);
            } else {
                int i2 = iCoerceIn - 1;
                pair = TuplesKt.to(Float.valueOf((RangesKt___RangesKt.coerceIn(i2 % 5, 0, 4) + 1.0f) / 5.0f), Float.valueOf(RangesKt___RangesKt.coerceIn((i2 / 5) + 1, 1, 10) / 10.0f));
            }
        } else {
            pair = TuplesKt.to(Float.valueOf(1.0f), Float.valueOf(RangesKt___RangesKt.coerceIn(iCoerceIn / 4.0f, 0.0f, 1.0f)));
        }
        return TuplesKt.to(Float.valueOf(((Number) pair.component1()).floatValue()), Float.valueOf(((Number) pair.component2()).floatValue()));
    }
}
