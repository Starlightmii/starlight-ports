package com.oplus.systemui.statusbar.notification.stack;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import com.oplus.systemui.statusbar.interpolator.ViscousFluidInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$overScrollerInterpolator$1 implements Interpolator {
    public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        TimeInterpolator timeInterpolator = this.this$0.overScrollerInterpolatorOverride;
        return timeInterpolator != null ? timeInterpolator.getInterpolation(f) : ViscousFluidInterpolator.Companion.getInstance().getInterpolation(f);
    }
}
