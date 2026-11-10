package com.oplus.systemui.qs.utils;

import android.content.Context;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.qs.base.util.SpecialLanguageTextScaleHelper;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DateViewTextConfig {
    public static float carrierMinTextSize;
    public static float textSize;
    public static final DateViewTextConfig INSTANCE = new DateViewTextConfig();
    private static final SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper = new SpecialLanguageTextScaleHelper();
    public static float defaultCellSizeTotalScale = 1.0f;

    public static int lockDpi(Context context, int i) {
        return (int) ((context.getResources().getDimensionPixelSize(i) * (KeyguardUtils.Companion.getInitialDisplayDensity() / 160.0f)) / context.getResources().getDisplayMetrics().density);
    }

    public final float getCarrierMinTextSize() {
        return carrierMinTextSize;
    }

    public final float getTextSize() {
        return textSize;
    }

    public final void updateScale(Context context, Function0 function0) {
        int i;
        SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper2 = specialLanguageTextScaleHelper;
        SystemTypefaceHelper.Companion.getInstance(context).getClass();
        if (specialLanguageTextScaleHelper2.isNeedUpdateScale(context, !OplusFontUtils.isFlipFontUsed)) {
            int iIntValue = ((Number) function0.invoke()).intValue();
            float f = iIntValue / 160.0f;
            defaultCellSizeTotalScale = RangesKt___RangesKt.coerceIn(specialLanguageTextScaleHelper2.calculatorSystemScale(f, iIntValue), SpecialLanguageTextScaleHelper.getMinScale(f), 1.15f);
            if (specialLanguageTextScaleHelper2.isSpecialHeightLanguage()) {
                i = 2131171627;
            } else {
                i = specialLanguageTextScaleHelper2.isSecondSpecialHeightLanguage() ? 2131171626 : 2131171623;
            }
            textSize = lockDpi(context, i) * defaultCellSizeTotalScale;
            carrierMinTextSize = lockDpi(context, 2131171917) * defaultCellSizeTotalScale;
        }
    }
}
