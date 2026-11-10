package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.graphics.PointF;
import com.android.systemui.navigationbar.GesturePointContainerEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GesturePointContainer extends GesturePointContainerEx {
    public final PointF mPoint;
    public final int mRotation;
    public final int mSide;
    public final int mState;

    public GesturePointContainer(PointF pointF, int i, int i2, int i3) {
        this.mPoint = pointF;
        this.mState = i;
        this.mSide = i2;
        this.mRotation = i3;
    }

    public PointF getPoint() {
        return this.mPoint;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public int getSide() {
        return this.mSide;
    }

    public int getState() {
        return this.mState;
    }
}
