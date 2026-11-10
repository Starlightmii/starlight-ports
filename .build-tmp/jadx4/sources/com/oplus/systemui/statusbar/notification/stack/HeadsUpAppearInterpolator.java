package com.oplus.systemui.statusbar.notification.stack;

import android.graphics.Path;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpAppearInterpolator extends PathInterpolator {
    public static final float XTOT = 250.0f + 200.0f;

    static {
        new PathInterpolator(0.22f, 0.34f, 0.05f, 1.0f);
        new PathInterpolator(0.4f, 0.0f, 0.4f, 1.0f);
        new PathInterpolator(0.22f, 0.23f, 0.05f, 1.0f);
    }

    public HeadsUpAppearInterpolator() {
        super(getAppearPath());
    }

    private static Path getAppearPath() {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        float f = XTOT;
        path.cubicTo(200.0f / f, 1.125f, 200.0f / f, 1.125f, 250.0f / f, 1.125f);
        path.cubicTo(330.0f / f, 1.125f, 290.0f / f, 1.0f, 1.0f, 1.0f);
        return path;
    }

    public static float getFractionUntilOvershoot() {
        return 250.0f / XTOT;
    }
}
