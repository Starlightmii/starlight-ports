package com.oplus.systemui.qs.base.util;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateEditTitleData {
    private String areaAfter;
    private String areaBefore;
    private String sizeAfter;
    private String sizeBefore;
    private String type;

    public SeparateEditTitleData(String str, String str2, String str3, String str4, String str5) {
        this.type = str;
        this.sizeBefore = str2;
        this.sizeAfter = str3;
        this.areaBefore = str4;
        this.areaAfter = str5;
    }

    public final String component1() {
        return this.type;
    }

    public final String component2() {
        return this.sizeBefore;
    }

    public final String component3() {
        return this.sizeAfter;
    }

    public final String component4() {
        return this.areaBefore;
    }

    public final String component5() {
        return this.areaAfter;
    }

    public final SeparateEditTitleData copy(String str, String str2, String str3, String str4, String str5) {
        return new SeparateEditTitleData(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeparateEditTitleData)) {
            return false;
        }
        SeparateEditTitleData separateEditTitleData = (SeparateEditTitleData) obj;
        return Intrinsics.areEqual(this.type, separateEditTitleData.type) && Intrinsics.areEqual(this.sizeBefore, separateEditTitleData.sizeBefore) && Intrinsics.areEqual(this.sizeAfter, separateEditTitleData.sizeAfter) && Intrinsics.areEqual(this.areaBefore, separateEditTitleData.areaBefore) && Intrinsics.areEqual(this.areaAfter, separateEditTitleData.areaAfter);
    }

    public final String getAreaAfter() {
        return this.areaAfter;
    }

    public final String getAreaBefore() {
        return this.areaBefore;
    }

    public final String getSizeAfter() {
        return this.sizeAfter;
    }

    public final String getSizeBefore() {
        return this.sizeBefore;
    }

    public final String getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.areaAfter.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.type.hashCode() * 31, 31, this.sizeBefore), 31, this.sizeAfter), 31, this.areaBefore);
    }

    public final void resetData() {
        this.type = "";
        this.sizeBefore = "";
        this.sizeAfter = "";
        this.areaBefore = "";
        this.areaAfter = "";
    }

    public final void setAreaAfter(String str) {
        this.areaAfter = str;
    }

    public final void setAreaBefore(String str) {
        this.areaBefore = str;
    }

    public final void setSizeAfter(String str) {
        this.sizeAfter = str;
    }

    public final void setSizeBefore(String str) {
        this.sizeBefore = str;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public String toString() {
        String str = this.type;
        String str2 = this.sizeBefore;
        String str3 = this.sizeAfter;
        String str4 = this.areaBefore;
        String str5 = this.areaAfter;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("SeparateEditTitleData(type=", str, ", sizeBefore=", str2, ", sizeAfter=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str3, ", areaBefore=", str4, ", areaAfter=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sbM, str5, ")");
    }
}
