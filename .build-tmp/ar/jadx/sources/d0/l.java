package d0;

import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1541e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f1542f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ h0 f1543g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.e f1544h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1545i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(long j5, h0 h0Var, a4.e eVar, int i5, int i6) {
        super(2);
        this.f1541e = i6;
        this.f1542f = j5;
        this.f1543g = h0Var;
        this.f1544h = eVar;
        this.f1545i = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1541e) {
            case 0:
                ((Number) obj2).intValue();
                c.a(this.f1542f, this.f1543g, this.f1544h, (f0.p) obj, f0.l.z(this.f1545i | 1));
                break;
            default:
                ((Number) obj2).intValue();
                u.b(this.f1542f, this.f1543g, this.f1544h, (f0.p) obj, f0.l.z(this.f1545i | 1));
                break;
        }
        return p3.n.f5026a;
    }
}
