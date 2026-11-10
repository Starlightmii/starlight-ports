package com.oplus.systemui.volume.manager;

import com.oplus.systemui.navigationbar.eximpl.NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeAnimStateManager {
    public static final VolumeAnimStateManager INSTANCE = new VolumeAnimStateManager();
    private static final CopyOnWriteArrayList<VolumeAnimStateChangeListener> stateListeners = new CopyOnWriteArrayList<>();
    public static boolean volumeAnimPlaying;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface VolumeAnimStateChangeListener {
    }

    public static void updateVolumeAnimPlay(boolean z) {
        if (volumeAnimPlaying != z) {
            volumeAnimPlaying = z;
            Iterator<T> it = stateListeners.iterator();
            while (it.hasNext()) {
                ((NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1) ((VolumeAnimStateChangeListener) it.next())).onVolumeAnimStateChanged(volumeAnimPlaying);
            }
        }
    }

    public final void addStateChangeListener(VolumeAnimStateChangeListener volumeAnimStateChangeListener) {
        CopyOnWriteArrayList<VolumeAnimStateChangeListener> copyOnWriteArrayList = stateListeners;
        if (copyOnWriteArrayList.contains(volumeAnimStateChangeListener)) {
            return;
        }
        copyOnWriteArrayList.add(volumeAnimStateChangeListener);
        ((NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1) volumeAnimStateChangeListener).onVolumeAnimStateChanged(volumeAnimPlaying);
    }

    public final void removeStateChangeListener(VolumeAnimStateChangeListener volumeAnimStateChangeListener) {
        stateListeners.remove(volumeAnimStateChangeListener);
    }
}
