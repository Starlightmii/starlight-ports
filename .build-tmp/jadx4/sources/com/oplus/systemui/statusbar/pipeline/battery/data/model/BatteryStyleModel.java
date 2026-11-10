package com.oplus.systemui.statusbar.pipeline.battery.data.model;

import android.os.LocaleList;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryStyleModel {
    public static final Companion Companion = new Companion();
    public final boolean capsuleShowing;
    public final int densityDpi;
    public final int iconStyle;
    public final boolean isDisableAnimation;
    private final LocaleList localeList;
    public final int percentStyle;
    public final float scaleFactor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        @Deprecated
        public static /* synthetic */ void getTYPE_SHOW_OLD_HORIZONTAL_BATTERY_ICON$annotations() {
        }
    }

    public BatteryStyleModel(int i, int i2, boolean z, LocaleList localeList, int i3, float f, boolean z2) {
        this.percentStyle = i;
        this.iconStyle = i2;
        this.capsuleShowing = z;
        this.localeList = localeList;
        this.densityDpi = i3;
        this.scaleFactor = f;
        this.isDisableAnimation = z2;
    }

    public static /* synthetic */ BatteryStyleModel copy$default(BatteryStyleModel batteryStyleModel, int i, int i2, LocaleList localeList, int i3, boolean z, int i4) {
        if ((i4 & 1) != 0) {
            i = batteryStyleModel.percentStyle;
        }
        int i5 = i;
        if ((i4 & 2) != 0) {
            i2 = batteryStyleModel.iconStyle;
        }
        int i6 = i2;
        boolean z2 = batteryStyleModel.capsuleShowing;
        if ((i4 & 8) != 0) {
            localeList = batteryStyleModel.localeList;
        }
        LocaleList localeList2 = localeList;
        if ((i4 & 16) != 0) {
            i3 = batteryStyleModel.densityDpi;
        }
        int i7 = i3;
        float f = batteryStyleModel.scaleFactor;
        if ((i4 & 64) != 0) {
            z = batteryStyleModel.isDisableAnimation;
        }
        return batteryStyleModel.copy(i5, i6, z2, localeList2, i7, f, z);
    }

    public final LocaleList component4() {
        return this.localeList;
    }

    public final BatteryStyleModel copy(int i, int i2, boolean z, LocaleList localeList, int i3, float f, boolean z2) {
        return new BatteryStyleModel(i, i2, z, localeList, i3, f, z2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BatteryStyleModel)) {
            return false;
        }
        BatteryStyleModel batteryStyleModel = (BatteryStyleModel) obj;
        return this.percentStyle == batteryStyleModel.percentStyle && this.iconStyle == batteryStyleModel.iconStyle && this.capsuleShowing == batteryStyleModel.capsuleShowing && Intrinsics.areEqual(this.localeList, batteryStyleModel.localeList) && this.densityDpi == batteryStyleModel.densityDpi && this.scaleFactor == batteryStyleModel.scaleFactor && this.isDisableAnimation == batteryStyleModel.isDisableAnimation;
    }

    public final boolean getCapsuleShowing() {
        return this.capsuleShowing;
    }

    public final int getDensityDpi() {
        return this.densityDpi;
    }

    public final int getIconStyle() {
        return this.iconStyle;
    }

    public final LocaleList getLocaleList() {
        return this.localeList;
    }

    public final int getPercentStyle() {
        return this.percentStyle;
    }

    public final float getScaleFactor() {
        return this.scaleFactor;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isDisableAnimation) + ChangeSize$.ExternalSyntheticOutline0.m(this.scaleFactor, (((this.localeList.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(((this.percentStyle * 31) + this.iconStyle) * 31, 31, this.capsuleShowing)) * 31) + this.densityDpi) * 31, 31);
    }

    public String toString() {
        LocaleList localeList = this.localeList;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("BatteryStyleModel:(percentStyle: ", this.percentStyle, this.iconStyle, ", iconStyle: ", "), capsuleShowing: ");
        sbM.append(this.capsuleShowing);
        sbM.append(", localeList: ");
        sbM.append(localeList);
        sbM.append(", densityDpi:");
        sbM.append(this.densityDpi);
        sbM.append(" isUserSwitching:");
        sbM.append(this.isDisableAnimation);
        return sbM.toString();
    }

    public /* synthetic */ BatteryStyleModel(int i) {
        this(0, i, false, LocaleList.getDefault(), 0, 1.0f, false);
    }
}
