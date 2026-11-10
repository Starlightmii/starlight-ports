package y;

import b4.l;
import k2.k;
import p3.p;
import q3.s;
import v.l0;
import y1.h0;
import y1.q;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h0 f6582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d2.d f6583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6585e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6586f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6587g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public k2.b f6589i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y1.a f6590j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6591k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b f6593m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public q f6594n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public k f6595o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f6588h = a.f6569a;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f6592l = a.a.f(0, 0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f6596p = h1.c.r(0, 0, 0, 0);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6597q = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6598r = -1;

    public d(String str, h0 h0Var, d2.d dVar, int i5, boolean z2, int i6, int i7) {
        this.f6581a = str;
        this.f6582b = h0Var;
        this.f6583c = dVar;
        this.f6584d = i5;
        this.f6585e = z2;
        this.f6586f = i6;
        this.f6587g = i7;
    }

    public final int a(int i5, k kVar) {
        int i6 = this.f6597q;
        int i7 = this.f6598r;
        if (i5 == i6 && i6 != -1) {
            return i7;
        }
        int iK = l0.k(b(h1.c.b(0, i5, 0, Integer.MAX_VALUE), kVar).b());
        this.f6597q = i5;
        this.f6598r = iK;
        return iK;
    }

    public final y1.a b(long j5, k kVar) {
        q qVarD = d(kVar);
        boolean z2 = this.f6585e;
        int i5 = this.f6584d;
        float fC = qVarD.c();
        int i6 = ((z2 || i5 == 2) && k2.a.e(j5)) ? k2.a.i(j5) : Integer.MAX_VALUE;
        if (k2.a.k(j5) != i6) {
            i6 = d4.a.m(l0.k(fC), k2.a.k(j5), i6);
        }
        int iH = k2.a.h(j5);
        int iMin = Math.min(0, 262142);
        int iMin2 = i6 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i6, 262142);
        int iH2 = h1.c.h(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        long jB = h1.c.b(iMin, iMin2, Math.min(iH2, 0), iH != Integer.MAX_VALUE ? Math.min(iH2, iH) : Integer.MAX_VALUE);
        boolean z3 = this.f6585e;
        int i7 = this.f6584d;
        int i8 = this.f6586f;
        return new y1.a((g2.d) qVarD, ((z3 || i7 != 2) && i8 >= 1) ? i8 : 1, i7 == 2, jB);
    }

    public final void c(k2.b bVar) {
        long jA;
        k2.b bVar2 = this.f6589i;
        if (bVar != null) {
            int i5 = a.f6570b;
            jA = a.a(bVar.a(), bVar.j());
        } else {
            jA = a.f6569a;
        }
        if (bVar2 == null) {
            this.f6589i = bVar;
            this.f6588h = jA;
            return;
        }
        if (bVar == null || this.f6588h != jA) {
            this.f6589i = bVar;
            this.f6588h = jA;
            this.f6590j = null;
            this.f6594n = null;
            this.f6595o = null;
            this.f6597q = -1;
            this.f6598r = -1;
            this.f6596p = h1.c.r(0, 0, 0, 0);
            this.f6592l = a.a.f(0, 0);
            this.f6591k = false;
        }
    }

    public final q d(k kVar) {
        q dVar = this.f6594n;
        if (dVar == null || kVar != this.f6595o || dVar.b()) {
            this.f6595o = kVar;
            String str = this.f6581a;
            h0 h0VarB = p.B(this.f6582b, kVar);
            k2.b bVar = this.f6589i;
            l.b(bVar);
            d2.d dVar2 = this.f6583c;
            s sVar = s.f5477d;
            dVar = new g2.d(str, h0VarB, sVar, sVar, dVar2, bVar);
        }
        this.f6594n = dVar;
        return dVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphLayoutCache(paragraph=");
        sb.append(this.f6590j != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        long j5 = this.f6588h;
        int i5 = a.f6570b;
        sb.append((Object) ("InlineDensity(density=" + Float.intBitsToFloat((int) (j5 >> 32)) + ", fontScale=" + Float.intBitsToFloat((int) (j5 & 4294967295L)) + ')'));
        sb.append(')');
        return sb.toString();
    }
}
