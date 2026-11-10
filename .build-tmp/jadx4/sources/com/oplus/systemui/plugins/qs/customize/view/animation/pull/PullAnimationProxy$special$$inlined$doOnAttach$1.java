package com.oplus.systemui.plugins.qs.customize.view.animation.pull;

import android.view.View;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PullAnimationProxy$special$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ COUIRecyclerView $this_doOnAttach;
    public /* synthetic */ PullAnimationProxy this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
        PullAnimationProxy pullAnimationProxy = this.this$0;
        RecyclerView.LayoutManager layoutManager = pullAnimationProxy.recyclerView.getLayoutManager();
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        if (staggeredPagerLayoutManager != null) {
            pullAnimationProxy.customLayoutManager = staggeredPagerLayoutManager;
        } else {
            Path$.ExternalSyntheticBUOutline0.m("recyclerview layoutManager is not StaggeredPagerLayoutManager");
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
