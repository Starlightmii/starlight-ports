package com.oplusos.systemui.common.util;

import android.graphics.Region;
import android.os.RemoteException;
import android.view.IWindow;
import android.view.View;
import android.view.WindowManagerGlobal;
import com.oplus.systemui.shade.NotificationPanelViewControllerExImp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationShadeRegionHelper {
    public static final String TAG = "NotificationShadeRegionHelper";
    private static WeakReference<View> currentView;
    public static final NotificationShadeRegionHelper INSTANCE = new NotificationShadeRegionHelper();
    private static Region region = new Region();
    private static Region lastRegion = new Region();
    private static final Runnable timeoutRunnable = NotificationShadeRegionHelper$timeoutRunnable$1.INSTANCE;
    private static final List<WeakReference<SetTouchExclusionRegionCallBack>> mCallbacks = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface SetTouchExclusionRegionCallBack {
    }

    public static final void registerCallback(SetTouchExclusionRegionCallBack setTouchExclusionRegionCallBack) {
        mCallbacks.add(new WeakReference<>(setTouchExclusionRegionCallBack));
    }

    public static final boolean setTouchExclusionRegion(boolean z, View view, String str) {
        SetTouchExclusionRegionCallBack setTouchExclusionRegionCallBack;
        INSTANCE.getClass();
        Iterator<T> it = mCallbacks.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (z && (setTouchExclusionRegionCallBack = (SetTouchExclusionRegionCallBack) weakReference.get()) != null) {
                ((NotificationPanelViewControllerExImp) setTouchExclusionRegionCallBack).onSetTouchExclusionRegionTrue();
            }
        }
        try {
            Region region2 = region;
            if (z) {
                region2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            } else {
                region2.set(0, 0, 0, 0);
            }
            if (Intrinsics.areEqual(lastRegion, region)) {
                return false;
            }
            WindowManagerGlobal.getWindowSession().updateTapExcludeRegion(IWindow.Stub.asInterface(view.getWindowToken()), region);
            lastRegion = Region.obtain(region);
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "setTouchExclusionRegion: " + region.getBounds() + " lastRegion = " + lastRegion + "mView = " + view + " from = " + str, null, 8);
            view.setTag(view.getId(), lastRegion);
            Runnable runnable = timeoutRunnable;
            SystemUIThreadUtil.removeCallbacksInUiThread(runnable);
            if (!z) {
                return true;
            }
            currentView = new WeakReference<>(view);
            SystemUIThreadUtil.runOnUiThreadDelay(runnable, 1000L);
            return true;
        } catch (RemoteException e) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "initExcludeRegion:" + e.getMessage());
            return false;
        }
    }
}
