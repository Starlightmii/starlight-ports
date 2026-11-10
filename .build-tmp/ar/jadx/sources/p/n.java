package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n implements b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b4.m f4538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f4539b = new m(this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n.t0 f4540c = new n.t0();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f0.s1 f4541d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0.s1 f4542e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f0.s1 f4543f;

    /* JADX WARN: Multi-variable type inference failed */
    public n(a4.c cVar) {
        this.f4538a = (b4.m) cVar;
        Boolean bool = Boolean.FALSE;
        this.f4541d = f0.l.s(bool);
        this.f4542e = f0.l.s(bool);
        this.f4543f = f0.l.s(bool);
    }

    @Override // p.b1
    public final boolean b() {
        return ((Boolean) this.f4541d.getValue()).booleanValue();
    }

    @Override // p.b1
    public final Object d(int i5, b0.d dVar, v3.c cVar) {
        Object objC = i4.y.c(new l(this, i5, dVar, null), cVar);
        return objC == u3.a.f5838d ? objC : p3.n.f5026a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [a4.c, b4.m] */
    @Override // p.b1
    public final float e(float f5) {
        return ((Number) this.f4538a.c(Float.valueOf(f5))).floatValue();
    }
}
