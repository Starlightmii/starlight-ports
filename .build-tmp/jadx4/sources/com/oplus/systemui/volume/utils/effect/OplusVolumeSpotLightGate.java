package com.oplus.systemui.volume.utils.effect;

import android.content.Context;
import com.coui.appcompat.COUIMaterialStateManager;
import com.oplus.systemui.volume.utils.material.VolumeMaterialCapability;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeSpotLightGate {
    public static boolean materialObserverRegistered;
    public static boolean materialStateListenerRegistered;
    public static final OplusVolumeSpotLightGate INSTANCE = new OplusVolumeSpotLightGate();
    private static final CopyOnWriteArrayList<Runnable> changeListeners = new CopyOnWriteArrayList<>();
    private static final OplusVolumeSpotLightGate$materialStateListener$1 materialStateListener = new OplusVolumeSpotLightGate$materialStateListener$1();

    /* JADX INFO: renamed from: com.oplus.systemui.volume.utils.effect.OplusVolumeSpotLightGate$registerEnableStateObserver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Runnable $onChanged;

        @Override // java.lang.Runnable
        public final void run() {
            OplusVolumeSpotLightGate.changeListeners.remove(this.$onChanged);
        }
    }

    public static final void access$notifyChanged(OplusVolumeSpotLightGate oplusVolumeSpotLightGate) {
        oplusVolumeSpotLightGate.getClass();
        Iterator<T> it = changeListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    public static final boolean isVolumeSpotLightEnabled(Context context, boolean z) {
        return VolumeMaterialCapability.Companion.isSpotLightEnabled(context, z);
    }

    public static final Runnable registerEnableStateObserver(Context context, Runnable runnable) {
        OplusVolumeSpotLightGate oplusVolumeSpotLightGate = INSTANCE;
        Context applicationContext = context.getApplicationContext();
        oplusVolumeSpotLightGate.getClass();
        if (!materialObserverRegistered) {
            COUIMaterialStateManager.INSTANCE.registerMaterialStateObserver(applicationContext, 4);
            materialObserverRegistered = true;
        }
        if (!materialStateListenerRegistered) {
            COUIMaterialStateManager.INSTANCE.addOnMaterialStateChangedListener(materialStateListener);
            materialStateListenerRegistered = true;
        }
        changeListeners.add(runnable);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$onChanged = runnable;
        VarHandle.storeStoreFence();
        return anonymousClass1;
    }
}
