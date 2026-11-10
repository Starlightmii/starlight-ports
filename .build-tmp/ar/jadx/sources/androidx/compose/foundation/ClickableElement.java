package androidx.compose.foundation;

import b4.l;
import n.n0;
import n.t;
import p1.q0;
import q.k;
import r0.o;
import w1.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class ClickableElement extends q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f59a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n0 f60b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f61c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f62d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f63e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a4.a f64f;

    public ClickableElement(k kVar, n0 n0Var, boolean z2, String str, f fVar, a4.a aVar) {
        this.f59a = kVar;
        this.f60b = n0Var;
        this.f61c = z2;
        this.f62d = str;
        this.f63e = fVar;
        this.f64f = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ClickableElement.class != obj.getClass()) {
            return false;
        }
        ClickableElement clickableElement = (ClickableElement) obj;
        return l.a(this.f59a, clickableElement.f59a) && l.a(this.f60b, clickableElement.f60b) && this.f61c == clickableElement.f61c && l.a(this.f62d, clickableElement.f62d) && l.a(this.f63e, clickableElement.f63e) && this.f64f == clickableElement.f64f;
    }

    @Override // p1.q0
    public final o h() {
        return new t(this.f59a, this.f60b, this.f61c, this.f62d, this.f63e, this.f64f);
    }

    public final int hashCode() {
        k kVar = this.f59a;
        int iHashCode = (kVar != null ? kVar.hashCode() : 0) * 31;
        n0 n0Var = this.f60b;
        int iC = b.b.c((iHashCode + (n0Var != null ? n0Var.hashCode() : 0)) * 31, 31, this.f61c);
        String str = this.f62d;
        int iHashCode2 = (iC + (str != null ? str.hashCode() : 0)) * 31;
        f fVar = this.f63e;
        return this.f64f.hashCode() + ((iHashCode2 + (fVar != null ? Integer.hashCode(fVar.f6279a) : 0)) * 31);
    }

    @Override // p1.q0
    public final void i(o oVar) {
        ((t) oVar).E0(this.f59a, this.f60b, this.f61c, this.f62d, this.f63e, this.f64f);
    }
}
