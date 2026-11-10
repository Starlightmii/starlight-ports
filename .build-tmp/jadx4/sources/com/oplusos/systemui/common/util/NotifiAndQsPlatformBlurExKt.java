package com.oplusos.systemui.common.util;

import android.content.Context;
import android.graphics.Color;
import android.util.MathUtils;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.blurability.MixColorWithShader;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotifiAndQsPlatformBlurExKt {
    public static final String TAG = "NotifiAndQsPlatformBlurEx";
    private static final MixColor PANEL_DEPRECATED_MIX_COLOR = new MixColor(5, Color.parseColor("#99333333"), Color.parseColor("#80999999"));
    private static final MixColorWithShader PANEL_LIGHT_MIX_COLOR = new MixColorWithShader(5, Color.parseColor("#92000000"), 2, Color.parseColor("#66999999"));
    private static final MixColorWithShader PANEL_NIGHT_MIX_COLOR = new MixColorWithShader(5, Color.parseColor("#A6000000"), 2, Color.parseColor("#33999999"));
    private static final MixColor BOUNCER_MIX_COLOR = new MixColor(5, Color.parseColor("#99262626"), Color.parseColor("#66A6A6A6"));

    public static final void applyPanelMirrorScale(boolean z, float f, ViewBlurProxy viewBlurProxy) {
        viewBlurProxy.getBlurConfig().getPlatformMixConfig().setMirrorScale(z ? MathUtils.lerp(1.0f, 0.94f, f) : 1.0f);
    }

    public static final MixColor getBOUNCER_MIX_COLOR() {
        return BOUNCER_MIX_COLOR;
    }

    public static final MixColor getPANEL_DEPRECATED_MIX_COLOR() {
        return PANEL_DEPRECATED_MIX_COLOR;
    }

    public static final MixColorWithShader getPANEL_LIGHT_MIX_COLOR() {
        return PANEL_LIGHT_MIX_COLOR;
    }

    public static final MixColorWithShader getPANEL_NIGHT_MIX_COLOR() {
        return PANEL_NIGHT_MIX_COLOR;
    }

    public static final int panelBlurRadius(Context context) {
        return context.getResources().getInteger(2131427347);
    }

    public static final BlurMixConfig panelBouncerMixConfig(boolean z) {
        BlurMixConfig.BlurMixSingle blurMixSingle = new BlurMixConfig.BlurMixSingle(BOUNCER_MIX_COLOR);
        LogUtil.i$default("NotifiAndQsPlatformBlurEx", "panelBouncerMixConfig", null, null, 12);
        blurMixSingle.setAlphaWithBlurAmount(!z);
        return blurMixSingle;
    }

    public static final BlurMixConfig panelElementPlatformMixConfig(MixColor mixColor) {
        return new BlurMixConfig.BlurMixMulti(mixColor, PANEL_DEPRECATED_MIX_COLOR);
    }

    public static final BlurMixConfig.BlurMixMultiWithShader panelElementPlatformMixWithShaderConfig(MixColorWithShader mixColorWithShader, boolean z, boolean z2) {
        MixColorWithShader empty;
        if (z2) {
            empty = PANEL_NIGHT_MIX_COLOR;
            if (!z) {
                empty = null;
            }
            if (empty == null) {
                empty = PANEL_LIGHT_MIX_COLOR;
            }
        } else {
            empty = MixColorWithShader.Companion.getEMPTY();
        }
        return new BlurMixConfig.BlurMixMultiWithShader(mixColorWithShader, empty);
    }

    public static final BlurMixConfig panelPlatformMixConfig(Context context, boolean z, boolean z2) {
        MixColorWithShader mixColorWithShader = PANEL_NIGHT_MIX_COLOR;
        if (!z2) {
            mixColorWithShader = null;
        }
        if (mixColorWithShader == null) {
            mixColorWithShader = PANEL_LIGHT_MIX_COLOR;
        }
        BlurMixConfig.BlurMixSingleWithShader blurMixSingleWithShader = new BlurMixConfig.BlurMixSingleWithShader(mixColorWithShader);
        blurMixSingleWithShader.setAlphaWithBlurAmount(!z);
        return blurMixSingleWithShader;
    }

    public static final BlurMixConfig.BlurMixMultiWithShader panelElementPlatformMixWithShaderConfig(Context context, MixColorWithShader mixColorWithShader, boolean z) {
        return panelElementPlatformMixWithShaderConfig(mixColorWithShader, context.getResources().getConfiguration().isNightModeActive(), z);
    }
}
