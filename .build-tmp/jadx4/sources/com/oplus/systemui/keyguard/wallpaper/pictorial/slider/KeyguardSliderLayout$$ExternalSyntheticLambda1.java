package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardSliderLayout$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ KeyguardSliderLayout f$0;

    public /* synthetic */ KeyguardSliderLayout$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardSliderLayout keyguardSliderLayout = this.f$0;
        switch (i) {
            case 0:
                ((ArrayList) keyguardSliderLayout.mSlidePagers).forEach(new KeyguardSliderLayout$$ExternalSyntheticLambda6(0));
                keyguardSliderLayout.setVisibility(8);
                break;
            default:
                Method method = KeyguardSliderLayout.sSetMeasuredDimensionRawMethod;
                keyguardSliderLayout.updateSliderPagers(1);
                KeyguardSliderLayout.KeyguardSliderPageTransformer keyguardSliderPageTransformer = keyguardSliderLayout.mTransformer;
                if (keyguardSliderPageTransformer != null) {
                    PictorialPreloadView pictorialPreloadView = keyguardSliderLayout.mPictorialPreloadViewOnRight;
                    if (pictorialPreloadView != null) {
                        keyguardSliderPageTransformer.transformView(1.0f, pictorialPreloadView);
                    }
                    PictorialPreloadView pictorialPreloadView2 = keyguardSliderLayout.mPictorialPreloadViewOnLeft;
                    if (pictorialPreloadView2 != null) {
                        keyguardSliderLayout.mTransformer.transformView(-1.0f, pictorialPreloadView2);
                    }
                }
                break;
        }
    }
}
