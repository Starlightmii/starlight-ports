package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2092d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2093e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2094f;

    public /* synthetic */ b1(Object obj, int i5, Object obj2) {
        this.f2092d = i5;
        this.f2093e = obj;
        this.f2094f = obj2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        long j5;
        switch (this.f2092d) {
            case 0:
                c1 c1Var = (c1) this.f2093e;
                Object obj2 = c1Var.f2103a;
                i4.h hVar = (i4.h) this.f2094f;
                synchronized (obj2) {
                    c1Var.f2104b.remove(hVar);
                }
                return p3.n.f5026a;
            default:
                p0.k kVar = (p0.k) obj;
                synchronized (p0.m.f4719c) {
                    j5 = p0.m.f4721e;
                    p0.m.f4721e = ((long) 1) + j5;
                }
                return new p0.b(j5, kVar, (a4.c) this.f2093e, (a4.c) this.f2094f);
        }
    }
}
