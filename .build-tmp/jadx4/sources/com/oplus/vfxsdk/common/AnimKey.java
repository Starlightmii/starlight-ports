package com.oplus.vfxsdk.common;

import android.animation.TimeInterpolator;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimKey implements IKey {
    private final float[] bezier;
    private float cacheValue;
    private String expr;
    private TimeInterpolator interpolator;
    private final String ipol;
    private final float[] spring;
    private final float time;
    private final String type;
    private float value;

    public AnimKey(float f, float f2, String str, float f3, String str2, float[] fArr, float[] fArr2, String str3, TimeInterpolator timeInterpolator) {
        this.time = f;
        this.value = f2;
        this.type = str;
        this.cacheValue = f3;
        this.ipol = str2;
        this.bezier = fArr;
        this.spring = fArr2;
        this.expr = str3;
        this.interpolator = timeInterpolator;
    }

    public static /* synthetic */ AnimKey copy$default(AnimKey animKey, float f, float f2, String str, float f3, String str2, float[] fArr, float[] fArr2, String str3, TimeInterpolator timeInterpolator, int i, Object obj) {
        if ((i & 1) != 0) {
            f = animKey.time;
        }
        if ((i & 2) != 0) {
            f2 = animKey.value;
        }
        if ((i & 4) != 0) {
            str = animKey.type;
        }
        if ((i & 8) != 0) {
            f3 = animKey.cacheValue;
        }
        if ((i & 16) != 0) {
            str2 = animKey.ipol;
        }
        if ((i & 32) != 0) {
            fArr = animKey.bezier;
        }
        if ((i & 64) != 0) {
            fArr2 = animKey.spring;
        }
        if ((i & 128) != 0) {
            str3 = animKey.expr;
        }
        if ((i & 256) != 0) {
            timeInterpolator = animKey.interpolator;
        }
        String str4 = str3;
        TimeInterpolator timeInterpolator2 = timeInterpolator;
        float[] fArr3 = fArr;
        float[] fArr4 = fArr2;
        String str5 = str2;
        String str6 = str;
        return animKey.copy(f, f2, str6, f3, str5, fArr3, fArr4, str4, timeInterpolator2);
    }

    public final float component1() {
        return this.time;
    }

    public final float component2() {
        return this.value;
    }

    public final String component3() {
        return this.type;
    }

    public final float component4() {
        return this.cacheValue;
    }

    public final String component5() {
        return this.ipol;
    }

    public final float[] component6() {
        return this.bezier;
    }

    public final float[] component7() {
        return this.spring;
    }

    public final String component8() {
        return this.expr;
    }

    public final TimeInterpolator component9() {
        return this.interpolator;
    }

    public final AnimKey copy(float f, float f2, String str, float f3, String str2, float[] fArr, float[] fArr2, String str3, TimeInterpolator timeInterpolator) {
        return new AnimKey(f, f2, str, f3, str2, fArr, fArr2, str3, timeInterpolator);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimKey)) {
            return false;
        }
        AnimKey animKey = (AnimKey) obj;
        return Float.compare(this.time, animKey.time) == 0 && Float.compare(this.value, animKey.value) == 0 && Intrinsics.areEqual(this.type, animKey.type) && Float.compare(this.cacheValue, animKey.cacheValue) == 0 && Intrinsics.areEqual(this.ipol, animKey.ipol) && Intrinsics.areEqual(this.bezier, animKey.bezier) && Intrinsics.areEqual(this.spring, animKey.spring) && Intrinsics.areEqual(this.expr, animKey.expr) && Intrinsics.areEqual(this.interpolator, animKey.interpolator);
    }

    public final float[] getBezier() {
        return this.bezier;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getCacheValue() {
        return this.cacheValue;
    }

    public final String getExpr() {
        return this.expr;
    }

    public final TimeInterpolator getInterpolator() {
        return this.interpolator;
    }

    public final String getIpol() {
        return this.ipol;
    }

    public final float[] getSpring() {
        return this.spring;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getTime() {
        return this.time;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public String getType() {
        return this.type;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public float getValue() {
        return this.value;
    }

    public int hashCode() {
        int iM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.spring, DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(this.bezier, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.cacheValue, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.value, Float.hashCode(this.time) * 31, 31), 31, this.type), 31), 31, this.ipol), 31), 31);
        String str = this.expr;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        TimeInterpolator timeInterpolator = this.interpolator;
        return iHashCode + (timeInterpolator != null ? timeInterpolator.hashCode() : 0);
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public void setCacheValue(float f) {
        this.cacheValue = f;
    }

    public final void setExpr(String str) {
        this.expr = str;
    }

    public final void setInterpolator(TimeInterpolator timeInterpolator) {
        this.interpolator = timeInterpolator;
    }

    @Override // com.oplus.vfxsdk.common.IKey
    public void setValue(float f) {
        this.value = f;
    }

    public String toString() {
        float f = this.time;
        float f2 = this.value;
        String str = this.type;
        float f3 = this.cacheValue;
        String str2 = this.ipol;
        String string = Arrays.toString(this.bezier);
        String string2 = Arrays.toString(this.spring);
        String str3 = this.expr;
        TimeInterpolator timeInterpolator = this.interpolator;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("AnimKey(time=", f, ", value=", f2, ", type=");
        sbM.append(str);
        sbM.append(", cacheValue=");
        sbM.append(f3);
        sbM.append(", ipol=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str2, ", bezier=", string, ", spring=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, string2, ", expr=", str3, ", interpolator=");
        sbM.append(timeInterpolator);
        sbM.append(")");
        return sbM.toString();
    }

    public /* synthetic */ AnimKey(float f, float f2, String str, float f3, String str2, float[] fArr, float[] fArr2, String str3, TimeInterpolator timeInterpolator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, str, f3, str2, fArr, fArr2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : timeInterpolator);
    }
}
