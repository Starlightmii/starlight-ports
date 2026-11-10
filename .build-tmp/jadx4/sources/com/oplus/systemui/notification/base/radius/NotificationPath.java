package com.oplus.systemui.notification.base.radius;

import android.graphics.Path;
import android.graphics.RectF;
import com.oplus.graphics.IOplusPath;
import com.oplus.graphics.OplusPath;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPath implements IOplusPath {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPath";
    private final IOplusPath oplusPath;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationPath(Path path) {
        this.oplusPath = (SmoothRoundEx.INSTANCE.getDEVICE_SUPPORT_SMOOTH() && OplusSmoothRoundedManager.isFullSmoothOn()) ? new OplusPath(path) : new OrginPath(path);
    }

    public void addSmoothRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Path.Direction direction) {
        addSmoothRoundRect(f, f2, f3, f4, f5, f6, OplusSmoothRoundedManager.getDefaultG2Weight(), direction);
    }

    public void addSmoothRoundRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, Path.Direction direction) {
        this.oplusPath.addSmoothRoundRect(f, f2, f3, f4, f5, f6, f7, direction);
    }

    public void addSmoothRoundRect(RectF rectF, float f, float f2, Path.Direction direction) {
        addSmoothRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, direction);
    }

    public void addSmoothRoundRect(RectF rectF, float f, float f2, float f3, Path.Direction direction) {
        addSmoothRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, f3, direction);
    }

    public void addSmoothRoundRect(RectF rectF, float[] fArr, Path.Direction direction, float f) {
        this.oplusPath.addSmoothRoundRect(rectF, fArr, direction, f);
    }

    public void addSmoothRoundRect(RectF rectF, float[] fArr, Path.Direction direction) {
        this.oplusPath.addSmoothRoundRect(rectF, fArr, direction);
    }

    public final void addSmoothRoundRect(RectF rectF, float f, float f2) {
        addSmoothRoundRect(rectF, f, f2, SmoothRoundEx.INSTANCE.getDefaultWeight(), Path.Direction.CCW);
    }
}
