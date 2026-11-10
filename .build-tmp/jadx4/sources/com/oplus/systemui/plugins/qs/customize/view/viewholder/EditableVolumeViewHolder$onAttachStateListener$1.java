package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableVolumeViewHolder$onAttachStateListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ EditableVolumeViewHolder this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.this$0.getQsVolume().registerCallbacks();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.this$0.getQsVolume().unregisterCallbacks();
        this.this$0.getQsVolume().disableShowVolumeDialog(false);
    }
}
