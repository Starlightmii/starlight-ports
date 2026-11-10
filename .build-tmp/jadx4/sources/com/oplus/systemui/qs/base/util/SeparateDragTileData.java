package com.oplus.systemui.qs.base.util;

import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateDragTileData {
    public boolean endAreaIsA;
    private String qsEndDragLoc;
    private String qsStartDragLoc;
    private String qsStartInvalidDragLoc;
    public boolean startAreaIsA;

    public SeparateDragTileData(String str, String str2, String str3, boolean z, boolean z2) {
        this.qsStartInvalidDragLoc = str;
        this.qsStartDragLoc = str2;
        this.qsEndDragLoc = str3;
        this.startAreaIsA = z;
        this.endAreaIsA = z2;
    }

    public final String component1() {
        return this.qsStartInvalidDragLoc;
    }

    public final String component2() {
        return this.qsStartDragLoc;
    }

    public final String component3() {
        return this.qsEndDragLoc;
    }

    public final SeparateDragTileData copy(String str, String str2, String str3, boolean z, boolean z2) {
        return new SeparateDragTileData(str, str2, str3, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeparateDragTileData)) {
            return false;
        }
        SeparateDragTileData separateDragTileData = (SeparateDragTileData) obj;
        return Intrinsics.areEqual(this.qsStartInvalidDragLoc, separateDragTileData.qsStartInvalidDragLoc) && Intrinsics.areEqual(this.qsStartDragLoc, separateDragTileData.qsStartDragLoc) && Intrinsics.areEqual(this.qsEndDragLoc, separateDragTileData.qsEndDragLoc) && this.startAreaIsA == separateDragTileData.startAreaIsA && this.endAreaIsA == separateDragTileData.endAreaIsA;
    }

    public final boolean getEndAreaIsA() {
        return this.endAreaIsA;
    }

    public final String getQsEndDragLoc() {
        return this.qsEndDragLoc;
    }

    public final String getQsStartDragLoc() {
        return this.qsStartDragLoc;
    }

    public final String getQsStartInvalidDragLoc() {
        return this.qsStartInvalidDragLoc;
    }

    public final boolean getStartAreaIsA() {
        return this.startAreaIsA;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.endAreaIsA) + ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.qsStartInvalidDragLoc.hashCode() * 31, 31, this.qsStartDragLoc), 31, this.qsEndDragLoc), 31, this.startAreaIsA);
    }

    public final void setEndAreaIsA(boolean z) {
        this.endAreaIsA = z;
    }

    public final void setQsEndDragLoc(String str) {
        this.qsEndDragLoc = str;
    }

    public final void setQsStartDragLoc(String str) {
        this.qsStartDragLoc = str;
    }

    public final void setQsStartInvalidDragLoc(String str) {
        this.qsStartInvalidDragLoc = str;
    }

    public final void setStartAreaIsA(boolean z) {
        this.startAreaIsA = z;
    }

    public String toString() {
        String str = this.qsStartInvalidDragLoc;
        String str2 = this.qsStartDragLoc;
        String str3 = this.qsEndDragLoc;
        boolean z = this.startAreaIsA;
        boolean z2 = this.endAreaIsA;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("SeparateDragTileData(qsStartInvalidDragLoc=", str, ", qsStartDragLoc=", str2, ", qsEndDragLoc=");
        DeviceSettingItem$.ExternalSyntheticOutline0.m(str3, ", startAreaIsA=", ", endAreaIsA=", sbM, z);
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, z2);
    }
}
