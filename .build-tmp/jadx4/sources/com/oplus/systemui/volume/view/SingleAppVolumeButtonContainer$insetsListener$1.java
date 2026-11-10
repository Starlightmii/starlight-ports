package com.oplus.systemui.volume.view;

import android.graphics.Rect;
import android.graphics.Region;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonContainer$insetsListener$1 implements ViewTreeObserver.OnComputeInternalInsetsListener {
    public /* synthetic */ SingleAppVolumeButtonContainer this$0;

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        Region region = new Region();
        Rect rect = new Rect();
        Rect rect2 = this.this$0.exclusionRect;
        if (rect2 != null) {
            rect.set(rect2);
            region.set(rect);
        }
        internalInsetsInfo.touchableRegion.set(region);
    }
}
