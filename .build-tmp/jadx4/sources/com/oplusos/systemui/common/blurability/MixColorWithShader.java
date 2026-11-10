package com.oplusos.systemui.common.blurability;

import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorWithShader {
    public static final Companion Companion = new Companion();
    private static final MixColorWithShader EMPTY = new MixColorWithShader(0, 0, 0, 0);
    public final int bottomLayerColor;
    public final int bottomMode;
    public final int topLayerColor;
    public final int topMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MixColorWithShader getEMPTY() {
            return MixColorWithShader.EMPTY;
        }
    }

    public MixColorWithShader(int i, int i2, int i3, int i4) {
        this.topMode = i;
        this.topLayerColor = i2;
        this.bottomMode = i3;
        this.bottomLayerColor = i4;
    }

    public final MixColorWithShader copy(int i, int i2, int i3, int i4) {
        return new MixColorWithShader(i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MixColorWithShader)) {
            return false;
        }
        MixColorWithShader mixColorWithShader = (MixColorWithShader) obj;
        return this.topMode == mixColorWithShader.topMode && this.topLayerColor == mixColorWithShader.topLayerColor && this.bottomMode == mixColorWithShader.bottomMode && this.bottomLayerColor == mixColorWithShader.bottomLayerColor;
    }

    public final int getBottomLayerColor() {
        return this.bottomLayerColor;
    }

    public final int getBottomMode() {
        return this.bottomMode;
    }

    public final int getTopLayerColor() {
        return this.topLayerColor;
    }

    public final int getTopMode() {
        return this.topMode;
    }

    public final int hashCode() {
        return Integer.hashCode(this.bottomLayerColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.bottomMode, MonoSpline$.ExternalSyntheticOutline0.m(this.topLayerColor, Integer.hashCode(this.topMode) * 31, 31), 31);
    }

    public String toString() {
        return "MixColorWithShader(topMode=" + this.topMode + ", topLayerColor=" + Integer.toHexString(this.topLayerColor) + ", bottomMode=" + this.bottomMode + ", bottomLayerColor=" + Integer.toHexString(this.bottomLayerColor) + ')';
    }
}
