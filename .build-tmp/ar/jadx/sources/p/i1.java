package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b1 f4484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n.x0 f4485b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a2.h f4486c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l0 f4487d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4488e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a1.b f4489f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4490g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p0 f4491h = androidx.compose.foundation.gestures.a.f86a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g1 f4492i = new g1(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final c0.g1 f4493j = new c0.g1(16, this);

    public i1(b1 b1Var, n.x0 x0Var, a2.h hVar, l0 l0Var, boolean z2, a1.b bVar) {
        this.f4484a = b1Var;
        this.f4485b = x0Var;
        this.f4486c = hVar;
        this.f4487d = l0Var;
        this.f4488e = z2;
        this.f4489f = bVar;
    }

    public static final long a(i1 i1Var, p0 p0Var, long j5, int i5) {
        i1.e eVar = (i1.e) i1Var.f4489f.f23d;
        i1.e eVar2 = null;
        i1.e eVar3 = (eVar == null || !eVar.f5693p) ? null : (i1.e) p1.f.j(eVar);
        long jC0 = eVar3 != null ? eVar3.C0(j5, i5) : 0L;
        long jG = x0.c.g(j5, jC0);
        long jD = i1Var.d(i1Var.g(p0Var.a(i1Var.f(i1Var.d(x0.c.a(jG, 0.0f, i1Var.f4487d == l0.f4526e ? 1 : 2))))));
        long jG2 = x0.c.g(jG, jD);
        i1.e eVar4 = (i1.e) i1Var.f4489f.f23d;
        if (eVar4 != null && eVar4.f5693p) {
            eVar2 = (i1.e) p1.f.j(eVar4);
        }
        i1.e eVar5 = eVar2;
        return x0.c.h(x0.c.h(jC0, jD), eVar5 != null ? eVar5.A0(jD, jG2, i5) : 0L);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object b(long j5, v3.c cVar) {
        d1 d1Var;
        b4.v vVar;
        if (cVar instanceof d1) {
            d1Var = (d1) cVar;
            int i5 = d1Var.f4432g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                d1Var.f4432g = i5 - Integer.MIN_VALUE;
            } else {
                d1Var = new d1(this, cVar);
            }
        } else {
            d1Var = new d1(this, cVar);
        }
        Object obj = d1Var.f4430e;
        int i6 = d1Var.f4432g;
        if (i6 == 0) {
            p3.j.O(obj);
            b4.v vVar2 = new b4.v();
            vVar2.f696d = j5;
            a4.e f1Var = new f1(this, vVar2, j5, null);
            d1Var.f4429d = vVar2;
            d1Var.f4432g = 1;
            Object objE = e(1, f1Var, d1Var);
            Object obj2 = u3.a.f5838d;
            if (objE == obj2) {
                return obj2;
            }
            vVar = vVar2;
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            vVar = d1Var.f4429d;
            p3.j.O(obj);
        }
        return new k2.o(vVar.f696d);
    }

    public final float c(float f5) {
        return this.f4488e ? f5 * (-1) : f5;
    }

    public final long d(long j5) {
        return this.f4488e ? x0.c.i(-1.0f, j5) : j5;
    }

    public final Object e(int i5, a4.e eVar, v3.c cVar) {
        Object objD = this.f4484a.d(i5, new b0.d(this, eVar, null), cVar);
        return objD == u3.a.f5838d ? objD : p3.n.f5026a;
    }

    public final float f(long j5) {
        return this.f4487d == l0.f4526e ? x0.c.d(j5) : x0.c.e(j5);
    }

    public final long g(float f5) {
        if (f5 == 0.0f) {
            return 0L;
        }
        return this.f4487d == l0.f4526e ? p3.j.b(f5, 0.0f) : p3.j.b(0.0f, f5);
    }
}
