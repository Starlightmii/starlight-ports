package com.oplus.systemui.seedlingservice.statemanager;

import android.content.Context;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StateManager {
    private static final String TAG = "StateManager";
    private static boolean isKeyguardOccluded;
    private static boolean isKeyguardShowing;
    public static final StateManager INSTANCE = new StateManager();
    private static final ConcurrentHashMap<String, StateListener> listeners = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface StateListener {
        default void onCancelIntent() {
        }

        default void onLocked() {
        }
    }

    private StateManager() {
    }

    public final void addListener(String str, StateListener stateListener) {
        listeners.put(str, stateListener);
    }

    public final void destroyEventCard(String str) {
        LogUtil.i(TAG, "destroyEventCard eventId=" + str);
        StateListener stateListener = listeners.get(str);
        if (stateListener != null) {
            stateListener.onCancelIntent();
        }
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisKeyguardShowing: ", isKeyguardShowing);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tisKeyguardOccluded: ", isKeyguardOccluded);
        printWriter.println("\tlisteners: " + listeners);
    }

    public final void init(Context context) {
    }

    public final boolean isKeyguardOccluded() {
        return isKeyguardOccluded;
    }

    public final boolean isKeyguardShowing() {
        return isKeyguardShowing;
    }

    public final void onDestroy() {
        LogUtil.i(TAG, "destroy all seedling card");
        Iterator<T> it = listeners.values().iterator();
        while (it.hasNext()) {
            ((StateListener) it.next()).onCancelIntent();
        }
    }

    public final void onKeyGuardStatusChange(boolean z, boolean z2) {
        LogUtil.i(TAG, "onKeyGuardStatusChange: isKeyguardShowing=" + z + ", isKeyguardOccluded=" + z2);
        isKeyguardShowing = z;
        isKeyguardOccluded = z2;
        Iterator<T> it = listeners.values().iterator();
        while (it.hasNext()) {
            ((StateListener) it.next()).onLocked();
        }
    }

    public final void removeListener(String str) {
        listeners.remove(str);
    }
}
