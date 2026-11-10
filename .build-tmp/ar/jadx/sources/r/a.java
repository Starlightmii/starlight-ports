package r;

import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f5502c = f0.l.s(r2.c.f5696e);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f5503d = f0.l.s(Boolean.TRUE);

    public a(int i5, String str) {
        this.f5500a = i5;
        this.f5501b = str;
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        return e().f5700d;
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        return e().f5698b;
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        return e().f5699c;
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        return e().f5697a;
    }

    public final r2.c e() {
        return (r2.c) this.f5502c.getValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            return this.f5500a == ((a) obj).f5500a;
        }
        return false;
    }

    public final void f(w2.z0 z0Var, int i5) {
        int i6 = this.f5500a;
        if (i5 == 0 || (i5 & i6) != 0) {
            this.f5502c.setValue(z0Var.f6456a.h(i6));
            this.f5503d.setValue(Boolean.valueOf(z0Var.f6456a.s(i6)));
        }
    }

    public final int hashCode() {
        return this.f5500a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5501b);
        sb.append('(');
        sb.append(e().f5697a);
        sb.append(", ");
        sb.append(e().f5698b);
        sb.append(", ");
        sb.append(e().f5699c);
        sb.append(", ");
        return b.b.f(sb, e().f5700d, ')');
    }
}
