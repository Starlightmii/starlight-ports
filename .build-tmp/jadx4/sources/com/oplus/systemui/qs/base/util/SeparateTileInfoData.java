package com.oplus.systemui.qs.base.util;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintSet$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateTileInfoData {
    private String qsTilesCount;
    public int qsTilesDotsCount;
    private String qsTilesSpec;

    public SeparateTileInfoData(String str, int i, String str2) {
        this.qsTilesCount = str;
        this.qsTilesDotsCount = i;
        this.qsTilesSpec = str2;
    }

    public final String component1() {
        return this.qsTilesCount;
    }

    public final String component3() {
        return this.qsTilesSpec;
    }

    public final SeparateTileInfoData copy(String str, int i, String str2) {
        return new SeparateTileInfoData(str, i, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeparateTileInfoData)) {
            return false;
        }
        SeparateTileInfoData separateTileInfoData = (SeparateTileInfoData) obj;
        return Intrinsics.areEqual(this.qsTilesCount, separateTileInfoData.qsTilesCount) && this.qsTilesDotsCount == separateTileInfoData.qsTilesDotsCount && Intrinsics.areEqual(this.qsTilesSpec, separateTileInfoData.qsTilesSpec);
    }

    public final String getQsTilesCount() {
        return this.qsTilesCount;
    }

    public final int getQsTilesDotsCount() {
        return this.qsTilesDotsCount;
    }

    public final String getQsTilesSpec() {
        return this.qsTilesSpec;
    }

    public final int hashCode() {
        return this.qsTilesSpec.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.qsTilesDotsCount, this.qsTilesCount.hashCode() * 31, 31);
    }

    public final void setQsTilesCount(String str) {
        this.qsTilesCount = str;
    }

    public final void setQsTilesDotsCount(int i) {
        this.qsTilesDotsCount = i;
    }

    public final void setQsTilesSpec(String str) {
        this.qsTilesSpec = str;
    }

    public String toString() {
        String str = this.qsTilesCount;
        int i = this.qsTilesDotsCount;
        return DebugInfo$.ExternalSyntheticOutline0.m(ConstraintSet$.ExternalSyntheticOutline0.m(i, "SeparateTileInfoData(qsTilesCount=", str, ", qsTilesDotsCount=", ", qsTilesSpec="), this.qsTilesSpec, ")");
    }
}
