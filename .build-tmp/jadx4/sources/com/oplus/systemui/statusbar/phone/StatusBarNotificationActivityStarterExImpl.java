package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterEx;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarNotificationActivityStarterExImpl extends StatusBarNotificationActivityStarterEx {
    public static final Companion Companion = new Companion();
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusBarNotificationActivityStarterExImpl(Context context) {
        this.context = context;
    }

    public void collapseOnMainThread(boolean z, Handler handler, Runnable runnable) {
        if (!z || (Util.isLandscape(this.context) && !DeviceStateController.getInstance().isOpenOrHalfOpen())) {
            handler.postDelayed(runnable, 50L);
        }
    }
}
