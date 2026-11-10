package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v.e0 f7011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f7012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7013c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f7014d;

    public u(v.e0 e0Var, long j5, int i5, boolean z2) {
        this.f7011a = e0Var;
        this.f7012b = j5;
        this.f7013c = i5;
        this.f7014d = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.f7011a == uVar.f7011a && x0.c.b(this.f7012b, uVar.f7012b) && this.f7013c == uVar.f7013c && this.f7014d == uVar.f7014d;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f7014d) + ((m.h.c(this.f7013c) + b.b.b(this.f7011a.hashCode() * 31, 31, this.f7012b)) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("SelectionHandleInfo(handle=");
        sb.append(this.f7011a);
        sb.append(", position=");
        sb.append((Object) x0.c.j(this.f7012b));
        sb.append(", anchor=");
        int i5 = this.f7013c;
        if (i5 == 1) {
            str = "Left";
        } else if (i5 != 2) {
            str = i5 != 3 ? "null" : "Right";
        } else {
            str = "Middle";
        }
        sb.append(str);
        sb.append(", visible=");
        sb.append(this.f7014d);
        sb.append(')');
        return sb.toString();
    }
}
