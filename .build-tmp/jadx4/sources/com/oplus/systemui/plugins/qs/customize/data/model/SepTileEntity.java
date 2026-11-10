package com.oplus.systemui.plugins.qs.customize.data.model;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileEntity {
    private String expandInfo;
    private String expandInfoThree;
    private String expandInfoTwo;
    private Loc tileLoc;
    private SpanSize tileSize;
    private final String tileSpec;
    public final int userId;

    public SepTileEntity(int i, String str, Loc loc, SpanSize spanSize, String str2, String str3, String str4) {
        this.userId = i;
        this.tileSpec = str;
        this.tileLoc = loc;
        this.tileSize = spanSize;
        this.expandInfo = str2;
        this.expandInfoTwo = str3;
        this.expandInfoThree = str4;
    }

    public final String component2() {
        return this.tileSpec;
    }

    public final Loc component3() {
        return this.tileLoc;
    }

    public final SpanSize component4() {
        return this.tileSize;
    }

    public final String component5() {
        return this.expandInfo;
    }

    public final String component6() {
        return this.expandInfoTwo;
    }

    public final String component7() {
        return this.expandInfoThree;
    }

    public final SepTileEntity copy(int i, String str, Loc loc, SpanSize spanSize, String str2, String str3, String str4) {
        return new SepTileEntity(i, str, loc, spanSize, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SepTileEntity)) {
            return false;
        }
        SepTileEntity sepTileEntity = (SepTileEntity) obj;
        return this.userId == sepTileEntity.userId && Intrinsics.areEqual(this.tileSpec, sepTileEntity.tileSpec) && Intrinsics.areEqual(this.tileLoc, sepTileEntity.tileLoc) && Intrinsics.areEqual(this.tileSize, sepTileEntity.tileSize) && Intrinsics.areEqual(this.expandInfo, sepTileEntity.expandInfo) && Intrinsics.areEqual(this.expandInfoTwo, sepTileEntity.expandInfoTwo) && Intrinsics.areEqual(this.expandInfoThree, sepTileEntity.expandInfoThree);
    }

    public final String getExpandInfo() {
        return this.expandInfo;
    }

    public final String getExpandInfoThree() {
        return this.expandInfoThree;
    }

    public final String getExpandInfoTwo() {
        return this.expandInfoTwo;
    }

    public final Loc getTileLoc() {
        return this.tileLoc;
    }

    public final SpanSize getTileSize() {
        return this.tileSize;
    }

    public final String getTileSpec() {
        return this.tileSpec;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int hashCode() {
        int iHashCode = (this.tileSize.hashCode() + ((this.tileLoc.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.userId) * 31, 31, this.tileSpec)) * 31)) * 31;
        String str = this.expandInfo;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.expandInfoTwo;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.expandInfoThree;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setExpandInfo(String str) {
        this.expandInfo = str;
    }

    public final void setExpandInfoThree(String str) {
        this.expandInfoThree = str;
    }

    public final void setExpandInfoTwo(String str) {
        this.expandInfoTwo = str;
    }

    public final void setTileLoc(Loc loc) {
        this.tileLoc = loc;
    }

    public final void setTileSize(SpanSize spanSize) {
        this.tileSize = spanSize;
    }

    public String toString() {
        String str = this.tileSpec;
        Loc loc = this.tileLoc;
        SpanSize spanSize = this.tileSize;
        String str2 = this.expandInfo;
        String str3 = this.expandInfoTwo;
        String str4 = this.expandInfoThree;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(this.userId, "SepTileEntity(userId=", ", tileSpec=", str, ", tileLoc=");
        sbM.append(loc);
        sbM.append(", tileSize=");
        sbM.append(spanSize);
        sbM.append(", expandInfo=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str2, ", expandInfoTwo=", str3, ", expandInfoThree=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sbM, str4, ")");
    }
}
