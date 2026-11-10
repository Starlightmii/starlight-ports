package com.oplusos.systemui.common.util;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationShadeRegionHelper$timeoutRunnable$1 implements Runnable {
    public static final NotificationShadeRegionHelper$timeoutRunnable$1 INSTANCE = new NotificationShadeRegionHelper$timeoutRunnable$1();

    @Override // java.lang.Runnable
    public final void run() {
        View view;
        WeakReference weakReference = NotificationShadeRegionHelper.currentView;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        NotificationShadeRegionHelper.setTouchExclusionRegion(false, view, "setTouchExclusionRegion timeout");
    }
}
