package com.oplus.systemui.seedlingservice.flashlight;

import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LabelText {
    private final String bgColor;
    private final String desc;
    private final String style;
    private final String text;
    private final String textColor;
    private final int textSize;
    private final String type;

    public /* synthetic */ LabelText(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "Text" : str2, (i2 & 4) != 0 ? "Text" : str3, (i2 & 8) != 0 ? FlashlightLiveAlertCard.COLOR_00000000 : str4, str5, str6, i);
    }

    public static /* synthetic */ LabelText copy$default(LabelText labelText, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = labelText.desc;
        }
        if ((i2 & 2) != 0) {
            str2 = labelText.type;
        }
        if ((i2 & 4) != 0) {
            str3 = labelText.style;
        }
        if ((i2 & 8) != 0) {
            str4 = labelText.bgColor;
        }
        if ((i2 & 16) != 0) {
            str5 = labelText.text;
        }
        if ((i2 & 32) != 0) {
            str6 = labelText.textColor;
        }
        if ((i2 & 64) != 0) {
            i = labelText.textSize;
        }
        String str7 = str6;
        int i3 = i;
        String str8 = str5;
        String str9 = str3;
        return labelText.copy(str, str2, str9, str4, str8, str7, i3);
    }

    public final String component1() {
        return this.desc;
    }

    public final String component2() {
        return this.type;
    }

    public final String component3() {
        return this.style;
    }

    public final String component4() {
        return this.bgColor;
    }

    public final String component5() {
        return this.text;
    }

    public final String component6() {
        return this.textColor;
    }

    public final int component7() {
        return this.textSize;
    }

    public final LabelText copy(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        return new LabelText(str, str2, str3, str4, str5, str6, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LabelText)) {
            return false;
        }
        LabelText labelText = (LabelText) obj;
        return Intrinsics.areEqual(this.desc, labelText.desc) && Intrinsics.areEqual(this.type, labelText.type) && Intrinsics.areEqual(this.style, labelText.style) && Intrinsics.areEqual(this.bgColor, labelText.bgColor) && Intrinsics.areEqual(this.text, labelText.text) && Intrinsics.areEqual(this.textColor, labelText.textColor) && this.textSize == labelText.textSize;
    }

    public final String getBgColor() {
        return this.bgColor;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getStyle() {
        return this.style;
    }

    public final String getText() {
        return this.text;
    }

    public final String getTextColor() {
        return this.textColor;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return Integer.hashCode(this.textSize) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.desc.hashCode() * 31, 31, this.type), 31, this.style), 31, this.bgColor), 31, this.text), 31, this.textColor);
    }

    public String toString() {
        String str = this.desc;
        String str2 = this.type;
        String str3 = this.style;
        String str4 = this.bgColor;
        String str5 = this.text;
        String str6 = this.textColor;
        int i = this.textSize;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("LabelText(desc=", str, ", type=", str2, ", style=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str3, ", bgColor=", str4, ", text=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str5, ", textColor=", str6, ", textSize=");
        return SurfaceContainer$.ExternalSyntheticOutline0.m(i, ")", sbM);
    }

    public LabelText(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.desc = str;
        this.type = str2;
        this.style = str3;
        this.bgColor = str4;
        this.text = str5;
        this.textColor = str6;
        this.textSize = i;
    }
}
