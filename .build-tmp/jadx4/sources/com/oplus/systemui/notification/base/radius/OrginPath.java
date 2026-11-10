package com.oplus.systemui.notification.base.radius;

import android.graphics.Path;
import android.graphics.RectF;
import com.oplus.graphics.IOplusPath;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OrginPath implements IOplusPath {
    private final Path mPath;

    public OrginPath(Path path) {
        this.mPath = path;
    }

    public void addSmoothRoundRect(RectF rectF, float f, float f2, float f3, Path.Direction direction) {
        addSmoothRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, f3, direction);
    }

    public void addSmoothRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Path.Direction direction) {
        this.mPath.addRoundRect(f, f2, f3, f4, f5, f6, direction);
    }

    public void addSmoothRoundRect(RectF rectF, float f, float f2, Path.Direction direction) {
        addSmoothRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, direction);
    }

    public void addSmoothRoundRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, Path.Direction direction) {
        this.mPath.addRoundRect(f, f2, f3, f4, OplusSmoothRoundedManager.reverseNoWeightRadius(f5, f7), OplusSmoothRoundedManager.reverseNoWeightRadius(f6, f7), direction);
    }

    public void addSmoothRoundRect(RectF rectF, float[] fArr, Path.Direction direction, float f) {
        this.mPath.addRoundRect(rectF, OplusSmoothRoundedManager.reverseNoWeightRadius(fArr, f), direction);
    }
}
