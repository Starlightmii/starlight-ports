package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.graphics.Point;
import android.view.View;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SideGestureHomeHandleAnimation {
    public Point mDisplaySize;
    public boolean mHandleNavBarAnimation;
    public View mHome;
    public SideGestureConfigurationEx mSideGestureConfiguration;

    public void setHomeButton(View view) {
        this.mHome = view;
    }
}
