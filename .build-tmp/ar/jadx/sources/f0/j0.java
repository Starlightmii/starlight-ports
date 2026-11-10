package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j0 extends y1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2204b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2205c;

    public j0() {
        super(new e3.a(1));
        this.f2205c = new k0();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(a4.a aVar) {
        super(aVar);
        h hVar = h.f2189j;
        this.f2205c = hVar;
    }

    @Override // f0.y1
    public final z1 a(Object obj) {
        switch (this.f2204b) {
            case 0:
                return new z1(this, obj, obj == null, null, true);
            default:
                return new z1(this, obj, obj == null, (b3) this.f2205c, true);
        }
    }

    @Override // f0.y1
    public j3 b() {
        switch (this.f2204b) {
            case 0:
                return (k0) this.f2205c;
            default:
                return super.b();
        }
    }
}
