package f0;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u2 implements q0.c, Iterable, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t2 f2351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2352e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2353f;

    public u2(t2 t2Var, int i5, int i6) {
        this.f2351d = t2Var;
        this.f2352e = i5;
        this.f2353f = i6;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u2)) {
            return false;
        }
        u2 u2Var = (u2) obj;
        return u2Var.f2352e == this.f2352e && u2Var.f2353f == this.f2353f && b4.l.a(u2Var.f2351d, this.f2351d);
    }

    public final int hashCode() {
        return (this.f2351d.hashCode() * 31) + this.f2352e;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        t2 t2Var = this.f2351d;
        if (t2Var.f2340k != this.f2353f) {
            v2.f();
        }
        int i5 = this.f2352e;
        t2Var.f(i5);
        return new u0(t2Var, i5 + 1, t2Var.f2333d[(i5 * 5) + 3] + i5);
    }
}
