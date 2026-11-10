package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusCentralSurfacesExImplKt {
    public static final int refactSplashscreenBgColor(Context context, int i) {
        boolean zIsColorLight = ContrastColorUtil.isColorLight(i);
        if (32 == (context.getResources().getConfiguration().uiMode & 48) && zIsColorLight) {
            i = context.getColor(2131103662);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "refact Splash screen background Color-->retColor=", "CentralSurfaces-->", "SystemUi--Notification");
        }
        return i;
    }
}
