package com.oplus.systemui.seamless.widget;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarFakeFrame$mOnDrawListener$1 implements ViewTreeObserver.OnDrawListener {
    public /* synthetic */ StatusBarFakeFrame this$0;

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        Object parent = this.this$0.getParent();
        View view = parent instanceof View ? (View) parent : null;
        if (view == null || this.this$0.getTransitionAlpha() <= 0.0f || view.getTransitionAlpha() <= 0.0f) {
            return;
        }
        this.this$0.invalidate();
    }
}
