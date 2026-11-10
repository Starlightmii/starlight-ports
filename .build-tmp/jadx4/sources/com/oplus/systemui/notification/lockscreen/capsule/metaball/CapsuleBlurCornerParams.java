package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import com.oplus.posteffect.params.CornerParams;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplusos.systemui.common.blurability.BlurConfig;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleBlurCornerParams {
    public final boolean bindCornerParamsToBlurConfig(BlurConfig blurConfig) {
        if (SmoothRoundExKt.isCapsuleSmooth(blurConfig.getGradientStrokeCornerParam())) {
            return false;
        }
        blurConfig.getGradientStrokeCornerParam().setRadius(blurConfig.getCornerRadius());
        return true;
    }

    public final boolean clearCornerParamsFromBlurConfig(BlurConfig blurConfig) {
        boolean z;
        CornerParams gradientStrokeCornerParam = blurConfig.getGradientStrokeCornerParam();
        if (gradientStrokeCornerParam == null) {
            z = true;
        } else {
            z = !(gradientStrokeCornerParam.getRadius() == 0.0f);
        }
        if (!z) {
            return false;
        }
        blurConfig.getGradientStrokeCornerParam().setRadius(0.0f);
        return true;
    }

    public final boolean updateBlurDrawableCornerParams(BlurConfig blurConfig, boolean z) {
        return z ? clearCornerParamsFromBlurConfig(blurConfig) : bindCornerParamsToBlurConfig(blurConfig);
    }
}
