package com.oplus.systemui.qs.base.util;

import android.view.MotionEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface Gefingerpoken {
    default boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    default boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    default boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
