package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model;

import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconModel {
    public static final Companion Companion = new Companion();
    private final String iconDesc;
    private final IconSource iconSource;
    public final int priority;
    public final boolean shouldAlert;
    private final String slot;
    public final long tipDuration;
    private final String tipText;
    public final int type;
    public final long updateTime;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DynamicIconModel(int i, int i2, String str, long j, boolean z, IconSource iconSource, String str2, String str3, long j2) {
        this.type = i;
        this.priority = i2;
        this.tipText = str;
        this.tipDuration = j;
        this.shouldAlert = z;
        this.iconSource = iconSource;
        this.slot = str2;
        this.iconDesc = str3;
        this.updateTime = j2;
    }

    public static /* synthetic */ DynamicIconModel copy$default(DynamicIconModel dynamicIconModel, boolean z, long j, int i) {
        int i2 = dynamicIconModel.type;
        int i3 = dynamicIconModel.priority;
        String str = dynamicIconModel.tipText;
        long j2 = dynamicIconModel.tipDuration;
        if ((i & 16) != 0) {
            z = dynamicIconModel.shouldAlert;
        }
        return dynamicIconModel.copy(i2, i3, str, j2, z, dynamicIconModel.iconSource, dynamicIconModel.slot, dynamicIconModel.iconDesc, j);
    }

    public final String component3() {
        return this.tipText;
    }

    public final IconSource component6() {
        return this.iconSource;
    }

    public final String component7() {
        return this.slot;
    }

    public final String component8() {
        return this.iconDesc;
    }

    public final DynamicIconModel copy(int i, int i2, String str, long j, boolean z, IconSource iconSource, String str2, String str3, long j2) {
        return new DynamicIconModel(i, i2, str, j, z, iconSource, str2, str3, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicIconModel)) {
            return false;
        }
        DynamicIconModel dynamicIconModel = (DynamicIconModel) obj;
        return this.type == dynamicIconModel.type && this.priority == dynamicIconModel.priority && Intrinsics.areEqual(this.tipText, dynamicIconModel.tipText) && this.tipDuration == dynamicIconModel.tipDuration && this.shouldAlert == dynamicIconModel.shouldAlert && Intrinsics.areEqual(this.iconSource, dynamicIconModel.iconSource) && Intrinsics.areEqual(this.slot, dynamicIconModel.slot) && Intrinsics.areEqual(this.iconDesc, dynamicIconModel.iconDesc) && this.updateTime == dynamicIconModel.updateTime;
    }

    public final String getIconDesc() {
        return this.iconDesc;
    }

    public final IconSource getIconSource() {
        return this.iconSource;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final boolean getShouldAlert() {
        return this.shouldAlert;
    }

    public final String getSlot() {
        return this.slot;
    }

    public final long getTipDuration() {
        return this.tipDuration;
    }

    public final String getTipText() {
        return this.tipText;
    }

    public final int getType() {
        return this.type;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public final int hashCode() {
        return Long.hashCode(this.updateTime) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((this.iconSource.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.priority, Integer.hashCode(this.type) * 31, 31), 31, this.tipText), 31, this.tipDuration), 31, this.shouldAlert)) * 31, 31, this.slot), 31, this.iconDesc);
    }

    public String toString() {
        String str = this.tipText;
        IconSource iconSource = this.iconSource;
        String str2 = this.slot;
        String str3 = this.iconDesc;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("DynamicIconModel(type=", this.type, this.priority, ", priority=", ", tipText=");
        sbM.append(str);
        sbM.append(", tipDuration=");
        sbM.append(this.tipDuration);
        sbM.append(", shouldAlert=");
        sbM.append(this.shouldAlert);
        sbM.append(", iconSource=");
        sbM.append(iconSource);
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, ", slot=", str2, ", iconDesc=", str3);
        sbM.append(", updateTime=");
        sbM.append(this.updateTime);
        sbM.append(")");
        return sbM.toString();
    }

    public /* synthetic */ DynamicIconModel(int i, int i2, String str, long j, IconSource iconSource, String str2, String str3) {
        this(i, i2, str, j, true, iconSource, str2, str3, System.currentTimeMillis());
    }
}
