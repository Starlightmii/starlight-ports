package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Locale;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpecialLanguageTextScaleHelper {
    public static final Companion Companion = new Companion();
    public static final String TAG = "SpecialLanguageTextScaleHelper";
    private final Set<String> SPECIAL_HEIGHT_LANGUAGES = SetsKt__SetsKt.setOf(new String[]{"my", "bo", "ko", "ja"});
    private final Set<String> SPECIAL_SECOND_HEIGHT_LANGUAGES = SetsKt__SetsKt.setOf(new String[]{"ug", "ar", "bn", "ne", "hi", "th", "lo", "km", "or", "fa", "ka", "gu", "kn", "mr", "ml", "pa", "si", "te", "ta", "ur", "hy"});
    private final Set<String> SPECIAL_HEIGHT_LANGUAGES_COUNTRY = SetsKt__SetsJVMKt.setOf("JP");
    public float lastSystemFontScale = -1.0f;
    public int lastDisplayMetricsDpi = -1;
    public float lastDisplayMetricsDensity = -1.0f;
    private String lastLocalLanguage = "zh";
    private String lastLocalCountry = "CN";
    public boolean lastIsDefaultFont = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    private final float getMaxScale() {
        return this.lastIsDefaultFont ? 1.15f : 1.1f;
    }

    public static float getMinScale(float f) {
        return f < 3.0f ? 0.8f : 0.9f;
    }

    public final float calculatorSystemScale(float f, int i) {
        float minScale;
        float f2 = this.lastSystemFontScale;
        float minScale2 = 1.0f;
        if (f2 > 1.0f) {
            minScale = getMaxScale();
        } else {
            minScale = f2 < 1.0f ? getMinScale(f) : 1.0f;
        }
        int i2 = this.lastDisplayMetricsDpi;
        if (i2 > i) {
            minScale2 = getMaxScale();
        } else if (i2 < i) {
            minScale2 = getMinScale(f);
        }
        return minScale * minScale2;
    }

    public final boolean isNeedUpdateScale(Context context, boolean z) {
        float f = Resources.getSystem().getConfiguration().fontScale;
        int i = context.getResources().getDisplayMetrics().densityDpi;
        float f2 = context.getResources().getDisplayMetrics().density;
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (this.lastSystemFontScale == f && this.lastDisplayMetricsDpi == i && this.lastDisplayMetricsDensity == f2 && Intrinsics.areEqual(this.lastLocalLanguage, language) && Intrinsics.areEqual(this.lastLocalCountry, country) && this.lastIsDefaultFont == z) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                return false;
            }
            Log.d("SystemUi--QuickSettings", "SpecialLanguageTextScaleHelper-->[isNeedUpdateScale] is not need update scale");
            return false;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            float f3 = this.lastSystemFontScale;
            int i2 = this.lastDisplayMetricsDpi;
            float f4 = this.lastDisplayMetricsDensity;
            String str = this.lastLocalLanguage;
            String str2 = this.lastLocalCountry;
            boolean z2 = this.lastIsDefaultFont;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("[isNeedUpdateScale] fontScale=", f3, " -> ", f, ", metricsDpi=");
            RectList$.ExternalSyntheticOutline0.m(i2, i, " -> ", ", metricsDensity=", sbM);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f4, " -> ", f2, ", language=");
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str, " -> ", language, ", country=");
            ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str2, " -> ", country, ", isDefaultFont=");
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" -> ", "SpecialLanguageTextScaleHelper-->", sbM, z2, z));
        }
        this.lastSystemFontScale = f;
        this.lastDisplayMetricsDpi = i;
        this.lastDisplayMetricsDensity = f2;
        language.getClass();
        this.lastLocalLanguage = language;
        country.getClass();
        this.lastLocalCountry = country;
        this.lastIsDefaultFont = z;
        return true;
    }

    public final boolean isSecondSpecialHeightLanguage() {
        return this.SPECIAL_SECOND_HEIGHT_LANGUAGES.contains(Locale.getDefault().getLanguage());
    }

    public final boolean isSpecialHeightLanguage() {
        return this.SPECIAL_HEIGHT_LANGUAGES_COUNTRY.contains(Locale.getDefault().getCountry()) || this.SPECIAL_HEIGHT_LANGUAGES.contains(Locale.getDefault().getLanguage());
    }
}
