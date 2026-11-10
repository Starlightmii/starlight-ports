package v;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f6098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y1.h0 f6099b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6102e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k2.b f6104g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d2.d f6105h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f3.a f6107j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public k2.k f6108k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6100c = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6101d = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6103f = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f6106i = q3.s.f5477d;

    public t0(y1.f fVar, y1.h0 h0Var, boolean z2, k2.b bVar, d2.d dVar, int i5) {
        this.f6098a = fVar;
        this.f6099b = h0Var;
        this.f6102e = z2;
        this.f6104g = bVar;
        this.f6105h = dVar;
    }

    public final void a(k2.k kVar) {
        f3.a aVar = this.f6107j;
        if (aVar == null || kVar != this.f6108k || aVar.b()) {
            this.f6108k = kVar;
            aVar = new f3.a(this.f6098a, p3.p.B(this.f6099b, kVar), this.f6106i, this.f6104g, this.f6105h);
        }
        this.f6107j = aVar;
    }
}
