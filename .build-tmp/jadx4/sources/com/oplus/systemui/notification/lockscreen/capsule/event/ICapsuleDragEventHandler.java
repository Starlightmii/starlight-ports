package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.view.MotionEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ICapsuleDragEventHandler {
    default void onCancelDragScale(String str) {
    }

    default void onCapsuleDragEnd(MotionEvent motionEvent) {
    }

    default void onCapsuleDragExchangeToStack() {
    }

    default void onCapsuleDragStart(MotionEvent motionEvent) {
    }

    default void onCapsuleDragging(MotionEvent motionEvent) {
    }
}
