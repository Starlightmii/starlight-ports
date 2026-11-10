package com.oplus.systemui.volume.utils;

import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.OplusVibrateHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRMChangeVibrateHelper {
    private final OplusVibrateHelper vibrateHelper;

    public OplusRMChangeVibrateHelper(OplusVibrateHelper oplusVibrateHelper) {
        this.vibrateHelper = oplusVibrateHelper;
    }

    public final boolean vibrateOnRMChanged(int i, String str) {
        Integer num;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            FeatureOption.INSTANCE.getClass();
            Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(",isSupportGameHyperButton = ", "OplusRMChangeVibrateUtil-->", KeyguardLogger$.ExternalSyntheticOutline0.m(i, "vibrateOnRMChanged: ringerMode = ", ", fromTag = ", str, ", isOplusActionButton = "), FeatureOption.isOplusActionButton(), FeatureOption.isSupportGameHyperButton()));
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isSupportGameHyperButton() && !FeatureOption.isOplusActionButton()) {
            return false;
        }
        if (i != 1) {
            num = i != 2 ? null : 308;
        } else {
            num = 430;
        }
        if (num == null) {
            return false;
        }
        OplusVibrateHelper.vibrate$default(this.vibrateHelper, LogUtil.TAG_VOLUME, num.intValue());
        return true;
    }
}
