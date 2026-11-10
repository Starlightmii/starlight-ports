package com.oplus.systemui.volume.utils.spotlight;

import android.content.Context;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSpotLightEffectParams {
    public static final OplusVolumeSpotLightEffectParams INSTANCE = new OplusVolumeSpotLightEffectParams();

    public static final void apply(VolumeSpotLightTarget volumeSpotLightTarget, Context context, COUISpotLightEffectDrawable cOUISpotLightEffectDrawable) {
        cOUISpotLightEffectDrawable.setSpotLightType(context, volumeSpotLightTarget.getSpotLightType$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
    }
}
