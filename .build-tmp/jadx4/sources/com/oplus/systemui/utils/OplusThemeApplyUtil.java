package com.oplus.systemui.utils;

import android.content.Context;
import android.view.ContextThemeWrapper;
import com.coui.appcompat.theme.COUIThemeOverlay;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusThemeApplyUtil {
    public static Context toAppContextTheme(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 2132019247);
        COUIThemeOverlay.getInstance().applyThemeOverlays(contextThemeWrapper);
        return contextThemeWrapper;
    }
}
