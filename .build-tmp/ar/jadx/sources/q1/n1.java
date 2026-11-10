package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f5292e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ j3.e f5293f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ String f5294g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n1(boolean z2, j3.e eVar, String str) {
        super(0);
        this.f5292e = z2;
        this.f5293f = eVar;
        this.f5294g = str;
    }

    @Override // a4.a
    public final Object a() {
        if (this.f5292e) {
            j3.e eVar = this.f5293f;
            String str = this.f5294g;
            k3.b bVar = eVar.f3341a;
            synchronized (((androidx.lifecycle.k0) bVar.f3432f)) {
            }
        }
        return p3.n.f5026a;
    }
}
