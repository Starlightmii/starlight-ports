package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b3.w f3785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1 f3786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f3787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f3788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f3789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f3790f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f3791g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f3792h;

    public u(a1.d dVar, a1 a1Var, Object obj, q qVar) {
        b3.w wVar = new b3.w((a1.d) dVar.f31e);
        this.f3785a = wVar;
        this.f3786b = a1Var;
        this.f3787c = obj;
        q qVar2 = (q) a1Var.f3614a.c(obj);
        this.f3788d = qVar2;
        this.f3789e = d.d(qVar);
        a4.c cVar = a1Var.f3615b;
        if (((q) wVar.f659g) == null) {
            wVar.f659g = qVar2.c();
        }
        q qVar3 = (q) wVar.f659g;
        if (qVar3 == null) {
            b4.l.j("targetVector");
            throw null;
        }
        int iB = qVar3.b();
        int i5 = 0;
        while (i5 < iB) {
            q qVar4 = (q) wVar.f659g;
            if (qVar4 == null) {
                b4.l.j("targetVector");
                throw null;
            }
            a1.d dVar2 = (a1.d) wVar.f656d;
            float fA = qVar2.a(i5);
            float fA2 = qVar.a(i5);
            l.e eVar = (l.e) dVar2.f31e;
            double dB = eVar.b(fA2);
            double d5 = l.f.f3491a;
            int i6 = i5;
            qVar4.e((Math.signum(fA2) * ((float) (Math.exp((d5 / (d5 - 1.0d)) * dB) * ((double) (eVar.f3489a * eVar.f3490b))))) + fA, i6);
            i5 = i6 + 1;
        }
        q qVar5 = (q) wVar.f659g;
        if (qVar5 == null) {
            b4.l.j("targetVector");
            throw null;
        }
        this.f3791g = cVar.c(qVar5);
        b3.w wVar2 = this.f3785a;
        q qVar6 = this.f3788d;
        if (((q) wVar2.f658f) == null) {
            wVar2.f658f = qVar6.c();
        }
        q qVar7 = (q) wVar2.f658f;
        if (qVar7 == null) {
            b4.l.j("velocityVector");
            throw null;
        }
        int iB2 = qVar7.b();
        long jMax = 0;
        for (int i7 = 0; i7 < iB2; i7++) {
            a1.d dVar3 = (a1.d) wVar2.f656d;
            qVar6.getClass();
            jMax = Math.max(jMax, ((long) (Math.exp(((l.e) dVar3.f31e).b(qVar.a(i7)) / (((double) l.f.f3491a) - 1.0d)) * 1000.0d)) * 1000000);
        }
        this.f3792h = jMax;
        q qVarD = d.d(this.f3785a.c(jMax, this.f3788d, qVar));
        this.f3790f = qVarD;
        int iB3 = qVarD.b();
        for (int i8 = 0; i8 < iB3; i8++) {
            q qVar8 = this.f3790f;
            float fA3 = qVar8.a(i8);
            this.f3785a.getClass();
            this.f3785a.getClass();
            qVar8.e(d4.a.l(fA3, -0.0f, 0.0f), i8);
        }
    }

    @Override // m.g
    public final boolean a() {
        return false;
    }

    @Override // m.g
    public final Object b(long j5) {
        if (g(j5)) {
            return this.f3791g;
        }
        a4.c cVar = this.f3786b.f3615b;
        b3.w wVar = this.f3785a;
        q qVar = (q) wVar.f657e;
        q qVar2 = this.f3788d;
        if (qVar == null) {
            wVar.f657e = qVar2.c();
        }
        q qVar3 = (q) wVar.f657e;
        if (qVar3 == null) {
            b4.l.j("valueVector");
            throw null;
        }
        int iB = qVar3.b();
        for (int i5 = 0; i5 < iB; i5++) {
            q qVar4 = (q) wVar.f657e;
            if (qVar4 == null) {
                b4.l.j("valueVector");
                throw null;
            }
            a1.d dVar = (a1.d) wVar.f656d;
            float fA = qVar2.a(i5);
            long j6 = j5 / 1000000;
            l.d dVarA = ((l.e) dVar.f31e).a(this.f3789e.a(i5));
            long j7 = dVarA.f3488c;
            qVar4.e((Math.signum(dVarA.f3486a) * dVarA.f3487b * l.b.a(j7 > 0 ? j6 / j7 : 1.0f).f3482a) + fA, i5);
        }
        q qVar5 = (q) wVar.f657e;
        if (qVar5 != null) {
            return cVar.c(qVar5);
        }
        b4.l.j("valueVector");
        throw null;
    }

    @Override // m.g
    public final long c() {
        return this.f3792h;
    }

    @Override // m.g
    public final a1 d() {
        return this.f3786b;
    }

    @Override // m.g
    public final Object e() {
        return this.f3791g;
    }

    @Override // m.g
    public final q f(long j5) {
        if (g(j5)) {
            return this.f3790f;
        }
        return this.f3785a.c(j5, this.f3788d, this.f3789e);
    }
}
