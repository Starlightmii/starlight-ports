package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChargeIcon {
    public final int iconId;
    public final boolean isVisible;
    public final int themeId;
    public final int tintColor;

    public ChargeIcon(int i, int i2, int i3, boolean z) {
        this.isVisible = z;
        this.iconId = i;
        this.tintColor = i2;
        this.themeId = i3;
    }

    public final ChargeIcon copy(boolean z, int i, int i2, int i3) {
        return new ChargeIcon(i, i2, i3, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChargeIcon)) {
            return false;
        }
        ChargeIcon chargeIcon = (ChargeIcon) obj;
        return this.isVisible == chargeIcon.isVisible && this.iconId == chargeIcon.iconId && this.tintColor == chargeIcon.tintColor && this.themeId == chargeIcon.themeId;
    }

    public final int getIconId() {
        return this.iconId;
    }

    public final int getThemeId() {
        return this.themeId;
    }

    public final int getTintColor() {
        return this.tintColor;
    }

    public final int hashCode() {
        return Integer.hashCode(this.themeId) + MonoSpline$.ExternalSyntheticOutline0.m(this.tintColor, MonoSpline$.ExternalSyntheticOutline0.m(this.iconId, Boolean.hashCode(this.isVisible) * 31, 31), 31);
    }

    public String toString() {
        String hexString = Integer.toHexString(this.iconId);
        String hexString2 = Integer.toHexString(this.tintColor);
        StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("isVisible:", " iconId:", hexString, this.isVisible, " tintColor:");
        sbM.append(hexString2);
        sbM.append(" themeId:");
        sbM.append(this.themeId);
        return sbM.toString();
    }
}
