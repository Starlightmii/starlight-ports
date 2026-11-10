package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3802e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s0 f3803f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(s0 s0Var, int i5) {
        super(1);
        this.f3802e = i5;
        this.f3803f = s0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3802e) {
            case 0:
                return new w0(this.f3803f, 0);
            default:
                return new w0(this.f3803f, 1);
        }
    }
}
