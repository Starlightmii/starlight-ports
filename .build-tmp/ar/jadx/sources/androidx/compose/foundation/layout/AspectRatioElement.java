package androidx.compose.foundation.layout;

import p1.q0;
import r.k;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class AspectRatioElement extends q0 {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof AspectRatioElement ? (AspectRatioElement) obj : null) == null) {
            return false;
        }
        ((AspectRatioElement) obj).getClass();
        return true;
    }

    @Override // p1.q0
    public final o h() {
        k kVar = new k();
        kVar.f5576q = 1.0f;
        return kVar;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + (Float.hashCode(1.0f) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((k) oVar).f5576q = 1.0f;
    }
}
