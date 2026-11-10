package y1;

import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j2.m f6709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d2.k f6711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d2.i f6712d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d2.j f6713e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d2.p f6714f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f6715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f6716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j2.a f6717i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final j2.n f6718j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f2.b f6719k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f6720l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j2.j f6721m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final p0 f6722n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final u f6723o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a1.g f6724p;

    public a0(long j5, long j6, d2.k kVar, d2.i iVar, d2.j jVar, d2.p pVar, String str, long j7, j2.a aVar, j2.n nVar, f2.b bVar, long j8, j2.j jVar2, p0 p0Var, int i5) {
        this((i5 & 1) != 0 ? y0.u.f6694g : j5, (i5 & 2) != 0 ? k2.m.f3421c : j6, (i5 & 4) != 0 ? null : kVar, (i5 & 8) != 0 ? null : iVar, (i5 & 16) != 0 ? null : jVar, (i5 & 32) != 0 ? null : pVar, (i5 & 64) != 0 ? null : str, (i5 & 128) != 0 ? k2.m.f3421c : j7, (i5 & 256) != 0 ? null : aVar, (i5 & 512) != 0 ? null : nVar, (i5 & 1024) != 0 ? null : bVar, (i5 & 2048) != 0 ? y0.u.f6694g : j8, (i5 & 4096) != 0 ? null : jVar2, (i5 & 8192) != 0 ? null : p0Var, (u) null);
    }

    public a0(long j5, long j6, d2.k kVar, d2.i iVar, d2.j jVar, d2.p pVar, String str, long j7, j2.a aVar, j2.n nVar, f2.b bVar, long j8, j2.j jVar2, p0 p0Var, u uVar) {
        this(j5 != 16 ? new j2.c(j5) : j2.l.f3327a, j6, kVar, iVar, jVar, pVar, str, j7, aVar, nVar, bVar, j8, jVar2, p0Var, uVar, null);
    }

    public a0(j2.m mVar, long j5, d2.k kVar, d2.i iVar, d2.j jVar, d2.p pVar, String str, long j6, j2.a aVar, j2.n nVar, f2.b bVar, long j7, j2.j jVar2, p0 p0Var, u uVar, a1.g gVar) {
        this.f6709a = mVar;
        this.f6710b = j5;
        this.f6711c = kVar;
        this.f6712d = iVar;
        this.f6713e = jVar;
        this.f6714f = pVar;
        this.f6715g = str;
        this.f6716h = j6;
        this.f6717i = aVar;
        this.f6718j = nVar;
        this.f6719k = bVar;
        this.f6720l = j7;
        this.f6721m = jVar2;
        this.f6722n = p0Var;
        this.f6723o = uVar;
        this.f6724p = gVar;
    }

    public final boolean a(a0 a0Var) {
        if (this == a0Var) {
            return true;
        }
        return k2.m.a(this.f6710b, a0Var.f6710b) && b4.l.a(this.f6711c, a0Var.f6711c) && b4.l.a(this.f6712d, a0Var.f6712d) && b4.l.a(this.f6713e, a0Var.f6713e) && b4.l.a(this.f6714f, a0Var.f6714f) && b4.l.a(this.f6715g, a0Var.f6715g) && k2.m.a(this.f6716h, a0Var.f6716h) && b4.l.a(this.f6717i, a0Var.f6717i) && b4.l.a(this.f6718j, a0Var.f6718j) && b4.l.a(this.f6719k, a0Var.f6719k) && y0.u.c(this.f6720l, a0Var.f6720l) && b4.l.a(this.f6723o, a0Var.f6723o);
    }

    public final boolean b(a0 a0Var) {
        return b4.l.a(this.f6709a, a0Var.f6709a) && b4.l.a(this.f6721m, a0Var.f6721m) && b4.l.a(this.f6722n, a0Var.f6722n) && b4.l.a(this.f6724p, a0Var.f6724p);
    }

    public final a0 c(a0 a0Var) {
        if (a0Var == null) {
            return this;
        }
        j2.m mVar = a0Var.f6709a;
        return b0.a(this, mVar.b(), mVar.c(), mVar.a(), a0Var.f6710b, a0Var.f6711c, a0Var.f6712d, a0Var.f6713e, a0Var.f6714f, a0Var.f6715g, a0Var.f6716h, a0Var.f6717i, a0Var.f6718j, a0Var.f6719k, a0Var.f6720l, a0Var.f6721m, a0Var.f6722n, a0Var.f6723o, a0Var.f6724p);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a0)) {
            return false;
        }
        a0 a0Var = (a0) obj;
        return a(a0Var) && b(a0Var);
    }

    public final int hashCode() {
        j2.m mVar = this.f6709a;
        long jB = mVar.b();
        int i5 = y0.u.f6695h;
        int iHashCode = Long.hashCode(jB) * 31;
        y0.p pVarC = mVar.c();
        int iHashCode2 = (Float.hashCode(mVar.a()) + ((iHashCode + (pVarC != null ? pVarC.hashCode() : 0)) * 31)) * 31;
        k2.n[] nVarArr = k2.m.f3420b;
        int iB = b.b.b(iHashCode2, 31, this.f6710b);
        d2.k kVar = this.f6711c;
        int i6 = (iB + (kVar != null ? kVar.f1615d : 0)) * 31;
        d2.i iVar = this.f6712d;
        int iHashCode3 = (i6 + (iVar != null ? Integer.hashCode(iVar.f1610a) : 0)) * 31;
        d2.j jVar = this.f6713e;
        int iHashCode4 = (iHashCode3 + (jVar != null ? Integer.hashCode(jVar.f1611a) : 0)) * 31;
        d2.p pVar = this.f6714f;
        int iHashCode5 = (iHashCode4 + (pVar != null ? pVar.hashCode() : 0)) * 31;
        String str = this.f6715g;
        int iB2 = b.b.b((iHashCode5 + (str != null ? str.hashCode() : 0)) * 31, 31, this.f6716h);
        j2.a aVar = this.f6717i;
        int iHashCode6 = (iB2 + (aVar != null ? Float.hashCode(aVar.f3305a) : 0)) * 31;
        j2.n nVar = this.f6718j;
        int iHashCode7 = (iHashCode6 + (nVar != null ? nVar.hashCode() : 0)) * 31;
        f2.b bVar = this.f6719k;
        int iB3 = b.b.b((iHashCode7 + (bVar != null ? bVar.f2446d.hashCode() : 0)) * 31, 31, this.f6720l);
        j2.j jVar2 = this.f6721m;
        int i7 = (iB3 + (jVar2 != null ? jVar2.f3325a : 0)) * 31;
        p0 p0Var = this.f6722n;
        int iHashCode8 = (i7 + (p0Var != null ? p0Var.hashCode() : 0)) * 31;
        u uVar = this.f6723o;
        int iHashCode9 = (iHashCode8 + (uVar != null ? uVar.hashCode() : 0)) * 31;
        a1.g gVar = this.f6724p;
        return iHashCode9 + (gVar != null ? gVar.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SpanStyle(color=");
        j2.m mVar = this.f6709a;
        sb.append((Object) y0.u.i(mVar.b()));
        sb.append(", brush=");
        sb.append(mVar.c());
        sb.append(", alpha=");
        sb.append(mVar.a());
        sb.append(", fontSize=");
        sb.append((Object) k2.m.d(this.f6710b));
        sb.append(", fontWeight=");
        sb.append(this.f6711c);
        sb.append(", fontStyle=");
        sb.append(this.f6712d);
        sb.append(", fontSynthesis=");
        sb.append(this.f6713e);
        sb.append(", fontFamily=");
        sb.append(this.f6714f);
        sb.append(", fontFeatureSettings=");
        sb.append(this.f6715g);
        sb.append(", letterSpacing=");
        sb.append((Object) k2.m.d(this.f6716h));
        sb.append(", baselineShift=");
        sb.append(this.f6717i);
        sb.append(", textGeometricTransform=");
        sb.append(this.f6718j);
        sb.append(", localeList=");
        sb.append(this.f6719k);
        sb.append(", background=");
        b.b.j(this.f6720l, sb, ", textDecoration=");
        sb.append(this.f6721m);
        sb.append(", shadow=");
        sb.append(this.f6722n);
        sb.append(", platformStyle=");
        sb.append(this.f6723o);
        sb.append(", drawStyle=");
        sb.append(this.f6724p);
        sb.append(')');
        return sb.toString();
    }
}
