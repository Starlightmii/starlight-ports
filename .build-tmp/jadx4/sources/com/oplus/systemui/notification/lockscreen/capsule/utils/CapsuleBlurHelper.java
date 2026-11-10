package com.oplus.systemui.notification.lockscreen.capsule.utils;

import android.content.Context;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleBlurHelper {
    public static final CapsuleBlurHelper INSTANCE = new CapsuleBlurHelper();

    public final boolean supportBlurDrawable(Context context) {
        return ScrimUtil.isMiddleOrHighAnimLevel(context);
    }

    public final boolean supportBlurMetaBall(Context context) {
        return supportBlurDrawable(context);
    }

    public final boolean userNormalDrawable(Context context) {
        if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            return true;
        }
        ScrimControllerEx.Companion.getClass();
        return ScrimControllerEx.isThirdTheme || GaussBlurUtils.isGaussBlurDisabled() || !supportBlurDrawable(context);
    }
}
