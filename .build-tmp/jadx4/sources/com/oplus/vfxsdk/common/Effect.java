package com.oplus.vfxsdk.common;

import com.android.keyguard.logging.KeyguardLogger$;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Effect {
    private final String cs;
    private final HashMap<String, Uniform> uniforms;
    private final int value;

    public Effect(int i, String str, HashMap<String, Uniform> map) {
        this.value = i;
        this.cs = str;
        this.uniforms = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Effect copy$default(Effect effect, int i, String str, HashMap map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = effect.value;
        }
        if ((i2 & 2) != 0) {
            str = effect.cs;
        }
        if ((i2 & 4) != 0) {
            map = effect.uniforms;
        }
        return effect.copy(i, str, map);
    }

    public final int component1() {
        return this.value;
    }

    public final String component2() {
        return this.cs;
    }

    public final HashMap<String, Uniform> component3() {
        return this.uniforms;
    }

    public final Effect copy(int i, String str, HashMap<String, Uniform> map) {
        return new Effect(i, str, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Effect)) {
            return false;
        }
        Effect effect = (Effect) obj;
        return this.value == effect.value && Intrinsics.areEqual(this.cs, effect.cs) && Intrinsics.areEqual(this.uniforms, effect.uniforms);
    }

    public final String getCs() {
        return this.cs;
    }

    public final HashMap<String, Uniform> getUniforms() {
        return this.uniforms;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.value) * 31;
        String str = this.cs;
        return this.uniforms.hashCode() + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public String toString() {
        int i = this.value;
        String str = this.cs;
        HashMap<String, Uniform> map = this.uniforms;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "Effect(value=", ", cs=", str, ", uniforms=");
        sbM.append(map);
        sbM.append(")");
        return sbM.toString();
    }
}
