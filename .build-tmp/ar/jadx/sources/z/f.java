package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6948e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6949f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f6950g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f6951h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f6952i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(r0.p pVar, a4.a aVar, boolean z2, int i5) {
        super(2);
        this.f6951h = pVar;
        this.f6952i = aVar;
        this.f6949f = z2;
        this.f6950g = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(boolean z2, j2.h hVar, e0 e0Var, int i5) {
        super(2);
        this.f6949f = z2;
        this.f6951h = hVar;
        this.f6952i = e0Var;
        this.f6950g = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f6948e;
        f0.p pVar = (f0.p) obj;
        ((Number) obj2).intValue();
        switch (i5) {
            case 0:
                p3.j.d((r0.p) this.f6951h, (a4.a) this.f6952i, this.f6949f, pVar, f0.l.z(this.f6950g | 1));
                break;
            default:
                p3.p.d(this.f6949f, (j2.h) this.f6951h, (e0) this.f6952i, pVar, f0.l.z(this.f6950g | 1));
                break;
        }
        return p3.n.f5026a;
    }
}
