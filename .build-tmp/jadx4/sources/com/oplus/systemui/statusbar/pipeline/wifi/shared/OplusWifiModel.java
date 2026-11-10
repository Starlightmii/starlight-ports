package com.oplus.systemui.statusbar.pipeline.wifi.shared;

import androidx.compose.animation.ChangeSize$;
import com.android.keyguard.logging.KeyguardLogger$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiModel {
    public static final Companion Companion = new Companion();
    public final boolean isPassPointAp;
    public final int networkId;
    public final boolean wifiDouble;
    public final int wifiStandard;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusWifiModel(int i, int i2, boolean z, boolean z2) {
        this.networkId = i;
        this.wifiDouble = z;
        this.isPassPointAp = z2;
        this.wifiStandard = i2;
    }

    public static /* synthetic */ OplusWifiModel copy$default(OplusWifiModel oplusWifiModel, int i, boolean z, boolean z2, int i2, int i3) {
        if ((i3 & 1) != 0) {
            i = oplusWifiModel.networkId;
        }
        if ((i3 & 2) != 0) {
            z = oplusWifiModel.wifiDouble;
        }
        if ((i3 & 4) != 0) {
            z2 = oplusWifiModel.isPassPointAp;
        }
        if ((i3 & 8) != 0) {
            i2 = oplusWifiModel.wifiStandard;
        }
        return oplusWifiModel.copy(i, z, z2, i2);
    }

    public final OplusWifiModel copy(int i, boolean z, boolean z2, int i2) {
        return new OplusWifiModel(i, i2, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusWifiModel)) {
            return false;
        }
        OplusWifiModel oplusWifiModel = (OplusWifiModel) obj;
        return this.networkId == oplusWifiModel.networkId && this.wifiDouble == oplusWifiModel.wifiDouble && this.isPassPointAp == oplusWifiModel.isPassPointAp && this.wifiStandard == oplusWifiModel.wifiStandard;
    }

    public final int getNetworkId() {
        return this.networkId;
    }

    public final boolean getWifiDouble() {
        return this.wifiDouble;
    }

    public final int getWifiStandard() {
        return this.wifiStandard;
    }

    public final int hashCode() {
        return Integer.hashCode(this.wifiStandard) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Integer.hashCode(this.networkId) * 31, 31, this.wifiDouble), 31, this.isPassPointAp);
    }

    public String toString() {
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("OplusWifiModel(networkId=", this.networkId, ", wifiDouble=", ", isPassPointAp=", this.wifiDouble);
        sbM.append(this.isPassPointAp);
        sbM.append(", wifiStandard=");
        sbM.append(this.wifiStandard);
        sbM.append(")");
        return sbM.toString();
    }

    public /* synthetic */ OplusWifiModel() {
        this(-1, 0, false, false);
    }
}
