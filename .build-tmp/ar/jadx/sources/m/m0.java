package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ b4.w f3704e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3705f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g f3706g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ q f3707h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ l f3708i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ float f3709j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ a4.c f3710k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(b4.w wVar, Object obj, g gVar, q qVar, l lVar, float f5, a4.c cVar) {
        super(1);
        this.f3704e = wVar;
        this.f3705f = obj;
        this.f3706g = gVar;
        this.f3707h = qVar;
        this.f3708i = lVar;
        this.f3709j = f5;
        this.f3710k = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        long jLongValue = ((Number) obj).longValue();
        g gVar = this.f3706g;
        j jVar = new j(this.f3705f, gVar.d(), this.f3707h, jLongValue, gVar.e(), jLongValue, new l0(this.f3708i, 0));
        d.e(jVar, jLongValue, this.f3709j, this.f3706g, this.f3708i, this.f3710k);
        this.f3704e.f697d = jVar;
        return p3.n.f5026a;
    }
}
