package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements w0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b0 f4087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Enum f4088b;

    public a0(b0 b0Var, Enum r4) {
        this.f4087a = b0Var;
        this.f4088b = r4;
    }

    @Override // n1.w0
    public final void dispose() {
        b0 b0Var = this.f4087a;
        p1.d0 d0Var = b0Var.f4091d;
        b0Var.e();
        p1.d0 d0Var2 = (p1.d0) b0Var.f4099l.remove(this.f4088b);
        if (d0Var2 != null) {
            if (b0Var.f4104q <= 0) {
                throw new IllegalStateException("No pre-composed items to dispose");
            }
            int i5 = ((h0.b) d0Var.o()).f2570d.i(d0Var2);
            int i6 = ((h0.b) d0Var.o()).f2570d.f2578f;
            int i7 = b0Var.f4104q;
            if (i5 < i6 - i7) {
                throw new IllegalStateException("Item is not in pre-composed item range");
            }
            b0Var.f4103p++;
            b0Var.f4104q = i7 - 1;
            int i8 = (((h0.b) d0Var.o()).f2570d.f2578f - b0Var.f4104q) - b0Var.f4103p;
            d0Var.f4790n = true;
            d0Var.G(i5, i8, 1);
            d0Var.f4790n = false;
            b0Var.d(i8);
        }
    }
}
