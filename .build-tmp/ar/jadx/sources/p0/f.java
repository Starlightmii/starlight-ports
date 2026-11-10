package p0;

import f0.k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f4693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4695c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4696d;

    public f(long j5, k kVar) {
        int iA;
        int iNumberOfTrailingZeros;
        this.f4693a = kVar;
        this.f4694b = j5;
        k1 k1Var = m.f4717a;
        if (j5 != 0) {
            k kVarD = d();
            long j6 = kVarD.f4712f;
            long[] jArr = kVarD.f4713g;
            if (jArr != null) {
                j5 = jArr[0];
            } else {
                long j7 = kVarD.f4711e;
                if (j7 != 0) {
                    iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j7);
                } else {
                    long j8 = kVarD.f4710d;
                    if (j8 != 0) {
                        j6 += (long) 64;
                        iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j8);
                    }
                }
                j5 = ((long) iNumberOfTrailingZeros) + j6;
            }
            synchronized (m.f4719c) {
                iA = m.f4722f.a(j5);
            }
        } else {
            iA = -1;
        }
        this.f4696d = iA;
    }

    public static void q(f fVar) {
        m.f4718b.z(fVar);
    }

    public final void a() {
        synchronized (m.f4719c) {
            b();
            p();
        }
    }

    public void b() {
        m.f4720d = m.f4720d.b(g());
    }

    public abstract void c();

    public k d() {
        return this.f4693a;
    }

    public abstract a4.c e();

    public abstract boolean f();

    public long g() {
        return this.f4694b;
    }

    public int h() {
        return 0;
    }

    public abstract a4.c i();

    public final f j() {
        a1.b bVar = m.f4718b;
        f fVar = (f) bVar.q();
        bVar.z(this);
        return fVar;
    }

    public abstract void k();

    public abstract void l();

    public abstract void m();

    public abstract void n(x xVar);

    public final void o() {
        int i5 = this.f4696d;
        if (i5 >= 0) {
            m.u(i5);
            this.f4696d = -1;
        }
    }

    public void p() {
        o();
    }

    public void r(k kVar) {
        this.f4693a = kVar;
    }

    public void s(long j5) {
        this.f4694b = j5;
    }

    public void t(int i5) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot");
    }

    public abstract f u(a4.c cVar);
}
