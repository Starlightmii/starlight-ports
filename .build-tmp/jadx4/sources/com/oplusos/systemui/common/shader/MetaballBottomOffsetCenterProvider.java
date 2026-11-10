package com.oplusos.systemui.common.shader;

import android.graphics.Rect;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaballBottomOffsetCenterProvider implements MetaballLightCenterProvider {
    public static final MetaballBottomOffsetCenterProvider INSTANCE = new MetaballBottomOffsetCenterProvider();

    @Override // com.oplusos.systemui.common.shader.MetaballLightCenterProvider
    public MetaballLightCenter calculate(Rect rect, float f, MetaballLightConfig metaballLightConfig) {
        return new MetaballLightCenter((metaballLightConfig.getCenterXRatio() * rect.width()) + rect.left, metaballLightConfig.getCenterYOffsetPx() + rect.bottom);
    }
}
