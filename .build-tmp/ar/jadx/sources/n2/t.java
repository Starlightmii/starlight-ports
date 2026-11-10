package n2;

import f0.j0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4251a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4252b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4253c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4254d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4255e;

    public t(int i5) {
        this(1, (i5 & 1) == 0, true);
    }

    public t(int i5, boolean z2, boolean z3) {
        j0 j0Var = j.f4226a;
        int i6 = !z2 ? 262152 : 262144;
        i6 = i5 == 2 ? i6 | 8192 : i6;
        i6 = z3 ? i6 : i6 | 512;
        boolean z4 = i5 == 1;
        this.f4251a = i6;
        this.f4252b = z4;
        this.f4253c = true;
        this.f4254d = true;
        this.f4255e = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.f4251a == tVar.f4251a && this.f4252b == tVar.f4252b && this.f4253c == tVar.f4253c && this.f4254d == tVar.f4254d && this.f4255e == tVar.f4255e;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + b.b.c(b.b.c(b.b.c(b.b.c(this.f4251a * 31, 31, this.f4252b), 31, this.f4253c), 31, this.f4254d), 31, this.f4255e);
    }
}
