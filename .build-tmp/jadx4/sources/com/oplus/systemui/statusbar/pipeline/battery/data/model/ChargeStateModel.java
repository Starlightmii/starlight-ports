package com.oplus.systemui.statusbar.pipeline.battery.data.model;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChargeStateModel {
    public final int chargerWattage;
    public final boolean charging;
    public final boolean heatStateActive;
    public final boolean pluggedInWireless;
    public final boolean superVoocCharging;
    public final boolean voocSpecialColorShow;
    public final boolean wirelessChargingReverse;
    public final boolean wirelessVoocCharging;

    public ChargeStateModel(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, boolean z6, boolean z7) {
        this.charging = z;
        this.pluggedInWireless = z2;
        this.superVoocCharging = z3;
        this.wirelessVoocCharging = z4;
        this.heatStateActive = z5;
        this.wirelessChargingReverse = z6;
        this.chargerWattage = i;
        this.voocSpecialColorShow = z7;
    }

    public final ChargeStateModel copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, boolean z7) {
        return new ChargeStateModel(z, z2, z3, z4, z5, i, z6, z7);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChargeStateModel)) {
            return false;
        }
        ChargeStateModel chargeStateModel = (ChargeStateModel) obj;
        return this.charging == chargeStateModel.charging && this.pluggedInWireless == chargeStateModel.pluggedInWireless && this.superVoocCharging == chargeStateModel.superVoocCharging && this.wirelessVoocCharging == chargeStateModel.wirelessVoocCharging && this.heatStateActive == chargeStateModel.heatStateActive && this.wirelessChargingReverse == chargeStateModel.wirelessChargingReverse && this.chargerWattage == chargeStateModel.chargerWattage && this.voocSpecialColorShow == chargeStateModel.voocSpecialColorShow;
    }

    public final int getChargerWattage() {
        return this.chargerWattage;
    }

    public final boolean getCharging() {
        return this.charging;
    }

    public final boolean getHeatStateActive() {
        return this.heatStateActive;
    }

    public final boolean getPluggedInWireless() {
        return this.pluggedInWireless;
    }

    public final boolean getSuperVoocCharging() {
        return this.superVoocCharging;
    }

    public final boolean getVoocSpecialColorShow() {
        return this.voocSpecialColorShow;
    }

    public final boolean getWirelessChargingReverse() {
        return this.wirelessChargingReverse;
    }

    public final boolean getWirelessVoocCharging() {
        return this.wirelessVoocCharging;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.voocSpecialColorShow) + ((ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Boolean.hashCode(this.charging) * 31, 31, this.pluggedInWireless), 31, this.superVoocCharging), 31, this.wirelessVoocCharging), 31, this.heatStateActive), 31, this.wirelessChargingReverse) + this.chargerWattage) * 31);
    }

    public String toString() {
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("ChargeStateModel:(charging: ", ", pluggedInWireless: ", ", superVoocCharging: ", this.charging, this.pluggedInWireless);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", wirelessVoocCharging: ", ", heatStateActive: ", sbM, this.superVoocCharging, this.wirelessVoocCharging);
        KeyguardLogger$.ExternalSyntheticOutline0.m(",wirelessChargingReverse: ", ", chargerWattage:", sbM, this.heatStateActive, this.wirelessChargingReverse);
        sbM.append(this.chargerWattage);
        sbM.append("voocSpecialColorShow: ");
        sbM.append(this.voocSpecialColorShow);
        sbM.append(")");
        return sbM.toString();
    }

    public /* synthetic */ ChargeStateModel() {
        this(false, false, false, false, false, 0, false, true);
    }
}
