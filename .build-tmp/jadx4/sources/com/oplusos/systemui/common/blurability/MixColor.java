package com.oplusos.systemui.common.blurability;

import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColor {
    public static final Companion Companion = new Companion();
    private static final MixColor EMPTY = new MixColor(0, 0, 0);
    public final int bottomLayerColor;
    public final int mode;
    public final int topLayerColor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MixColor getEMPTY() {
            return MixColor.EMPTY;
        }
    }

    public MixColor(int i, int i2, int i3) {
        this.mode = i;
        this.topLayerColor = i2;
        this.bottomLayerColor = i3;
    }

    public final MixColor copy(int i, int i2, int i3) {
        return new MixColor(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MixColor)) {
            return false;
        }
        MixColor mixColor = (MixColor) obj;
        return this.mode == mixColor.mode && this.topLayerColor == mixColor.topLayerColor && this.bottomLayerColor == mixColor.bottomLayerColor;
    }

    public final int getBottomLayerColor() {
        return this.bottomLayerColor;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getTopLayerColor() {
        return this.topLayerColor;
    }

    public final int hashCode() {
        return Integer.hashCode(this.bottomLayerColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.topLayerColor, Integer.hashCode(this.mode) * 31, 31);
    }

    public String toString() {
        return "MixColor(mode=" + this.mode + ", topLayerColor=" + Integer.toHexString(this.topLayerColor) + ", bottomLayerColor=" + Integer.toHexString(this.bottomLayerColor) + ')';
    }
}
