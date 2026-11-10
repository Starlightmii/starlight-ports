package com.oplus.systemui.seedlingservice.flashlight;

import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LabelButton {
    private final String bgColor;
    private final String desc;
    private final String intent;
    private final String state;
    private final String style;
    private final String text;
    private final String textColor;
    private final int textSize;
    private final String type;

    public /* synthetic */ LabelButton(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "TEXT_LABEL" : str2, (i2 & 4) != 0 ? "BUTTON" : str3, str4, str5, str6, (i2 & 64) != 0 ? "" : str7, (i2 & 128) != 0 ? "" : str8, (i2 & 256) != 0 ? 0 : i);
    }

    public static /* synthetic */ LabelButton copy$default(LabelButton labelButton, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = labelButton.desc;
        }
        if ((i2 & 2) != 0) {
            str2 = labelButton.type;
        }
        if ((i2 & 4) != 0) {
            str3 = labelButton.style;
        }
        if ((i2 & 8) != 0) {
            str4 = labelButton.text;
        }
        if ((i2 & 16) != 0) {
            str5 = labelButton.state;
        }
        if ((i2 & 32) != 0) {
            str6 = labelButton.intent;
        }
        if ((i2 & 64) != 0) {
            str7 = labelButton.bgColor;
        }
        if ((i2 & 128) != 0) {
            str8 = labelButton.textColor;
        }
        if ((i2 & 256) != 0) {
            i = labelButton.textSize;
        }
        String str9 = str8;
        int i3 = i;
        String str10 = str6;
        String str11 = str7;
        String str12 = str5;
        String str13 = str3;
        return labelButton.copy(str, str2, str13, str4, str12, str10, str11, str9, i3);
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
        return this.text;
    }

    public final String component5() {
        return this.state;
    }

    public final String component6() {
        return this.intent;
    }

    public final String component7() {
        return this.bgColor;
    }

    public final String component8() {
        return this.textColor;
    }

    public final int component9() {
        return this.textSize;
    }

    public final LabelButton copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        return new LabelButton(str, str2, str3, str4, str5, str6, str7, str8, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LabelButton)) {
            return false;
        }
        LabelButton labelButton = (LabelButton) obj;
        return Intrinsics.areEqual(this.desc, labelButton.desc) && Intrinsics.areEqual(this.type, labelButton.type) && Intrinsics.areEqual(this.style, labelButton.style) && Intrinsics.areEqual(this.text, labelButton.text) && Intrinsics.areEqual(this.state, labelButton.state) && Intrinsics.areEqual(this.intent, labelButton.intent) && Intrinsics.areEqual(this.bgColor, labelButton.bgColor) && Intrinsics.areEqual(this.textColor, labelButton.textColor) && this.textSize == labelButton.textSize;
    }

    public final String getBgColor() {
        return this.bgColor;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final String getState() {
        return this.state;
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
        return Integer.hashCode(this.textSize) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.desc.hashCode() * 31, 31, this.type), 31, this.style), 31, this.text), 31, this.state), 31, this.intent), 31, this.bgColor), 31, this.textColor);
    }

    public String toString() {
        String str = this.desc;
        String str2 = this.type;
        String str3 = this.style;
        String str4 = this.text;
        String str5 = this.state;
        String str6 = this.intent;
        String str7 = this.bgColor;
        String str8 = this.textColor;
        int i = this.textSize;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("LabelButton(desc=", str, ", type=", str2, ", style=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str3, ", text=", str4, ", state=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str5, ", intent=", str6, ", bgColor=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str7, ", textColor=", str8, ", textSize=");
        return SurfaceContainer$.ExternalSyntheticOutline0.m(i, ")", sbM);
    }

    public LabelButton(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        this.desc = str;
        this.type = str2;
        this.style = str3;
        this.text = str4;
        this.state = str5;
        this.intent = str6;
        this.bgColor = str7;
        this.textColor = str8;
        this.textSize = i;
    }
}
