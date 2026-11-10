package com.oplusos.systemui.common.util;

import androidx.compose.animation.ChangeSize$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LineParams {
    public float alpha;
    public float fadeToSides1;
    public float fadeToSides2;
    public float fadeTowardCenter1;
    public float fadeTowardCenter2;
    public int width;

    public LineParams(int i, float f, float f2, float f3, float f4, float f5) {
        this.width = i;
        this.alpha = f;
        this.fadeToSides1 = f2;
        this.fadeToSides2 = f3;
        this.fadeTowardCenter1 = f4;
        this.fadeTowardCenter2 = f5;
    }

    public final LineParams copy(int i, float f, float f2, float f3, float f4, float f5) {
        return new LineParams(i, f, f2, f3, f4, f5);
    }

    public final void copyFrom(LineParams lineParams) {
        this.width = lineParams != null ? lineParams.width : 0;
        this.alpha = lineParams != null ? lineParams.alpha : 0.0f;
        this.fadeToSides1 = lineParams != null ? lineParams.fadeToSides1 : 0.0f;
        this.fadeToSides2 = lineParams != null ? lineParams.fadeToSides2 : 0.0f;
        this.fadeTowardCenter1 = lineParams != null ? lineParams.fadeTowardCenter1 : 0.0f;
        this.fadeTowardCenter2 = lineParams != null ? lineParams.fadeTowardCenter2 : 0.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineParams)) {
            return false;
        }
        LineParams lineParams = (LineParams) obj;
        return this.width == lineParams.width && Float.compare(this.alpha, lineParams.alpha) == 0 && Float.compare(this.fadeToSides1, lineParams.fadeToSides1) == 0 && Float.compare(this.fadeToSides2, lineParams.fadeToSides2) == 0 && Float.compare(this.fadeTowardCenter1, lineParams.fadeTowardCenter1) == 0 && Float.compare(this.fadeTowardCenter2, lineParams.fadeTowardCenter2) == 0;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getFadeToSides1() {
        return this.fadeToSides1;
    }

    public final float getFadeToSides2() {
        return this.fadeToSides2;
    }

    public final float getFadeTowardCenter1() {
        return this.fadeTowardCenter1;
    }

    public final float getFadeTowardCenter2() {
        return this.fadeTowardCenter2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int hashCode() {
        return Float.hashCode(this.fadeTowardCenter2) + ChangeSize$.ExternalSyntheticOutline0.m(this.fadeTowardCenter1, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeToSides2, ChangeSize$.ExternalSyntheticOutline0.m(this.fadeToSides1, ChangeSize$.ExternalSyntheticOutline0.m(this.alpha, Integer.hashCode(this.width) * 31, 31), 31), 31), 31);
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final void setFadeToSides1(float f) {
        this.fadeToSides1 = f;
    }

    public final void setFadeToSides2(float f) {
        this.fadeToSides2 = f;
    }

    public final void setFadeTowardCenter1(float f) {
        this.fadeTowardCenter1 = f;
    }

    public final void setFadeTowardCenter2(float f) {
        this.fadeTowardCenter2 = f;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LineParams(width=");
        sb.append(this.width);
        sb.append(", alpha=");
        sb.append(this.alpha);
        sb.append(", fadeToSides1=");
        sb.append(this.fadeToSides1);
        sb.append(", fadeToSides2=");
        sb.append(this.fadeToSides2);
        sb.append(", fadeTowardCenter1=");
        sb.append(this.fadeTowardCenter1);
        sb.append(", fadeTowardCenter2=");
        return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.fadeTowardCenter2, ')');
    }
}
