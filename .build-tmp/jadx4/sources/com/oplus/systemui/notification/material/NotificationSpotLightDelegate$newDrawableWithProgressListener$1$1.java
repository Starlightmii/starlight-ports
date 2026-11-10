package com.oplus.systemui.notification.material;

import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightDelegate$newDrawableWithProgressListener$1$1 implements COUISpotLightEffect.OnSpotLightProgressScopeListener {
    public /* synthetic */ COUISpotLightEffectDrawable $d;
    public /* synthetic */ NotificationSpotLightDelegate this$0;

    public final void onProgressScopeChanged(float f) {
        NotificationSpotLightDelegate.access$onSpotLightProgress(this.this$0, this.$d, f);
    }
}
