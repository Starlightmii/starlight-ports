package b0;

import c0.d0;
import p1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class u extends r0.o implements p1.k, p1.o, p1.v {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final q.j f453q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f454r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float f455s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final c0.c0 f456t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d0 f457u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public a0 f458v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f459w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f461y;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f460x = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final j.y f462z = new j.y();

    public u(q.j jVar, boolean z2, float f5, c0.c0 c0Var, d0 d0Var) {
        this.f453q = jVar;
        this.f454r = z2;
        this.f455s = f5;
        this.f456t = c0Var;
        this.f457u = d0Var;
    }

    public final void A0(q.o oVar) {
        if (oVar instanceof q.m) {
            y0((q.m) oVar, this.f460x, this.f459w);
        } else if (oVar instanceof q.n) {
            B0(((q.n) oVar).f5085a);
        } else if (oVar instanceof q.l) {
            B0(((q.l) oVar).f5083a);
        }
    }

    public abstract void B0(q.m mVar);

    @Override // p1.o
    public final void d(f0 f0Var) {
        f0Var.c();
        a0 a0Var = this.f458v;
        if (a0Var != null) {
            a0Var.a(f0Var, this.f459w, this.f456t.a());
        }
        z0(f0Var);
    }

    @Override // p1.v
    public final void m(long j5) {
        this.f461y = true;
        k2.b bVar = p1.f.s(this).f4796t;
        this.f460x = a.a.O(j5);
        float f5 = this.f455s;
        this.f459w = Float.isNaN(f5) ? n.a(bVar, this.f454r, this.f460x) : bVar.v(f5);
        j.y yVar = this.f462z;
        Object[] objArr = yVar.f3174a;
        int i5 = yVar.f3175b;
        for (int i6 = 0; i6 < i5; i6++) {
            A0((q.o) objArr[i6]);
        }
        yVar.b();
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // r0.o
    public final void r0() {
        i4.y.n(n0(), null, new t(this, null, 0), 3);
    }

    public abstract void y0(q.m mVar, long j5, float f5);

    public abstract void z0(f0 f0Var);
}
