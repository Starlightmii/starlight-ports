package com.oplus.systemui.panelanimation;

import com.android.internal.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LevelPositionAlgorithmImpl$fractionAnimation$1$1 implements DynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ LevelPositionAlgorithmImpl this$0;

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
        LevelPositionAlgorithmImpl levelPositionAlgorithmImpl = this.this$0;
        if (levelPositionAlgorithmImpl.inTracking) {
            levelPositionAlgorithmImpl.currentFraction = f;
        }
    }
}
