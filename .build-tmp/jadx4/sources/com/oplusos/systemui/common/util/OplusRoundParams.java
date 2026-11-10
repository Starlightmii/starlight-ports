package com.oplusos.systemui.common.util;

import androidx.compose.animation.ChangeSize$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRoundParams {
    public final boolean isCircle;
    public float radius;
    public final float radiusDp;
    public float weight;

    public /* synthetic */ OplusRoundParams(float f, float f2, float f3, int i) {
        this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0.0f : f2, (i & 4) != 0 ? 0.0f : f3, (i & 8) == 0);
    }

    public final OplusRoundParams copy(float f, float f2, float f3, boolean z) {
        return new OplusRoundParams(f, f2, f3, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusRoundParams)) {
            return false;
        }
        OplusRoundParams oplusRoundParams = (OplusRoundParams) obj;
        return Float.compare(this.radiusDp, oplusRoundParams.radiusDp) == 0 && Float.compare(this.radius, oplusRoundParams.radius) == 0 && Float.compare(this.weight, oplusRoundParams.weight) == 0 && this.isCircle == oplusRoundParams.isCircle;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final float getRadiusDp$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
        return this.radiusDp;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isCircle) + ChangeSize$.ExternalSyntheticOutline0.m(this.weight, ChangeSize$.ExternalSyntheticOutline0.m(this.radius, Float.hashCode(this.radiusDp) * 31, 31), 31);
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    public final void setWeight(float f) {
        this.weight = f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OplusRoundParams(radiusDp=");
        sb.append(this.radiusDp);
        sb.append(", radius=");
        sb.append(this.radius);
        sb.append(", weight=");
        sb.append(this.weight);
        sb.append(", isCircle=");
        return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.isCircle, ')');
    }

    public OplusRoundParams(float f, float f2, float f3, boolean z) {
        this.radiusDp = f;
        this.radius = f2;
        this.weight = f3;
        this.isCircle = z;
    }
}
