package androidx.compose.foundation.selection;

import a4.c;
import b4.l;
import m.h;
import p1.q0;
import q.k;
import r0.o;
import w1.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class ToggleableElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f127b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f128c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f129d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f130e;

    public ToggleableElement(boolean z2, k kVar, boolean z3, f fVar, c cVar) {
        this.f126a = z2;
        this.f127b = kVar;
        this.f128c = z3;
        this.f129d = fVar;
        this.f130e = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ToggleableElement.class != obj.getClass()) {
            return false;
        }
        ToggleableElement toggleableElement = (ToggleableElement) obj;
        return this.f126a == toggleableElement.f126a && l.a(this.f127b, toggleableElement.f127b) && this.f128c == toggleableElement.f128c && this.f129d.equals(toggleableElement.f129d) && this.f130e == toggleableElement.f130e;
    }

    @Override // p1.q0
    public final o h() {
        return new t.c(this.f126a, this.f127b, this.f128c, this.f129d, this.f130e);
    }

    public final int hashCode() {
        int iHashCode = Boolean.hashCode(this.f126a) * 31;
        k kVar = this.f127b;
        return this.f130e.hashCode() + h.b(this.f129d.f6279a, b.b.c((iHashCode + (kVar != null ? kVar.hashCode() : 0)) * 961, 31, this.f128c), 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        t.c cVar = (t.c) oVar;
        boolean z2 = cVar.K;
        boolean z3 = this.f126a;
        if (z2 != z3) {
            cVar.K = z3;
            p1.f.n(cVar);
        }
        cVar.L = this.f130e;
        cVar.E0(this.f127b, null, this.f128c, null, this.f129d, cVar.M);
    }
}
