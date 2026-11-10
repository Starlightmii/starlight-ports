package com.oplus.systemui.usb.utils;

import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintSet$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusUsbData {
    public int icon;
    private String name;
    public int type;

    public OplusUsbData(String str, int i, int i2) {
        this.name = str;
        this.icon = i;
        this.type = i2;
    }

    public final String component1() {
        return this.name;
    }

    public final OplusUsbData copy(String str, int i, int i2) {
        return new OplusUsbData(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusUsbData)) {
            return false;
        }
        OplusUsbData oplusUsbData = (OplusUsbData) obj;
        return Intrinsics.areEqual(this.name, oplusUsbData.name) && this.icon == oplusUsbData.icon && this.type == oplusUsbData.type;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final String getName() {
        return this.name;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        String str = this.name;
        return Integer.hashCode(this.type) + MonoSpline$.ExternalSyntheticOutline0.m(this.icon, (str == null ? 0 : str.hashCode()) * 31, 31);
    }

    public final void setIcon(int i) {
        this.icon = i;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return ConstraintSet$.ExternalSyntheticOutline0.m(this.type, "OplusUsbData{name='", this.name, "', type=", "}");
    }
}
