package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.Animator;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$animateShow$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public /* synthetic */ TemporarilyInfoView $localShownTextView$inlined;
    public /* synthetic */ TemporarilyInfoManager this$0;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        TemporarilyInfoView temporarilyInfoView = this.$localShownTextView$inlined;
        if (temporarilyInfoView != null) {
            TemporarilyInfoManager.resetTemporarilyInfoView(temporarilyInfoView);
            LogUtil.i$default(LogUtil.TAG_QS, "TemporarilyInfoManager", "[animateShow] remove " + temporarilyInfoView, null, 8);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
