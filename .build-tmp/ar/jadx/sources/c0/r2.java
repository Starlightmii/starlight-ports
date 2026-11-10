package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u.d f1225a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u.d f1226b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u.d f1227c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u.d f1228d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u.d f1229e;

    public r2() {
        u.d dVar = q2.f1199a;
        u.d dVar2 = q2.f1200b;
        u.d dVar3 = q2.f1201c;
        u.d dVar4 = q2.f1202d;
        u.d dVar5 = q2.f1203e;
        this.f1225a = dVar;
        this.f1226b = dVar2;
        this.f1227c = dVar3;
        this.f1228d = dVar4;
        this.f1229e = dVar5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r2)) {
            return false;
        }
        r2 r2Var = (r2) obj;
        return b4.l.a(this.f1225a, r2Var.f1225a) && b4.l.a(this.f1226b, r2Var.f1226b) && b4.l.a(this.f1227c, r2Var.f1227c) && b4.l.a(this.f1228d, r2Var.f1228d) && b4.l.a(this.f1229e, r2Var.f1229e);
    }

    public final int hashCode() {
        return this.f1229e.hashCode() + ((this.f1228d.hashCode() + ((this.f1227c.hashCode() + ((this.f1226b.hashCode() + (this.f1225a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Shapes(extraSmall=" + this.f1225a + ", small=" + this.f1226b + ", medium=" + this.f1227c + ", large=" + this.f1228d + ", extraLarge=" + this.f1229e + ')';
    }
}
