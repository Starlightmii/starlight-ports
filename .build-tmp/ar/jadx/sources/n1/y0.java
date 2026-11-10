package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b0 f4191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x0 f4192b = new x0(this, 2);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final x0 f4193c = new x0(this, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x0 f4194d = new x0(this, 1);

    public final b0 a() {
        b0 b0Var = this.f4191a;
        if (b0Var != null) {
            return b0Var;
        }
        throw new IllegalArgumentException("SubcomposeLayoutState is not attached to SubcomposeLayout");
    }
}
