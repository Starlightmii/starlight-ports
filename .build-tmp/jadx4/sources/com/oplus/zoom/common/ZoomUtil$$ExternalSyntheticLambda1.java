package com.oplus.zoom.common;

import android.animation.ValueAnimator;
import com.oplus.wrapper.view.SurfaceControl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomUtil$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ SurfaceControl.Transaction f$0;
    public /* synthetic */ android.view.SurfaceControl f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ android.view.SurfaceControl f$3;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ZoomUtil.lambda$startSnapShotSurfaceAlphaAnim$0(this.f$0, this.f$1, this.f$2, this.f$3, valueAnimator);
    }
}
