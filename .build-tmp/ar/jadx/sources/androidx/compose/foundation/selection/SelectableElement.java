package androidx.compose.foundation.selection;

import b4.l;
import m.h;
import n.n0;
import p1.q0;
import q.k;
import r0.o;
import w1.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class SelectableElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n0 f122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a4.a f125f;

    public SelectableElement(boolean z2, k kVar, n0 n0Var, boolean z3, f fVar, a4.a aVar) {
        this.f120a = z2;
        this.f121b = kVar;
        this.f122c = n0Var;
        this.f123d = z3;
        this.f124e = fVar;
        this.f125f = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SelectableElement.class != obj.getClass()) {
            return false;
        }
        SelectableElement selectableElement = (SelectableElement) obj;
        return this.f120a == selectableElement.f120a && l.a(this.f121b, selectableElement.f121b) && l.a(this.f122c, selectableElement.f122c) && this.f123d == selectableElement.f123d && this.f124e.equals(selectableElement.f124e) && this.f125f == selectableElement.f125f;
    }

    @Override // p1.q0
    public final o h() {
        t.b bVar = new t.b(this.f121b, this.f122c, this.f123d, null, this.f124e, this.f125f);
        bVar.K = this.f120a;
        return bVar;
    }

    public final int hashCode() {
        int iHashCode = Boolean.hashCode(this.f120a) * 31;
        k kVar = this.f121b;
        int iHashCode2 = (iHashCode + (kVar != null ? kVar.hashCode() : 0)) * 31;
        n0 n0Var = this.f122c;
        return this.f125f.hashCode() + h.b(this.f124e.f6279a, b.b.c((iHashCode2 + (n0Var != null ? n0Var.hashCode() : 0)) * 31, 31, this.f123d), 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        t.b bVar = (t.b) oVar;
        boolean z2 = bVar.K;
        boolean z3 = this.f120a;
        if (z2 != z3) {
            bVar.K = z3;
            p1.f.n(bVar);
        }
        bVar.E0(this.f121b, this.f122c, this.f123d, null, this.f124e, this.f125f);
    }
}
