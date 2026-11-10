package com.oplus.systemui.volume.utils.effect;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1 implements Runnable {
    public /* synthetic */ Runnable $onBlurLightChanged;
    public /* synthetic */ Runnable $onSpotLightChanged;

    @Override // java.lang.Runnable
    public final void run() {
        this.$onBlurLightChanged.run();
        this.$onSpotLightChanged.run();
    }
}
