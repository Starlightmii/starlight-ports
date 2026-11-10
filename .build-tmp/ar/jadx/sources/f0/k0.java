package f0;

import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 implements j3 {
    @Override // f0.j3
    public final Object a(w1 w1Var) {
        h3 h3Var = AndroidCompositionLocals_androidKt.f204b;
        w1Var.getClass();
        if (((Context) l.t(w1Var, h3Var)).getPackageManager().hasSystemFeature("android.software.leanback")) {
            return p.g.f4459b;
        }
        p.d.f4424a.getClass();
        return p.c.f4419c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        Object obj2 = p.e.f4433f;
        return obj2.equals(obj2);
    }

    public final int hashCode() {
        return p.e.f4433f.hashCode();
    }

    public final String toString() {
        return "ComputedValueHolder(compute=" + p.e.f4433f + ')';
    }
}
