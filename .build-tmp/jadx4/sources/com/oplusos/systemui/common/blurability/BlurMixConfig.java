package com.oplusos.systemui.common.blurability;

import androidx.compose.animation.ChangeSize$;
import com.oplus.posteffect.agsl.ShaderBlendParam;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BlurMixConfig {
    public static final Companion Companion = new Companion();
    public boolean alphaWithBlurAmount;
    public float mirrorScale = 1.0f;
    public int placeHolderColor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BlurMixMulti extends BlurMixConfig {
        private final MixColor backgroundMixColor;
        private final MixColor foregroundMixColor;

        public BlurMixMulti(MixColor mixColor, MixColor mixColor2) {
            this.foregroundMixColor = mixColor;
            this.backgroundMixColor = mixColor2;
        }

        public final MixColor component1() {
            return this.foregroundMixColor;
        }

        public final MixColor component2() {
            return this.backgroundMixColor;
        }

        public final BlurMixMulti copy(MixColor mixColor, MixColor mixColor2) {
            return new BlurMixMulti(mixColor, mixColor2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlurMixMulti)) {
                return false;
            }
            BlurMixMulti blurMixMulti = (BlurMixMulti) obj;
            return Intrinsics.areEqual(this.foregroundMixColor, blurMixMulti.foregroundMixColor) && Intrinsics.areEqual(this.backgroundMixColor, blurMixMulti.backgroundMixColor);
        }

        public final MixColor getBackgroundMixColor() {
            return this.backgroundMixColor;
        }

        public final MixColor getForegroundMixColor() {
            return this.foregroundMixColor;
        }

        public final int hashCode() {
            return this.backgroundMixColor.hashCode() + (this.foregroundMixColor.hashCode() * 31);
        }

        public String toString() {
            return "BlurMixMulti(foregroundMixColor=" + this.foregroundMixColor + ", backgroundMixColor=" + this.backgroundMixColor + ')';
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BlurMixSingle extends BlurMixConfig {
        private final MixColor mixColor;

        public BlurMixSingle(MixColor mixColor) {
            this.mixColor = mixColor;
        }

        public final MixColor component1() {
            return this.mixColor;
        }

        public final BlurMixSingle copy(MixColor mixColor) {
            return new BlurMixSingle(mixColor);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof BlurMixSingle) && Intrinsics.areEqual(this.mixColor, ((BlurMixSingle) obj).mixColor);
        }

        public final MixColor getMixColor() {
            return this.mixColor;
        }

        public final int hashCode() {
            return this.mixColor.hashCode();
        }

        public String toString() {
            return "BlurMixSingle(mixColor=" + this.mixColor + ')';
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BlurMixSingleWithShader extends BlurMixConfig {
        private final MixColorWithShader backgroundShaderParam;
        private ArrayList<ShaderBlendParam> mixSingleShaderParams = new ArrayList<>(2);

        public BlurMixSingleWithShader(MixColorWithShader mixColorWithShader) {
            this.backgroundShaderParam = mixColorWithShader;
        }

        public final MixColorWithShader component1() {
            return this.backgroundShaderParam;
        }

        public final BlurMixSingleWithShader copy(MixColorWithShader mixColorWithShader) {
            return new BlurMixSingleWithShader(mixColorWithShader);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof BlurMixSingleWithShader) && Intrinsics.areEqual(this.backgroundShaderParam, ((BlurMixSingleWithShader) obj).backgroundShaderParam);
        }

        public final MixColorWithShader getBackgroundShaderParam() {
            return this.backgroundShaderParam;
        }

        public final ArrayList<ShaderBlendParam> getMixSingleShaderParams() {
            return this.mixSingleShaderParams;
        }

        public final int hashCode() {
            return this.backgroundShaderParam.hashCode();
        }

        public String toString() {
            return "BlurMixSingleWithShader(backgroundShaderParam=" + this.backgroundShaderParam + ')';
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class None extends BlurMixConfig {
        public static final None INSTANCE = new None();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof None);
        }

        public final int hashCode() {
            return -1483958144;
        }

        public String toString() {
            return "None";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OverlayColor extends BlurMixConfig {
        public static final OverlayColor INSTANCE = new OverlayColor();
        private static ArrayList<ShaderBlendParam> mixOverlayColorShaderParams = new ArrayList<>(1);

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof OverlayColor);
        }

        public final ArrayList<ShaderBlendParam> getMixOverlayColorShaderParams() {
            return mixOverlayColorShaderParams;
        }

        public final int hashCode() {
            return -618836421;
        }

        public String toString() {
            return "OverlayColor";
        }
    }

    public boolean getAlphaWithBlurAmount() {
        return this.alphaWithBlurAmount;
    }

    public float getMirrorScale() {
        return this.mirrorScale;
    }

    public int getPlaceHolderColor() {
        return this.placeHolderColor;
    }

    public void setAlphaWithBlurAmount(boolean z) {
        this.alphaWithBlurAmount = z;
    }

    public void setMirrorScale(float f) {
        this.mirrorScale = f;
    }

    public void setPlaceHolderColor(int i) {
        this.placeHolderColor = i;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BlurMixMultiWithShader extends BlurMixConfig {
        public static final Companion Companion = new Companion();
        private static final BlurMixMultiWithShader EMPTY;
        public boolean alphaWithBlurAmount;
        private final MixColorWithShader backgroundShaderParam;
        private final MixColorWithShader foregroundShaderParam;
        public float mirrorScale;
        private ArrayList<ShaderBlendParam> mixMultiShaderParams;
        public int placeHolderColor;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final BlurMixMultiWithShader getEMPTY() {
                return BlurMixMultiWithShader.EMPTY;
            }
        }

        static {
            MixColorWithShader.Companion companion = MixColorWithShader.Companion;
            EMPTY = new BlurMixMultiWithShader(companion.getEMPTY(), companion.getEMPTY());
        }

        public BlurMixMultiWithShader(MixColorWithShader mixColorWithShader, MixColorWithShader mixColorWithShader2, float f, boolean z, int i) {
            this.foregroundShaderParam = mixColorWithShader;
            this.backgroundShaderParam = mixColorWithShader2;
            this.mirrorScale = f;
            this.alphaWithBlurAmount = z;
            this.placeHolderColor = i;
            this.mixMultiShaderParams = new ArrayList<>(4);
        }

        public static /* synthetic */ BlurMixMultiWithShader copy$default(BlurMixMultiWithShader blurMixMultiWithShader, MixColorWithShader mixColorWithShader, int i) {
            if ((i & 1) != 0) {
                mixColorWithShader = blurMixMultiWithShader.foregroundShaderParam;
            }
            return blurMixMultiWithShader.copy(mixColorWithShader, blurMixMultiWithShader.backgroundShaderParam, blurMixMultiWithShader.mirrorScale, blurMixMultiWithShader.alphaWithBlurAmount, blurMixMultiWithShader.placeHolderColor);
        }

        public final MixColorWithShader component1() {
            return this.foregroundShaderParam;
        }

        public final MixColorWithShader component2() {
            return this.backgroundShaderParam;
        }

        public final BlurMixMultiWithShader copy(MixColorWithShader mixColorWithShader, MixColorWithShader mixColorWithShader2, float f, boolean z, int i) {
            return new BlurMixMultiWithShader(mixColorWithShader, mixColorWithShader2, f, z, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlurMixMultiWithShader)) {
                return false;
            }
            BlurMixMultiWithShader blurMixMultiWithShader = (BlurMixMultiWithShader) obj;
            return Intrinsics.areEqual(this.foregroundShaderParam, blurMixMultiWithShader.foregroundShaderParam) && Intrinsics.areEqual(this.backgroundShaderParam, blurMixMultiWithShader.backgroundShaderParam) && Float.compare(this.mirrorScale, blurMixMultiWithShader.mirrorScale) == 0 && this.alphaWithBlurAmount == blurMixMultiWithShader.alphaWithBlurAmount && this.placeHolderColor == blurMixMultiWithShader.placeHolderColor;
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public boolean getAlphaWithBlurAmount() {
            return this.alphaWithBlurAmount;
        }

        public final MixColorWithShader getBackgroundShaderParam() {
            return this.backgroundShaderParam;
        }

        public final MixColorWithShader getForegroundShaderParam() {
            return this.foregroundShaderParam;
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public float getMirrorScale() {
            return this.mirrorScale;
        }

        public final ArrayList<ShaderBlendParam> getMixMultiShaderParams() {
            return this.mixMultiShaderParams;
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public int getPlaceHolderColor() {
            return this.placeHolderColor;
        }

        public final int hashCode() {
            return Integer.hashCode(this.placeHolderColor) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.mirrorScale, (this.backgroundShaderParam.hashCode() + (this.foregroundShaderParam.hashCode() * 31)) * 31, 31), 31, this.alphaWithBlurAmount);
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public void setAlphaWithBlurAmount(boolean z) {
            this.alphaWithBlurAmount = z;
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public void setMirrorScale(float f) {
            this.mirrorScale = f;
        }

        @Override // com.oplusos.systemui.common.blurability.BlurMixConfig
        public void setPlaceHolderColor(int i) {
            this.placeHolderColor = i;
        }

        public String toString() {
            return "BlurMixMultiWithShader(foregroundShaderParam=" + this.foregroundShaderParam + ", backgroundShaderParam=" + this.backgroundShaderParam + ", mirrorScale=" + getMirrorScale() + " alphaWithBlurAmount=" + getAlphaWithBlurAmount() + " placeHolderColor=" + Integer.toHexString(getPlaceHolderColor());
        }

        public /* synthetic */ BlurMixMultiWithShader(MixColorWithShader mixColorWithShader, MixColorWithShader mixColorWithShader2) {
            this(mixColorWithShader, mixColorWithShader2, 1.0f, false, 0);
        }
    }
}
