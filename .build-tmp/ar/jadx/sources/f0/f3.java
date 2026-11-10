package f0;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f3 implements q0.c, Iterable, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t2 f2169d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m2 f2171f;

    public f3(t2 t2Var, int i5, v0 v0Var, m2 m2Var) {
        this.f2169d = t2Var;
        this.f2170e = i5;
        this.f2171f = m2Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f3)) {
            return false;
        }
        f3 f3Var = (f3) obj;
        return f3Var.f2170e == this.f2170e && f3Var.f2169d.equals(this.f2169d) && f3Var.f2171f.equals(this.f2171f);
    }

    public final int hashCode() {
        return this.f2171f.hashCode() + ((this.f2169d.hashCode() + (this.f2170e * 31)) * 31);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new e3(this.f2169d, this.f2170e, null, this.f2171f);
    }
}
