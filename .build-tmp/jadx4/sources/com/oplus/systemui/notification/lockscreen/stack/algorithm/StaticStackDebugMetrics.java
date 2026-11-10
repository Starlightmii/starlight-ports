package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticStackDebugMetrics {
    public float seedlingNeededHeight;
    public float staticArea;
    public int staticStackCount;
    public float totalStaticNeeded;

    public StaticStackDebugMetrics(float f, float f2, float f3, int i) {
        this.staticArea = f;
        this.seedlingNeededHeight = f2;
        this.totalStaticNeeded = f3;
        this.staticStackCount = i;
    }

    public final StaticStackDebugMetrics copy(float f, float f2, float f3, int i) {
        return new StaticStackDebugMetrics(f, f2, f3, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticStackDebugMetrics)) {
            return false;
        }
        StaticStackDebugMetrics staticStackDebugMetrics = (StaticStackDebugMetrics) obj;
        return Float.compare(this.staticArea, staticStackDebugMetrics.staticArea) == 0 && Float.compare(this.seedlingNeededHeight, staticStackDebugMetrics.seedlingNeededHeight) == 0 && Float.compare(this.totalStaticNeeded, staticStackDebugMetrics.totalStaticNeeded) == 0 && this.staticStackCount == staticStackDebugMetrics.staticStackCount;
    }

    public final float getSeedlingNeededHeight() {
        return this.seedlingNeededHeight;
    }

    public final float getStaticArea() {
        return this.staticArea;
    }

    public final int getStaticStackCount() {
        return this.staticStackCount;
    }

    public final float getTotalStaticNeeded() {
        return this.totalStaticNeeded;
    }

    public final int hashCode() {
        return Integer.hashCode(this.staticStackCount) + ChangeSize$.ExternalSyntheticOutline0.m(this.totalStaticNeeded, ChangeSize$.ExternalSyntheticOutline0.m(this.seedlingNeededHeight, Float.hashCode(this.staticArea) * 31, 31), 31);
    }

    public final void setSeedlingNeededHeight(float f) {
        this.seedlingNeededHeight = f;
    }

    public final void setStaticArea(float f) {
        this.staticArea = f;
    }

    public final void setStaticStackCount(int i) {
        this.staticStackCount = i;
    }

    public final void setTotalStaticNeeded(float f) {
        this.totalStaticNeeded = f;
    }

    public String toString() {
        float f = this.staticArea;
        float f2 = this.seedlingNeededHeight;
        float f3 = this.totalStaticNeeded;
        int i = this.staticStackCount;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("StaticStackDebugMetrics(staticArea=", f, ", seedlingNeededHeight=", f2, ", totalStaticNeeded=");
        sbM.append(f3);
        sbM.append(", staticStackCount=");
        sbM.append(i);
        sbM.append(")");
        return sbM.toString();
    }
}
