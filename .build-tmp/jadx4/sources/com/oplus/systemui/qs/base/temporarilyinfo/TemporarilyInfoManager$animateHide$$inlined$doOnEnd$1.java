package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.Animator;
import android.view.ViewGroup;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public /* synthetic */ Function1 $onEnd$inlined;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ TemporarilyInfoView $targetHide$inlined;
    public /* synthetic */ TemporarilyInfoManager this$0;

    public /* synthetic */ TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(int i) {
        this.$r8$classId = i;
    }

    private final void onAnimationCancel$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationCancel$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateShow$$inlined$doOnEnd$2(Animator animator) {
    }

    private final void onAnimationRepeat$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateShow$$inlined$doOnEnd$2(Animator animator) {
    }

    private final void onAnimationStart$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateHide$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$oplus$systemui$qs$base$temporarilyinfo$TemporarilyInfoManager$animateShow$$inlined$doOnEnd$2(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                LogUtil.i$default(LogUtil.TAG_QS, "TemporarilyInfoManager", "[animateHide] " + this.$targetHide$inlined + " end", null, 8);
                ViewGroup statusBarContainerView = this.this$0.getStatusBarContainerView();
                if (statusBarContainerView != null) {
                    statusBarContainerView.setTransitionAlpha(1.0f);
                }
                ViewGroup qsQuickEntranceContainerView = this.this$0.getQsQuickEntranceContainerView();
                if (qsQuickEntranceContainerView != null) {
                    qsQuickEntranceContainerView.setTransitionAlpha(1.0f);
                }
                ViewGroup privacyContainerView = this.this$0.getPrivacyContainerView();
                if (privacyContainerView != null) {
                    privacyContainerView.setTransitionAlpha(1.0f);
                }
                this.this$0.isShowing = false;
                TemporarilyInfoManager.resetTemporarilyInfoView(this.$targetHide$inlined);
                ((TemporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0) this.$onEnd$inlined).invoke(animator);
                break;
            default:
                LogUtil.i$default(LogUtil.TAG_QS, "TemporarilyInfoManager", "[animateShow] " + this.$targetHide$inlined + " end", null, 8);
                TemporarilyInfoManager temporarilyInfoManager = this.this$0;
                temporarilyInfoManager.fullShownTextView = TemporarilyInfoManager.access$getNextTemporarilyInfoView(temporarilyInfoManager, this.$targetHide$inlined);
                ((TemporarilyInfoManager$$ExternalSyntheticLambda4) this.$onEnd$inlined).invoke(animator);
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.$r8$classId;
    }
}
