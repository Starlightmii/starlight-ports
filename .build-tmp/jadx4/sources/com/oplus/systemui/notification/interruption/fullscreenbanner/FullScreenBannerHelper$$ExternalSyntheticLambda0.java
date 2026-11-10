package com.oplus.systemui.notification.interruption.fullscreenbanner;

import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import com.oplusos.systemui.common.util.ThreadManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FullScreenBannerHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ FullScreenBannerHelper f$0;
    public /* synthetic */ Runnable[] f$1;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        FullScreenBannerHelper fullScreenBannerHelper = this.f$0;
        Runnable[] runnableArr = this.f$1;
        Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
        if (valueAnimator.getAnimatedFraction() <= 0.8f || !fullScreenBannerHelper.mShouldDoCallBackInMidAnimation) {
            return;
        }
        if (runnableArr.length != 0) {
            for (Runnable runnable : runnableArr) {
                ThreadManager.getInstance().getThreadHandler().post(runnable);
            }
        }
        fullScreenBannerHelper.mShouldDoCallBackInMidAnimation = false;
    }
}
