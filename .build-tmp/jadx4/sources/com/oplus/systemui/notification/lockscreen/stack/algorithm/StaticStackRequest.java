package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import androidx.car.app.hardware.common.CarZone$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticStackRequest {
    public float bottomBorder;
    public float fingerprintTop;
    private List<? extends ExpandableView> normalStackViews;
    public float notificationDragAmount;
    private StackPhase phase;
    private List<? extends ExpandableView> seedlingViews;
    public float topBorder;

    public StaticStackRequest(StackPhase stackPhase, List<? extends ExpandableView> list, float f, float f2, float f3, float f4, List<? extends ExpandableView> list2) {
        this.phase = stackPhase;
        this.normalStackViews = list;
        this.bottomBorder = f;
        this.notificationDragAmount = f2;
        this.topBorder = f3;
        this.fingerprintTop = f4;
        this.seedlingViews = list2;
    }

    public final StackPhase component1() {
        return this.phase;
    }

    public final List<ExpandableView> component2() {
        return this.normalStackViews;
    }

    public final List<ExpandableView> component7() {
        return this.seedlingViews;
    }

    public final StaticStackRequest copy(StackPhase stackPhase, List<? extends ExpandableView> list, float f, float f2, float f3, float f4, List<? extends ExpandableView> list2) {
        return new StaticStackRequest(stackPhase, list, f, f2, f3, f4, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticStackRequest)) {
            return false;
        }
        StaticStackRequest staticStackRequest = (StaticStackRequest) obj;
        return this.phase == staticStackRequest.phase && Intrinsics.areEqual(this.normalStackViews, staticStackRequest.normalStackViews) && Float.compare(this.bottomBorder, staticStackRequest.bottomBorder) == 0 && Float.compare(this.notificationDragAmount, staticStackRequest.notificationDragAmount) == 0 && Float.compare(this.topBorder, staticStackRequest.topBorder) == 0 && Float.compare(this.fingerprintTop, staticStackRequest.fingerprintTop) == 0 && Intrinsics.areEqual(this.seedlingViews, staticStackRequest.seedlingViews);
    }

    public final float getBottomBorder() {
        return this.bottomBorder;
    }

    public final float getFingerprintTop() {
        return this.fingerprintTop;
    }

    public final List<ExpandableView> getNormalStackViews() {
        return this.normalStackViews;
    }

    public final float getNotificationDragAmount() {
        return this.notificationDragAmount;
    }

    public final StackPhase getPhase() {
        return this.phase;
    }

    public final List<ExpandableView> getSeedlingViews() {
        return this.seedlingViews;
    }

    public final float getTopBorder() {
        return this.topBorder;
    }

    public final int hashCode() {
        int iM = ChangeSize$.ExternalSyntheticOutline0.m(this.fingerprintTop, ChangeSize$.ExternalSyntheticOutline0.m(this.topBorder, ChangeSize$.ExternalSyntheticOutline0.m(this.notificationDragAmount, ChangeSize$.ExternalSyntheticOutline0.m(this.bottomBorder, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.phase.hashCode() * 31, 31, this.normalStackViews), 31), 31), 31), 31);
        List<? extends ExpandableView> list = this.seedlingViews;
        return iM + (list == null ? 0 : list.hashCode());
    }

    public final void setBottomBorder(float f) {
        this.bottomBorder = f;
    }

    public final void setFingerprintTop(float f) {
        this.fingerprintTop = f;
    }

    public final void setNormalStackViews(List<? extends ExpandableView> list) {
        this.normalStackViews = list;
    }

    public final void setNotificationDragAmount(float f) {
        this.notificationDragAmount = f;
    }

    public final void setPhase(StackPhase stackPhase) {
        this.phase = stackPhase;
    }

    public final void setSeedlingViews(List<? extends ExpandableView> list) {
        this.seedlingViews = list;
    }

    public final void setTopBorder(float f) {
        this.topBorder = f;
    }

    public String toString() {
        StackPhase stackPhase = this.phase;
        List<? extends ExpandableView> list = this.normalStackViews;
        float f = this.bottomBorder;
        float f2 = this.notificationDragAmount;
        float f3 = this.topBorder;
        float f4 = this.fingerprintTop;
        List<? extends ExpandableView> list2 = this.seedlingViews;
        StringBuilder sb = new StringBuilder("StaticStackRequest(phase=");
        sb.append(stackPhase);
        sb.append(", normalStackViews=");
        sb.append(list);
        sb.append(", bottomBorder=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f, ", notificationDragAmount=", f2, ", topBorder=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f3, ", fingerprintTop=", f4, ", seedlingViews=");
        return CarZone$.ExternalSyntheticOutline0.m(sb, list2, ")");
    }
}
