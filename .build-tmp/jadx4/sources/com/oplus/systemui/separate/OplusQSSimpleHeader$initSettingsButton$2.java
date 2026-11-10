package com.oplus.systemui.separate;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$initSettingsButton$2 implements View.OnAttachStateChangeListener {
    public /* synthetic */ OplusQSSimpleHeader this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        if (view.getParent() == this.this$0.settingsContainer) {
            this.this$0.refreshSettingsButtonBackground();
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.this$0.getViewBlurManager().releaseBlurProxyForView(view, true, "OplusQSSimpleHeader detached");
    }
}
