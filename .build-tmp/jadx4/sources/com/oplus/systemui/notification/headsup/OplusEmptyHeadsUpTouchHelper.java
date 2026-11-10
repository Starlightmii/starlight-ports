package com.oplus.systemui.notification.headsup;

import android.view.MotionEvent;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusEmptyHeadsUpTouchHelper extends HeadsUpTouchHelper {
    public OplusEmptyHeadsUpTouchHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, HeadsUpTouchHelper.Callback callback, HeadsUpTouchHelper.HeadsUpNotificationViewController headsUpNotificationViewController) {
        super(headsUpManager, iStatusBarService, callback, headsUpNotificationViewController);
    }

    public final boolean isTrackingHeadsUp() {
        return false;
    }

    public final void notifyFling(boolean z) {
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
