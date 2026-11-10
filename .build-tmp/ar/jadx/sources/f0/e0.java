package f0;

import android.os.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 implements a0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b0 f2130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a1.b f2131e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReference f2132f = new AtomicReference(null);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f2133g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final j.c0 f2134h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final t2 f2135i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final j.z f2136j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final j.a0 f2137k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j.a0 f2138l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j.z f2139m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g0.a f2140n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final g0.a f2141o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final j.z f2142p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public j.z f2143q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2144r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public e0 f2145s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2146t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a1.d f2147u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final n0.j f2148v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final v f2149w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2150x;

    public e0(b0 b0Var, a1.b bVar) {
        this.f2130d = b0Var;
        this.f2131e = bVar;
        j.c0 c0Var = new j.c0(new j.a0());
        this.f2134h = c0Var;
        t2 t2Var = new t2();
        if (b0Var.c()) {
            t2Var.f2343n = new j.r();
        }
        if (b0Var.e()) {
            t2Var.b();
        }
        this.f2135i = t2Var;
        this.f2136j = d4.a.o();
        this.f2137k = new j.a0();
        this.f2138l = new j.a0();
        this.f2139m = d4.a.o();
        g0.a aVar = new g0.a();
        this.f2140n = aVar;
        g0.a aVar2 = new g0.a();
        this.f2141o = aVar2;
        this.f2142p = d4.a.o();
        this.f2143q = d4.a.o();
        a1.d dVar = new a1.d(5, b0Var);
        this.f2147u = dVar;
        this.f2148v = new n0.j();
        v vVar = new v(bVar, b0Var, t2Var, c0Var, aVar, aVar2, dVar, this);
        b0Var.n(vVar);
        this.f2149w = vVar;
    }

    public final void A(a4.e eVar) {
        boolean z2;
        synchronized (this.f2133g) {
            z2 = true;
            if (this.f2150x != 1) {
                z2 = false;
            }
            if (z2) {
                this.f2150x = 0;
            }
        }
        p();
        if (z2) {
            j((n0.d) eVar);
        } else {
            this.f2130d.a(this, (n0.d) eVar);
        }
    }

    public final void a() {
        this.f2132f.set(null);
        this.f2140n.f2454k.O();
        this.f2141o.f2454k.O();
        j.c0 c0Var = this.f2134h;
        if (c0Var.f3089d.g()) {
            return;
        }
        n0.j jVar = this.f2148v;
        try {
            jVar.e(c0Var, this.f2149w.y());
            jVar.b();
        } finally {
            jVar.a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0070  */
    public final void b(Object obj, boolean z2) {
        int i5;
        Object objE = this.f2136j.e(obj);
        if (objE == null) {
            return;
        }
        boolean z3 = objE instanceof j.a0;
        y0 y0Var = y0.f2422d;
        j.a0 a0Var = this.f2137k;
        j.a0 a0Var2 = this.f2138l;
        j.z zVar = this.f2142p;
        if (!z3) {
            b2 b2Var = (b2) objE;
            if (d4.a.F(zVar, obj, b2Var) || b2Var.c(obj) == y0Var) {
                return;
            }
            if (b2Var.f2101g == null || z2) {
                a0Var.a(b2Var);
                return;
            } else {
                a0Var2.a(b2Var);
                return;
            }
        }
        j.a0 a0Var3 = (j.a0) objE;
        Object[] objArr = a0Var3.f3067b;
        long[] jArr = a0Var3.f3066a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i6 = 0;
        while (true) {
            long j5 = jArr[i6];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8;
                int i8 = 8 - ((~(i6 - length)) >>> 31);
                int i9 = 0;
                while (i9 < i8) {
                    if ((j5 & 255) < 128) {
                        b2 b2Var2 = (b2) objArr[(i6 << 3) + i9];
                        if (d4.a.F(zVar, obj, b2Var2)) {
                            i5 = i7;
                        } else {
                            i5 = i7;
                            if (b2Var2.c(obj) != y0Var) {
                                if (b2Var2.f2101g == null || z2) {
                                    a0Var.a(b2Var2);
                                } else {
                                    a0Var2.a(b2Var2);
                                }
                            }
                        }
                    } else {
                        i5 = i7;
                    }
                    j5 >>= i5;
                    i9++;
                    i7 = i5;
                }
                if (i8 != i7) {
                    return;
                }
            }
            if (i6 == length) {
                return;
            } else {
                i6++;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0235 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:111:0x0237 A[LOOP:6: B:94:0x01e3->B:111:0x0237, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:204:0x0244 A[EDGE_INSN: B:204:0x0244->B:113:0x0244 BREAK  A[LOOP:6: B:94:0x01e3->B:111:0x0237], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:224:0x0122 A[EDGE_INSN: B:224:0x0122->B:219:0x0122 BREAK  A[LOOP:13: B:63:0x0151->B:74:0x0185], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0183 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x0185 A[LOOP:13: B:63:0x0151->B:74:0x0185, LOOP_END] */
    public final void c(Set set, boolean z2) {
        long j5;
        long j6;
        long j7;
        char c5;
        long[] jArr;
        long[] jArr2;
        long j8;
        boolean zC;
        long[] jArr3;
        long j9;
        long[] jArr4;
        long[] jArr5;
        int i5;
        long j10;
        boolean zG;
        int i6;
        long j11;
        long[] jArr6;
        long[] jArr7;
        char c6;
        long j12;
        int i7;
        int i8;
        boolean z3 = set instanceof h0.h;
        j.z zVar = this.f2139m;
        Object obj = null;
        int i9 = 8;
        if (z3) {
            j.a0 a0Var = ((h0.h) set).f2589d;
            Object[] objArr = a0Var.f3067b;
            long[] jArr8 = a0Var.f3066a;
            int length = jArr8.length - 2;
            if (length >= 0) {
                int i10 = 0;
                j5 = 128;
                j6 = 255;
                while (true) {
                    long j13 = jArr8[i10];
                    char c7 = 7;
                    j7 = -9187201950435737472L;
                    if ((((~j13) << 7) & j13 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i11 = 8 - ((~(i10 - length)) >>> 31);
                        int i12 = 0;
                        while (i12 < i11) {
                            if ((j13 & 255) < 128) {
                                Object obj2 = objArr[(i10 << 3) + i12];
                                c6 = c7;
                                if (obj2 instanceof b2) {
                                    ((b2) obj2).c(obj);
                                } else {
                                    b(obj2, z2);
                                    Object objE = zVar.e(obj2);
                                    if (objE != null) {
                                        if (objE instanceof j.a0) {
                                            j.a0 a0Var2 = (j.a0) objE;
                                            Object[] objArr2 = a0Var2.f3067b;
                                            long[] jArr9 = a0Var2.f3066a;
                                            int length2 = jArr9.length - 2;
                                            if (length2 >= 0) {
                                                int i13 = i9;
                                                i7 = length;
                                                int i14 = 0;
                                                while (true) {
                                                    long j14 = jArr9[i14];
                                                    j12 = j13;
                                                    long[] jArr10 = jArr9;
                                                    if ((((~j14) << c6) & j14 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i15 = 8 - ((~(i14 - length2)) >>> 31);
                                                        int i16 = 0;
                                                        while (i16 < i15) {
                                                            if ((j14 & 255) < 128) {
                                                                b((n0) objArr2[(i14 << 3) + i16], z2);
                                                            }
                                                            j14 >>= i13;
                                                            i16++;
                                                            jArr8 = jArr8;
                                                        }
                                                        jArr7 = jArr8;
                                                        if (i15 != i13) {
                                                            break;
                                                        }
                                                    } else {
                                                        jArr7 = jArr8;
                                                    }
                                                    if (i14 == length2) {
                                                        break;
                                                    }
                                                    i14++;
                                                    jArr9 = jArr10;
                                                    j13 = j12;
                                                    jArr8 = jArr7;
                                                    i13 = 8;
                                                }
                                            }
                                        } else {
                                            jArr7 = jArr8;
                                            j12 = j13;
                                            i7 = length;
                                            b((n0) objE, z2);
                                        }
                                    }
                                    i8 = 8;
                                }
                                jArr7 = jArr8;
                                j12 = j13;
                                i7 = length;
                                i8 = 8;
                            } else {
                                jArr7 = jArr8;
                                c6 = c7;
                                j12 = j13;
                                i7 = length;
                                i8 = i9;
                            }
                            j13 = j12 >> i8;
                            i12++;
                            length = i7;
                            i9 = i8;
                            c7 = c6;
                            jArr8 = jArr7;
                            obj = null;
                        }
                        jArr6 = jArr8;
                        c5 = c7;
                        int i17 = length;
                        if (i11 != i9) {
                            break;
                        } else {
                            length = i17;
                        }
                    } else {
                        jArr6 = jArr8;
                        c5 = 7;
                    }
                    if (i10 == length) {
                        break;
                    }
                    i10++;
                    jArr8 = jArr6;
                    obj = null;
                    i9 = 8;
                }
            } else {
                j5 = 128;
                j6 = 255;
                j7 = -9187201950435737472L;
                c5 = 7;
            }
        } else {
            j5 = 128;
            j6 = 255;
            j7 = -9187201950435737472L;
            c5 = 7;
            for (Object obj3 : set) {
                if (obj3 instanceof b2) {
                    ((b2) obj3).c(null);
                } else {
                    b(obj3, z2);
                    Object objE2 = zVar.e(obj3);
                    if (objE2 != null) {
                        if (objE2 instanceof j.a0) {
                            j.a0 a0Var3 = (j.a0) objE2;
                            Object[] objArr3 = a0Var3.f3067b;
                            long[] jArr11 = a0Var3.f3066a;
                            int length3 = jArr11.length - 2;
                            if (length3 >= 0) {
                                int i18 = 0;
                                while (true) {
                                    long j15 = jArr11[i18];
                                    if ((((~j15) << 7) & j15 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i18 != length3) {
                                            break;
                                            break;
                                        }
                                        i18++;
                                    } else {
                                        int i19 = 8 - ((~(i18 - length3)) >>> 31);
                                        for (int i20 = 0; i20 < i19; i20++) {
                                            if ((j15 & 255) < 128) {
                                                b((n0) objArr3[(i18 << 3) + i20], z2);
                                            }
                                            j15 >>= 8;
                                        }
                                        if (i19 != 8) {
                                            break;
                                        } else if (i18 != length3) {
                                            break;
                                        } else {
                                            i18++;
                                        }
                                    }
                                }
                            }
                        } else {
                            b((n0) objE2, z2);
                        }
                    }
                }
            }
        }
        j.z zVar2 = this.f2136j;
        j.a0 a0Var4 = this.f2137k;
        if (z2) {
            j.a0 a0Var5 = this.f2138l;
            if (a0Var5.h()) {
                long[] jArr12 = zVar2.f3176a;
                int length4 = jArr12.length - 2;
                if (length4 >= 0) {
                    int i21 = 0;
                    while (true) {
                        long j16 = jArr12[i21];
                        if ((((~j16) << c5) & j16 & j7) != j7) {
                            int i22 = 8 - ((~(i21 - length4)) >>> 31);
                            int i23 = 0;
                            while (i23 < i22) {
                                if ((j16 & j6) < j5) {
                                    int i24 = (i21 << 3) + i23;
                                    Object obj4 = zVar2.f3177b[i24];
                                    Object obj5 = zVar2.f3178c[i24];
                                    if (obj5 instanceof j.a0) {
                                        j.a0 a0Var6 = (j.a0) obj5;
                                        Object[] objArr4 = a0Var6.f3067b;
                                        long[] jArr13 = a0Var6.f3066a;
                                        int length5 = jArr13.length - 2;
                                        if (length5 >= 0) {
                                            j10 = j16;
                                            int i25 = 0;
                                            while (true) {
                                                long j17 = jArr13[i25];
                                                jArr5 = jArr12;
                                                i5 = length4;
                                                if ((((~j17) << c5) & j17 & j7) != j7) {
                                                    int i26 = 8 - ((~(i25 - length5)) >>> 31);
                                                    for (int i27 = 0; i27 < i26; i27 = i6 + 1) {
                                                        if ((j17 & j6) < j5) {
                                                            i6 = i27;
                                                            int i28 = (i25 << 3) + i6;
                                                            j11 = j17;
                                                            b2 b2Var = (b2) objArr4[i28];
                                                            if (a0Var5.c(b2Var) || a0Var4.c(b2Var)) {
                                                                a0Var6.k(i28);
                                                            }
                                                        } else {
                                                            i6 = i27;
                                                            j11 = j17;
                                                        }
                                                        j17 = j11 >> 8;
                                                    }
                                                    if (i26 != 8) {
                                                        break;
                                                    }
                                                    if (i25 != length5) {
                                                        break;
                                                    }
                                                    i25++;
                                                    length4 = i5;
                                                    jArr12 = jArr5;
                                                } else if (i25 != length5) {
                                                    break;
                                                    break;
                                                } else {
                                                    i25++;
                                                    length4 = i5;
                                                    jArr12 = jArr5;
                                                }
                                            }
                                        } else {
                                            jArr5 = jArr12;
                                            i5 = length4;
                                            j10 = j16;
                                        }
                                        zG = a0Var6.g();
                                    } else {
                                        jArr5 = jArr12;
                                        i5 = length4;
                                        j10 = j16;
                                        b4.l.c("null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap", obj5);
                                        b2 b2Var2 = (b2) obj5;
                                        zG = a0Var5.c(b2Var2) || a0Var4.c(b2Var2);
                                    }
                                    if (zG) {
                                        zVar2.h(i24);
                                    }
                                } else {
                                    jArr5 = jArr12;
                                    i5 = length4;
                                    j10 = j16;
                                }
                                j16 = j10 >> 8;
                                i23++;
                                length4 = i5;
                                jArr12 = jArr5;
                            }
                            jArr4 = jArr12;
                            int i29 = length4;
                            if (i22 != 8) {
                                break;
                            } else {
                                length4 = i29;
                            }
                        } else {
                            jArr4 = jArr12;
                        }
                        if (i21 == length4) {
                            break;
                        }
                        i21++;
                        jArr12 = jArr4;
                    }
                }
                a0Var5.b();
                h();
                return;
            }
        }
        if (a0Var4.h()) {
            long[] jArr14 = zVar2.f3176a;
            int length6 = jArr14.length - 2;
            if (length6 >= 0) {
                int i30 = 0;
                while (true) {
                    long j18 = jArr14[i30];
                    if ((((~j18) << c5) & j18 & j7) != j7) {
                        int i31 = 8 - ((~(i30 - length6)) >>> 31);
                        int i32 = 0;
                        while (i32 < i31) {
                            if ((j18 & j6) < j5) {
                                int i33 = (i30 << 3) + i32;
                                Object obj6 = zVar2.f3177b[i33];
                                Object obj7 = zVar2.f3178c[i33];
                                if (obj7 instanceof j.a0) {
                                    j.a0 a0Var7 = (j.a0) obj7;
                                    Object[] objArr5 = a0Var7.f3067b;
                                    long[] jArr15 = a0Var7.f3066a;
                                    int length7 = jArr15.length - 2;
                                    if (length7 >= 0) {
                                        j8 = j18;
                                        int i34 = 0;
                                        while (true) {
                                            long j19 = jArr15[i34];
                                            Object[] objArr6 = objArr5;
                                            long[] jArr16 = jArr15;
                                            if ((((~j19) << c5) & j19 & j7) != j7) {
                                                int i35 = 8 - ((~(i34 - length7)) >>> 31);
                                                int i36 = 0;
                                                while (i36 < i35) {
                                                    if ((j19 & j6) < j5) {
                                                        jArr3 = jArr14;
                                                        int i37 = (i34 << 3) + i36;
                                                        j9 = j19;
                                                        if (a0Var4.c((b2) objArr6[i37])) {
                                                            a0Var7.k(i37);
                                                        }
                                                    } else {
                                                        jArr3 = jArr14;
                                                        j9 = j19;
                                                    }
                                                    i36++;
                                                    jArr14 = jArr3;
                                                    j19 = j9 >> 8;
                                                }
                                                jArr2 = jArr14;
                                                if (i35 != 8) {
                                                    break;
                                                }
                                            } else {
                                                jArr2 = jArr14;
                                            }
                                            if (i34 == length7) {
                                                break;
                                            }
                                            i34++;
                                            objArr5 = objArr6;
                                            jArr15 = jArr16;
                                            jArr14 = jArr2;
                                        }
                                    } else {
                                        jArr2 = jArr14;
                                        j8 = j18;
                                    }
                                    zC = a0Var7.g();
                                } else {
                                    jArr2 = jArr14;
                                    j8 = j18;
                                    b4.l.c("null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap", obj7);
                                    zC = a0Var4.c((b2) obj7);
                                }
                                if (zC) {
                                    zVar2.h(i33);
                                }
                            } else {
                                jArr2 = jArr14;
                                j8 = j18;
                            }
                            i32++;
                            j18 = j8 >> 8;
                            jArr14 = jArr2;
                        }
                        jArr = jArr14;
                        if (i31 != 8) {
                            break;
                        }
                    } else {
                        jArr = jArr14;
                    }
                    if (i30 == length6) {
                        break;
                    }
                    i30++;
                    jArr14 = jArr;
                }
            }
            h();
            a0Var4.b();
        }
    }

    public final void d() {
        synchronized (this.f2133g) {
            try {
                e(this.f2140n);
                n();
            } catch (Throwable th) {
                try {
                    if (!this.f2134h.f3089d.g()) {
                        n0.j jVar = this.f2148v;
                        try {
                            jVar.e(this.f2134h, this.f2149w.y());
                            jVar.b();
                        } finally {
                            jVar.a();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    a();
                    throw th2;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:156:0x0139 A[EDGE_INSN: B:156:0x0139->B:74:0x0139 BREAK  A[LOOP:3: B:141:0x00ee->B:72:0x012e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x012c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x012e A[Catch: all -> 0x011e, LOOP:3: B:141:0x00ee->B:72:0x012e, LOOP_END, TryCatch #7 {all -> 0x011e, blocks: (B:57:0x00ee, B:59:0x00fc, B:61:0x0106, B:63:0x010c, B:65:0x011a, B:68:0x0121, B:74:0x0139, B:82:0x015d, B:85:0x0170, B:72:0x012e, B:77:0x0143, B:91:0x0190, B:93:0x019e), top: B:141:0x00ee }] */
    public final void e(g0.a aVar) throws Throwable {
        n0.j jVar;
        long[] jArr;
        int i5;
        long[] jArr2;
        n0.j jVar2;
        long j5;
        char c5;
        long j6;
        int i6;
        boolean zG;
        g0.a aVar2 = this.f2141o;
        v vVar = this.f2149w;
        q0.d dVarY = vVar.y();
        n0.j jVar3 = this.f2148v;
        jVar3.e(this.f2134h, dVarY);
        try {
            if (aVar.f2454k.Q()) {
                try {
                    if (aVar2.f2454k.Q()) {
                        jVar3.b();
                    }
                    return;
                } finally {
                    jVar3.a();
                }
            }
            a1.b bVar = this.f2131e;
            try {
                Trace.beginSection(bVar.equals(null) ? "Compose:recordChanges" : "Compose:applyChanges");
                try {
                    w2 w2VarD = this.f2135i.d();
                    int i7 = 0;
                    try {
                        aVar.O(bVar, w2VarD, jVar3, vVar.y());
                        w2VarD.e(true);
                        bVar.x();
                        Trace.endSection();
                        jVar3.c();
                        h0.e eVar = jVar3.f4078g;
                        if (eVar.f2578f != 0) {
                            Trace.beginSection("Compose:sideeffects");
                            try {
                                Object[] objArr = eVar.f2576d;
                                int i8 = eVar.f2578f;
                                for (int i9 = 0; i9 < i8; i9++) {
                                    ((a4.a) objArr[i9]).a();
                                }
                                eVar.g();
                                Trace.endSection();
                            } catch (Throwable th) {
                                Trace.endSection();
                                throw th;
                            }
                        }
                        if (this.f2144r) {
                            Trace.beginSection("Compose:unobserve");
                            try {
                                this.f2144r = false;
                                j.z zVar = this.f2136j;
                                long[] jArr3 = zVar.f3176a;
                                int length = jArr3.length - 2;
                                if (length >= 0) {
                                    int i10 = 0;
                                    while (true) {
                                        long j7 = jArr3[i10];
                                        char c6 = 7;
                                        long j8 = -9187201950435737472L;
                                        if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i11 = 8;
                                            int i12 = 8 - ((~(i10 - length)) >>> 31);
                                            int i13 = i7;
                                            while (i13 < i12) {
                                                if ((j7 & 255) < 128) {
                                                    c5 = c6;
                                                    int i14 = (i10 << 3) + i13;
                                                    j6 = j8;
                                                    Object obj = zVar.f3177b[i14];
                                                    Object obj2 = zVar.f3178c[i14];
                                                    if (obj2 instanceof j.a0) {
                                                        j.a0 a0Var = (j.a0) obj2;
                                                        Object[] objArr2 = a0Var.f3067b;
                                                        long[] jArr4 = a0Var.f3066a;
                                                        int i15 = i11;
                                                        int length2 = jArr4.length - 2;
                                                        i5 = i13;
                                                        jArr2 = jArr3;
                                                        jVar2 = jVar3;
                                                        if (length2 >= 0) {
                                                            int i16 = 0;
                                                            while (true) {
                                                                try {
                                                                    long j9 = jArr4[i16];
                                                                    j5 = j7;
                                                                    if ((((~j9) << c5) & j9 & j6) != j6) {
                                                                        int i17 = 8 - ((~(i16 - length2)) >>> 31);
                                                                        for (int i18 = 0; i18 < i17; i18++) {
                                                                            if ((j9 & 255) < 128) {
                                                                                int i19 = (i16 << 3) + i18;
                                                                                if (!((b2) objArr2[i19]).b()) {
                                                                                    a0Var.k(i19);
                                                                                }
                                                                            }
                                                                            j9 >>= i15;
                                                                        }
                                                                        if (i17 != i15) {
                                                                            break;
                                                                        }
                                                                        if (i16 != length2) {
                                                                            break;
                                                                        }
                                                                        i16++;
                                                                        i15 = 8;
                                                                        j7 = j5;
                                                                    } else if (i16 != length2) {
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        i16++;
                                                                        i15 = 8;
                                                                        j7 = j5;
                                                                    }
                                                                } catch (Throwable th2) {
                                                                    th = th2;
                                                                    Trace.endSection();
                                                                    throw th;
                                                                }
                                                            }
                                                        } else {
                                                            j5 = j7;
                                                        }
                                                        zG = a0Var.g();
                                                    } else {
                                                        i5 = i13;
                                                        jArr2 = jArr3;
                                                        jVar2 = jVar3;
                                                        j5 = j7;
                                                        b4.l.c("null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap", obj2);
                                                        zG = !((b2) obj2).b();
                                                    }
                                                    if (zG) {
                                                        zVar.h(i14);
                                                    }
                                                    i6 = 8;
                                                } else {
                                                    i5 = i13;
                                                    jArr2 = jArr3;
                                                    jVar2 = jVar3;
                                                    j5 = j7;
                                                    c5 = c6;
                                                    j6 = j8;
                                                    i6 = i11;
                                                }
                                                j7 = j5 >> i6;
                                                i13 = i5 + 1;
                                                i11 = i6;
                                                c6 = c5;
                                                j8 = j6;
                                                jVar3 = jVar2;
                                                jArr3 = jArr2;
                                            }
                                            jArr = jArr3;
                                            jVar = jVar3;
                                            if (i12 != i11) {
                                                break;
                                            }
                                        } else {
                                            jArr = jArr3;
                                            jVar = jVar3;
                                        }
                                        if (i10 == length) {
                                            break;
                                        }
                                        i10++;
                                        jVar3 = jVar;
                                        jArr3 = jArr;
                                        i7 = 0;
                                    }
                                } else {
                                    jVar = jVar3;
                                }
                                h();
                                Trace.endSection();
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } else {
                            jVar = jVar3;
                        }
                        try {
                            if (aVar2.f2454k.Q()) {
                                jVar.b();
                            }
                            return;
                        } finally {
                            jVar.a();
                        }
                    } catch (Throwable th4) {
                        try {
                            w2VarD.e(false);
                            throw th4;
                        } catch (Throwable th5) {
                            th = th5;
                            Trace.endSection();
                            throw th;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Throwable th8) {
            th = th8;
        }
        try {
            if (aVar2.f2454k.Q()) {
                jVar3.b();
            }
            throw th;
        } finally {
            jVar3.a();
        }
    }

    public final void f() {
        synchronized (this.f2133g) {
            try {
                if (this.f2141o.f2454k.R()) {
                    e(this.f2141o);
                }
            } catch (Throwable th) {
                try {
                    if (!this.f2134h.f3089d.g()) {
                        n0.j jVar = this.f2148v;
                        try {
                            jVar.e(this.f2134h, this.f2149w.y());
                            jVar.b();
                        } finally {
                            jVar.a();
                        }
                    }
                    throw th;
                } catch (Throwable th2) {
                    a();
                    throw th2;
                }
            }
        }
    }

    public final void g() {
        synchronized (this.f2133g) {
            try {
                this.f2149w.f2375v = null;
                if (!this.f2134h.f3089d.g()) {
                    n0.j jVar = this.f2148v;
                    try {
                        jVar.e(this.f2134h, this.f2149w.y());
                        jVar.b();
                        jVar.a();
                    } catch (Throwable th) {
                        jVar.a();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                try {
                    if (!this.f2134h.f3089d.g()) {
                        n0.j jVar2 = this.f2148v;
                        try {
                            jVar2.e(this.f2134h, this.f2149w.y());
                            jVar2.b();
                        } finally {
                            jVar2.a();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    a();
                    throw th3;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x00a1 A[LOOP:2: B:16:0x005a->B:30:0x00a1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x00b0 A[EDGE_INSN: B:83:0x00b0->B:32:0x00b0 BREAK  A[LOOP:2: B:16:0x005a->B:30:0x00a1], SYNTHETIC] */
    public final void h() {
        char c5;
        long j5;
        long j6;
        long j7;
        long[] jArr;
        long[] jArr2;
        int i5;
        long j8;
        char c6;
        long j9;
        long j10;
        int i6;
        boolean zG;
        int i7;
        long j11;
        j.z zVar = this.f2139m;
        long[] jArr3 = zVar.f3176a;
        int length = jArr3.length - 2;
        char c7 = 7;
        long j12 = -9187201950435737472L;
        int i8 = 8;
        if (length >= 0) {
            int i9 = 0;
            long j13 = 128;
            while (true) {
                long j14 = jArr3[i9];
                j6 = 255;
                if ((((~j14) << c7) & j14 & j12) != j12) {
                    int i10 = 8 - ((~(i9 - length)) >>> 31);
                    int i11 = 0;
                    while (i11 < i10) {
                        if ((j14 & 255) < j13) {
                            c6 = c7;
                            int i12 = (i9 << 3) + i11;
                            j9 = j12;
                            Object obj = zVar.f3177b[i12];
                            Object obj2 = zVar.f3178c[i12];
                            boolean z2 = obj2 instanceof j.a0;
                            j.z zVar2 = this.f2136j;
                            if (z2) {
                                j.a0 a0Var = (j.a0) obj2;
                                Object[] objArr = a0Var.f3067b;
                                long[] jArr4 = a0Var.f3066a;
                                j10 = j13;
                                int length2 = jArr4.length - 2;
                                if (length2 >= 0) {
                                    j8 = j14;
                                    int i13 = i8;
                                    int i14 = 0;
                                    while (true) {
                                        long j15 = jArr4[i14];
                                        jArr2 = jArr3;
                                        i5 = length;
                                        if ((((~j15) << c6) & j15 & j9) == j9) {
                                            if (i14 != length2) {
                                                break;
                                                break;
                                            }
                                            i14++;
                                            jArr3 = jArr2;
                                            length = i5;
                                            i13 = 8;
                                        } else {
                                            int i15 = 8 - ((~(i14 - length2)) >>> 31);
                                            int i16 = 0;
                                            while (i16 < i15) {
                                                if ((j15 & 255) < j10) {
                                                    i7 = i16;
                                                    int i17 = (i14 << 3) + i7;
                                                    j11 = j15;
                                                    if (!zVar2.b((n0) objArr[i17])) {
                                                        a0Var.k(i17);
                                                    }
                                                } else {
                                                    i7 = i16;
                                                    j11 = j15;
                                                }
                                                j15 = j11 >> i13;
                                                i16 = i7 + 1;
                                            }
                                            if (i15 != i13) {
                                                break;
                                            }
                                            if (i14 != length2) {
                                                break;
                                            }
                                            i14++;
                                            jArr3 = jArr2;
                                            length = i5;
                                            i13 = 8;
                                        }
                                    }
                                } else {
                                    jArr2 = jArr3;
                                    i5 = length;
                                    j8 = j14;
                                }
                                zG = a0Var.g();
                            } else {
                                jArr2 = jArr3;
                                i5 = length;
                                j8 = j14;
                                j10 = j13;
                                b4.l.c("null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap", obj2);
                                zG = !zVar2.b((n0) obj2);
                            }
                            if (zG) {
                                zVar.h(i12);
                            }
                            i6 = 8;
                        } else {
                            jArr2 = jArr3;
                            i5 = length;
                            j8 = j14;
                            c6 = c7;
                            j9 = j12;
                            j10 = j13;
                            i6 = i8;
                        }
                        j14 = j8 >> i6;
                        i11++;
                        i8 = i6;
                        c7 = c6;
                        j12 = j9;
                        j13 = j10;
                        jArr3 = jArr2;
                        length = i5;
                    }
                    jArr = jArr3;
                    int i18 = length;
                    c5 = c7;
                    j5 = j12;
                    j7 = j13;
                    if (i10 != i8) {
                        break;
                    } else {
                        length = i18;
                    }
                } else {
                    jArr = jArr3;
                    c5 = c7;
                    j5 = j12;
                    j7 = j13;
                }
                if (i9 == length) {
                    break;
                }
                i9++;
                c7 = c5;
                j12 = j5;
                j13 = j7;
                jArr3 = jArr;
                i8 = 8;
            }
        } else {
            c5 = 7;
            j5 = -9187201950435737472L;
            j6 = 255;
            j7 = 128;
        }
        j.a0 a0Var2 = this.f2138l;
        if (!a0Var2.h()) {
            return;
        }
        Object[] objArr2 = a0Var2.f3067b;
        long[] jArr5 = a0Var2.f3066a;
        int length3 = jArr5.length - 2;
        if (length3 < 0) {
            return;
        }
        int i19 = 0;
        while (true) {
            long j16 = jArr5[i19];
            if ((((~j16) << c5) & j16 & j5) != j5) {
                int i20 = 8 - ((~(i19 - length3)) >>> 31);
                for (int i21 = 0; i21 < i20; i21++) {
                    if ((j16 & j6) < j7) {
                        int i22 = (i19 << 3) + i21;
                        if (!(((b2) objArr2[i22]).f2101g != null)) {
                            a0Var2.k(i22);
                        }
                    }
                    j16 >>= 8;
                }
                if (i20 != 8) {
                    return;
                }
            }
            if (i19 == length3) {
                return;
            } else {
                i19++;
            }
        }
    }

    public final void i(n0.d dVar) {
        try {
            synchronized (this.f2133g) {
                m();
                j.z zVar = this.f2143q;
                this.f2143q = d4.a.o();
                try {
                    v vVar = this.f2149w;
                    if (!vVar.f2358e.f2454k.Q()) {
                        z.a("Expected applyChanges() to have been called");
                    }
                    vVar.n(zVar, dVar);
                } catch (Throwable th) {
                    this.f2143q = zVar;
                    throw th;
                }
            }
        } catch (Throwable th2) {
            try {
                if (!this.f2134h.f3089d.g()) {
                    n0.j jVar = this.f2148v;
                    try {
                        jVar.e(this.f2134h, this.f2149w.y());
                        jVar.b();
                    } finally {
                        jVar.a();
                    }
                }
                throw th2;
            } catch (Throwable th3) {
                a();
                throw th3;
            }
        }
    }

    public final void j(n0.d dVar) {
        v vVar = this.f2149w;
        vVar.f2379z = 100;
        vVar.f2378y = true;
        this.f2130d.a(this, dVar);
        if (vVar.F || vVar.f2379z != 100) {
            x1.a("Cannot disable reuse from root if it was caused by other groups");
        }
        vVar.f2379z = -1;
        vVar.f2378y = false;
    }

    public final void k() {
        synchronized (this.f2133g) {
            try {
                boolean z2 = this.f2135i.f2334e > 0;
                if (z2 || !this.f2134h.f3089d.g()) {
                    Trace.beginSection("Compose:deactivate");
                    try {
                        n0.j jVar = this.f2148v;
                        try {
                            jVar.e(this.f2134h, this.f2149w.y());
                            if (z2) {
                                w2 w2VarD = this.f2135i.d();
                                try {
                                    w2VarD.n(w2VarD.f2409t, new x(this.f2148v, w2VarD));
                                    w2VarD.e(true);
                                    this.f2131e.x();
                                    jVar.c();
                                } catch (Throwable th) {
                                    w2VarD.e(false);
                                    throw th;
                                }
                            }
                            jVar.b();
                            jVar.a();
                            Trace.endSection();
                        } catch (Throwable th2) {
                            jVar.a();
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        Trace.endSection();
                        throw th3;
                    }
                }
                this.f2136j.a();
                this.f2139m.a();
                this.f2143q.a();
                this.f2140n.f2454k.O();
                this.f2141o.f2454k.O();
                v vVar = this.f2149w;
                vVar.E.clear();
                vVar.f2372s.clear();
                vVar.f2358e.f2454k.O();
                vVar.f2375v = null;
                this.f2150x = 1;
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    public final void l() {
        synchronized (this.f2133g) {
            try {
                if (this.f2149w.F) {
                    x1.b("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                }
                if (this.f2150x != 3) {
                    this.f2150x = 3;
                    g0.a aVar = this.f2149w.L;
                    if (aVar != null) {
                        e(aVar);
                    }
                    boolean z2 = this.f2135i.f2334e > 0;
                    if (z2 || !this.f2134h.f3089d.g()) {
                        n0.j jVar = this.f2148v;
                        try {
                            jVar.e(this.f2134h, this.f2149w.y());
                            if (z2) {
                                w2 w2VarD = this.f2135i.d();
                                try {
                                    w2VarD.n(w2VarD.f2409t, new y(0, this.f2148v));
                                    w2VarD.H();
                                    w2VarD.e(true);
                                    this.f2131e.f();
                                    this.f2131e.x();
                                    jVar.c();
                                } catch (Throwable th) {
                                    w2VarD.e(false);
                                    throw th;
                                }
                            }
                            jVar.b();
                            jVar.a();
                        } catch (Throwable th2) {
                            jVar.a();
                            throw th2;
                        }
                    }
                    v vVar = this.f2149w;
                    vVar.getClass();
                    Trace.beginSection("Compose:Composer.dispose");
                    try {
                        vVar.f2355b.q(vVar);
                        vVar.E.clear();
                        vVar.f2372s.clear();
                        vVar.f2358e.f2454k.O();
                        vVar.f2375v = null;
                        vVar.f2354a.f();
                        Trace.endSection();
                    } catch (Throwable th3) {
                        Trace.endSection();
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        this.f2130d.r(this);
    }

    public final void m() {
        Object obj = l.f2232d;
        AtomicReference atomicReference = this.f2132f;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet != null) {
            if (andSet.equals(obj)) {
                z.b("pending composition has not been applied");
                throw new i4.p();
            }
            if (andSet instanceof Set) {
                c((Set) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                z.b("corrupt pendingModifications drain: " + atomicReference);
                throw new i4.p();
            }
            for (Set set : (Set[]) andSet) {
                c(set, true);
            }
        }
    }

    public final void n() {
        AtomicReference atomicReference = this.f2132f;
        Object andSet = atomicReference.getAndSet(null);
        if (b4.l.a(andSet, l.f2232d)) {
            return;
        }
        if (andSet instanceof Set) {
            c((Set) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set set : (Set[]) andSet) {
                c(set, false);
            }
            return;
        }
        if (andSet == null) {
            z.a("calling recordModificationsOf and applyChanges concurrently is not supported");
            return;
        }
        z.b("corrupt pendingModifications drain: " + atomicReference);
        throw new i4.p();
    }

    public final void o() {
        q3.u uVar = q3.u.f5479d;
        AtomicReference atomicReference = this.f2132f;
        Object andSet = atomicReference.getAndSet(uVar);
        if (b4.l.a(andSet, l.f2232d) || andSet == null) {
            return;
        }
        if (andSet instanceof Set) {
            c((Set) andSet, false);
            return;
        }
        if (!(andSet instanceof Object[])) {
            z.b("corrupt pendingModifications drain: " + atomicReference);
            throw new i4.p();
        }
        for (Set set : (Set[]) andSet) {
            c(set, false);
        }
    }

    public final void p() {
        String str;
        int i5 = this.f2150x;
        if (i5 == 0) {
            return;
        }
        if (i5 == 1) {
            str = "The composition should be activated before setting content.";
        } else if (i5 != 2) {
            str = i5 != 3 ? "" : "The composition is disposed";
        } else {
            str = "A previous pausable composition for this composition was cancelled. This composition must be disposed.";
        }
        x1.b(str);
    }

    public final void q(ArrayList arrayList) {
        j.c0 c0Var = this.f2134h;
        v vVar = this.f2149w;
        if (arrayList.size() > 0) {
            ((g1) ((p3.f) arrayList.get(0)).f5014d).getClass();
            throw null;
        }
        try {
            vVar.getClass();
            try {
                vVar.A(arrayList);
                vVar.i();
            } catch (Throwable th) {
                vVar.a();
                throw th;
            }
        } catch (Throwable th2) {
            try {
                if (!c0Var.f3089d.g()) {
                    n0.j jVar = this.f2148v;
                    try {
                        jVar.e(c0Var, vVar.y());
                        jVar.b();
                    } finally {
                        jVar.a();
                    }
                }
                throw th2;
            } catch (Throwable th3) {
                a();
                throw th3;
            }
        }
    }

    public final y0 r(b2 b2Var, Object obj) {
        e0 e0Var;
        int i5 = b2Var.f2096b;
        if ((i5 & 2) != 0) {
            b2Var.f2096b = i5 | 4;
        }
        a aVar = b2Var.f2097c;
        if (aVar == null || !aVar.a()) {
            return y0.f2422d;
        }
        if (this.f2135i.e(aVar)) {
            if (b2Var.f2098d == null) {
                return y0.f2422d;
            }
            y0 y0VarT = t(b2Var, aVar, obj);
            if (y0VarT != y0.f2422d) {
                this.f2147u.j();
            }
            return y0VarT;
        }
        synchronized (this.f2133g) {
            e0Var = this.f2145s;
        }
        if (e0Var != null) {
            v vVar = e0Var.f2149w;
            if (vVar.F && vVar.a0(b2Var, obj)) {
                return y0.f2425g;
            }
        }
        return y0.f2422d;
    }

    public final void s() {
        e0 e0Var;
        synchronized (this.f2133g) {
            try {
                for (Object obj : this.f2135i.f2335f) {
                    b2 b2Var = obj instanceof b2 ? (b2) obj : null;
                    if (b2Var != null && (e0Var = b2Var.f2095a) != null) {
                        e0Var.r(b2Var, null);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00cb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x00cd A[Catch: all -> 0x0042, LOOP:0: B:47:0x008c->B:61:0x00cd, LOOP_END, TryCatch #0 {all -> 0x0042, blocks: (B:4:0x000b, B:6:0x0010, B:8:0x0018, B:10:0x001f, B:14:0x0029, B:16:0x002f, B:13:0x0024, B:25:0x0047, B:27:0x004d, B:32:0x0058, B:36:0x005e, B:37:0x0067, B:39:0x006b, B:40:0x0074, B:42:0x007c, B:44:0x0080, B:47:0x008c, B:49:0x009c, B:51:0x00a8, B:53:0x00b2, B:57:0x00c1, B:61:0x00cd, B:62:0x00d0, B:65:0x00d5), top: B:78:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:65:0x00d5 A[Catch: all -> 0x0042, EDGE_INSN: B:65:0x00d5->B:66:0x00da BREAK  A[LOOP:0: B:47:0x008c->B:61:0x00cd], TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:4:0x000b, B:6:0x0010, B:8:0x0018, B:10:0x001f, B:14:0x0029, B:16:0x002f, B:13:0x0024, B:25:0x0047, B:27:0x004d, B:32:0x0058, B:36:0x005e, B:37:0x0067, B:39:0x006b, B:40:0x0074, B:42:0x007c, B:44:0x0080, B:47:0x008c, B:49:0x009c, B:51:0x00a8, B:53:0x00b2, B:57:0x00c1, B:61:0x00cd, B:62:0x00d0, B:65:0x00d5), top: B:78:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:81:0x00d5 A[SYNTHETIC] */
    public final y0 t(b2 b2Var, a aVar, Object obj) {
        synchronized (this.f2133g) {
            try {
                e0 e0Var = this.f2145s;
                e0 e0Var2 = null;
                if (e0Var != null) {
                    t2 t2Var = this.f2135i;
                    int i5 = this.f2146t;
                    if (t2Var.f2339j) {
                        z.a("Writer is active");
                    }
                    if (i5 < 0 || i5 >= t2Var.f2334e) {
                        z.a("Invalid group index");
                    }
                    if (t2Var.e(aVar)) {
                        int i6 = t2Var.f2333d[(i5 * 5) + 3] + i5;
                        int i7 = aVar.f2082a;
                        if (i5 > i7 || i7 >= i6) {
                            e0Var = null;
                        }
                    } else {
                        e0Var = null;
                    }
                    e0Var2 = e0Var;
                }
                if (e0Var2 == null) {
                    v vVar = this.f2149w;
                    if (vVar.F && vVar.a0(b2Var, obj)) {
                        return y0.f2425g;
                    }
                    if (obj != null && (obj instanceof n0)) {
                        Object objE = this.f2143q.e(b2Var);
                        if (objE != null) {
                            if (!(objE instanceof j.a0)) {
                                if (objE != h.f2188i) {
                                    d4.a.e(this.f2143q, b2Var, obj);
                                    break;
                                }
                            } else {
                                j.a0 a0Var = (j.a0) objE;
                                Object[] objArr = a0Var.f3067b;
                                long[] jArr = a0Var.f3066a;
                                int length = jArr.length - 2;
                                if (length < 0) {
                                    d4.a.e(this.f2143q, b2Var, obj);
                                    break;
                                }
                                int i8 = 0;
                                loop0: while (true) {
                                    long j5 = jArr[i8];
                                    if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i8 == length) {
                                            d4.a.e(this.f2143q, b2Var, obj);
                                            break;
                                        }
                                        i8++;
                                    } else {
                                        int i9 = 8;
                                        int i10 = 8 - ((~(i8 - length)) >>> 31);
                                        int i11 = 0;
                                        while (i11 < i10) {
                                            if ((j5 & 255) < 128 && objArr[(i8 << 3) + i11] == h.f2188i) {
                                                break loop0;
                                            }
                                            j5 >>= i9;
                                            i11++;
                                            i9 = i9;
                                        }
                                        if (i10 == i9) {
                                            if (i8 == length) {
                                                i8++;
                                            }
                                        }
                                        d4.a.e(this.f2143q, b2Var, obj);
                                        break;
                                    }
                                }
                            }
                        } else {
                            d4.a.e(this.f2143q, b2Var, obj);
                            break;
                        }
                    } else {
                        this.f2143q.i(b2Var, h.f2188i);
                    }
                }
                if (e0Var2 != null) {
                    return e0Var2.t(b2Var, aVar, obj);
                }
                this.f2130d.k(this);
                return this.f2149w.F ? y0.f2424f : y0.f2423e;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void u(Object obj) {
        Object objE = this.f2136j.e(obj);
        if (objE == null) {
            return;
        }
        boolean z2 = objE instanceof j.a0;
        j.z zVar = this.f2142p;
        if (!z2) {
            b2 b2Var = (b2) objE;
            if (b2Var.c(obj) == y0.f2425g) {
                d4.a.e(zVar, obj, b2Var);
                return;
            }
            return;
        }
        j.a0 a0Var = (j.a0) objE;
        Object[] objArr = a0Var.f3067b;
        long[] jArr = a0Var.f3066a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i5 = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        b2 b2Var2 = (b2) objArr[(i5 << 3) + i7];
                        if (b2Var2.c(obj) == y0.f2425g) {
                            d4.a.e(zVar, obj, b2Var2);
                        }
                    }
                    j5 >>= 8;
                }
                if (i6 != 8) {
                    return;
                }
            }
            if (i5 == length) {
                return;
            } else {
                i5++;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0059 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x005b A[LOOP:0: B:7:0x001c->B:21:0x005b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x007b A[SYNTHETIC] */
    public final boolean v(Set set) {
        boolean z2 = set instanceof h0.h;
        j.z zVar = this.f2139m;
        j.z zVar2 = this.f2136j;
        if (z2) {
            j.a0 a0Var = ((h0.h) set).f2589d;
            Object[] objArr = a0Var.f3067b;
            long[] jArr = a0Var.f3066a;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i5 = 0;
                loop0: while (true) {
                    long j5 = jArr[i5];
                    if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i6 = 8 - ((~(i5 - length)) >>> 31);
                        for (int i7 = 0; i7 < i6; i7++) {
                            if ((255 & j5) < 128) {
                                Object obj = objArr[(i5 << 3) + i7];
                                if (zVar2.b(obj) || zVar.b(obj)) {
                                    break loop0;
                                }
                            }
                            j5 >>= 8;
                        }
                        if (i6 == 8) {
                            if (i5 != length) {
                                i5++;
                            }
                        }
                    } else if (i5 != length) {
                        i5++;
                    }
                }
                return true;
            }
        } else {
            for (Object obj2 : set) {
                if (zVar2.b(obj2) || zVar.b(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean w() {
        boolean zR;
        synchronized (this.f2133g) {
            m();
            try {
                j.z zVar = this.f2143q;
                this.f2143q = d4.a.o();
                try {
                    v vVar = this.f2149w;
                    g0.a aVar = vVar.f2358e;
                    if (!aVar.f2454k.Q()) {
                        z.a("Expected applyChanges() to have been called");
                    }
                    if (zVar.f3180e > 0 || !vVar.f2372s.isEmpty()) {
                        vVar.n(zVar, null);
                        zR = aVar.f2454k.R();
                    } else {
                        zR = false;
                    }
                    if (!zR) {
                        n();
                    }
                } catch (Throwable th) {
                    this.f2143q = zVar;
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    if (!this.f2134h.f3089d.g()) {
                        n0.j jVar = this.f2148v;
                        try {
                            jVar.e(this.f2134h, this.f2149w.y());
                            jVar.b();
                        } finally {
                            jVar.a();
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    a();
                    throw th3;
                }
            }
        }
        return zR;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void x(h0.h hVar) {
        Object obj;
        while (true) {
            Object obj2 = this.f2132f.get();
            if (obj2 == null || obj2.equals(l.f2232d)) {
                obj = hVar;
            } else if (obj2 instanceof Set) {
                obj = new Set[]{obj2, hVar};
            } else {
                if (!(obj2 instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.f2132f).toString());
                }
                Set[] setArr = (Set[]) obj2;
                int length = setArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(setArr, length + 1);
                objArrCopyOf[length] = hVar;
                obj = objArrCopyOf;
            }
            AtomicReference atomicReference = this.f2132f;
            do {
                if (atomicReference.compareAndSet(obj2, obj)) {
                    if (obj2 == null) {
                        synchronized (this.f2133g) {
                            n();
                        }
                        return;
                    }
                    return;
                }
            } while (atomicReference.get() == obj2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c  */
    public final void y(Object obj) {
        b2 b2VarW;
        int i5;
        boolean z2;
        boolean z3;
        boolean z4;
        v vVar = this.f2149w;
        if (vVar.A <= 0 && (b2VarW = vVar.w()) != null) {
            boolean z5 = true;
            int i6 = b2VarW.f2096b | 1;
            b2VarW.f2096b = i6;
            if ((i6 & 32) == 0) {
                j.x xVar = b2VarW.f2100f;
                if (xVar == null) {
                    xVar = new j.x();
                    b2VarW.f2100f = xVar;
                }
                int i7 = b2VarW.f2099e;
                int iB = xVar.b(obj);
                if (iB < 0) {
                    iB = ~iB;
                    i5 = -1;
                } else {
                    i5 = xVar.f3170c[iB];
                }
                xVar.f3169b[iB] = obj;
                xVar.f3170c[iB] = i7;
                if (i5 == b2VarW.f2099e) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                z2 = false;
            }
            this.f2147u.j();
            if (z2) {
                return;
            }
            if (obj instanceof p0.y) {
                ((p0.y) obj).f(1);
            }
            d4.a.e(this.f2136j, obj, b2VarW);
            if (obj instanceof n0) {
                n0 n0Var = (n0) obj;
                m0 m0VarH = n0Var.h();
                j.z zVar = this.f2139m;
                d4.a.G(zVar, obj);
                j.x xVar2 = m0VarH.f2270e;
                Object[] objArr = xVar2.f3169b;
                long[] jArr = xVar2.f3168a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i8 = 0;
                    while (true) {
                        long j5 = jArr[i8];
                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i9 = 8;
                            int i10 = 8 - ((~(i8 - length)) >>> 31);
                            int i11 = 0;
                            while (i11 < i10) {
                                if ((j5 & 255) < 128) {
                                    p0.x xVar3 = (p0.x) objArr[(i8 << 3) + i11];
                                    if (xVar3 instanceof p0.y) {
                                        z4 = true;
                                        ((p0.y) xVar3).f(1);
                                    } else {
                                        z4 = true;
                                    }
                                    d4.a.e(zVar, xVar3, obj);
                                } else {
                                    z4 = z5;
                                }
                                j5 >>= i9;
                                i11++;
                                z5 = z4;
                                i9 = i9;
                            }
                            z3 = z5;
                            if (i10 != i9) {
                                break;
                            }
                        } else {
                            z3 = z5;
                        }
                        if (i8 == length) {
                            break;
                        }
                        i8++;
                        z5 = z3;
                    }
                }
                Object obj2 = m0VarH.f2271f;
                j.z zVar2 = b2VarW.f2101g;
                if (zVar2 == null) {
                    zVar2 = new j.z();
                    b2VarW.f2101g = zVar2;
                }
                zVar2.i(n0Var, obj2);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0057 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0059 A[Catch: all -> 0x004f, LOOP:0: B:11:0x001f->B:23:0x0059, LOOP_END, TryCatch #0 {all -> 0x004f, blocks: (B:4:0x0003, B:6:0x000e, B:8:0x0012, B:11:0x001f, B:13:0x002f, B:15:0x003b, B:17:0x0044, B:20:0x0051, B:23:0x0059, B:24:0x005c), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0061 A[EDGE_INSN: B:31:0x0061->B:25:0x0061 BREAK  A[LOOP:0: B:11:0x001f->B:23:0x0059], SYNTHETIC] */
    public final void z(Object obj) {
        synchronized (this.f2133g) {
            try {
                u(obj);
                Object objE = this.f2139m.e(obj);
                if (objE != null) {
                    if (objE instanceof j.a0) {
                        j.a0 a0Var = (j.a0) objE;
                        Object[] objArr = a0Var.f3067b;
                        long[] jArr = a0Var.f3066a;
                        int length = jArr.length - 2;
                        if (length >= 0) {
                            int i5 = 0;
                            while (true) {
                                long j5 = jArr[i5];
                                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                                    if (i5 != length) {
                                        break;
                                        break;
                                    }
                                    i5++;
                                } else {
                                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                                    for (int i7 = 0; i7 < i6; i7++) {
                                        if ((255 & j5) < 128) {
                                            u((n0) objArr[(i5 << 3) + i7]);
                                        }
                                        j5 >>= 8;
                                    }
                                    if (i6 != 8) {
                                        break;
                                    } else if (i5 != length) {
                                        break;
                                    } else {
                                        i5++;
                                    }
                                }
                            }
                        }
                    } else {
                        u((n0) objE);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
