package com.oplus.systemui.notification.lockscreen.capsule.stroke;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IStrokeAnimator {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Interpolator BEZIER_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);

        public final Interpolator getBEZIER_INTERPOLATOR() {
            return BEZIER_INTERPOLATOR;
        }
    }

    float getProgress();

    float getStartAngle();

    void setProgress(float f);

    void setStartAngle(float f);
}
