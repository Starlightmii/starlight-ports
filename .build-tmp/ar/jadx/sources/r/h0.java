package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f5565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5566b;

    public h0(a aVar, int i5) {
        this.f5565a = aVar;
        this.f5566b = i5;
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        if ((this.f5566b & 32) != 0) {
            return this.f5565a.a(bVar);
        }
        return 0;
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        if ((this.f5566b & 16) != 0) {
            return this.f5565a.b(bVar);
        }
        return 0;
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        if (((kVar == k2.k.f3416d ? 4 : 1) & this.f5566b) != 0) {
            return this.f5565a.c(bVar, kVar);
        }
        return 0;
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        if (((kVar == k2.k.f3416d ? 8 : 2) & this.f5566b) != 0) {
            return this.f5565a.d(bVar, kVar);
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h0)) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return b4.l.a(this.f5565a, h0Var.f5565a) && this.f5566b == h0Var.f5566b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f5566b) + (this.f5565a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.f5565a);
        sb.append(" only ");
        StringBuilder sb2 = new StringBuilder("WindowInsetsSides(");
        StringBuilder sb3 = new StringBuilder();
        int i5 = this.f5566b;
        int i6 = c.f5531c;
        if ((i5 & i6) == i6) {
            c.g(sb3, "Start");
        }
        int i7 = c.f5533e;
        if ((i5 & i7) == i7) {
            c.g(sb3, "Left");
        }
        if ((i5 & 16) == 16) {
            c.g(sb3, "Top");
        }
        int i8 = c.f5532d;
        if ((i5 & i8) == i8) {
            c.g(sb3, "End");
        }
        int i9 = c.f5534f;
        if ((i5 & i9) == i9) {
            c.g(sb3, "Right");
        }
        if ((i5 & 32) == 32) {
            c.g(sb3, "Bottom");
        }
        String string = sb3.toString();
        b4.l.d("StringBuilder().apply(builderAction).toString()", string);
        sb2.append(string);
        sb2.append(')');
        sb.append((Object) sb2.toString());
        sb.append(')');
        return sb.toString();
    }
}
