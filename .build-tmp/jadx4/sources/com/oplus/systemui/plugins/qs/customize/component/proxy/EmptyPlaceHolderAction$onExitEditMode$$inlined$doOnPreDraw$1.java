package com.oplus.systemui.plugins.qs.customize.component.proxy;

import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ boolean $withAnimation$inlined;
    public /* synthetic */ EmptyPlaceHolderAction this$0;

    public /* synthetic */ EmptyPlaceHolderAction$onExitEditMode$$inlined$doOnPreDraw$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.this$0.layoutManager;
                (staggeredPagerLayoutManager != null ? staggeredPagerLayoutManager : null).enablePlaceHolder(false, this.$withAnimation$inlined);
                break;
            default:
                StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = this.this$0.layoutManager;
                (staggeredPagerLayoutManager2 != null ? staggeredPagerLayoutManager2 : null).enablePlaceHolder(true, this.$withAnimation$inlined);
                break;
        }
    }
}
