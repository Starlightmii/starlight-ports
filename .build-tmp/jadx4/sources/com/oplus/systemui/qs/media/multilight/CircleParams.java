package com.oplus.systemui.qs.media.multilight;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CircleParams {
    public static final Companion Companion = new Companion();
    private static final CircleParams INFINITE = new CircleParams(0.3f, 500.0f, 0.5f, 0.1f, -1.0f, 700.0f, 0.0f, 2000.0f, 50.0f);
    private static final CircleParams LIMITED = new CircleParams(0.6f, 1000.0f, 0.5f, 0.1f, -1.0f, 700.0f, 0.0f, 2000.0f, 50.0f);
    private static final CircleParams MEDIA_ONE = new CircleParams(0.6f, 1000.0f, 0.8f, 0.1f, -0.3f, 1200.0f, 0.0f, 1200.0f, 50.0f);
    public final float alpha;
    public final float freq;
    public final float maskDisW;
    public final float maskDisX;
    public final float maskDisY;
    public final float maskDisZ;
    public final float speed;
    public final float widthX;
    public final float widthY;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final CircleParams getINFINITE() {
            return CircleParams.INFINITE;
        }

        public final CircleParams getLIMITED() {
            return CircleParams.LIMITED;
        }

        public final CircleParams getMEDIA_ONE() {
            return CircleParams.MEDIA_ONE;
        }
    }

    public CircleParams(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.speed = f;
        this.freq = f2;
        this.alpha = f3;
        this.widthX = f4;
        this.widthY = f5;
        this.maskDisX = f6;
        this.maskDisY = f7;
        this.maskDisZ = f8;
        this.maskDisW = f9;
    }

    public final void applyTo(MultiLightShaderParams multiLightShaderParams) {
        multiLightShaderParams.setCircleSpeed(Float.valueOf(this.speed), Float.valueOf(this.freq));
        multiLightShaderParams.setCircleAlpha(this.alpha);
        multiLightShaderParams.setCircleWidth(Float.valueOf(this.widthX), Float.valueOf(this.widthY));
        multiLightShaderParams.setCircleMaskDis(Float.valueOf(this.maskDisX), Float.valueOf(this.maskDisY), Float.valueOf(this.maskDisZ), Float.valueOf(this.maskDisW));
    }

    public final CircleParams copy(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return new CircleParams(f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CircleParams)) {
            return false;
        }
        CircleParams circleParams = (CircleParams) obj;
        return Float.compare(this.speed, circleParams.speed) == 0 && Float.compare(this.freq, circleParams.freq) == 0 && Float.compare(this.alpha, circleParams.alpha) == 0 && Float.compare(this.widthX, circleParams.widthX) == 0 && Float.compare(this.widthY, circleParams.widthY) == 0 && Float.compare(this.maskDisX, circleParams.maskDisX) == 0 && Float.compare(this.maskDisY, circleParams.maskDisY) == 0 && Float.compare(this.maskDisZ, circleParams.maskDisZ) == 0 && Float.compare(this.maskDisW, circleParams.maskDisW) == 0;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getFreq() {
        return this.freq;
    }

    public final float getMaskDisW() {
        return this.maskDisW;
    }

    public final float getMaskDisX() {
        return this.maskDisX;
    }

    public final float getMaskDisY() {
        return this.maskDisY;
    }

    public final float getMaskDisZ() {
        return this.maskDisZ;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final float getWidthX() {
        return this.widthX;
    }

    public final float getWidthY() {
        return this.widthY;
    }

    public final int hashCode() {
        return Float.hashCode(this.maskDisW) + ChangeSize$.ExternalSyntheticOutline0.m(this.maskDisZ, ChangeSize$.ExternalSyntheticOutline0.m(this.maskDisY, ChangeSize$.ExternalSyntheticOutline0.m(this.maskDisX, ChangeSize$.ExternalSyntheticOutline0.m(this.widthY, ChangeSize$.ExternalSyntheticOutline0.m(this.widthX, ChangeSize$.ExternalSyntheticOutline0.m(this.alpha, ChangeSize$.ExternalSyntheticOutline0.m(this.freq, Float.hashCode(this.speed) * 31, 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("CircleParams(speed=", this.speed, ", freq=", this.freq, ", alpha=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.alpha, ", widthX=", this.widthX, ", widthY=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.widthY, ", maskDisX=", this.maskDisX, ", maskDisY=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.maskDisY, ", maskDisZ=", this.maskDisZ, ", maskDisW=");
        return DpCornerSize$.ExternalSyntheticOutline0.m(this.maskDisW, ")", sbM);
    }
}
