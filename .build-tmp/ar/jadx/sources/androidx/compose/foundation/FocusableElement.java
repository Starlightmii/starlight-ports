package androidx.compose.foundation;

import b4.l;
import n.c0;
import p1.q0;
import q.k;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class FocusableElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f65a;

    public FocusableElement(k kVar) {
        this.f65a = kVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FocusableElement) {
            return l.a(this.f65a, ((FocusableElement) obj).f65a);
        }
        return false;
    }

    @Override // p1.q0
    public final o h() {
        return new c0(this.f65a);
    }

    public final int hashCode() {
        k kVar = this.f65a;
        if (kVar != null) {
            return kVar.hashCode();
        }
        return 0;
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((c0) oVar).B0(this.f65a);
    }
}
