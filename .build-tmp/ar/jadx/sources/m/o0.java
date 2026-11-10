package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c1 f3721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1 f3722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f3723c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f3724d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f3725e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f3726f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q f3727g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3728h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public q f3729i;

    public o0(k kVar, a1 a1Var, Object obj, Object obj2, q qVar) {
        this.f3721a = kVar.a(a1Var);
        this.f3722b = a1Var;
        this.f3723c = obj2;
        this.f3724d = obj;
        this.f3725e = (q) a1Var.f3614a.c(obj);
        a4.c cVar = a1Var.f3614a;
        this.f3726f = (q) cVar.c(obj2);
        this.f3727g = qVar != null ? d.d(qVar) : ((q) cVar.c(obj)).c();
        this.f3728h = -1L;
    }

    @Override // m.g
    public final boolean a() {
        return this.f3721a.a();
    }

    @Override // m.g
    public final Object b(long j5) {
        if (g(j5)) {
            return this.f3723c;
        }
        q qVarF = this.f3721a.f(j5, this.f3725e, this.f3726f, this.f3727g);
        int iB = qVarF.b();
        for (int i5 = 0; i5 < iB; i5++) {
            if (Float.isNaN(qVarF.a(i5))) {
                throw new IllegalStateException("AnimationVector cannot contain a NaN. " + qVarF + ". Animation: " + this + ", playTimeNanos: " + j5);
            }
        }
        return this.f3722b.f3615b.c(qVarF);
    }

    @Override // m.g
    public final long c() {
        if (this.f3728h < 0) {
            this.f3728h = this.f3721a.b(this.f3725e, this.f3726f, this.f3727g);
        }
        return this.f3728h;
    }

    @Override // m.g
    public final a1 d() {
        return this.f3722b;
    }

    @Override // m.g
    public final Object e() {
        return this.f3723c;
    }

    @Override // m.g
    public final q f(long j5) {
        if (!g(j5)) {
            return this.f3721a.d(j5, this.f3725e, this.f3726f, this.f3727g);
        }
        q qVar = this.f3729i;
        if (qVar != null) {
            return qVar;
        }
        q qVarO = this.f3721a.o(this.f3725e, this.f3726f, this.f3727g);
        this.f3729i = qVarO;
        return qVarO;
    }

    public final String toString() {
        return "TargetBasedAnimation: " + this.f3724d + " -> " + this.f3723c + ",initial velocity: " + this.f3727g + ", duration: " + (c() / 1000000) + " ms,animationSpec: " + this.f3721a;
    }
}
