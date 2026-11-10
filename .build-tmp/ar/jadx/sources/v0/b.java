package v0;

import b4.l;
import b4.m;
import c0.t2;
import k2.k;
import p1.a1;
import p1.f0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements a1, a, p1.o {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final c f6162q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6163r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.c f6164s;

    public b(c cVar, a4.c cVar2) {
        this.f6162q = cVar;
        this.f6164s = cVar2;
        cVar.f6165d = this;
    }

    @Override // p1.a1
    public final void J() {
        y0();
    }

    @Override // v0.a
    public final k2.b a() {
        return p1.f.s(this).f4796t;
    }

    @Override // v0.a
    public final long b() {
        return a.a.O(p1.f.q(this, 128).f4132f);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [a4.c, b4.m] */
    @Override // p1.o
    public final void d(f0 f0Var) {
        boolean z2 = this.f6163r;
        c cVar = this.f6162q;
        if (!z2) {
            cVar.f6166e = null;
            p1.f.p(this, new t2(this, 12, cVar));
            if (cVar.f6166e == null) {
                d4.a.M("DrawResult not defined, did you forget to call onDraw?");
                throw null;
            }
            this.f6163r = true;
        }
        a1.d dVar = cVar.f6166e;
        l.b(dVar);
        ((m) dVar.f31e).c(f0Var);
    }

    @Override // p1.o
    public final void f0() {
        y0();
    }

    @Override // v0.a
    public final k getLayoutDirection() {
        return p1.f.s(this).f4797u;
    }

    @Override // r0.o
    public final void s0() {
    }

    public final void y0() {
        this.f6163r = false;
        this.f6162q.f6166e = null;
        p1.f.l(this);
    }
}
