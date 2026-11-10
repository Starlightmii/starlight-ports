package w;

import b0.t;
import c0.b1;
import j1.d0;
import j1.i;
import j1.j;
import j1.z;
import n1.e0;
import n1.g0;
import n1.h0;
import n1.m0;
import p1.j1;
import p1.m;
import p1.w;
import w0.q;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends m implements w, j1, w0.c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.a f6219s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f6220t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d0 f6221u;

    public b(a4.a aVar) {
        this.f6219s = aVar;
        t tVar = new t(this, null, 19);
        i iVar = z.f3303a;
        d0 d0Var = new d0(null, null, tVar);
        y0(d0Var);
        this.f6221u = d0Var;
    }

    @Override // p1.j1
    public final void W() {
        this.f6221u.W();
    }

    @Override // p1.w
    public final g0 c(h0 h0Var, e0 e0Var, long j5) {
        int iM = h0Var.M(androidx.compose.foundation.text.handwriting.a.f137a);
        int iM2 = h0Var.M(androidx.compose.foundation.text.handwriting.a.f138b);
        int i5 = iM2 * 2;
        int i6 = iM * 2;
        m0 m0VarC = e0Var.c(h1.c.G(i5, i6, j5));
        int i7 = m0VarC.f4131e - i6;
        return h0Var.F(m0VarC.f4130d - i5, i7, q3.t.f5478d, new b1(m0VarC, iM2, iM, 2));
    }

    @Override // w0.c
    public final void k0(q qVar) {
        this.f6220t = qVar.a();
    }

    @Override // p1.j1
    public final void y(i iVar, j jVar, long j5) {
        this.f6221u.y(iVar, jVar, j5);
    }
}
