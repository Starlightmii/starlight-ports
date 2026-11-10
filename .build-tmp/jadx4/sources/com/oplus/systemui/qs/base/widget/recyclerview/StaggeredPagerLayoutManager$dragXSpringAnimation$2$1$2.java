package com.oplus.systemui.qs.base.widget.recyclerview;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ StaggeredPagerLayoutManager this$0;

    public /* synthetic */ StaggeredPagerLayoutManager$dragXSpringAnimation$2$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.scrollHorizontallyInternal((int) f);
                break;
            default:
                this.this$0.scrollVerticallyInternal((int) f);
                break;
        }
    }
}
