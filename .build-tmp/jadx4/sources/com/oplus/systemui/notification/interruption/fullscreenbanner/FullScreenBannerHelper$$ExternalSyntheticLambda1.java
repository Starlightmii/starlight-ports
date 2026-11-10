package com.oplus.systemui.notification.interruption.fullscreenbanner;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBanner;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBannerContainer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FullScreenBannerHelper$$ExternalSyntheticLambda1 implements View.OnTouchListener {
    public /* synthetic */ FullScreenBannerHelper f$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        FullScreenBanner fullScreenBanner;
        FullScreenBannerHelper fullScreenBannerHelper = this.f$0;
        Interpolator interpolator = FullScreenBannerHelper.INTERPOLATOR_BANNER_MOVE;
        if (motionEvent.getAction() == 4 && motionEvent.getX() == 0.0f && motionEvent.getY() == 0.0f && (fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner) != null && fullScreenBanner.mReplyLayout.getVisibility() == 0) {
            fullScreenBannerHelper.closeRemoteInputs();
            fullScreenBannerHelper.mHandler.sendEmptyMessageDelayed(0, 5000L);
        }
        FullScreenBannerContainer fullScreenBannerContainer = fullScreenBannerHelper.mRootView;
        if (fullScreenBannerContainer == null) {
            return false;
        }
        return fullScreenBannerContainer.onTouchEvent(motionEvent);
    }
}
