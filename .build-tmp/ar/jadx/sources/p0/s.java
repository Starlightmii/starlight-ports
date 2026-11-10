package p0;

import f0.b3;
import f0.m0;
import f0.n0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import p1.f1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a4.c f4732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4733b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j.x f4734c;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4741j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4742k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4735d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j.z f4736e = d4.a.o();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j.z f4737f = new j.z();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j.a0 f4738g = new j.a0();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h0.e f4739h = new h0.e(new n0[16]);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f0.u f4740i = new f0.u(1, this);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j.z f4743l = d4.a.o();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap f4744m = new HashMap();

    public s(a4.c cVar) {
        this.f4732a = cVar;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0219 A[DONT_INVERT, PHI: r23
      0x0219: PHI (r23v19 boolean) = (r23v18 boolean), (r23v20 boolean) binds: [B:92:0x01f1, B:100:0x0217] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:102:0x021b A[Catch: all -> 0x012d, LOOP:8: B:91:0x01e7->B:102:0x021b, LOOP_END, TryCatch #1 {all -> 0x012d, blocks: (B:27:0x0084, B:29:0x0088, B:32:0x009c, B:34:0x00ac, B:36:0x00b6, B:38:0x00bc, B:41:0x00d9, B:43:0x00e5, B:45:0x00eb, B:47:0x00ef, B:50:0x0101, B:52:0x010f, B:54:0x0119, B:56:0x011f, B:60:0x0135, B:70:0x016d, B:64:0x0140, B:66:0x014e, B:67:0x0158, B:76:0x0190, B:79:0x01ab, B:82:0x01c3, B:84:0x01cf, B:86:0x01d5, B:88:0x01d9, B:91:0x01e7, B:93:0x01f3, B:95:0x01ff, B:97:0x0205, B:98:0x020f, B:102:0x021b, B:103:0x021e, B:104:0x0224), top: B:324:0x0084 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x02a8 A[DONT_INVERT, PHI: r23
      0x02a8: PHI (r23v12 boolean) = (r23v11 boolean), (r23v13 boolean) binds: [B:123:0x0280, B:131:0x02a6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:133:0x02aa A[LOOP:6: B:122:0x0276->B:133:0x02aa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:203:0x0428  */
    /* JADX WARN: Code duplicated, block: B:246:0x04fd A[EDGE_INSN: B:246:0x04fd->B:247:0x04fe BREAK  A[LOOP:20: B:230:0x04bc->B:243:0x04f3], PHI: r2
      0x04fd: PHI (r2v10 boolean) = (r2v2 boolean), (r2v2 boolean), (r2v14 boolean) binds: [B:224:0x04a7, B:228:0x04b8, B:244:0x04f6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:250:0x0506  */
    /* JADX WARN: Code duplicated, block: B:274:0x056e A[DONT_INVERT, PHI: r0
      0x056e: PHI (r0v8 boolean) = (r0v7 boolean), (r0v9 boolean) binds: [B:265:0x0547, B:273:0x056c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:275:0x0570 A[LOOP:18: B:264:0x053d->B:275:0x0570, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:335:0x015f A[EDGE_INSN: B:335:0x015f->B:68:0x015f BREAK  A[LOOP:4: B:50:0x0101->B:64:0x0140], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:340:0x02b2 A[EDGE_INSN: B:340:0x02b2->B:135:0x02b2 BREAK  A[LOOP:6: B:122:0x0276->B:133:0x02aa], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:345:0x01a6 A[EDGE_INSN: B:345:0x01a6->B:78:0x01a6 BREAK  A[LOOP:8: B:91:0x01e7->B:102:0x021b], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:366:0x0577 A[EDGE_INSN: B:366:0x0577->B:277:0x0577 BREAK  A[LOOP:18: B:264:0x053d->B:275:0x0570], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x013e A[DONT_INVERT, PHI: r23
      0x013e: PHI (r23v29 boolean) = (r23v28 boolean), (r23v30 boolean) binds: [B:51:0x010d, B:62:0x013c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:64:0x0140 A[Catch: all -> 0x012d, LOOP:4: B:50:0x0101->B:64:0x0140, LOOP_END, TryCatch #1 {all -> 0x012d, blocks: (B:27:0x0084, B:29:0x0088, B:32:0x009c, B:34:0x00ac, B:36:0x00b6, B:38:0x00bc, B:41:0x00d9, B:43:0x00e5, B:45:0x00eb, B:47:0x00ef, B:50:0x0101, B:52:0x010f, B:54:0x0119, B:56:0x011f, B:60:0x0135, B:70:0x016d, B:64:0x0140, B:66:0x014e, B:67:0x0158, B:76:0x0190, B:79:0x01ab, B:82:0x01c3, B:84:0x01cf, B:86:0x01d5, B:88:0x01d9, B:91:0x01e7, B:93:0x01f3, B:95:0x01ff, B:97:0x0205, B:98:0x020f, B:102:0x021b, B:103:0x021e, B:104:0x0224), top: B:324:0x0084 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0149  */
    public final boolean a(Set set) throws Throwable {
        long j5;
        boolean z2;
        Iterator it;
        Object obj;
        String str;
        HashMap map;
        int i5;
        boolean z3;
        Iterator it2;
        Object obj2;
        String str2;
        j.z zVar;
        int i6;
        boolean z4;
        long[] jArr;
        int i7;
        int i8;
        Object[] objArr;
        int i9;
        j.x xVar;
        long[] jArr2;
        f0.h hVar;
        j.z zVar2;
        Object[] objArr2;
        int i10;
        long[] jArr3;
        f0.h hVar2;
        int i11;
        int i12;
        int i13;
        int i14;
        long j6;
        j.z zVar3;
        Object[] objArr3;
        boolean z5;
        j.z zVar4;
        int i15;
        int i16;
        int i17;
        long j7;
        s sVar = this;
        f0.h hVar3 = f0.h.f2189j;
        boolean z6 = set instanceof h0.h;
        String str3 = "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>";
        h0.e eVar = sVar.f4739h;
        j.z zVar5 = sVar.f4743l;
        HashMap map2 = sVar.f4744m;
        j.z zVar6 = sVar.f4736e;
        j.a0 a0Var = sVar.f4738g;
        if (z6) {
            j.a0 a0Var2 = ((h0.h) set).f2589d;
            Object[] objArr4 = a0Var2.f3067b;
            long[] jArr4 = a0Var2.f3066a;
            j5 = -9187201950435737472L;
            int length = jArr4.length - 2;
            if (length >= 0) {
                int i18 = 0;
                z2 = false;
                while (true) {
                    int i19 = 8;
                    long j8 = jArr4[i18];
                    j.z zVar7 = zVar5;
                    if ((((~j8) << 7) & j8 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i20 = 8 - ((~(i18 - length)) >>> 31);
                        int i21 = 0;
                        while (i21 < i20) {
                            if ((j8 & 255) < 128) {
                                Object obj3 = objArr4[(i18 << 3) + i21];
                                jArr3 = jArr4;
                                if (obj3 instanceof y) {
                                    hVar2 = hVar3;
                                    if (!((y) obj3).e(2)) {
                                    }
                                    j8 = j6 >> 8;
                                    i21 = i12 + 1;
                                    i19 = 8;
                                    objArr4 = objArr3;
                                    jArr4 = jArr3;
                                    hVar3 = hVar2;
                                    length = i13;
                                    i18 = i14;
                                    i20 = i11;
                                    zVar7 = zVar3;
                                } else {
                                    hVar2 = hVar3;
                                }
                                if (sVar.f4741j) {
                                    zVar3 = zVar7;
                                    i11 = i20;
                                    i12 = i21;
                                    i13 = length;
                                    i14 = i18;
                                    j6 = j8;
                                    objArr3 = objArr4;
                                } else {
                                    zVar3 = zVar7;
                                    if (zVar3.b(obj3)) {
                                        sVar.f4741j = true;
                                        try {
                                            Object objE = zVar3.e(obj3);
                                            if (objE != null) {
                                                objArr3 = objArr4;
                                                try {
                                                    if (objE instanceof j.a0) {
                                                        j.a0 a0Var3 = (j.a0) objE;
                                                        Object[] objArr5 = a0Var3.f3067b;
                                                        long[] jArr5 = a0Var3.f3066a;
                                                        int length2 = jArr5.length - 2;
                                                        if (length2 >= 0) {
                                                            j6 = j8;
                                                            int i22 = 0;
                                                            while (true) {
                                                                long j9 = jArr5[i22];
                                                                i13 = length;
                                                                i14 = i18;
                                                                if ((((~j9) << 7) & j9 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                    int i23 = 8 - ((~(i22 - length2)) >>> 31);
                                                                    int i24 = 0;
                                                                    while (i24 < i23) {
                                                                        if ((j9 & 255) < 128) {
                                                                            i16 = i21;
                                                                            n0 n0Var = (n0) objArr5[(i22 << 3) + i24];
                                                                            b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>", n0Var);
                                                                            i17 = i24;
                                                                            Object obj4 = map2.get(n0Var);
                                                                            j7 = j9;
                                                                            b3 b3Var = n0Var.f2278f;
                                                                            if (b3Var == null) {
                                                                                b3Var = hVar2;
                                                                            }
                                                                            if (b3Var.a(n0Var.h().f2271f, obj4)) {
                                                                                zVar4 = zVar3;
                                                                                i15 = i20;
                                                                                eVar.b(n0Var);
                                                                            } else {
                                                                                Object objE2 = zVar6.e(n0Var);
                                                                                if (objE2 == null) {
                                                                                    zVar4 = zVar3;
                                                                                    i15 = i20;
                                                                                } else if (objE2 instanceof j.a0) {
                                                                                    j.a0 a0Var4 = (j.a0) objE2;
                                                                                    Object[] objArr6 = a0Var4.f3067b;
                                                                                    long[] jArr6 = a0Var4.f3066a;
                                                                                    int length3 = jArr6.length - 2;
                                                                                    if (length3 >= 0) {
                                                                                        i15 = i20;
                                                                                        int i25 = 0;
                                                                                        while (true) {
                                                                                            long j10 = jArr6[i25];
                                                                                            zVar4 = zVar3;
                                                                                            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                                                if (i25 != length3) {
                                                                                                    break;
                                                                                                    break;
                                                                                                }
                                                                                                i25++;
                                                                                                i19 = 8;
                                                                                                zVar3 = zVar4;
                                                                                            } else {
                                                                                                int i26 = 8 - ((~(i25 - length3)) >>> 31);
                                                                                                for (int i27 = 0; i27 < i26; i27++) {
                                                                                                    if ((j10 & 255) < 128) {
                                                                                                        a0Var.a(objArr6[(i25 << 3) + i27]);
                                                                                                        z2 = true;
                                                                                                    }
                                                                                                    j10 >>= i19;
                                                                                                }
                                                                                                if (i26 != i19) {
                                                                                                    break;
                                                                                                }
                                                                                                if (i25 != length3) {
                                                                                                    break;
                                                                                                }
                                                                                                i25++;
                                                                                                i19 = 8;
                                                                                                zVar3 = zVar4;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        zVar4 = zVar3;
                                                                                        i15 = i20;
                                                                                    }
                                                                                } else {
                                                                                    zVar4 = zVar3;
                                                                                    i15 = i20;
                                                                                    a0Var.a(objE2);
                                                                                    z2 = true;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            zVar4 = zVar3;
                                                                            i15 = i20;
                                                                            i16 = i21;
                                                                            i17 = i24;
                                                                            j7 = j9;
                                                                        }
                                                                        j9 = j7 >> 8;
                                                                        i24 = i17 + 1;
                                                                        i19 = 8;
                                                                        i21 = i16;
                                                                        i20 = i15;
                                                                        zVar3 = zVar4;
                                                                    }
                                                                    zVar3 = zVar3;
                                                                    i11 = i20;
                                                                    i12 = i21;
                                                                    if (i23 != i19) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    zVar3 = zVar3;
                                                                    i11 = i20;
                                                                    i12 = i21;
                                                                }
                                                                if (i22 == length2) {
                                                                    break;
                                                                }
                                                                i22++;
                                                                i19 = 8;
                                                                length = i13;
                                                                i18 = i14;
                                                                i21 = i12;
                                                                i20 = i11;
                                                                zVar3 = zVar3;
                                                            }
                                                        }
                                                    } else {
                                                        zVar3 = zVar3;
                                                        i11 = i20;
                                                        i12 = i21;
                                                        i13 = length;
                                                        i14 = i18;
                                                        j6 = j8;
                                                        n0 n0Var2 = (n0) objE;
                                                        Object obj5 = map2.get(n0Var2);
                                                        b3 b3Var2 = n0Var2.f2278f;
                                                        if (b3Var2 == null) {
                                                            b3Var2 = hVar2;
                                                        }
                                                        if (b3Var2.a(n0Var2.h().f2271f, obj5)) {
                                                            eVar.b(n0Var2);
                                                        } else {
                                                            Object objE3 = zVar6.e(n0Var2);
                                                            if (objE3 != null) {
                                                                if (objE3 instanceof j.a0) {
                                                                    j.a0 a0Var5 = (j.a0) objE3;
                                                                    Object[] objArr7 = a0Var5.f3067b;
                                                                    long[] jArr7 = a0Var5.f3066a;
                                                                    int length4 = jArr7.length - 2;
                                                                    if (length4 >= 0) {
                                                                        int i28 = 0;
                                                                        while (true) {
                                                                            long j11 = jArr7[i28];
                                                                            if ((((~j11) << 7) & j11 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                                if (i28 != length4) {
                                                                                    break;
                                                                                    break;
                                                                                }
                                                                                i28++;
                                                                            } else {
                                                                                int i29 = 8 - ((~(i28 - length4)) >>> 31);
                                                                                for (int i30 = 0; i30 < i29; i30++) {
                                                                                    if ((j11 & 255) < 128) {
                                                                                        a0Var.a(objArr7[(i28 << 3) + i30]);
                                                                                        z2 = true;
                                                                                    }
                                                                                    j11 >>= 8;
                                                                                }
                                                                                if (i29 != 8) {
                                                                                    break;
                                                                                }
                                                                                if (i28 != length4) {
                                                                                    break;
                                                                                }
                                                                                i28++;
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    a0Var.a(objE3);
                                                                    z2 = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    sVar = this;
                                                    sVar.f4741j = false;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    z5 = false;
                                                    sVar = this;
                                                    sVar.f4741j = z5;
                                                    throw th;
                                                }
                                            } else {
                                                objArr3 = objArr4;
                                            }
                                            i11 = i20;
                                            i12 = i21;
                                            i13 = length;
                                            i14 = i18;
                                            j6 = j8;
                                            sVar = this;
                                            sVar.f4741j = false;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            z5 = false;
                                        }
                                    } else {
                                        zVar3 = zVar3;
                                        objArr3 = objArr4;
                                        i11 = i20;
                                        i12 = i21;
                                        i13 = length;
                                        i14 = i18;
                                        j6 = j8;
                                    }
                                }
                                Object objE4 = zVar6.e(obj3);
                                if (objE4 != null) {
                                    if (objE4 instanceof j.a0) {
                                        j.a0 a0Var6 = (j.a0) objE4;
                                        Object[] objArr8 = a0Var6.f3067b;
                                        long[] jArr8 = a0Var6.f3066a;
                                        int length5 = jArr8.length - 2;
                                        if (length5 >= 0) {
                                            int i31 = 0;
                                            while (true) {
                                                long j12 = jArr8[i31];
                                                if ((((~j12) << 7) & j12 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                    if (i31 != length5) {
                                                        break;
                                                        break;
                                                    }
                                                    i31++;
                                                } else {
                                                    int i32 = 8 - ((~(i31 - length5)) >>> 31);
                                                    for (int i33 = 0; i33 < i32; i33++) {
                                                        if ((j12 & 255) < 128) {
                                                            a0Var.a(objArr8[(i31 << 3) + i33]);
                                                            z2 = true;
                                                        }
                                                        j12 >>= 8;
                                                    }
                                                    if (i32 != 8) {
                                                        break;
                                                    }
                                                    if (i31 != length5) {
                                                        break;
                                                    }
                                                    i31++;
                                                }
                                            }
                                        }
                                    } else {
                                        a0Var.a(objE4);
                                        z2 = true;
                                    }
                                }
                                j8 = j6 >> 8;
                                i21 = i12 + 1;
                                i19 = 8;
                                objArr4 = objArr3;
                                jArr4 = jArr3;
                                hVar3 = hVar2;
                                length = i13;
                                i18 = i14;
                                i20 = i11;
                                zVar7 = zVar3;
                            } else {
                                jArr3 = jArr4;
                                hVar2 = hVar3;
                            }
                            i11 = i20;
                            i12 = i21;
                            i13 = length;
                            i14 = i18;
                            j6 = j8;
                            zVar3 = zVar7;
                            objArr3 = objArr4;
                            j8 = j6 >> 8;
                            i21 = i12 + 1;
                            i19 = 8;
                            objArr4 = objArr3;
                            jArr4 = jArr3;
                            hVar3 = hVar2;
                            length = i13;
                            i18 = i14;
                            i20 = i11;
                            zVar7 = zVar3;
                        }
                        jArr2 = jArr4;
                        hVar = hVar3;
                        int i34 = length;
                        int i35 = i18;
                        zVar2 = zVar7;
                        objArr2 = objArr4;
                        if (i20 != i19) {
                            break;
                        }
                        length = i34;
                        i10 = i35;
                    } else {
                        jArr2 = jArr4;
                        hVar = hVar3;
                        zVar2 = zVar7;
                        objArr2 = objArr4;
                        i10 = i18;
                    }
                    if (i10 == length) {
                        break;
                    }
                    i18 = i10 + 1;
                    objArr4 = objArr2;
                    jArr4 = jArr2;
                    hVar3 = hVar;
                    zVar5 = zVar2;
                }
            } else {
                z2 = false;
            }
        } else {
            j.z zVar8 = zVar5;
            j5 = -9187201950435737472L;
            Iterator it3 = set.iterator();
            boolean z7 = false;
            while (it3.hasNext()) {
                Object next = it3.next();
                if (!(next instanceof y) || ((y) next).e(2)) {
                    if (sVar.f4741j) {
                        it = it3;
                        obj = next;
                        str = str3;
                        map = map2;
                        i5 = 0;
                    } else {
                        j.z zVar9 = zVar8;
                        if (zVar9.b(next)) {
                            sVar.f4741j = true;
                            try {
                                Object objE5 = zVar9.e(next);
                                if (objE5 == null) {
                                    it = it3;
                                    obj = next;
                                    str = str3;
                                    zVar8 = zVar9;
                                    map = map2;
                                } else if (objE5 instanceof j.a0) {
                                    j.a0 a0Var7 = (j.a0) objE5;
                                    Object[] objArr9 = a0Var7.f3067b;
                                    long[] jArr9 = a0Var7.f3066a;
                                    int length6 = jArr9.length - 2;
                                    if (length6 >= 0) {
                                        boolean z8 = z7;
                                        map = map2;
                                        int i36 = 0;
                                        while (true) {
                                            long j13 = jArr9[i36];
                                            long[] jArr10 = jArr9;
                                            Object[] objArr10 = objArr9;
                                            if ((((~j13) << 7) & j13 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                int i37 = 8 - ((~(i36 - length6)) >>> 31);
                                                long j14 = j13;
                                                int i38 = 0;
                                                while (i38 < i37) {
                                                    if ((j14 & 255) < 128) {
                                                        n0 n0Var3 = (n0) objArr10[(i36 << 3) + i38];
                                                        b4.l.c(str3, n0Var3);
                                                        Object obj6 = map.get(n0Var3);
                                                        it2 = it3;
                                                        b3 b3Var3 = n0Var3.f2278f;
                                                        if (b3Var3 == null) {
                                                            b3Var3 = hVar3;
                                                        }
                                                        str2 = str3;
                                                        if (b3Var3.a(n0Var3.h().f2271f, obj6)) {
                                                            obj2 = next;
                                                            zVar = zVar9;
                                                            i6 = i38;
                                                            eVar.b(n0Var3);
                                                        } else {
                                                            Object objE6 = zVar6.e(n0Var3);
                                                            if (objE6 == null) {
                                                                obj2 = next;
                                                                zVar = zVar9;
                                                                i6 = i38;
                                                                z4 = z8;
                                                            } else if (objE6 instanceof j.a0) {
                                                                j.a0 a0Var8 = (j.a0) objE6;
                                                                Object[] objArr11 = a0Var8.f3067b;
                                                                long[] jArr11 = a0Var8.f3066a;
                                                                int length7 = jArr11.length - 2;
                                                                if (length7 >= 0) {
                                                                    zVar = zVar9;
                                                                    i6 = i38;
                                                                    int i39 = 0;
                                                                    while (true) {
                                                                        long j15 = jArr11[i39];
                                                                        obj2 = next;
                                                                        Object[] objArr12 = objArr11;
                                                                        if ((((~j15) << 7) & j15 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                            int i40 = 8 - ((~(i39 - length7)) >>> 31);
                                                                            int i41 = 0;
                                                                            while (i41 < i40) {
                                                                                if ((j15 & 255) < 128) {
                                                                                    a0Var.a(objArr12[(i39 << 3) + i41]);
                                                                                    z8 = true;
                                                                                }
                                                                                j15 >>= 8;
                                                                                i41++;
                                                                                jArr11 = jArr11;
                                                                            }
                                                                            jArr = jArr11;
                                                                            if (i40 != 8) {
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            jArr = jArr11;
                                                                        }
                                                                        if (i39 == length7) {
                                                                            break;
                                                                        }
                                                                        i39++;
                                                                        next = obj2;
                                                                        objArr11 = objArr12;
                                                                        jArr11 = jArr;
                                                                    }
                                                                } else {
                                                                    obj2 = next;
                                                                    zVar = zVar9;
                                                                    i6 = i38;
                                                                }
                                                                z4 = z8;
                                                            } else {
                                                                obj2 = next;
                                                                zVar = zVar9;
                                                                i6 = i38;
                                                                a0Var.a(objE6);
                                                                z4 = true;
                                                            }
                                                            z8 = z4;
                                                        }
                                                    } else {
                                                        it2 = it3;
                                                        obj2 = next;
                                                        str2 = str3;
                                                        zVar = zVar9;
                                                        i6 = i38;
                                                    }
                                                    j14 >>= 8;
                                                    i38 = i6 + 1;
                                                    it3 = it2;
                                                    str3 = str2;
                                                    next = obj2;
                                                    zVar9 = zVar;
                                                }
                                                it = it3;
                                                obj = next;
                                                str = str3;
                                                zVar8 = zVar9;
                                                if (i37 != 8) {
                                                    break;
                                                }
                                            } else {
                                                it = it3;
                                                obj = next;
                                                str = str3;
                                                zVar8 = zVar9;
                                            }
                                            if (i36 == length6) {
                                                break;
                                            }
                                            i36++;
                                            it3 = it;
                                            objArr9 = objArr10;
                                            jArr9 = jArr10;
                                            str3 = str;
                                            next = obj;
                                            zVar9 = zVar8;
                                        }
                                        z7 = z8;
                                    } else {
                                        it = it3;
                                        obj = next;
                                        str = str3;
                                        zVar8 = zVar9;
                                        map = map2;
                                    }
                                } else {
                                    it = it3;
                                    obj = next;
                                    str = str3;
                                    zVar8 = zVar9;
                                    map = map2;
                                    n0 n0Var4 = (n0) objE5;
                                    Object obj7 = map.get(n0Var4);
                                    b3 b3Var4 = n0Var4.f2278f;
                                    if (b3Var4 == null) {
                                        b3Var4 = hVar3;
                                    }
                                    if (b3Var4.a(n0Var4.h().f2271f, obj7)) {
                                        eVar.b(n0Var4);
                                    } else {
                                        Object objE7 = zVar6.e(n0Var4);
                                        if (objE7 == null) {
                                            z3 = z7;
                                            break;
                                        }
                                        if (objE7 instanceof j.a0) {
                                            j.a0 a0Var9 = (j.a0) objE7;
                                            Object[] objArr13 = a0Var9.f3067b;
                                            long[] jArr12 = a0Var9.f3066a;
                                            int length8 = jArr12.length - 2;
                                            if (length8 < 0) {
                                                z3 = z7;
                                                break;
                                            }
                                            boolean z9 = z7;
                                            int i42 = 0;
                                            while (true) {
                                                long j16 = jArr12[i42];
                                                if ((((~j16) << 7) & j16 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                    int i43 = 8 - ((~(i42 - length8)) >>> 31);
                                                    long j17 = j16;
                                                    for (int i44 = 0; i44 < i43; i44++) {
                                                        if ((j17 & 255) < 128) {
                                                            a0Var.a(objArr13[(i42 << 3) + i44]);
                                                            z9 = true;
                                                        }
                                                        j17 >>= 8;
                                                    }
                                                    if (i43 != 8) {
                                                        z3 = z9;
                                                        break;
                                                    }
                                                }
                                                if (i42 == length8) {
                                                    z7 = z9;
                                                    z3 = z7;
                                                    break;
                                                }
                                                i42++;
                                            }
                                        } else {
                                            a0Var.a(objE7);
                                            z3 = true;
                                        }
                                        z7 = z3;
                                    }
                                }
                                i5 = 0;
                                sVar.f4741j = false;
                            } catch (Throwable th3) {
                                sVar.f4741j = false;
                                throw th3;
                            }
                        } else {
                            zVar8 = zVar9;
                            it = it3;
                            obj = next;
                            str = str3;
                            map = map2;
                            i5 = 0;
                        }
                    }
                    boolean z10 = z7;
                    Object objE8 = zVar6.e(obj);
                    if (objE8 != null) {
                        if (objE8 instanceof j.a0) {
                            j.a0 a0Var10 = (j.a0) objE8;
                            Object[] objArr14 = a0Var10.f3067b;
                            long[] jArr13 = a0Var10.f3066a;
                            int length9 = jArr13.length - 2;
                            if (length9 >= 0) {
                                int i45 = i5;
                                while (true) {
                                    long j18 = jArr13[i45];
                                    if ((((~j18) << 7) & j18 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i45 != length9) {
                                            break;
                                            break;
                                        }
                                        i45++;
                                    } else {
                                        int i46 = 8 - ((~(i45 - length9)) >>> 31);
                                        for (int i47 = i5; i47 < i46; i47++) {
                                            if ((j18 & 255) < 128) {
                                                a0Var.a(objArr14[(i45 << 3) + i47]);
                                                z10 = true;
                                            }
                                            j18 >>= 8;
                                        }
                                        if (i46 != 8) {
                                            break;
                                        }
                                        if (i45 != length9) {
                                            break;
                                        }
                                        i45++;
                                    }
                                }
                            }
                        } else {
                            a0Var.a(objE8);
                            z10 = true;
                        }
                    }
                    z7 = z10;
                } else {
                    it = it3;
                    str = str3;
                    map = map2;
                }
                it3 = it;
                map2 = map;
                str3 = str;
            }
            z2 = z7;
        }
        int i48 = 0;
        if (!sVar.f4741j && (i7 = eVar.f2578f) != 0) {
            Object[] objArr15 = eVar.f2576d;
            int i49 = 0;
            while (i49 < i7) {
                n0 n0Var5 = (n0) objArr15[i49];
                int iHashCode = Long.hashCode(m.j().g());
                Object objE9 = zVar6.e(n0Var5);
                if (objE9 != null) {
                    boolean z11 = objE9 instanceof j.a0;
                    j.z zVar10 = sVar.f4737f;
                    if (z11) {
                        j.a0 a0Var11 = (j.a0) objE9;
                        Object[] objArr16 = a0Var11.f3067b;
                        long[] jArr14 = a0Var11.f3066a;
                        int length10 = jArr14.length - 2;
                        if (length10 >= 0) {
                            int i50 = i48;
                            while (true) {
                                long j19 = jArr14[i50];
                                i9 = i49;
                                if ((((~j19) << 7) & j19 & j5) != j5) {
                                    int i51 = 8 - ((~(i50 - length10)) >>> 31);
                                    int i52 = 0;
                                    while (i52 < i51) {
                                        if ((j19 & 255) < 128) {
                                            Object obj8 = objArr16[(i50 << 3) + i52];
                                            j.x xVar2 = (j.x) zVar10.e(obj8);
                                            if (xVar2 == null) {
                                                xVar = new j.x();
                                                zVar10.i(obj8, xVar);
                                            } else {
                                                xVar = xVar2;
                                            }
                                            sVar.b(n0Var5, iHashCode, obj8, xVar);
                                        }
                                        j19 >>= 8;
                                        i52++;
                                        i7 = i7;
                                        objArr15 = objArr15;
                                    }
                                    i8 = i7;
                                    objArr = objArr15;
                                    if (i51 != 8) {
                                        break;
                                    }
                                } else {
                                    i8 = i7;
                                    objArr = objArr15;
                                }
                                if (i50 == length10) {
                                    break;
                                }
                                i50++;
                                i49 = i9;
                                i7 = i8;
                                objArr15 = objArr;
                            }
                        } else {
                            i8 = i7;
                            objArr = objArr15;
                            i9 = i49;
                        }
                    } else {
                        i8 = i7;
                        objArr = objArr15;
                        i9 = i49;
                        j.x xVar3 = (j.x) zVar10.e(objE9);
                        if (xVar3 == null) {
                            xVar3 = new j.x();
                            zVar10.i(objE9, xVar3);
                        }
                        sVar.b(n0Var5, iHashCode, objE9, xVar3);
                    }
                } else {
                    i8 = i7;
                    objArr = objArr15;
                    i9 = i49;
                }
                i49 = i9 + 1;
                i7 = i8;
                objArr15 = objArr;
                i48 = 0;
            }
            eVar.g();
        }
        return z2;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x008b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x008d A[LOOP:0: B:15:0x0048->B:28:0x008d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:37:0x0090 A[EDGE_INSN: B:37:0x0090->B:29:0x0090 BREAK  A[LOOP:0: B:15:0x0048->B:28:0x008d], SYNTHETIC] */
    public final void b(Object obj, int i5, Object obj2, j.x xVar) {
        int i6;
        if (this.f4742k > 0) {
            return;
        }
        int iB = xVar.b(obj);
        if (iB < 0) {
            iB = ~iB;
            i6 = -1;
        } else {
            i6 = xVar.f3170c[iB];
        }
        xVar.f3169b[iB] = obj;
        xVar.f3170c[iB] = i5;
        if ((obj instanceof n0) && i6 != i5) {
            m0 m0VarH = ((n0) obj).h();
            this.f4744m.put(obj, m0VarH.f2271f);
            j.x xVar2 = m0VarH.f2270e;
            j.z zVar = this.f4743l;
            d4.a.G(zVar, obj);
            Object[] objArr = xVar2.f3169b;
            long[] jArr = xVar2.f3168a;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i7 = 0;
                while (true) {
                    long j5 = jArr[i7];
                    if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i7 != length) {
                            break;
                            break;
                        }
                        i7++;
                    } else {
                        int i8 = 8 - ((~(i7 - length)) >>> 31);
                        for (int i9 = 0; i9 < i8; i9++) {
                            if ((j5 & 255) < 128) {
                                x xVar3 = (x) objArr[(i7 << 3) + i9];
                                if (xVar3 instanceof y) {
                                    ((y) xVar3).f(2);
                                }
                                d4.a.e(zVar, xVar3, obj);
                            }
                            j5 >>= 8;
                        }
                        if (i8 != 8) {
                            break;
                        } else if (i7 != length) {
                            break;
                        } else {
                            i7++;
                        }
                    }
                }
            }
        }
        if (i6 == -1) {
            if (obj instanceof y) {
                ((y) obj).f(2);
            }
            d4.a.e(this.f4736e, obj, obj2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00cd  */
    public final void c() {
        long[] jArr;
        long[] jArr2;
        long j5;
        char c5;
        long j6;
        int i5;
        boolean z2;
        Object obj;
        long j7;
        Object obj2;
        j.z zVar = this.f4737f;
        long[] jArr3 = zVar.f3176a;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i6 = 0;
        while (true) {
            long j8 = jArr3[i6];
            char c6 = 7;
            long j9 = -9187201950435737472L;
            if ((((~j8) << 7) & j8 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i7 = 8;
                int i8 = 8 - ((~(i6 - length)) >>> 31);
                int i9 = 0;
                while (i9 < i8) {
                    if ((j8 & 255) < 128) {
                        int i10 = (i6 << 3) + i9;
                        c5 = c6;
                        Object obj3 = zVar.f3177b[i10];
                        j6 = j9;
                        j.x xVar = (j.x) zVar.f3178c[i10];
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.OwnerScope", obj3);
                        boolean zW = ((f1) obj3).w();
                        if (zW) {
                            jArr2 = jArr3;
                            j5 = j8;
                            z2 = zW;
                        } else {
                            Object[] objArr = xVar.f3169b;
                            int[] iArr = xVar.f3170c;
                            long[] jArr4 = xVar.f3168a;
                            int i11 = i7;
                            int length2 = jArr4.length - 2;
                            if (length2 >= 0) {
                                jArr2 = jArr3;
                                j5 = j8;
                                int i12 = 0;
                                while (true) {
                                    long j10 = jArr4[i12];
                                    long[] jArr5 = jArr4;
                                    z2 = zW;
                                    if ((((~j10) << c5) & j10 & j6) != j6) {
                                        int i13 = 8 - ((~(i12 - length2)) >>> 31);
                                        int i14 = 0;
                                        while (i14 < i13) {
                                            if ((j10 & 255) < 128) {
                                                int i15 = (i12 << 3) + i14;
                                                j7 = j10;
                                                Object obj4 = objArr[i15];
                                                int i16 = iArr[i15];
                                                j.z zVar2 = this.f4736e;
                                                d4.a.F(zVar2, obj4, obj3);
                                                obj2 = obj3;
                                                if ((obj4 instanceof n0) && !zVar2.b(obj4)) {
                                                    d4.a.G(this.f4743l, obj4);
                                                    this.f4744m.remove(obj4);
                                                }
                                            } else {
                                                j7 = j10;
                                                obj2 = obj3;
                                            }
                                            j10 = j7 >> i11;
                                            i14++;
                                            obj3 = obj2;
                                        }
                                        obj = obj3;
                                        if (i13 != i11) {
                                            break;
                                        }
                                    } else {
                                        obj = obj3;
                                    }
                                    if (i12 == length2) {
                                        break;
                                    }
                                    i12++;
                                    zW = z2;
                                    jArr4 = jArr5;
                                    obj3 = obj;
                                    i11 = 8;
                                }
                            } else {
                                jArr2 = jArr3;
                                j5 = j8;
                                z2 = zW;
                            }
                        }
                        if (!z2) {
                            zVar.h(i10);
                        }
                        i5 = 8;
                    } else {
                        jArr2 = jArr3;
                        j5 = j8;
                        c5 = c6;
                        j6 = j9;
                        i5 = i7;
                    }
                    i9++;
                    i7 = i5;
                    j8 = j5 >> i5;
                    c6 = c5;
                    j9 = j6;
                    jArr3 = jArr2;
                }
                jArr = jArr3;
                if (i8 != i7) {
                    return;
                }
            } else {
                jArr = jArr3;
            }
            if (i6 == length) {
                return;
            }
            i6++;
            jArr3 = jArr;
        }
    }
}
