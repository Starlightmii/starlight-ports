package com.oplusos.systemui.common.blurability;

import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WindowBlurConfig {
    public final int maxWindowBlurRadius;
    public final int minWindowBlurRadius;
    public int mixedColor;

    public WindowBlurConfig(int i, int i2, int i3) {
        this.minWindowBlurRadius = i;
        this.maxWindowBlurRadius = i2;
        this.mixedColor = i3;
    }

    public final WindowBlurConfig copy(int i, int i2, int i3) {
        return new WindowBlurConfig(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowBlurConfig)) {
            return false;
        }
        WindowBlurConfig windowBlurConfig = (WindowBlurConfig) obj;
        return this.minWindowBlurRadius == windowBlurConfig.minWindowBlurRadius && this.maxWindowBlurRadius == windowBlurConfig.maxWindowBlurRadius && this.mixedColor == windowBlurConfig.mixedColor;
    }

    public final int getMaxWindowBlurRadius() {
        return this.maxWindowBlurRadius;
    }

    public final int getMinWindowBlurRadius() {
        return this.minWindowBlurRadius;
    }

    public final int getMixedColor() {
        return this.mixedColor;
    }

    public final int hashCode() {
        return Integer.hashCode(this.mixedColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.maxWindowBlurRadius, Integer.hashCode(this.minWindowBlurRadius) * 31, 31);
    }

    public final void setMixedColor(int i) {
        this.mixedColor = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("WindowBlurConfig(minWindowBlurRadius=");
        sb.append(this.minWindowBlurRadius);
        sb.append(", maxWindowBlurRadius=");
        sb.append(this.maxWindowBlurRadius);
        sb.append(", mixedColor=");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.mixedColor, ')');
    }
}
