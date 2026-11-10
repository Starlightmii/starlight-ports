package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.os.LocaleList;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusLockIconView$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PercentOutIcon {
    public final int batteryLevel;
    public final boolean isVisible;
    private final LocaleList localeList;
    public final int tintColor;

    public PercentOutIcon(boolean z, int i, int i2, LocaleList localeList) {
        this.isVisible = z;
        this.batteryLevel = i;
        this.tintColor = i2;
        this.localeList = localeList;
    }

    public final LocaleList component4() {
        return this.localeList;
    }

    public final PercentOutIcon copy(boolean z, int i, int i2, LocaleList localeList) {
        return new PercentOutIcon(z, i, i2, localeList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PercentOutIcon)) {
            return false;
        }
        PercentOutIcon percentOutIcon = (PercentOutIcon) obj;
        return this.isVisible == percentOutIcon.isVisible && this.batteryLevel == percentOutIcon.batteryLevel && this.tintColor == percentOutIcon.tintColor && Intrinsics.areEqual(this.localeList, percentOutIcon.localeList);
    }

    public final int getBatteryLevel() {
        return this.batteryLevel;
    }

    public final LocaleList getLocaleList() {
        return this.localeList;
    }

    public final int getTintColor() {
        return this.tintColor;
    }

    public final int hashCode() {
        return this.localeList.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.tintColor, MonoSpline$.ExternalSyntheticOutline0.m(this.batteryLevel, Boolean.hashCode(this.isVisible) * 31, 31), 31);
    }

    public String toString() {
        String hexString = Integer.toHexString(this.tintColor);
        LocaleList localeList = this.localeList;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("isVisible:", this.batteryLevel, " batteryLevel:", " tintColor:", this.isVisible);
        sbM.append(hexString);
        sbM.append(" localeList:");
        sbM.append(localeList);
        return sbM.toString();
    }
}
