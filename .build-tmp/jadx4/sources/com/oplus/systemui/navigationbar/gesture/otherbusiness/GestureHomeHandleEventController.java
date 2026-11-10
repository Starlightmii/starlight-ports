package com.oplus.systemui.navigationbar.gesture.otherbusiness;

import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GestureHomeHandleEventController {
    private static final String TAG = "GestureHomeHandleEventController";
    public static boolean isKeyguardShowing;
    private static InputMonitorCompat mInputMonitor;
    public static final GestureHomeHandleEventController INSTANCE = new GestureHomeHandleEventController();
    private static final Lazy mListeners$delegate = LazyKt__LazyJVMKt.lazy(new GestureHomeHandleEventController$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface HomeHandleTouchEvent {
        default void onDown() {
        }

        default void onLongPressCancel() {
        }

        void onLongPressed();

        default void onPreLongPress() {
        }

        default void onShowPress() {
        }
    }

    private final CopyOnWriteArraySet<HomeHandleTouchEvent> getMListeners() {
        return (CopyOnWriteArraySet) mListeners$delegate.getValue();
    }

    public final void addHomeHandlerLongClickListener(HomeHandleTouchEvent homeHandleTouchEvent) {
        if (getMListeners().contains(homeHandleTouchEvent)) {
            return;
        }
        getMListeners().add(homeHandleTouchEvent);
    }

    public final boolean hasListeners() {
        return !getMListeners().isEmpty();
    }

    public final void onDown() {
        Iterator<T> it = getMListeners().iterator();
        while (it.hasNext()) {
            ((HomeHandleTouchEvent) it.next()).onDown();
        }
    }

    public final void onLongClick() {
        InputMonitorCompat inputMonitorCompat;
        List list = CollectionsKt___CollectionsKt.toList(getMListeners());
        if (!list.isEmpty()) {
            LauncherProxyService launcherProxyService = (LauncherProxyService) Dependency.sDependency.getDependencyInner(LauncherProxyService.class);
            launcherProxyService.getClass();
            try {
                if (launcherProxyService.mLauncherProxy != null) {
                    LogUtil.d(LogUtil.TAG_NAVBAR, "LauncherProxyService", " mLauncherProxy.notifyHomeHandleTriggerLongPress();");
                    launcherProxyService.mLauncherProxy.notifyHomeHandleTriggerLongPress();
                } else {
                    Log.e("LauncherProxyService", "Failed to get overview proxy for HomeHandleTriggerLongPress.");
                }
            } catch (RemoteException e) {
                Log.e("LauncherProxyService", "Failed to call notifyHomeHandleTriggerLongPress()", e);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "GestureHomeHandleEventController-->".concat("isKeyguardShowing: " + isKeyguardShowing + ", mInputMonitor: " + mInputMonitor));
            }
            if (isKeyguardShowing && (inputMonitorCompat = mInputMonitor) != null) {
                inputMonitorCompat.mInputMonitor.pilferPointers();
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((HomeHandleTouchEvent) it.next()).onLongPressed();
        }
    }

    public final void onLongPressCancel() {
        Iterator<T> it = getMListeners().iterator();
        while (it.hasNext()) {
            ((HomeHandleTouchEvent) it.next()).onLongPressCancel();
        }
    }

    public final void onPreLongPress() {
        Iterator<T> it = getMListeners().iterator();
        while (it.hasNext()) {
            ((HomeHandleTouchEvent) it.next()).onPreLongPress();
        }
    }

    public final void onShowPress() {
        Iterator<T> it = getMListeners().iterator();
        while (it.hasNext()) {
            ((HomeHandleTouchEvent) it.next()).onShowPress();
        }
    }

    public final void removeHomeHandlerLongClickListener(HomeHandleTouchEvent homeHandleTouchEvent) {
        if (getMListeners().contains(homeHandleTouchEvent)) {
            getMListeners().remove(homeHandleTouchEvent);
        }
    }

    public final void setInputMonitor(InputMonitorCompat inputMonitorCompat) {
        mInputMonitor = inputMonitorCompat;
    }

    public final void setKeyguardState(boolean z) {
        isKeyguardShowing = z;
    }
}
