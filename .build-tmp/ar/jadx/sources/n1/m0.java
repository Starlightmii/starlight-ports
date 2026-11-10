package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4132f = a.a.f(0, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f4133g = o0.f4143a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f4134h = 0;

    public abstract int f0(l lVar);

    public final int g0() {
        return (int) (this.f4132f >> 32);
    }

    public Object h() {
        return null;
    }

    public final void h0() {
        this.f4130d = d4.a.m((int) (this.f4132f >> 32), k2.a.k(this.f4133g), k2.a.i(this.f4133g));
        int iM = d4.a.m((int) (this.f4132f & 4294967295L), k2.a.j(this.f4133g), k2.a.h(this.f4133g));
        this.f4131e = iM;
        int i5 = this.f4130d;
        long j5 = this.f4132f;
        this.f4134h = h1.c.d((i5 - ((int) (j5 >> 32))) / 2, (iM - ((int) (j5 & 4294967295L))) / 2);
    }

    public abstract void k0(long j5, float f5, a4.c cVar);

    public final void m0(long j5) {
        if (k2.j.a(this.f4132f, j5)) {
            return;
        }
        this.f4132f = j5;
        h0();
    }

    public final void n0(long j5) {
        if (k2.a.c(this.f4133g, j5)) {
            return;
        }
        this.f4133g = j5;
        h0();
    }
}
