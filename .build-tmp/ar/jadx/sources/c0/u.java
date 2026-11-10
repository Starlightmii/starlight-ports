package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f1329a = new f0.h3(t.f1295f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f0.h3 f1330b = new f0.h3(t.f1296g);

    public static final long a(s sVar, long j5) {
        long j6 = sVar.f1244a;
        long j7 = sVar.f1260q;
        if (y0.u.c(j5, j6)) {
            return sVar.f1245b;
        }
        if (y0.u.c(j5, sVar.f1249f)) {
            return sVar.f1250g;
        }
        if (y0.u.c(j5, sVar.f1253j)) {
            return sVar.f1254k;
        }
        if (y0.u.c(j5, sVar.f1257n)) {
            return sVar.f1258o;
        }
        if (y0.u.c(j5, sVar.f1266w)) {
            return sVar.f1267x;
        }
        if (y0.u.c(j5, sVar.f1246c)) {
            return sVar.f1247d;
        }
        if (y0.u.c(j5, sVar.f1251h)) {
            return sVar.f1252i;
        }
        if (y0.u.c(j5, sVar.f1255l)) {
            return sVar.f1256m;
        }
        if (y0.u.c(j5, sVar.f1268y)) {
            return sVar.f1269z;
        }
        if (y0.u.c(j5, sVar.f1264u)) {
            return sVar.f1265v;
        }
        if (y0.u.c(j5, sVar.f1259p)) {
            return j7;
        }
        if (y0.u.c(j5, sVar.f1261r)) {
            return sVar.f1262s;
        }
        if (y0.u.c(j5, sVar.D) || y0.u.c(j5, sVar.F) || y0.u.c(j5, sVar.G) || y0.u.c(j5, sVar.H) || y0.u.c(j5, sVar.I) || y0.u.c(j5, sVar.J)) {
            return j7;
        }
        int i5 = y0.u.f6695h;
        return y0.u.f6694g;
    }

    public static final long b(long j5, f0.p pVar) {
        f0.v vVar = (f0.v) pVar;
        vVar.V(-1680936624);
        long jA = a((s) vVar.j(f1329a), j5);
        if (jA == 16) {
            jA = ((y0.u) vVar.j(b0.f762a)).f6696a;
        }
        vVar.p(false);
        return jA;
    }

    public static final long c(s sVar, int i5) {
        switch (m.h.c(i5)) {
            case 0:
                return sVar.f1257n;
            case 1:
                return sVar.f1266w;
            case 2:
                return sVar.f1268y;
            case 3:
                return sVar.f1265v;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return sVar.f1248e;
            case 5:
                return sVar.f1264u;
            case 6:
                return sVar.f1258o;
            case 7:
                return sVar.f1267x;
            case 8:
                return sVar.f1269z;
            case 9:
                return sVar.f1245b;
            case 10:
                return sVar.f1247d;
            case 11:
            case 12:
            case 15:
            case 16:
            case 21:
            case 22:
            case 27:
            case 28:
            case 32:
            case 33:
            default:
                return y0.u.f6694g;
            case 13:
                return sVar.f1250g;
            case 14:
                return sVar.f1252i;
            case 17:
                return sVar.f1260q;
            case 18:
                return sVar.f1262s;
            case 19:
                return sVar.f1254k;
            case 20:
                return sVar.f1256m;
            case 23:
                return sVar.A;
            case 24:
                return sVar.B;
            case 25:
                return sVar.f1244a;
            case 26:
                return sVar.f1246c;
            case 29:
                return sVar.C;
            case 30:
                return sVar.f1249f;
            case 31:
                return sVar.f1251h;
            case 34:
                return sVar.f1259p;
            case 35:
                return sVar.D;
            case 36:
                return sVar.F;
            case 37:
                return sVar.G;
            case 38:
                return sVar.H;
            case 39:
                return sVar.I;
            case 40:
                return sVar.J;
            case 41:
                return sVar.E;
            case 42:
                return sVar.f1263t;
            case 43:
                return sVar.f1261r;
            case 44:
                return sVar.f1253j;
            case 45:
                return sVar.f1255l;
        }
    }

    public static final long d(f0.p pVar, int i5) {
        return c((s) ((f0.v) pVar).j(f1329a), i5);
    }

    public static s e() {
        long j5 = e0.b.f1694t;
        return new s(j5, e0.b.f1684j, e0.b.f1695u, e0.b.f1685k, e0.b.f1679e, e0.b.f1697w, e0.b.f1686l, e0.b.f1698x, e0.b.f1687m, e0.b.H, e0.b.f1690p, e0.b.I, e0.b.f1691q, e0.b.f1675a, e0.b.f1681g, e0.b.f1699y, e0.b.f1688n, e0.b.G, e0.b.f1689o, j5, e0.b.f1680f, e0.b.f1678d, e0.b.f1676b, e0.b.f1682h, e0.b.f1677c, e0.b.f1683i, e0.b.f1692r, e0.b.f1693s, e0.b.f1696v, e0.b.f1700z, e0.b.F, e0.b.A, e0.b.B, e0.b.C, e0.b.D, e0.b.E);
    }
}
