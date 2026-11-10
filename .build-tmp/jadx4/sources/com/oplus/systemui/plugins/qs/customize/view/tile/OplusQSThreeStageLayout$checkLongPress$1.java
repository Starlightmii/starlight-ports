package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLayout$checkLongPress$1 implements Runnable {
    public /* synthetic */ OplusQSThreeStageLayout this$0;

    @Override // java.lang.Runnable
    public final void run() {
        OplusQSThreeStageLayout oplusQSThreeStageLayout = this.this$0;
        oplusQSThreeStageLayout.isLongPressTriggered = true;
        View.OnLongClickListener onLongClickListener = oplusQSThreeStageLayout.onLongClickListener;
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(this.this$0);
        }
        this.this$0.performHapticFeedback(0);
        ViewParent parent = this.this$0.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
