package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a1 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5855e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c1 f5856f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a1(c1 c1Var, int i5) {
        super(0);
        this.f5855e = i5;
        this.f5856f = c1Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f5855e) {
            case 0:
                return Boolean.valueOf(this.f5856f.f5869a.g() > 0.0f);
            default:
                c1 c1Var = this.f5856f;
                return Boolean.valueOf(c1Var.f5869a.g() < c1Var.f5870b.g());
        }
    }
}
