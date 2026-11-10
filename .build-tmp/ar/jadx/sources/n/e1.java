package n;

import p1.l1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 extends r0.o implements l1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public h1 f3896q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3897r;

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        f4.d[] dVarArr = w1.s.f6363a;
        w1.t tVar = w1.q.f6347l;
        f4.d[] dVarArr2 = w1.s.f6363a;
        f4.d dVar = dVarArr2[6];
        tVar.a(iVar, Boolean.TRUE);
        w1.g gVar = new w1.g(new d1(this, 0), new d1(this, 1));
        if (this.f3897r) {
            w1.t tVar2 = w1.q.f6351p;
            f4.d dVar2 = dVarArr2[11];
            tVar2.a(iVar, gVar);
        } else {
            w1.t tVar3 = w1.q.f6350o;
            f4.d dVar3 = dVarArr2[10];
            tVar3.a(iVar, gVar);
        }
    }
}
