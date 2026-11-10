package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l1 implements c, m.d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2238d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2239e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f2240f;

    public l1(int i5, int i6, m.v vVar) {
        this.f2238d = i5;
        this.f2239e = i6;
        this.f2240f = new b3.w((m.y) new m.a0(i5, i6, vVar));
    }

    public l1(c cVar, int i5) {
        this.f2240f = cVar;
        this.f2238d = i5;
    }

    @Override // f0.c
    public void c(int i5, Object obj) {
        ((c) this.f2240f).c(i5 + (this.f2239e == 0 ? this.f2238d : 0), obj);
    }

    @Override // m.c1
    public m.q d(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        return ((b3.w) this.f2240f).d(j5, qVar, qVar2, qVar3);
    }

    @Override // f0.c
    public void e(Object obj) {
        this.f2239e++;
        ((c) this.f2240f).e(obj);
    }

    @Override // m.c1
    public m.q f(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        return ((b3.w) this.f2240f).f(j5, qVar, qVar2, qVar3);
    }

    @Override // f0.c
    public void g() {
        ((c) this.f2240f).g();
    }

    @Override // f0.c
    public void h(int i5, Object obj) {
        ((c) this.f2240f).h(i5 + (this.f2239e == 0 ? this.f2238d : 0), obj);
    }

    @Override // f0.c
    public void i(int i5, int i6, int i7) {
        int i8 = this.f2239e == 0 ? this.f2238d : 0;
        ((c) this.f2240f).i(i5 + i8, i6 + i8, i7);
    }

    @Override // f0.c
    public Object j() {
        return ((c) this.f2240f).j();
    }

    @Override // f0.c
    public void k(int i5, int i6) {
        ((c) this.f2240f).k(i5 + (this.f2239e == 0 ? this.f2238d : 0), i6);
    }

    @Override // m.d1
    public int l() {
        return this.f2239e;
    }

    @Override // f0.c
    public void m(a4.e eVar, Object obj) {
        ((c) this.f2240f).m(eVar, obj);
    }

    @Override // m.d1
    public int n() {
        return this.f2238d;
    }

    @Override // f0.c
    public void p() {
        if (!(this.f2239e > 0)) {
            z.a("OffsetApplier up called with no corresponding down");
        }
        this.f2239e--;
        ((c) this.f2240f).p();
    }
}
