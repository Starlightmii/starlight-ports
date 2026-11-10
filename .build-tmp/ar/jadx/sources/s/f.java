package s;

import b0.t;
import b4.m;
import i4.v;
import i4.y;
import p1.y0;
import p3.j;
import p3.n;
import v3.i;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f5752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ g f5753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0 f5754f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ m f5755g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n.i f5756h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public f(g gVar, y0 y0Var, a4.a aVar, n.i iVar, t3.c cVar) {
        super(2, cVar);
        this.f5753e = gVar;
        this.f5754f = y0Var;
        this.f5755g = (m) aVar;
        this.f5756h = iVar;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [a4.a, b4.m] */
    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        f fVar = new f(this.f5753e, this.f5754f, this.f5755g, this.f5756h, cVar);
        fVar.f5752d = obj;
        return fVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((v) obj, (t3.c) obj2)).invokeSuspend(n.f5026a);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [a4.a, b4.m] */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        j.O(obj);
        v vVar = (v) this.f5752d;
        y0 y0Var = this.f5754f;
        ?? r4 = this.f5755g;
        g gVar = this.f5753e;
        y.n(vVar, null, new b0.d(gVar, y0Var, (a4.a) r4, (t3.c) null), 3);
        return y.n(vVar, null, new t(gVar, this.f5756h, null, 16), 3);
    }
}
