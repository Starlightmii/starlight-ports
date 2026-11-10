package y1;

import y0.o0;
import y0.p0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f6729a = d4.a.w(14);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f6730b = d4.a.w(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f6731c = y0.u.f6693f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j2.m f6732d;

    static {
        long j5 = y0.u.f6689b;
        f6732d = j5 != 16 ? new j2.c(j5) : j2.l.f3327a;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x016a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:103:0x016c  */
    /* JADX WARN: Code duplicated, block: B:106:0x0171 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:113:0x0181  */
    /* JADX WARN: Code duplicated, block: B:114:0x0184  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:120:0x0191  */
    /* JADX WARN: Code duplicated, block: B:121:0x0194  */
    /* JADX WARN: Code duplicated, block: B:123:0x0198  */
    /* JADX WARN: Code duplicated, block: B:124:0x019b  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:128:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:130:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:134:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:136:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:138:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:141:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:142:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:146:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:148:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:150:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:151:0x01df  */
    /* JADX WARN: Code duplicated, block: B:154:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:156:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:157:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:7:0x0029 A[PHI: r11
      0x0029: PHI (r11v8 long) = 
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v9 long)
     binds: [B:36:0x0088, B:48:0x00b2, B:45:0x00a8, B:42:0x009e, B:39:0x0094, B:34:0x007c, B:29:0x006f, B:25:0x005f, B:22:0x0059, B:19:0x004f, B:16:0x0045, B:5:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:80:0x010b  */
    /* JADX WARN: Code duplicated, block: B:82:0x010f  */
    /* JADX WARN: Code duplicated, block: B:84:0x011e  */
    /* JADX WARN: Code duplicated, block: B:85:0x0124  */
    /* JADX WARN: Code duplicated, block: B:86:0x0126  */
    /* JADX WARN: Code duplicated, block: B:88:0x012c  */
    /* JADX WARN: Code duplicated, block: B:89:0x0135  */
    /* JADX WARN: Code duplicated, block: B:91:0x013b  */
    /* JADX WARN: Code duplicated, block: B:93:0x013f  */
    public static final a0 a(a0 a0Var, long j5, y0.p pVar, float f5, long j6, d2.k kVar, d2.i iVar, d2.j jVar, d2.p pVar2, String str, long j7, j2.a aVar, j2.n nVar, f2.b bVar, long j8, j2.j jVar2, p0 p0Var, u uVar, a1.g gVar) {
        long j9;
        j2.n nVar2;
        long j10;
        u uVar2;
        a1.g gVar2;
        j2.l lVar;
        j2.m cVar;
        j2.m mVar;
        boolean z2;
        d2.p pVar3;
        d2.k kVar2;
        d2.i iVar2;
        d2.j jVar3;
        long j11;
        f2.b bVar2;
        p0 p0Var2;
        u uVar3;
        a1.g gVar3;
        long jF;
        String str2 = str;
        j2.a aVar2 = aVar;
        j2.j jVar4 = jVar2;
        if (!d4.a.A(j6)) {
            j9 = j6;
            if (!k2.m.a(j9, a0Var.f6710b)) {
                nVar2 = nVar;
                j10 = j8;
                uVar2 = uVar;
                gVar2 = gVar;
            }
            lVar = j2.l.f3327a;
            if (pVar != null) {
                if (pVar instanceof s0) {
                    jF = h1.c.F(f5, ((s0) pVar).f6684a);
                    if (jF != 16) {
                        cVar = new j2.c(jF);
                    } else {
                        cVar = lVar;
                    }
                } else {
                    if (pVar instanceof o0) {
                        throw new i4.p();
                    }
                    cVar = new j2.b((o0) pVar, f5);
                }
            } else if (j5 != 16) {
                cVar = new j2.c(j5);
            } else {
                cVar = lVar;
            }
            mVar = a0Var.f6709a;
            mVar.getClass();
            z2 = cVar instanceof j2.b;
            if (!z2 && (mVar instanceof j2.b)) {
                j2.b bVar3 = (j2.b) cVar;
                o0 o0Var = bVar3.f3306a;
                float fA = bVar3.f3307b;
                if (Float.isNaN(fA)) {
                    fA = mVar.a();
                }
                cVar = new j2.b(o0Var, fA);
            } else if ((z2 || (mVar instanceof j2.b)) && ((!z2 && (mVar instanceof j2.b)) || cVar.equals(lVar))) {
            }
            if (pVar2 == null) {
                pVar3 = a0Var.f6714f;
            } else {
                pVar3 = pVar2;
            }
            if (d4.a.A(j9)) {
                j9 = a0Var.f6710b;
            }
            if (kVar == null) {
                kVar2 = a0Var.f6711c;
            } else {
                kVar2 = kVar;
            }
            if (iVar == null) {
                iVar2 = a0Var.f6712d;
            } else {
                iVar2 = iVar;
            }
            d2.p pVar4 = pVar3;
            if (jVar == null) {
                jVar3 = a0Var.f6713e;
            } else {
                jVar3 = jVar;
            }
            if (str2 == null) {
                str2 = a0Var.f6715g;
            }
            if (d4.a.A(j7)) {
                j11 = a0Var.f6716h;
            } else {
                j11 = j7;
            }
            if (aVar2 == null) {
                aVar2 = a0Var.f6717i;
            }
            if (nVar2 == null) {
                nVar2 = a0Var.f6718j;
            }
            long j12 = j11;
            if (bVar == null) {
                bVar2 = a0Var.f6719k;
            } else {
                bVar2 = bVar;
            }
            if (j10 == 16) {
                j10 = a0Var.f6720l;
            }
            if (jVar4 == null) {
                jVar4 = a0Var.f6721m;
            }
            if (p0Var == null) {
                p0Var2 = a0Var.f6722n;
            } else {
                p0Var2 = p0Var;
            }
            uVar3 = a0Var.f6723o;
            if (uVar3 == null) {
                uVar3 = uVar2;
            }
            if (gVar2 == null) {
                gVar3 = a0Var.f6724p;
            } else {
                gVar3 = gVar2;
            }
            return new a0(cVar, j9, kVar2, iVar2, jVar3, pVar4, str2, j12, aVar2, nVar2, bVar2, j10, jVar4, p0Var2, uVar3, gVar3);
        }
        j9 = j6;
        if ((pVar != null || j5 == 16 || y0.u.c(j5, a0Var.f6709a.b())) && ((iVar == null || iVar.equals(a0Var.f6712d)) && ((kVar == null || kVar.equals(a0Var.f6711c)) && ((pVar2 == null || pVar2 == a0Var.f6714f) && (d4.a.A(j7) || k2.m.a(j7, a0Var.f6716h)))))) {
            if ((jVar4 == null || jVar4.equals(a0Var.f6721m)) && b4.l.a(pVar, a0Var.f6709a.c()) && ((pVar == null || f5 == a0Var.f6709a.a()) && ((jVar == null || jVar.equals(a0Var.f6713e)) && ((str2 == null || str2.equals(a0Var.f6715g)) && (aVar2 == null || aVar2.equals(a0Var.f6717i)))))) {
                if (nVar != null) {
                    nVar2 = nVar;
                    if (nVar2.equals(a0Var.f6718j)) {
                    }
                } else {
                    nVar2 = nVar;
                }
                if (bVar == null || bVar.equals(a0Var.f6719k)) {
                    if (j8 != 16) {
                        j10 = j8;
                        if (y0.u.c(j10, a0Var.f6720l)) {
                        }
                    } else {
                        j10 = j8;
                    }
                    if (p0Var == null || p0Var.equals(a0Var.f6722n)) {
                        uVar2 = uVar;
                        if (uVar2 == null || uVar2.equals(a0Var.f6723o)) {
                            gVar2 = gVar;
                            if (gVar2 == null || gVar2.equals(a0Var.f6724p)) {
                                return a0Var;
                            }
                        }
                    }
                    gVar2 = gVar;
                }
                uVar2 = uVar;
                gVar2 = gVar;
            } else {
                nVar2 = nVar;
            }
            j10 = j8;
            uVar2 = uVar;
            gVar2 = gVar;
        } else {
            nVar2 = nVar;
            j10 = j8;
            uVar2 = uVar;
            gVar2 = gVar;
        }
        lVar = j2.l.f3327a;
        if (pVar != null) {
            if (pVar instanceof s0) {
                jF = h1.c.F(f5, ((s0) pVar).f6684a);
                if (jF != 16) {
                    cVar = new j2.c(jF);
                } else {
                    cVar = lVar;
                }
            } else {
                if (pVar instanceof o0) {
                    throw new i4.p();
                }
                cVar = new j2.b((o0) pVar, f5);
            }
        } else if (j5 != 16) {
            cVar = new j2.c(j5);
        } else {
            cVar = lVar;
        }
        mVar = a0Var.f6709a;
        mVar.getClass();
        z2 = cVar instanceof j2.b;
        if (!z2) {
            cVar = z2 ? mVar : mVar;
        } else if (z2) {
        }
        if (pVar2 == null) {
            pVar3 = a0Var.f6714f;
        } else {
            pVar3 = pVar2;
        }
        if (d4.a.A(j9)) {
            j9 = a0Var.f6710b;
        }
        if (kVar == null) {
            kVar2 = a0Var.f6711c;
        } else {
            kVar2 = kVar;
        }
        if (iVar == null) {
            iVar2 = a0Var.f6712d;
        } else {
            iVar2 = iVar;
        }
        d2.p pVar5 = pVar3;
        if (jVar == null) {
            jVar3 = a0Var.f6713e;
        } else {
            jVar3 = jVar;
        }
        if (str2 == null) {
            str2 = a0Var.f6715g;
        }
        if (d4.a.A(j7)) {
            j11 = j7;
        } else {
            j11 = a0Var.f6716h;
        }
        if (aVar2 == null) {
            aVar2 = a0Var.f6717i;
        }
        if (nVar2 == null) {
            nVar2 = a0Var.f6718j;
        }
        long j13 = j11;
        if (bVar == null) {
            bVar2 = a0Var.f6719k;
        } else {
            bVar2 = bVar;
        }
        if (j10 == 16) {
            j10 = a0Var.f6720l;
        }
        if (jVar4 == null) {
            jVar4 = a0Var.f6721m;
        }
        if (p0Var == null) {
            p0Var2 = a0Var.f6722n;
        } else {
            p0Var2 = p0Var;
        }
        uVar3 = a0Var.f6723o;
        if (uVar3 == null) {
            uVar3 = uVar2;
        }
        if (gVar2 == null) {
            gVar3 = a0Var.f6724p;
        } else {
            gVar3 = gVar2;
        }
        return new a0(cVar, j9, kVar2, iVar2, jVar3, pVar5, str2, j13, aVar2, nVar2, bVar2, j10, jVar4, p0Var2, uVar3, gVar3);
    }

    public static final Object b(Object obj, Object obj2, float f5) {
        return ((double) f5) < 0.5d ? obj : obj2;
    }

    public static final long c(float f5, long j5, long j6) {
        if (d4.a.A(j5) || d4.a.A(j6)) {
            return ((k2.m) b(new k2.m(j5), new k2.m(j6), f5)).f3422a;
        }
        if (d4.a.A(j5) || d4.a.A(j6)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        if (k2.n.a(k2.m.b(j5), k2.m.b(j6))) {
            return d4.a.B(a.a.D(k2.m.c(j5), k2.m.c(j6), f5), 1095216660480L & j5);
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) k2.n.b(k2.m.b(j5))) + " and " + ((Object) k2.n.b(k2.m.b(j6)))).toString());
    }
}
