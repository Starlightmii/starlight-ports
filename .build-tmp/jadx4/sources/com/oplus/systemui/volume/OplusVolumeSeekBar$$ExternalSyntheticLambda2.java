package com.oplus.systemui.volume;

import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeSeekBar$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusVolumeSeekBar f$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        COUIVerticalSeekBar cOUIVerticalSeekBar = this.f$0;
        switch (i) {
            case 0:
                Interpolator interpolator = OplusVolumeSeekBar.VOLUME_SCALE_WIDTH_INTERPOLATOR;
                ((Integer) valueAnimator.getAnimatedValue()).getClass();
                cOUIVerticalSeekBar.invalidate();
                break;
            case 1:
                Interpolator interpolator2 = OplusVolumeSeekBar.VOLUME_SCALE_WIDTH_INTERPOLATOR;
                cOUIVerticalSeekBar.getClass();
                ((Float) valueAnimator.getAnimatedValue()).getClass();
                cOUIVerticalSeekBar.invalidate();
                break;
            default:
                Interpolator interpolator3 = OplusVolumeSeekBar.VOLUME_SCALE_WIDTH_INTERPOLATOR;
                ((Float) valueAnimator.getAnimatedValue()).getClass();
                cOUIVerticalSeekBar.invalidate();
                break;
        }
    }
}
