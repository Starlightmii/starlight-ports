package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.exifinterface.media.ExifInterface$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryIconColor {
    public final int backgroundColor;
    public final int foregroundColor;
    public final int singleToneColor;

    public BatteryIconColor(int i, int i2, int i3) {
        this.singleToneColor = i;
        this.foregroundColor = i2;
        this.backgroundColor = i3;
    }

    public final BatteryIconColor copy(int i, int i2, int i3) {
        return new BatteryIconColor(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryIconColor)) {
            return false;
        }
        BatteryIconColor batteryIconColor = (BatteryIconColor) obj;
        return this.singleToneColor == batteryIconColor.singleToneColor && this.foregroundColor == batteryIconColor.foregroundColor && this.backgroundColor == batteryIconColor.backgroundColor;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    public final int getSingleToneColor() {
        return this.singleToneColor;
    }

    public final int hashCode() {
        return Integer.hashCode(this.backgroundColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.foregroundColor, Integer.hashCode(this.singleToneColor) * 31, 31);
    }

    public String toString() {
        String hexString = Integer.toHexString(this.singleToneColor);
        String hexString2 = Integer.toHexString(this.foregroundColor);
        return DebugInfo$.ExternalSyntheticOutline0.m(ExifInterface$.ExternalSyntheticOutline1.m("BatteryIconColor(singleToneColor=", hexString, ", foregroundColor=", hexString2, ", backgroundColor="), Integer.toHexString(this.backgroundColor), ")");
    }
}
