package r;

import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f5645b;

    public y0(e0 e0Var, String str) {
        this.f5644a = str;
        this.f5645b = f0.l.s(e0Var);
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        return e().f5556d;
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        return e().f5554b;
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        return e().f5555c;
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        return e().f5553a;
    }

    public final e0 e() {
        return (e0) this.f5645b.getValue();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof y0) {
            return b4.l.a(e(), ((y0) obj).e());
        }
        return false;
    }

    public final void f(e0 e0Var) {
        this.f5645b.setValue(e0Var);
    }

    public final int hashCode() {
        return this.f5644a.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5644a);
        sb.append("(left=");
        sb.append(e().f5553a);
        sb.append(", top=");
        sb.append(e().f5554b);
        sb.append(", right=");
        sb.append(e().f5555c);
        sb.append(", bottom=");
        return b.b.f(sb, e().f5556d, ')');
    }
}
