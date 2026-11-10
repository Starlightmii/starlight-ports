package com.oplus.systemui.qs.utils;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocalLongClickHandler$countDownRunnable$1 implements Runnable {
    public /* synthetic */ LocalLongClickHandler this$0;

    @Override // java.lang.Runnable
    public final void run() {
        View.OnLongClickListener longClickListener;
        if (!this.this$0.mIsRelease.compareAndSet(false, true) || (longClickListener = this.this$0.getLongClickListener()) == null) {
            return;
        }
        longClickListener.onLongClick(null);
    }
}
