package y1;

import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h0 f6779d = new h0(0, 0, null, 0, 0, 0, 16777215);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f6780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f6781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f6782c;

    public h0(long j5, long j6, d2.k kVar, long j7, int i5, long j8, int i6) {
        this(new a0((i6 & 1) != 0 ? y0.u.f6694g : j5, (i6 & 2) != 0 ? k2.m.f3421c : j6, (i6 & 4) != 0 ? null : kVar, (d2.i) null, (d2.j) null, (d2.p) null, (String) null, (i6 & 128) != 0 ? k2.m.f3421c : j7, (j2.a) null, (j2.n) null, (f2.b) null, y0.u.f6694g, (j2.j) null, (p0) null, (u) null), new r((32768 & i6) != 0 ? Integer.MIN_VALUE : i5, Integer.MIN_VALUE, (i6 & 131072) != 0 ? k2.m.f3421c : j8, null, null, null, 0, Integer.MIN_VALUE, null), null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h0(a0 a0Var, r rVar) {
        u uVar = a0Var.f6723o;
        t tVar = rVar.f6813e;
        this(a0Var, rVar, (uVar == null && tVar == null) ? null : new v(uVar, tVar));
    }

    public h0(a0 a0Var, r rVar, v vVar) {
        this.f6780a = a0Var;
        this.f6781b = rVar;
        this.f6782c = vVar;
    }

    public static h0 a(h0 h0Var, long j5, long j6, d2.k kVar, d2.p pVar, long j7, long j8, j2.g gVar, int i5) {
        j2.m cVar;
        long jB = (i5 & 1) != 0 ? h0Var.f6780a.f6709a.b() : j5;
        long j9 = (i5 & 2) != 0 ? h0Var.f6780a.f6710b : j6;
        d2.k kVar2 = (i5 & 4) != 0 ? h0Var.f6780a.f6711c : kVar;
        a0 a0Var = h0Var.f6780a;
        d2.i iVar = a0Var.f6712d;
        d2.j jVar = a0Var.f6713e;
        d2.p pVar2 = (i5 & 32) != 0 ? a0Var.f6714f : pVar;
        String str = a0Var.f6715g;
        long j10 = (i5 & 128) != 0 ? a0Var.f6716h : j7;
        j2.a aVar = a0Var.f6717i;
        j2.n nVar = a0Var.f6718j;
        f2.b bVar = a0Var.f6719k;
        long j11 = a0Var.f6720l;
        j2.j jVar2 = a0Var.f6721m;
        p0 p0Var = a0Var.f6722n;
        a1.g gVar2 = a0Var.f6724p;
        int i6 = (i5 & 32768) != 0 ? h0Var.f6781b.f6809a : 3;
        r rVar = h0Var.f6781b;
        int i7 = rVar.f6810b;
        long j12 = (i5 & 131072) != 0 ? rVar.f6811c : j8;
        j2.o oVar = rVar.f6812d;
        v vVar = (i5 & 524288) != 0 ? h0Var.f6782c : d0.c.f1513a;
        j2.g gVar3 = (i5 & 1048576) != 0 ? rVar.f6814f : gVar;
        int i8 = rVar.f6815g;
        int i9 = rVar.f6816h;
        j2.p pVar3 = rVar.f6817i;
        if (y0.u.c(jB, a0Var.f6709a.b())) {
            cVar = a0Var.f6709a;
        } else {
            cVar = jB != 16 ? new j2.c(jB) : j2.l.f3327a;
        }
        return new h0(new a0(cVar, j9, kVar2, iVar, jVar, pVar2, str, j10, aVar, nVar, bVar, j11, jVar2, p0Var, vVar != null ? vVar.f6823a : null, gVar2), new r(i6, i7, j12, oVar, vVar != null ? vVar.f6824b : null, gVar3, i8, i9, pVar3), vVar);
    }

    public static h0 e(h0 h0Var, long j5, long j6, long j7, int i5, long j8, int i6) {
        long j9 = (i6 & 2) != 0 ? k2.m.f3421c : j6;
        long j10 = (i6 & 128) != 0 ? k2.m.f3421c : j7;
        long j11 = y0.u.f6694g;
        int i7 = (32768 & i6) != 0 ? Integer.MIN_VALUE : i5;
        long j12 = (i6 & 131072) != 0 ? k2.m.f3421c : j8;
        a0 a0VarA = b0.a(h0Var.f6780a, j5, null, Float.NaN, j9, null, null, null, null, null, j10, null, null, null, j11, null, null, null, null);
        r rVarA = s.a(h0Var.f6781b, i7, Integer.MIN_VALUE, j12, null, null, null, 0, Integer.MIN_VALUE, null);
        return (h0Var.f6780a == a0VarA && h0Var.f6781b == rVarA) ? h0Var : new h0(a0VarA, rVarA);
    }

    public final long b() {
        return this.f6780a.f6709a.b();
    }

    public final boolean c(h0 h0Var) {
        if (this != h0Var) {
            return b4.l.a(this.f6781b, h0Var.f6781b) && this.f6780a.a(h0Var.f6780a);
        }
        return true;
    }

    public final h0 d(h0 h0Var) {
        return (h0Var == null || h0Var.equals(f6779d)) ? this : new h0(this.f6780a.c(h0Var.f6780a), this.f6781b.a(h0Var.f6781b));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h0)) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return b4.l.a(this.f6780a, h0Var.f6780a) && b4.l.a(this.f6781b, h0Var.f6781b) && b4.l.a(this.f6782c, h0Var.f6782c);
    }

    public final int hashCode() {
        int iHashCode = (this.f6781b.hashCode() + (this.f6780a.hashCode() * 31)) * 31;
        v vVar = this.f6782c;
        return iHashCode + (vVar != null ? vVar.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextStyle(color=");
        sb.append((Object) y0.u.i(b()));
        sb.append(", brush=");
        a0 a0Var = this.f6780a;
        sb.append(a0Var.f6709a.c());
        sb.append(", alpha=");
        sb.append(a0Var.f6709a.a());
        sb.append(", fontSize=");
        sb.append((Object) k2.m.d(a0Var.f6710b));
        sb.append(", fontWeight=");
        sb.append(a0Var.f6711c);
        sb.append(", fontStyle=");
        sb.append(a0Var.f6712d);
        sb.append(", fontSynthesis=");
        sb.append(a0Var.f6713e);
        sb.append(", fontFamily=");
        sb.append(a0Var.f6714f);
        sb.append(", fontFeatureSettings=");
        sb.append(a0Var.f6715g);
        sb.append(", letterSpacing=");
        sb.append((Object) k2.m.d(a0Var.f6716h));
        sb.append(", baselineShift=");
        sb.append(a0Var.f6717i);
        sb.append(", textGeometricTransform=");
        sb.append(a0Var.f6718j);
        sb.append(", localeList=");
        sb.append(a0Var.f6719k);
        sb.append(", background=");
        b.b.j(a0Var.f6720l, sb, ", textDecoration=");
        sb.append(a0Var.f6721m);
        sb.append(", shadow=");
        sb.append(a0Var.f6722n);
        sb.append(", drawStyle=");
        sb.append(a0Var.f6724p);
        sb.append(", textAlign=");
        r rVar = this.f6781b;
        sb.append((Object) j2.i.a(rVar.f6809a));
        sb.append(", textDirection=");
        sb.append((Object) j2.k.a(rVar.f6810b));
        sb.append(", lineHeight=");
        sb.append((Object) k2.m.d(rVar.f6811c));
        sb.append(", textIndent=");
        sb.append(rVar.f6812d);
        sb.append(", platformStyle=");
        sb.append(this.f6782c);
        sb.append(", lineHeightStyle=");
        sb.append(rVar.f6814f);
        sb.append(", lineBreak=");
        sb.append((Object) j2.e.a(rVar.f6815g));
        sb.append(", hyphens=");
        sb.append((Object) j2.d.a(rVar.f6816h));
        sb.append(", textMotion=");
        sb.append(rVar.f6817i);
        sb.append(')');
        return sb.toString();
    }
}
