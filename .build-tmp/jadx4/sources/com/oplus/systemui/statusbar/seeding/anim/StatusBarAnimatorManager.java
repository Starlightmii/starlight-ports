package com.oplus.systemui.statusbar.seeding.anim;

import android.animation.Animator;
import android.content.Context;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarAnimatorManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatusBarAnimatorManager";
    private final StatusBarCoordAnimator animatorProvider;
    private Animator runningAnimator;
    private Long runningAnimatorId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusBarAnimatorManager(Context context, IStatusBarViewProvider iStatusBarViewProvider) {
        this.animatorProvider = new StatusBarCoordAnimator(context, iStatusBarViewProvider);
    }

    public final void cancelAnimation(StatusBarAnimationData statusBarAnimationData) {
    }

    public final void endAnimation(StatusBarAnimationData statusBarAnimationData) {
    }

    public final void prepareAnimation(StatusBarAnimationData statusBarAnimationData) {
        Animator animator = this.runningAnimator;
        if (animator != null) {
            if (!animator.isRunning()) {
                animator = null;
            }
            if (animator != null) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "prepareAnimation: force end running animator");
                animator.cancel();
            }
        }
        this.runningAnimatorId = Long.valueOf(statusBarAnimationData.getId());
        this.runningAnimator = this.animatorProvider.createFromAnimationData(statusBarAnimationData);
    }

    public final void startAnimation(StatusBarAnimationData statusBarAnimationData) {
        long id = statusBarAnimationData.getId();
        Long l = this.runningAnimatorId;
        if (l == null || id != l.longValue()) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "startAnimation: id not match");
            return;
        }
        Animator animator = this.runningAnimator;
        if (animator != null) {
            animator.start();
        }
    }
}
