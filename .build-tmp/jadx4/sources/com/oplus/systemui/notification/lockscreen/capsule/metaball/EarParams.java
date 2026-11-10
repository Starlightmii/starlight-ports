package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.core.provider.FontRequest$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EarParams {
    public final float arcAngle;
    private final float[] arcPoint0;
    private final float[] arcPoint1;
    private final float[] arcPoint2;
    private final float[] arcPos;
    public final float preCalcIsFC;
    public final float process;
    private final float[] processPoint;
    private final float[] speedGain;
    public final float weight;

    public EarParams(float f, float[] fArr, float[] fArr2, float f2, float f3, float f4, float[] fArr3, float[] fArr4, float[] fArr5, float[] fArr6) {
        this.process = f;
        this.processPoint = fArr;
        this.speedGain = fArr2;
        this.weight = f2;
        this.preCalcIsFC = f3;
        this.arcAngle = f4;
        this.arcPoint0 = fArr3;
        this.arcPoint1 = fArr4;
        this.arcPoint2 = fArr5;
        this.arcPos = fArr6;
    }

    public final float[] component10() {
        return this.arcPos;
    }

    public final float[] component2() {
        return this.processPoint;
    }

    public final float[] component3() {
        return this.speedGain;
    }

    public final float[] component7() {
        return this.arcPoint0;
    }

    public final float[] component8() {
        return this.arcPoint1;
    }

    public final float[] component9() {
        return this.arcPoint2;
    }

    public final EarParams copy(float f, float[] fArr, float[] fArr2, float f2, float f3, float f4, float[] fArr3, float[] fArr4, float[] fArr5, float[] fArr6) {
        return new EarParams(f, fArr, fArr2, f2, f3, f4, fArr3, fArr4, fArr5, fArr6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!EarParams.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        EarParams earParams = (EarParams) obj;
        return this.process == earParams.process && Arrays.equals(this.processPoint, earParams.processPoint) && Arrays.equals(this.speedGain, earParams.speedGain) && this.weight == earParams.weight && this.preCalcIsFC == earParams.preCalcIsFC && this.arcAngle == earParams.arcAngle && Arrays.equals(this.arcPoint0, earParams.arcPoint0) && Arrays.equals(this.arcPoint1, earParams.arcPoint1) && Arrays.equals(this.arcPoint2, earParams.arcPoint2) && Arrays.equals(this.arcPos, earParams.arcPos);
    }

    public final float getArcAngle() {
        return this.arcAngle;
    }

    public final float[] getArcPoint0() {
        return this.arcPoint0;
    }

    public final float[] getArcPoint1() {
        return this.arcPoint1;
    }

    public final float[] getArcPoint2() {
        return this.arcPoint2;
    }

    public final float[] getArcPos() {
        return this.arcPos;
    }

    public final float getPreCalcIsFC() {
        return this.preCalcIsFC;
    }

    public final float getProcess() {
        return this.process;
    }

    public final float[] getProcessPoint() {
        return this.processPoint;
    }

    public final float[] getSpeedGain() {
        return this.speedGain;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.arcPos) + DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.arcPoint2, DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.arcPoint1, DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.arcPoint0, ChangeSize$.ExternalSyntheticOutline0.m(this.arcAngle, ChangeSize$.ExternalSyntheticOutline0.m(this.preCalcIsFC, ChangeSize$.ExternalSyntheticOutline0.m(this.weight, DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.speedGain, DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.processPoint, Float.hashCode(this.process) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        String string = Arrays.toString(this.processPoint);
        String string2 = Arrays.toString(this.speedGain);
        String string3 = Arrays.toString(this.arcPoint0);
        String string4 = Arrays.toString(this.arcPoint1);
        String string5 = Arrays.toString(this.arcPoint2);
        String string6 = Arrays.toString(this.arcPos);
        StringBuilder sb = new StringBuilder("EarParams(process=");
        sb.append(this.process);
        sb.append(", processPoint=");
        sb.append(string);
        sb.append(", speedGain=");
        sb.append(string2);
        sb.append(", weight=");
        sb.append(this.weight);
        sb.append(", preCalcIsFC=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, this.preCalcIsFC, ", arcAngle=", this.arcAngle, ", arcPoint0=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sb, string3, ", arcPoint1=", string4, ", arcPoint2=");
        return FontRequest$.ExternalSyntheticOutline0.m(sb, string5, ", arcPos=", string6, ")");
    }
}
