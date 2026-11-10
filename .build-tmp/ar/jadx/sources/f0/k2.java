package f0;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k2 extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List f2218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f2219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f2220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j.a0 f2221g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public j.a0 f2222h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j.a0 f2223i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Set f2224j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public j.a0 f2225k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2226l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ g f2227m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l2 f2228n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k2(l2 l2Var, t3.c cVar) {
        super(3, cVar);
        this.f2228n = l2Var;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0075 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0077 A[Catch: all -> 0x0029, LOOP:1: B:12:0x0041->B:22:0x0077, LOOP_END, TryCatch #0 {all -> 0x0029, blocks: (B:4:0x000b, B:6:0x0018, B:9:0x002c, B:12:0x0041, B:14:0x0052, B:16:0x005c, B:18:0x0062, B:19:0x006f, B:24:0x0082, B:27:0x008f, B:29:0x009a, B:31:0x00a4, B:33:0x00aa, B:34:0x00b4, B:37:0x00bc, B:38:0x00bf, B:41:0x00cf, B:43:0x00da, B:45:0x00e4, B:47:0x00ea, B:48:0x00f7, B:51:0x00ff, B:52:0x0102, B:22:0x0077), top: B:57:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00ba A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x00bc A[Catch: all -> 0x0029, LOOP:3: B:27:0x008f->B:37:0x00bc, LOOP_END, TryCatch #0 {all -> 0x0029, blocks: (B:4:0x000b, B:6:0x0018, B:9:0x002c, B:12:0x0041, B:14:0x0052, B:16:0x005c, B:18:0x0062, B:19:0x006f, B:24:0x0082, B:27:0x008f, B:29:0x009a, B:31:0x00a4, B:33:0x00aa, B:34:0x00b4, B:37:0x00bc, B:38:0x00bf, B:41:0x00cf, B:43:0x00da, B:45:0x00e4, B:47:0x00ea, B:48:0x00f7, B:51:0x00ff, B:52:0x0102, B:22:0x0077), top: B:57:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x00ff A[Catch: all -> 0x0029, LOOP:5: B:41:0x00cf->B:51:0x00ff, LOOP_END, TryCatch #0 {all -> 0x0029, blocks: (B:4:0x000b, B:6:0x0018, B:9:0x002c, B:12:0x0041, B:14:0x0052, B:16:0x005c, B:18:0x0062, B:19:0x006f, B:24:0x0082, B:27:0x008f, B:29:0x009a, B:31:0x00a4, B:33:0x00aa, B:34:0x00b4, B:37:0x00bc, B:38:0x00bf, B:41:0x00cf, B:43:0x00da, B:45:0x00e4, B:47:0x00ea, B:48:0x00f7, B:51:0x00ff, B:52:0x0102, B:22:0x0077), top: B:57:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0082 A[EDGE_INSN: B:61:0x0082->B:24:0x0082 BREAK  A[LOOP:1: B:12:0x0041->B:22:0x0077], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00bf A[EDGE_INSN: B:66:0x00bf->B:38:0x00bf BREAK  A[LOOP:3: B:27:0x008f->B:37:0x00bc], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x0102 A[EDGE_INSN: B:71:0x0102->B:52:0x0102 BREAK  A[LOOP:5: B:41:0x00cf->B:51:0x00ff], SYNTHETIC] */
    public static final void f(l2 l2Var, List list, List list2, List list3, j.a0 a0Var, j.a0 a0Var2, j.a0 a0Var3, j.a0 a0Var4) {
        char c5;
        long j5;
        long j6;
        synchronized (l2Var.f2245c) {
            try {
                list.clear();
                list2.clear();
                int size = list3.size();
                for (int i5 = 0; i5 < size; i5++) {
                    e0 e0Var = (e0) list3.get(i5);
                    e0Var.a();
                    l2Var.F(e0Var);
                }
                list3.clear();
                Object[] objArr = a0Var.f3067b;
                long[] jArr = a0Var.f3066a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i6 = 0;
                    j5 = 255;
                    while (true) {
                        long j7 = jArr[i6];
                        c5 = 7;
                        j6 = -9187201950435737472L;
                        if ((((~j7) << 7) & j7 & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i6 != length) {
                                break;
                                break;
                            }
                            i6++;
                        } else {
                            int i7 = 8 - ((~(i6 - length)) >>> 31);
                            for (int i8 = 0; i8 < i7; i8++) {
                                if ((j7 & 255) < 128) {
                                    e0 e0Var2 = (e0) objArr[(i6 << 3) + i8];
                                    e0Var2.a();
                                    l2Var.F(e0Var2);
                                }
                                j7 >>= 8;
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
                } else {
                    c5 = 7;
                    j5 = 255;
                    j6 = -9187201950435737472L;
                }
                a0Var.b();
                Object[] objArr2 = a0Var2.f3067b;
                long[] jArr2 = a0Var2.f3066a;
                int length2 = jArr2.length - 2;
                if (length2 >= 0) {
                    int i9 = 0;
                    while (true) {
                        long j8 = jArr2[i9];
                        if ((((~j8) << c5) & j8 & j6) == j6) {
                            if (i9 != length2) {
                                break;
                                break;
                            }
                            i9++;
                        } else {
                            int i10 = 8 - ((~(i9 - length2)) >>> 31);
                            for (int i11 = 0; i11 < i10; i11++) {
                                if ((j8 & j5) < 128) {
                                    ((e0) objArr2[(i9 << 3) + i11]).g();
                                }
                                j8 >>= 8;
                            }
                            if (i10 != 8) {
                                break;
                            } else if (i9 != length2) {
                                break;
                            } else {
                                i9++;
                            }
                        }
                    }
                }
                a0Var2.b();
                a0Var3.b();
                Object[] objArr3 = a0Var4.f3067b;
                long[] jArr3 = a0Var4.f3066a;
                int length3 = jArr3.length - 2;
                if (length3 >= 0) {
                    int i12 = 0;
                    while (true) {
                        long j9 = jArr3[i12];
                        if ((((~j9) << c5) & j9 & j6) == j6) {
                            if (i12 != length3) {
                                break;
                                break;
                            }
                            i12++;
                        } else {
                            int i13 = 8 - ((~(i12 - length3)) >>> 31);
                            for (int i14 = 0; i14 < i13; i14++) {
                                if ((j9 & j5) < 128) {
                                    e0 e0Var3 = (e0) objArr3[(i12 << 3) + i14];
                                    e0Var3.a();
                                    l2Var.F(e0Var3);
                                }
                                j9 >>= 8;
                            }
                            if (i13 != 8) {
                                break;
                            } else if (i12 != length3) {
                                break;
                            } else {
                                i12++;
                            }
                        }
                    }
                }
                a0Var4.b();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final void g(List list, l2 l2Var) {
        list.clear();
        synchronized (l2Var.f2245c) {
            try {
                ArrayList arrayList = l2Var.f2253k;
                int size = arrayList.size();
                for (int i5 = 0; i5 < size; i5++) {
                    list.add((g1) arrayList.get(i5));
                }
                l2Var.f2253k.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        k2 k2Var = new k2(this.f2228n, (t3.c) obj3);
        k2Var.f2227m = (g) obj2;
        k2Var.invokeSuspend(p3.n.f5026a);
        return u3.a.f5838d;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0099 A[DONT_GENERATE] */
    /* JADX WARN: Code duplicated, block: B:17:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:21:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:22:0x00d0 A[Catch: all -> 0x00e7, TRY_LEAVE, TryCatch #0 {, blocks: (B:19:0x00c8, B:22:0x00d0), top: B:66:0x00c8 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:40:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:43:0x0123  */
    /* JADX WARN: Code duplicated, block: B:65:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:66:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0123 -> B:44:0x012b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01d8 -> B:12:0x0094). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.visitors.regions.maker.SynchronizedRegionMaker.traverseMonitorExits(SynchronizedRegionMaker.java:97)
        	at jadx.core.dex.visitors.regions.maker.SynchronizedRegionMaker.traverseMonitorExits(SynchronizedRegionMaker.java:109)
        */
    @Override // v3.a
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f0.k2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
