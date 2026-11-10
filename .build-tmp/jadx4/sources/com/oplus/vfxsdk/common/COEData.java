package com.oplus.vfxsdk.common;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class COEData {
    private HashMap<String, AnimatorValue> animParams;
    private long cT;
    private long cV;
    private COEExpressions expressions;
    private Layer[] layers;
    private int mV;
    private String name;
    private int v;

    public /* synthetic */ COEData(String str, int i, int i2, long j, long j2, Layer[] layerArr, COEExpressions cOEExpressions, HashMap map, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? 0L : j, (i3 & 16) != 0 ? 1L : j2, layerArr, cOEExpressions, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ COEData copy$default(COEData cOEData, String str, int i, int i2, long j, long j2, Layer[] layerArr, COEExpressions cOEExpressions, HashMap map, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = cOEData.name;
        }
        if ((i3 & 2) != 0) {
            i = cOEData.v;
        }
        if ((i3 & 4) != 0) {
            i2 = cOEData.mV;
        }
        if ((i3 & 8) != 0) {
            j = cOEData.cT;
        }
        if ((i3 & 16) != 0) {
            j2 = cOEData.cV;
        }
        if ((i3 & 32) != 0) {
            layerArr = cOEData.layers;
        }
        if ((i3 & 64) != 0) {
            cOEExpressions = cOEData.expressions;
        }
        if ((i3 & 128) != 0) {
            map = cOEData.animParams;
        }
        HashMap map2 = map;
        Layer[] layerArr2 = layerArr;
        long j3 = j2;
        long j4 = j;
        int i4 = i2;
        return cOEData.copy(str, i, i4, j4, j3, layerArr2, cOEExpressions, map2);
    }

    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.v;
    }

    public final int component3() {
        return this.mV;
    }

    public final long component4() {
        return this.cT;
    }

    public final long component5() {
        return this.cV;
    }

    public final Layer[] component6() {
        return this.layers;
    }

    public final COEExpressions component7() {
        return this.expressions;
    }

    public final HashMap<String, AnimatorValue> component8() {
        return this.animParams;
    }

    public final COEData copy(String str, int i, int i2, long j, long j2, Layer[] layerArr, COEExpressions cOEExpressions, HashMap<String, AnimatorValue> map) {
        return new COEData(str, i, i2, j, j2, layerArr, cOEExpressions, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof COEData)) {
            return false;
        }
        COEData cOEData = (COEData) obj;
        return Intrinsics.areEqual(this.name, cOEData.name) && this.v == cOEData.v && this.mV == cOEData.mV && this.cT == cOEData.cT && this.cV == cOEData.cV && Intrinsics.areEqual(this.layers, cOEData.layers) && Intrinsics.areEqual(this.expressions, cOEData.expressions) && Intrinsics.areEqual(this.animParams, cOEData.animParams);
    }

    public final HashMap<String, AnimatorValue> getAnimParams() {
        return this.animParams;
    }

    public final long getCT() {
        return this.cT;
    }

    public final long getCV() {
        return this.cV;
    }

    public final COEExpressions getExpressions() {
        return this.expressions;
    }

    public final Layer[] getLayers() {
        return this.layers;
    }

    public final int getMV() {
        return this.mV;
    }

    public final String getName() {
        return this.name;
    }

    public final int getV() {
        return this.v;
    }

    public int hashCode() {
        int iHashCode = (this.expressions.hashCode() + ((Arrays.hashCode(this.layers) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.mV, MonoSpline$.ExternalSyntheticOutline0.m(this.v, this.name.hashCode() * 31, 31), 31), 31, this.cT), 31, this.cV)) * 31)) * 31;
        HashMap<String, AnimatorValue> map = this.animParams;
        return iHashCode + (map == null ? 0 : map.hashCode());
    }

    public final void setAnimParams(HashMap<String, AnimatorValue> map) {
        this.animParams = map;
    }

    public final void setCT(long j) {
        this.cT = j;
    }

    public final void setCV(long j) {
        this.cV = j;
    }

    public final void setExpressions(COEExpressions cOEExpressions) {
        this.expressions = cOEExpressions;
    }

    public final void setLayers(Layer[] layerArr) {
        this.layers = layerArr;
    }

    public final void setMV(int i) {
        this.mV = i;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setV(int i) {
        this.v = i;
    }

    public String toString() {
        String str = this.name;
        int i = this.v;
        int i2 = this.mV;
        long j = this.cT;
        long j2 = this.cV;
        String string = Arrays.toString(this.layers);
        COEExpressions cOEExpressions = this.expressions;
        HashMap<String, AnimatorValue> map = this.animParams;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "COEData(name=", str, ", v=", ", mV=");
        sbM.append(i2);
        sbM.append(", cT=");
        sbM.append(j);
        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ", cV=", j2, ", layers=");
        sbM.append(string);
        sbM.append(", expressions=");
        sbM.append(cOEExpressions);
        sbM.append(", animParams=");
        sbM.append(map);
        sbM.append(")");
        return sbM.toString();
    }

    public COEData(String str, int i, int i2, long j, long j2, Layer[] layerArr, COEExpressions cOEExpressions, HashMap<String, AnimatorValue> map) {
        this.name = str;
        this.v = i;
        this.mV = i2;
        this.cT = j;
        this.cV = j2;
        this.layers = layerArr;
        this.expressions = cOEExpressions;
        this.animParams = map;
    }
}
