package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.os.UserHandle;
import android.provider.Settings;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SideGestureConfiguration$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SideGestureConfiguration f$0;
    public /* synthetic */ int f$1;

    @Override // java.lang.Runnable
    public final void run() {
        SideGestureConfiguration sideGestureConfiguration = this.f$0;
        Settings.System.putIntForUser(sideGestureConfiguration.mContext.getContentResolver(), "side_gesture_area_width", this.f$1, UserHandle.myUserId());
    }
}
