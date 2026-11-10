package com.oplus.systemui.shade;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NsslAnimParams {
    public static final Companion Companion = new Companion();
    public final float alphaBounce;
    public final float alphaHideResponse;
    public final float alphaShowResponse;
    public final float hideAlpha;
    public final float hideScale;
    public final float minVisibleChange;
    public final float scaleHideBounce;
    public final float scaleHideResponse;
    public final float scaleShowBounce;
    public final float scaleShowResponse;
    public final float showAlpha;
    public final float showScale;
    public final float springDamping;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NsslAnimParams bbPlusAlphaScale() {
            return new NsslAnimParams(1.0f, 0.0f, 0.35f, 0.15f, 0.0f, 1.0f, 0.8f, 0.3f, 0.3f, 0.0f, 0.16f, 1.0f, 0.002f);
        }

        public final NsslAnimParams legacyScaleOnly() {
            return new NsslAnimParams(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.9f, 0.3f, 0.2f, 0.16f, 0.16f, 1.0f, 0.002f);
        }
    }

    public NsslAnimParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        this.showAlpha = f;
        this.hideAlpha = f2;
        this.alphaShowResponse = f3;
        this.alphaHideResponse = f4;
        this.alphaBounce = f5;
        this.showScale = f6;
        this.hideScale = f7;
        this.scaleShowResponse = f8;
        this.scaleHideResponse = f9;
        this.scaleShowBounce = f10;
        this.scaleHideBounce = f11;
        this.springDamping = f12;
        this.minVisibleChange = f13;
    }

    public final NsslAnimParams copy(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        return new NsslAnimParams(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NsslAnimParams)) {
            return false;
        }
        NsslAnimParams nsslAnimParams = (NsslAnimParams) obj;
        return Float.compare(this.showAlpha, nsslAnimParams.showAlpha) == 0 && Float.compare(this.hideAlpha, nsslAnimParams.hideAlpha) == 0 && Float.compare(this.alphaShowResponse, nsslAnimParams.alphaShowResponse) == 0 && Float.compare(this.alphaHideResponse, nsslAnimParams.alphaHideResponse) == 0 && Float.compare(this.alphaBounce, nsslAnimParams.alphaBounce) == 0 && Float.compare(this.showScale, nsslAnimParams.showScale) == 0 && Float.compare(this.hideScale, nsslAnimParams.hideScale) == 0 && Float.compare(this.scaleShowResponse, nsslAnimParams.scaleShowResponse) == 0 && Float.compare(this.scaleHideResponse, nsslAnimParams.scaleHideResponse) == 0 && Float.compare(this.scaleShowBounce, nsslAnimParams.scaleShowBounce) == 0 && Float.compare(this.scaleHideBounce, nsslAnimParams.scaleHideBounce) == 0 && Float.compare(this.springDamping, nsslAnimParams.springDamping) == 0 && Float.compare(this.minVisibleChange, nsslAnimParams.minVisibleChange) == 0;
    }

    public final float getAlphaBounce() {
        return this.alphaBounce;
    }

    public final float getAlphaHideResponse() {
        return this.alphaHideResponse;
    }

    public final float getAlphaShowResponse() {
        return this.alphaShowResponse;
    }

    public final float getHideAlpha() {
        return this.hideAlpha;
    }

    public final float getHideScale() {
        return this.hideScale;
    }

    public final float getMinVisibleChange() {
        return this.minVisibleChange;
    }

    public final float getScaleHideBounce() {
        return this.scaleHideBounce;
    }

    public final float getScaleHideResponse() {
        return this.scaleHideResponse;
    }

    public final float getScaleShowBounce() {
        return this.scaleShowBounce;
    }

    public final float getScaleShowResponse() {
        return this.scaleShowResponse;
    }

    public final float getShowAlpha() {
        return this.showAlpha;
    }

    public final float getShowScale() {
        return this.showScale;
    }

    public final float getSpringDamping() {
        return this.springDamping;
    }

    public final int hashCode() {
        return Float.hashCode(this.minVisibleChange) + ChangeSize$.ExternalSyntheticOutline0.m(this.springDamping, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleHideBounce, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleShowBounce, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleHideResponse, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleShowResponse, ChangeSize$.ExternalSyntheticOutline0.m(this.hideScale, ChangeSize$.ExternalSyntheticOutline0.m(this.showScale, ChangeSize$.ExternalSyntheticOutline0.m(this.alphaBounce, ChangeSize$.ExternalSyntheticOutline0.m(this.alphaHideResponse, ChangeSize$.ExternalSyntheticOutline0.m(this.alphaShowResponse, ChangeSize$.ExternalSyntheticOutline0.m(this.hideAlpha, Float.hashCode(this.showAlpha) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("NsslAnimParams(showAlpha=", this.showAlpha, ", hideAlpha=", this.hideAlpha, ", alphaShowResponse=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.alphaShowResponse, ", alphaHideResponse=", this.alphaHideResponse, ", alphaBounce=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.alphaBounce, ", showScale=", this.showScale, ", hideScale=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.hideScale, ", scaleShowResponse=", this.scaleShowResponse, ", scaleHideResponse=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.scaleHideResponse, ", scaleShowBounce=", this.scaleShowBounce, ", scaleHideBounce=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.scaleHideBounce, ", springDamping=", this.springDamping, ", minVisibleChange=");
        return DpCornerSize$.ExternalSyntheticOutline0.m(this.minVisibleChange, ")", sbM);
    }
}
