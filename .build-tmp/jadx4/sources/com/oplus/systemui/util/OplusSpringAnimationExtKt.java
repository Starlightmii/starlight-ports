package com.oplus.systemui.util;

import android.view.Choreographer;
import com.android.internal.dynamicanimation.animation.SpringAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusSpringAnimationExtKt {
    public static final void resetTo(SpringAnimation springAnimation, float f) {
        springAnimation.setStartValue(f);
        springAnimation.animateToFinalPosition(f);
        springAnimation.skipToEnd();
        springAnimation.doAnimationFrame((Choreographer.getInstance().getLastFrameTimeNanos() == Long.MIN_VALUE ? Choreographer.getInstance().getLastFrameTimeNanos() : System.nanoTime()) / 1000000);
        springAnimation.doAnimationFrame(0L);
    }
}
