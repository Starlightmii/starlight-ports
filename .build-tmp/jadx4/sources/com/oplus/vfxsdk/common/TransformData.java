package com.oplus.vfxsdk.common;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TransformData {
    private final String halign;
    private final Float[] layout;
    private final String[] layout_expr;
    private final Float[] margin;
    private final Float[] padding;
    private final String positionType;
    private final Float[] rotate;
    private final Float[] scale;
    private final String valign;

    public /* synthetic */ TransformData(Float[] fArr, String[] strArr, Float[] fArr2, Float[] fArr3, Float[] fArr4, Float[] fArr5, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, strArr, fArr2, fArr3, (i & 16) != 0 ? null : fArr4, (i & 32) != 0 ? null : fArr5, (i & 64) != 0 ? null : str, (i & 128) != 0 ? null : str2, (i & 256) != 0 ? null : str3);
    }

    public static /* synthetic */ TransformData copy$default(TransformData transformData, Float[] fArr, String[] strArr, Float[] fArr2, Float[] fArr3, Float[] fArr4, Float[] fArr5, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            fArr = transformData.layout;
        }
        if ((i & 2) != 0) {
            strArr = transformData.layout_expr;
        }
        if ((i & 4) != 0) {
            fArr2 = transformData.scale;
        }
        if ((i & 8) != 0) {
            fArr3 = transformData.rotate;
        }
        if ((i & 16) != 0) {
            fArr4 = transformData.margin;
        }
        if ((i & 32) != 0) {
            fArr5 = transformData.padding;
        }
        if ((i & 64) != 0) {
            str = transformData.positionType;
        }
        if ((i & 128) != 0) {
            str2 = transformData.valign;
        }
        if ((i & 256) != 0) {
            str3 = transformData.halign;
        }
        String str4 = str2;
        String str5 = str3;
        Float[] fArr6 = fArr5;
        String str6 = str;
        Float[] fArr7 = fArr4;
        Float[] fArr8 = fArr2;
        return transformData.copy(fArr, strArr, fArr8, fArr3, fArr7, fArr6, str6, str4, str5);
    }

    public final Float[] component1() {
        return this.layout;
    }

    public final String[] component2() {
        return this.layout_expr;
    }

    public final Float[] component3() {
        return this.scale;
    }

    public final Float[] component4() {
        return this.rotate;
    }

    public final Float[] component5() {
        return this.margin;
    }

    public final Float[] component6() {
        return this.padding;
    }

    public final String component7() {
        return this.positionType;
    }

    public final String component8() {
        return this.valign;
    }

    public final String component9() {
        return this.halign;
    }

    public final TransformData copy(Float[] fArr, String[] strArr, Float[] fArr2, Float[] fArr3, Float[] fArr4, Float[] fArr5, String str, String str2, String str3) {
        return new TransformData(fArr, strArr, fArr2, fArr3, fArr4, fArr5, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformData)) {
            return false;
        }
        TransformData transformData = (TransformData) obj;
        return Intrinsics.areEqual(this.layout, transformData.layout) && Intrinsics.areEqual(this.layout_expr, transformData.layout_expr) && Intrinsics.areEqual(this.scale, transformData.scale) && Intrinsics.areEqual(this.rotate, transformData.rotate) && Intrinsics.areEqual(this.margin, transformData.margin) && Intrinsics.areEqual(this.padding, transformData.padding) && Intrinsics.areEqual(this.positionType, transformData.positionType) && Intrinsics.areEqual(this.valign, transformData.valign) && Intrinsics.areEqual(this.halign, transformData.halign);
    }

    public final String getHalign() {
        return this.halign;
    }

    public final Float[] getLayout() {
        return this.layout;
    }

    public final String[] getLayout_expr() {
        return this.layout_expr;
    }

    public final Float[] getMargin() {
        return this.margin;
    }

    public final Float[] getPadding() {
        return this.padding;
    }

    public final String getPositionType() {
        return this.positionType;
    }

    public final Float[] getRotate() {
        return this.rotate;
    }

    public final Float[] getScale() {
        return this.scale;
    }

    public final String getValign() {
        return this.valign;
    }

    public int hashCode() {
        int iHashCode = Arrays.hashCode(this.layout) * 31;
        String[] strArr = this.layout_expr;
        int iHashCode2 = (((((iHashCode + (strArr == null ? 0 : Arrays.hashCode(strArr))) * 31) + Arrays.hashCode(this.scale)) * 31) + Arrays.hashCode(this.rotate)) * 31;
        Float[] fArr = this.margin;
        int iHashCode3 = (iHashCode2 + (fArr == null ? 0 : Arrays.hashCode(fArr))) * 31;
        Float[] fArr2 = this.padding;
        int iHashCode4 = (iHashCode3 + (fArr2 == null ? 0 : Arrays.hashCode(fArr2))) * 31;
        String str = this.positionType;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.valign;
        int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.halign;
        return iHashCode6 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        String string = Arrays.toString(this.layout);
        String string2 = Arrays.toString(this.layout_expr);
        String string3 = Arrays.toString(this.scale);
        String string4 = Arrays.toString(this.rotate);
        String string5 = Arrays.toString(this.margin);
        String string6 = Arrays.toString(this.padding);
        String str = this.positionType;
        String str2 = this.valign;
        String str3 = this.halign;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("TransformData(layout=", string, ", layout_expr=", string2, ", scale=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, string3, ", rotate=", string4, ", margin=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, string5, ", padding=", string6, ", positionType=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str, ", valign=", str2, ", halign=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sbM, str3, ")");
    }

    public TransformData(Float[] fArr, String[] strArr, Float[] fArr2, Float[] fArr3, Float[] fArr4, Float[] fArr5, String str, String str2, String str3) {
        this.layout = fArr;
        this.layout_expr = strArr;
        this.scale = fArr2;
        this.rotate = fArr3;
        this.margin = fArr4;
        this.padding = fArr5;
        this.positionType = str;
        this.valign = str2;
        this.halign = str3;
    }
}
