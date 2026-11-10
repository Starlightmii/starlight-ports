package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2161e;

    public /* synthetic */ f(int i5, Object obj) {
        this.f2160d = i5;
        this.f2161e = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f(a4.c cVar) {
        this.f2160d = 1;
        this.f2161e = (b4.m) cVar;
    }

    /* JADX WARN: Type inference failed for: r6v7, types: [a4.c, b4.m] */
    @Override // a4.c
    public final Object c(Object obj) {
        long j5;
        switch (this.f2160d) {
            case 0:
                ((i) this.f2161e).cancel();
                return p3.n.f5026a;
            case 1:
                return ((b4.m) this.f2161e).c(Long.valueOf(((Number) obj).longValue() / 1000000));
            default:
                p0.k kVar = (p0.k) obj;
                synchronized (p0.m.f4719c) {
                    j5 = p0.m.f4721e;
                    p0.m.f4721e = ((long) 1) + j5;
                }
                return new p0.e(j5, kVar, (a4.c) this.f2161e);
        }
    }
}
