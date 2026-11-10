package p0;

import f0.k1;
import f0.x1;
import j.g0;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class b extends f {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f4662n = new int[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a4.c f4663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a4.c f4664f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4665g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public j.a0 f4666h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f4667i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k f4668j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f4669k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4670l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4671m;

    public b(long j5, k kVar, a4.c cVar, a4.c cVar2) {
        super(j5, kVar);
        this.f4663e = cVar;
        this.f4664f = cVar2;
        this.f4668j = k.f4709h;
        this.f4669k = f4662n;
        this.f4670l = 1;
    }

    public final void A(long j5) {
        synchronized (m.f4719c) {
            this.f4668j = this.f4668j.e(j5);
        }
    }

    public void B(j.a0 a0Var) {
        this.f4666h = a0Var;
    }

    public b C(a4.c cVar, a4.c cVar2) throws Throwable {
        if (this.f4695c) {
            x1.a("Cannot use a disposed snapshot");
        }
        if (this.f4671m && this.f4696d < 0) {
            x1.b("Unsupported operation on a disposed or applied snapshot");
        }
        A(g());
        Object obj = m.f4719c;
        synchronized (obj) {
            try {
                long j5 = m.f4721e;
                long j6 = 1;
                m.f4721e = j5 + j6;
                m.f4720d = m.f4720d.e(j5);
                k kVarD = d();
                r(kVarD.e(j5));
                try {
                    c cVar3 = new c(j5, m.d(kVarD, g() + j6, j5), m.k(cVar, e(), true), m.l(cVar2, i()), this);
                    if (this.f4671m || this.f4695c) {
                        return cVar3;
                    }
                    long jG = g();
                    synchronized (obj) {
                        long j7 = m.f4721e;
                        m.f4721e = j7 + j6;
                        s(j7);
                        m.f4720d = m.f4720d.e(g());
                    }
                    r(m.d(d(), jG + j6, g()));
                    return cVar3;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // p0.f
    public final void b() {
        m.f4720d = m.f4720d.b(g()).a(this.f4668j);
    }

    @Override // p0.f
    public void c() {
        if (this.f4695c) {
            return;
        }
        this.f4695c = true;
        synchronized (m.f4719c) {
            o();
        }
        l();
    }

    @Override // p0.f
    public boolean f() {
        return false;
    }

    @Override // p0.f
    public int h() {
        return this.f4665g;
    }

    @Override // p0.f
    public a4.c i() {
        return this.f4664f;
    }

    @Override // p0.f
    public void k() {
        this.f4670l++;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x008c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x008e A[LOOP:0: B:18:0x0039->B:35:0x008e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:39:0x0091 A[EDGE_INSN: B:39:0x0091->B:36:0x0091 BREAK  A[LOOP:0: B:18:0x0039->B:35:0x008e], SYNTHETIC] */
    @Override // p0.f
    public void l() {
        if (this.f4670l <= 0) {
            x1.a("no pending nested snapshots");
        }
        int i5 = this.f4670l - 1;
        this.f4670l = i5;
        if (i5 != 0 || this.f4671m) {
            return;
        }
        j.a0 a0VarX = x();
        if (a0VarX != null) {
            if (this.f4671m) {
                x1.b("Unsupported operation on a snapshot that has been applied");
            }
            B(null);
            long jG = g();
            Object[] objArr = a0VarX.f3067b;
            long[] jArr = a0VarX.f3066a;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i6 = 0;
                while (true) {
                    long j5 = jArr[i6];
                    if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i6 != length) {
                            break;
                            break;
                        }
                        i6++;
                    } else {
                        int i7 = 8 - ((~(i6 - length)) >>> 31);
                        for (int i8 = 0; i8 < i7; i8++) {
                            if ((255 & j5) < 128) {
                                for (z zVarA = ((x) objArr[(i6 << 3) + i8]).a(); zVarA != null; zVarA = zVarA.f4768b) {
                                    long j6 = zVarA.f4767a;
                                    if (j6 == jG || q3.l.V(this.f4668j, Long.valueOf(j6))) {
                                        k1 k1Var = m.f4717a;
                                        zVarA.f4767a = 0L;
                                    }
                                }
                            }
                            j5 >>= 8;
                        }
                        if (i7 != 8) {
                            break;
                        } else if (i6 != length) {
                            break;
                        } else {
                            i6++;
                        }
                    }
                }
            }
        }
        a();
    }

    @Override // p0.f
    public void m() {
        if (this.f4671m || this.f4695c) {
            return;
        }
        v();
    }

    @Override // p0.f
    public void n(x xVar) {
        j.a0 a0VarX = x();
        if (a0VarX == null) {
            int i5 = g0.f3106a;
            a0VarX = new j.a0();
            B(a0VarX);
        }
        a0VarX.a(xVar);
    }

    @Override // p0.f
    public final void p() {
        int length = this.f4669k.length;
        for (int i5 = 0; i5 < length; i5++) {
            m.u(this.f4669k[i5]);
        }
        o();
    }

    @Override // p0.f
    public void t(int i5) {
        this.f4665g = i5;
    }

    @Override // p0.f
    public f u(a4.c cVar) throws Throwable {
        if (this.f4695c) {
            x1.a("Cannot use a disposed snapshot");
        }
        if (this.f4671m && this.f4696d < 0) {
            x1.b("Unsupported operation on a disposed or applied snapshot");
        }
        long jG = g();
        A(g());
        Object obj = m.f4719c;
        synchronized (obj) {
            try {
                long j5 = m.f4721e;
                long j6 = 1;
                m.f4721e = j5 + j6;
                m.f4720d = m.f4720d.e(j5);
                try {
                    d dVar = new d(j5, m.d(d(), jG + j6, j5), m.k(cVar, e(), true), this);
                    if (this.f4671m || this.f4695c) {
                        return dVar;
                    }
                    long jG2 = g();
                    synchronized (obj) {
                        long j7 = m.f4721e;
                        m.f4721e = j7 + j6;
                        s(j7);
                        m.f4720d = m.f4720d.e(g());
                    }
                    r(m.d(d(), jG2 + j6, g()));
                    return dVar;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void v() {
        long j5;
        A(g());
        if (this.f4671m || this.f4695c) {
            return;
        }
        long jG = g();
        synchronized (m.f4719c) {
            long j6 = m.f4721e;
            j5 = 1;
            m.f4721e = j6 + j5;
            s(j6);
            m.f4720d = m.f4720d.e(g());
        }
        r(m.d(d(), jG + j5, g()));
    }

    /* JADX WARN: Code duplicated, block: B:101:0x014a A[EDGE_INSN: B:101:0x014a->B:77:0x014a BREAK  A[LOOP:4: B:66:0x011b->B:76:0x0147], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x0108 A[Catch: all -> 0x00fe, LOOP:2: B:48:0x00d6->B:60:0x0108, LOOP_END, TryCatch #1 {all -> 0x00fe, blocks: (B:43:0x00ba, B:45:0x00ca, B:48:0x00d6, B:50:0x00e2, B:52:0x00ec, B:54:0x00f2, B:57:0x0100, B:63:0x0111, B:66:0x011b, B:68:0x0125, B:70:0x012f, B:72:0x0135, B:73:0x013f, B:76:0x0147, B:77:0x014a, B:79:0x014e, B:81:0x0155, B:82:0x0161, B:60:0x0108), top: B:91:0x00ba }] */
    /* JADX WARN: Code duplicated, block: B:61:0x010b  */
    /* JADX WARN: Code duplicated, block: B:75:0x0145 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x0147 A[Catch: all -> 0x00fe, LOOP:4: B:66:0x011b->B:76:0x0147, LOOP_END, TryCatch #1 {all -> 0x00fe, blocks: (B:43:0x00ba, B:45:0x00ca, B:48:0x00d6, B:50:0x00e2, B:52:0x00ec, B:54:0x00f2, B:57:0x0100, B:63:0x0111, B:66:0x011b, B:68:0x0125, B:70:0x012f, B:72:0x0135, B:73:0x013f, B:76:0x0147, B:77:0x014a, B:79:0x014e, B:81:0x0155, B:82:0x0161, B:60:0x0108), top: B:91:0x00ba }] */
    /* JADX WARN: Code duplicated, block: B:96:0x010f A[EDGE_INSN: B:96:0x010f->B:62:0x010f BREAK  A[LOOP:2: B:48:0x00d6->B:60:0x0108], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.Collection, java.util.List] */
    public r w() {
        HashMap mapB;
        ?? r4;
        j.a0 a0Var;
        long j5;
        long j6;
        j.a0 a0VarX = x();
        if (a0VarX != null) {
            long j7 = m.f4726j.f4694b;
            mapB = m.b(j7, this, m.f4720d.b(j7));
        } else {
            mapB = null;
        }
        q3.s sVar = q3.s.f5477d;
        synchronized (m.f4719c) {
            try {
                m.c(this);
                if (a0VarX == null || a0VarX.f3069d == 0) {
                    b();
                    a aVar = m.f4726j;
                    j.a0 a0Var2 = aVar.f4666h;
                    m.v(aVar, m.f4717a);
                    if (a0Var2 == null || !a0Var2.h()) {
                        r4 = sVar;
                        a0Var = null;
                    } else {
                        r4 = m.f4724h;
                        a0Var = a0Var2;
                    }
                } else {
                    a aVar2 = m.f4726j;
                    r rVarZ = z(m.f4721e, a0VarX, mapB, m.f4720d.b(aVar2.f4694b));
                    if (!rVarZ.equals(h.f4697b)) {
                        return rVarZ;
                    }
                    b();
                    a0Var = aVar2.f4666h;
                    m.v(aVar2, m.f4717a);
                    B(null);
                    aVar2.f4666h = null;
                    r4 = m.f4724h;
                }
                this.f4671m = true;
                if (a0Var != null) {
                    h0.h hVar = new h0.h(a0Var);
                    if (!a0Var.g()) {
                        int size = r4.size();
                        for (int i5 = 0; i5 < size; i5++) {
                            ((a4.e) r4.get(i5)).invoke(hVar, this);
                        }
                    }
                }
                if (a0VarX != null && a0VarX.h()) {
                    h0.h hVar2 = new h0.h(a0VarX);
                    int size2 = r4.size();
                    for (int i6 = 0; i6 < size2; i6++) {
                        ((a4.e) r4.get(i6)).invoke(hVar2, this);
                    }
                }
                synchronized (m.f4719c) {
                    try {
                        p();
                        m.f();
                        if (a0Var != null) {
                            Object[] objArr = a0Var.f3067b;
                            long[] jArr = a0Var.f3066a;
                            int length = jArr.length - 2;
                            if (length >= 0) {
                                int i7 = 0;
                                j5 = 128;
                                while (true) {
                                    long j8 = jArr[i7];
                                    j6 = 255;
                                    if ((((~j8) << 7) & j8 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i7 != length) {
                                            break;
                                            break;
                                        }
                                        i7++;
                                    } else {
                                        int i8 = 8 - ((~(i7 - length)) >>> 31);
                                        for (int i9 = 0; i9 < i8; i9++) {
                                            if ((j8 & 255) < 128) {
                                                m.q((x) objArr[(i7 << 3) + i9]);
                                            }
                                            j8 >>= 8;
                                        }
                                        if (i8 != 8) {
                                            break;
                                        }
                                        if (i7 != length) {
                                            break;
                                        }
                                        i7++;
                                    }
                                }
                            } else {
                                j5 = 128;
                                j6 = 255;
                            }
                        } else {
                            j5 = 128;
                            j6 = 255;
                        }
                        if (a0VarX != null) {
                            Object[] objArr2 = a0VarX.f3067b;
                            long[] jArr2 = a0VarX.f3066a;
                            int length2 = jArr2.length - 2;
                            if (length2 >= 0) {
                                int i10 = 0;
                                while (true) {
                                    long j9 = jArr2[i10];
                                    if ((((~j9) << 7) & j9 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i10 != length2) {
                                            break;
                                            break;
                                        }
                                        i10++;
                                    } else {
                                        int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                        for (int i12 = 0; i12 < i11; i12++) {
                                            if ((j9 & j6) < j5) {
                                                m.q((x) objArr2[(i10 << 3) + i12]);
                                            }
                                            j9 >>= 8;
                                        }
                                        if (i11 != 8) {
                                            break;
                                        }
                                        if (i10 != length2) {
                                            break;
                                        }
                                        i10++;
                                    }
                                }
                            }
                        }
                        ArrayList arrayList = this.f4667i;
                        if (arrayList != null) {
                            int size3 = arrayList.size();
                            for (int i13 = 0; i13 < size3; i13++) {
                                m.q((x) arrayList.get(i13));
                            }
                        }
                        this.f4667i = null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return h.f4697b;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public j.a0 x() {
        return this.f4666h;
    }

    @Override // p0.f
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public a4.c e() {
        return this.f4663e;
    }

    /* JADX WARN: Code duplicated, block: B:67:0x0173  */
    /* JADX WARN: Code duplicated, block: B:69:0x017d  */
    /* JADX WARN: Code duplicated, block: B:78:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:80:0x01a9 A[LOOP:3: B:79:0x01a7->B:80:0x01a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:84:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:88:0x0190 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final r z(long j5, j.a0 a0Var, HashMap map, k kVar) {
        ArrayList arrayList;
        ArrayList arrayListE0;
        ArrayList arrayList2;
        int size;
        int i5;
        ArrayList arrayList3;
        int size2;
        int i6;
        x xVar;
        z zVar;
        k kVar2;
        Object[] objArr;
        long[] jArr;
        k kVar3;
        Object[] objArr2;
        long[] jArr2;
        int i7;
        long j6;
        ArrayList arrayList4;
        z zVarB;
        k kVarD = d().e(g()).d(this.f4668j);
        Object[] objArr3 = a0Var.f3067b;
        long[] jArr3 = a0Var.f3066a;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i8 = 0;
            arrayList2 = null;
            arrayListE0 = null;
            while (true) {
                long j7 = jArr3[i8];
                if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i9 = 8 - ((~(i8 - length)) >>> 31);
                    int i10 = 0;
                    while (i10 < i9) {
                        if ((j7 & 255) < 128) {
                            objArr2 = objArr3;
                            x xVar2 = (x) objArr3[(i8 << 3) + i10];
                            jArr2 = jArr3;
                            z zVarA = xVar2.a();
                            i7 = i10;
                            ArrayList arrayList5 = arrayList2;
                            z zVarS = m.s(zVarA, j5, kVar);
                            if (zVarS == null) {
                                kVar3 = kVarD;
                                arrayList4 = arrayListE0;
                                j6 = j7;
                            } else {
                                arrayList4 = arrayListE0;
                                j6 = j7;
                                z zVarS2 = m.s(zVarA, g(), kVarD);
                                if (zVarS2 == null) {
                                    kVar3 = kVarD;
                                } else {
                                    kVar3 = kVarD;
                                    if (zVarS2.f4767a != 1 && !zVarS.equals(zVarS2)) {
                                        z zVarS3 = m.s(zVarA, g(), d());
                                        if (zVarS3 == null) {
                                            m.r();
                                            throw null;
                                        }
                                        if (map == null || (zVarB = (z) map.get(zVarS)) == null) {
                                            zVarB = xVar2.b(zVarS2, zVarS, zVarS3);
                                        }
                                        if (zVarB == null) {
                                            return new g();
                                        }
                                        if (!zVarB.equals(zVarS3)) {
                                            if (zVarB.equals(zVarS)) {
                                                ArrayList arrayList6 = arrayList5 == null ? new ArrayList() : arrayList5;
                                                arrayList6.add(new p3.f(xVar2, zVarS.b(g())));
                                                arrayListE0 = arrayList4 == null ? new ArrayList() : arrayList4;
                                                arrayListE0.add(xVar2);
                                                arrayList2 = arrayList6;
                                            } else {
                                                arrayList2 = arrayList5 == null ? new ArrayList() : arrayList5;
                                                arrayList2.add(!zVarB.equals(zVarS2) ? new p3.f(xVar2, zVarB) : new p3.f(xVar2, zVarS2.b(g())));
                                            }
                                        }
                                        arrayListE0 = arrayList4;
                                    }
                                }
                            }
                            arrayList2 = arrayList5;
                            arrayListE0 = arrayList4;
                        } else {
                            kVar3 = kVarD;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i7 = i10;
                            j6 = j7;
                        }
                        j7 = j6 >> 8;
                        i10 = i7 + 1;
                        jArr3 = jArr2;
                        objArr3 = objArr2;
                        kVarD = kVar3;
                    }
                    kVar2 = kVarD;
                    objArr = objArr3;
                    jArr = jArr3;
                    if (i9 != 8) {
                        break;
                    }
                } else {
                    kVar2 = kVarD;
                    objArr = objArr3;
                    jArr = jArr3;
                }
                if (i8 != length) {
                    i8++;
                    jArr3 = jArr;
                    objArr3 = objArr;
                    kVarD = kVar2;
                } else {
                    arrayList = arrayList2;
                }
            }
            if (arrayList2 != null) {
                v();
                size2 = arrayList2.size();
                for (i6 = 0; i6 < size2; i6++) {
                    p3.f fVar = (p3.f) arrayList2.get(i6);
                    xVar = (x) fVar.f5014d;
                    zVar = (z) fVar.f5015e;
                    zVar.f4767a = j5;
                    synchronized (m.f4719c) {
                        zVar.f4768b = xVar.a();
                        xVar.c(zVar);
                    }
                }
            }
            if (arrayListE0 != null) {
                size = arrayListE0.size();
                for (i5 = 0; i5 < size; i5++) {
                    a0Var.j((x) arrayListE0.get(i5));
                }
                arrayList3 = this.f4667i;
                if (arrayList3 != null) {
                    arrayListE0 = q3.l.e0(arrayList3, arrayListE0);
                }
                this.f4667i = arrayListE0;
            }
            return h.f4697b;
        }
        arrayList = null;
        arrayListE0 = null;
        arrayList2 = arrayList;
        if (arrayList2 != null) {
            v();
            size2 = arrayList2.size();
            while (i6 < size2) {
                p3.f fVar2 = (p3.f) arrayList2.get(i6);
                xVar = (x) fVar2.f5014d;
                zVar = (z) fVar2.f5015e;
                zVar.f4767a = j5;
                synchronized (m.f4719c) {
                    zVar.f4768b = xVar.a();
                    xVar.c(zVar);
                }
            }
        }
        if (arrayListE0 != null) {
            size = arrayListE0.size();
            while (i5 < size) {
                a0Var.j((x) arrayListE0.get(i5));
            }
            arrayList3 = this.f4667i;
            if (arrayList3 != null) {
                arrayListE0 = q3.l.e0(arrayList3, arrayListE0);
            }
            this.f4667i = arrayListE0;
        }
        return h.f4697b;
    }
}
