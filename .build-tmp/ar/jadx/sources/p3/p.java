package p3;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.LocaleList;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.k0;
import f0.b2;
import f0.v;
import j1.z;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p1.d0;
import p1.l1;
import q1.o2;
import v.e1;
import v.o0;
import v.u0;
import y0.p0;
import y1.a0;
import y1.b0;
import y1.c0;
import y1.g0;
import y1.h0;
import y1.r;
import y1.s;
import y1.t;
import y1.u;
import z.e0;
import z.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static y0.g f5029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static y0.d f5030b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static a1.c f5031c;

    public static t3.h A(t3.f fVar, t3.h hVar) {
        b4.l.e("context", hVar);
        return hVar == t3.i.f5807d ? fVar : (t3.h) hVar.t(new f0.k(29), fVar);
    }

    public static final h0 B(h0 h0Var, k2.k kVar) {
        d2.p pVar;
        long j5;
        a0 a0Var = h0Var.f6780a;
        j2.m mVar = b0.f6732d;
        j2.m mVar2 = a0Var.f6709a;
        long j6 = a0Var.f6716h;
        if (mVar2.equals(j2.l.f3327a)) {
            mVar2 = b0.f6732d;
        }
        j2.m mVar3 = mVar2;
        long j7 = a0Var.f6710b;
        if (d4.a.A(j7)) {
            j7 = b0.f6729a;
        }
        d2.k kVar2 = a0Var.f6711c;
        if (kVar2 == null) {
            kVar2 = d2.k.f1613f;
        }
        d2.k kVar3 = kVar2;
        d2.i iVar = a0Var.f6712d;
        d2.i iVar2 = new d2.i(iVar != null ? iVar.f1610a : 0);
        d2.j jVar = a0Var.f6713e;
        d2.j jVar2 = new d2.j(jVar != null ? jVar.f1611a : 1);
        d2.p pVar2 = a0Var.f6714f;
        if (pVar2 == null) {
            pVar2 = d2.p.f1618a;
        }
        String str = a0Var.f6715g;
        if (str == null) {
            str = "";
        }
        if (d4.a.A(j6)) {
            j6 = b0.f6730b;
        }
        long j8 = j6;
        j2.a aVar = a0Var.f6717i;
        j2.a aVar2 = new j2.a(aVar != null ? aVar.f3305a : 0.0f);
        j2.n nVar = a0Var.f6718j;
        if (nVar == null) {
            nVar = j2.n.f3328c;
        }
        j2.n nVar2 = nVar;
        f2.b bVar = a0Var.f6719k;
        if (bVar == null) {
            f2.b bVar2 = f2.b.f2445f;
            a1.b bVar3 = f2.c.f2448a;
            bVar3.getClass();
            LocaleList localeList = LocaleList.getDefault();
            synchronized (((k0) bVar3.f25f)) {
                pVar = pVar2;
                try {
                    f2.b bVar4 = (f2.b) bVar3.f24e;
                    if (bVar4 == null || localeList != ((LocaleList) bVar3.f23d)) {
                        int size = localeList.size();
                        ArrayList arrayList = new ArrayList(size);
                        int i5 = 0;
                        while (i5 < size) {
                            arrayList.add(new f2.a(localeList.get(i5)));
                            i5++;
                            size = size;
                            j7 = j7;
                        }
                        j5 = j7;
                        f2.b bVar5 = new f2.b(arrayList);
                        bVar3.f23d = localeList;
                        bVar3.f24e = bVar5;
                        bVar = bVar5;
                    } else {
                        j5 = j7;
                        bVar = bVar4;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            pVar = pVar2;
            j5 = j7;
        }
        long j9 = a0Var.f6720l;
        if (j9 == 16) {
            j9 = b0.f6731c;
        }
        j2.j jVar3 = a0Var.f6721m;
        if (jVar3 == null) {
            jVar3 = j2.j.f3322b;
        }
        p0 p0Var = a0Var.f6722n;
        if (p0Var == null) {
            p0Var = p0.f6668d;
        }
        u uVar = a0Var.f6723o;
        a1.g gVar = a0Var.f6724p;
        if (gVar == null) {
            gVar = a1.i.f33a;
        }
        a0 a0Var2 = new a0(mVar3, j5, kVar3, iVar2, jVar2, pVar, str, j8, aVar2, nVar2, bVar, j9, jVar3, p0Var, uVar, gVar);
        r rVar = h0Var.f6781b;
        int i6 = s.f6819b;
        int i7 = rVar.f6809a;
        long j10 = rVar.f6811c;
        int i8 = 5;
        if (i7 == Integer.MIN_VALUE) {
            i7 = 5;
        }
        int i9 = rVar.f6810b;
        if (i9 == 3) {
            int iOrdinal = kVar.ordinal();
            if (iOrdinal == 0) {
                i8 = 4;
            } else if (iOrdinal != 1) {
                throw new i4.p();
            }
        } else if (i9 == Integer.MIN_VALUE) {
            int iOrdinal2 = kVar.ordinal();
            if (iOrdinal2 == 0) {
                i8 = 1;
            } else {
                if (iOrdinal2 != 1) {
                    throw new i4.p();
                }
                i8 = 2;
            }
        } else {
            i8 = i9;
        }
        if (d4.a.A(j10)) {
            j10 = s.f6818a;
        }
        long j11 = j10;
        j2.o oVar = rVar.f6812d;
        if (oVar == null) {
            oVar = j2.o.f3331c;
        }
        t tVar = rVar.f6813e;
        j2.g gVar2 = rVar.f6814f;
        int i10 = rVar.f6815g;
        if (i10 == 0) {
            i10 = j2.e.f3310b;
        }
        int i11 = i10;
        int i12 = rVar.f6816h;
        int i13 = i12 == Integer.MIN_VALUE ? 1 : i12;
        j2.p pVar3 = rVar.f6817i;
        if (pVar3 == null) {
            pVar3 = j2.p.f3334c;
        }
        return new h0(a0Var2, new r(i7, i8, j11, oVar, tVar, gVar2, i11, i13, pVar3), h0Var.f6782c);
    }

    public static void C(Window window) {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 35) {
            t2.b.e(window);
        } else if (i5 >= 30) {
            t2.b.d(window);
        } else {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 1792);
        }
    }

    public static final boolean D(Throwable th, a4.a aVar) {
        List listAsList;
        Object objInvoke;
        b4.l.e("<this>", th);
        Integer num = y3.a.f6891a;
        q0.f fVar = null;
        if (num == null || num.intValue() >= 19) {
            Throwable[] suppressed = th.getSuppressed();
            b4.l.d("getSuppressed(...)", suppressed);
            listAsList = Arrays.asList(suppressed);
            b4.l.d("asList(...)", listAsList);
        } else {
            Method method = x3.a.f6568b;
            if (method == null || (objInvoke = method.invoke(th, null)) == null) {
                listAsList = q3.s.f5477d;
            } else {
                listAsList = Arrays.asList((Throwable[]) objInvoke);
                b4.l.d("asList(...)", listAsList);
            }
        }
        int size = listAsList.size();
        boolean z2 = false;
        for (int i5 = 0; i5 < size; i5++) {
            if (((Throwable) listAsList.get(i5)) instanceof q0.f) {
                return false;
            }
        }
        try {
            q0.a aVar2 = (q0.a) aVar.a();
            if (aVar2 != null && !aVar2.f5086a.isEmpty()) {
                z2 = true;
            }
            if (z2) {
                b4.l.b(aVar2);
                fVar = new q0.f(aVar2);
            }
        } catch (Throwable th2) {
            fVar = th2;
        }
        if (fVar != null) {
            d4.a.f(th, fVar);
        }
        return z2;
    }

    public static final double E(long j5) {
        return ((j5 >>> 11) * ((double) 2048)) + (j5 & 2047);
    }

    public static final long a(float f5, float f6) {
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
        int i5 = x0.a.f6527b;
        return jFloatToRawIntBits;
    }

    public static final x0.d b(long j5, long j6) {
        return new x0.d(x0.c.d(j5), x0.c.e(j5), x0.f.d(j6) + x0.c.d(j5), x0.f.b(j6) + x0.c.e(j5));
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0061 A[LOOP:0: B:4:0x000b->B:35:0x0061, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:40:0x0064 A[EDGE_INSN: B:40:0x0064->B:36:0x0064 BREAK  A[LOOP:0: B:4:0x000b->B:35:0x0061], SYNTHETIC] */
    public static final w1.n c(d0 d0Var, boolean z2) {
        r0.o oVar = d0Var.f4801y.f4972e;
        Object obj = null;
        if ((oVar.f5684g & 8) != 0) {
            loop0: while (oVar != null) {
                if ((oVar.f5683f & 8) == 0) {
                    if ((oVar.f5684g & 8) != 0) {
                        break;
                        break;
                    }
                    oVar = oVar.f5686i;
                } else {
                    r0.o oVarE = oVar;
                    h0.e eVar = null;
                    while (oVarE != null) {
                        if (oVarE instanceof l1) {
                            obj = oVarE;
                            break loop0;
                        }
                        if ((oVarE.f5683f & 8) != 0 && (oVarE instanceof p1.m)) {
                            int i5 = 0;
                            for (r0.o oVar2 = ((p1.m) oVarE).f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
                                if ((oVar2.f5683f & 8) != 0) {
                                    i5++;
                                    if (i5 == 1) {
                                        oVarE = oVar2;
                                    } else {
                                        if (eVar == null) {
                                            eVar = new h0.e(new r0.o[16]);
                                        }
                                        if (oVarE != null) {
                                            eVar.b(oVarE);
                                            oVarE = null;
                                        }
                                        eVar.b(oVar2);
                                    }
                                }
                            }
                            if (i5 == 1) {
                            }
                        }
                        oVarE = p1.f.e(eVar);
                    }
                    if ((oVar.f5684g & 8) != 0) {
                        break;
                    }
                    oVar = oVar.f5686i;
                }
            }
        }
        b4.l.b(obj);
        r0.o oVar3 = ((r0.o) ((l1) obj)).f5681d;
        w1.i iVarN = d0Var.n();
        b4.l.b(iVarN);
        return new w1.n(oVar3, z2, d0Var, iVarN);
    }

    public static final void d(boolean z2, j2.h hVar, e0 e0Var, f0.p pVar, int i5) {
        int i6;
        boolean z3;
        j2.h hVar2;
        v vVar = (v) pVar;
        vVar.X(-1344558920);
        if ((i5 & 6) == 0) {
            i6 = (vVar.g(z2) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(hVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(e0Var) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
            z3 = z2;
            hVar2 = hVar;
        } else {
            int i7 = i6 & 14;
            boolean zF = (i7 == 4) | vVar.f(e0Var);
            Object objK = vVar.K();
            f0.h hVar3 = f0.o.f2281a;
            if (zF || objK == hVar3) {
                objK = new z.d0(e0Var, z2);
                vVar.f0(objK);
            }
            u0 u0Var = (u0) objK;
            boolean zH = vVar.h(e0Var) | (i7 == 4);
            Object objK2 = vVar.K();
            if (zH || objK2 == hVar3) {
                objK2 = new f0(e0Var, z2);
                vVar.f0(objK2);
            }
            z.l lVar = (z.l) objK2;
            boolean zF2 = g0.f(e0Var.j().f2063b);
            boolean zH2 = vVar.h(u0Var);
            Object objK3 = vVar.K();
            if (zH2 || objK3 == hVar3) {
                objK3 = new b0.t(u0Var, null, 24);
                vVar.f0(objK3);
            }
            z3 = z2;
            hVar2 = hVar;
            j.c(lVar, z3, hVar2, zF2, 0L, z.a(r0.m.f5680a, u0Var, (a4.e) objK3), vVar, (i6 << 3) & 1008);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z.f(z3, hVar2, e0Var, i5);
        }
    }

    public static final long e(o0 o0Var, x0.d dVar, x0.d dVar2, int i5) {
        long jS = s(o0Var, dVar, i5);
        if (g0.b(jS)) {
            return g0.f6768b;
        }
        long jS2 = s(o0Var, dVar2, i5);
        if (g0.b(jS2)) {
            return g0.f6768b;
        }
        int i6 = (int) (jS >> 32);
        int i7 = (int) (jS2 & 4294967295L);
        return j.g(Math.min(i6, i6), Math.max(i7, i7));
    }

    public static final boolean f(y1.e0 e0Var, int i5) {
        int iE = e0Var.e(i5);
        return i5 == e0Var.h(iE) || i5 == e0Var.d(iE, false) ? e0Var.i(i5) != e0Var.a(i5) : e0Var.a(i5) != e0Var.a(i5 - 1);
    }

    public static final r0.p g(r0.p pVar, float f5) {
        return f5 == 1.0f ? pVar : androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, f5, 0.0f, null, true, 126971);
    }

    public static void h(String str, Object obj) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void i(int i5, int i6, int i7) {
        if (i5 >= 0 && i6 <= i7) {
            if (i5 > i6) {
                throw new IllegalArgumentException(b.b.d("fromIndex: ", i5, " > toIndex: ", i6));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i5 + ", toIndex: " + i6 + ", size: " + i7);
    }

    public static int j(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static final ArrayList k(q0.a aVar) {
        int[] iArr = {201, 202, 204, 206, 207, 125, -127, 126665345, 200};
        List list = aVar.f5086a;
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        while (i5 < size) {
            int i6 = i5 + 1;
            q0.b bVar = (q0.b) list.get(i5);
            int i7 = bVar.f5087a;
            int i8 = 0;
            while (true) {
                if (i8 >= 9) {
                    i8 = -1;
                    break;
                }
                if (i7 == iArr[i8]) {
                    break;
                }
                i8++;
            }
            if (!(i8 >= 0)) {
                if (bVar.f5087a == 100) {
                    int i9 = i5 + 2;
                    if (i9 < size && ((q0.b) list.get(i9)).f5087a == 1000) {
                        break;
                    }
                    if (!arrayList.isEmpty()) {
                        arrayList.remove(j.z(arrayList));
                    }
                } else {
                    arrayList.add(bVar);
                }
            }
            i5 = i6;
        }
        return arrayList;
    }

    public static final int l(int i5, List list) {
        byte b5;
        int size = list.size() - 1;
        int i6 = 0;
        while (i6 <= size) {
            int i7 = (i6 + size) >>> 1;
            y1.o oVar = (y1.o) list.get(i7);
            if (oVar.f6800b > i5) {
                b5 = 1;
            } else {
                b5 = oVar.f6801c <= i5 ? (byte) -1 : (byte) 0;
            }
            if (b5 < 0) {
                i6 = i7 + 1;
            } else {
                if (b5 <= 0) {
                    return i7;
                }
                size = i7 - 1;
            }
        }
        return -(i6 + 1);
    }

    public static final int m(int i5, ArrayList arrayList) {
        byte b5;
        int size = arrayList.size() - 1;
        int i6 = 0;
        while (i6 <= size) {
            int i7 = (i6 + size) >>> 1;
            y1.o oVar = (y1.o) arrayList.get(i7);
            if (oVar.f6802d > i5) {
                b5 = 1;
            } else {
                b5 = oVar.f6803e <= i5 ? (byte) -1 : (byte) 0;
            }
            if (b5 < 0) {
                i6 = i7 + 1;
            } else {
                if (b5 <= 0) {
                    return i7;
                }
                size = i7 - 1;
            }
        }
        return -(i6 + 1);
    }

    public static final int n(ArrayList arrayList, float f5) {
        byte b5;
        if (f5 <= 0.0f) {
            return 0;
        }
        if (f5 >= ((y1.o) q3.l.b0(arrayList)).f6805g) {
            return j.z(arrayList);
        }
        int size = arrayList.size() - 1;
        int i5 = 0;
        while (i5 <= size) {
            int i6 = (i5 + size) >>> 1;
            y1.o oVar = (y1.o) arrayList.get(i6);
            if (oVar.f6804f > f5) {
                b5 = 1;
            } else {
                b5 = oVar.f6805g <= f5 ? (byte) -1 : (byte) 0;
            }
            if (b5 < 0) {
                i5 = i6 + 1;
            } else {
                if (b5 <= 0) {
                    return i6;
                }
                size = i6 - 1;
            }
        }
        return -(i5 + 1);
    }

    public static final void o(ArrayList arrayList, long j5, a4.c cVar) {
        int size = arrayList.size();
        for (int iL = l(g0.e(j5), arrayList); iL < size; iL++) {
            y1.o oVar = (y1.o) arrayList.get(iL);
            if (oVar.f6800b >= g0.d(j5)) {
                return;
            }
            if (oVar.f6800b != oVar.f6801c) {
                cVar.c(oVar);
            }
        }
    }

    public static t3.f p(t3.f fVar, t3.g gVar) {
        b4.l.e("key", gVar);
        if (b4.l.a(fVar.getKey(), gVar)) {
            return fVar;
        }
        return null;
    }

    public static final int q(y1.m mVar, long j5, o2 o2Var) {
        float f5 = o2Var != null ? o2Var.f() : 0.0f;
        int iC = mVar.c(x0.c.e(j5));
        if (x0.c.e(j5) < mVar.d(iC) - f5 || x0.c.e(j5) > mVar.b(iC) + f5 || x0.c.d(j5) < (-f5) || x0.c.d(j5) > mVar.f6792d + f5) {
            return -1;
        }
        return iC;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x006a A[LOOP:0: B:4:0x000b->B:37:0x006a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x006d A[EDGE_INSN: B:42:0x006d->B:38:0x006d BREAK  A[LOOP:0: B:4:0x000b->B:37:0x006a], SYNTHETIC] */
    public static final l1 r(d0 d0Var) {
        r0.o oVar = d0Var.f4801y.f4972e;
        Object obj = null;
        if ((oVar.f5684g & 8) != 0) {
            loop0: while (oVar != null) {
                if ((oVar.f5683f & 8) == 0) {
                    if ((oVar.f5684g & 8) != 0) {
                        break;
                        break;
                    }
                    oVar = oVar.f5686i;
                } else {
                    r0.o oVarE = oVar;
                    h0.e eVar = null;
                    while (oVarE != null) {
                        if (oVarE instanceof l1) {
                            if (((l1) oVarE).c0()) {
                                obj = oVarE;
                                break loop0;
                            }
                        } else if ((oVarE.f5683f & 8) != 0 && (oVarE instanceof p1.m)) {
                            int i5 = 0;
                            for (r0.o oVar2 = ((p1.m) oVarE).f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
                                if ((oVar2.f5683f & 8) != 0) {
                                    i5++;
                                    if (i5 == 1) {
                                        oVarE = oVar2;
                                    } else {
                                        if (eVar == null) {
                                            eVar = new h0.e(new r0.o[16]);
                                        }
                                        if (oVarE != null) {
                                            eVar.b(oVarE);
                                            oVarE = null;
                                        }
                                        eVar.b(oVar2);
                                    }
                                }
                            }
                            if (i5 == 1) {
                            }
                        }
                        oVarE = p1.f.e(eVar);
                    }
                    if ((oVar.f5684g & 8) != 0) {
                        break;
                    }
                    oVar = oVar.f5686i;
                }
            }
        }
        return (l1) obj;
    }

    public static final long s(o0 o0Var, x0.d dVar, int i5) {
        e1 e1VarD = o0Var.d();
        y1.m mVar = e1VarD != null ? e1VarD.f5899a.f6754b : null;
        n1.p pVarC = o0Var.c();
        return (mVar == null || pVarC == null) ? g0.f6768b : mVar.f(dVar.h(pVarC.K(0L)), i5, c0.f6738b);
    }

    public static final j2.h t(y1.e0 e0Var, int i5) {
        y1.d0 d0Var = e0Var.f6753a;
        if (d0Var.f6743a.f6759d.length() != 0) {
            int iE = e0Var.e(i5);
            if ((i5 != 0 && iE == e0Var.e(i5 - 1)) || (i5 != d0Var.f6743a.f6759d.length() && iE == e0Var.e(i5 + 1))) {
                return e0Var.a(i5);
            }
        }
        return e0Var.i(i5);
    }

    public static final boolean u(int i5) {
        int type = Character.getType(i5);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    public static final boolean v(e0 e0Var, boolean z2) {
        n1.p pVarC;
        o0 o0Var = e0Var.f6930d;
        if (o0Var == null || (pVarC = o0Var.c()) == null) {
            return false;
        }
        x0.d dVarW = q.f.w(pVarC);
        long jI = e0Var.i(z2);
        float f5 = dVarW.f6534a;
        float f6 = dVarW.f6536c;
        float fD = x0.c.d(jI);
        if (f5 > fD || fD > f6) {
            return false;
        }
        float f7 = dVarW.f6535b;
        float f8 = dVarW.f6537d;
        float fE = x0.c.e(jI);
        return f7 <= fE && fE <= f8;
    }

    public static final boolean w(int i5) {
        return Character.isWhitespace(i5) || i5 == 160;
    }

    public static final boolean x(int i5) {
        int type;
        return (!w(i5) || (type = Character.getType(i5)) == 14 || type == 13 || i5 == 10) ? false : true;
    }

    public static t3.h y(t3.f fVar, t3.g gVar) {
        b4.l.e("key", gVar);
        return b4.l.a(fVar.getKey(), gVar) ? t3.i.f5807d : fVar;
    }

    public static MappedByteBuffer z(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return null;
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return map;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                try {
                    parcelFileDescriptorOpenFileDescriptor.close();
                    throw th3;
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                    throw th3;
                }
            }
        } catch (IOException unused) {
        }
    }
}
