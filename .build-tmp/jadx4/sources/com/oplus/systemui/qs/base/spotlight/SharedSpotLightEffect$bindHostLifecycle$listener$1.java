package com.oplus.systemui.qs.base.spotlight;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SharedSpotLightEffect$bindHostLifecycle$listener$1 implements View.OnAttachStateChangeListener {
    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        SharedSpotLightEffect.detach(view, "detach");
        view.removeOnAttachStateChangeListener(this);
        view.setTag(2131365593, null);
    }
}
