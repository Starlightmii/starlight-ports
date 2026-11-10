package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UpdateData {
    public float earTransX;
    public float height;
    public boolean isPhase1;
    public float p1BlurRadius;
    public float p2BlurRadius;
    public float width;

    public UpdateData(float f, float f2, float f3, float f4, float f5, boolean z) {
        this.width = f;
        this.height = f2;
        this.p1BlurRadius = f3;
        this.p2BlurRadius = f4;
        this.earTransX = f5;
        this.isPhase1 = z;
    }

    public final UpdateData copy(float f, float f2, float f3, float f4, float f5, boolean z) {
        return new UpdateData(f, f2, f3, f4, f5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UpdateData)) {
            return false;
        }
        UpdateData updateData = (UpdateData) obj;
        return Float.compare(this.width, updateData.width) == 0 && Float.compare(this.height, updateData.height) == 0 && Float.compare(this.p1BlurRadius, updateData.p1BlurRadius) == 0 && Float.compare(this.p2BlurRadius, updateData.p2BlurRadius) == 0 && Float.compare(this.earTransX, updateData.earTransX) == 0 && this.isPhase1 == updateData.isPhase1;
    }

    public final float getEarTransX() {
        return this.earTransX;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getP1BlurRadius() {
        return this.p1BlurRadius;
    }

    public final float getP2BlurRadius() {
        return this.p2BlurRadius;
    }

    public final float getWidth() {
        return this.width;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isPhase1) + ChangeSize$.ExternalSyntheticOutline0.m(this.earTransX, ChangeSize$.ExternalSyntheticOutline0.m(this.p2BlurRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.p1BlurRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.height, Float.hashCode(this.width) * 31, 31), 31), 31), 31);
    }

    public final void setEarTransX(float f) {
        this.earTransX = f;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public final void setP1BlurRadius(float f) {
        this.p1BlurRadius = f;
    }

    public final void setP2BlurRadius(float f) {
        this.p2BlurRadius = f;
    }

    public final void setPhase1(boolean z) {
        this.isPhase1 = z;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public String toString() {
        float f = this.width;
        float f2 = this.height;
        float f3 = this.p1BlurRadius;
        float f4 = this.p2BlurRadius;
        float f5 = this.earTransX;
        boolean z = this.isPhase1;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("UpdateData(width=", f, ", height=", f2, ", p1BlurRadius=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, ", p2BlurRadius=", f4, ", earTransX=");
        sbM.append(f5);
        sbM.append(", isPhase1=");
        sbM.append(z);
        sbM.append(")");
        return sbM.toString();
    }
}
