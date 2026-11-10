package com.oplus.systemui.statusbar.notification.fullscreenbanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.notification.framerate.BoostFrameRateManager;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBanner;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FullScreenBannerBoostFrameRateRateHandler {
    public boolean isBoostFrameRate;
    private final FullScreenBanner view;

    public FullScreenBannerBoostFrameRateRateHandler(FullScreenBanner fullScreenBanner) {
        this.view = fullScreenBanner;
    }

    public final void boostFrameRate(Animator animator) {
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.notification.fullscreenbanner.FullScreenBannerBoostFrameRateRateHandler.boostFrameRate.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator2) {
                FullScreenBannerBoostFrameRateRateHandler.this.setBoostFrameRate(false);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                FullScreenBannerBoostFrameRateRateHandler.this.setBoostFrameRate(false);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                FullScreenBannerBoostFrameRateRateHandler.this.setBoostFrameRate(true);
            }
        });
    }

    public final FullScreenBanner getView() {
        return this.view;
    }

    public final void setBoostFrameRate(boolean z) {
        if (this.isBoostFrameRate != z) {
            this.isBoostFrameRate = z;
            ((BoostFrameRateManager) DependencyEx.sDependency.getDependency(BoostFrameRateManager.class)).boostFrameRate(this.view, this.isBoostFrameRate);
        }
    }
}
