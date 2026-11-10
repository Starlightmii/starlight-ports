package com.oplus.systemui.separate;

import android.content.IntentFilter;
import android.os.UserHandle;
import android.view.View;
import com.android.systemui.broadcast.BroadcastDispatcher;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSPrivacyManager$attachStateChangeListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ OplusSeparateQSPrivacyManager this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        BroadcastDispatcher.registerReceiver$default(this.this$0.broadcastDispatcher, this.this$0.safetyCenterReceiver, new IntentFilter("android.safetycenter.action.SAFETY_CENTER_ENABLED_CHANGED"), this.this$0.backgroundExecutor, (UserHandle) null, 0, 56);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.this$0.broadcastDispatcher.unregisterReceiver(this.this$0.safetyCenterReceiver);
    }
}
