package com.oplus.systemui.notification.headsup.windowframe;

import android.view.MotionEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CouiMotionForwarder {
    void forwardMotionEvent(MotionEvent motionEvent);

    void setDragYCallback(HeadsUpDragYCallback headsUpDragYCallback);
}
