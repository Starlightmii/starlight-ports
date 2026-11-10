package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.mechanics.haptics.SegmentHaptics$SpringTension$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicStackRequest {
    public float bottomBorder;
    private List<? extends ExpandableView> expandableViews;
    private StackPhase phase;
    public float topBorder;

    public DynamicStackRequest(StackPhase stackPhase, List<? extends ExpandableView> list, float f, float f2) {
        this.phase = stackPhase;
        this.expandableViews = list;
        this.bottomBorder = f;
        this.topBorder = f2;
    }

    public final StackPhase component1() {
        return this.phase;
    }

    public final List<ExpandableView> component2() {
        return this.expandableViews;
    }

    public final DynamicStackRequest copy(StackPhase stackPhase, List<? extends ExpandableView> list, float f, float f2) {
        return new DynamicStackRequest(stackPhase, list, f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicStackRequest)) {
            return false;
        }
        DynamicStackRequest dynamicStackRequest = (DynamicStackRequest) obj;
        return this.phase == dynamicStackRequest.phase && Intrinsics.areEqual(this.expandableViews, dynamicStackRequest.expandableViews) && Float.compare(this.bottomBorder, dynamicStackRequest.bottomBorder) == 0 && Float.compare(this.topBorder, dynamicStackRequest.topBorder) == 0;
    }

    public final float getBottomBorder() {
        return this.bottomBorder;
    }

    public final List<ExpandableView> getExpandableViews() {
        return this.expandableViews;
    }

    public final StackPhase getPhase() {
        return this.phase;
    }

    public final float getTopBorder() {
        return this.topBorder;
    }

    public final int hashCode() {
        return Float.hashCode(this.topBorder) + ChangeSize$.ExternalSyntheticOutline0.m(this.bottomBorder, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.phase.hashCode() * 31, 31, this.expandableViews), 31);
    }

    public final void setBottomBorder(float f) {
        this.bottomBorder = f;
    }

    public final void setExpandableViews(List<? extends ExpandableView> list) {
        this.expandableViews = list;
    }

    public final void setPhase(StackPhase stackPhase) {
        this.phase = stackPhase;
    }

    public final void setTopBorder(float f) {
        this.topBorder = f;
    }

    public String toString() {
        StackPhase stackPhase = this.phase;
        List<? extends ExpandableView> list = this.expandableViews;
        float f = this.bottomBorder;
        float f2 = this.topBorder;
        StringBuilder sb = new StringBuilder("DynamicStackRequest(phase=");
        sb.append(stackPhase);
        sb.append(", expandableViews=");
        sb.append(list);
        sb.append(", bottomBorder=");
        return SegmentHaptics$SpringTension$.ExternalSyntheticOutline0.m(sb, f, ", topBorder=", f2, ")");
    }
}
