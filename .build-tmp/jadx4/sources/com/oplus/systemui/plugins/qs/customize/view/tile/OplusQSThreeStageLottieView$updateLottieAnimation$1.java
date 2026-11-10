package com.oplus.systemui.plugins.qs.customize.view.tile;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLottieView$updateLottieAnimation$1 implements LottieOnCompositionLoadedListener {
    public /* synthetic */ String $assetName;
    public /* synthetic */ OplusQSThreeStageLottieView $this_updateLottieAnimation;
    public /* synthetic */ OplusQSThreeStageLottieView this$0;

    public final void onCompositionLoaded(LottieComposition lottieComposition) {
        OplusQSThreeStageLottieView oplusQSThreeStageLottieView = this.this$0;
        oplusQSThreeStageLottieView.curLottieFileName = this.$assetName;
        if (oplusQSThreeStageLottieView.animating) {
            this.$this_updateLottieAnimation.setVisibility(0);
        }
    }
}
