package com.oplus.systemui.seedlingservice.flashlight;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Icon {

    @SerializedName("Icon")
    private final IconData iconData;

    public Icon(IconData iconData) {
        this.iconData = iconData;
    }

    public static /* synthetic */ Icon copy$default(Icon icon, IconData iconData, int i, Object obj) {
        if ((i & 1) != 0) {
            iconData = icon.iconData;
        }
        return icon.copy(iconData);
    }

    public final IconData component1() {
        return this.iconData;
    }

    public final Icon copy(IconData iconData) {
        return new Icon(iconData);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Icon) && Intrinsics.areEqual(this.iconData, ((Icon) obj).iconData);
    }

    public final IconData getIconData() {
        return this.iconData;
    }

    public int hashCode() {
        return this.iconData.hashCode();
    }

    public String toString() {
        return "Icon(iconData=" + this.iconData + ")";
    }
}
