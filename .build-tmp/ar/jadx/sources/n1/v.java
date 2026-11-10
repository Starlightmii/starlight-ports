package n1;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f4172b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Map f4173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ w f4174d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ b0 f4175e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ b4.m f4176f;

    /* JADX WARN: Multi-variable type inference failed */
    public v(int i5, int i6, Map map, w wVar, b0 b0Var, a4.c cVar) {
        this.f4171a = i5;
        this.f4172b = i6;
        this.f4173c = map;
        this.f4174d = wVar;
        this.f4175e = b0Var;
        this.f4176f = (b4.m) cVar;
    }

    @Override // n1.g0
    public final int e() {
        return this.f4172b;
    }

    @Override // n1.g0
    public final int l() {
        return this.f4171a;
    }

    @Override // n1.g0
    public final Map m() {
        return this.f4173c;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [a4.c, b4.m] */
    @Override // n1.g0
    public final void n() {
        p1.s sVar;
        p1.d0 d0Var = this.f4175e.f4091d;
        boolean zO = this.f4174d.o();
        ?? r4 = this.f4176f;
        if (!zO || (sVar = d0Var.f4801y.f4969b.N) == null) {
            r4.c(d0Var.f4801y.f4969b.f4925l);
        } else {
            r4.c(sVar.f4925l);
        }
    }

    @Override // n1.g0
    public final a4.c o() {
        return null;
    }
}
