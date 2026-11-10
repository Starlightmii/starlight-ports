package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.content.Context;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMetaBallUtils {
    public static final CapsuleMetaBallUtils INSTANCE = new CapsuleMetaBallUtils();

    public static final boolean isSupportMetaBall(Context context) {
        if (context == null) {
            return false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        if (!NotificationFeatureOption.isNotificationCapsuleMetaBallEnable || GaussBlurUtils.isGaussBlurDisabled()) {
            return false;
        }
        ScrimControllerEx.Companion.getClass();
        return !ScrimControllerEx.isThirdTheme && ScrimUtil.isMiddleOrHighAnimLevel(context);
    }
}
