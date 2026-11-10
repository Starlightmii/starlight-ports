package com.oplusos.systemui.common.blurability.platformblur;

import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface BlurConfigsProvider {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
    }

    Map<Integer, BlurConfig> createBlurConfigs();

    BlurMixConfig createPlatformMixConfig(int i);

    default GradientStrokeLineParams resolveGradientStrokeLineParams(int i) {
        return null;
    }

    default InnerShadowParams resolveInnerShadowParams(int i) {
        return null;
    }
}
