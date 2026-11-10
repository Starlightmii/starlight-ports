package com.oplus.systemui.qs.base.seek;

import com.airbnb.lottie.LottieAnimationView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeSliderController$updateVolumeIconContentDescription$1 implements Runnable {
    public /* synthetic */ String $contentDescription;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsVolumeSliderController this$0;

    public /* synthetic */ OplusQsVolumeSliderController$updateVolumeIconContentDescription$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LottieAnimationView lottieAnimationView = this.this$0.volumeIconView;
                if (lottieAnimationView != null) {
                    lottieAnimationView.setContentDescription(this.$contentDescription);
                }
                break;
            default:
                ((OplusQsBaseToggleSliderLayout) this.this$0.mView).setSliderContentDescription(this.$contentDescription);
                break;
        }
    }
}
