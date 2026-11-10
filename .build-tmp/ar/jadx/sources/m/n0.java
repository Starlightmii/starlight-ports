package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ b4.w f3713e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f3714f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g f3715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ l f3716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.c f3717i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(b4.w wVar, float f5, g gVar, l lVar, a4.c cVar) {
        super(1);
        this.f3713e = wVar;
        this.f3714f = f5;
        this.f3715g = gVar;
        this.f3716h = lVar;
        this.f3717i = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        long jLongValue = ((Number) obj).longValue();
        Object obj2 = this.f3713e.f697d;
        b4.l.b(obj2);
        d.e((j) obj2, jLongValue, this.f3714f, this.f3715g, this.f3716h, this.f3717i);
        return p3.n.f5026a;
    }
}
