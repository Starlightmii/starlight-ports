package com.oplus.uxicon.helper;

import android.content.res.Resources;
import android.util.Log;
import com.airbnb.lottie.animation.content.BaseStrokeContent$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class IconConfigParser {
    public static IconConfig parserConfig(Resources resources, Long l) {
        double d;
        if (l.longValue() == -1) {
            return null;
        }
        IconConfig iconConfig = new IconConfig();
        iconConfig.setForeign((l.intValue() & 15) == 1);
        long jLongValue = l.longValue();
        int iIntValue = Long.valueOf(jLongValue >> 4).intValue() & 15;
        iconConfig.setTheme(iIntValue);
        iconConfig.setArtPlusOn(Long.valueOf(jLongValue >> 8).intValue() & 7);
        iconConfig.setSingleColorInternalChange((Long.valueOf(jLongValue >> 11).intValue() & 1) == 1);
        iconConfig.setIconShape(Long.valueOf(jLongValue >> 12).intValue() & 15);
        int iIntValue2 = Long.valueOf(jLongValue >> 16).intValue() & 65535;
        if (iIntValue2 == 0) {
            float fM = BaseStrokeContent$.ExternalSyntheticOutline0.m(resources.getDimensionPixelSize(2131168478), 1.0f, resources.getDisplayMetrics().density, 100.0f);
            int i = (int) fM;
            d = 0.5d;
            if (Math.abs(fM - i) > 0.5d) {
                i++;
            }
            iIntValue2 = i;
        } else {
            d = 0.5d;
        }
        iconConfig.setIconSize(iIntValue2);
        int iIntValue3 = Long.valueOf(jLongValue >> 32).intValue() & 65535;
        if (iIntValue3 == 0) {
            float fM2 = BaseStrokeContent$.ExternalSyntheticOutline0.m(resources.getDimensionPixelSize(2131168478), 1.0f, resources.getDisplayMetrics().density, 100.0f);
            iIntValue3 = (int) fM2;
            if (Math.abs(fM2 - iIntValue3) > d) {
                iIntValue3++;
            }
        }
        iconConfig.setForegroundSize(iIntValue3);
        iconConfig.setIconRadius(Long.valueOf(jLongValue >> 48).intValue() & 4095);
        iconConfig.setMonoFollowWallpaper((Long.valueOf(jLongValue >> 60).intValue() & 1) == 1 && (iIntValue == 3 || iIntValue == 8));
        iconConfig.setDarkModeIcon(Long.valueOf(jLongValue >> 61).intValue() & 1);
        iconConfig.setLocalSpecial((Long.valueOf(jLongValue >> 62).intValue() & 1) == 1);
        Log.d("IconConfigParser", "parseConfig::icon config =: ".concat(iconConfig.toString()));
        return iconConfig;
    }
}
