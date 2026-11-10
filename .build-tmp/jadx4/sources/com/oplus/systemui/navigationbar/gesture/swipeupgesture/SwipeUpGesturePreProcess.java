package com.oplus.systemui.navigationbar.gesture.swipeupgesture;

import android.content.Context;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SwipeUpGesturePreProcess {
    public Context mContext;
    public long mLastEventTime;
    public int mScreenHeight;
    public int mScreenWidth;
    public volatile int mSwipeActionState;
    public SwipeUpGestureManager mSwipeUpGestureManager;

    public long getLastEventTime() {
        return this.mLastEventTime;
    }

    public int getSwipeActionState() {
        return this.mSwipeActionState;
    }
}
