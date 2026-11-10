package com.oplus.systemui.statusbar.phone.dynamic;

import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIcon {
    private String desc;
    private final IconSource iconSource;
    public boolean visible;

    public DynamicIcon(IconSource iconSource, String str, boolean z) {
        this.iconSource = iconSource;
        this.desc = str;
        this.visible = z;
    }

    public final IconSource component1() {
        return this.iconSource;
    }

    public final String component2() {
        return this.desc;
    }

    public final DynamicIcon copy(IconSource iconSource, String str, boolean z) {
        return new DynamicIcon(iconSource, str, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicIcon)) {
            return false;
        }
        DynamicIcon dynamicIcon = (DynamicIcon) obj;
        return Intrinsics.areEqual(this.iconSource, dynamicIcon.iconSource) && Intrinsics.areEqual(this.desc, dynamicIcon.desc) && this.visible == dynamicIcon.visible;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final IconSource getIconSource() {
        return this.iconSource;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.visible) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.iconSource.hashCode() * 31, 31, this.desc);
    }

    public final void setDesc(String str) {
        this.desc = str;
    }

    public final void setVisible(boolean z) {
        this.visible = z;
    }

    public String toString() {
        IconSource iconSource = this.iconSource;
        String str = this.desc;
        boolean z = this.visible;
        StringBuilder sb = new StringBuilder("DynamicIcon(iconSource=");
        sb.append(iconSource);
        sb.append(", desc=");
        sb.append(str);
        sb.append(", visible=");
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sb, z);
    }
}
