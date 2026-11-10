package com.oplus.systemui.notification.material;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightDelegate$drawableCallback$1 implements Drawable.Callback {
    public /* synthetic */ NotificationSpotLightDelegate this$0;

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        View view = this.this$0.sessionHost;
        if (view != null) {
            view.invalidate();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        View view = this.this$0.sessionHost;
        if (view != null) {
            view.scheduleDrawable(drawable, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        View view = this.this$0.sessionHost;
        if (view != null) {
            view.unscheduleDrawable(drawable, runnable);
        }
    }
}
