package p1;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 implements n1.g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4914a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f4915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Map f4916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ b4.m f4917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m0 f4918e;

    /* JADX WARN: Multi-variable type inference failed */
    public k0(int i5, int i6, Map map, a4.c cVar, m0 m0Var) {
        this.f4914a = i5;
        this.f4915b = i6;
        this.f4916c = map;
        this.f4917d = (b4.m) cVar;
        this.f4918e = m0Var;
    }

    @Override // n1.g0
    public final int e() {
        return this.f4915b;
    }

    @Override // n1.g0
    public final int l() {
        return this.f4914a;
    }

    @Override // n1.g0
    public final Map m() {
        return this.f4916c;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [a4.c, b4.m] */
    @Override // n1.g0
    public final void n() {
        this.f4917d.c(this.f4918e.f4925l);
    }

    @Override // n1.g0
    public final a4.c o() {
        return null;
    }
}
