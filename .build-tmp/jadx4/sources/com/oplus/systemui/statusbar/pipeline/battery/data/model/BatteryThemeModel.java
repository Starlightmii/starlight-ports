package com.oplus.systemui.statusbar.pipeline.battery.data.model;

import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.biometrics.ui.PromptIconState$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryThemeModel {
    public final int batteryOutChargeBgMode;
    public final int batteryThemeVersion;
    public final int frameColorMode;
    public final boolean isThirdTheme;
    public final int themeChanged;
    public final int themeMode;
    public final int themeModeUntil14;
    public final int themePercentOutBgMode;
    public final int themePercentOutProgressMode;
    public final int uiMode;

    public BatteryThemeModel(int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.themeChanged = i;
        this.uiMode = i2;
        this.isThirdTheme = z;
        this.themeMode = i3;
        this.frameColorMode = i4;
        this.themeModeUntil14 = i5;
        this.batteryThemeVersion = i6;
        this.themePercentOutBgMode = i7;
        this.themePercentOutProgressMode = i8;
        this.batteryOutChargeBgMode = i9;
    }

    public final BatteryThemeModel copy(int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        return new BatteryThemeModel(i, i2, z, i3, i4, i5, i6, i7, i8, i9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryThemeModel)) {
            return false;
        }
        BatteryThemeModel batteryThemeModel = (BatteryThemeModel) obj;
        return this.themeChanged == batteryThemeModel.themeChanged && this.uiMode == batteryThemeModel.uiMode && this.isThirdTheme == batteryThemeModel.isThirdTheme && this.themeMode == batteryThemeModel.themeMode && this.frameColorMode == batteryThemeModel.frameColorMode && this.themeModeUntil14 == batteryThemeModel.themeModeUntil14 && this.batteryThemeVersion == batteryThemeModel.batteryThemeVersion && this.themePercentOutBgMode == batteryThemeModel.themePercentOutBgMode && this.themePercentOutProgressMode == batteryThemeModel.themePercentOutProgressMode && this.batteryOutChargeBgMode == batteryThemeModel.batteryOutChargeBgMode;
    }

    public final int getBatteryOutChargeBgMode() {
        return this.batteryOutChargeBgMode;
    }

    public final int getBatteryThemeVersion() {
        return this.batteryThemeVersion;
    }

    public final int getFrameColorMode() {
        return this.frameColorMode;
    }

    public final int getThemeChanged() {
        return this.themeChanged;
    }

    public final int getThemeMode() {
        return this.themeMode;
    }

    public final int getThemeModeUntil14() {
        return this.themeModeUntil14;
    }

    public final int getThemePercentOutBgMode() {
        return this.themePercentOutBgMode;
    }

    public final int getThemePercentOutProgressMode() {
        return this.themePercentOutProgressMode;
    }

    public final int getUiMode() {
        return this.uiMode;
    }

    public final int hashCode() {
        return Integer.hashCode(this.batteryOutChargeBgMode) + MonoSpline$.ExternalSyntheticOutline0.m(this.themePercentOutProgressMode, MonoSpline$.ExternalSyntheticOutline0.m(this.themePercentOutBgMode, MonoSpline$.ExternalSyntheticOutline0.m(this.batteryThemeVersion, MonoSpline$.ExternalSyntheticOutline0.m(this.themeModeUntil14, MonoSpline$.ExternalSyntheticOutline0.m(this.frameColorMode, MonoSpline$.ExternalSyntheticOutline0.m(this.themeMode, ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.uiMode, Integer.hashCode(this.themeChanged) * 31, 31), 31, this.isThirdTheme), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("BatteryThemeModel(themeChanged=", this.themeChanged, this.uiMode, ", uiMode=", ", isThirdTheme=");
        PromptIconState$.ExternalSyntheticOutline0.m(this.themeMode, ", themeMode=", ", frameColorMode=", sbM, this.isThirdTheme);
        RectList$.ExternalSyntheticOutline0.m(this.frameColorMode, this.themeModeUntil14, ", themeModeUntil14=", ", batteryThemeVersion=", sbM);
        RectList$.ExternalSyntheticOutline0.m(this.batteryThemeVersion, this.themePercentOutBgMode, ", themePercentOutBgMode=", ", themePercentOutProgressMode=", sbM);
        return TimerSpan$.ExternalSyntheticOutline0.m(this.themePercentOutProgressMode, this.batteryOutChargeBgMode, ", batteryOutChargeBgMode=", ")", sbM);
    }
}
