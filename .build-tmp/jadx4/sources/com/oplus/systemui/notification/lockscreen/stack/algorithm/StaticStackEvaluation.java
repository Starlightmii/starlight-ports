package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import androidx.compose.animation.ChangeSize$;
import com.android.systemui.shade.NotificationPanelViewController$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticStackEvaluation {
    public boolean canStaticStack;
    private StaticStackDebugMetrics debugMetrics;
    public float gapBetweenSeedlingAndNotif;

    public StaticStackEvaluation(boolean z, float f, StaticStackDebugMetrics staticStackDebugMetrics) {
        this.canStaticStack = z;
        this.gapBetweenSeedlingAndNotif = f;
        this.debugMetrics = staticStackDebugMetrics;
    }

    public final StaticStackDebugMetrics component3() {
        return this.debugMetrics;
    }

    public final StaticStackEvaluation copy(boolean z, float f, StaticStackDebugMetrics staticStackDebugMetrics) {
        return new StaticStackEvaluation(z, f, staticStackDebugMetrics);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticStackEvaluation)) {
            return false;
        }
        StaticStackEvaluation staticStackEvaluation = (StaticStackEvaluation) obj;
        return this.canStaticStack == staticStackEvaluation.canStaticStack && Float.compare(this.gapBetweenSeedlingAndNotif, staticStackEvaluation.gapBetweenSeedlingAndNotif) == 0 && Intrinsics.areEqual(this.debugMetrics, staticStackEvaluation.debugMetrics);
    }

    public final boolean getCanStaticStack() {
        return this.canStaticStack;
    }

    public final StaticStackDebugMetrics getDebugMetrics() {
        return this.debugMetrics;
    }

    public final float getGapBetweenSeedlingAndNotif() {
        return this.gapBetweenSeedlingAndNotif;
    }

    public final int hashCode() {
        return this.debugMetrics.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.gapBetweenSeedlingAndNotif, Boolean.hashCode(this.canStaticStack) * 31, 31);
    }

    public final void setCanStaticStack(boolean z) {
        this.canStaticStack = z;
    }

    public final void setDebugMetrics(StaticStackDebugMetrics staticStackDebugMetrics) {
        this.debugMetrics = staticStackDebugMetrics;
    }

    public final void setGapBetweenSeedlingAndNotif(float f) {
        this.gapBetweenSeedlingAndNotif = f;
    }

    public String toString() {
        boolean z = this.canStaticStack;
        float f = this.gapBetweenSeedlingAndNotif;
        StaticStackDebugMetrics staticStackDebugMetrics = this.debugMetrics;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("StaticStackEvaluation(canStaticStack=", z, ", gapBetweenSeedlingAndNotif=", f, ", debugMetrics=");
        sbM.append(staticStackDebugMetrics);
        sbM.append(")");
        return sbM.toString();
    }
}
