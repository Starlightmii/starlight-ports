package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.os.LocaleList;
import androidx.car.app.SurfaceContainer$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryIconStyle {
    public final int batteryOutChargeBgMode;
    public final int densityDpi;
    public final int frameColorMode;
    public final int iconStyle;
    public final boolean isPercentOutBgMode;
    public final boolean isPercentOutPbMode;
    public final boolean isThemeModeColor;
    private final LocaleList localeList;
    public final boolean oldStylePercentIn;
    public final float scaleFactor;
    public final int themeId;

    public BatteryIconStyle(int i, boolean z, LocaleList localeList, int i2, float f, int i3, boolean z2, int i4, boolean z3, boolean z4, int i5) {
        this.iconStyle = i;
        this.oldStylePercentIn = z;
        this.localeList = localeList;
        this.densityDpi = i2;
        this.scaleFactor = f;
        this.themeId = i3;
        this.isThemeModeColor = z2;
        this.frameColorMode = i4;
        this.isPercentOutBgMode = z3;
        this.isPercentOutPbMode = z4;
        this.batteryOutChargeBgMode = i5;
    }

    public final LocaleList component3() {
        return this.localeList;
    }

    public final BatteryIconStyle copy(int i, boolean z, LocaleList localeList, int i2, float f, int i3, boolean z2, int i4, boolean z3, boolean z4, int i5) {
        return new BatteryIconStyle(i, z, localeList, i2, f, i3, z2, i4, z3, z4, i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryIconStyle)) {
            return false;
        }
        BatteryIconStyle batteryIconStyle = (BatteryIconStyle) obj;
        return this.iconStyle == batteryIconStyle.iconStyle && this.oldStylePercentIn == batteryIconStyle.oldStylePercentIn && Intrinsics.areEqual(this.localeList, batteryIconStyle.localeList) && this.densityDpi == batteryIconStyle.densityDpi && Float.compare(this.scaleFactor, batteryIconStyle.scaleFactor) == 0 && this.themeId == batteryIconStyle.themeId && this.isThemeModeColor == batteryIconStyle.isThemeModeColor && this.frameColorMode == batteryIconStyle.frameColorMode && this.isPercentOutBgMode == batteryIconStyle.isPercentOutBgMode && this.isPercentOutPbMode == batteryIconStyle.isPercentOutPbMode && this.batteryOutChargeBgMode == batteryIconStyle.batteryOutChargeBgMode;
    }

    public final int getBatteryOutChargeBgMode() {
        return this.batteryOutChargeBgMode;
    }

    public final int getDensityDpi() {
        return this.densityDpi;
    }

    public final int getFrameColorMode() {
        return this.frameColorMode;
    }

    public final int getIconStyle() {
        return this.iconStyle;
    }

    public final LocaleList getLocaleList() {
        return this.localeList;
    }

    public final boolean getOldStylePercentIn() {
        return this.oldStylePercentIn;
    }

    public final float getScaleFactor() {
        return this.scaleFactor;
    }

    public final int getThemeId() {
        return this.themeId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.batteryOutChargeBgMode) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.frameColorMode, ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.themeId, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleFactor, MonoSpline$.ExternalSyntheticOutline0.m(this.densityDpi, (this.localeList.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(Integer.hashCode(this.iconStyle) * 31, 31, this.oldStylePercentIn)) * 31, 31), 31), 31), 31, this.isThemeModeColor), 31), 31, this.isPercentOutBgMode), 31, this.isPercentOutPbMode);
    }

    public String toString() {
        LocaleList localeList = this.localeList;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("BatteryIconStyle(iconStyle=", this.iconStyle, ", oldStylePercentIn=", ", localeList=", this.oldStylePercentIn);
        sbM.append(localeList);
        sbM.append(", densityDpi=");
        sbM.append(this.densityDpi);
        sbM.append(", scaleFactor=");
        ChildHelper$.ExternalSyntheticOutline0.m(this.scaleFactor, this.themeId, ", themeId=", ", isThemeModeColor=", sbM);
        PromptIconState$.ExternalSyntheticOutline0.m(this.frameColorMode, ", frameColorMode=", ", isPercentOutBgMode=", sbM, this.isThemeModeColor);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isPercentOutPbMode=", ", batteryOutChargeBgMode=", sbM, this.isPercentOutBgMode, this.isPercentOutPbMode);
        return SurfaceContainer$.ExternalSyntheticOutline0.m(this.batteryOutChargeBgMode, ")", sbM);
    }
}
