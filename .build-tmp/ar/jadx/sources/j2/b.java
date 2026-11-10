package j2;

import y0.o0;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o0 f3306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3307b;

    public b(o0 o0Var, float f5) {
        this.f3306a = o0Var;
        this.f3307b = f5;
    }

    @Override // j2.m
    public final float a() {
        return this.f3307b;
    }

    @Override // j2.m
    public final long b() {
        int i5 = u.f6695h;
        return u.f6694g;
    }

    @Override // j2.m
    public final y0.p c() {
        return this.f3306a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return b4.l.a(this.f3306a, bVar.f3306a) && Float.compare(this.f3307b, bVar.f3307b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3307b) + (this.f3306a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BrushStyle(value=");
        sb.append(this.f3306a);
        sb.append(", alpha=");
        return b.b.e(sb, this.f3307b, ')');
    }
}
