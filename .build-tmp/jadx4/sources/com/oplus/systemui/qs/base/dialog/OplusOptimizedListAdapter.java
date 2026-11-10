package com.oplus.systemui.qs.base.dialog;

import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusOptimizedListAdapter extends ListAdapter {
    private final AtomicReference<List<Object>> mCacheList;
    private final AtomicReference<Runnable> mCachedCallBack;
    private final AtomicBoolean mCanSubmit;
    private final AtomicBoolean mNeedRefresh;

    public OplusOptimizedListAdapter(AsyncDifferConfig asyncDifferConfig) {
        ListAdapter.1 r0 = new ListAdapter.1(this);
        AsyncListDiffer asyncListDiffer = new AsyncListDiffer(new AdapterListUpdateCallback(this), asyncDifferConfig);
        ((ListAdapter) this).mDiffer = asyncListDiffer;
        ((CopyOnWriteArrayList) asyncListDiffer.mListeners).add(r0);
        this.mCacheList = new AtomicReference<>();
        this.mCachedCallBack = new AtomicReference<>();
        this.mNeedRefresh = new AtomicBoolean(false);
        this.mCanSubmit = new AtomicBoolean(true);
    }

    public final void internalSubmitList(List list, Runnable runnable) {
        if (!this.mCanSubmit.compareAndSet(true, false)) {
            this.mNeedRefresh.set(true);
            this.mCacheList.set(list);
            this.mCachedCallBack.set(runnable);
        } else {
            OplusOptimizedListAdapter$internalSubmitList$internalCallback$1 oplusOptimizedListAdapter$internalSubmitList$internalCallback$1 = new OplusOptimizedListAdapter$internalSubmitList$internalCallback$1();
            oplusOptimizedListAdapter$internalSubmitList$internalCallback$1.this$0 = this;
            oplusOptimizedListAdapter$internalSubmitList$internalCallback$1.$commitCallback = runnable;
            VarHandle.storeStoreFence();
            ((ListAdapter) this).mDiffer.submitList(list, oplusOptimizedListAdapter$internalSubmitList$internalCallback$1);
        }
    }

    public void submitList(List<Object> list) {
        internalSubmitList(list, null);
    }

    public void submitList(List<Object> list, Runnable runnable) {
        internalSubmitList(list, runnable);
    }

    public OplusOptimizedListAdapter(DiffUtil.ItemCallback itemCallback) {
        super(itemCallback);
        this.mCacheList = new AtomicReference<>();
        this.mCachedCallBack = new AtomicReference<>();
        this.mNeedRefresh = new AtomicBoolean(false);
        this.mCanSubmit = new AtomicBoolean(true);
    }
}
