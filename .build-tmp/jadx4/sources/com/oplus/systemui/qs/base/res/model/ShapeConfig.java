package com.oplus.systemui.qs.base.res.model;

import android.content.Context;
import android.graphics.Path;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShapeConfig {
    public final boolean circleShape;
    private final Path path;
    public final int radiusRes;
    public final float stdHeight;
    public final float stdWidth;
    public final boolean supportSmoothRound;

    public /* synthetic */ ShapeConfig(Path path, int i, boolean z, int i2) {
        this(path, i, 132.0f, 132.0f, (i2 & 16) != 0 ? false : z, (i2 & 32) == 0);
    }

    public final Path component1() {
        return this.path;
    }

    public final ShapeConfig copy(Path path, int i, float f, float f2, boolean z, boolean z2) {
        return new ShapeConfig(path, i, f, f2, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShapeConfig)) {
            return false;
        }
        ShapeConfig shapeConfig = (ShapeConfig) obj;
        return Intrinsics.areEqual(this.path, shapeConfig.path) && this.radiusRes == shapeConfig.radiusRes && Float.compare(this.stdWidth, shapeConfig.stdWidth) == 0 && Float.compare(this.stdHeight, shapeConfig.stdHeight) == 0 && this.circleShape == shapeConfig.circleShape && this.supportSmoothRound == shapeConfig.supportSmoothRound;
    }

    public final boolean getCircleShape() {
        return this.circleShape;
    }

    public final Path getPath() {
        return this.path;
    }

    public final float getRadius(Context context) {
        return context.getResources().getDimension(this.radiusRes);
    }

    public final int getRadiusRes() {
        return this.radiusRes;
    }

    public final float getStdHeight() {
        return this.stdHeight;
    }

    public final float getStdWidth() {
        return this.stdWidth;
    }

    public final boolean getSupportSmoothRound() {
        return this.supportSmoothRound;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.supportSmoothRound) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.stdHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.stdWidth, MonoSpline$.ExternalSyntheticOutline0.m(this.radiusRes, this.path.hashCode() * 31, 31), 31), 31), 31, this.circleShape);
    }

    public String toString() {
        Path path = this.path;
        StringBuilder sb = new StringBuilder("ShapeConfig(path=");
        sb.append(path);
        sb.append(", radiusRes=");
        sb.append(this.radiusRes);
        sb.append(", stdWidth=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.stdWidth, ", stdHeight=", this.stdHeight, ", circleShape=");
        return DynamicRouteAttributes$.ExternalSyntheticOutline0.m(", supportSmoothRound=", ")", sb, this.circleShape, this.supportSmoothRound);
    }

    public ShapeConfig(Path path, int i, float f, float f2, boolean z, boolean z2) {
        this.path = path;
        this.radiusRes = i;
        this.stdWidth = f;
        this.stdHeight = f2;
        this.circleShape = z;
        this.supportSmoothRound = z2;
    }
}
