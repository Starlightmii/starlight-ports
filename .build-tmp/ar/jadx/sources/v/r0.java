package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u0 f6065f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r0(u0 u0Var, int i5) {
        super(0);
        this.f6064e = i5;
        this.f6065f = u0Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f6064e) {
            case 0:
                this.f6065f.a();
                break;
            default:
                this.f6065f.onCancel();
                break;
        }
        return p3.n.f5026a;
    }
}
