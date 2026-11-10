package l4;

/* JADX INFO: loaded from: classes.dex */
public final class k extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b0.h f3549d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f3550e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3551f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ b0.h f3552g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f3553h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(b0.h hVar, t3.c cVar) {
        super(cVar);
        this.f3552g = hVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f3550e = obj;
        this.f3551f |= Integer.MIN_VALUE;
        return this.f3552g.d(null, this);
    }
}
