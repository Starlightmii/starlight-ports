package com.oplus.systemui.seedlingservice.flashlight;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IconData {
    private final String icon;
    private final String shape;
    private final String size;

    public IconData(String str, String str2, String str3) {
        this.icon = str;
        this.size = str2;
        this.shape = str3;
    }

    public static /* synthetic */ IconData copy$default(IconData iconData, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = iconData.icon;
        }
        if ((i & 2) != 0) {
            str2 = iconData.size;
        }
        if ((i & 4) != 0) {
            str3 = iconData.shape;
        }
        return iconData.copy(str, str2, str3);
    }

    public final String component1() {
        return this.icon;
    }

    public final String component2() {
        return this.size;
    }

    public final String component3() {
        return this.shape;
    }

    public final IconData copy(String str, String str2, String str3) {
        return new IconData(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IconData)) {
            return false;
        }
        IconData iconData = (IconData) obj;
        return Intrinsics.areEqual(this.icon, iconData.icon) && Intrinsics.areEqual(this.size, iconData.size) && Intrinsics.areEqual(this.shape, iconData.shape);
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getShape() {
        return this.shape;
    }

    public final String getSize() {
        return this.size;
    }

    public int hashCode() {
        return this.shape.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.icon.hashCode() * 31, 31, this.size);
    }

    public String toString() {
        String str = this.icon;
        String str2 = this.size;
        return DebugInfo$.ExternalSyntheticOutline0.m(ExifInterface$.ExternalSyntheticOutline1.m("IconData(icon=", str, ", size=", str2, ", shape="), this.shape, ")");
    }
}
