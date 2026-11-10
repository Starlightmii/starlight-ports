package androidx.compose.foundation.layout;

import m.h;
import p1.q0;
import r.f0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class IntrinsicWidthElement extends q0 {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IntrinsicWidthElement ? (IntrinsicWidthElement) obj : null) != null;
    }

    @Override // p1.q0
    public final o h() {
        f0 f0Var = new f0();
        f0Var.f5561q = 2;
        f0Var.f5562r = true;
        return f0Var;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (h.c(2) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        f0 f0Var = (f0) oVar;
        f0Var.f5561q = 2;
        f0Var.f5562r = true;
    }
}
