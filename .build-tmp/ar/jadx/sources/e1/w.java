package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ x f1956f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(x xVar, int i5) {
        super(1);
        this.f1955e = i5;
        this.f1956f = xVar;
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [a4.a, b4.m] */
    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f1955e) {
            case 0:
                x xVar = this.f1956f;
                xVar.f1959d = true;
                xVar.f1961f.a();
                return p3.n.f5026a;
            default:
                a1.f fVar = (a1.f) obj;
                x xVar2 = this.f1956f;
                b bVar = xVar2.f1957b;
                float f5 = xVar2.f1966k;
                float f6 = xVar2.f1967l;
                a1.b bVarA = fVar.A();
                long jT = bVarA.t();
                bVarA.r().p();
                try {
                    ((a1.d) bVarA.f23d).v(f5, f6, 0L);
                    bVar.a(fVar);
                    return p3.n.f5026a;
                } finally {
                    bVarA.r().m();
                    bVarA.D(jT);
                }
        }
    }
}
