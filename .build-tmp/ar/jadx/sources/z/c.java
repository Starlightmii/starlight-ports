package z;

import q1.j1;
import q1.o2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o2 f6908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f6909f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f6910g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ r0.p f6911h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ l f6912i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(o2 o2Var, long j5, boolean z2, r0.p pVar, l lVar) {
        super(2);
        this.f6908e = o2Var;
        this.f6909f = j5;
        this.f6910g = z2;
        this.f6911h = pVar;
        this.f6912i = lVar;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001b  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                f0.l.a(j1.f5248q.a(this.f6908e), n0.i.c(-1426434671, new b(this.f6909f, this.f6910g, this.f6911h, this.f6912i), pVar), pVar, 56);
            }
        } else {
            f0.l.a(j1.f5248q.a(this.f6908e), n0.i.c(-1426434671, new b(this.f6909f, this.f6910g, this.f6911h, this.f6912i), pVar), pVar, 56);
        }
        return p3.n.f5026a;
    }
}
