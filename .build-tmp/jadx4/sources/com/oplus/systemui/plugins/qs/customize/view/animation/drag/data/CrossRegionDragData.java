package com.oplus.systemui.plugins.qs.customize.view.animation.drag.data;

import com.oplus.systemui.plugins.qs.customize.view.animation.drag.CopyViewProxy;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.viewholder.GridData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CrossRegionDragData {
    private CopyViewProxy copyViewProxy;
    private IntentTarget currentDragIntent;
    private String insertSpecInOtherArea;
    public boolean isRegionFirstCrossed;
    private Loc locInPersonalArea;
    private Loc originLoc;
    private GridData swapDataInPersonalArea;

    public CrossRegionDragData(boolean z, IntentTarget intentTarget, Loc loc, Loc loc2, GridData gridData, String str, CopyViewProxy copyViewProxy) {
        this.isRegionFirstCrossed = z;
        this.currentDragIntent = intentTarget;
        this.originLoc = loc;
        this.locInPersonalArea = loc2;
        this.swapDataInPersonalArea = gridData;
        this.insertSpecInOtherArea = str;
        this.copyViewProxy = copyViewProxy;
    }

    public final IntentTarget component2() {
        return this.currentDragIntent;
    }

    public final Loc component3() {
        return this.originLoc;
    }

    public final Loc component4() {
        return this.locInPersonalArea;
    }

    public final GridData component5() {
        return this.swapDataInPersonalArea;
    }

    public final String component6() {
        return this.insertSpecInOtherArea;
    }

    public final CopyViewProxy component7() {
        return this.copyViewProxy;
    }

    public final CrossRegionDragData copy(boolean z, IntentTarget intentTarget, Loc loc, Loc loc2, GridData gridData, String str, CopyViewProxy copyViewProxy) {
        return new CrossRegionDragData(z, intentTarget, loc, loc2, gridData, str, copyViewProxy);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CrossRegionDragData)) {
            return false;
        }
        CrossRegionDragData crossRegionDragData = (CrossRegionDragData) obj;
        return this.isRegionFirstCrossed == crossRegionDragData.isRegionFirstCrossed && this.currentDragIntent == crossRegionDragData.currentDragIntent && Intrinsics.areEqual(this.originLoc, crossRegionDragData.originLoc) && Intrinsics.areEqual(this.locInPersonalArea, crossRegionDragData.locInPersonalArea) && Intrinsics.areEqual(this.swapDataInPersonalArea, crossRegionDragData.swapDataInPersonalArea) && Intrinsics.areEqual(this.insertSpecInOtherArea, crossRegionDragData.insertSpecInOtherArea) && Intrinsics.areEqual(this.copyViewProxy, crossRegionDragData.copyViewProxy);
    }

    public final CopyViewProxy getCopyViewProxy() {
        return this.copyViewProxy;
    }

    public final IntentTarget getCurrentDragIntent() {
        return this.currentDragIntent;
    }

    public final String getInsertSpecInOtherArea() {
        return this.insertSpecInOtherArea;
    }

    public final Loc getLocInPersonalArea() {
        return this.locInPersonalArea;
    }

    public final Loc getOriginLoc() {
        return this.originLoc;
    }

    public final GridData getSwapDataInPersonalArea() {
        return this.swapDataInPersonalArea;
    }

    public final int hashCode() {
        int iHashCode = (this.currentDragIntent.hashCode() + (Boolean.hashCode(this.isRegionFirstCrossed) * 31)) * 31;
        Loc loc = this.originLoc;
        int iHashCode2 = (iHashCode + (loc == null ? 0 : loc.hashCode())) * 31;
        Loc loc2 = this.locInPersonalArea;
        int iHashCode3 = (iHashCode2 + (loc2 == null ? 0 : loc2.hashCode())) * 31;
        GridData gridData = this.swapDataInPersonalArea;
        int iHashCode4 = (iHashCode3 + (gridData == null ? 0 : gridData.hashCode())) * 31;
        String str = this.insertSpecInOtherArea;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        CopyViewProxy copyViewProxy = this.copyViewProxy;
        return iHashCode5 + (copyViewProxy != null ? copyViewProxy.hashCode() : 0);
    }

    public final void setCopyViewProxy(CopyViewProxy copyViewProxy) {
        this.copyViewProxy = copyViewProxy;
    }

    public final void setCurrentDragIntent(IntentTarget intentTarget) {
        this.currentDragIntent = intentTarget;
    }

    public final void setInsertSpecInOtherArea(String str) {
        this.insertSpecInOtherArea = str;
    }

    public final void setLocInPersonalArea(Loc loc) {
        this.locInPersonalArea = loc;
    }

    public final void setOriginLoc(Loc loc) {
        this.originLoc = loc;
    }

    public final void setRegionFirstCrossed(boolean z) {
        this.isRegionFirstCrossed = z;
    }

    public final void setSwapDataInPersonalArea(GridData gridData) {
        this.swapDataInPersonalArea = gridData;
    }

    public String toString() {
        return "CrossRegionDragData(isRegionFirstCrossed=" + this.isRegionFirstCrossed + ", currentDragIntent=" + this.currentDragIntent + ", originLoc=" + this.originLoc + ", locInPersonalArea=" + this.locInPersonalArea + ", swapDataInPersonalArea=" + this.swapDataInPersonalArea + ", insertSpecInOtherArea=" + this.insertSpecInOtherArea + ", copyViewProxy=" + this.copyViewProxy + ")";
    }
}
