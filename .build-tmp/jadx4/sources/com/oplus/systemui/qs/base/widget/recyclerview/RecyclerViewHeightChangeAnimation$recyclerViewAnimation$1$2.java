package com.oplus.systemui.qs.base.widget.recyclerview;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ RecyclerViewHeightChangeAnimation this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        this.this$0.recyclerView.setTransitionAlpha(1.0f);
        LogUtil.i$default(LogUtil.TAG_QS, "OplusItemShowHideAnimator", "recyclerViewAnimation onAnimationEnd, value=" + f, null, 8);
    }
}
