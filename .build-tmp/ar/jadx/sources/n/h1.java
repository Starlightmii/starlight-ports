package n;

import f0.p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h1 implements p.b1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a2.h f3919i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p1 f3920a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3924e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p1 f3921b = new p1(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q.k f3922c = new q.k();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p1 f3923d = new p1(Integer.MAX_VALUE);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p.n f3925f = new p.n(new c0.g1(11, this));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f0.n0 f3926g = f0.l.n(new g1(this, 1));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f0.n0 f3927h = f0.l.n(new g1(this, 0));

    static {
        l lVar = l.f3956i;
        f3919i = new a2.h(f1.f3904e, 15, lVar);
    }

    public h1(int i5) {
        this.f3920a = new p1(i5);
    }

    @Override // p.b1
    public final boolean a() {
        return ((Boolean) this.f3927h.getValue()).booleanValue();
    }

    @Override // p.b1
    public final boolean b() {
        return this.f3925f.b();
    }

    @Override // p.b1
    public final boolean c() {
        return ((Boolean) this.f3926g.getValue()).booleanValue();
    }

    @Override // p.b1
    public final Object d(int i5, b0.d dVar, v3.c cVar) {
        Object objD = this.f3925f.d(i5, dVar, cVar);
        return objD == u3.a.f5838d ? objD : p3.n.f5026a;
    }

    @Override // p.b1
    public final float e(float f5) {
        return this.f3925f.e(f5);
    }
}
