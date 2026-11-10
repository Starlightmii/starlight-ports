package com.oplus.systemui.volume.utils.effect;

import android.content.Context;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeMaterialEffectObserver {
    public static final OplusVolumeMaterialEffectObserver INSTANCE = new OplusVolumeMaterialEffectObserver();

    /* JADX INFO: renamed from: com.oplus.systemui.volume.utils.effect.OplusVolumeMaterialEffectObserver$register$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Runnable $registerBlurLightGate;
        public /* synthetic */ Runnable $registerSpotLightGate;
        public /* synthetic */ register.unregisterSuperPower.1 $unregisterSuperPower;

        @Override // java.lang.Runnable
        public final void run() {
            this.$unregisterSuperPower.run();
            this.$registerBlurLightGate.run();
            this.$registerSpotLightGate.run();
        }
    }

    public static final Runnable register(Context context, Runnable runnable, Runnable runnable2) {
        OplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1 oplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1 = new OplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1();
        oplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1.$onBlurLightChanged = runnable;
        oplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1.$onSpotLightChanged = runnable2;
        VarHandle.storeStoreFence();
        IObserverListener iObserverListener = new register.superPowerListener.1();
        ((register.superPowerListener.1) iObserverListener).$onBaseConditionChanged = oplusVolumeMaterialEffectObserver$register$onBaseConditionChanged$1;
        VarHandle.storeStoreFence();
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(iObserverListener);
        register.unregisterSuperPower.1 r0 = new register.unregisterSuperPower.1();
        r0.$superPowerListener = iObserverListener;
        VarHandle.storeStoreFence();
        Runnable runnableRegisterEnableStateObserver = OplusVolumeBlurLightGate.registerEnableStateObserver(context, runnable);
        Runnable runnableRegisterEnableStateObserver2 = OplusVolumeSpotLightGate.registerEnableStateObserver(context, runnable2);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$unregisterSuperPower = r0;
        anonymousClass1.$registerBlurLightGate = runnableRegisterEnableStateObserver;
        anonymousClass1.$registerSpotLightGate = runnableRegisterEnableStateObserver2;
        VarHandle.storeStoreFence();
        return anonymousClass1;
    }
}
