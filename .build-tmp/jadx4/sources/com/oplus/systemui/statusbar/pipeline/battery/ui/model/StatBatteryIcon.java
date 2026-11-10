package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.hidl.base.V1_0.DebugInfo$;
import android.os.LocaleList;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class StatBatteryIcon {
    public final int backgroundColor;
    public final int chargeIconId;
    private final String contentDescription;
    public final int powerLevel;
    public final int progressColor;
    public final boolean specialProgressColor;
    public final int tintColor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Horizontal extends StatBatteryIcon {
        public final int backgroundColor;
        public final int chargeBgIconId;
        public final int chargeIconId;
        private final String contentDescription;
        private final LocaleList localeList;
        public final boolean percentIn;
        public final int powerLevel;
        public final int progressColor;
        public final boolean specialProgressColor;
        public final int tintColor;

        public Horizontal(int i, int i2, int i3, int i4, int i5, int i6, boolean z, String str, boolean z2, LocaleList localeList) {
            super(i, i2, i4, i5, i6, z, str);
            this.powerLevel = i;
            this.chargeIconId = i2;
            this.chargeBgIconId = i3;
            this.tintColor = i4;
            this.progressColor = i5;
            this.backgroundColor = i6;
            this.specialProgressColor = z;
            this.contentDescription = str;
            this.percentIn = z2;
            this.localeList = localeList;
        }

        public final LocaleList component10() {
            return this.localeList;
        }

        public final String component8() {
            return this.contentDescription;
        }

        public final Horizontal copy(int i, int i2, int i3, int i4, int i5, int i6, boolean z, String str, boolean z2, LocaleList localeList) {
            return new Horizontal(i, i2, i3, i4, i5, i6, z, str, z2, localeList);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Horizontal)) {
                return false;
            }
            Horizontal horizontal = (Horizontal) obj;
            return this.powerLevel == horizontal.powerLevel && this.chargeIconId == horizontal.chargeIconId && this.chargeBgIconId == horizontal.chargeBgIconId && this.tintColor == horizontal.tintColor && this.progressColor == horizontal.progressColor && this.backgroundColor == horizontal.backgroundColor && this.specialProgressColor == horizontal.specialProgressColor && Intrinsics.areEqual(this.contentDescription, horizontal.contentDescription) && this.percentIn == horizontal.percentIn && Intrinsics.areEqual(this.localeList, horizontal.localeList);
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public final int getChargeBgIconId() {
            return this.chargeBgIconId;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getChargeIconId() {
            return this.chargeIconId;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public String getContentDescription() {
            return this.contentDescription;
        }

        public final LocaleList getLocaleList() {
            return this.localeList;
        }

        public final boolean getPercentIn() {
            return this.percentIn;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getPowerLevel() {
            return this.powerLevel;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getProgressColor() {
            return this.progressColor;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public boolean getSpecialProgressColor() {
            return this.specialProgressColor;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getTintColor() {
            return this.tintColor;
        }

        public final int hashCode() {
            return this.localeList.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.backgroundColor, MonoSpline$.ExternalSyntheticOutline0.m(this.progressColor, MonoSpline$.ExternalSyntheticOutline0.m(this.tintColor, MonoSpline$.ExternalSyntheticOutline0.m(this.chargeBgIconId, MonoSpline$.ExternalSyntheticOutline0.m(this.chargeIconId, Integer.hashCode(this.powerLevel) * 31, 31), 31), 31), 31), 31), 31, this.specialProgressColor), 31, this.contentDescription), 31, this.percentIn);
        }

        public String toString() {
            String str = this.contentDescription;
            LocaleList localeList = this.localeList;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("Horizontal(powerLevel=", this.powerLevel, this.chargeIconId, ", chargeIconId=", ", chargeBgIconId=");
            RectList$.ExternalSyntheticOutline0.m(this.chargeBgIconId, this.tintColor, ", tintColor=", ", progressColor=", sbM);
            RectList$.ExternalSyntheticOutline0.m(this.progressColor, this.backgroundColor, ", backgroundColor=", ", specialProgressColor=", sbM);
            DeviceItem$.ExternalSyntheticOutline0.m(", contentDescription=", str, ", percentIn=", sbM, this.specialProgressColor);
            sbM.append(this.percentIn);
            sbM.append(", localeList=");
            sbM.append(localeList);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class VerticalOrCircle extends StatBatteryIcon {
        public final int backgroundColor;
        public final int chargeIconId;
        private final String contentDescription;
        public final int powerLevel;
        public final int progressColor;
        public final boolean specialProgressColor;
        public final int tintColor;

        public VerticalOrCircle(int i, int i2, int i3, int i4, int i5, boolean z, String str) {
            super(i, i2, i3, i4, i5, z, str);
            this.powerLevel = i;
            this.chargeIconId = i2;
            this.tintColor = i3;
            this.progressColor = i4;
            this.backgroundColor = i5;
            this.specialProgressColor = z;
            this.contentDescription = str;
        }

        public final String component7() {
            return this.contentDescription;
        }

        public final VerticalOrCircle copy(int i, int i2, int i3, int i4, int i5, boolean z, String str) {
            return new VerticalOrCircle(i, i2, i3, i4, i5, z, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VerticalOrCircle)) {
                return false;
            }
            VerticalOrCircle verticalOrCircle = (VerticalOrCircle) obj;
            return this.powerLevel == verticalOrCircle.powerLevel && this.chargeIconId == verticalOrCircle.chargeIconId && this.tintColor == verticalOrCircle.tintColor && this.progressColor == verticalOrCircle.progressColor && this.backgroundColor == verticalOrCircle.backgroundColor && this.specialProgressColor == verticalOrCircle.specialProgressColor && Intrinsics.areEqual(this.contentDescription, verticalOrCircle.contentDescription);
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getChargeIconId() {
            return this.chargeIconId;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public String getContentDescription() {
            return this.contentDescription;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getPowerLevel() {
            return this.powerLevel;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getProgressColor() {
            return this.progressColor;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public boolean getSpecialProgressColor() {
            return this.specialProgressColor;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon
        public int getTintColor() {
            return this.tintColor;
        }

        public final int hashCode() {
            return this.contentDescription.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.backgroundColor, MonoSpline$.ExternalSyntheticOutline0.m(this.progressColor, MonoSpline$.ExternalSyntheticOutline0.m(this.tintColor, MonoSpline$.ExternalSyntheticOutline0.m(this.chargeIconId, Integer.hashCode(this.powerLevel) * 31, 31), 31), 31), 31), 31, this.specialProgressColor);
        }

        public String toString() {
            String str = this.contentDescription;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("VerticalOrCircle(powerLevel=", this.powerLevel, this.chargeIconId, ", chargeIconId=", ", tintColor=");
            RectList$.ExternalSyntheticOutline0.m(this.tintColor, this.progressColor, ", progressColor=", ", backgroundColor=", sbM);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(this.backgroundColor, ", specialProgressColor=", ", contentDescription=", sbM, this.specialProgressColor);
            return DebugInfo$.ExternalSyntheticOutline0.m(sbM, str, ")");
        }
    }

    public StatBatteryIcon(int i, int i2, int i3, int i4, int i5, boolean z, String str) {
        this.powerLevel = i;
        this.chargeIconId = i2;
        this.tintColor = i3;
        this.progressColor = i4;
        this.backgroundColor = i5;
        this.specialProgressColor = z;
        this.contentDescription = str;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getChargeIconId() {
        return this.chargeIconId;
    }

    public String getContentDescription() {
        return this.contentDescription;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public int getProgressColor() {
        return this.progressColor;
    }

    public boolean getSpecialProgressColor() {
        return this.specialProgressColor;
    }

    public int getTintColor() {
        return this.tintColor;
    }
}
