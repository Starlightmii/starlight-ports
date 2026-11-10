package com.oplus.vfxsdk.common;

import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RendPass {
    private final String cs;
    private final Effect[] effects;
    private final String fs;
    private final int order;
    private final StatusAnim[] status;
    private final HashMap<String, Uniform> uniforms;
    private final String vs;

    public RendPass(String str, String str2, int i, HashMap<String, Uniform> map, StatusAnim[] statusAnimArr, String str3, Effect[] effectArr) {
        this.vs = str;
        this.fs = str2;
        this.order = i;
        this.uniforms = map;
        this.status = statusAnimArr;
        this.cs = str3;
        this.effects = effectArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RendPass copy$default(RendPass rendPass, String str, String str2, int i, HashMap map, StatusAnim[] statusAnimArr, String str3, Effect[] effectArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = rendPass.vs;
        }
        if ((i2 & 2) != 0) {
            str2 = rendPass.fs;
        }
        if ((i2 & 4) != 0) {
            i = rendPass.order;
        }
        if ((i2 & 8) != 0) {
            map = rendPass.uniforms;
        }
        if ((i2 & 16) != 0) {
            statusAnimArr = rendPass.status;
        }
        if ((i2 & 32) != 0) {
            str3 = rendPass.cs;
        }
        if ((i2 & 64) != 0) {
            effectArr = rendPass.effects;
        }
        String str4 = str3;
        Effect[] effectArr2 = effectArr;
        StatusAnim[] statusAnimArr2 = statusAnimArr;
        int i3 = i;
        return rendPass.copy(str, str2, i3, map, statusAnimArr2, str4, effectArr2);
    }

    public final String component1() {
        return this.vs;
    }

    public final String component2() {
        return this.fs;
    }

    public final int component3() {
        return this.order;
    }

    public final HashMap<String, Uniform> component4() {
        return this.uniforms;
    }

    public final StatusAnim[] component5() {
        return this.status;
    }

    public final String component6() {
        return this.cs;
    }

    public final Effect[] component7() {
        return this.effects;
    }

    public final RendPass copy(String str, String str2, int i, HashMap<String, Uniform> map, StatusAnim[] statusAnimArr, String str3, Effect[] effectArr) {
        return new RendPass(str, str2, i, map, statusAnimArr, str3, effectArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RendPass)) {
            return false;
        }
        RendPass rendPass = (RendPass) obj;
        return Intrinsics.areEqual(this.vs, rendPass.vs) && Intrinsics.areEqual(this.fs, rendPass.fs) && this.order == rendPass.order && Intrinsics.areEqual(this.uniforms, rendPass.uniforms) && Intrinsics.areEqual(this.status, rendPass.status) && Intrinsics.areEqual(this.cs, rendPass.cs) && Intrinsics.areEqual(this.effects, rendPass.effects);
    }

    public final String getCs() {
        return this.cs;
    }

    public final Effect[] getEffects() {
        return this.effects;
    }

    public final String getFs() {
        return this.fs;
    }

    public final int getOrder() {
        return this.order;
    }

    public final StatusAnim[] getStatus() {
        return this.status;
    }

    public final HashMap<String, Uniform> getUniforms() {
        return this.uniforms;
    }

    public final String getVs() {
        return this.vs;
    }

    public int hashCode() {
        int iHashCode = (this.uniforms.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.order, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.vs.hashCode() * 31, 31, this.fs), 31)) * 31;
        StatusAnim[] statusAnimArr = this.status;
        int iHashCode2 = (iHashCode + (statusAnimArr == null ? 0 : Arrays.hashCode(statusAnimArr))) * 31;
        String str = this.cs;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Effect[] effectArr = this.effects;
        return iHashCode3 + (effectArr != null ? Arrays.hashCode(effectArr) : 0);
    }

    public String toString() {
        String str = this.vs;
        String str2 = this.fs;
        int i = this.order;
        HashMap<String, Uniform> map = this.uniforms;
        String string = Arrays.toString(this.status);
        String str3 = this.cs;
        String string2 = Arrays.toString(this.effects);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("RendPass(vs=", str, ", fs=", str2, ", order=");
        sbM.append(i);
        sbM.append(", uniforms=");
        sbM.append(map);
        sbM.append(", status=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, string, ", cs=", str3, ", effects=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sbM, string2, ")");
    }

    public /* synthetic */ RendPass(String str, String str2, int i, HashMap map, StatusAnim[] statusAnimArr, String str3, Effect[] effectArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, map, statusAnimArr, (i2 & 32) != 0 ? null : str3, effectArr);
    }
}
