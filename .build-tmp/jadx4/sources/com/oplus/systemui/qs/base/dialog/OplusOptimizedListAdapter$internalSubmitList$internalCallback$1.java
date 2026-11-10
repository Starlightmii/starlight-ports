package com.oplus.systemui.qs.base.dialog;

import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusOptimizedListAdapter$internalSubmitList$internalCallback$1 implements Runnable {
    public /* synthetic */ Runnable $commitCallback;
    public /* synthetic */ OplusOptimizedListAdapter this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.mCanSubmit.set(true);
        Runnable runnable = this.$commitCallback;
        if (runnable != null) {
            runnable.run();
        }
        if (this.this$0.mNeedRefresh.compareAndSet(true, false)) {
            OplusOptimizedListAdapter oplusOptimizedListAdapter = this.this$0;
            oplusOptimizedListAdapter.internalSubmitList((List) oplusOptimizedListAdapter.mCacheList.getAndSet(null), (Runnable) this.this$0.mCachedCallBack.getAndSet(null));
        }
    }
}
